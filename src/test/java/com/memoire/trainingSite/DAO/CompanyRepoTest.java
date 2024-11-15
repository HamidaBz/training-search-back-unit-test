package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Company;
import com.memoire.trainingSite.models.CompanyProfile;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CompanyRepoTest {
    @Autowired
    CompanyRepo companyRepo;

    @AfterEach
    void tearDown(){
        companyRepo.deleteAll();
    }

    @Test
    void test_existsByUsername_it_returns_true_when_company_exists() {
        //given
        String username = "H&H";
        Company company = new Company(null,username,"password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com", Role.COMPANY,
                "H&H Company",new CompanyProfile(), List.of());
        companyRepo.save(company);

        //when
        Boolean exists = companyRepo.existsByUsername(username);

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void test_existsByUsername_it_returns_false_when_company_does_not_exist() {
        String username = "H&H";
        //when
        Boolean exists = companyRepo.existsByUsername(username);

        //then
        assertThat(exists).isFalse();
    }

    @Test
    void test_findByUsername_it_returns_company_when_it_exists() {
        //given
        String username = "H&H";
        Company company = new Company(null,username,"password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",Role.COMPANY,
                "H&H Company",new CompanyProfile(), List.of());
        companyRepo.save(company);

        //when
        Optional<Company> result = companyRepo.findByUsername(username);
        //then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getUser_id()).isEqualTo(company.getUser_id());
    }


    @Test
    void test_findByUsername_it_returns_empty_when_it_does_not_exist() {
        //given
        String username = "H&H";
        //when
        Optional<Company> result = companyRepo.findByUsername(username);
        //then
        assertThat(result).isEmpty();
    }
}