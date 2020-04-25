package com.mycompany.servicios;

import com.mycompany.dto.UserDTO;
import com.mycompany.modelo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> getAllUsers();
    public String addUser(UserDTO userDTO);
    public String updateUser(UserDTO userDTO);
    public String deleteUser(Long id);

}
