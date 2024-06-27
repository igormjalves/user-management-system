package com.application.usermanagement.controller;

import com.application.usermanagement.dto.DepartmentDTO;
import com.application.usermanagement.dto.UserDTO;
import com.application.usermanagement.repository.DepartmentRepository;
import com.application.usermanagement.service.UserService;
import com.application.usermanagement.util.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final DepartmentRepository departmentRepository;

    public UserController(UserService userService, DepartmentRepository departmentRepository) {
        this.userService = userService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        Mapper mapper = new Mapper();
        return ResponseEntity.ok(departmentRepository
                .findAll()
                .stream()
                .map(department -> mapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList()));
    }
}
