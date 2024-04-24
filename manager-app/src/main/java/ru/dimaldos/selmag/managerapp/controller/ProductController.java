package ru.dimaldos.selmag.managerapp.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dimaldos.selmag.managerapp.client.BadRequestException;
import ru.dimaldos.selmag.managerapp.controller.payload.UpdateProductPayload;
import ru.dimaldos.selmag.managerapp.entity.Product;
import ru.dimaldos.selmag.managerapp.service.ProductService;

import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
public class ProductController {

    private final ProductService productService;

    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productService.findProduct(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public String getProduct() {
        return "catalogue/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalogue/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute(name = "product", binding = false) Product product, UpdateProductPayload payload, Model model) {
        try {
            this.productService.updateProduct(product.id(), payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAllAttributes(Map.of(
                    "payload", payload,
                    "errors", exception.getErrors()));
            return "catalogue/products/edit";
        }
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productService.deleteProduct(product.id());
        return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model, HttpServletResponse response, Locale locale) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        model.addAllAttributes(Map.of("error",
                this.messageSource.getMessage(exception.getMessage(), null, locale)));
        return "errors/404";
    }

}
