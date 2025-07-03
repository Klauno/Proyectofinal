package techlab.Proyectofinal.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String nombreProducto) {
        super("Stock insuficiente para el producto: " + nombreProducto);
    }
}
