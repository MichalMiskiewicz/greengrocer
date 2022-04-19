package pl.miskiewiczmichal.greengrocerapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserDTO;
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
}
