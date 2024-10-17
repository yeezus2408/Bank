package com.example.bank.Services.lib;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GeneratorCardNum {

    public String cardNum(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }
        sb.toString();
        String s = sb.toString().substring(0, 4) + " " +
                sb.substring(4, 8) + " " +
                sb.substring(8, 12) + " " +
                sb.substring(12);
        return s;
    }

    public Integer cardCVV(){
        Random random = new Random();

        // Генерируем случайное трехзначное число
        return random.nextInt(900) + 100;
    }

    public Integer agreement(){
        Random random = new Random();
        return random.nextInt(900) + 100;
    }
}
