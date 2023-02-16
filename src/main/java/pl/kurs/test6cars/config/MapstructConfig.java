package pl.kurs.test6cars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kurs.test6cars.model.mapper.CarMapper;
import pl.kurs.test6cars.model.mapper.CarMapperImpl;
import pl.kurs.test6cars.model.mapper.GarageMapper;
import pl.kurs.test6cars.model.mapper.GarageMapperImpl;

@Configuration
public class MapstructConfig {

    @Bean
    public CarMapper carMapper() {
        return new CarMapperImpl();
    }

    @Bean
    public GarageMapper garageMapper() {
        return new GarageMapperImpl();
    }

}
