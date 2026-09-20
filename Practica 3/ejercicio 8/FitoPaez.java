package practica3;

import java.util.Objects;

/** Singleton implementado mediante un enum. */
public enum FitoPaez {
    INSTANCIA;

    private final InstrumentoMusical instrumento = new Piano();

    public InstrumentoMusical getInstrumento() {
        return instrumento;
    }

    public void tocarCancion(Notas[] notas, int[] duraciones) {
        Objects.requireNonNull(notas, "notas");
        Objects.requireNonNull(duraciones, "duraciones");
        if (notas.length != duraciones.length) {
            throw new IllegalArgumentException(
                    "Debe existir una duracion para cada nota");
        }

        System.out.println("Fito Paez comienza a tocar:");
        for (int i = 0; i < notas.length; i++) {
            instrumento.hacerSonar(notas[i], duraciones[i]);
        }
    }
}
