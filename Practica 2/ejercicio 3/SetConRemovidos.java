import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Punto c) Decorador que cuenta los elementos REMOVIDOS.
 *
 * Remover tiene mas vias de entrada que agregar, y hay que cubrirlas todas:
 *   remove(Object), removeAll, retainAll, clear() e iterator().remove()
 *
 * Para removeAll/retainAll/clear se cuenta por DIFERENCIA DE TAMANIO en vez de
 * confiar en cuantos elementos traia la coleccion argumento. Ese es justamente
 * el error del codigo original del enunciado (usaba c.size()): la cantidad de
 * elementos del argumento no es la cantidad de elementos que efectivamente
 * cambiaron el conjunto.
 */
public class SetConRemovidos<E> extends SetForwarding<E> {

    private int cantidadRemovidos = 0;

    public SetConRemovidos(Set<E> delegado) {
        super(delegado);
    }

    public int getCantidadRemovidos() {
        return cantidadRemovidos;
    }

    @Override
    public boolean remove(Object o) {
        boolean removido = super.remove(o);
        if (removido) {
            cantidadRemovidos++;
        }
        return removido;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int antes = size();
        boolean cambio = super.removeAll(c);
        cantidadRemovidos += antes - size();
        return cambio;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int antes = size();
        boolean cambio = super.retainAll(c);
        cantidadRemovidos += antes - size();
        return cambio;
    }

    @Override
    public void clear() {
        cantidadRemovidos += size();
        super.clear();
    }

    /**
     * Se envuelve el iterador para que su remove() tambien cuente. Sin esto,
     * un for-each con it.remove() vaciaria el conjunto sin registrar nada.
     */
    @Override
    public Iterator<E> iterator() {
        final Iterator<E> it = super.iterator();
        return new Iterator<E>() {
            @Override public boolean hasNext() { return it.hasNext(); }
            @Override public E next()          { return it.next(); }
            @Override public void remove() {
                it.remove();
                cantidadRemovidos++;
            }
        };
    }
}
