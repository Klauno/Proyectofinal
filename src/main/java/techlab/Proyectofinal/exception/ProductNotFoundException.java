package techlab.Proyectofinal.exception;

/**
 * Excepción para indicar que un producto no fue encontrado.
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String mensaje) {
        super(mensaje);
    }
}
