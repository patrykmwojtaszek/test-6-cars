package pl.kurs.test6cars.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import pl.kurs.test6cars.model.Car;
import pl.kurs.test6cars.model.commands.CreateCarCommand;
import pl.kurs.test6cars.model.dto.CarDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CarMapper {

    Car mapFromCreateCarCommandToCar(CreateCarCommand source);
    CarDto mapFromCarToCarDto(Car source);

}
