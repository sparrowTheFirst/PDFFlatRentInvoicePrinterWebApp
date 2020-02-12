package org.example.repository;

import org.example.model.Contractor;

import java.io.InputStream;
import java.util.List;

public interface ContractorRepository {

    List<Contractor> allContractors();
    void getContractors(InputStream inputStream);
    void clearContractorsRepository();
}