package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice {

    private String signature;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private String period;
    private Contractor contractor;

    @Override
    public String toString() {
        return "Faktura o sygnaturze: " + (signature != null ? signature : "-") + ", wystawiona dnia: " + createdAt + "\n"
                + "Sprzedawca: " + "\n"
                + "Najemca: " + (contractor != null ? contractor.toString() : "-") + "\n"
                + "Tytułem: Wynajem mieszkania za miesiąc: " + period + ", w kwocie: ";
    }
}