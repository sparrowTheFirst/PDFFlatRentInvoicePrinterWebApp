package org.example.repository;

import org.example.model.Invoice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private List<Invoice> invoices = new ArrayList<>();

    @Override
    public List<Invoice> allInvoices() {
        return invoices;
    }

    @Override
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    @Override
    public void addInvoices(List<Invoice> invoices) {
            this.invoices = invoices;
    }

    @Override
    public void clearInvoices(List<Invoice> invoices) {
        invoices.clear();
    }
}