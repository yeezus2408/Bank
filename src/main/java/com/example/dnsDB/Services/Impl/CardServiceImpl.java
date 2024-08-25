package com.example.dnsDB.Services.Impl;

import com.example.dnsDB.Repository.CardRepository;
import com.example.dnsDB.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl {
    @Autowired
    CardRepository cardRepository;
    public void newCard(Card newCard){
        cardRepository.save(newCard);
    }

    public void removeCard(Long id){
        cardRepository.deleteById(id);
    }
}
