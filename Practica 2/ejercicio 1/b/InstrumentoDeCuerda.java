public class InstrumentoDeCuerda implements InstrumentoMusical {

    public void hacerSonar() {
        System.out.println("Sonar Cuerdas");
    }

    public String queEs() {
        return "Instrumento de Cuerda";
    }

    // Esta clase NO es abstract, asi que con 'void afinar();' abstracto en la
    // interface estaba obligada a implementarlo:
    //   error: InstrumentoDeCuerda is not abstract and does not override
    //          abstract method afinar() in InstrumentoMusical
    // Al ser 'default' en la interface, hereda la implementacion y no hace
    // falta tocar esta clase. Ese es el punto del ejercicio.
}
