package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.CompanyRepo;
import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.CompanyDTO;
import com.memoire.trainingSite.mappers.CompanyDTOMapper;
import com.memoire.trainingSite.models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;


public class CompanyService {
    private CompanyRepo companyRepo ;
    private CompanyDTOMapper companyDTOMapper;
    @Autowired
    public CompanyService(CompanyRepo companyRepo , CompanyDTOMapper companyDTOMapper){
        this.companyRepo = companyRepo;
        this.companyDTOMapper = companyDTOMapper;
    }
    public Company createCompany(Company company){
        try {
            return companyRepo.save(company);
        }catch(Exception e) {
            e.printStackTrace();
            System.err.println();
            return null ;
        }
    }


    public CompanyDTO getCompany(Long id) {
        return companyRepo.findById(id).map(companyDTOMapper::toDTO).orElseThrow(() -> new IllegalArgumentException( id + " does not exist"));
    }
    public List<CompanyDTO> getCompanies() {
        return companyRepo.findAll()
                .stream()
                .map(company -> companyDTOMapper.toDTO(company))
                .collect(Collectors.toList());
    }
    //create a function that returns a company object and takes in a long id and a company object as parameters
    public Company updateCompany(Long id, Company company) {
        if (companyRepo.existsById(id)) {
            return companyRepo.save(company);
        } else {
            throw new IllegalArgumentException( id + " does not exist");
        }
    }
    public void deleteCompany(Long id) {
        if (companyRepo.existsById(id)) {
            companyRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException(id + " does not exist");
        }
    }









}
