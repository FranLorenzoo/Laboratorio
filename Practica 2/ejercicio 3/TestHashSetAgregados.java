import java.util.Arrays;
import java.util.List;

public class TestHashSetAgregados {

    public static void main(String[] args) {

        System.out.println("=== a) Probar el codigo del enunciado ===");
        System.out.println();

        // --- Caso 1: solo add() ---
        HashSetAgregados<String> s1 = new HashSetAgregados<>();
        s1.add("uno");
        s1.add("dos");
        s1.add("tres");
        System.out.println("--- Solo add(), 3 veces ---");
        System.out.println("size()                = " + s1.size());
        System.out.println("getCantidadAgregados()= " + s1.getCantidadAgregados());
        System.out.println("esperado              = 3   -> "
                           + (s1.getCantidadAgregados() == 3 ? "BIEN" : "MAL"));
        System.out.println();

        // --- Caso 2: addAll() como pide el enunciado ---
        List<String> otra = Arrays.asList("a", "b", "c");
        HashSetAgregados<String> s2 = new HashSetAgregados<>();
        s2.addAll(otra);
        System.out.println("--- addAll() de una coleccion de 3 elementos ---");
        System.out.println("size()                = " + s2.size());
        System.out.println("getCantidadAgregados()= " + s2.getCantidadAgregados());
        System.out.println("esperado              = 3   -> "
                           + (s2.getCantidadAgregados() == 3 ? "BIEN" : "MAL"));
        System.out.println(">> Cuenta el DOBLE: 3 de c.size() + 3 de los add() internos");
        System.out.println();

        // --- Caso 3: elementos repetidos ---
        List<String> conRepetidos = Arrays.asList("x", "x", "y");
        HashSetAgregados<String> s3 = new HashSetAgregados<>();
        s3.addAll(conRepetidos);
        System.out.println("--- addAll() con un repetido: [x, x, y] ---");
        System.out.println("size()                = " + s3.size() + "   (x se guarda una vez)");
        System.out.println("getCantidadAgregados()= " + s3.getCantidadAgregados());
        System.out.println();

        // --- Caso 4: por que pasa ---
        System.out.println("--- La causa: HashSet.addAll llama a add() ---");
        HashSetAgregados<String> s4 = new HashSetAgregados<String>() {
            @Override
            public boolean add(String e) {
                System.out.println("    add(\"" + e + "\") invocado desde addAll");
                return super.add(e);
            }
        };
        System.out.println("Llamando a s4.addAll([p, q]):");
        s4.addAll(Arrays.asList("p", "q"));
        System.out.println("getCantidadAgregados()= " + s4.getCantidadAgregados()
                           + "  (2 de c.size() + 2 de los add)");
    }
}
