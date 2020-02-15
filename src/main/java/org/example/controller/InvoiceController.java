package org.example.controller;

import org.example.model.Contractor;
import org.example.model.Invoice;
import org.example.service.ContractorService;
import org.example.service.InvoiceService;
import org.example.utilities.PDFCreator;
import org.example.utilities.SystemDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

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
        model.addAttribute(
                "invoice",
                Invoice.builder()
                        .createdAt(SystemDateTime.getDateNow())
                        .soldAt(SystemDateTime.getDateNow())
                        .build()
        );
        model.addAttribute("contractors", contractorService.allContractors());
        return "invoiceCreate";
    }

    @PostMapping("/add")
    public String postCreateInvoice(Model model, @ModelAttribute Invoice invoice, @RequestParam String contractorId) {
        invoiceService.fillInvoiceWithContractor(invoice, contractorService.getContractorById(Integer.parseInt(contractorId)));
        model.addAttribute("invoices", invoiceService.allInvoices());
        return "invoiceHome";
    }

    @GetMapping("/addBatch")
    public String getCreateInvoicesBatchPage(Model model) {
        model.addAttribute(
                "invoice",
                Invoice.builder()
                        .createdAt(SystemDateTime.getDateNow())
                        .soldAt(SystemDateTime.getDateNow())
                        .build()
        );
        return "invoiceBatchCreate";
    }

    @PostMapping("/addBatch")
    public String postCreateInvoicesBatch(Model model, @Valid @ModelAttribute Invoice invoice) {
        invoiceService.fillInvoicesWithContractors(invoice, contractorService.allContractors());
        model.addAttribute("invoices", invoiceService.allInvoices());
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