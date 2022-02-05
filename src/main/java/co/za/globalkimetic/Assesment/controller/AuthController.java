package co.za.globalkimetic.Assesment.controller;

import co.za.globalkimetic.Assesment.dto.LogoutDTO;
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
    public void logout(@RequestBody LogoutDTO logoutDTO){
        authenticationService.logout(logoutDTO.getToken());
    }

    @PostMapping(path = "/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO){
        return authenticationService.login(loginDTO);
    }
}
