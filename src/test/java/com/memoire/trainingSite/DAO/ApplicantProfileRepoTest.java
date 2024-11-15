package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.ApplicantProfile;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ApplicantProfileRepoTest {
    @Autowired
    ApplicantProfileRepo applicantProfileRepo;
    @Autowired
    ApplicantRepo applicantRepo;
    @BeforeEach
    void setUp() {
        applicantRepo.deleteAll();
        applicantProfileRepo.deleteAll();
    }
    @Test
    void testFindByApplicantUsername_WhenUsernameExists() {
        //given
        ApplicantProfile applicantProfile = new ApplicantProfile();
        Applicant applicant = new Applicant(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com", Role.APPLICANT,"Hamida","Bouaziz",
                LocalDate.of(1990,9,8), List.of(),applicantProfile);

        applicantProfile.setApplicant(applicant);
        applicantRepo.save(applicant);
        // when
        Optional<ApplicantProfile> foundProfile = applicantProfileRepo.findByApplicantUsername("Hami");
        // then
        assertThat(foundProfile).isPresent();
        assertThat(foundProfile.get().getApplicant().getUsername()).isEqualTo("Hami");
    }

    @Test
    void testFindByApplicantUsername_WhenUsernameDoesNotExist() {
        // when
        Optional<ApplicantProfile> foundProfile = applicantProfileRepo.findByApplicantUsername("non_existent_user");

        // then
        assertThat(foundProfile).isEmpty();
    }

}