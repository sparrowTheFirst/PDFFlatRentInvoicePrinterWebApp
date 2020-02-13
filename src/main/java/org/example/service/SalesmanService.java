package org.example.service;

import org.example.model.Salesman;

import java.io.InputStream;
import java.util.List;

public interface SalesmanService {

    List<Salesman> allSalesmen();
    void addSalesman(Salesman salesman);
    void clearSalesmen();
    void getSalesmen(InputStream inputStream);
}