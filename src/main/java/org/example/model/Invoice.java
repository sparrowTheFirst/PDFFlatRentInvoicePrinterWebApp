package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Invoice {

    private String signature;
    private LocalDate createdAt;
    private LocalDate period;
    private BigDecimal amount;

    @Override
    public String toString() {
        return "Faktura o sygnaturze: " + signature + ", wystawiona dnia: " + createdAt + "\n" +
                "Tytułem: Wynajem mieszkania za miesiąc: " + period.getMonth().name() + ", w kwocie: " + amount;
    }
}