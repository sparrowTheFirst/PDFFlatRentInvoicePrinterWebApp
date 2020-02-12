package org.example.service;

import org.example.model.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> allInvoices();
    void addInvoice(Invoice invoice);
    void addInvoices(List<Invoice> invoices);
    void clearInvoices(List<Invoice> invoices);
}