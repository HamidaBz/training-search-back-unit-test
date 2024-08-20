package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.CompanyDTO;
import com.memoire.trainingSite.DTO.CompanyResponseDTO;
import com.memoire.trainingSite.models.Company;
import com.memoire.trainingSite.models.CompanyProfile;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CompanyMapperTest {

    @Autowired
    CompanyMapper companyMapper;

    @Test
    void test_toEntity() {
        //given
        CompanyDTO companyDTO = new CompanyDTO(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",
                "H&H Company");

        Company company = new Company(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",
                "H&H Company");
        //when
        Company result = companyMapper.toEntity(companyDTO);
        //then
        assertThat(result).isEqualTo(company);

    }

    @Test
    void test_toResponseDTO() {
            //given
            Company company = new Company(null,"H&H","password",
                    LocalDateTime.of(2020,2,10, 1,15),
                    UserStatus.ACTIVE, "0555657585","h.h@gmail.com",
                    "H&H Company");

            CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",
                "H&H Company", new CompanyProfile(), List.of());
            //when
            CompanyResponseDTO result = companyMapper.toResponseDTO(company);
            //then
            assertThat(result).isEqualTo(companyResponseDTO);

    }
}