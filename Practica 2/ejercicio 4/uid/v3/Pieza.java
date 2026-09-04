import java.io.Serializable;

public class Pieza implements Serializable {

    private static final long serialVersionUID = 2L;   // <-- CAMBIADO

    private String codigo;
    private int cantidad;

    public Pieza(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Pieza[codigo=" + codigo + ", cantidad=" + cantidad + "]";
    }
}
