package co.za.globalkimetic.Assesment;

import co.za.globalkimetic.Assesment.config.JwtUtil;
import co.za.globalkimetic.Assesment.domain.User;
import co.za.globalkimetic.Assesment.dto.LoginDTO;
import co.za.globalkimetic.Assesment.dto.LoginResponseDTO;
import co.za.globalkimetic.Assesment.dto.UserDTO;
import co.za.globalkimetic.Assesment.dto.UserResponseDTO;
import co.za.globalkimetic.Assesment.repository.UserRepository;
import co.za.globalkimetic.Assesment.service.AuthenticationService;
import co.za.globalkimetic.Assesment.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class GlobalKineticAssesmentApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtUtil jwtUtil;

    @Test
    void testCreateUSer(){

        //test create user
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername("batman");
        userDTO.setPassword("1234");
        userDTO.setPhoneNumber("0123456789");
        userService.createUser(userDTO);

        User user = userRepository.findUserByUsername(userDTO.getUsername());

        Assertions.assertEquals(userDTO.getUsername(), user.getUsername());
        Assertions.assertEquals(userDTO.getPhoneNumber(), user.getPhoneNumber());

        //test get users
        UserResponseDTO userList = userService.getUsers().get(0);
        Assertions.assertEquals(user.getPhoneNumber(), userList.getPhoneNumber());
        Assertions.assertEquals(user.getId(), userList.getUserId());

        //test login service
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("batman");
        loginDTO.setPassword("1234");
        LoginResponseDTO loginResponseDTO = authenticationService.login(loginDTO);

        //test token validation
        Assertions.assertFalse(jwtUtil.isTokenExpired(loginResponseDTO.getToken()));
        Assertions.assertTrue(jwtUtil.isValidToken(loginResponseDTO.getToken(), loginDTO.getUsername()));
        Assertions.assertEquals(loginDTO.getUsername(), jwtUtil.getSubject(loginResponseDTO.getToken()));

    }

}
