package pl.kurs.test6cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.test6cars.model.Garage;

public interface GarageRepository extends JpaRepository<Garage, Long> {
}
