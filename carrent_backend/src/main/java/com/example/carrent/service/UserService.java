package com.example.carrent.service;

import com.example.carrent.dto.UserDTO;
import com.example.carrent.model.User;
import com.example.carrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDto).orElse(null);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(userDTO.getEmail());
                    // Do not update the password directly in a real application
                    user.setPassword(userDTO.getPassword());
                    userRepository.save(user);
                    return convertToDto(user);
                })
                .orElse(null); 
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoleId(user.getRole().getId());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        // Do not set the password directly like this in a real application; it should be encrypted
        user.setPassword(userDTO.getPassword());
        // Set the role for the user here (you need to fetch or create the role entity)
        // user.setRole(roleRepository.findById(userDTO.getRoleId()).orElse(null));
        return user;
    }
}
