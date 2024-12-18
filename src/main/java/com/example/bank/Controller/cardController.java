package com.example.bank.Controller;

import com.example.bank.Repository.AvatarRepository;
import com.example.bank.Repository.userRepository;
import com.example.bank.Services.cardService;
import com.example.bank.Services.operationsService;
import com.example.bank.Services.userService;
import com.example.bank.models.Avatar;
import com.example.bank.models.card;
import com.example.bank.models.operations;
import com.example.bank.models.user;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class cardController {
    private final cardService cardService;
    private final userService userService;
    private final userRepository userRepository;
    private final AvatarRepository avatarRepository;
    private final operationsService operationService;

    @GetMapping("/insertCard")
    public String insertCard(Model model, @ModelAttribute("card") card card) {
        user user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Avatar avatar = avatarRepository.findAvatarByUserId(user.getId());
        model.addAttribute("avatarUrl", "/public/avatars/"+avatar.getName());
        model.addAttribute("avatar", avatar);
        model.addAttribute("card", card);
        model.addAttribute("user", userService.hasAuhorize());
        return "insertCard";
    }

    @PostMapping("/insertCard")
    public void insertCard(@ModelAttribute("card") card card){
        cardService.insertCard(card);
    }


    @GetMapping("/myCards")
    public String myCards(Model model, operations operation){
        user user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Avatar avatar = avatarRepository.findAvatarByUserId(user.getId());
        model.addAttribute("avatarUrl", "/public/avatars/"+avatar.getName());
        model.addAttribute("avatar", avatar);
        model.addAttribute("cards", cardService.getAllCardsByOwner());
        model.addAttribute("operation", operation);
//        model.addAttribute("cardByPrincipal", cardService.getCardByPrincipal(principal));
        return "myCards";
    }

    @PostMapping("/transaction")
    public void transaction(@RequestParam("fromCard") String cardNumFrom, @RequestParam("toCard") String cardNumTo, Integer amount, operations operation){
        operationService.transaction(cardNumFrom, cardNumTo, amount, operation);
    }


    @PostMapping("/changeActive")
    public void changeActive(@RequestParam("fromCard") String cardNum){
        cardService.changeActive(cardNum);
    }
}
