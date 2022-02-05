package co.za.globalkimetic.Assesment.config;

import co.za.globalkimetic.Assesment.dto.LoginDTO;
import co.za.globalkimetic.Assesment.dto.LoginResponseDTO;
import co.za.globalkimetic.Assesment.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.secret }")
    private String secret;

    @Value("${jwt.expirationTime }")
    private String expirationTime;

    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/user/login");
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            LoginDTO loginDTO = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(secret.getBytes()));

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        co.za.globalkimetic.Assesment.domain.User user = userRepository.findUserByUserName(((User)auth.getPrincipal()).getUsername());
        loginResponseDTO.setId(user.getId());
        loginResponseDTO.setToken(token);

        res.getWriter().write(new Gson().toJson(loginResponseDTO));
        res.getWriter().flush();
    }

}
