package com.memoire.trainingSite.services;

import com.memoire.trainingSite.DAO.ApplicantProfileRepo;
import com.memoire.trainingSite.Services.ApplicantProfileService;
import com.memoire.trainingSite.models.ApplicantProfile;
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
class ApplicantProfileServiceTest {
    @Mock
    ApplicantProfileRepo applicantProfileRepo;
    @InjectMocks
    ApplicantProfileService applicantProfileService;

    @Test
    void test_getApplicantProfileByProfileId_when_profileId_exists() {
        //given
        Long profile_id = 1L;
        ApplicantProfile applicantProfile = new ApplicantProfile();
        applicantProfile.setProfile_id(profile_id);

        when(applicantProfileRepo.findById(profile_id)).thenReturn(Optional.of(applicantProfile));
        //when
        Optional<ApplicantProfile> result =
                applicantProfileService.getApplicantProfileByProfileId(profile_id);
        //then
        verify(applicantProfileRepo).findById(profile_id);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(applicantProfile);
    }

    @Test
    void test_getApplicantProfileByProfileId_when_profileId_does_not_exist() {
        //given
        Long profile_id = 1L;
        when(applicantProfileRepo.findById(profile_id)).thenReturn(Optional.empty());
        //when
        Optional<ApplicantProfile> result =
                applicantProfileService.getApplicantProfileByProfileId(profile_id);
        //then
        verify(applicantProfileRepo).findById(profile_id);
        assertThat(result).isEmpty();
    }

    @Test
    void test_getApplicantProfileByApplicantUsername_when_applicant_username_exists(){
        //given
        String applicant_username = "Hami";
        ApplicantProfile applicantProfile = new ApplicantProfile();
        when(applicantProfileRepo.findByApplicantUsername(applicant_username)).
                thenReturn(Optional.of(applicantProfile));
        //when
        Optional<ApplicantProfile> result =
                applicantProfileService.getApplicantProfileByApplicantUsername(applicant_username);
        //then
        verify(applicantProfileRepo).findByApplicantUsername(applicant_username);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(applicantProfile);
    }

    @Test
    void test_getApplicantProfileByApplicantUsername_when_applicant_username_does_not_exist(){
        //given
        String applicant_username = "Hami";
        when(applicantProfileRepo.findByApplicantUsername(applicant_username)).
                thenReturn(Optional.empty());
        //when
        Optional<ApplicantProfile> result =
                applicantProfileService.getApplicantProfileByApplicantUsername(applicant_username);
        //then
        verify(applicantProfileRepo).findByApplicantUsername(applicant_username);
        assertThat(result).isEmpty();
    }
    @Test
    void test_updateProfile_if_profile_exists() {
        //given
        Long profile_id = 1L;

        ApplicantProfile existingApplicantProfile = new ApplicantProfile();
        existingApplicantProfile.setProfile_id(profile_id);
        existingApplicantProfile.setProfile_intro("former Intro");
        ApplicantProfile updatedApplicantProfile = new ApplicantProfile();
        updatedApplicantProfile.setProfile_id(profile_id);
        updatedApplicantProfile.setProfile_intro("new Intro");

        when(applicantProfileRepo.findById(profile_id)).thenReturn(Optional.of(existingApplicantProfile));
        when(applicantProfileRepo.save(updatedApplicantProfile)).thenReturn(updatedApplicantProfile);

        //when
        Optional<ApplicantProfile> result =
                applicantProfileService.updateProfile(profile_id, updatedApplicantProfile);
        //then
        assertThat(result).isPresent();
        verify(applicantProfileRepo).findById(profile_id);
        ArgumentCaptor<ApplicantProfile> captorProfile = ArgumentCaptor.forClass(ApplicantProfile.class);
        verify(applicantProfileRepo).save(captorProfile.capture());
        assertThat(captorProfile.getValue()).isEqualTo(updatedApplicantProfile);
        assertThat(result.get()).isEqualTo(updatedApplicantProfile);
    }

    @Test
    void test_updateProfile_if_profile_does_not_exist() {
        //given
        Long profile_id = 1L;

        ApplicantProfile updatedApplicantProfile = new ApplicantProfile();
        updatedApplicantProfile.setProfile_id(profile_id);
        updatedApplicantProfile.setProfile_intro("new Intro");

        when(applicantProfileRepo.findById(profile_id)).thenReturn(Optional.empty());
        //when
        Optional<ApplicantProfile> result =
                applicantProfileService.updateProfile(profile_id, updatedApplicantProfile);
        //then
        verify(applicantProfileRepo).findById(profile_id);
        verify(applicantProfileRepo, never()).save(updatedApplicantProfile);
        assertThat(result).isEmpty();

    }
}