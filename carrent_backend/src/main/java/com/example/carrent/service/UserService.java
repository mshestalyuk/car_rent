package com.example.carrent.service;

import com.example.carrent.dto.UserDTO;
import com.example.carrent.model.User;
import com.example.carrent.model.Role;
import com.example.carrent.repository.UserRepository;
import com.example.carrent.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        if (user.getRole() == null) {
            roleRepository.findById(1L).ifPresent(user::setRole);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); // Encrypt password
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        if (id == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        return userRepository.findById(id)
            .map(user -> {
                user.setEmail(userDTO.getEmail());
                if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                    user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
                }
                // Only update the role if a new roleId is provided
                if (userDTO.getRoleId() != null) {
                    roleRepository.findById(userDTO.getRoleId())
                        .ifPresent(user::setRole);
                }
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
        userDTO.setPassword(null);
        userDTO.setRoleId(user.getRole() != null ? user.getRole().getIdRole() : null);
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId()); 
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); 
        Optional<Role> role = roleRepository.findById(Optional.ofNullable(userDTO.getRoleId()).orElse(1L));
        role.ifPresent(user::setRole);
        return user;
    }
}
