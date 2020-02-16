package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice {

    private String signature;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate soldAt;
    private String period;
    private Salesman salesman;
    private Contractor contractor;

    public LocalDate getSoldAt() {
        return createdAt.plus(Period.ofDays(14));
    }
}