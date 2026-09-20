package practica3;

import java.util.Iterator;

/** Demuestra la sintaxis para crear una clase interna desde otra clase. */
public class TestStackIteratorExterno {
    public static void main(String[] args) {
        Stack pila = new Stack();
        pila.push("A");
        pila.push("B");

        Iterator<String> iterador = pila.new StackIterator();
        iterador.forEachRemaining(System.out::println);
    }
}
