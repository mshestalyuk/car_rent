package com.example.carrent.service;

import com.example.carrent.dto.UserDTO;
import com.example.carrent.model.User;
import com.example.carrent.repository.RoleRepository;
import com.example.carrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        if (userDTO.getId_role() == null) {
            userDTO.setId_role(1L); 
        }
        String encryptedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);

        User user = convertToEntity(userDTO);
        if (user.getRole() == null) {
            throw new IllegalArgumentException("Role not found for ID: " + userDTO.getId_role());
        }

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
            .map(user -> {
                user.setEmail(userDTO.getEmail());
                if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                    user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
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
        userDTO.setPassword(user.getPassword());
        userDTO.setId_role(user.getRole().getId());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        roleRepository.findById(userDTO.getId_role()).ifPresent(user::setRole);
        return user;
    }
}
