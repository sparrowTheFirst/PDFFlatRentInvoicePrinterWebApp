package org.example.service;

import org.example.model.Salesman;
import org.example.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class SalesmanServiceImpl implements SalesmanService {

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Override
    public List<Salesman> allSalesmen() {
        return salesmanRepository.allSalesmen();
    }

    @Override
    public void addSalesman(Salesman salesman) {
        salesmanRepository.addSalesman(salesman);
    }

    @Override
    public void clearSalesmen() {
        salesmanRepository.clearSalesmen();
    }

    @Override
    public void getSalesmen(InputStream inputStream) {
        salesmanRepository.getSalesmen(inputStream);
    }
}