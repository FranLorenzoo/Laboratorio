package laboratorio;

import java.io.File;

/**
 * Solucion al ejercicio 3.2.
 *
 * La version original no compilaba porque java.io.File no tiene constructor
 * sin argumentos, y el constructor de MiArchivo generaba una llamada
 * implicita a super(). Aca se resuelve invocando explicitamente a uno de los
 * constructores que File si ofrece.
 */
public class MiArchivo extends File {

    // File implementa Serializable, por lo que conviene declarar este campo
    // para evitar la advertencia del compilador (javac -Xlint:serial).
    private static final long serialVersionUID = 1L;

    /** Solucion 1: el constructor recibe la ruta y la delega a File. */
    public MiArchivo(String pathname) {
        super(pathname);
        System.out.println("Mi Archivo instanciado: " + getAbsolutePath());
    }

    /** Solucion 2: se conserva el constructor sin argumentos usando una ruta por defecto. */
    public MiArchivo() {
        super("archivo_por_defecto.txt");
        System.out.println("Mi Archivo instanciado (ruta por defecto): " + getAbsolutePath());
    }

    /** Metodo propio, para mostrar que se hereda todo el comportamiento de File. */
    public void mostrarDatos() {
        System.out.println("  nombre  : " + getName());
        System.out.println("  existe  : " + exists());
        System.out.println("  ruta abs: " + getAbsolutePath());
    }
}
