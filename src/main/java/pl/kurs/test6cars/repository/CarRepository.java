package pl.kurs.test6cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import pl.kurs.test6cars.model.Car;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Car> findCarWithLockingById(Long id);

}
