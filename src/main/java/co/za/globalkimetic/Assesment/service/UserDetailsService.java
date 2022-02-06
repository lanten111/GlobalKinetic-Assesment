package co.za.globalkimetic.Assesment.service;

import co.za.globalkimetic.Assesment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    //load user and password from the database to compare with supplied cred
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        co.za.globalkimetic.Assesment.domain.User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user "+username+" not found");
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}
