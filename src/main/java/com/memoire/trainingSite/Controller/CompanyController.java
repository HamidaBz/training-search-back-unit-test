package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.DTO.CompanyDTO;
import com.memoire.trainingSite.DTO.CompanyResponseDTO;
import com.memoire.trainingSite.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(@RequestBody CompanyDTO companyDTO){
        CompanyResponseDTO company =  companyService.createCompany(companyDTO);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> getCompanies() {
        List<CompanyResponseDTO> companies = companyService.getCompanies();
        if(companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable Long companyId) {
        Optional<CompanyResponseDTO> company = companyService.getCompanyById(companyId);
        if(company.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<CompanyResponseDTO> getCompanyByUserName(@RequestParam String username){
        Optional<CompanyResponseDTO>  company = companyService.getCompanyByUsername(username);
        if(company.isPresent()){
            return new ResponseEntity<>(company.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@PathVariable Long companyId, @RequestBody CompanyDTO companyDTO) {
        Optional<CompanyResponseDTO> company = companyService.updateCompany(companyId,companyDTO);
        if(company.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        }
    }
    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
