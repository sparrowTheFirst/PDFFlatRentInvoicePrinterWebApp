package org.example.controller;

import org.example.model.Invoice;
import org.example.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/add")
    public String getCreateInvoicePage(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoiceCreate";
    }

    @PostMapping("/add")
    public String postCreateInvoicePage(Model model, @RequestParam Invoice invoice) {
        invoiceService.addInvoice(invoice);
        model.addAttribute("invoice", invoice);
        return "invoiceHome";
    }
}