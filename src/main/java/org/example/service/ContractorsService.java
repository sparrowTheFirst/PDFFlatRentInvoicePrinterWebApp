package org.example.service;

import org.example.model.ContractorDTO;

import java.io.InputStream;
import java.util.List;

public interface ContractorsService {

    List<ContractorDTO> allContractors();
    void getContractors(InputStream inputStream);
    void clearContractors();
}