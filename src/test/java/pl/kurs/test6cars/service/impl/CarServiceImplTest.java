package pl.kurs.test6cars.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.test6cars.exceptions.model.NoEntityException;
import pl.kurs.test6cars.exceptions.model.WrongIdException;
import pl.kurs.test6cars.model.Car;
import pl.kurs.test6cars.model.FuelType;
import pl.kurs.test6cars.model.Garage;
import pl.kurs.test6cars.repository.CarRepository;
import pl.kurs.test6cars.repository.GarageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private GarageRepository garageRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Captor
    private ArgumentCaptor<Car> carArgumentCaptor;

    @Test
    public void add_shouldAdd() {
        //given
        Car car = Car.builder()
                .producer("BMW")
                .model("5")
                .power(190)
                .fuelType(FuelType.ON)
                .build();
        when(carRepository.save(any(Car.class))).thenReturn(car);

        //when
        Car carResult = carService.add(car);

        //then
        assertEquals(car, carResult);

        verify(carRepository).save(carArgumentCaptor.capture());
        Car saved = carArgumentCaptor.getValue();
        assertEquals(car, saved);
    }

    @Test
    public void add_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        String exceptionMessage = "No entity to add!";

        NoEntityException e = Assertions.assertThrows(NoEntityException.class, () -> carService.add(null));
        assertEquals(exceptionMessage, e.getMessage());

        verifyNoInteractions(carRepository);
    }

    @Test
    public void addCarToGarage_shouldAdd() {
        //given
        Car car = Car.builder()
                .producer("BMW")
                .model("5")
                .power(190)
                .fuelType(FuelType.ON)
                .build();

        Garage garage = Garage.builder()
                .id(1L)
                .address("Warszawa")
                .capacity(10)
                .isLpgAllowed(false)
                .cars(List.of())
                .build();

//        when(garage.getCars()).thenReturn(new ArrayList<>());

        when(garageRepository.save(any(Garage.class))).thenReturn(garage);
        when(carRepository.save(any(Car.class))).thenReturn(car);

        //when
//        car.setGarage(garage);
        Car carResult = carService.addCarToGarage(car, garage);
        car.setGarage(garage);

        //then
        assertEquals(car, carResult);

        verify(carRepository).save(carArgumentCaptor.capture());
        Car saved = carArgumentCaptor.getValue();
        assertEquals(car, saved);
    }

    @Test
    public void addCarToGarage_shouldThrowIllegalArgumentExceptionWhenCarIsParkedAlready() {
        String exceptionMessage = "This car is parked in another garage!";

        Garage garage = Garage.builder()
                .id(2L)
                .address("Warszawa")
                .capacity(20)
                .isLpgAllowed(false)
                .build();

        Car carForPark = Car.builder()
                .id(5L)
                .producer("BMW")
                .model("5")
                .power(190)
                .fuelType(FuelType.ON)
                .garage(garage)
                .build();

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> carService.addCarToGarage(carForPark, garage));
        assertEquals(exceptionMessage, e.getMessage());

        verifyNoInteractions(carRepository);
    }

    @Test
    public void addCarToGarage_shouldThrowIllegalArgumentExceptionWhenThereIsNoSpace() {
        String exceptionMessage = "This garage does not have enough space for add this car";

        Car car = Car.builder()
                .producer("BMW")
                .model("5")
                .power(190)
                .fuelType(FuelType.ON)
                .build();

        Garage garage = Garage.builder()
                .id(1L)
                .address("Warszawa")
                .capacity(0)
                .isLpgAllowed(false)
                .build();

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> carService.addCarToGarage(car, garage));
        assertEquals(exceptionMessage, e.getMessage());

        verifyNoInteractions(carRepository);
    }

    @Test
    public void addCarToGarage_shouldThrowIllegalArgumentExceptionWhenLPGIsNotAllowedInGarage() {
        String exceptionMessage = "This garage is not accept cars LPG";

        Car car = Car.builder()
                .producer("BMW")
                .model("5")
                .power(190)
                .fuelType(FuelType.LPG)
                .build();

        Garage garage = Garage.builder()
                .id(1L)
                .address("Warszawa")
                .capacity(10)
                .isLpgAllowed(false)
                .build();

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> carService.addCarToGarage(car, garage));
        assertEquals(exceptionMessage, e.getMessage());

        verifyNoInteractions(carRepository);
    }

    @Test
    public void getCarById_shouldGetCarById() {
        //given
        Car car = Car.builder()
                .id(1L)
                .producer("BMW")
                .model("5")
                .power(190)
                .fuelType(FuelType.ON)
                .build();
        when(carRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(car));

        //when
        Car carResult = carService.getCarById(1L);

        //then
        assertEquals(car, carResult);
    }

    @Test
    public void getCarById_shouldThrowWrongIdExceptionWhenEntityIsNull() {
        String exceptionMessage = "Id should be not null";

        WrongIdException e = Assertions.assertThrows(WrongIdException.class, () -> carService.getCarById(null));
        assertEquals(exceptionMessage, e.getMessage());

        verifyNoInteractions(carRepository);
    }

    @Test
    public void getCarById_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        Long id = 1L;
        String exceptionMessage = "Entity with id " + id + " do not exists";

        when(carRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        NoEntityException e = Assertions.assertThrows(NoEntityException.class, () -> carService.getCarById(id));
        assertEquals(exceptionMessage, e.getMessage());

        verify(carRepository).findById(id);
    }

    @Test
    public void deleteCarFromGarage_shouldDelete() {
        //given
        Car car = Car.builder()
                .producer("AUDI")
                .model("A6")
                .power(245)
                .fuelType(FuelType.PB95)
                .build();

        Garage garage = Garage.builder()
                .id(1L)
                .address("Krakow")
                .capacity(10)
                .isLpgAllowed(false)
                .cars(List.of(car))
                .build();

        when(garageRepository.save(any(Garage.class))).thenReturn(garage);
        when(carRepository.save(any(Car.class))).thenReturn(car);

        //when
        car.setGarage(garage);
        Car carResult = carService.deleteCarFromGarage(car);

        //then
        assertEquals(car, carResult);

        verify(carRepository).save(carArgumentCaptor.capture());
        Car saved = carArgumentCaptor.getValue();
        assertEquals(car, saved);
    }

    @Test
    public void deleteCarFromGarage_shouldThrowIllegalArgumentExceptionWhenCarIsNotParkedAlready() {
        String exceptionMessage = "This car is not added to any garage";

        Car carForPark = Car.builder()
                .id(5L)
                .producer("BMW")
                .model("5")
                .power(190)
                .fuelType(FuelType.ON)
                .garage(null)
                .build();

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> carService.deleteCarFromGarage(carForPark));
        assertEquals(exceptionMessage, e.getMessage());

        verifyNoInteractions(carRepository);
    }

}