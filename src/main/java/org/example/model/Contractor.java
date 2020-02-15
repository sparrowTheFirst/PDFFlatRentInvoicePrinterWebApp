package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.utilities.ApartmentType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contractor {

    private Long index;
    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String city;
    private ApartmentType apartmentType;
    private String amount;
    private String apartmentNumber;
}