package pl.kurs.test6cars.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.test6cars.model.Car;
import pl.kurs.test6cars.model.Garage;
import pl.kurs.test6cars.model.commands.CreateCarCommand;
import pl.kurs.test6cars.model.commands.CreateGarageCommand;
import pl.kurs.test6cars.model.dto.CarDto;
import pl.kurs.test6cars.model.dto.GarageDto;
import pl.kurs.test6cars.model.mapper.CarMapper;
import pl.kurs.test6cars.model.mapper.GarageMapper;
import pl.kurs.test6cars.service.CarService;
import pl.kurs.test6cars.service.GarageService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/garages")
@RequiredArgsConstructor
public class GarageController {

    private final GarageService garageService;
    private final GarageMapper garageMapper;

    @PostMapping
    public ResponseEntity<GarageDto> addGarage(@RequestBody @Valid CreateGarageCommand createGarageCommand) {
        Garage garage = garageMapper.mapFromCreateGarageCommandToGarage(createGarageCommand);
        garage = garageService.add(garage);
        GarageDto garageDto = garageMapper.mapFromGarageToGarageDto(garage);
        return ResponseEntity.status(HttpStatus.CREATED).body(garageDto);
    }

}
