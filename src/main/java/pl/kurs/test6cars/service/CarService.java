package pl.kurs.test6cars.service;

import org.springframework.data.jpa.repository.Lock;
import pl.kurs.test6cars.model.Car;
import pl.kurs.test6cars.model.Garage;

import javax.persistence.LockModeType;

public interface CarService {

    Car add(Car car);
    Car addCarToGarage(Long carId, Long garageId);
//    Car getCarById(Long id);
    Car deleteCarFromGarage(Long carId);

}
