package techlab.Proyectofinal.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String mensaje) {
        super(mensaje);
    }
}
