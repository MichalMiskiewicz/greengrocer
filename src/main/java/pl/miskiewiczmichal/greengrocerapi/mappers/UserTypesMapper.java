package pl.miskiewiczmichal.greengrocerapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserTypesDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.UserType;

@Mapper(componentModel = "spring")
public interface UserTypesMapper {

    @Mappings({
            @Mapping(source = "userType.id", target = "id"),
            @Mapping(source = "userType.name", target = "name")
    })
    UserTypesDTO mapUserTypeToUserTypesDTO(UserType userType);
}
