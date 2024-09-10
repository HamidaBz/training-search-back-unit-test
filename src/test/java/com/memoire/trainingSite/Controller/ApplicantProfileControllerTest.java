package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.Services.ApplicantProfileService;
import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicantProfileController.class)
class ApplicantProfileControllerTest {

    @MockBean
    ApplicantProfileService applicantProfileService;
    @Autowired
    MockMvc mockMvc;

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
                "hami.bouaziz@gmail.com", List.of(),"Hamida","Bouaziz",
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
    void updateProfile() {
    }
}