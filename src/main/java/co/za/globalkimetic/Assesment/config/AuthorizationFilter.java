package co.za.globalkimetic.Assesment.config;

import co.za.globalkimetic.Assesment.domain.TokenEntity;
import co.za.globalkimetic.Assesment.domain.User;
import co.za.globalkimetic.Assesment.repository.TokenRepository;
import com.auth0.jwt.impl.JWTParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter.HEADER_NAME;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    TokenRepository tokenRepository;

    public AuthorizationFilter(AuthenticationManager authManager, TokenRepository tokenRepository) {
        super(authManager);
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_NAME);
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = authenticate(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
        String token = request.getHeader(HEADER_NAME);
        TokenEntity tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity.isLoggedOut()){
            throw new AuthorizationServiceException("user already logged, please login again");
        }

        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(ConstantKeys.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            User user = new User();
            user.setUserName(claims.getSubject());
            user.setPhoneNumber(claims.get("phoneNumber").toString());

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }else{
                return  null;
            }

        }
        return null;
    }
}
