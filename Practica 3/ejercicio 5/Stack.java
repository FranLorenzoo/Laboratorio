package practica3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Versión de Stack cuyo Iterator se implementa con una clase anónima. */
public class Stack implements Iterable<String> {
    private final ArrayList<String> items = new ArrayList<>();

    public void push(String item) {
        items.add(item);
    }

    public String pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("La pila esta vacia");
        }
        return items.remove(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Iterator<String> iterator() {
        final int indiceInicial = items.size() - 1;

        return new Iterator<String>() {
            private int indice;

            // Inicializador de instancia de la clase anónima.
            {
                indice = indiceInicial;
            }

            @Override
            public boolean hasNext() {
                return indice >= 0;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return items.get(indice--);
            }
        };
    }

    public static void main(String[] args) {
        Stack pila = new Stack();
        pila.push("uno");
        pila.push("dos");
        pila.push("tres");

        pila.forEach(System.out::println);
        System.out.println("Segundo recorrido:");
        pila.forEach(System.out::println);
    }
}
