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

    @Column(name = "type")
    private String type;

    @Column(name = "from_card")
    private String fromCard;
    @Column(name = "to_card")
    private String toCard;
    @Column(name = "date_operation")
    private LocalDate dateOperation;


    @Column(name = "from_user_id")
    private Long fromUser;

    @Column(name = "to_user_id")
    private Long toUser;
}
