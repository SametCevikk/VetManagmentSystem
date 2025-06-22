package dev.patika.VetManagementSystem.exception;


public class AppointmentAlreadyExistsException extends RuntimeException{
    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }
}
