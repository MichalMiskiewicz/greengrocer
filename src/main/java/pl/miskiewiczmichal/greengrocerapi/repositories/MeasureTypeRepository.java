package pl.miskiewiczmichal.greengrocerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.miskiewiczmichal.greengrocerapi.entities.MeasureType;

import java.util.UUID;

public interface MeasureTypeRepository extends JpaRepository<MeasureType, UUID> {

    MeasureType getByName(String name);
}
