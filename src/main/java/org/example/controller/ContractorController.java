package org.example.controller;

import org.example.model.ContractorDTO;
import org.example.service.ContractorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContractorController {

    @Autowired
    private ContractorsService contractorsService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contractors", contractorsService.allContractors());
        return "home";
    }

    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
        List<ContractorDTO> contractors = new ArrayList<>();
        if (!file.isEmpty()) {
            try {
                contractorsService.getContractors(file.getInputStream());
            } catch (Exception e) {
            }
        }
        model.addAttribute("contractors", contractorsService.allContractors());
        return "home";
    }
}