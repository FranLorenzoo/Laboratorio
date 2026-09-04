import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Punto b) Alternativa por COMPOSICION.
 *
 * En vez de heredar de HashSet, la clase:
 *   - implementa la interface Set<E>  (es un Set para quien la use)
 *   - CONTIENE un Set<E> al que le delega el trabajo real
 *
 * Al no heredar, no queda expuesta a como HashSet este implementado por
 * dentro: nadie llama a nuestro add() por la espalda.
 *
 * Semantica del contador: cuenta los elementos EFECTIVAMENTE agregados, o sea
 * los que hicieron crecer el conjunto. Los duplicados no cuentan, porque no se
 * agregaron. (Si se quisiera contar intentos, se incrementaria antes del if.)
 */
public class ConjuntoConAgregados<E> implements Set<E> {

    private final Set<E> elementos;
    private int cantidadAgregados = 0;

    public ConjuntoConAgregados() {
        this.elementos = new HashSet<>();
    }

    /** Permite decidir desde afuera QUE Set se usa por dentro. */
    public ConjuntoConAgregados(Set<E> elementos) {
        this.elementos = elementos;
    }

    public int getCantidadAgregados() {
        return cantidadAgregados;
    }

    // ---- los dos metodos que nos interesan ----

    @Override
    public boolean add(E e) {
        boolean agregado = elementos.add(e);
        if (agregado) {
            cantidadAgregados++;
        }
        return agregado;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean cambio = false;
        for (E e : c) {
            if (add(e)) {          // llamamos a NUESTRO add: se cuenta una sola vez
                cambio = true;
            }
        }
        return cambio;
    }

    // ---- el resto se delega sin mas ----

    @Override public int size()                                 { return elementos.size(); }
    @Override public boolean isEmpty()                          { return elementos.isEmpty(); }
    @Override public boolean contains(Object o)                 { return elementos.contains(o); }
    @Override public Iterator<E> iterator()                     { return elementos.iterator(); }
    @Override public Object[] toArray()                         { return elementos.toArray(); }
    @Override public <T> T[] toArray(T[] a)                     { return elementos.toArray(a); }
    @Override public boolean remove(Object o)                   { return elementos.remove(o); }
    @Override public boolean containsAll(Collection<?> c)       { return elementos.containsAll(c); }
    @Override public boolean retainAll(Collection<?> c)         { return elementos.retainAll(c); }
    @Override public boolean removeAll(Collection<?> c)         { return elementos.removeAll(c); }
    @Override public void clear()                               { elementos.clear(); }

    @Override public boolean equals(Object o)                   { return elementos.equals(o); }
    @Override public int hashCode()                             { return elementos.hashCode(); }
    @Override public String toString()                          { return elementos.toString(); }
}
