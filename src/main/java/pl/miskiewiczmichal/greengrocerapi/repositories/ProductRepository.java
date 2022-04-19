package pl.miskiewiczmichal.greengrocerapi.repositories;


import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.miskiewiczmichal.greengrocerapi.entities.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> getAllByCategory(String category);
}
