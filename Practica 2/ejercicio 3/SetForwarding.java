import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Punto c) Clase de REENVIO (forwarding).
 *
 * Implementa Set<E> delegando absolutamente todo en otro Set<E>. Por si sola
 * no agrega comportamiento: su valor es servir de base para decoradores, que
 * solo redefinen los metodos que les interesan y heredan el reenvio del resto.
 *
 * Es la pieza que hace facil definir nuevos tipos de Set: escribir un
 * decorador cuesta redefinir 2 o 3 metodos en vez de los 15 de la interface.
 *
 * Como envuelve un Set<E> cualquiera, los decoradores se pueden APILAR y
 * funcionan sobre HashSet, TreeSet, LinkedHashSet o cualquier otra
 * implementacion.
 */
public class SetForwarding<E> implements Set<E> {

    protected final Set<E> delegado;

    public SetForwarding(Set<E> delegado) {
        if (delegado == null) {
            throw new IllegalArgumentException("el Set delegado no puede ser null");
        }
        this.delegado = delegado;
    }

    @Override public int size()                            { return delegado.size(); }
    @Override public boolean isEmpty()                     { return delegado.isEmpty(); }
    @Override public boolean contains(Object o)            { return delegado.contains(o); }
    @Override public Iterator<E> iterator()                { return delegado.iterator(); }
    @Override public Object[] toArray()                    { return delegado.toArray(); }
    @Override public <T> T[] toArray(T[] a)                { return delegado.toArray(a); }
    @Override public boolean add(E e)                      { return delegado.add(e); }
    @Override public boolean remove(Object o)              { return delegado.remove(o); }
    @Override public boolean containsAll(Collection<?> c)  { return delegado.containsAll(c); }
    @Override public boolean addAll(Collection<? extends E> c) { return delegado.addAll(c); }
    @Override public boolean retainAll(Collection<?> c)    { return delegado.retainAll(c); }
    @Override public boolean removeAll(Collection<?> c)    { return delegado.removeAll(c); }
    @Override public void clear()                          { delegado.clear(); }

    @Override public boolean equals(Object o)              { return delegado.equals(o); }
    @Override public int hashCode()                        { return delegado.hashCode(); }
    @Override public String toString()                     { return delegado.toString(); }
}
