package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.miskiewiczmichal.greengrocerapi.entities.User;
import pl.miskiewiczmichal.greengrocerapi.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getByUsername(username);
        if (user.getUsername() != null) {
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority(user.getUserType()));
            return new User(user.getUsername(), user.getPassword(), list);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}