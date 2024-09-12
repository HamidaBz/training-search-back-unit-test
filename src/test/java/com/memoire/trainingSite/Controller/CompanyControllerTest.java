package com.memoire.trainingSite.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memoire.trainingSite.DTO.CompanyDTO;
import com.memoire.trainingSite.DTO.CompanyResponseDTO;
import com.memoire.trainingSite.Services.CompanyService;
import com.memoire.trainingSite.models.CompanyProfile;
import com.memoire.trainingSite.models.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CompanyService companyService;
    @Autowired
    ObjectMapper objectMapper; //used to convert objects to Json

    @Test
    void test_getCompanies() throws Exception {

        //given
        CompanyResponseDTO companyResponseDTO_1 = new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0555657585","h.h@gmail.com",List.of(),
                "H&H Company", new CompanyProfile(), List.of());


        CompanyResponseDTO companyResponseDTO_2 = new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2024,2,10, 1,15),
                UserStatus.ACTIVE, "0982883762","dd.dd@gmail.com",List.of(),
                "DD Company", new CompanyProfile(), List.of());

        when(companyService.getCompanies()).thenReturn(List.of(companyResponseDTO_1,companyResponseDTO_2));

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/companies"));
        //then
        verify(companyService).getCompanies();
        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(companyResponseDTO_1,companyResponseDTO_2))));
    }

    @Test
    void test_createCompany() throws Exception {
        //given
        CompanyDTO companyDTO = new CompanyDTO(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company", null, List.of());
        String companyJSON = objectMapper.writeValueAsString(companyDTO);

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company", new CompanyProfile(), List.of());

        //when
        when(companyService.createCompany(companyDTO)).thenReturn(companyResponseDTO);
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/companies")// Perform the POST request and expect status isCreated
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(companyJSON));

        //then
        verify(companyService).createCompany(companyDTO);
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("H&H"))
                .andExpect(jsonPath("$.email").value("h.h@gmail.com"))
                .andExpect(jsonPath("$.companyName").value("HH Company"));
    }

    @Test
    void test_getCompanyById_if_exists() throws Exception {
        //given
        Long companyId = 1L;

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(1L,"H&H",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company", new CompanyProfile(), List.of());
        //when
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.of(companyResponseDTO));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/companies/1"));
        //then
        verify(companyService).getCompanyById(companyId);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value(1L));
    }

    @Test
    void test_getCompanyById_if_does_not_exists() throws Exception {
        //given
        Long companyId = 1L;

        //when
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/companies/1"));
        //then
        verify(companyService).getCompanyById(companyId);
        result.andExpect(status().isNotFound());
    }

    @Test
    void test_updateCompany_if_exists() throws Exception {
        //given
        Long company_id = 1L;
        CompanyDTO companyDTO = new CompanyDTO(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company",new CompanyProfile(), List.of());
        String companyDTOJson = objectMapper.writeValueAsString(companyDTO);


        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(null,"H&H",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company", new CompanyProfile(), List.of());
        //when
        when(companyService.updateCompany(company_id, companyDTO)).thenReturn(Optional.of(companyResponseDTO));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/companies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyDTOJson));
        //then
        verify(companyService).updateCompany(company_id,companyDTO);
        result.andExpect(status().isOk());
    }


    @Test
    void test_updateCompany_if_does_not_exist() throws Exception {

        //given
        Long company_id = 1L;
        CompanyDTO companyDTO = new CompanyDTO(null,"H&H","password",
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company",new CompanyProfile(), List.of());
        String companyDTOJson = objectMapper.writeValueAsString(companyDTO);

        //when
        when(companyService.updateCompany(company_id, companyDTO)).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/companies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyDTOJson));
        //then
        verify(companyService).updateCompany(company_id,companyDTO);
        result.andExpect(status().isNotFound());
    }

    @Test
    void test_getApplicantByUserName_if_exists() throws Exception {
        //given
        String username = "HH";//%26 is the code of &, we use %26 because & is a reserved caracter used to separate parameters in a url

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(null,username,
                LocalDateTime.of(2020,2,10, 1,15),
                UserStatus.ACTIVE, "0765245636","h.h@gmail.com",List.of(),
                "HH Company", new CompanyProfile(), List.of());
        //when
        when(companyService.getCompanyByUsername(username)).thenReturn(Optional.of(companyResponseDTO));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/companies/search?username="+username));
        //then
        verify(companyService).getCompanyByUsername(username);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username));
    }

    @Test
    void test_getCompanyByUserName_if_does_not_exist() throws Exception {
        //given
        String username = "HH";
        //when
        when(companyService.getCompanyByUsername(username)).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/companies/search?username="+username));
        //then
        verify(companyService).getCompanyByUsername(username);
        result.andExpect(status().isNotFound());
    }

    @Test
    void test_deleteCompany() throws Exception {
        //given
        Long company_id = 1L;

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/v1/companies/"+company_id));

        //then
        verify(companyService).deleteCompany(company_id);
        result.andExpect(status().isNoContent());
    }

}