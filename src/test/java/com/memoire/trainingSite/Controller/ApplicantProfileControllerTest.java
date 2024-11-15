package com.memoire.trainingSite.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.Services.ApplicantProfileService;
import com.memoire.trainingSite.config.TestSecurityConfig;
import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.UserStatus;
import com.memoire.trainingSite.security.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicantProfileController.class)
@Import(TestSecurityConfig.class)
class ApplicantProfileControllerTest {

    @MockBean
    ApplicantProfileService applicantProfileService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    SiteUserRepo userRepo;  // userRepo et jwtService sont utilisé apar le mockuser
    @MockBean
    JWTService jwtService;

    @Test
    void test_getApplicantProfileByProfileId_when_profile_exists() throws Exception {
        //given
        Long profileId = 1L;
        ApplicantProfile applicantprofile = new ApplicantProfile();
        applicantprofile.setProfile_id(profileId);
        when(applicantProfileService.getApplicantProfileByProfileId(profileId)).
                thenReturn(Optional.of(applicantprofile));
        //when
        ResultActions result = mockMvc.
                perform(MockMvcRequestBuilders.get("/v1/applicantprofiles/1"));
        //then
        verify(applicantProfileService).getApplicantProfileByProfileId(profileId);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.profile_id").value(profileId));
    }

    @Test
    void test_getApplicantProfileByProfileId_when_profile_does_not_exist() throws Exception {
        //given
        Long profileId = 1L;
        when(applicantProfileService.getApplicantProfileByProfileId(profileId)).
                thenReturn(Optional.empty());
        //when
        ResultActions result = mockMvc.
                perform(MockMvcRequestBuilders.get("/v1/applicantprofiles/1"));
        //then
        verify(applicantProfileService).getApplicantProfileByProfileId(profileId);
        result.andExpect(status().isNotFound());

    }

    @Test
    void test_getApplicantProfileByApplicantUsername_when_profile_exists() throws Exception {
        //given
        String applicantUsername = "Hami";
        ApplicantProfile applicantprofile = new ApplicantProfile();
        applicantprofile.setApplicant(new Applicant(
                1L,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com", Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),null));
        when(applicantProfileService.getApplicantProfileByApplicantUsername(applicantUsername)).
                thenReturn(Optional.of(applicantprofile));
        //when
        ResultActions result = mockMvc.
                perform(MockMvcRequestBuilders.get("/v1/applicantprofiles/search?applicantUsername=Hami"));
        //then
        verify(applicantProfileService).getApplicantProfileByApplicantUsername(applicantUsername);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.applicant.username").value(applicantUsername));
    }

    @Test
    void test_getApplicantProfileByApplicantUsername_when_profile_does_not_exist() throws Exception {
        //given
        String applicantUsername = "Hami";

        when(applicantProfileService.getApplicantProfileByApplicantUsername(applicantUsername)).
                thenReturn(Optional.empty());
        //when
        ResultActions result = mockMvc.
                perform(MockMvcRequestBuilders.get("/v1/applicantprofiles/search?applicantUsername=Hami"));
        //then
        verify(applicantProfileService).getApplicantProfileByApplicantUsername(applicantUsername);
        result.andExpect(status().isNotFound());

    }

    @Test
    void test_updateProfile_if_profile_exists() throws Exception {
        //given
        Long profileId = 1L;

        ApplicantProfile newApplicantProfile = new ApplicantProfile();
        newApplicantProfile.setProfile_id(profileId);
        String newApplicantProfileJson = objectMapper.writeValueAsString(newApplicantProfile);
        when(applicantProfileService.updateProfile(profileId, newApplicantProfile)).thenReturn(Optional.of(newApplicantProfile));
        //when
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.put("/v1/applicantprofiles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newApplicantProfileJson));
        //then
        verify(applicantProfileService).updateProfile(profileId, newApplicantProfile);
        result.andExpect(status().isOk());
    }

    @Test
    void test_updateProfile_if_profile_does_not_exist() throws Exception {
        //given
        Long profileId = 1L;

        ApplicantProfile newApplicantProfile = new ApplicantProfile();
        newApplicantProfile.setProfile_id(profileId);
        String newApplicantProfileJson = objectMapper.writeValueAsString(newApplicantProfile);
        when(applicantProfileService.updateProfile(profileId, newApplicantProfile)).thenReturn(Optional.empty());
        //when
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.put("/v1/applicantprofiles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newApplicantProfileJson));
        //then
        verify(applicantProfileService).updateProfile(profileId, newApplicantProfile);
        result.andExpect(status().isNotFound());
    }
}