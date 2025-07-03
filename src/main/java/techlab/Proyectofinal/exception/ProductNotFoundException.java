package techlab.Proyectofinal.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String searchTerm) {
        super("No se encontró ningún producto con el término: " + searchTerm);
    }
}
