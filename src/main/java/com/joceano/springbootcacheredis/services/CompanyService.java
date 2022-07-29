package com.joceano.springbootcacheredis.services;

import com.joceano.springbootcacheredis.entities.Company;
import com.joceano.springbootcacheredis.exceptions.CompanyException;
import com.joceano.springbootcacheredis.repositories.CompanyRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Cacheable(value = "Company", key = "#root.method.name", unless = "#result==null or #result.isEmpty()")
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Cacheable(value = "Company", key = "#id", unless = "#result==null")
    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new CompanyException("Id not found: " + id));
    }

    @CacheEvict(value = "Company", allEntries = true)
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @CachePut(value = "Company", key="#company.getId()")
    public Company update(Company company) {
        if (notExistsCompany(company.getId()))
            throw new CompanyException("Company is not exists");

        return companyRepository.save(company);
    }

    @CacheEvict(value = "Company", key="#id")
    public void deleteById(Long id) {
        if (notExistsCompany(id))
            throw new CompanyException("Company is not exists");

        companyRepository.deleteById(id);
    }

    private boolean notExistsCompany(Long id) {
        return !companyRepository.existsById(id);
    }
}
