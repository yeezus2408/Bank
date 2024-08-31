package com.example.dnsDB.Services;

import org.springframework.stereotype.Service;

@Service
public interface CardService {
    String generatorBankNumber();
    Integer generatorBankCVV();
}
