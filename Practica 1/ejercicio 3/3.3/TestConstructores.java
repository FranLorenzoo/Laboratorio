import laboratorio1.OtraClase;
import laboratorio1.SubClase;

/**
 * Prueba del caso "constructores protegidos" del ejercicio 3.3.
 * El caso "constructores privados" no se incluye porque, por definicion,
 * no compila (ver 3.3.md).
 */
public class TestConstructores {
    public static void main(String[] args) {

        System.out.println("--- SubClase (paquete laboratorio1) ---");
        SubClase s = new SubClase();
        System.out.println("Instancia creada: " + s.getClass().getName());

        System.out.println();
        System.out.println("--- OtraClase.getX() ---");
        OtraClase o = new OtraClase();
        o.getX();
    }
}
