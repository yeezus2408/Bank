package com.example.bank.Repository;

import com.example.bank.models.card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cardRepository extends JpaRepository<card, Long> {
    card findByCardNumber(String cardNumber);
    List<card> findAllByOwnerId(Long id);

    @Query("select c from card c where c.owner_id = :id")
    List<card> findAllByOwner_id(@Param("id") Long id);
    card findCardById(Long id);
    card findCardByOwnerPhoneNumber(String phoneNumber);
}
