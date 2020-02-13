package org.example.service;

import org.example.model.Contractor;
import org.example.model.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> allInvoices();
    void addInvoice(Invoice invoice);
    void clearInvoices();
    void fillInvoicesWithContractors(Invoice invoice, List<Contractor> contractors);
}