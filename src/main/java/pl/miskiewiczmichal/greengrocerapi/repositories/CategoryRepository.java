package pl.miskiewiczmichal.greengrocerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.miskiewiczmichal.greengrocerapi.entities.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> getCategoryByName(String name);

}
