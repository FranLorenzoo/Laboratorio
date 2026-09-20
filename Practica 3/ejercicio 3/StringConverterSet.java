package practica3;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Vista Set cuyos elementos se exponen como String. Conserva el orden de
 * inserción y evita representaciones String duplicadas.
 */
public class StringConverterSet extends AbstractSet<String> {
    private final Set<Object> elementos = new LinkedHashSet<>();

    public StringConverterSet() {
    }

    public StringConverterSet(Collection<?> origen) {
        Objects.requireNonNull(origen, "origen");
        for (Object elemento : origen) {
            agregarObjeto(elemento);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new IteratorStringAdapter(elementos.iterator());
    }

    @Override
    public int size() {
        return elementos.size();
    }

    @Override
    public boolean add(String elemento) {
        // La vista expone Strings: incluso null se representa como "null".
        return agregarObjeto(String.valueOf(elemento));
    }

    @Override
    public void clear() {
        elementos.clear();
    }

    private boolean agregarObjeto(Object elemento) {
        String representacion = String.valueOf(elemento);
        for (Object existente : elementos) {
            if (String.valueOf(existente).equals(representacion)) {
                return false;
            }
        }
        return elementos.add(elemento);
    }

    /** Adapter entre Iterator<Object> e Iterator<String>. */
    private static class IteratorStringAdapter implements Iterator<String> {
        private final Iterator<Object> adaptado;

        IteratorStringAdapter(Iterator<Object> adaptado) {
            this.adaptado = adaptado;
        }

        @Override
        public boolean hasNext() {
            return adaptado.hasNext();
        }

        @Override
        public String next() {
            return String.valueOf(adaptado.next());
        }

        @Override
        public void remove() {
            adaptado.remove();
        }
    }
}
