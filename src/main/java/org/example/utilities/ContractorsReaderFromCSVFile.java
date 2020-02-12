package org.example.utilities;

import com.opencsv.CSVReader;
import org.example.model.ContractorDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContractorsReaderFromCSVFile {

    public static List<ContractorDTO> getContractors(String filePath) {
        List<ContractorDTO> result = new ArrayList<>();
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(System.getProperty("user.dir") + filePath));
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                result.add(ContractorDTO.builder()
                        .firstName(line[0])
                        .lastName(line[1])
                        .address(line[2])
                        .postcode(line[3])
                        .city(line[4])
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<ContractorDTO> getContractors(File file) {
        List<ContractorDTO> result = new ArrayList<>();
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(file));
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                result.add(ContractorDTO.builder()
                        .firstName(line[0])
                        .lastName(line[1])
                        .address(line[2])
                        .postcode(line[3])
                        .city(line[4])
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<ContractorDTO> getContractors(InputStream inputStream) {
        List<ContractorDTO> result = new ArrayList<>();
        CSVReader csvReader;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("[;,]");
                result.add(ContractorDTO.builder()
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