package org.example.utilities;

import org.example.model.Contractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContractorReader {

    public static List<Contractor> getContractorsFromInputStream(InputStream inputStream) {
        List<Contractor> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("[;,]");
                result.add(Contractor.builder()
                        .firstName(split[0])
                        .lastName(split[1])
                        .address(split[2])
                        .postcode(split[3])
                        .city(split[4])
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}