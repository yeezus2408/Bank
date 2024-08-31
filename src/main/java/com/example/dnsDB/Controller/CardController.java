package com.example.dnsDB.Controller;

import com.example.dnsDB.Services.Impl.CardServiceImpl;
import com.example.dnsDB.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
    @Autowired
    CardServiceImpl cardServiceImpl;

    @PostMapping("/saveCard")
    public void newCard(@RequestBody Card card){
        cardServiceImpl.newCard(card);
    }

    @PostMapping("/removeCard/{id}")
    public void removeCard(@PathVariable Long id){ cardServiceImpl.removeCard(id);}

    @PostMapping("/updateCard/{id}")
    public void updateCard(@PathVariable Long id){cardServiceImpl.updateCard(id);}
}
