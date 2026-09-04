public interface InstrumentoMusical {

    // Metodos abstractos: implicitamente public abstract.
    void hacerSonar();

    String queEs();

    // Original del enunciado:
    //   void afinar(){}
    // error: interface abstract methods cannot have body
    //
    // Un metodo de interface con cuerpo necesita ser default (o static, o
    // private). Declararlo 'default' es tambien la respuesta a la pregunta
    // del enunciado: da una implementacion heredada por todas las clases que
    // implementan la interface, sin obligarlas a definir afinar().
    default void afinar() {
        System.out.println("Afinando " + queEs() + " (afinado estandar)");
    }
}
