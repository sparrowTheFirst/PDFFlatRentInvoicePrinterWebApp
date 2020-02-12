package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Invoice {

    private String signature;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private String period;
    private BigDecimal amount;

    @Override
    public String toString() {
        return "Faktura o sygnaturze: " + signature + ", wystawiona dnia: " + createdAt + "\n" +
                "Tytułem: Wynajem mieszkania za miesiąc: " + period + ", w kwocie: " + amount;
    }
}