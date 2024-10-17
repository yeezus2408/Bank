package com.example.bank.Controller;

import com.example.bank.Services.cardService;
import com.example.bank.Services.userService;
import com.example.bank.models.card;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class cardController {
    private final cardService cardService;
    private final userService userService;


    @GetMapping("/insertCard")
    public String insertCard(Model model, @ModelAttribute("card") card card) {
        model.addAttribute("card", card);
        model.addAttribute("user", userService.hasAuhorize());
        return "insertCard";
    }

    @PostMapping("/insertCard")
    public void insertCard(@ModelAttribute("card") card card){
        cardService.insertCard(card);
    }


    @GetMapping("/myCards")
    public String myCards(Model model){

        model.addAttribute("cards", cardService.getAllCardsByOwner());
//        model.addAttribute("cardByPrincipal", cardService.getCardByPrincipal(principal));
        return "myCards";
    }
}
