package com.memoire.trainingSite.services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.Services.ApplicantService;
import com.memoire.trainingSite.mappers.ApplicantDTOMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ApplicantServiceTest {

    //@Autowired
    //MockMvc mockMvc;
    @InjectMocks
    ApplicantService applicantService;

    @MockBean
    ApplicantRepo applicantRepo;
    @Autowired
    ApplicantDTOMapper applicantDTOMapper;

    @Test
    public void unique_username_for_applicants(){
        //given

        ApplicantDTO applicant1 = new ApplicantDTO();
        //applicant1.setUsername("user1");
        System.out.println("concerne "+applicant1);

        // Mock the service to return a specific response
        when(applicantService.createApplicant(applicant1)).
                thenReturn(Optional.of(applicantDTOMapper.toResponseDTO(applicantDTOMapper.toEntity(applicant1))));

        // thenReturn(Optional.empty());

        // Mock the service to throw an exception or return a conflict status
        // when(applicantService.createApplicant(applicant2)).thenThrow(new RuntimeException("Username already exists"));

    }

}
