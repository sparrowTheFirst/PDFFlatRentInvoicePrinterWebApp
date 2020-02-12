package org.example.model;

import lombok.Builder;

@Builder
public class Contractor {

    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String city;

    @Override
    public String toString() {
        return firstName + " " + lastName + " | " + address + " | " + postcode + " " + city;
    }
}