package practica3;

public class TestPiano {
    public static void main(String[] args) {
        Piano piano = new Piano();
        System.out.println(piano.queEs());
        piano.hacerSonar();
        piano.afinar(FrecuenciasDeLA.CAMARA);
        piano.hacerSonar(Notas.LA, 500);

        Notas[] melodia = {
            Notas.DO, Notas.RE, Notas.MI, Notas.FA,
            Notas.SOL, Notas.LA, Notas.SI, Notas.DO
        };
        int[] tiempos = {300, 300, 300, 300, 300, 300, 300, 600};

        FitoPaez fito = FitoPaez.INSTANCIA;
        fito.getInstrumento().afinar(FrecuenciasDeLA.ISO_16);
        fito.tocarCancion(melodia, tiempos);

        System.out.println("\nMisma instancia de Fito: "
                + (fito == FitoPaez.INSTANCIA));
    }
}
