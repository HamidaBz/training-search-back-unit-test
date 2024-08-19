package com.memoire.trainingSite.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memoire.trainingSite.Controller.ApplicantController;
import com.memoire.trainingSite.DTO.ApplicantDTO;
import com.memoire.trainingSite.Services.ApplicantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicantController.class) // to load only the beans of web context related to ApplicantController
public class ApplicantControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicantService applicantService; // Mock the service
    @Autowired
    private ObjectMapper objectMapper; // Used to convert the object to JSON

    @Test
    public void unique_username_for_applicants() throws Exception {
        // given
        ApplicantDTO applicant1 = new ApplicantDTO();
        applicant1.setUsername("user1");
        String applicant1Json = objectMapper.writeValueAsString(applicant1);// Convert the object to JSON


        mockMvc.perform(post("/v1/applicants")// Perform the POST request and expect status isCreated
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(applicant1Json));
                //.andExpect(status().isCreated());

        // Create another applicant with the same username
        ApplicantDTO applicant2 = new ApplicantDTO();
        applicant2.setUsername("user1"); // Duplicate username
        String applicant2Json = objectMapper.writeValueAsString(applicant2);

        // Perform the POST request and expect status conflict
        ResultActions result = mockMvc.perform(post("/v1/applicants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(applicant2Json));

        // Expect a conflict status (409)
        result.andExpect(status().isFound());
    }
}
