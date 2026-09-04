import java.io.Serializable;

public class Pieza implements Serializable {

    // sin serialVersionUID: la JVM lo calcula desde la estructura

    private String codigo;
    private int cantidad;
    private double precio;        // cambia la estructura -> cambia el UID calculado

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
