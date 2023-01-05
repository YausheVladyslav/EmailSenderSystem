package com.example.emailsender.services;

import com.example.emailsender.entities.UserEntity;
import com.example.emailsender.exceptions.RequestException;
import com.example.emailsender.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private static final int PAGE_SIZE = 5;

    public void saveUser(String username, String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setEmail(email);
            userRepository.save(user);
        } else {
            throw new RequestException("This email is already taken");
        }
    }

    public void editUser(long userId, String username, String email) {
        Optional<UserEntity> userById = userRepository.findById(userId);
        if (userById.isPresent()) {
            UserEntity user  = userById.get();
            user.setUsername(username);
            user.setEmail(email);
            userRepository.save(user);
        } else {
            throw new RequestException("User by id " + userId + " not found");
        }
    }

    public void deleteUser(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        user.ifPresent(userRepository::delete);
    }

    public UserEntity findUser(String email) {
        Optional<UserEntity> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent()) {
            return userByEmail.get();
        } else {
            throw new RequestException("User with such email not found");
        }
    }

    public Page<UserEntity> allUsers(int pageNumber) {
        Pageable paging = PageRequest.of(
                pageNumber,
                PAGE_SIZE,
                Sort.by("createdOn").descending());
        return userRepository.findAll(paging);
    }
}
