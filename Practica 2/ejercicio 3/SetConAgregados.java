import java.util.Collection;
import java.util.Set;

/**
 * Decorador: cuenta los elementos agregados. Solo redefine add y addAll.
 */
public class SetConAgregados<E> extends SetForwarding<E> {

    private int cantidadAgregados = 0;

    public SetConAgregados(Set<E> delegado) {
        super(delegado);
    }

    public int getCantidadAgregados() {
        return cantidadAgregados;
    }

    @Override
    public boolean add(E e) {
        boolean agregado = super.add(e);
        if (agregado) {
            cantidadAgregados++;
        }
        return agregado;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean cambio = false;
        for (E e : c) {
            if (add(e)) {
                cambio = true;
            }
        }
        return cambio;
    }
}
