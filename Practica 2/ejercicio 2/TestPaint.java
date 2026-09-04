import java.util.Arrays;
import java.util.Comparator;

/**
 * Ejercicio 2: imprimir las figuras ordenadas por area, usando los metodos
 * de ordenacion de java.util.Arrays.
 */
public class TestPaint {

    public static void main(String[] args) {

        FiguraGeometrica[] figuras = {
            new Circulo(3),
            new Rectangulo(10, 4),
            new Cuadrado(5),
            new TrianguloRectangulo(6, 8),
            new Circulo(1.5),
            new Rectangulo(2, 3)
        };

        Geometria.mostrar("--- Sin ordenar (orden de creacion) ---", figuras);
        System.out.println("Esta ordenado por area? "
                           + Geometria.estaOrdenadoPorArea(figuras));
        System.out.println();

        // ---------------------------------------------------------------
        // Orden natural: usa compareTo de FiguraGeometrica (Comparable)
        // ---------------------------------------------------------------
        Arrays.sort(figuras);
        Geometria.mostrar("--- Arrays.sort(figuras) : orden natural, por area ---",
                          figuras);
        System.out.println("Esta ordenado por area? "
                           + Geometria.estaOrdenadoPorArea(figuras));
        System.out.println();

        // ---------------------------------------------------------------
        // Orden descendente sin tocar compareTo
        // ---------------------------------------------------------------
        Arrays.sort(figuras, Comparator.reverseOrder());
        Geometria.mostrar("--- Arrays.sort(figuras, reverseOrder()) : area descendente ---",
                          figuras);

        // ---------------------------------------------------------------
        // Otro criterio, con Comparator, sin modificar las clases
        // ---------------------------------------------------------------
        Arrays.sort(figuras, Geometria.porPerimetro());
        Geometria.mostrar("--- Arrays.sort(figuras, porPerimetro()) ---", figuras);

        Arrays.sort(figuras, Geometria.porNombre());
        Geometria.mostrar("--- Arrays.sort(figuras, porNombre()) ---", figuras);

        // ---------------------------------------------------------------
        // binarySearch exige el arreglo ordenado con el MISMO criterio
        // ---------------------------------------------------------------
        Arrays.sort(figuras);
        FiguraGeometrica buscada = figuras[3];
        int pos = Arrays.binarySearch(figuras, buscada);
        System.out.println("--- Arrays.binarySearch sobre el arreglo ordenado ---");
        System.out.println("Buscando: " + buscada.getNombre());
        System.out.println("Encontrada en la posicion: " + pos);
        System.out.println();

        // ---------------------------------------------------------------
        // Ordenar solo un tramo
        // ---------------------------------------------------------------
        Arrays.sort(figuras, Comparator.reverseOrder());
        Arrays.sort(figuras, 0, 3);
        Geometria.mostrar("--- Arrays.sort(figuras, 0, 3) : solo las 3 primeras ---",
                          figuras);

        System.out.printf("Suma de todas las areas: %.2f%n",
                          Geometria.sumaAreas(figuras));
    }
}
