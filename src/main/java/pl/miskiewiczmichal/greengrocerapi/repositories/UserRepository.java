package pl.miskiewiczmichal.greengrocerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.miskiewiczmichal.greengrocerapi.entities.Product;
import pl.miskiewiczmichal.greengrocerapi.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT * FROM user_tb u WHERE u.user_type = 'Kierowca';"
            , nativeQuery = true)
    List<User> getDrivers();

    User getByUsername(String username);
}
