package com.memoire.trainingSite.DAO;


import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.SiteUser;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class SiteUserRepoTest {
    @Autowired
    SiteUserRepo siteUserRepo;

    @AfterEach
    void tearDown(){
        siteUserRepo.deleteAll();
    }

    @Test
    public void test_existsByUsername_it_returns_true_when_user_exists() {
        //given
        SiteUser user = new SiteUser(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com", Role.USER);
        siteUserRepo.save(user);
        String username = "Hami";
        //when
        boolean exists = siteUserRepo.existsByUsername(username);
        //then
        assertThat(exists).isTrue();
    }
    @Test
    public void test_existsByUsername_it_returns_false_when_user_does_not_exist() {
        //given
        String username = "user1";
        //when
        boolean exists = siteUserRepo.existsByUsername(username);
        //then
        assertThat(exists).isFalse();
    }

    @Test
    public void test_findByUsername_it_returns_user_when_it_exists() {
        //given
        SiteUser user = new SiteUser(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        siteUserRepo.save(user);

        String username = "Hami";
        //when
        Optional<SiteUser> applicantRetrieved = siteUserRepo.findByUsername(username);
        //then
        assertThat(applicantRetrieved).isPresent();
        assertThat(applicantRetrieved.get().getUser_id()).isEqualTo(user.getUser_id());
    }

    @Test
    public void test_findByUsername_it_returns_empty_when_it_does_not_exist() {
        //given
        String username = "user1";
        //when
        Optional<SiteUser>  applicantRetrieved = siteUserRepo.findByUsername(username);
        //then
        assertThat(applicantRetrieved).isEmpty();
    }
}