package pl.kurs.test6cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.test6cars.model.Car;
import pl.kurs.test6cars.model.Garage;
import pl.kurs.test6cars.model.commands.CreateCarCommand;
import pl.kurs.test6cars.model.dto.CarDto;
import pl.kurs.test6cars.model.mapper.CarMapper;
import pl.kurs.test6cars.service.CarService;
import pl.kurs.test6cars.service.GarageService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final GarageService garageService;
    private final CarMapper carMapper;

    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody @Valid CreateCarCommand createCarCommand) {
        Car car = carMapper.mapFromCreateCarCommandToCar(createCarCommand);
        car = carService.add(car);
        CarDto carDto = carMapper.mapFromCarToCarDto(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(carDto);
    }

    @PatchMapping (value = "/garage/{garageId}/car/{carId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CarDto> addCarToGarage(@PathVariable long carId,
                                                 @PathVariable long garageId) {
//        Car car = carService.getCarById(carId);
//        Garage garage = garageService.getGarageById(garageId);
        Car car = carService.addCarToGarage(carId, garageId);
        CarDto carDto = carMapper.mapFromCarToCarDto(car);
        return ResponseEntity.status(HttpStatus.OK).body(carDto);
    }

    @PatchMapping(value = "/delete-car-from-garage/{carId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CarDto> deleteCarFromGarage(@PathVariable(name = "carId") long carId) {
//        Car car = carService.getCarById(carId);
        Car car = carService.deleteCarFromGarage(carId);
        CarDto carDto = carMapper.mapFromCarToCarDto(car);
        return ResponseEntity.status(HttpStatus.OK).body(carDto);
    }

}
