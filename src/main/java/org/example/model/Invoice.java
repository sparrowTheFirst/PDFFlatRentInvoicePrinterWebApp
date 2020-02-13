package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.utilities.SalesmanPID;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Invoice {

    private String signature;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private String period;
    private BigDecimal amount;
    private Contractor contractor;

    @Override
    public String toString() {
        return "Faktura o sygnaturze: " + signature + ", wystawiona dnia: " + createdAt + "\n"
                + "Sprzedawca: " + SalesmanPID.getData() + "\n"
                + "Najemca: " + (contractor != null ? contractor.toString() : "-") + "\n"
                + "Tytułem: Wynajem mieszkania za miesiąc: " + period + ", w kwocie: " + amount;
    }
}