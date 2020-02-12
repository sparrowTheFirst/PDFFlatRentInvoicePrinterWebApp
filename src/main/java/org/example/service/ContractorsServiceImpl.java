package org.example.service;

import org.example.model.ContractorDTO;
import org.example.repository.ContractorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ContractorsServiceImpl implements ContractorsService {

    @Autowired
    private ContractorsRepository contractorsRepository;

    @Override
    public List<ContractorDTO> allContractors() {
        return contractorsRepository.allContractors();
    }

    @Override
    public void getContractors(InputStream inputStream) {
        contractorsRepository.getContractors(inputStream);
    }

    @Override
    public void clearContractors() {
        contractorsRepository.clearContractorsRepository();
    }
}
