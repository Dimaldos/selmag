package ru.dimaldos.selmag.catalogueservice.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProductPayload(
        @NotBlank(message = "{catalogue.products.edit.errors.title.blank}")
        @Size(min = 3, max = 50, message = "{catalogue.products.edit.errors.title.size_limit}")
        String title,
        @Size(max = 1000, message = "{catalogue.products.edit.errors.details.size_limit}")
        String details) {
}
