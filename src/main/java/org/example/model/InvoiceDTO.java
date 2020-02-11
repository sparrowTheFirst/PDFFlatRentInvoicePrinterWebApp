package org.example.model;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public class InvoiceDTO {

    private String signature;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate periodStart;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate periodEnd;
    private BigDecimal amount;
}
