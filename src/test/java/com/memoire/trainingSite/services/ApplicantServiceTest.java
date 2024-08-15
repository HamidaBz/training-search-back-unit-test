package com.memoire.trainingSite.services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.Services.ApplicantService;
import com.memoire.trainingSite.mappers.ApplicantDTOMapper;
import com.memoire.trainingSite.models.Applicant;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ApplicantServiceTest {
    @Mock
    ApplicantRepo applicantRepo;
    @Mock
    ApplicantDTOMapper applicantDTOMapper;
    @InjectMocks
    ApplicantService applicantService;

    @Test
    void create_applicant_if_no_applicant_with_this_username_exists() {
        //given
        ApplicantDTO applicantDTO = new ApplicantDTO();
        applicantDTO.setUsername("user1");
        String username = "user1";

        Applicant applicant = new Applicant();
        applicant.setUsername(username);

        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO();
        applicantResponseDTO.setUsername(username);


        when(applicantDTOMapper.toEntity(applicantDTO)).thenReturn(applicant);
        when(applicantRepo.existsByUsername(username)).thenReturn(false);
        when(applicantDTOMapper.toResponseDTO(applicant)).thenReturn(applicantResponseDTO);

        //when
        Optional<ApplicantResponseDTO>  applicantResponseDTOOpt= applicantService.createApplicant(applicantDTO);
        //then
        verify(applicantRepo).existsByUsername(applicant.getUsername());
        verify(applicantRepo).save(applicant);
        assertThat(applicantResponseDTOOpt).isPresent();
    }

    @Test
    void donot_create_applicant_if_an_applicant_with_this_username_exists() {
        //given
        ApplicantDTO applicantDTO = new ApplicantDTO();
        applicantDTO.setUsername("user1");
        String username = "user1";

        Applicant applicant = new Applicant();
        applicant.setUsername(username);

        when(applicantDTOMapper.toEntity(applicantDTO)).thenReturn(applicant);
        when(applicantRepo.existsByUsername(username)).thenReturn(true);

        //when
        Optional<ApplicantResponseDTO>  applicantResponseDTO= applicantService.createApplicant(applicantDTO);
        //then
        verify(applicantRepo).existsByUsername(applicant.getUsername());
        assertThat(applicantResponseDTO).isEmpty();
    }

    @Test
    @Disabled
    void getApplicant() {
    }

    @Test
    @Disabled
    void getApplicantByUsername() {
    }

    @Test
    @Disabled
    void getApplicants() {
    }

    @Test
    @Disabled
    void updateApplicant() {
    }

    @Test
    @Disabled
    void deleteApplicant() {
    }
}
