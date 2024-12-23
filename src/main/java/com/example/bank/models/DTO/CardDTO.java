package com.example.bank.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private Long id;
    private String cardNumber;
    private String cardName;
    private Integer cardCVV;
    private LocalDate cardExpiryDate;
    private String cardCategory;
    private String cardStatus;
    private String cardPin;
    private Long owner_id;
}
