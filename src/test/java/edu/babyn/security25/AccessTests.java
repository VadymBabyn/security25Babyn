package edu.babyn.security25;

/*
@author   vadim
@project   security25
@class  AccessTests
@version  1.0.0
@since 23.04.2025 - 15.28
*/


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")

public class AccessTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    @BeforeAll
    void beforeAll()
    {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    public void whenEmptyCredsThenStatusUnauthorized() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items"))
                .andExpect(status()
                .isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthentificatedThenStatusOk() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/helloadmin"))
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenAuthentificatedUSERThenStatus403() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/helloadmin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenAuthentificatedSUPERADMINThenStatus403() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/helloadmin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthentificatedThenStatus403() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/hellouser"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenAuthentificatedUserThenStatus200() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/hellouser"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenAuthentificatedSuperThenStatus403() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/helloguest"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthentificatedAdminThenStatusisOK() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/helloguest"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenAuthentificatedUserThenStatusisOK() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/helloguest"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenCallThenStatusisOK() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/helloeveryone"))
                .andExpect(status().isOk());
    }
}
