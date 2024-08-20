package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.models.Applicant;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApplicantDTOMapperTest {
    @Autowired
    ApplicantDTOMapper applicantDTOMapper;

    @Test
    void toEntity() {
        //given
        ApplicantDTO applicantDTO = new ApplicantDTO();
        Applicant applicant = new Applicant();
        //when
        Applicant result = applicantDTOMapper.toEntity(applicantDTO);
        //then
        assertThat(result).isEqualTo(applicant);
    }

    @Test
    void toResponseDTO() {
        //given
        Applicant applicant = new Applicant();
        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO();
        //when
        ApplicantResponseDTO result = applicantDTOMapper.toResponseDTO(applicant);
        //then
        assertThat(result).isEqualTo(applicantResponseDTO);
    }
}