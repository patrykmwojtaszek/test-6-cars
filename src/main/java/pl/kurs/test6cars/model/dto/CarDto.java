package pl.kurs.test6cars.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.kurs.test6cars.model.FuelType;
import pl.kurs.test6cars.model.Garage;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
@RequiredArgsConstructor
public class CarDto {

    private Long id;
    private String producer;
    private String model;
    private int power;
    private FuelType fuelType;
    private Long garageId;

}
