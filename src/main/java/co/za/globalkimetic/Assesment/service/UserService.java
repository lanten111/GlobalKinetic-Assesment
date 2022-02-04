package co.za.globalkimetic.Assesment.service;

import co.za.globalkimetic.Assesment.domain.User;
import co.za.globalkimetic.Assesment.dto.UserDTO;
import co.za.globalkimetic.Assesment.dto.UserResponseDTO;
import co.za.globalkimetic.Assesment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void createUser(UserDTO userDTO){
        //create user

        //check if user with same user ame exists
        User existingUser = userRepository.findUserByUserName(userDTO.getUserName());
        if (existingUser != null){
            throw new RuntimeException("User "+userDTO.getUserName()+ " Already exist, Choose another name");
        }else {
            //if user with same username does not exist the continue to create one
            userRepository.save(transferUserData(userDTO));
        }
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
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    private UserResponseDTO transferUserData(User user){
        //transfer user details from transfer object to user entity
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserName(user.getUserName());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        return userResponseDTO;
    }
}
