package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.DTO.CompanyDTO;
import com.memoire.trainingSite.Services.CompanyService;
import com.memoire.trainingSite.models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        List<CompanyDTO> companies = companyService.getCompanies();
        if(companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        CompanyDTO company = companyService.getCompanyById(id);
        if(company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
    }
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return companyService.updateCompany(id, company);
    }
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
    @PostMapping("/new")
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }








}
