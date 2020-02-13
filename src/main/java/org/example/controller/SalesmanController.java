package org.example.controller;

import org.example.service.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/salesman")
public class SalesmanController {

    @Autowired
    private SalesmanService salesmanService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("salesmen", salesmanService.allSalesmen());
        return "salesmanUpload";
    }

    @GetMapping("/reset")
    public String resetContractors(Model model) {
        salesmanService.clearSalesmen();
        model.addAttribute("salesmen", salesmanService.allSalesmen());
        return "salesmanUpload";
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                salesmanService.getSalesmen(file.getInputStream());
            } catch (Exception e) {
            }
        }
        model.addAttribute("salesmen", salesmanService.allSalesmen());
        return "salesmanUpload";
    }
}
