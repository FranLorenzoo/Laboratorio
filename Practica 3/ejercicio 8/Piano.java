package practica3;

import java.util.Objects;

public class Piano implements InstrumentoMusical {
    private FrecuenciasDeLA afinacion = FrecuenciasDeLA.ISO_16;

    @Override
    public void hacerSonar() {
        System.out.println("El piano hace sonar todos sus registros");
    }

    @Override
    public void hacerSonar(Notas nota, int duracion) {
        Objects.requireNonNull(nota, "nota");
        if (duracion <= 0) {
            throw new IllegalArgumentException("La duracion debe ser positiva");
        }
        System.out.printf("Piano: %-7s durante %d ms (LA=%d Hz)%n",
                nota, duracion, afinacion.getHertz());
    }

    @Override
    public String queEs() {
        return "Piano";
    }

    @Override
    public void afinar(FrecuenciasDeLA frecuencia) {
        afinacion = Objects.requireNonNull(frecuencia, "frecuencia");
        System.out.println("Piano afinado en " + afinacion);
    }

    public FrecuenciasDeLA getAfinacion() {
        return afinacion;
    }
}
