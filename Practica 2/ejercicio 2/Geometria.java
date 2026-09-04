import java.util.Comparator;

/** Utilidades sobre arreglos de figuras. */
public class Geometria {

    private Geometria() {
    }

    public static void mostrar(String titulo, FiguraGeometrica[] figuras) {
        System.out.println(titulo);
        for (FiguraGeometrica f : figuras) {
            System.out.println("  " + f);
        }
        System.out.println();
    }

    public static double sumaAreas(FiguraGeometrica[] figuras) {
        double total = 0;
        for (FiguraGeometrica f : figuras) {
            total += f.area();
        }
        return total;
    }

    /** Comparador por perimetro, para contrastar con el orden natural. */
    public static Comparator<FiguraGeometrica> porPerimetro() {
        return Comparator.comparingDouble(FiguraGeometrica::perimetro);
    }

    /** Comparador por nombre, orden lexicografico. */
    public static Comparator<FiguraGeometrica> porNombre() {
        return Comparator.comparing(FiguraGeometrica::getNombre);
    }

    public static boolean estaOrdenadoPorArea(FiguraGeometrica[] figuras) {
        for (int i = 1; i < figuras.length; i++) {
            if (figuras[i - 1].compareTo(figuras[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
