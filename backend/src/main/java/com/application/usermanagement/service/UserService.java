package com.application.usermanagement.service;

import com.application.usermanagement.dto.UserDTO;
import com.application.usermanagement.exception.ResourceNotFoundException;
import com.application.usermanagement.model.Department;
import com.application.usermanagement.model.User;
import com.application.usermanagement.repository.DepartmentRepository;
import com.application.usermanagement.repository.UserRepository;
import com.application.usermanagement.util.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final Mapper mapper = new Mapper();

    public UserService(UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        return mapper.map(user, UserDTO.class);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        Department department;
        if(userDTO.getDepartment().getId() != null) {
            department = departmentRepository.findById(userDTO.getDepartment().getId())
                    .orElse(departmentRepository.save(user.getDepartment()));
        } else {
            department = departmentRepository.findByName(userDTO.getDepartment().getName())
                    .orElse(departmentRepository.save(user.getDepartment()));
        }
        user.setDepartment(department);
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        Department department;
        if(userDTO.getDepartment().getId() != null) {
            department = departmentRepository.findById(userDTO.getDepartment().getId())
                    .orElse(departmentRepository.save(mapper.map(userDTO, User.class).getDepartment()));
        } else {
            department = departmentRepository.findByName(userDTO.getDepartment().getName())
                    .orElse(departmentRepository.save(mapper.map(userDTO, User.class).getDepartment()));
        }
        user.setDepartment(department);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        userRepository.delete(user);
    }
}
