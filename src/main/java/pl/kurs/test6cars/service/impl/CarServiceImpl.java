package pl.kurs.test6cars.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.test6cars.exceptions.model.NoEntityException;
import pl.kurs.test6cars.exceptions.model.WrongIdException;
import pl.kurs.test6cars.model.Car;
import pl.kurs.test6cars.model.FuelType;
import pl.kurs.test6cars.model.Garage;
import pl.kurs.test6cars.repository.CarRepository;
import pl.kurs.test6cars.repository.GarageRepository;
import pl.kurs.test6cars.service.CarService;
import java.util.Arrays;
import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Transactional
@Component
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final GarageRepository garageRepository;

    @Override
    public Car add(Car car) {
        if (Objects.isNull(car)) {
            throw new NoEntityException("No entity to add!");
        }
        return carRepository.save(car);
    }

    @Override
    public Car addCarToGarage(Car car, Garage garage) {

        if (!Objects.isNull(car.getGarage())) {
            throw new IllegalArgumentException("This car is parked in another garage!");
        }

        if (garage.getCapacity() < 1) {
            throw new IllegalArgumentException("This garage does not have enough space for add this car");
        }

        if (car.getFuelType() == FuelType.LPG && !garage.isLpgAllowed()) {
//            throw new IllegalArgumentException(MessageFormat
//                    .format("The garage with id {} is not accept cars LPG", garage.getId()));
            throw new IllegalArgumentException("This garage is not accept cars LPG");
        }

        List<Car> cars = new ArrayList<>(garage.getCars());
        cars.add(car);
        garage.setCars(cars);
        garage.setCapacity(garage.getCapacity()-1);
        garageRepository.save(garage);
        car.setGarage(garage);

        return carRepository.save(car);
    }

    @Override
    public Car getCarById(Long id) {
        if (Objects.isNull(id)) {
            throw new WrongIdException("Id should be not null");
        }
        return carRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Entity with id " + id + " do not exists"));
    }

    @Override
    public Car deleteCarFromGarage(Car car) {
        if (Objects.isNull(car.getGarage())) {
            throw new IllegalArgumentException("This car is not added to any garage");
        }

        Garage garageForEdit = car.getGarage();
        List<Car> cars = new ArrayList<>(garageForEdit.getCars());
        cars.remove(car);
        garageForEdit.setCapacity(garageForEdit.getCapacity()+1);
        garageRepository.save(garageForEdit);
        car.setGarage(null);
        return carRepository.save(car);
    }
}
