package co.za.globalkimetic.Assesment.controller;

import co.za.globalkimetic.Assesment.dto.UserDTO;
import co.za.globalkimetic.Assesment.dto.UserResponseDTO;
import co.za.globalkimetic.Assesment.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    public void login(){

    }

    @PutMapping("/users")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createUser(@RequestBody UserDTO userDTO){
         userService.createUser(userDTO);
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getUsers(){
       return userService.getUsers();
    }
}
