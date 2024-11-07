package com.memoire.trainingSite.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.DTO.ApplicantResponseDTO;
import com.memoire.trainingSite.Services.ApplicantService;
import com.memoire.trainingSite.models.UserStatus;
import com.memoire.trainingSite.security.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest({ApplicantController.class, JWTService.class})
class ApplicantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JWTService jwtService;

    @MockBean
    ApplicantService applicantService;
    @Autowired
    ObjectMapper objectMapper; //used to convert objects to Json

    @Test
     void test_getApplicants() throws Exception {

        //given
        ApplicantResponseDTO applicantResponseDTO_1 = new ApplicantResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",List.of(),"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);

        ApplicantResponseDTO applicantResponseDTO_2 = new ApplicantResponseDTO(
                null,"Houhou",
                LocalDateTime.now(), UserStatus.ACTIVE, "0982883762",
                "houhou.bouaziz@gmail.com",List.of(),"Houhou","Bouaziz",
                LocalDate.of(1995,7,1), List.of(),null);

        when(applicantService.getApplicants()).thenReturn(List.of(applicantResponseDTO_1,applicantResponseDTO_2));

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/applicants"));
        //then
        verify(applicantService).getApplicants();
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(applicantResponseDTO_1,applicantResponseDTO_2))));
    }

    @Test
    void test_createApplicant() throws Exception {
        //given
        ApplicantDTO applicantDTO =new ApplicantDTO(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",List.of(),"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        String applicantJSON = objectMapper.writeValueAsString(applicantDTO);

        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",List.of(),"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);

        //when
        when(applicantService.createApplicant(applicantDTO)).thenReturn(applicantResponseDTO);
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/applicants")// Perform the POST request and expect status isCreated
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(applicantJSON));

        //then
        verify(applicantService).createApplicant(applicantDTO);
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("Hami"))
                .andExpect(jsonPath("$.email").value("hami.bouaziz@gmail.com"))
                .andExpect(jsonPath("$.applicant_firstname").value("Hamida"))
                .andExpect(jsonPath("$.applicant_lastname").value("Bouaziz"));
    }

    @Test
    void test_getApplicantById_if_exists() throws Exception {
        //given
        Long applicantId = 1L;

        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO(
                applicantId,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",List.of(),"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        //when
        when(applicantService.getApplicantById(applicantId)).thenReturn(Optional.of(applicantResponseDTO));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/applicants/1"));
        //then
        verify(applicantService).getApplicantById(applicantId);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value(1L));
    }

    @Test
    void test_getApplicantById_if_does_not_exists() throws Exception {
        //given
        Long applicantId = 1L;

        //when
        when(applicantService.getApplicantById(applicantId)).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/applicants/1"));
        //then
        verify(applicantService).getApplicantById(applicantId);
        result.andExpect(status().isNotFound());
    }

    @Test
    void test_updateApplicant_if_exists() throws Exception {

        //given
        Long applicant_id = 1L;
        ApplicantDTO applicantDTO = new ApplicantDTO(
                applicant_id,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",List.of(),"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        String applicantDTOJson = objectMapper.writeValueAsString(applicantDTO);


        ApplicantResponseDTO applicantResponseDTO =  new ApplicantResponseDTO(
                applicant_id,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",List.of(),"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        //when
        when(applicantService.updateApplicant(applicant_id, applicantDTO)).thenReturn(Optional.of(applicantResponseDTO));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/applicants/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(applicantDTOJson));
        //then
        verify(applicantService).updateApplicant(applicant_id,applicantDTO);
        result.andExpect(status().isOk());
    }


    @Test
    void test_updateApplicant_if_does_not_exist() throws Exception {

        //given
        Long applicant_id = 1L;
        ApplicantDTO applicantDTO = new ApplicantDTO(
                applicant_id,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",List.of(),"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        String applicantDTOJson = objectMapper.writeValueAsString(applicantDTO);

        //when
        when(applicantService.updateApplicant(applicant_id, applicantDTO)).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/applicants/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(applicantDTOJson));
        //then
        verify(applicantService).updateApplicant(applicant_id,applicantDTO);
        result.andExpect(status().isNotFound());
    }

    @Test
    void test_getApplicantByUserName_if_exists() throws Exception {
        //given
        String username = "Hami";

        ApplicantResponseDTO applicantResponseDTO = new ApplicantResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",List.of(),"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null);
        //when
        when(applicantService.getApplicantByUsername(username)).thenReturn(Optional.of(applicantResponseDTO));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/applicants/search?username="+username));
        //then
        verify(applicantService).getApplicantByUsername(username);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username));
    }

    @Test
    void test_getApplicantByUserName_if_doesnot_exist() throws Exception {
        //given
        String username = "Hami";
        //when
        when(applicantService.getApplicantByUsername(username)).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/applicants/search?username="+username));
        //then
        verify(applicantService).getApplicantByUsername(username);
        result.andExpect(status().isNotFound());
    }

    @Test
    void test_deleteApplicant() throws Exception {
        //given
        Long applicant_id = 1L;
       Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
       String jwtToken = jwtService.generateToken(authentication);
        System.out.println(" token  : "  +jwtToken);
        //when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.delete("/v1/applicants/"+applicant_id)
                       .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                      );

        //then
        verify(applicantService).deleteApplicant(applicant_id);
        result.andExpect(status().isNoContent());
    }

}