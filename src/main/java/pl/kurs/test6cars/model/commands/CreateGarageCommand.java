package pl.kurs.test6cars.model.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateGarageCommand {

    @NotNull
    private String address;

    @NotNull
    @Positive
    private int capacity;

    @NotNull
    private boolean isLpgAllowed;

}
