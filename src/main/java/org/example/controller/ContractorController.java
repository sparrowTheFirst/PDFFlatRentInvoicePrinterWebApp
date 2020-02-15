package org.example.controller;

import org.example.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/contractor")
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("contractors", contractorService.allContractors());
        return "contractorUpload";
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                contractorService.getContractors(file.getInputStream());
                contractorService.setContractorsIndex();
            } catch (Exception e) {
            }
        }
        model.addAttribute("contractors", contractorService.allContractors());
        return "contractorUpload";
    }

    @GetMapping("/reset")
    public String resetContractors(Model model) {
        contractorService.clearContractors();
        model.addAttribute("contractors", contractorService.allContractors());
        return "contractorUpload";
    }
}