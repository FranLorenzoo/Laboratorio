// Original del enunciado:
//   class abstract InstrumentoDeViento ...
// error: <identifier> expected
// El orden correcto es 'abstract class'; los modificadores van antes de la
// palabra clave 'class'.
public abstract class InstrumentoDeViento implements InstrumentoMusical {

    // Original del enunciado:
    //   void hacerSonar(){ ... }
    // error: attempting to assign weaker access privileges; was public
    // Los metodos de una interface son public, asi que la implementacion
    // tambien debe ser public.
    public void hacerSonar() {
        System.out.println("Sonar Vientos");
    }

    public String queEs() {
        return "Instrumento de Viento";
    }

    // Como es abstract, NO esta obligada a implementar afinar(), incluso si
    // afinar() fuese abstracto en la interface.
}
