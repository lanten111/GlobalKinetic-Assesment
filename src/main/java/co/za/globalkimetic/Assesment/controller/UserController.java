package co.za.globalkimetic.Assesment.controller;

import co.za.globalkimetic.Assesment.dto.UserDTO;
import co.za.globalkimetic.Assesment.dto.UserResponseDTO;
import co.za.globalkimetic.Assesment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping(path = "/users")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createUser(@RequestBody UserDTO userDTO){
         userService.createUser(userDTO);
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponseDTO> getUsers(){
       return userService.getUsers();
    }

}
