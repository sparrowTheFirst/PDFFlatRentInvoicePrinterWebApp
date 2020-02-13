package org.example.repository;

import org.example.model.Salesman;
import org.example.utilities.SalesmanReader;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SalesmanRepositoryImpl implements SalesmanRepository {

    private List<Salesman> salesmen = new ArrayList<>();

    @Override
    public List<Salesman> allSalesmen() {
        return salesmen;
    }

    @Override
    public void addSalesman(Salesman salesman) {
        salesmen.add(salesman);
    }

    @Override
    public void clearSalesmen() {
        salesmen.clear();
    }

    @Override
    public void getSalesmen(InputStream inputStream) {
        salesmen = SalesmanReader.getSalesmenFromInputStream(inputStream);
    }

    @Override
    public Salesman getSalesman(int salesmanIndex) {
        return salesmen.get(salesmanIndex);
    }
}