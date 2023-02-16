package pl.kurs.test6cars.service;

import pl.kurs.test6cars.model.Car;
import pl.kurs.test6cars.model.Garage;

public interface CarService {

    Car add(Car car);
    Car addCarToGarage(Car car, Garage garage);
    Car getCarById(Long id);
    Car deleteCarFromGarage(Car car);

}
