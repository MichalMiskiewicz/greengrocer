package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.User;
import pl.miskiewiczmichal.greengrocerapi.entities.UserType;
import pl.miskiewiczmichal.greengrocerapi.mappers.UserMapper;
import pl.miskiewiczmichal.greengrocerapi.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllDrivers(){
      // List<User> users = userRepository.findAll();
       List<User> users = userRepository.getDrivers();

        return users.stream().map(userMapper::mapUserToUserDTO).collect(Collectors.toList());
    }
}
