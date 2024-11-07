package com.memoire.trainingSite.mappers;

import com.memoire.trainingSite.DTO.UserDTO;
import com.memoire.trainingSite.DTO.UserResponseDTO;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.SiteUser;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SiteUserMapperTest {
    @Autowired
    SiteUserMapper userMapper;

    @Test
    void toEntity() {
        //given
        UserDTO userDTO = new UserDTO(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com", Role.USER);

        SiteUser user = new SiteUser(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        //when
        SiteUser result = userMapper.toEntity(userDTO);
        //then
        assertThat(result).isEqualTo(user);
    }

    @Test
    void toResponseDTO() {
        //given
        SiteUser user = new SiteUser(
                null,"Hami","password",
                LocalDateTime.of(2020,10,8,4,2,1), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        UserResponseDTO userResponseDTO = new UserResponseDTO(
                null,"Hami",
                LocalDateTime.of(2020,10,8,4,2,1), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        //when
        UserResponseDTO result = userMapper.toResponseDTO(user);
        //then
        assertThat(result).isEqualTo(userResponseDTO);
    }

}