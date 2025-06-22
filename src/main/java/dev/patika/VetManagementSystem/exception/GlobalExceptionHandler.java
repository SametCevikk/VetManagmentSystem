package dev.patika.VetManagementSystem.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException ex,
                                                          WebRequest request) {
        ApiResponseError error = new ApiResponseError(HttpStatus.CONFLICT, ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(error, error.getStatus());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(ConflictException ex,
                                                         WebRequest request) {
        ApiResponseError error = new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", request.getDescription(false));

        return new ResponseEntity<>(error, error.getStatus());

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex,
                                                          WebRequest request) {
        ApiResponseError error = new ApiResponseError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, error.getStatus());

    }

    @ExceptionHandler(AppointmentTimeException.class)
    public ResponseEntity<Object> handleAppointmentTimeException(AppointmentTimeException ex,
                                                             WebRequest request){
        ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, error.getStatus());
    }
    @ExceptionHandler(AppointmentDateException.class)
    public ResponseEntity<Object> handleAppointmentDateException(AppointmentDateException ex,
                                                             WebRequest request){
        ApiResponseError error = new ApiResponseError(HttpStatus.CONFLICT, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, error.getStatus());
    }
    @ExceptionHandler(AppointmentAlreadyExistsException.class)
    public ResponseEntity<Object> handleAppointmentAlreadyExistsException(AppointmentAlreadyExistsException ex,
                                                             WebRequest request){
        ApiResponseError error = new ApiResponseError(HttpStatus.CONFLICT, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, error.getStatus());
    }

}
