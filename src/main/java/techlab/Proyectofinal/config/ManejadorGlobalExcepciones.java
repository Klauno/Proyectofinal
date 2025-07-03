package techlab.Proyectofinal.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import techlab.Proyectofinal.exception.ProductNotFoundException;

/**
 * Maneja globalmente las excepciones lanzadas en los controladores.
 */
@ControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> manejarProductoNoEncontrado(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionGeneral(Exception ex) {
        // Aquí puedes agregar logging si quieres
        return new ResponseEntity<>("Ocurrió un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
