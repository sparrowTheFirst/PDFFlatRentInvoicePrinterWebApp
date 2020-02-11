package org.example.utilities;

import lombok.Getter;
import org.example.model.ContractorDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextFileContractorsReader {

    public static List<ContractorDTO> getContractorsFromTextFile(String filePath) {
        List<ContractorDTO> result = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + filePath));
            for (String line : lines) {
                if (!line.isEmpty()) {
                    String[] split = line.split(";");
                    result.add(ContractorDTO.builder()
                            .firstName(split[0])
                            .lastName(split[1])
                            .address(split[2])
                            .postcode(split[3])
                            .city(split[4])
                            .build());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}