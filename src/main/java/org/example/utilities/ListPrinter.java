package org.example.utilities;

import org.example.model.ContractorDTO;

import java.util.List;

public class ListPrinter {

    public static String getStringRepresentation(List<ContractorDTO> list) {
        StringBuffer result = new StringBuffer();
        for (ContractorDTO object : list) {
            result.append(object.toString()).append("\n");
        }
        return result.toString();
    }
}
