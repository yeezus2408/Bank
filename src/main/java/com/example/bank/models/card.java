package com.example.bank.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "cards")
public class card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, name = "card_number")
    private String cardNumber;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_cvv")
    private Integer cardCVV;

    @Column(name = "created_at")
    private LocalDate cardExpiryDate;

    @Column(name = "card_category")
    private String cardCategory;

    @Column(name = "card_status")
    private String cardStatus;

    @Column(name = "card_pin")
    private String cardPin;

    @Column(name = "date_to_end")
    private LocalDate dateToEnd;

    @Column(name = "card_balance")
    private Integer cardBalance;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private user owner;

    @Column(name = "owner_id")
    private Long owner_id;

    @PrePersist
    protected void onCreate() {
        cardExpiryDate = LocalDate.now();
        dateToEnd = cardExpiryDate.plusYears(6);
    }

}
