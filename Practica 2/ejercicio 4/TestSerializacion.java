import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class TestSerializacion {

    private static final String ARCHIVO = "figuras.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FiguraGeometrica[] originales = {
            new Circulo(3),
            new Rectangulo(10, 4),
            new Cuadrado(5),
            new TrianguloRectangulo(6, 8)
        };

        // Ordenadas por area, para mostrar que el orden tambien se conserva
        Arrays.sort(originales);

        System.out.println("--- Serializable es una interface marcadora ---");
        System.out.println("Circulo instanceof Serializable ? "
                           + (originales[0] instanceof Serializable));
        System.out.println("Metodos que declara Serializable: "
                           + Serializable.class.getDeclaredMethods().length);
        System.out.println();

        System.out.println("--- Objetos originales ---");
        for (FiguraGeometrica f : originales) {
            System.out.println("  " + f);
        }
        System.out.println();

        // Cargamos el cache transient antes de escribir
        for (FiguraGeometrica f : originales) {
            f.areaCacheada();
        }
        System.out.println("Cache cargado antes de serializar? "
                           + originales[0].cacheCargado());
        System.out.println();

        // ---------------------------------------------------------------
        // ESCRITURA
        // ---------------------------------------------------------------
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(originales);
        }
        long bytes = Files.size(Path.of(ARCHIVO));
        System.out.println("--- Escrito " + ARCHIVO + " (" + bytes + " bytes) ---");
        System.out.println();

        // ---------------------------------------------------------------
        // LECTURA
        // ---------------------------------------------------------------
        FiguraGeometrica[] recuperadas;
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            recuperadas = (FiguraGeometrica[]) in.readObject();
        }

        System.out.println("--- Objetos recuperados ---");
        for (FiguraGeometrica f : recuperadas) {
            System.out.println("  " + f);
        }
        System.out.println();

        // ---------------------------------------------------------------
        // Verificaciones
        // ---------------------------------------------------------------
        System.out.println("--- Que se conservo y que no ---");
        System.out.println("Cantidad de figuras:  " + recuperadas.length);
        System.out.println("Tipos concretos:      "
                           + recuperadas[0].getClass().getSimpleName() + ", "
                           + recuperadas[1].getClass().getSimpleName() + ", "
                           + recuperadas[2].getClass().getSimpleName() + ", "
                           + recuperadas[3].getClass().getSimpleName());
        System.out.println("Siguen ordenadas?     " + estaOrdenado(recuperadas));

        boolean areasIguales = true;
        for (int i = 0; i < originales.length; i++) {
            if (originales[i].area() != recuperadas[i].area()) {
                areasIguales = false;
            }
        }
        System.out.println("Areas identicas?      " + areasIguales);
        System.out.println();

        System.out.println("--- Identidad: son objetos NUEVOS ---");
        System.out.println("originales[0] == recuperadas[0] ? "
                           + (originales[0] == recuperadas[0]));
        System.out.println("Misma clase?                     "
                           + (originales[0].getClass() == recuperadas[0].getClass()));
        System.out.println(">> La deserializacion CREA objetos, no los comparte.");
        System.out.println();

        System.out.println("--- El campo transient NO se serializo ---");
        System.out.println("Cache cargado tras deserializar? "
                           + recuperadas[0].cacheCargado());
        System.out.println("areaCacheada() lo recalcula:      "
                           + String.format("%.2f", recuperadas[0].areaCacheada()));
        System.out.println("Cache cargado ahora?              "
                           + recuperadas[0].cacheCargado());
        System.out.println();

        System.out.println("--- El constructor NO se ejecuta al deserializar ---");
        System.out.println("nombre recuperado: " + recuperadas[0].getNombre());
        System.out.println(">> El nombre esta, y es un campo final que ningun");
        System.out.println("   constructor asigno: lo escribio la deserializacion.");
    }

    private static boolean estaOrdenado(FiguraGeometrica[] fs) {
        for (int i = 1; i < fs.length; i++) {
            if (fs[i - 1].compareTo(fs[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
