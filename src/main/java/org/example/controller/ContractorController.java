package org.example.controller;

import org.example.utilities.ContractorsReaderFromCSVFile;
import org.example.utilities.ListPrinter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractorController {

    @GetMapping("/list")
    public String getContractorsList() {
        return ListPrinter.getStringRepresentation(ContractorsReaderFromCSVFile.getContractors("\\src\\main\\resources\\data\\contractors.csv"));
    }
}