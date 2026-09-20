package test;

import loggingutils.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.logInfo("Aplicacion iniciada correctamente");
        logger.logWarning("La operacion demoro mas de lo esperado");
        logger.logError("No se pudo completar la operacion");
    }
}
