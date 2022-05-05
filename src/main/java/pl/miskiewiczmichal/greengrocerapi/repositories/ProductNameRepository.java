package pl.miskiewiczmichal.greengrocerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.miskiewiczmichal.greengrocerapi.entities.ProductName;

import java.util.List;
import java.util.UUID;

public interface ProductNameRepository extends JpaRepository<ProductName, UUID> {

    List<ProductName> getAllByCategory_Id(UUID uuid);
}
