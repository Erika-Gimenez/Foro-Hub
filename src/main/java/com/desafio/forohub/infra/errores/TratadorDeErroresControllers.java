package com.desafio.forohub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErroresControllers {

    // Manejo de errores 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarError404() {
        return ResponseEntity.notFound().build();
    }

    // Manejo de errores de validación (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErrorValidacion>> tratarError400(MethodArgumentNotValidException e) {
        List<DatosErrorValidacion> errores = e.getFieldErrors().stream()
                .map(DatosErrorValidacion::new)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errores);
    }

    // Manejo de errores personalizados de validación
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<String> tratarErrorDeValidacion(ValidacionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // Manejo de errores de credenciales incorrectas
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> manejarBadCredentials(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

    // Clase para encapsular errores de validación
    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
