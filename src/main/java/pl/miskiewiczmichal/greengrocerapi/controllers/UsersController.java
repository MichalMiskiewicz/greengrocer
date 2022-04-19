package pl.miskiewiczmichal.greengrocerapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddUserDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserTypesDTO;
import pl.miskiewiczmichal.greengrocerapi.services.UserService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/users")
@RestController
public class UsersController {
    private final UserService userService;

    @GetMapping("/drivers/all/")
    public ResponseEntity<List<UserDTO>> getDriversList(){
        return ResponseEntity.ok().body(userService.getAllDrivers());
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody AddUserDTO addUserDTO){
        return ResponseEntity.ok().body(userService.addNewUser(addUserDTO));
    }

    @GetMapping("/users-types")
    public ResponseEntity<List<UserTypesDTO>> getUserTypes(){
        return ResponseEntity.ok().body(userService.getUsersTypes());
    }
}
