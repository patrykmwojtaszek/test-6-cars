package pl.kurs.test6cars.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private int capacity;

    private boolean isLpgAllowed;

    @OneToMany(mappedBy = "garage", fetch = FetchType.LAZY)
    private List<Car> cars = new ArrayList<>();

}
