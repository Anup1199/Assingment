package com.sample_project.userApp.controller;

import com.sample_project.userApp.dto.UserDto;
import com.sample_project.userApp.model.User;
import com.sample_project.userApp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockService;

    @Test
    void testShowAllUsers() throws Exception {
        // Setup
        // Configure UserService.showAll(...).
        final List<User> users = List.of(new User(0L, "Name", "Username", "Email", "task"));
        when(mockService.showAll()).thenReturn(users);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/User/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowAllUsers_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockService.showAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/User/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testUserById() throws Exception {
        // Setup
        // Configure UserService.getUserById(...).
        final User user = new User(0L, "Name", "Username", "Email", "task");
        when(mockService.getUserById(0L)).thenReturn(user);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/User/getUser/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAddUser() throws Exception {
        // Setup
        UserDto userDto = new UserDto(
                0L,
                "name",
                "userName",
                "email",
                "task"
        );
        when(mockService.addUser(any(UserDto.class))).thenReturn("expectedResponse");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/User/addUser")
                        .content("content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }


    @Test
    void testDeleteUser() throws Exception {
        // Setup
        when(mockService.deleteUser(0L)).thenReturn("body");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/User/deleteUser/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdateUser() throws Exception {
        // Setup
        // Configure UserService.updateUser(...).
        final User user = new User(
                0L,
                "Name",
                "Username",
                "Email",
                "task"
        );
        when(mockService.updateUser(
                eq(0L),
                any(UserDto.class)
        )).thenReturn(user);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/User/updateUser/{id}", 0)
                        .content("content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

}
