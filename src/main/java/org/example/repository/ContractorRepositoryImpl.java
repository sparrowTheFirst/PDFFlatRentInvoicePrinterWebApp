package org.example.repository;

import org.example.model.Contractor;
import org.example.utilities.ContractorReader;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContractorRepositoryImpl implements ContractorRepository {

    private List<Contractor> contractors = new ArrayList<>();

    @Override
    public List<Contractor> allContractors() {
        return contractors;
    }

    @Override
    public void getContractors(InputStream inputStream) {
        contractors = ContractorReader.getContractorsFromInputStream(inputStream);
    }

    @Override
    public void clearContractorsRepository() {
        contractors.clear();
    }
}