package co.za.globalkimetic.Assesment.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.expirationTime}")
    String expirationTime;

    @Value("${jwt.headerString}")
    String header;

    @Value("${jwt.prefix}")
    String prefix;


    public String createToken(String username){
        return  JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 100000L ))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public Claims getClaims(String token){
        String token1 = token.replace(prefix, "");
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token1).getBody();
    }


    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }

    public boolean isValidToken(String token,String username) {
        String tokenUserName=getSubject(token);
        return (username.equals(tokenUserName) && !isTokenExpired(token));
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }
}
