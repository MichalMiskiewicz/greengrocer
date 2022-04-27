package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddUserDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Address;
import pl.miskiewiczmichal.greengrocerapi.entities.User;
import pl.miskiewiczmichal.greengrocerapi.mappers.UserMapper;
import pl.miskiewiczmichal.greengrocerapi.repositories.AddressRepository;
import pl.miskiewiczmichal.greengrocerapi.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<UserDTO> getAllDrivers(){
       List<User> users = userRepository.getDrivers();

        return users.stream().map(userMapper::mapUserToUserDTO).collect(Collectors.toList());
    }

    public UserDTO addNewUser(AddUserDTO addUserDTO){

        Address address = getAddress(addUserDTO.address);
        User user = User.builder().username(addUserDTO.username)
                .name(addUserDTO.name)
                .surname(addUserDTO.surname)
                .emailAddress(addUserDTO.eMail)
                .password(passwordEncoder.encode(addUserDTO.password))
                .telNumber(addUserDTO.telNumber)
                .address(address)
                .userType(addUserDTO.userType)
                .build();
        userRepository.save(user);

        return userMapper.mapUserToUserDTO(user);
    }


    private Address getAddress(Address address) {
        Address newAddress;
        Optional<Address> optionalAddress = addressRepository
                .getAddressByCityAndAndStreetAndHouseNumber(
                        address.getCity(),
                        address.getStreet(),
                        address.getHouseNumber());

        if(optionalAddress.isEmpty()){
            newAddress = Address.builder().city(address.getCity())
                    .houseNumber(address.getHouseNumber())
                    .street(address.getZipCode())
                    .zipCode(address.getZipCode())
                    .build();
            addressRepository.save(newAddress);
        } else{
            newAddress = optionalAddress.get();
        }
        return newAddress;
    }


}
