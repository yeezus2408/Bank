package com.example.bank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "operations")
public class operations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "from_card")
    private String fromCard;
    @Column(name = "to_card")
    private String toCard;
    @Column(name = "date_operation")
    private LocalDate dateOperation;

    @ManyToOne(fetch = FetchType.LAZY)
    private user fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private user toUser;
}
