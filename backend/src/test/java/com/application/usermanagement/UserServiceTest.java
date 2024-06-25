package com.application.usermanagement;

import com.application.usermanagement.dto.DepartmentDTO;
import com.application.usermanagement.dto.UserDTO;
import com.application.usermanagement.exception.ResourceNotFoundException;
import com.application.usermanagement.model.Department;
import com.application.usermanagement.model.User;
import com.application.usermanagement.repository.DepartmentRepository;
import com.application.usermanagement.repository.UserRepository;
import com.application.usermanagement.service.UserService;
import com.application.usermanagement.util.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;
    private UserDTO userUpdateDTO;
    private Department department;
    private DepartmentDTO departmentDTO;

    @BeforeEach
    public void setUp() {
        department = new Department();
        department.setId(1L);
        department.setName("Financeiro");

        departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Financeiro");

        user = new User();
        user.setId(1L);
        user.setName("Jose Francisco");
        user.setEmail("jose@francisco.com");
        user.setDepartment(department);

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
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<UserDTO> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(userDTO.getId(), users.get(0).getId());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals(userDTO.getId(), foundUser.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(1L, userDTO));

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUser() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(userRepository.save(argThat(user ->
                user.getName().equals(userDTO.getName()) &&
                user.getEmail().equals(userDTO.getEmail()) &&
                user.getDepartment().getId().equals(department.getId()) &&
                user.getDepartment().getName().equals(departmentDTO.getName())
                ))).thenReturn(user);

        UserDTO createdUser = userService.createUser(userDTO);

        assertNotNull(createdUser);
        assertEquals(userDTO.getId(), createdUser.getId());
        verify(departmentRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO updatedUser = userService.updateUser(1L, userUpdateDTO);

        assertNotNull(updatedUser);
        assertEquals(userUpdateDTO.getId(), updatedUser.getId());
        verify(userRepository, times(1)).findById(1L);
        verify(departmentRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(1L, userDTO));

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(1L));

        verify(userRepository, times(1)).findById(1L);
    }

}
