package org.example.service;

import org.example.model.Invoice;
import org.example.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> allInvoices() {
        return invoiceRepository.allInvoices();
    }

    @Override
    public void addInvoice(Invoice invoice) {
        invoiceRepository.addInvoice(invoice);
    }

    @Override
    public void addInvoices(List<Invoice> invoices) {
        invoiceRepository.addInvoices(invoices);
    }

    @Override
    public void clearInvoices(List<Invoice> invoices) {
        invoiceRepository.clearInvoices(invoices);
    }
}