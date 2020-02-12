package org.example.controller;

import org.example.model.ContractorDTO;
import org.example.utilities.ContractorsReaderFromCSVFile;
import org.example.utilities.ListPrinter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContractorController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    @ResponseBody
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
        List<ContractorDTO> contractors = new ArrayList<>();
        if (!file.isEmpty()) {
            try {
                contractors = ContractorsReaderFromCSVFile.getContractors(file.getInputStream());
            } catch (Exception e) {
            }
        }
        return ListPrinter.getStringRepresentation(contractors).toUpperCase();
    }
}