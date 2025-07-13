package techlab.Proyectofinal.entity;

public enum EstadoPedido {
    PENDIENTE, CONFIRMADO, ENVIADO, ENTREGADO, CANCELADO;

    public static boolean esValido(String estado) {
        try {
            EstadoPedido.valueOf(estado.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
