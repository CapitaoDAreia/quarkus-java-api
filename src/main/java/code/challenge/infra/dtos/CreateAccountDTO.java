package code.challenge.infra.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountDTO(
        @NotBlank(message = "customerCpf cannot be null")
        String customerCpf,
        @NotBlank(message = "accountNumber cannot be null")
        String accountNumber,
        @NotBlank(message = "agency cannot be null")
        String agency
) {
}