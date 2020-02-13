package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.utilities.ApartmentType;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Getter
public class Contractor {

    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String city;
    private ApartmentType apartmentType;
    private BigDecimal amount;
}