public class MisColores implements ColImpresion, ColArcoIris {

    private final int unColor;

    public MisColores() {
        // Original del enunciado:
        //   int unColor = AMARILLO;
        // error: reference to AMARILLO is ambiguous
        //   both variable AMARILLO in ColImpresion and variable AMARILLO
        //   in ColArcoIris match
        //
        // Correccion: calificar de que interface se quiere la constante.
        this.unColor = ColImpresion.AMARILLO;
    }

    public int getUnColor() {
        return unColor;
    }
}
