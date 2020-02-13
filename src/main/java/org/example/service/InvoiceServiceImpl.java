package org.example.service;

import org.example.model.Contractor;
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
    public void clearInvoices() {
        invoiceRepository.clearInvoices();
    }

    @Override
    public void fillInvoicesWithContractors(Invoice invoice, List<Contractor> contractors) {
        for (Contractor contractor : contractors) {
            addInvoice(Invoice.builder()
                    .signature(invoice.getSignature())
                    .createdAt(invoice.getCreatedAt())
                    .period(invoice.getPeriod())
                    .amount(invoice.getAmount())
                    .contractor(contractor)
                    .build());
        }
    }
}