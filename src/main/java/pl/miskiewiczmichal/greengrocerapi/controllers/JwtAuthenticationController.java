package pl.miskiewiczmichal.greengrocerapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.miskiewiczmichal.greengrocerapi.DTOs.UserAuthDTO;
import pl.miskiewiczmichal.greengrocerapi.configuration.JwtTokenUtil;
import pl.miskiewiczmichal.greengrocerapi.entities.User;
import pl.miskiewiczmichal.greengrocerapi.jwt.JwtRequest;
import pl.miskiewiczmichal.greengrocerapi.mappers.UserMapper;
import pl.miskiewiczmichal.greengrocerapi.repositories.UserRepository;
import pl.miskiewiczmichal.greengrocerapi.services.JwtUserDetailsService;

@AllArgsConstructor
@CrossOrigin
@RestController
public class JwtAuthenticationController {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());


        User user = userRepository.getByUsername(userDetails.getUsername()).get();
        final String token = jwtTokenUtil.generateToken(userDetails);
        UserAuthDTO userAuthDTO = userMapper.userDetailsToUserAuthDTO(user.getId(), user.getUserType(), user.getUsername(), token);
        return ResponseEntity.ok(userAuthDTO);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}