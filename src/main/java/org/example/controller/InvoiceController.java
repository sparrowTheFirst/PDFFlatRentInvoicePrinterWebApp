package org.example.controller;

import org.example.model.Invoice;
import org.example.service.ContractorService;
import org.example.service.InvoiceService;
import org.example.utilities.PDFCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ContractorService contractorService;

    @GetMapping("/")
    public String getInvoicePage(Model model) {
        model.addAttribute("invoices", invoiceService.allInvoices());
        return "invoiceHome";
    }

    @GetMapping("/add")
    public String getCreateInvoicePage(Model model) {
        model.addAttribute("invoice", Invoice.builder().createdAt(LocalDate.now()).build());
        return "invoiceCreate";
    }

    @PostMapping("/add")
    public String postCreateInvoicePage(Model model, @ModelAttribute Invoice invoice) {
        invoiceService.fillInvoicesWithContractors(invoice, contractorService.allContractors());
        model.addAttribute("invoices", invoiceService.allInvoices());
        model.addAttribute("invoice", invoice);
        return "invoiceHome";
    }

    @GetMapping("/reset")
    public String resetInvoices(Model model) {
        invoiceService.clearInvoices();
        model.addAttribute("invoices", invoiceService.allInvoices());
        return "invoiceHome";
    }

    @GetMapping("/print")
    public String printInvoices(Model model) {
        PDFCreator.print(invoiceService.allInvoices());
        model.addAttribute("invoices", invoiceService.allInvoices());
        return "invoiceHome";
    }
}