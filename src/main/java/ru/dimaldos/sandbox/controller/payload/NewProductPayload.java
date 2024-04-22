package ru.dimaldos.sandbox.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewProductPayload(
        @NotNull
        @NotBlank
        @Size(min = 3, max = 50)
        String title,
        @Size(max = 1000)
        String details) {
}
