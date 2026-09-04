import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TestAlternativas {

    public static void main(String[] args) {

        List<String> otra = Arrays.asList("a", "b", "c");

        // =============================================================
        System.out.println("=== b) Alternativa por composicion ===");
        System.out.println();

        ConjuntoConAgregados<String> c1 = new ConjuntoConAgregados<>();
        c1.addAll(otra);
        System.out.println("--- addAll() de 3 elementos ---");
        System.out.println("size()                = " + c1.size());
        System.out.println("getCantidadAgregados()= " + c1.getCantidadAgregados());
        System.out.println("esperado              = 3   -> "
                           + (c1.getCantidadAgregados() == 3 ? "BIEN" : "MAL"));
        System.out.println();

        ConjuntoConAgregados<String> c2 = new ConjuntoConAgregados<>();
        c2.addAll(Arrays.asList("x", "x", "y"));
        System.out.println("--- addAll() con un repetido: [x, x, y] ---");
        System.out.println("size()                = " + c2.size());
        System.out.println("getCantidadAgregados()= " + c2.getCantidadAgregados()
                           + "   (el duplicado no se agrego, no se cuenta)");
        System.out.println();

        System.out.println("--- Sigue siendo un Set: se usa donde se espera Set ---");
        Set<String> comoSet = c1;
        System.out.println("contains(\"a\")  = " + comoSet.contains("a"));
        System.out.println("polimorfismo OK: " + comoSet);
        System.out.println();

        // =============================================================
        System.out.println("=== c) Decoradores sobre SetForwarding ===");
        System.out.println();

        SetConAgregados<String> ag = new SetConAgregados<>(new LinkedHashSet<>());
        ag.addAll(otra);
        ag.add("d");
        ag.add("a");   // ya estaba
        System.out.println("--- SetConAgregados sobre LinkedHashSet ---");
        System.out.println("contenido             = " + ag);
        System.out.println("getCantidadAgregados()= " + ag.getCantidadAgregados()
                           + "   (a,b,c,d; el segundo \"a\" no)");
        System.out.println();

        SetConRemovidos<String> rem = new SetConRemovidos<>(new TreeSet<>());
        rem.addAll(Arrays.asList("p", "q", "r", "s", "t"));
        rem.remove("p");
        rem.removeAll(Arrays.asList("q", "zzz"));   // zzz no esta
        System.out.println("--- SetConRemovidos sobre TreeSet ---");
        System.out.println("contenido             = " + rem);
        System.out.println("getCantidadRemovidos()= " + rem.getCantidadRemovidos()
                           + "   (p y q; zzz no estaba)");

        Iterator<String> it = rem.iterator();
        it.next();
        it.remove();
        System.out.println("tras iterator().remove():");
        System.out.println("contenido             = " + rem);
        System.out.println("getCantidadRemovidos()= " + rem.getCantidadRemovidos());

        rem.clear();
        System.out.println("tras clear():");
        System.out.println("getCantidadRemovidos()= " + rem.getCantidadRemovidos()
                           + "   (los 2 que quedaban)");
        System.out.println();

        // =============================================================
        System.out.println("--- Los decoradores se APILAN ---");
        SetConAgregados<String> interno = new SetConAgregados<>(new TreeSet<>());
        SetConRemovidos<String> apilado = new SetConRemovidos<>(interno);

        apilado.addAll(Arrays.asList("m", "n", "o"));
        apilado.remove("n");
        System.out.println("contenido             = " + apilado);
        System.out.println("agregados (interno)   = " + interno.getCantidadAgregados());
        System.out.println("removidos (externo)   = " + apilado.getCantidadRemovidos());
    }
}
