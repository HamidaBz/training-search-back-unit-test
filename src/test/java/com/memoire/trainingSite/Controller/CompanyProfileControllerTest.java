package com.memoire.trainingSite.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memoire.trainingSite.Services.CompanyProfileService;
import com.memoire.trainingSite.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyProfileController.class)
class CompanyProfileControllerTest {
    @MockBean
    CompanyProfileService companyProfileService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test_getCompanyProfileByProfileId_when_profile_exists() throws Exception {
        //given
        Long profileId = 1L;
        CompanyProfile companyprofile = new CompanyProfile();
        companyprofile.setProfile_id(profileId);
        when(companyProfileService.getCompanyProfileByProfileId(profileId)).
                thenReturn(Optional.of(companyprofile));
        //when
        ResultActions result = mockMvc.
                perform(MockMvcRequestBuilders.get("/v1/companyprofiles/1"));
        //then
        verify(companyProfileService).getCompanyProfileByProfileId(profileId);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.profile_id").value(profileId));
    }

    @Test
    void test_getCompanyProfileByProfileId_when_profile_does_not_exist() throws Exception {
        //given
        Long profileId = 1L;
        when(companyProfileService.getCompanyProfileByProfileId(profileId)).
                thenReturn(Optional.empty());
        //when
        ResultActions result = mockMvc.
                perform(MockMvcRequestBuilders.get("/v1/companyprofiles/1"));
        //then
        verify(companyProfileService).getCompanyProfileByProfileId(profileId);
        result.andExpect(status().isNotFound());

    }

    @Test
    void test_getCompanyProfileByCompanyUsername_when_profile_exists() throws Exception {
        //given
        String companyUsername = "Hami";
        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setCompany(new Company(1L,companyUsername,"password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company", companyProfile, List.of()));
        when(companyProfileService.getCompanyProfileByCompanyUsername(companyUsername)).
                thenReturn(Optional.of(companyProfile));
        //when
        ResultActions result = mockMvc.
                perform(MockMvcRequestBuilders.get("/v1/companyprofiles/search?companyUsername=Hami"));
        //then
        verify(companyProfileService).getCompanyProfileByCompanyUsername(companyUsername);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.company").value(null));
    }

    @Test
    void test_getCompanyProfileByCompanyUsername_when_profile_does_not_exist() throws Exception {
        //given
        String companyUsername = "Hami";

        when(companyProfileService.getCompanyProfileByCompanyUsername(companyUsername)).
                thenReturn(Optional.empty());
        //when
        ResultActions result = mockMvc.
                perform(MockMvcRequestBuilders.get("/v1/companyprofiles/search?companyUsername=Hami"));
        //then
        verify(companyProfileService).getCompanyProfileByCompanyUsername(companyUsername);
        result.andExpect(status().isNotFound());

    }

    @Test
    void test_updateProfile_if_profile_exists() throws Exception {
        //given
        Long profileId = 1L;

        CompanyProfile newCompanyProfile = new CompanyProfile();
        newCompanyProfile.setProfile_id(profileId);
        String newApplicantProfileJson = objectMapper.writeValueAsString(newCompanyProfile);
        when(companyProfileService.updateCompanyProfile(profileId, newCompanyProfile)).thenReturn(Optional.of(newCompanyProfile));
        //when
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.put("/v1/companyprofiles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newApplicantProfileJson));
        //then
        verify(companyProfileService).updateCompanyProfile(profileId, newCompanyProfile);
        result.andExpect(status().isOk());
    }

    @Test
    void test_updateProfile_if_profile_does_not_exist() throws Exception {
        //given
        Long profileId = 1L;

        CompanyProfile newCompanyProfile = new CompanyProfile();
        newCompanyProfile.setProfile_id(profileId);
        String newCompanyProfileJson = objectMapper.writeValueAsString(newCompanyProfile);
        when(companyProfileService.updateCompanyProfile(profileId, newCompanyProfile)).thenReturn(Optional.empty());
        //when
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.put("/v1/companyprofiles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCompanyProfileJson));
        //then
        verify(companyProfileService).updateCompanyProfile(profileId, newCompanyProfile);
        result.andExpect(status().isNotFound());
    }
}