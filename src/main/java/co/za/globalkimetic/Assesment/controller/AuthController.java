package co.za.globalkimetic.Assesment.controller;

import co.za.globalkimetic.Assesment.dto.LoginDTO;
import co.za.globalkimetic.Assesment.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    //logout  controller user using token
    @PostMapping(path = "/logout/{token}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void logout(@PathVariable String token){
        authenticationService.logout(token);
        ResponseEntity.ok();
    }

    //login controller with password and username
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        ResponseEntity.ok();
            return ResponseEntity.ok(authenticationService.login(loginDTO));
    }

}
