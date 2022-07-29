package com.joceano.springbootcacheredis.controllers;

import com.joceano.springbootcacheredis.entities.Company;
import com.joceano.springbootcacheredis.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company company) {
        return companyService.create(company);
    }

    @PutMapping
    public Company update(@RequestBody Company company) {
        return companyService.update(company);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        companyService.deleteById(id);
    }
}
