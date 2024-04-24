package ru.dimaldos.selmag.managerapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dimaldos.selmag.managerapp.client.BadRequestException;
import ru.dimaldos.selmag.managerapp.controller.payload.NewProductPayload;
import ru.dimaldos.selmag.managerapp.entity.Product;
import ru.dimaldos.selmag.managerapp.service.ProductService;

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
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload, Model model) {
        try {
            Product product = this.productService.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAllAttributes(Map.of(
                    "payload", payload,
                    "errors", exception.getErrors()));
            return "/catalogue/products/new_product";
        }
    }

}
