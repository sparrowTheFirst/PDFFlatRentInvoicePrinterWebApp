package org.example.utilities;

import org.example.model.Contractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContractorReader {

    private static final int CONTRACTOR_FIRST_NAME = 0;
    private static final int CONTRACTOR_LAST_NAME = 1;
    private static final int CONTRACTOR_ADDRESS = 2;
    private static final int CONTRACTOR_POSTCODE = 3;
    private static final int CONTRACTOR_CITY = 4;
    private static final int CONTRACTOR_APARTMENT_TYPE = 5;
    private static final int CONTRACTOR_APARTMENT_PRICE = 6;
    private static final int CONTRACTOR_APARTMENT_NUMBER = 7;

    public static List<Contractor> getContractorsFromInputStream(InputStream inputStream) {
        List<Contractor> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("[;]");
                result.add(Contractor.builder()
                        .firstName(split[CONTRACTOR_FIRST_NAME])
                        .lastName(split[CONTRACTOR_LAST_NAME])
                        .address(split[CONTRACTOR_ADDRESS])
                        .postcode(split[CONTRACTOR_POSTCODE])
                        .city(split[CONTRACTOR_CITY])
                        .apartmentType(ApartmentType.valueOf(split[CONTRACTOR_APARTMENT_TYPE]))
                        .amount(split[CONTRACTOR_APARTMENT_PRICE])
                        .apartmentNumber(split[CONTRACTOR_APARTMENT_NUMBER])
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}