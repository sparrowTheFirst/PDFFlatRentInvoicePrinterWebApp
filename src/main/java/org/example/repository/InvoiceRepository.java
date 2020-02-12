package org.example.repository;

import org.example.model.Invoice;

import java.util.List;

public interface InvoiceRepository {

    List<Invoice> allInvoices();
    void addInvoice(Invoice invoice);
    void addInvoices(List<Invoice> invoices);
    void clearInvoices(List<Invoice> invoices);
}