package pl.miskiewiczmichal.greengrocerapi.DTOs;

import pl.miskiewiczmichal.greengrocerapi.entities.Address;
import pl.miskiewiczmichal.greengrocerapi.entities.UserType;

import java.util.UUID;

public class UserDTO {
    public UUID userId;
    public String username;
    public String name;
    public String surname;
    public String eMail;
    public String telNumber;
    public Address address;
    public String userType;
}
