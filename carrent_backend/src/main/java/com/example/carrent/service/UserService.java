package com.example.carrent.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import com.example.carrent.dto.UserDTO;

@Service
public class UserService {
    private final ConcurrentHashMap<Long, UserDTO> users = new ConcurrentHashMap<>();
    private long userIdSequence = 1L;  // Example user ID sequence generator

    public List<UserDTO> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    public UserDTO findUserById(Long id) {
        return users.get(id);
    }

    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setId(userIdSequence++);
        users.put(userDTO.getId(), userDTO);
        return userDTO;
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        if (users.containsKey(id)) {
            userDTO.setId(id);
            users.put(id, userDTO);
            return userDTO;
        }
        return null;  // Or throw an exception if preferred
    }

    public void deleteUser(Long id) {
        users.remove(id);
    }
}
