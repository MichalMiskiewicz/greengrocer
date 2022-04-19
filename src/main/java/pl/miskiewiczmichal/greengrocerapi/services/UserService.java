package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddUserDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserTypesDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Address;
import pl.miskiewiczmichal.greengrocerapi.entities.User;
import pl.miskiewiczmichal.greengrocerapi.entities.UserType;
import pl.miskiewiczmichal.greengrocerapi.mappers.UserMapper;
import pl.miskiewiczmichal.greengrocerapi.mappers.UserTypesMapper;
import pl.miskiewiczmichal.greengrocerapi.repositories.AddressRepository;
import pl.miskiewiczmichal.greengrocerapi.repositories.UserRepository;
import pl.miskiewiczmichal.greengrocerapi.repositories.UserTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserTypesMapper userTypesMapper;

    public List<UserDTO> getAllDrivers(){
       List<User> users = userRepository.getDrivers();

        return users.stream().map(userMapper::mapUserToUserDTO).collect(Collectors.toList());
    }

    public UserDTO addNewUser(AddUserDTO userDTO){

        Address address = getAddress(userDTO.address);
        Optional<UserType> optionalUserType = userTypeRepository.getAllByName(userDTO.userType.getName());

        User user = User.builder().username(userDTO.username)
                .name(userDTO.name)
                .surname(userDTO.surname)
                .emailAddress(userDTO.eMail)
                .telNumber(userDTO.telNumber)
                .address(address)
                .userType(optionalUserType.get())
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

    public List<UserTypesDTO> getUsersTypes(){
        List<UserType> types = userTypeRepository.findAll();

        return types.stream().map(userTypesMapper::mapUserTypeToUserTypesDTO).collect(Collectors.toList());
    }


}
