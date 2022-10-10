package com.andrekreou.iot.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Testing the Login view of the application")
    public void testLoginPage() throws Exception{
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @DisplayName("Testing the Register view of the application")
    public void testRegisterPage() throws Exception{
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    @DisplayName("Testing the Registration Complete view of the application")
    public void testRegisterCompletePage() throws Exception{
        mockMvc.perform(get("/registration-complete"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration-complete"));
    }

    @Test
    @DisplayName("Testing the Verification Complete view of the application")
    public void testVerificationCompletePage() throws Exception{
        mockMvc.perform(get("/verification-complete"))
                .andExpect(status().isOk())
                .andExpect(view().name("verification-complete"));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    @DisplayName("Testing the Welcome view of the application")
    public void testWelcomePage() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    @DisplayName("Testing the News view of the application")
    public void testNewsPage() throws Exception{
        mockMvc.perform(get("/show-news-contents"))
                .andExpect(status().isOk())
                .andExpect(view().name("news-db-contents"));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    @DisplayName("Testing the Error URL from Spring Security")
    public void testErrorPage() throws Exception{
        mockMvc.perform(get("/login-error"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    @DisplayName("Testing the Movies view of the application")
    public void testMoviesPage() throws Exception{
        mockMvc.perform(get("/show-movies-contents"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies-db-contents"));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    @DisplayName("Testing the Logout view of the application")
    public void testLogoutPage() throws Exception{
        mockMvc.perform(logout("/logout"));
    }
}
