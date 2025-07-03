package techlab.Proyectofinal.dto;

public class ProductResponseDTO {
    private String message;

    public ProductResponseDTO() {}

    public ProductResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
