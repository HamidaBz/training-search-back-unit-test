package com.memoire.trainingSite.Services;

import com.memoire.trainingSite.DAO.CompanyRepo;
import com.memoire.trainingSite.DTO.CompanyDTO;
import com.memoire.trainingSite.DTO.CompanyResponseDTO;
import com.memoire.trainingSite.mappers.CompanyMapper;
import com.memoire.trainingSite.models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CompanyService {
    private CompanyRepo companyRepo ;
    private CompanyMapper companyMapper;
    @Autowired
    public CompanyService(CompanyRepo companyRepo, CompanyMapper companyMapper){
        this.companyRepo = companyRepo;
        this.companyMapper = companyMapper;
    }
    public CompanyResponseDTO createCompany(CompanyDTO companyDTO){
        Company company = companyMapper.toEntity(companyDTO);
        return (companyMapper.toResponseDTO(companyRepo.save(company)));
    }


    public Optional<CompanyResponseDTO> getCompanyById(Long id) {
        return companyRepo.findById(id).map(companyMapper::toResponseDTO);
    }
    public Optional<CompanyResponseDTO> getCompanyByUsername(String username){
        return companyRepo.findByUsername(username).map(companyMapper::toResponseDTO);
    }
    public List<CompanyResponseDTO> getCompanies() {
        return companyRepo.findAll()
                .stream()
                .map(companyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    //create a function that returns a company object and takes in a long id and a company object as parameters
    public Optional<CompanyResponseDTO> updateCompany(Long id, CompanyDTO companyDTO) {
        Optional<Company> company = companyRepo.findById(id);

        if (company.isPresent()) {
            Company updatedCompany = companyMapper.toEntity(companyDTO);
            companyRepo.save(updatedCompany);
            return Optional.of(companyMapper.toResponseDTO(updatedCompany));
        }
        return Optional.empty();
    }
    public void deleteCompany(Long id) {
        companyRepo.deleteById(id);
    }
}
