package com.memoire.trainingSite.services;

import com.memoire.trainingSite.DAO.ApplicantProfileRepo;
import com.memoire.trainingSite.Services.ApplicantProfileService;
import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.ApplicantProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void updateProfile() {
    }
}