package com.application.usermanagement;

import com.application.usermanagement.controller.UserController;
import com.application.usermanagement.dto.DepartmentDTO;
import com.application.usermanagement.dto.UserDTO;
import com.application.usermanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserControllerTest {

    static String API_URL = "/api/users";

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;
    private UserDTO userUpdateDTO;
    private DepartmentDTO departmentDTO;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Financeiro");

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Jose Francisco");
        userDTO.setEmail("jose@francisco.com");
        userDTO.setDepartment(departmentDTO);

        userUpdateDTO = new UserDTO();
        userUpdateDTO.setId(1L);
        userUpdateDTO.setName("Eduardo Almeida");
        userUpdateDTO.setEmail("eduardo@almeida.com");
        userUpdateDTO.setDepartment(departmentDTO);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<UserDTO> users = Collections.singletonList(userDTO);
        when(userService.getAllUsers()).thenReturn(users);

       mockMvc.perform(get(API_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(userDTO.getName()))
                .andExpect(jsonPath("$[0].email").value(userDTO.getEmail()))
                .andExpect(jsonPath("$[0].department.id").value(departmentDTO.getId()))
                .andExpect(jsonPath("$[0].department.name").value(departmentDTO.getName()));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(userDTO);

        mockMvc.perform(get(API_URL.concat("/1")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(userDTO.getName()))
                .andExpect(jsonPath("email").value(userDTO.getEmail()))
                .andExpect(jsonPath("department.id").value(departmentDTO.getId()))
                .andExpect(jsonPath("department.name").value(departmentDTO.getName()));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        String userDTOJsonString = new ObjectMapper().writeValueAsString(userDTO);

        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDTOJsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(userDTO.getName()))
                .andExpect(jsonPath("email").value(userDTO.getEmail()))
                .andExpect(jsonPath("department.id").value(departmentDTO.getId()))
                .andExpect(jsonPath("department.name").value(departmentDTO.getName()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userService.updateUser(anyLong(), any(UserDTO.class))).thenReturn(userUpdateDTO);

        String userDTOJsonString = new ObjectMapper().writeValueAsString(userDTO);

        mockMvc.perform(put(API_URL.concat("/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDTOJsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(userUpdateDTO.getName()))
                .andExpect(jsonPath("email").value(userUpdateDTO.getEmail()))
                .andExpect(jsonPath("department.id").value(departmentDTO.getId()))
                .andExpect(jsonPath("department.name").value(departmentDTO.getName()));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete(API_URL.concat("/1")))
                .andExpect(status().isNoContent());
    }
}
