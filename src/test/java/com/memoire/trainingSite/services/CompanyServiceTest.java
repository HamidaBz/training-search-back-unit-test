package com.memoire.trainingSite.services;

import com.memoire.trainingSite.DAO.CompanyRepo;
import com.memoire.trainingSite.DTO.CompanyDTO;
import com.memoire.trainingSite.DTO.CompanyResponseDTO;
import com.memoire.trainingSite.Services.CompanyService;
import com.memoire.trainingSite.mappers.CompanyMapper;
import com.memoire.trainingSite.models.Company;
import com.memoire.trainingSite.models.CompanyProfile;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @Mock
    private CompanyRepo companyRepo;
    @Mock
    private CompanyMapper companyMapper;
    @InjectMocks
    private CompanyService companyService;

    @Test
    public void testCreate_company() {
        //given
        String username = "H&H";

        CompanyDTO companyDTO = new CompanyDTO(null,username,"password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company",new CompanyProfile(), List.of());

        Company company = new Company(null,username,"password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company",new CompanyProfile(), List.of());

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(null,username,
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company", new CompanyProfile(), List.of());

        when(companyMapper.toEntity(companyDTO)).thenReturn(company);
        when(companyRepo.save(company)).thenReturn(company);
        when(companyMapper.toResponseDTO(company)).thenReturn(companyResponseDTO);

        //when
        CompanyResponseDTO  result= companyService.createCompany(companyDTO);
        //then
        verify(companyRepo).save(company);
        assertThat(result.getUser_id()).isEqualTo(companyDTO.getUser_id());
    }


    @Test
    void getCompany_return_company_if_id_exists() {
        //given
        Long companyId = 1L;

        Company company =  new Company(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company",new CompanyProfile(), List.of());
        company.setUser_id(companyId);

        CompanyResponseDTO companyResponseDTO =  new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company", new CompanyProfile(), List.of());
        companyResponseDTO.setUser_id(companyId);

        when(companyRepo.findById(companyId)).thenReturn(Optional.of(company));
        when(companyMapper.toResponseDTO(company)).thenReturn(companyResponseDTO);
        //when
        Optional<CompanyResponseDTO> result =
                companyService.getCompanyById(companyId);
        //then
        verify(companyRepo).findById(companyId);
        assertThat(result).isPresent();
        assertThat(result.get().getUser_id()).isEqualTo(companyId);
    }

    @Test
    void return_empty_optional_if_companyId_does_not_exist() {
        //given
        Long companyId = 1L;


        when(companyRepo.findById(companyId)).thenReturn(Optional.empty());
        //when
        Optional<CompanyResponseDTO> result =
                companyService.getCompanyById(companyId);
        //then
        verify(companyRepo).findById(companyId);
        assertThat(result).isEmpty();
    }

    @Test
    void get_company_if_username_exists() {
        //given
        String existingUsername = "existingUsername";
        Company company = new Company(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company",new CompanyProfile(), List.of());
        company.setUsername(existingUsername);

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company", new CompanyProfile(), List.of());
        companyResponseDTO.setUsername(existingUsername);

        when(companyMapper.toResponseDTO(company)).thenReturn(companyResponseDTO);
        when(companyRepo.findByUsername(existingUsername)).thenReturn(Optional.of(company));

        //when
        Optional<CompanyResponseDTO> result =
                companyService.getCompanyByUsername(existingUsername);

        //then
        verify(companyRepo).findByUsername(existingUsername);
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo(existingUsername);
    }

    @Test
    void return_empty_optional_if_company_username_does_not_exist(){
        //given
        String existingUsername = "existingUsername";
        when(companyRepo.findByUsername(existingUsername)).thenReturn(Optional.empty());

        //when
        Optional<CompanyResponseDTO> result = companyService.getCompanyByUsername(existingUsername);

        //then
        verify(companyRepo).findByUsername(existingUsername);
        assertThat(result).isEmpty();
    }

    @Test
    void test_getCompanies() {
        //given
        Company company_1 = new Company(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company",new CompanyProfile(), List.of());

        Company company_2 = new Company(null,"DD","password",
                LocalDateTime.of(2024,2,10, 1,15),
                UserStatus.ACTIVE, "0982883762","dd.dd@gmail.com",List.of(),
                "DD Company",new CompanyProfile(), List.of());

        CompanyResponseDTO companyResponseDTO_1 = new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company", new CompanyProfile(), List.of());

        CompanyResponseDTO companyResponseDTO_2 = new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2024,2,10, 1,15),
                UserStatus.ACTIVE, "0982883762","dd.dd@gmail.com",List.of(),
                "DD Company", new CompanyProfile(), List.of());

        when(companyRepo.findAll()).thenReturn(List.of(company_1,company_2));
        when(companyMapper.toResponseDTO(company_1)).thenReturn(companyResponseDTO_1);
        when(companyMapper.toResponseDTO(company_2)).thenReturn(companyResponseDTO_2);
        //when
        List<CompanyResponseDTO> result = companyService.getCompanies();

        //then
        verify(companyRepo).findAll();
        assertThat(result).isEqualTo(List.of(companyResponseDTO_1, companyResponseDTO_2));
    }

    @Test
    void updateCompany_if_exists() {
        //given
        Long companyId = 1L;

        Company existedCompany =  new Company(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company",new CompanyProfile(), List.of());
        existedCompany.setUser_id(companyId);

        CompanyDTO updatedCompanyDTO = new CompanyDTO(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company",new CompanyProfile(), List.of());

        updatedCompanyDTO.setUser_id(companyId);

        Company updatedCompany =   new Company(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company",new CompanyProfile(), List.of());

        CompanyResponseDTO updatedCompanyResponseDTO = new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company", new CompanyProfile(), List.of());
        updatedCompanyResponseDTO.setUser_id(companyId);


        when(companyRepo.findById(companyId)).thenReturn(Optional.of(existedCompany));
        when(companyMapper.toEntity(updatedCompanyDTO)).thenReturn(updatedCompany);
        when(companyMapper.toResponseDTO(updatedCompany)).thenReturn(updatedCompanyResponseDTO);
        //when
        Optional<CompanyResponseDTO> result = companyService.updateCompany(companyId,updatedCompanyDTO);
        //then
        verify(companyRepo).findById(companyId);
        ArgumentCaptor<Company> captorCompany = ArgumentCaptor.forClass(Company.class);
        verify(companyRepo).save(captorCompany.capture());
        assertThat(captorCompany.getValue()).isEqualTo(updatedCompany);
        assertThat(result).isPresent();
    }

    @Test
    void updateCompany_returns_emptyOptional_if_company_does_not_exists(){
        //given
        Long companyId = 1L;

        CompanyDTO updatedCompanyDTO = new CompanyDTO(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company",new CompanyProfile(), List.of());
        updatedCompanyDTO.setUser_id(companyId);

        when(companyRepo.findById(companyId)).thenReturn(Optional.empty());
        //when
        Optional<CompanyResponseDTO> result = companyService.updateCompany(companyId,updatedCompanyDTO);
        //then
        verify(companyRepo).findById(companyId);
        assertThat(result).isEmpty();
    }

    @Test
    void deleteCompany_must_call_deleteById() {
        //given
        Long companyId = 1L;
        //when
        companyService.deleteCompany(companyId);
        //then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(companyRepo).deleteById(argumentCaptor.capture());
        Long capturedId = argumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(companyId);
    }

}