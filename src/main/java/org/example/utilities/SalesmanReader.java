package org.example.utilities;

import org.example.model.Salesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SalesmanReader {

    private static final int COMPANY_NAME = 0;
    private static final int COMPANY_ADDRESS = 1;
    private static final int COMPANY_POSTCODE = 2;
    private static final int COMPANY_CITY = 3;

    public static List<Salesman> getSalesmenFromInputStream(InputStream inputStream) {
        List<Salesman> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("[;,]");
                result.add(Salesman.builder()
                        .companyName(split[COMPANY_NAME])
                        .address(split[COMPANY_ADDRESS])
                        .postcode(split[COMPANY_POSTCODE])
                        .city(split[COMPANY_CITY])
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}