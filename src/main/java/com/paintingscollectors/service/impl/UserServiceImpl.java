package com.paintingscollectors.service.impl;

import com.paintingscollectors.config.LoggedUser;
import com.paintingscollectors.model.dto.UserLoginDto;
import com.paintingscollectors.model.dto.UserRegisterDto;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           LoggedUser loggedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDto data) {
        Optional<User> existingUser =
                userRepository
                        .findByUsernameOrEmail(data.getUsername(),
                                data.getEmail());

        if (existingUser.isPresent()){
            return false;
        }

        User user = new User();

        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);
        return true;
    }


    @Override
    public boolean login(UserLoginDto data) {
        Optional<User> registeredUser = userRepository
                .findByUsername(data.getUsername());

        if (registeredUser.isEmpty()){
            return false;
        }
        if (passwordEncoder.matches(data.getPassword(),registeredUser.get().getPassword())){
            return false;
        }

        loggedUser.login(registeredUser.get());

        return true;
    }

    @Override
    public Set<Painting> findFavourites(long id) {
        return   userRepository.findById(id)
                .map(User::getFavoritePaintings)
                .orElseGet(HashSet::new);
    }

    @Override
    public List<Painting> findAddedPaintings(long id) {
        return userRepository.findById(id)
                .map(User::getAddedPaintings)
                .orElseGet(ArrayList::new);
    }




}
