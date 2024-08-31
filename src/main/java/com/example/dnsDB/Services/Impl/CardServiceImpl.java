package com.example.dnsDB.Services.Impl;

import com.example.dnsDB.Repository.CardRepository;
import com.example.dnsDB.Services.CardService;
import com.example.dnsDB.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;
    public void newCard(Card newCard){

        String newCardNum = generatorBankNumber();
        if(!cardRepository.existsCardByCardNum(newCardNum)){
            newCard.setCardNum(newCardNum);
        }else {
            newCard.setCardNum(generatorBankNumber());
        }

        newCard.setCvv(generatorBankCVV());
        cardRepository.save(newCard);
    }

    public void removeCard(Long id){
        cardRepository.deleteById(id);
    }

    public void updateCard(Long id){
        Card card = cardRepository.findById(id).get();
        if (card != null ){
            card.setStatus("Blocked");
            cardRepository.save(card);
        }
        else {
            throw new RuntimeException("Card not found");
        }

    }

    @Override
    public String generatorBankNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }
        String formattedCardNumber =
                sb.substring(0, 4) + " " +
                        sb.substring(4, 8) + " " +
                        sb.substring(8, 12) + " " +
                        sb.substring(12);
        return formattedCardNumber;
    }

    @Override
    public Integer generatorBankCVV() {
        Random random = new Random();
        Integer cvv = random.nextInt(999);
        return cvv;
    }

}
