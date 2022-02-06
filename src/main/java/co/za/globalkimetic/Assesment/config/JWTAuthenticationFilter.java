package co.za.globalkimetic.Assesment.config;

import co.za.globalkimetic.Assesment.domain.InvalidToken;
import co.za.globalkimetic.Assesment.repository.InvalidTokenRepository;
import co.za.globalkimetic.Assesment.service.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;

    InvalidTokenRepository invalidTokenRepository;

    JwtUtil jwtUtil;

    UserDetailsService userDetailsService;


    public JWTAuthenticationFilter(InvalidTokenRepository invalidTokenRepository, JwtUtil jwtUtil, UserDetailsService userDetailsService){
        this.jwtUtil = jwtUtil;
        this.invalidTokenRepository = invalidTokenRepository;
        this.userDetailsService = userDetailsService;
    }


    //custom jwt authentication filter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Reading Token from Authorization Header
        String token = request.getHeader("Authorization");

        //check if token is null
            if( token != null ){
                //check if token exist in loggedout tokens repository
                if (invalidTokenRepository.existsByToken(token.replace(jwtUtil.prefix+" ", ""))){
                        SecurityContextHolder.getContext().setAuthentication(null);
                } else {
                    try {
                        String username= jwtUtil.getSubject(token);
                        //if username is not null & Context Authentication must be null
                        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
                            UserDetails user= userDetailsService.loadUserByUsername(username);
                            boolean isValid=jwtUtil.isValidToken(token, user.getUsername());
                            if(isValid) {
                                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
                                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                SecurityContextHolder.getContext().setAuthentication(authToken);
                            }
                        }
                    }catch (Exception exception){
                        SecurityContextHolder.getContext().setAuthentication(null);
                    }
                }
            } else {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
            filterChain.doFilter(request, response);
    }
}
