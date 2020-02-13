package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Contractor {

    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String city;

    @Override
    public String toString() {
        return lastName + " " + firstName + "\n" + postcode + " " + city + "\n" + address;
    }
}