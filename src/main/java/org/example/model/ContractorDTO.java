package org.example.model;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class ContractorDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String city;
}
