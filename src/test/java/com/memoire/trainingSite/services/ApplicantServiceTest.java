package com.memoire.trainingSite.services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.Services.ApplicantService;
import com.memoire.trainingSite.mappers.ApplicantDTOMapper;
import com.memoire.trainingSite.models.Applicant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ApplicantServiceTest {
    @Mock
    private ApplicantRepo applicantRepo;
    @Mock
    private ApplicantDTOMapper applicantDTOMapper;
    @InjectMocks
    private ApplicantService applicantService;


    @Test
    public void create_applicant_if_no_applicant_with_this_username_exists() {
        //given
        String username = "newUser";

        ApplicantDTO applicantDTO = new ApplicantDTO();
        applicantDTO.setUsername(username);

        Applicant applicant = new Applicant();
        applicant.setUsername(username);

        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO();
        applicantResponseDTO.setUsername(username);


        when(applicantDTOMapper.toEntity(applicantDTO)).thenReturn(applicant);
        when(applicantRepo.existsByUsername(username)).thenReturn(false);
        when(applicantRepo.save(applicant)).thenReturn(applicant);
        when(applicantDTOMapper.toResponseDTO(applicant)).thenReturn(applicantResponseDTO);

        //when
        Optional<ApplicantResponseDTO>  result= applicantService.createApplicant(applicantDTO);
        //then
        verify(applicantRepo).existsByUsername(applicant.getUsername());
        verify(applicantRepo).save(applicant);
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo(username);
    }

    @Test
    public void donot_create_applicant_if_an_applicant_with_this_username_exists() {
        //given
        ApplicantDTO applicantDTO = new ApplicantDTO();
        String existingUsername = "existingUser";
        applicantDTO.setUsername(existingUsername);


        Applicant applicant = new Applicant();
        applicant.setUsername(existingUsername);

        when(applicantDTOMapper.toEntity(applicantDTO)).thenReturn(applicant);
        when(applicantRepo.existsByUsername(existingUsername)).thenReturn(true);

        //when
        Optional<ApplicantResponseDTO>  result= applicantService.createApplicant(applicantDTO);
        //then
        verify(applicantRepo).existsByUsername(applicant.getUsername());
        verify(applicantRepo, never()).save(applicant);
        assertThat(result).isEmpty();
    }

    @Test
    void get_applicant_return_applicant_if_id_exists() {
        //given
        Long applicantId = 1L;

        Applicant applicant = new Applicant();
        applicant.setUser_id(applicantId);

        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO();
        applicantResponseDTO.setUser_id(applicantId);

        when(applicantRepo.findById(applicantId)).thenReturn(Optional.of(applicant));
        when(applicantDTOMapper.toResponseDTO(applicant)).thenReturn(applicantResponseDTO);
        //when
        Optional<ApplicantResponseDTO> result =
                applicantService.getApplicant(applicantId);
        //then
        verify(applicantRepo).findById(applicantId);
        assertThat(result).isPresent();
        assertThat(result.get().getUser_id()).isEqualTo(applicantId);
    }

    @Test
    void return_empty_optional_if_applicantId_does_not_exist() {
        //given
        Long applicantId = 1L;


        when(applicantRepo.findById(applicantId)).thenReturn(Optional.empty());
        //when
        Optional<ApplicantResponseDTO> result =
                applicantService.getApplicant(applicantId);
        //then
        verify(applicantRepo).findById(applicantId);
        assertThat(result).isEmpty();
    }

    @Test
    void get_applicant_if_usename_exists() {
        //given
        String existingUsername = "existingUsername";
        Applicant applicant = new Applicant();
        applicant.setUsername(existingUsername);

        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO();
        applicantResponseDTO.setUsername(existingUsername);

        when(applicantDTOMapper.toResponseDTO(applicant)).thenReturn(applicantResponseDTO);
        when(applicantRepo.findByUsername(existingUsername)).thenReturn(Optional.of(applicant));

        //when
        Optional<ApplicantResponseDTO> result = applicantService.getApplicantByUsername(existingUsername);

        //then
        verify(applicantRepo).findByUsername(existingUsername);
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo(existingUsername);
    }

    @Test
    void return_empty_optional_if_applicant_username_does_not_exist(){
        //given
        String existingUsername = "existingUsername";
        when(applicantRepo.findByUsername(existingUsername)).thenReturn(Optional.empty());

        //when
        Optional<ApplicantResponseDTO> result = applicantService.getApplicantByUsername(existingUsername);

        //then
        verify(applicantRepo).findByUsername(existingUsername);
        assertThat(result).isEmpty();
    }

    @Test
    void getApplicants_must_call_findAll() {
        //given
        //when
        List<ApplicantResponseDTO> result = applicantService.getApplicants();
        //then
        verify(applicantRepo).findAll();
    }

    @Test
    void updateApplicant_if_exists() {
        //given
        Long applicantId = 1L;

        Applicant existedApplicant = new Applicant();
        existedApplicant.setUser_id(applicantId);

        ApplicantDTO updatedApplicantDTO = new ApplicantDTO();
        updatedApplicantDTO.setUser_id(applicantId);

        Applicant updatedApplicant = new Applicant();
        updatedApplicant.setUser_id(applicantId);

        when(applicantRepo.findById(applicantId)).thenReturn(Optional.of(existedApplicant));
        when(applicantDTOMapper.toEntity(updatedApplicantDTO)).thenReturn(updatedApplicant);
        //when
        Optional<ApplicantResponseDTO> result = applicantService.updateApplicant(applicantId,updatedApplicantDTO);
        //then
        verify(applicantRepo).findById(applicantId);
        ArgumentCaptor<Applicant> captorApplicant = ArgumentCaptor.forClass(Applicant.class);
        verify(applicantRepo).save(captorApplicant.capture());
        assertThat(captorApplicant.getValue()).isEqualTo(updatedApplicant);
        assertThat(result.isPresent());
    }

    @Test
    void updateApplicant_return_emptyOptional_if_applicant_doesnot_exists(){
        //given
        Long applicantId = 1L;

        ApplicantDTO updatedApplicantDTO = new ApplicantDTO();
        updatedApplicantDTO.setUser_id(applicantId);

        Applicant updatedApplicant = new Applicant();
        updatedApplicant.setUser_id(applicantId);

        when(applicantRepo.findById(applicantId)).thenReturn(Optional.empty());
        //when
        Optional<ApplicantResponseDTO> result = applicantService.updateApplicant(applicantId,updatedApplicantDTO);
        //then
        verify(applicantRepo).findById(applicantId);
        assertThat(result).isEmpty();
    }

    @Test
    void deleteApplicant_must_call_deleteById() {
        //given
        Long applicantId = 1L;
        //when
        applicantService.deleteApplicant(applicantId);
        //then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(applicantRepo).deleteById(argumentCaptor.capture());
        Long capturedId = argumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(applicantId);
    }

}
