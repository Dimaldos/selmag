package ru.dimaldos.sandbox.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewProductPayload(
        @NotBlank(message = "{catalogue.products.create.errors.title.blank}")
        @Size(min = 3, max = 50, message = "{catalogue.products.create.errors.title.size_limit}")
        String title,
        @Size(max = 1000, message = "{catalogue.products.create.errors.details.size_limit}")
        String details) {
}
