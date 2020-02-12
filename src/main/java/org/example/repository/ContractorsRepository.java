package org.example.repository;

import org.example.model.ContractorDTO;

import java.io.InputStream;
import java.util.List;

public interface ContractorsRepository {

    List<ContractorDTO> allContractors();
    void getContractors(InputStream inputStream);
    void clearContractorsRepository();
}