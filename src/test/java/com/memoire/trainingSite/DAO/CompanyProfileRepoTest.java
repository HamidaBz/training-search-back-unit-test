package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CompanyProfileRepoTest {

    @Autowired
    CompanyProfileRepo companyProfileRepo;
    @Autowired
    CompanyRepo companyRepo;
    @BeforeEach
    void setUp() {
        companyRepo.deleteAll();
        companyProfileRepo.deleteAll();
    }
   @Test
    void test_findByCompanyUsername_whenUsernameExists() {
        //given
        CompanyProfile companyProfile = new CompanyProfile();
        Company company = new Company(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",Role.COMPANY,
                "H&H Company", companyProfile, List.of());

        companyProfile.setCompany(company);
        companyRepo.save(company);
        // when
        Optional<CompanyProfile> foundProfile = companyProfileRepo.findByCompanyUsername("H&H");
        // then
        assertThat(foundProfile).isPresent();
        assertThat(foundProfile.get().getCompany().getUsername()).isEqualTo("H&H");
    }

    @Test
    void test_findByCompanyUsername_WhenUsernameDoesNotExist() {
        // when
        Optional<CompanyProfile> foundProfile = companyProfileRepo.findByCompanyUsername("non_existent_company");

        // then
        assertThat(foundProfile).isEmpty();
    }
}