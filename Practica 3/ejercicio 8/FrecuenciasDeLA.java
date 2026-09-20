package practica3;

public enum FrecuenciasDeLA {
    ISO_16(440, "Organizacion Internacional de Estandarizacion ISO 16"),
    CAMARA(444, "Afinacion de camara"),
    RENACIMIENTO(446, "Renacimiento"),
    ORGANOS_DE_BACH(480, "Organos alemanes que tocaba Bach");

    private final int hertz;
    private final String descripcion;

    FrecuenciasDeLA(int hertz, String descripcion) {
        this.hertz = hertz;
        this.descripcion = descripcion;
    }

    public int getHertz() {
        return hertz;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return hertz + " Hz - " + descripcion;
    }
}
