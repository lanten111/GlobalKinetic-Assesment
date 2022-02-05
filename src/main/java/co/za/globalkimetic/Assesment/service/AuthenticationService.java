package co.za.globalkimetic.Assesment.service;

import co.za.globalkimetic.Assesment.domain.TokenEntity;
import co.za.globalkimetic.Assesment.repository.TokenRepository;
import co.za.globalkimetic.Assesment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    public void logout(String token){
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setLoggedOut(true);
        tokenRepository.save(tokenEntity);
    }
}
