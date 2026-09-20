package practica3;

import java.util.List;
import java.util.Set;

public class TestStringConverterSet {
    public static void main(String[] args) {
        Set<String> valores = new StringConverterSet(
                List.of(42, 3.14, true, "texto", 'X'));

        System.out.println("Elementos convertidos:");
        valores.forEach(valor ->
                System.out.println(valor + " (" + valor.getClass().getSimpleName() + ")"));

        System.out.println("\nadd('nuevo'): " + valores.add("nuevo"));
        System.out.println("contains('42'): " + valores.contains("42"));
        System.out.println("remove('true'): " + valores.remove("true"));
        System.out.println("Tamanio final: " + valores.size());
        System.out.println(valores);
    }
}
