package pl.kurs.test6cars.exceptions.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoEntityException extends EntityNotFoundException {

    public NoEntityException(String message) {
        super(message);
    }
}
