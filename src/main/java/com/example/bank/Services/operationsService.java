package com.example.bank.Services;

import com.example.bank.Repository.userRepository;
import com.example.bank.models.card;
import com.example.bank.models.operations;
import com.example.bank.models.user;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class operationsService {
    private final userRepository userRepository;
    private final cardService cardService;
    private final com.example.bank.Repository.cardRepository cardRepository;
    private final com.example.bank.Repository.operationsRepository operationsRepository;

    public void transaction(String cardNumFrom, String cardNumTo, Integer amount, operations operation){
        user currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        user userTo = cardRepository.findByCardNumber(cardNumTo).getOwner();
        card cardFrom = cardRepository.findByCardNumber(cardNumFrom);
        card cardTo = cardService.findByCardNum(cardNumTo);

        if(cardNumTo.length() < 12){
            cardTo = cardRepository.findCardByOwnerPhoneNumber(cardNumTo);
        }

        operation.setType("Перевод");
        operation.setFromUser(currentUser.getId());
        operation.setToUser(userTo.getId());
        operation.setDateOperation(LocalDate.now());
        cardFrom.setCardBalance(cardFrom.getCardBalance() - amount);
        cardTo.setCardBalance(cardTo.getCardBalance() + amount);
        cardRepository.save(cardTo);
        cardRepository.save(cardFrom);
        operationsRepository.save(operation);
    }


}
