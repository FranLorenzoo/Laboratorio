package practica3;

public interface InstrumentoMusical {
    void hacerSonar();

    void hacerSonar(Notas nota, int duracion);

    String queEs();

    default void afinar() {
        afinar(FrecuenciasDeLA.ISO_16);
    }

    void afinar(FrecuenciasDeLA frecuencia);
}
