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
