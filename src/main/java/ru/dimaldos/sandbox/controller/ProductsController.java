package ru.dimaldos.sandbox.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dimaldos.sandbox.controller.payload.NewProductPayload;
import ru.dimaldos.sandbox.entity.Product;
import ru.dimaldos.sandbox.service.ProductService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping(value = "list")
    public String getProductsList(Model model) {
        model.addAllAttributes(Map.of("products", this.productService.findAllProducts()));
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(@Valid NewProductPayload payload, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(Map.of(
                    "payload", payload,
                    "errors", bindingResult.getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList()
            ));
            return "/catalogue/products/new_product";
        } else {
            Product product = this.productService.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.getId());
        }

    }

}
