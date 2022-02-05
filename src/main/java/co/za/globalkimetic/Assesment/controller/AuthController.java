package co.za.globalkimetic.Assesment.controller;

import co.za.globalkimetic.Assesment.dto.LogOutDTO;
import co.za.globalkimetic.Assesment.dto.LoginDTO;
import co.za.globalkimetic.Assesment.dto.LoginResponseDTO;
import co.za.globalkimetic.Assesment.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(path = "/logout")
    public void login(@RequestBody LogOutDTO logOutDTO){
        authenticationService.logout(logOutDTO.getToken());
    }
}
