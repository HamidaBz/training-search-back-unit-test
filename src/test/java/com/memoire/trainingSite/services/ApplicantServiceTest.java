package com.memoire.trainingSite.services;

import com.memoire.trainingSite.DAO.ApplicantRepo;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.Services.ApplicantService;
import com.memoire.trainingSite.mappers.ApplicantMapper;
import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicantServiceTest {
    @Mock
    private ApplicantRepo applicantRepo;
    @Mock
    private ApplicantMapper applicantMapper;
    @InjectMocks
    private ApplicantService applicantService;

    @Test
    public void testCreate_applicant() {
        //given
        String username = "Hami";

        ApplicantDTO applicantDTO = new ApplicantDTO(
                null,username,"password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com", Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);

        Applicant applicant = new Applicant(
                null,username,"password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);

        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO(
                null,username,
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);


        when(applicantMapper.toEntity(applicantDTO)).thenReturn(applicant);
        when(applicantRepo.save(applicant)).thenReturn(applicant);
        when(applicantMapper.toResponseDTO(applicant)).thenReturn(applicantResponseDTO);

        //when
        ApplicantResponseDTO  result= applicantService.createApplicant(applicantDTO);
        //then
        verify(applicantRepo).save(applicant);
        assertThat(result.getUser_id()).isEqualTo(applicantDTO.getUser_id());
    }


    @Test
    void getApplicant_return_applicant_if_id_exists() {
        //given
        Long applicantId = 1L;

        Applicant applicant =  new Applicant(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        applicant.setUser_id(applicantId);

        ApplicantResponseDTO applicantResponseDTO =  new ApplicantResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com", Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        applicantResponseDTO.setUser_id(applicantId);

        when(applicantRepo.findById(applicantId)).thenReturn(Optional.of(applicant));
        when(applicantMapper.toResponseDTO(applicant)).thenReturn(applicantResponseDTO);
        //when
        Optional<ApplicantResponseDTO> result =
                applicantService.getApplicantById(applicantId);
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
                applicantService.getApplicantById(applicantId);
        //then
        verify(applicantRepo).findById(applicantId);
        assertThat(result).isEmpty();
    }

    @Test
    void get_applicant_if_username_exists() {
        //given
        String existingUsername = "existingUsername";
        Applicant applicant = new Applicant(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        applicant.setUsername(existingUsername);

        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        applicantResponseDTO.setUsername(existingUsername);

        when(applicantMapper.toResponseDTO(applicant)).thenReturn(applicantResponseDTO);
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
    void testGetApplicants() {
        //given
        Applicant applicant_1 = new Applicant(
                null,"Hami",null,
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);

        Applicant applicant_2 = new Applicant(
                null,"Houhou", null,
                LocalDateTime.now(), UserStatus.ACTIVE, "0982883762",
                "houhou.bouaziz@gmail.com",Role.APPLICANT,"Houhou","Bouaziz",
                LocalDate.of(1995,7,1), List.of(),null);

        ApplicantResponseDTO applicantResponseDTO_1 = new ApplicantResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);

        ApplicantResponseDTO applicantResponseDTO_2 = new ApplicantResponseDTO(
                null,"Houhou",
                LocalDateTime.now(), UserStatus.ACTIVE, "0982883762",
                "houhou.bouaziz@gmail.com",Role.APPLICANT,"Houhou","Bouaziz",
                LocalDate.of(1995,7,1), List.of(),null);

        when(applicantRepo.findAll()).thenReturn(List.of(applicant_1,applicant_2));
        when(applicantMapper.toResponseDTO(applicant_1)).thenReturn(applicantResponseDTO_1);
        when(applicantMapper.toResponseDTO(applicant_2)).thenReturn(applicantResponseDTO_2);
        //when
        List<ApplicantResponseDTO> result = applicantService.getApplicants();

        //then
        verify(applicantRepo).findAll();
        assertThat(result).isEqualTo(List.of(applicantResponseDTO_1, applicantResponseDTO_2));
    }

    @Test
    void updateApplicant_if_exists() {
        //given
        Long applicantId = 1L;

        Applicant existedApplicant = new Applicant(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        existedApplicant.setUser_id(applicantId);

        ApplicantDTO updatedApplicantDTO = new ApplicantDTO(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "06712237373",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1995,9,8), List.of(),null);
        updatedApplicantDTO.setUser_id(applicantId);

        Applicant updatedApplicant = new Applicant(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "06712237373",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1995,9,8), List.of(),null);
        updatedApplicant.setUser_id(applicantId);

        ApplicantResponseDTO updatedApplicantResponseDTO = new ApplicantResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "06712237373",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1995,9,8), List.of(),null);
        updatedApplicantResponseDTO.setUser_id(applicantId);


        when(applicantRepo.findById(applicantId)).thenReturn(Optional.of(existedApplicant));
        when(applicantMapper.toEntity(updatedApplicantDTO)).thenReturn(updatedApplicant);
        when(applicantMapper.toResponseDTO(updatedApplicant)).thenReturn(updatedApplicantResponseDTO);
        //when
        Optional<ApplicantResponseDTO> result = applicantService.updateApplicant(applicantId,updatedApplicantDTO);
        //then
        verify(applicantRepo).findById(applicantId);
        ArgumentCaptor<Applicant> captorApplicant = ArgumentCaptor.forClass(Applicant.class);
        verify(applicantRepo).save(captorApplicant.capture());
        assertThat(captorApplicant.getValue()).isEqualTo(updatedApplicant);
        assertThat(result).isPresent();
    }

    @Test
    void updateApplicant_return_emptyOptional_if_applicant_does_not_exists(){
        //given
        Long applicantId = 1L;

        ApplicantDTO updatedApplicantDTO = new ApplicantDTO(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139308",
                "hami.bouaziz@gmail.com",Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        updatedApplicantDTO.setUser_id(applicantId);


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
        doNothing().when(applicantRepo).deleteById(applicantId);
        applicantService.deleteApplicant(applicantId);
        //then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(applicantRepo).deleteById(argumentCaptor.capture());
        Long capturedId = argumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(applicantId);
    }

}
