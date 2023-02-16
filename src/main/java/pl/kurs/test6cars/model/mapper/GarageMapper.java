package pl.kurs.test6cars.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import pl.kurs.test6cars.model.Car;
import pl.kurs.test6cars.model.Garage;
import pl.kurs.test6cars.model.commands.CreateCarCommand;
import pl.kurs.test6cars.model.commands.CreateGarageCommand;
import pl.kurs.test6cars.model.dto.CarDto;
import pl.kurs.test6cars.model.dto.GarageDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GarageMapper {

    Garage mapFromCreateGarageCommandToGarage(CreateGarageCommand source);
    GarageDto mapFromGarageToGarageDto(Garage source);

}
