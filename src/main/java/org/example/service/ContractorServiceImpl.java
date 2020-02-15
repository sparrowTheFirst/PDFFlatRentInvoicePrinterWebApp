package org.example.service;

import org.example.model.Contractor;
import org.example.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ContractorServiceImpl implements ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    @Override
    public List<Contractor> allContractors() {
        return contractorRepository.allContractors();
    }

    @Override
    public void getContractors(InputStream inputStream) {
        contractorRepository.getContractors(inputStream);
    }

    @Override
    public Contractor getContractorById(int index) {
        return contractorRepository.getContractorById(index);
    }

    @Override
    public void clearContractors() {
        contractorRepository.clearContractorsRepository();
    }

    @Override
    public void setContractorsIndex() {
        for (int i = 0; i < contractorRepository.allContractors().size(); i++) {
            if (allContractors().get(i).getIndex() == null) {
                contractorRepository.allContractors().get(i).setIndex(Long.valueOf(i));
            }
        }
    }
}