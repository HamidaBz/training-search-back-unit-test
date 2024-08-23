package com.memoire.trainingSite.services;

import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.UserDTO;
import com.memoire.trainingSite.DTO.UserResponseDTO;
import com.memoire.trainingSite.Services.SiteUserService;
import com.memoire.trainingSite.mappers.SiteUserMapper;
import com.memoire.trainingSite.models.Applicant;
import com.memoire.trainingSite.models.SiteUser;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SiteUserServiceTest {
    @Mock
    private SiteUserRepo siteUserRepo;
    @Mock
    private SiteUserMapper siteUserMapper;
    @InjectMocks
    private SiteUserService siteUserService;

    @Test
    public void testCreate_user() {
        //given
        String username = "Hami";

        UserDTO userDTO = new UserDTO(
                null,username,"password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");

        SiteUser user = new SiteUser(
                null,username,"password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                null,username,
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");


        when(siteUserMapper.toEntity(userDTO)).thenReturn(user);
        when(siteUserRepo.save(user)).thenReturn(user);
        when(siteUserMapper.toResponseDTO(user)).thenReturn(userResponseDTO);

        //when
        UserResponseDTO  result= siteUserService.createUser(userDTO);
        //then
        verify(siteUserRepo).save(user);
        assertThat(result.getUser_id()).isEqualTo(userDTO.getUser_id());
    }


    @Test
    void getUser_return_user_if_id_exists() {
        //given
        Long userId = 1L;

        SiteUser user =  new SiteUser(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");
        user.setUser_id(userId);

        UserResponseDTO userResponseDTO =  new UserResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");
        userResponseDTO.setUser_id(userId);

        when(siteUserRepo.findById(userId)).thenReturn(Optional.of(user));
        when(siteUserMapper.toResponseDTO(user)).thenReturn(userResponseDTO);
        //when
        Optional<UserResponseDTO> result = siteUserService.getUserById(userId);
        //then
        verify(siteUserRepo).findById(userId);
        assertThat(result).isPresent();
        assertThat(result.get().getUser_id()).isEqualTo(userId);
    }

    @Test
    void return_empty_optional_if_userId_does_not_exist() {
        //given
        Long userId = 1L;


        when(siteUserRepo.findById(userId)).thenReturn(Optional.empty());
        //when
        Optional<UserResponseDTO> result = siteUserService.getUserById(userId);
        //then
        verify(siteUserRepo).findById(userId);
        assertThat(result).isEmpty();
    }

    @Test
    void get_user_if_username_exists() {
        //given
        String existingUsername = "existingUsername";
        SiteUser user = new SiteUser(
                null,existingUsername,"password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");
       // applicant.setUsername(existingUsername);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                null,existingUsername,
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");
        //applicantResponseDTO.setUsername(existingUsername);

        when(siteUserMapper.toResponseDTO(user)).thenReturn(userResponseDTO);
        when(siteUserRepo.findByUsername(existingUsername)).thenReturn(Optional.of(user));

        //when
        Optional<UserResponseDTO> result = siteUserService.getUserByUsername(existingUsername);

        //then
        verify(siteUserRepo).findByUsername(existingUsername);
        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo(existingUsername);
    }

    @Test
    void return_empty_optional_if_user_username_does_not_exist(){
        //given
        String existingUsername = "existingUsername";
        when(siteUserRepo.findByUsername(existingUsername)).thenReturn(Optional.empty());

        //when
        Optional<UserResponseDTO> result = siteUserService.getUserByUsername(existingUsername);

        //then
        verify(siteUserRepo).findByUsername(existingUsername);
        assertThat(result).isEmpty();
    }

    @Test
    void testGetUsers() {
        //given
        SiteUser user_1 = new SiteUser(
                null,"Hami",null,
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");

        SiteUser user_2 = new SiteUser(
                null,"Houhou", null,
                LocalDateTime.now(), UserStatus.ACTIVE, "0982883762",
                "houhou.bouaziz@gmail.com");

        UserResponseDTO userResponseDTO_1 = new UserResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");

        UserResponseDTO userResponseDTO_2 = new UserResponseDTO(
                null,"Houhou",
                LocalDateTime.now(), UserStatus.ACTIVE, "0982883762",
                "houhou.bouaziz@gmail.com");

        when(siteUserRepo.findAll()).thenReturn(List.of(user_1,user_2));
        when(siteUserMapper.toResponseDTO(user_1)).thenReturn(userResponseDTO_1);
        when(siteUserMapper.toResponseDTO(user_2)).thenReturn(userResponseDTO_2);
        //when
        List<UserResponseDTO> result = siteUserService.getAllUsers();

        //then
        verify(siteUserRepo).findAll();
        assertThat(result).isEqualTo(List.of(userResponseDTO_1, userResponseDTO_2));
    }

    @Test
    void updateUser_if_exists() {
        //given
        Long userId = 1L;

        SiteUser existingUser = new SiteUser(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com");
        existingUser.setUser_id(userId);

        UserDTO updatedUserDTO = new UserDTO(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "06712237373",
                "hami.bouaziz@gmail.com");
        updatedUserDTO.setUser_id(userId);

        SiteUser updatedUser = new SiteUser(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "06712237373",
                "hami.bouaziz@gmail.com");
        updatedUser.setUser_id(userId);

        UserResponseDTO updatedUserResponseDTO = new UserResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "06712237373",
                "hami.bouaziz@gmail.com");
        updatedUserResponseDTO.setUser_id(userId);


        when(siteUserRepo.findById(userId)).thenReturn(Optional.of(existingUser));
        when(siteUserMapper.toEntity(updatedUserDTO)).thenReturn(updatedUser);
        when(siteUserMapper.toResponseDTO(updatedUser)).thenReturn(updatedUserResponseDTO);
        //when
        Optional<UserResponseDTO> result = siteUserService.updateUser(userId,updatedUserDTO);
        //then
        verify(siteUserRepo).findById(userId);
        ArgumentCaptor<Applicant> captorApplicant = ArgumentCaptor.forClass(Applicant.class);
        verify(siteUserRepo).save(captorApplicant.capture());
        assertThat(captorApplicant.getValue()).isEqualTo(updatedUser);
        assertThat(result).isPresent();
    }

    @Test
    void updateUser_return_emptyOptional_if_user_does_not_exists(){
        //given
        Long userId = 1L;

        UserDTO updatedUserDTO = new UserDTO(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139308",
                "hami.bouaziz@gmail.com");
        updatedUserDTO.setUser_id(userId);


        when(siteUserRepo.findById(userId)).thenReturn(Optional.empty());
        //when
        Optional<UserResponseDTO> result = siteUserService.updateUser(userId,updatedUserDTO);
        //then
        verify(siteUserRepo).findById(userId);
        assertThat(result).isEmpty();
    }

    @Test
    void deleteUser_must_call_deleteById() {
        //given
        Long userId = 1L;
        //when
        siteUserService.deleteUser(userId);
        //then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(siteUserRepo).deleteById(argumentCaptor.capture());
        Long capturedId = argumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(userId);
    }

}