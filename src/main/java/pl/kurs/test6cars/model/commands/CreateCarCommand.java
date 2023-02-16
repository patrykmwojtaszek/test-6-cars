package pl.kurs.test6cars.model.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kurs.test6cars.model.FuelType;
import pl.kurs.test6cars.model.Garage;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCarCommand {

    @NotEmpty
    private String producer;

    @NotEmpty
    private String model;

    @NotNull
    @Positive
    private int power;

    @NotNull
    private FuelType fuelType;

    // zakladamy, ze nie przypisujemy garazu od razu przy dodawaniu auta przez uzytkownika
//    @NotNull
//    @Positive
//    private Long garageId;

}
