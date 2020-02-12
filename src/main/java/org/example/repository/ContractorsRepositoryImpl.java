package org.example.repository;

import org.example.model.ContractorDTO;
import org.example.utilities.ContractorsReaderFromInputStream;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContractorsRepositoryImpl implements ContractorsRepository {

    private List<ContractorDTO> contractors = new ArrayList<>();

    @Override
    public List<ContractorDTO> allContractors() {
        return contractors;
    }

    @Override
    public void getContractors(InputStream inputStream) {
        contractors = ContractorsReaderFromInputStream.getContractors(inputStream);
    }

    @Override
    public ContractorDTO addContractor(ContractorDTO contractor) {
        contractors.add(contractor);
        return contractor;
    }

    @Override
    public void clearContractorsRepository() {
        contractors.clear();
    }
}