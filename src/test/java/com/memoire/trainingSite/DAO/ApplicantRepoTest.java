package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Applicant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class ApplicantRepoTest {

    @Autowired
    ApplicantRepo applicantRepo;

    @AfterEach
    void tearDown(){
        applicantRepo.deleteAll();
    }

    @Test
    void itShouldCheckIfApplicantUsernameExists() {
        //given
        Applicant applicant = new Applicant();
        applicant.setUsername("user1");
        applicantRepo.save(applicant);
        String username = "user1";
        //when
        boolean exists = applicantRepo.existsByUsername(username);
        //then
        assertThat(exists).isTrue();
    }
    @Test
    void itShouldCheckIfApplicantUsernameDoesNotExist() {
        //given
        String username = "user1";
        //when
        boolean exists = applicantRepo.existsByUsername(username);
        //then
        assertThat(exists).isFalse();
    }

    @Test
    void itShouldReturnApplicantIfUsernameExists() {
        //given
        Applicant applicant = new Applicant();
        applicant.setUsername("user1");
        applicantRepo.save(applicant);

        String username = "user1";
        //when
        Optional<Applicant>  applicantRetrieved = applicantRepo.findByUsername(username);
        //then
        assertThat(applicantRetrieved).isPresent();
        assertThat(applicantRetrieved.get()).isEqualTo(applicant);
    }

    @Test
    void itShouldReturnEmptyOptionalOfApplicantIfUsernameDoesNotExist() {
        //given
        String username = "user1";
        //when
        Optional<Applicant>  applicantRetrieved = applicantRepo.findByUsername(username);
        //then
        assertThat(applicantRetrieved).isEmpty();
    }
}