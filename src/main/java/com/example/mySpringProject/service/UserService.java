package com.example.mySpringProject.service;

import com.example.mySpringProject.entities.User;
import com.example.mySpringProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository.existsByDisplayName(user.getDisplayName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Display name already exists");
        }
        return userRepository.save(user);
    }

    public User getUser(String displayName) {
        return userRepository.findByDisplayName(displayName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // Atualizar usuário (não permite modificar o displayName)
    public User updateUser(String displayName, User updatedUser) {
        User existingUser = userRepository.findByDisplayName(displayName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Atualiza os campos permitidos
        existingUser.setCity(updatedUser.getCity());
        existingUser.setState(updatedUser.getState());
        existingUser.setZipcode(updatedUser.getZipcode());
        existingUser.setInterestedInPeanutAllergy(updatedUser.getInterestedInPeanutAllergy());
        existingUser.setInterestedInEggAllergy(updatedUser.getInterestedInEggAllergy());
        existingUser.setInterestedInDairyAllergy(updatedUser.getInterestedInDairyAllergy());

        return userRepository.save(existingUser);
    }
}


