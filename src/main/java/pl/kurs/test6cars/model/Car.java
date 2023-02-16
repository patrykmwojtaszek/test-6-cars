package pl.kurs.test6cars.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producer;

    private String model;

    private int power;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @ManyToOne
    private Garage garage;

}
