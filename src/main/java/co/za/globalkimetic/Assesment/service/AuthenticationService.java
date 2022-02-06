package co.za.globalkimetic.Assesment.service;

import co.za.globalkimetic.Assesment.config.JwtUtil;
import co.za.globalkimetic.Assesment.domain.InvalidToken;
import co.za.globalkimetic.Assesment.dto.LoginDTO;
import co.za.globalkimetic.Assesment.dto.LoginResponseDTO;
import co.za.globalkimetic.Assesment.repository.InvalidTokenRepository;
import co.za.globalkimetic.Assesment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InvalidTokenRepository invalidTokenRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Transactional
    public void logout(String token){
        //add loggedout token into invalid token table
        if (invalidTokenRepository.existsByToken(token) ){
            throw new RuntimeException("already logged out");
        } else {
            InvalidToken invalidToken = new InvalidToken();
            invalidToken.setToken(token);
            invalidTokenRepository.save(invalidToken);
        }
    }

    public LoginResponseDTO login(LoginDTO loginDTO){
        //login with username and password
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //create login response on succesfull authentication
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setId(userRepository.findUserByUsername(loginDTO.getUsername()).getId());
        loginResponseDTO.setToken(jwtUtil.createToken(loginDTO.getUsername()));
        return loginResponseDTO;
    }
}
