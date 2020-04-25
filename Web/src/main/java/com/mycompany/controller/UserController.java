package com.mycompany.controller;

import com.mycompany.dto.UserDTO;
import com.mycompany.modelo.User;
import com.mycompany.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @PostMapping(value="/addUser")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO) {
        String response = userService.addUser(userDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping(value="/updateUser")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {
        String response = userService.updateUser(userDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping(value="/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        String response = userService.deleteUser(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
