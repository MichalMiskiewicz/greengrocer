package pl.miskiewiczmichal.greengrocerapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddUserDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserDTO;
import pl.miskiewiczmichal.greengrocerapi.services.UserService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/users")
@RestController
public class UsersController {
    private final UserService userService;

    @GetMapping("/drivers/all")
    public ResponseEntity<List<UserDTO>> getDriversList(){
        return ResponseEntity.ok().body(userService.getAllDrivers());
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody AddUserDTO addUserDTO) throws DataIntegrityViolationException {
        return ResponseEntity.ok().body(userService.addNewUser(addUserDTO));
    }

}
