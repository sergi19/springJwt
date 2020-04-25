package com.mycompany.impl;

import com.mycompany.dto.UserDTO;
import com.mycompany.modelo.User;
import com.mycompany.persistencia.UserRepository;
import com.mycompany.security.modelo.UserPrincipal;
import com.mycompany.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    public User setUserData(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder().encode(userDTO.getPassword()));
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAge(userDTO.getAge());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String addUser(UserDTO userDTO) {
        userRepository.saveAndFlush(setUserData(userDTO));
        return "Usuario registrado exitosamente";
    }

    @Override
    public String updateUser(UserDTO userDTO) {
        User user = setUserData(userDTO);
        user.setId(userDTO.getId());
        userRepository.saveAndFlush(user);
        return "Usuario editado exitosamente";
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "Usuario eliminado exitosamente";
    }
    
    @Bean
    private PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
