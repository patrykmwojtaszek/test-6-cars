package pl.kurs.test6cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import pl.kurs.test6cars.model.Car;
import pl.kurs.test6cars.model.Garage;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface GarageRepository extends JpaRepository<Garage, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Garage> findGarageWithLockingById(Long id);

}
