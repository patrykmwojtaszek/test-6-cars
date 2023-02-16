package pl.kurs.test6cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.test6cars.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
