package ru.geekbrains.programworld.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.programworld.api.dtos.EditProfileDTO;
import ru.geekbrains.programworld.api.dtos.UserDTO;
import ru.geekbrains.programworld.auth.exceptions.DataValidationException;
import ru.geekbrains.programworld.auth.model.User;
import ru.geekbrains.programworld.auth.services.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping()
    public UserDTO newUser(@RequestBody @Validated UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        User user = userService.registerNewUserAccount(userDTO);
        return new UserDTO(user.getId(), user.getPassword(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    @PutMapping()
    public UserDTO editUser(@RequestBody @Validated EditProfileDTO editProfileDTO, BindingResult bindingResult, @RequestHeader String username) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        User user = userService.editUserAccount(editProfileDTO, username);
        return new UserDTO(user.getId(), user.getPassword(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName());
    }
}
