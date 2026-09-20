package practica3;

public enum Notas {
    DO("C"),
    RE("D"),
    MI("E"),
    FA("F"),
    SOL("G"),
    LA("A"),
    SI("B");

    private final String cifradoAmericano;

    Notas(String cifradoAmericano) {
        this.cifradoAmericano = cifradoAmericano;
    }

    public String getCifradoAmericano() {
        return cifradoAmericano;
    }

    @Override
    public String toString() {
        return name() + " (" + cifradoAmericano + ")";
    }
}
