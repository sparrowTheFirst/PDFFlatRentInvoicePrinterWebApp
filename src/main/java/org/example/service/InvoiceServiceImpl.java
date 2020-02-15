package org.example.service;

import org.example.model.Contractor;
import org.example.model.Invoice;
import org.example.repository.InvoiceRepository;
import org.example.repository.SalesmanRepository;
import org.example.utilities.ApartmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SalesmanRepository salesmanRepository;

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
        for (int contractorIndex = 0; contractorIndex < contractors.size(); contractorIndex++) {
            addInvoice(Invoice.builder()
                    .signature(getSignature(contractorIndex, invoice.getCreatedAt(), contractors.get(contractorIndex).getApartmentType()))
                    .createdAt(invoice.getCreatedAt())
                    .period(invoice.getPeriod())
                    .salesman(salesmanRepository.getSalesman(0))
                    .contractor(contractors.get(contractorIndex))
                    .build());
        }
    }

    private String getSignature(int contractorIndex,LocalDate invoiceDate, ApartmentType apartmentType) {
        StringBuilder result = new StringBuilder();
        result.append(contractorIndex + 1)
                .append("/")
                .append(invoiceDate.getMonthValue() < 10 ? 0+""+invoiceDate.getMonthValue() : invoiceDate.getMonthValue())
                .append("/")
                .append(invoiceDate.getYear())
                .append("/")
                .append(apartmentType.name());
        return result.toString();
    }
}