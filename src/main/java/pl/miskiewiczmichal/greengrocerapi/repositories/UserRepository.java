package pl.miskiewiczmichal.greengrocerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.miskiewiczmichal.greengrocerapi.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT * FROM user_tb u join user_type_tb ut on u.user_type_id = ut.id where ut.name = 'Kierowca';"
            , nativeQuery = true)
    List<User> getDrivers();
}
