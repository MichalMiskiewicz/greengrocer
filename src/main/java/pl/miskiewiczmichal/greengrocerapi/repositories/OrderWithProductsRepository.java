package pl.miskiewiczmichal.greengrocerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.miskiewiczmichal.greengrocerapi.entities.OrderWithProducts;

import java.util.UUID;

public interface OrderWithProductsRepository extends JpaRepository<OrderWithProducts, UUID> {

}
