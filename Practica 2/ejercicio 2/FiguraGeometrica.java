/**
 * Superclase abstracta de la jerarquia de figuras.
 *
 * Implementa Comparable<FiguraGeometrica> para definir el orden natural
 * pedido por el enunciado: una figura A es menor que una figura B si el
 * area de A es menor que el area de B.
 */
public abstract class FiguraGeometrica implements Comparable<FiguraGeometrica> {

    private final String nombre;

    protected FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /** Cada figura concreta sabe calcular su area. */
    public abstract double area();

    public abstract double perimetro();

    /**
     * Orden natural: por area.
     *
     * Se usa Double.compare y NO (int)(this.area() - o.area()), porque la
     * resta de double truncada a int da 0 para cualquier diferencia menor
     * que 1, y ademas desborda. Con areas de 3.14 y 3.99 la resta truncada
     * diria "iguales" y el orden saldria mal.
     */
    @Override
    public int compareTo(FiguraGeometrica otra) {
        return Double.compare(this.area(), otra.area());
    }

    @Override
    public String toString() {
        return String.format("%-22s area=%8.2f  perimetro=%8.2f",
                             nombre, area(), perimetro());
    }
}
