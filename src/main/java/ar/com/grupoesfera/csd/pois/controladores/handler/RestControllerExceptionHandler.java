package ar.com.grupoesfera.csd.pois.controladores.handler;

import ar.com.grupoesfera.csd.pois.exceptions.NoNearPointFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    // Excepción para BAD_REQUEST en RequestParam cuando parsea
    // Harcodear mensaje

    @ExceptionHandler(NoNearPointFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEmptyName(
            RuntimeException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("error", HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMessageNotReadable(
            RuntimeException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "La latitud y la longitud deben ser valores numéricos");
        body.put("error", HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleArgumentTypeMismatch(
            RuntimeException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "La latitud y la longitud deben ser valores numéricos");
        body.put("error", HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }

}
