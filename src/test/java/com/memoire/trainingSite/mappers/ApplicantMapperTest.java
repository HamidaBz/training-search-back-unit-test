package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApplicantMapperTest {
    @Autowired
    ApplicantMapper applicantMapper;

    @Test
    void toEntity() {
        //given
        ApplicantDTO applicantDTO = new ApplicantDTO(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com", Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);

        Applicant applicant = new Applicant(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT, "Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        //when
        Applicant result = applicantMapper.toEntity(applicantDTO);
        //then
        assertThat(result).isEqualTo(applicant);
    }

    @Test
    void toResponseDTO() {
        //given
        Applicant applicant = new Applicant(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8),  List.of(),null);
        //when
        ApplicantResponseDTO result = applicantMapper.toResponseDTO(applicant);
        //then
        assertThat(result).isEqualTo(applicantResponseDTO);
    }
}