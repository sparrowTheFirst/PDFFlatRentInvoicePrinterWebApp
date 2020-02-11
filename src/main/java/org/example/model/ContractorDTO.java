package org.example.model;

import lombok.Builder;

@Builder
public class ContractorDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String city;
}
