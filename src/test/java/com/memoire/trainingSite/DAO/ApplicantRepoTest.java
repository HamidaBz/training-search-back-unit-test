package com.memoire.trainingSite.DAO;

import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public void itShouldCheckIfApplicantUsernameExists() {
        //given
        Applicant applicant = new Applicant(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com","Hamida","Bouaziz",
                 LocalDate.of(1990,9,8));
        applicantRepo.save(applicant);
        String username = "Hami";
        //when
        boolean exists = applicantRepo.existsByUsername(username);
        //then
        assertThat(exists).isTrue();
    }
    @Test
    public void itShouldCheckIfApplicantUsernameDoesNotExist() {
        //given
        String username = "user1";
        //when
        boolean exists = applicantRepo.existsByUsername(username);
        //then
        assertThat(exists).isFalse();
    }

    @Test
    public void itShouldReturnApplicantIfUsernameExists() {
        //given
        Applicant applicant = new Applicant(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com","Hamida","Bouaziz",
                LocalDate.of(1990,9,8));
        applicantRepo.save(applicant);

        String username = "Hami";
        //when
        Optional<Applicant>  applicantRetrieved = applicantRepo.findByUsername(username);
        //then
        assertThat(applicantRetrieved).isPresent();
        assertThat(applicantRetrieved.get().getUser_id()).isEqualTo(applicant.getUser_id());
    }

    @Test
    public void itShouldReturnEmptyOptionalOfApplicantIfUsernameDoesNotExist() {
        //given
        String username = "user1";
        //when
        Optional<Applicant>  applicantRetrieved = applicantRepo.findByUsername(username);
        //then
        assertThat(applicantRetrieved).isEmpty();
    }
}