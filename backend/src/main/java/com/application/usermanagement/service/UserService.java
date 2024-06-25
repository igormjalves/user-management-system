package com.application.usermanagement.service;

import com.application.usermanagement.dto.UserDTO;
import com.application.usermanagement.exception.ResourceNotFoundException;
import com.application.usermanagement.model.User;
import com.application.usermanagement.repository.UserRepository;
import com.application.usermanagement.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Mapper mapper = new Mapper();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setDepartment(user.getDepartment());
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        userRepository.delete(user);
    }
}
