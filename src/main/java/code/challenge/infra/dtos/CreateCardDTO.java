package code.challenge.infra.dtos;

import java.util.Date;

public record CreateCardDTO(

        String number,
        Date expirationDate,
        String cvv,
        String country,
        String account
) {
}