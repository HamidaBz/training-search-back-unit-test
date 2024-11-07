package com.memoire.trainingSite.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.DTO.UserDTO;
import com.memoire.trainingSite.DTO.UserResponseDTO;
import com.memoire.trainingSite.Services.SiteUserService;
import com.memoire.trainingSite.config.TestSecurityConfig;
import com.memoire.trainingSite.models.Role;
import com.memoire.trainingSite.models.UserStatus;
import com.memoire.trainingSite.security.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(SiteUserController.class)
@Import(TestSecurityConfig.class)
class SiteUserControllerTest {
    @MockBean
    SiteUserRepo userRepo;  // userRepo et jwtService sont utilisé apar le mockuser
    @MockBean
    JWTService jwtService;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    SiteUserService userService;
    @Autowired
    ObjectMapper objectMapper; //used to convert objects to Json

    @Test
    void test_getUsers() throws Exception {

        //given
        UserResponseDTO userResponseDTO_1 = new UserResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com", Role.USER);

        UserResponseDTO userResponseDTO_2 = new UserResponseDTO(
                null,"Houhou",
                LocalDateTime.now(), UserStatus.ACTIVE, "0982883762",
                "houhou.bouaziz@gmail.com",Role.USER);

        when(userService.getAllUsers()).thenReturn(List.of(userResponseDTO_1,userResponseDTO_2));

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/users"));
        //then
        verify(userService).getAllUsers();
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(userResponseDTO_1,userResponseDTO_2))));
    }

    @Test
    void test_createUser() throws Exception {
        //given
        UserDTO userDTO =new UserDTO(
                null,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        String applicantJSON = objectMapper.writeValueAsString(userDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);

        //when
        when(userService.createUser(userDTO)).thenReturn(userResponseDTO);
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")// Perform the POST request and expect status isCreated
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(applicantJSON));

        //then
        verify(userService).createUser(userDTO);
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("Hami"))
                .andExpect(jsonPath("$.email").value("hami.bouaziz@gmail.com"));
    }

    @Test
    void test_getUserById_if_exists() throws Exception {
        //given
        Long userId = 1L;

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                userId,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        //when
        when(userService.getUserById(userId)).thenReturn(Optional.of(userResponseDTO));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/1"));
        //then
        verify(userService).getUserById(userId);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value(1L));
    }

    @Test
    void test_getUserById_if_does_not_exists() throws Exception {
        //given
        Long userId = 1L;

        //when
        when(userService.getUserById(userId)).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/1"));
        //then
        verify(userService).getUserById(userId);
        result.andExpect(status().isNotFound());
    }

    @Test
    void test_updateUser_if_exists() throws Exception {

        //given
        Long user_id = 1L;
        UserDTO userDTO = new UserDTO(
                user_id,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        String userDTOJson = objectMapper.writeValueAsString(userDTO);


        UserResponseDTO userResponseDTO =  new UserResponseDTO(
                user_id,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        //when
        when(userService.updateUser(user_id, userDTO)).thenReturn(Optional.of(userResponseDTO));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDTOJson));
        //then
        verify(userService).updateUser(user_id,userDTO);
        result.andExpect(status().isOk());
    }


    @Test
    void test_updateUser_if_does_not_exist() throws Exception {

        //given
        Long user_id = 1L;
        UserDTO userDTO = new UserDTO(
                user_id,"Hami","password",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        String userDTOJson = objectMapper.writeValueAsString(userDTO);

        //when
        when(userService.updateUser(user_id, userDTO)).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDTOJson));
        //then
        verify(userService).updateUser(user_id,userDTO);
        result.andExpect(status().isNotFound());
    }

    @Test
    void test_getUserByUserName_if_exists() throws Exception {
        //given
        String username = "Hami";

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                null,"Hami",
                LocalDateTime.now(), UserStatus.ACTIVE, "0799139309",
                "hami.bouaziz@gmail.com",Role.USER);
        //when
        when(userService.getUserByUsername(username)).thenReturn(Optional.of(userResponseDTO));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/search?username="+username));
        //then
        verify(userService).getUserByUsername(username);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username));
    }

    @Test
    void test_getUserByUserName_if_does_not_exist() throws Exception {
        //given
        String username = "Hami";
        //when
        when(userService.getUserByUsername(username)).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/search?username="+username));
        //then
        verify(userService).getUserByUsername(username);
        result.andExpect(status().isNotFound());
    }

    @Test
    void test_deleteUser() throws Exception {
        //given
        Long user_id = 1L;


        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/"+user_id));
        //then
        verify(userService).deleteUser(user_id);
        result.andExpect(status().isNoContent());
    }
}