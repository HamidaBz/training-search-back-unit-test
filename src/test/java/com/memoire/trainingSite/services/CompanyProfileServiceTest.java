package com.memoire.trainingSite.services;

import com.memoire.trainingSite.DAO.ApplicantProfileRepo;
import com.memoire.trainingSite.DAO.CompanyProfileRepo;
import com.memoire.trainingSite.Services.ApplicantProfileService;
import com.memoire.trainingSite.Services.CompanyProfileService;
import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.CompanyProfile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyProfileServiceTest {

    @Mock
    CompanyProfileRepo companyProfileRepo;
    @InjectMocks
    CompanyProfileService companyProfileService;

    @Test
    void test_getCompanyProfileByProfileId_when_profileId_exists() {
        //given
        Long profile_id = 1L;
        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setProfile_id(profile_id);

        when(companyProfileRepo.findById(profile_id)).thenReturn(Optional.of(companyProfile));
        //when
        Optional<CompanyProfile> result =
                companyProfileService.getCompanyProfileByProfileId(profile_id);
        //then
        verify(companyProfileRepo).findById(profile_id);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(companyProfile);
    }

    @Test
    void test_getCompanyProfileByProfileId_when_profileId_does_not_exist() {
        //given
        Long profile_id = 1L;
        when(companyProfileRepo.findById(profile_id)).thenReturn(Optional.empty());
        //when
        Optional<CompanyProfile> result =
                companyProfileService.getCompanyProfileByProfileId(profile_id);
        //then
        verify(companyProfileRepo).findById(profile_id);
        assertThat(result).isEmpty();
    }

    @Test
    void test_getCompanyProfileByApplicantUsername_when_company_username_exists(){
        //given
        String company_username = "Hami";
        CompanyProfile companyProfile = new CompanyProfile();
        when(companyProfileRepo.findByCompanyUsername(company_username)).
                thenReturn(Optional.of(companyProfile));
        //when
        Optional<CompanyProfile> result =
                companyProfileService.getCompanyProfileByCompanyUsername(company_username);
        //then
        verify(companyProfileRepo).findByCompanyUsername(company_username);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(companyProfile);
    }

    @Test
    void test_getCompanyProfileByApplicantUsername_when_company_username_does_not_exist(){
        //given
        String company_username = "Hami";
        when(companyProfileRepo.findByCompanyUsername(company_username)).
                thenReturn(Optional.empty());
        //when
        Optional<CompanyProfile> result =
                companyProfileService.getCompanyProfileByCompanyUsername(company_username);
        //then
        verify(companyProfileRepo).findByCompanyUsername(company_username);
        assertThat(result).isEmpty();
    }
    @Test
    void test_updateProfile_if_profile_exists() {
        //given
        Long profile_id = 1L;

        CompanyProfile existingCompanyProfile = new CompanyProfile();
        existingCompanyProfile.setProfile_id(profile_id);
        existingCompanyProfile.setProfile_intro("former Intro");
        CompanyProfile updatedCompanyProfile = new CompanyProfile();
        updatedCompanyProfile.setProfile_id(profile_id);
        updatedCompanyProfile.setProfile_intro("new Intro");

        when(companyProfileRepo.findById(profile_id)).thenReturn(Optional.of(existingCompanyProfile));
        when(companyProfileRepo.save(updatedCompanyProfile)).thenReturn(updatedCompanyProfile);

        //when
        Optional<CompanyProfile> result =
                companyProfileService.updateCompanyProfile(profile_id, updatedCompanyProfile);
        //then
        assertThat(result).isPresent();
        verify(companyProfileRepo).findById(profile_id);
        ArgumentCaptor<CompanyProfile> captorProfile = ArgumentCaptor.forClass(CompanyProfile.class);
        verify(companyProfileRepo).save(captorProfile.capture());
        assertThat(captorProfile.getValue()).isEqualTo(updatedCompanyProfile);
        assertThat(result.get()).isEqualTo(updatedCompanyProfile);
    }

    @Test
    void test_updateProfile_if_profile_does_not_exist() {
        //given
        Long profile_id = 1L;

        CompanyProfile updatedCompanyProfile = new CompanyProfile();
        updatedCompanyProfile.setProfile_id(profile_id);
        updatedCompanyProfile.setProfile_intro("new Intro");

        when(companyProfileRepo.findById(profile_id)).thenReturn(Optional.empty());
        //when
        Optional<CompanyProfile> result =
                companyProfileService.updateCompanyProfile(profile_id, updatedCompanyProfile);
        //then
        verify(companyProfileRepo).findById(profile_id);
        verify(companyProfileRepo, never()).save(updatedCompanyProfile);
        assertThat(result).isEmpty();

    }
}