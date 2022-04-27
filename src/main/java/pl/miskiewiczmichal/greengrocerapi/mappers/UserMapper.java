package pl.miskiewiczmichal.greengrocerapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserAuthDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.User;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.username", target = "username"),
            @Mapping(source = "user.emailAddress", target = "eMail"),
            @Mapping(source = "user.password", target = "password"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.surname", target = "surname"),
            @Mapping(source = "user.telNumber", target = "telNumber"),
            @Mapping(source = "user.address", target = "address"),
            @Mapping(source = "user.userType", target = "userType")
    })
    UserDTO mapUserToUserDTO(User user);

    @Mappings({
            @Mapping(source = "uuid", target = "id"),
            @Mapping(source = "userType", target = "userType"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "token", target = "token")
    })
    UserAuthDTO userDetailsToUserAuthDTO(UUID uuid, String userType, String username,
                                                     String token);
}