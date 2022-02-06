package co.za.globalkimetic.Assesment.service;

import co.za.globalkimetic.Assesment.domain.User;
import co.za.globalkimetic.Assesment.dto.UserDTO;
import co.za.globalkimetic.Assesment.dto.UserResponseDTO;
import co.za.globalkimetic.Assesment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public String createUser(UserDTO userDTO){
        //create user

        //check if user with same user ame exists
        if (userRepository.existsByUsername(userDTO.getUsername())){
            throw new ValidationException("User "+userDTO.getUsername()+ " already exist, Choose another name");
        }else {
            //if user with same username does not exist to continue to create one
            userRepository.save(transferUserData(userDTO));
        }
        return "user "+userDTO.getUsername() + " created successfully";
    }

    //get all users in a list
    public List<UserResponseDTO> getUsers(){

        List<User> userList = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOList  = new ArrayList<>();

        for (User user: userList){
            userResponseDTOList.add(transferUserData(user))    ;
        }

        return userResponseDTOList;
    }

    private User transferUserData(UserDTO userDTO){
        //transfer user details from transfer object to user entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        //validate password before encryption
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()){
            throw new ValidationException("Password cannot be empty");
        }
        //encrypt password
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    private UserResponseDTO transferUserData(User user){
        //transfer user entity from transfer object to user response object
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(user.getId());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        return userResponseDTO;
    }

}
