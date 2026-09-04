import java.util.Collection;
import java.util.HashSet;

/**
 * Codigo ORIGINAL del enunciado, transcripto tal cual para poder probarlo.
 * NO funciona: ver 3.md, punto a).
 */
public class HashSetAgregados<E> extends HashSet<E> {

    // No es parte del enunciado: se agrega solo para silenciar la advertencia
    // de javac -Xlint:serial (HashSet es Serializable, ver ejercicio 4).
    private static final long serialVersionUID = 1L;

    private int cantidadAgregados = 0;

    public HashSetAgregados() {
    }

    public HashSetAgregados(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        cantidadAgregados++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        cantidadAgregados += c.size();
        return super.addAll(c);
    }

    public int getCantidadAgregados() {
        return cantidadAgregados;
    }
}
