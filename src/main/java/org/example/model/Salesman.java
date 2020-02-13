package org.example.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Salesman {

    private String companyName;
    private String address;
    private String postcode;
    private String city;

    @Override
    public String toString() {
        return companyName + "\n"
                + postcode + " " + city + "\n"
                + address;
    }
}
