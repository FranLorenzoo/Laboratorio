public interface TodosLosColores extends ColImpresion, ColArcoIris {

    int FUCSIA = 17, BORDO = ROJO + 90;

    // ROJO no es ambiguo: llega por dos caminos (ColImpresion y ColArcoIris)
    // pero es UNA sola constante, la heredada de ColPrimarios.
    //
    // AMARILLO si lo seria, porque son dos constantes distintas:
    //   int X = AMARILLO;   // error: reference to AMARILLO is ambiguous
    // Hay que calificarlo:
    int AMARILLO_IMPRESION = ColImpresion.AMARILLO;
    int AMARILLO_ARCOIRIS  = ColArcoIris.AMARILLO;
}
