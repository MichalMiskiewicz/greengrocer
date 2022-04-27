package pl.miskiewiczmichal.greengrocerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.miskiewiczmichal.greengrocerapi.entities.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> getAllByCreatedBy_IdOrderByCreationDateDesc(UUID uuid);
    List<Order> getAllByDriver_IdOrderByCreationDateDesc(UUID uuid);
}
