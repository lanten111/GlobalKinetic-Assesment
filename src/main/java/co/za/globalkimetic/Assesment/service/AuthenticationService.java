package co.za.globalkimetic.Assesment.service;

import co.za.globalkimetic.Assesment.config.JwtUtil;
import co.za.globalkimetic.Assesment.domain.TokenEntity;
import co.za.globalkimetic.Assesment.dto.LoginDTO;
import co.za.globalkimetic.Assesment.dto.LoginResponseDTO;
import co.za.globalkimetic.Assesment.repository.TokenRepository;
import co.za.globalkimetic.Assesment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Transactional
    public void logout(String token){
        if (tokenRepository.existsByToken(token) ){
            throw new RuntimeException("already logged out");
        } else {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setToken(token);
            tokenEntity.setLoggedOut(true);
            tokenRepository.save(tokenEntity);
        }
    }

    public LoginResponseDTO login(LoginDTO loginDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setId(userRepository.findUserByUserName(loginDTO.getUsername()).getId());
        loginResponseDTO.setToken(jwtUtil.createToken(loginDTO.getUsername()));
        return loginResponseDTO;
    }
}
