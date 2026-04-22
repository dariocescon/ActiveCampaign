package com.aton.proj.dto;

import jakarta.validation.constraints.NotBlank;

public record AcContactDto(
        @NotBlank String email,
        String firstName,
        String lastName,
        String phone
) {
}
