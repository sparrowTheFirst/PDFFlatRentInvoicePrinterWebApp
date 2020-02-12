package org.example.service;

import org.example.model.Contractor;

import java.io.InputStream;
import java.util.List;

public interface ContractorService {

    List<Contractor> allContractors();
    void getContractors(InputStream inputStream);
    void clearContractors();
}