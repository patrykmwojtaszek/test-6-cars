package pl.kurs.test6cars.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.test6cars.exceptions.model.NoEntityException;
import pl.kurs.test6cars.exceptions.model.WrongIdException;
import pl.kurs.test6cars.model.Garage;
import pl.kurs.test6cars.repository.GarageRepository;
import pl.kurs.test6cars.service.GarageService;

import javax.transaction.Transactional;
import java.util.Objects;

@Transactional
@Component
@Service
@RequiredArgsConstructor
public class GarageServiceImpl implements GarageService {

    private final GarageRepository garageRepository;

    @Override
    public Garage add(Garage garage) {
        if (Objects.isNull(garage)) {
            throw new NoEntityException("No entity to add!");
        }
        return garageRepository.save(garage);
    }

    @Override
    public Garage getGarageById(Long id) {
        if (Objects.isNull(id)) {
            throw new WrongIdException("Id should be not null");
        }
        return garageRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Entity with id " + id + " do not exists"));
    }
}
