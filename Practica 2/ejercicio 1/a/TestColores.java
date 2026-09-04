public class TestColores implements TodosLosColores {

    public static void main(String[] args) {

        System.out.println("--- Constantes heredadas de ColPrimarios ---");
        System.out.println("ROJO  = " + ROJO);
        System.out.println("VERDE = " + VERDE);
        System.out.println("AZUL  = " + AZUL);

        System.out.println();
        System.out.println("--- ROJO llega por dos caminos, pero es UNA constante ---");
        System.out.println("ColPrimarios.ROJO    = " + ColPrimarios.ROJO);
        System.out.println("ColArcoIris.ROJO     = " + ColArcoIris.ROJO);
        System.out.println("ColImpresion.ROJO    = " + ColImpresion.ROJO);
        System.out.println("TodosLosColores.ROJO = " + TodosLosColores.ROJO);
        System.out.println("Por eso BORDO = ROJO + 90 = " + BORDO + " (no es ambiguo)");

        System.out.println();
        System.out.println("--- AMARILLO son DOS constantes distintas ---");
        System.out.println("ColArcoIris.AMARILLO  = " + ColArcoIris.AMARILLO);
        System.out.println("ColImpresion.AMARILLO = " + ColImpresion.AMARILLO);
        System.out.println("Sin calificar: error de compilacion (reference is ambiguous)");

        System.out.println();
        System.out.println("--- Las constantes son public static final implicitamente ---");
        java.lang.reflect.Field f;
        try {
            f = ColPrimarios.class.getField("ROJO");
            int m = f.getModifiers();
            System.out.println("ROJO es public? " + java.lang.reflect.Modifier.isPublic(m));
            System.out.println("ROJO es static? " + java.lang.reflect.Modifier.isStatic(m));
            System.out.println("ROJO es final?  " + java.lang.reflect.Modifier.isFinal(m));
        } catch (NoSuchFieldException e) {
            System.out.println("no deberia pasar: " + e);
        }

        System.out.println();
        System.out.println("--- MisColores corregida ---");
        MisColores mc = new MisColores();
        System.out.println("unColor (ColImpresion.AMARILLO) = " + mc.getUnColor());
    }
}
