import java.io.Serializable;

/**
 * Ejercicio 4: la jerarquia del ejercicio 2, ahora serializable.
 *
 * Serializable es una interface MARCADORA: no declara ningun metodo. Solo
 * marca el tipo como "autorizado a serializarse"; el trabajo lo hace la JVM
 * por reflexion.
 *
 * Al implementarla en la SUPERCLASE, todas las subclases quedan serializables
 * automaticamente: Serializable se hereda.
 */
public abstract class FiguraGeometrica
        implements Comparable<FiguraGeometrica>, Serializable {

    /**
     * Identificador de version de la clase.
     *
     * Si no se lo declara, el compilador calcula uno a partir de la ESTRUCTURA
     * de la clase (nombre, campos, metodos, interfaces). Cualquier cambio en
     * esa estructura cambia el numero, y los objetos serializados con la
     * version anterior dejan de poder leerse: InvalidClassException.
     *
     * Declarandolo a mano, el control de compatibilidad es explicito.
     */
    private static final long serialVersionUID = 1L;

    private final String nombre;

    /**
     * Campo TRANSIENT: no se serializa.
     *
     * Es un cache del area. No tiene sentido guardarlo —se puede recalcular—
     * y ademas queda null al deserializar, lo que permite mostrar que
     * 'transient' realmente se saltea.
     */
    private transient Double areaCache;

    protected FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double area();

    public abstract double perimetro();

    /** Area con cache; el cache no sobrevive a la serializacion. */
    public double areaCacheada() {
        if (areaCache == null) {
            areaCache = area();
        }
        return areaCache;
    }

    public boolean cacheCargado() {
        return areaCache != null;
    }

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
