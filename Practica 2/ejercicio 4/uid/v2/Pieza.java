import java.io.Serializable;

public class Pieza implements Serializable {

    private static final long serialVersionUID = 1L;   // SIN CAMBIOS

    private String codigo;
    private int cantidad;
    private double precio;        // <-- CAMPO NUEVO

    public Pieza(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Pieza[codigo=" + codigo + ", cantidad=" + cantidad
               + ", precio=" + precio + "]";
    }
}
