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
import pl.kurs.test6cars.model.Garage;
import pl.kurs.test6cars.repository.GarageRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GarageServiceImplTest {

    @Mock
    private GarageRepository garageRepository;

    @InjectMocks
    private GarageServiceImpl garageService;

    @Captor
    private ArgumentCaptor<Garage> garageArgumentCaptor;

    @Test
    public void add_shouldAdd() {
        //given

        Garage garage = Garage.builder()
                .address("Warszawa")
                .capacity(1)
                .lpgAllowed(false)
                .build();
        when(garageRepository.save(any(Garage.class))).thenReturn(garage);

        //when
        Garage garageResult = garageService.add(garage);

        //then
        assertEquals(garage, garageResult);

        verify(garageRepository).save(garageArgumentCaptor.capture());
        Garage saved = garageArgumentCaptor.getValue();
        assertEquals(garage, saved);
    }

    @Test
    public void add_shouldThrowNoEntityExceptionWhenEntityIsNull() {
        String exceptionMessage = "No entity to add!";

        NoEntityException e = Assertions.assertThrows(NoEntityException.class, () -> garageService.add(null));
        assertEquals(exceptionMessage, e.getMessage());

        verifyNoInteractions(garageRepository);
    }

//    @Test
//    public void getGarageById_shouldGetGarageById() {
//        //given
//        Garage garage = Garage.builder()
//                .id(1L)
//                .address("Warszawa")
//                .capacity(1)
//                .lpgAllowed(false)
//                .build();
//        when(garageRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(garage));
//
//        //when
//        Garage garageResult = garageService.getGarageById(1L);
//
//        //then
//        assertEquals(garage, garageResult);
//    }
//
//    @Test
//    public void getGarageById_shouldThrowWrongIdExceptionWhenEntityIsNull() {
//        String exceptionMessage = "Id should be not null";
//
//        WrongIdException e = Assertions.assertThrows(WrongIdException.class, () -> garageService.getGarageById(null));
//        assertEquals(exceptionMessage, e.getMessage());
//
//        verifyNoInteractions(garageRepository);
//    }
//
//    @Test
//    public void getGarageById_shouldThrowNoEntityExceptionWhenEntityIsNull() {
//        Long id = 1L;
//        String exceptionMessage = "Entity with id " + id + " do not exists";
//
//        when(garageRepository.findById(any(Long.class))).thenReturn(Optional.empty());
//
//        NoEntityException e = Assertions.assertThrows(NoEntityException.class, () -> garageService.getGarageById(id));
//        assertEquals(exceptionMessage, e.getMessage());
//
//        verify(garageRepository).findById(id);
//    }

}