package practica3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Pila de String recorrible sin modificar sus elementos. */
public class Stack implements Iterable<String> {
    private final ArrayList<String> items;

    public Stack() {
        items = new ArrayList<>();
    }

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

    public int size() {
        return items.size();
    }

    /**
     * Cada llamada crea un iterador independiente. El recorrido respeta el
     * orden LIFO: comienza por el elemento ubicado en el tope.
     */
    @Override
    public StackIterator iterator() {
        return new StackIterator();
    }

    /**
     * Es una clase interna (no static), por lo que cada instancia queda
     * asociada a una instancia particular de Stack.
     */
    public class StackIterator implements Iterator<String> {
        private int indice = items.size() - 1;

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
    }

    public static void main(String[] args) {
        Stack pila = new Stack();
        pila.push("uno");
        pila.push("dos");
        pila.push("tres");

        System.out.println("Recorrido destructivo con pop (solo una vez):");
        while (!pila.isEmpty()) {
            System.out.println(pila.pop());
        }

        pila.push("uno");
        pila.push("dos");
        pila.push("tres");

        System.out.println("\nPrimer recorrido con StackIterator:");
        for (String item : pila) {
            System.out.println(item);
        }

        System.out.println("\nSegundo recorrido con otro StackIterator:");
        Iterator<String> otroRecorrido = pila.iterator();
        otroRecorrido.forEachRemaining(System.out::println);

        System.out.println("\nLa pila conserva " + pila.size() + " elementos.");
    }
}
