package pl.kurs.test6cars.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GarageDto {

    private Long id;
    private String address;
    private int capacity;
    private boolean LpgAllowed;

}
