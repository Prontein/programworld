package ru.geekbrains.programworld.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.programworld.api.dtos.EditProfileDTO;
import ru.geekbrains.programworld.api.dtos.UserDTO;
import ru.geekbrains.programworld.auth.exceptions.UserAlreadyExistException;
import ru.geekbrains.programworld.auth.model.Role;
import ru.geekbrains.programworld.auth.model.User;
import ru.geekbrains.programworld.auth.repositories.RoleRepository;
import ru.geekbrains.programworld.auth.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User registerNewUserAccount(UserDTO userDTO) {
        if (userExists(userDTO.getUsername())) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует: " + userDTO.getUsername());
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        return userRepository.save(user);
    }

    private boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Transactional
    public User editUserAccount(EditProfileDTO editProfileDTO, String username) {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));

        if (editProfileDTO.getFirstName() != null) user.setFirstName(editProfileDTO.getFirstName());
        if (editProfileDTO.getLastName() != null) user.setLastName(editProfileDTO.getLastName());
        if (editProfileDTO.getEmail() != null) user.setEmail(editProfileDTO.getEmail());

        return userRepository.save(user);
    }

}