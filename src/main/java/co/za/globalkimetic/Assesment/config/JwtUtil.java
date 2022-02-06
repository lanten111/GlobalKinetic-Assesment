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


    //generate new token
    public String createToken(String username){
        long now = System.currentTimeMillis();
        return  JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + Long.parseLong(expirationTime)))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    //get data from he token
    public Claims getClaims(String token){
        String token1 = token.replace(prefix, "");
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token1).getBody();
    }

    //check if token has expired
    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(getIssuedDate(token));
    }

    //check username in the token
    public boolean isValidToken(String token,String username) {
        String tokenUserName=getSubject(token);
        return (username.equals(tokenUserName) && !isTokenExpired(token));
    }


    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public Date getIssuedDate(String token) {
        return getClaims(token).getIssuedAt();
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }
}
