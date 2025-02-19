package code.challenge.infra.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateCardDTO(
        @NotBlank(message = "Card number is required")
        String number,
        @NotNull(message = "Expiration date is required")
        Date expirationDate,
        @NotBlank(message = "CVV is required")
        String cvv,
        @NotBlank(message = "Country is required")
        String country,
        @NotBlank(message = "Account is required")
        String account
) {
}