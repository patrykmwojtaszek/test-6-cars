package pl.kurs.test6cars.exceptions.model;


public class WrongIdException extends RuntimeException{
    public WrongIdException(String message) {
        super(message);
    }
}
