public class TestInstrumentos {

    public static void main(String[] args) {

        // Polimorfismo: el arreglo es del tipo de la INTERFACE.
        InstrumentoMusical[] orquesta = {
            new Flauta(),
            new InstrumentoDeCuerda(),
            new Guitarra()
        };

        System.out.println("--- Polimorfismo sobre la interface ---");
        for (InstrumentoMusical i : orquesta) {
            System.out.println(i.queEs() + ":");
            i.hacerSonar();
            i.afinar();
            System.out.println();
        }

        System.out.println("--- Quien usa el default y quien lo redefine ---");
        System.out.println("Flauta            -> default de InstrumentoMusical");
        System.out.println("InstrumentoDeCuerda -> default de InstrumentoMusical");
        System.out.println("Guitarra          -> redefine afinar()");

        System.out.println();
        System.out.println("--- InstrumentoDeViento es abstract: no se instancia ---");
        System.out.println("// new InstrumentoDeViento();");
        System.out.println("//   error: InstrumentoDeViento is abstract; cannot be instantiated");
    }
}
