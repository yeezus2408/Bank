package com.example.bank.Services;

import com.example.bank.Repository.cardRepository;
import com.example.bank.Repository.userRepository;
import com.example.bank.Services.lib.GeneratorCardNum;
import com.example.bank.models.card;
import com.example.bank.models.user;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class cardService {
    private final cardRepository cardRepository;
    private final userRepository userRepository;
    @Autowired
    GeneratorCardNum generatorCardNum;

    public void insertCard(card card) {
        user currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        user owner = userRepository.getReferenceById(currentUser.getId());
        String typeCard = card.getCardCategory();

        switch (typeCard) {
            case "Кредитная":
                card.setCardBalance(card.getCardBalance());
                break;
            case "Депетовая", "Накопительная":
                card.setCardBalance(0);
                break;
        }
        card.setCardNumber(generatorCardNum.cardNum());
        card.setCardCVV(generatorCardNum.cardCVV());
        card.setCardStatus("active");
        card.setCardPin(card.getCardPin());
        currentUser.setAgreement(generatorCardNum.agreement());
        card.setOwner(owner);
        card.setCardName(currentUser.getFirstname() + " " + currentUser.getLastname());
        cardRepository.save(card);
    }
    

    public List<card> getAllCardsByOwner() {
        user currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currentUser == null){
            return null;
        }
        else {
           return cardRepository.findAllByOwner_Id(currentUser.getId());
        }
    }


    public card findByCardNum(String cardNum){
        List<card> cards = getAllCardsByOwner();
        card carda = null;
        if(cards == null){
            return null;
        }
        for (card card : cards) {
            if(card.getCardNumber().equals(cardNum)){
                carda = card;
            }
        }
        return carda;
    }
}
