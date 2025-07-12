package techlab.Proyectofinal.exception;


//Excepción para indicar que el stock es insuficiente para un producto.
public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException(String nombreProducto) {
        super("Stock insuficiente para el producto: " + nombreProducto);
    }
}
