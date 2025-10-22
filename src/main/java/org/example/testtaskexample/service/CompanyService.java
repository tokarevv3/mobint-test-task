package org.example.testtaskexample.service;

import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.entity.Company;
import org.example.testtaskexample.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company findById(Long id) {
        return companyRepository.findCompanyById(id);
    }
}
