package me.vladislav.web_4.services;

import lombok.RequiredArgsConstructor;
import me.vladislav.web_4.dto.UserDTO;
import me.vladislav.web_4.models.User;
import me.vladislav.web_4.exceptions.UserAlreadyExistException;
import me.vladislav.web_4.exceptions.UserNotFoundException;
import me.vladislav.web_4.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerNewUserAccount(UserDTO userDTO){

        if(userRepository.findUserByLogin(userDTO.getLogin()).isPresent()){
            throw new UserAlreadyExistException("A user with login \"" + userDTO.getLogin() + "\" already exists.");
        }

        User user = new User(
                userDTO.getLogin(),
                passwordEncoder.encode(userDTO.getPassword())
        );

        userRepository.save(user);
    }

    public User getUserByLogin(String login){
        return userRepository.findUserByLogin(login).orElseThrow(() -> new UserNotFoundException("Error when getting user by login. User not found."));
    }

}
