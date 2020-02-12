package org.example.controller;

import org.example.model.Invoice;
import org.example.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public String getInvoicePage() {
        return "invoiceHome";
    }

    @GetMapping("/add")
    public String getCreateInvoicePage(Model model) {
        model.addAttribute("invoice", Invoice.builder().createdAt(LocalDate.now()).build());
        return "invoiceCreate";
    }

    @PostMapping("/add")
    public String postCreateInvoicePage(Model model, @Valid @ModelAttribute Invoice invoice) {
        invoiceService.addInvoice(invoice);
        model.addAttribute("invoice", invoice);
        return "invoiceHome";
    }
}