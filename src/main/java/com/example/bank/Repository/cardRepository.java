package com.example.bank.Repository;

import com.example.bank.models.card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cardRepository extends JpaRepository<card, Long> {
    card findByOwnerId(Long id);
    card findByCardNumber(String cardNumber);
    List<card> findAllByOwner_Id(Long id);
    card findCardById(Long id);
    card findCardByOwnerPhoneNumber(String phoneNumber);
}
