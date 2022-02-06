package co.za.globalkimetic.Assesment.controller;

import co.za.globalkimetic.Assesment.dto.UserDTO;
import co.za.globalkimetic.Assesment.dto.UserResponseDTO;
import co.za.globalkimetic.Assesment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //create new user
    @PutMapping(path = "/users")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
         return ResponseEntity.ok(userService.createUser(userDTO));
    }

    //get all registered users
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers(){
       return ResponseEntity.ok(userService.getUsers());
    }

}
