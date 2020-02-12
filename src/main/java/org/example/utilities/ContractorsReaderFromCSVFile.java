package org.example.utilities;

import com.opencsv.CSVReader;
import org.example.model.ContractorDTO;

import java.io.FileReader;
import java.io.IOException;
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
}