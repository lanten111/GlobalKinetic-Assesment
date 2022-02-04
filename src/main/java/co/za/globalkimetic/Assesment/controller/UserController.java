package co.za.globalkimetic.Assesment.controller;

import co.za.globalkimetic.Assesment.dto.UserDTO;
import co.za.globalkimetic.Assesment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    public void login(){

    }

    @PutMapping("/users")
    public void createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
    }
}
