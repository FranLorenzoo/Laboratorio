package loggingutils;

import java.util.Objects;

/** Logger Singleton que delega el registro en java.util.logging. */
public final class Logger {
    private static final Logger INSTANCIA = new Logger();
    private static final java.util.logging.Logger LOGGER_JAVA =
            java.util.logging.Logger.getLogger(Logger.class.getName());

    private Logger() {
    }

    public static Logger getInstance() {
        return INSTANCIA;
    }

    public void logInfo(String mensaje) {
        LOGGER_JAVA.info(Objects.requireNonNull(mensaje, "mensaje"));
    }

    public void logWarning(String mensaje) {
        LOGGER_JAVA.warning(Objects.requireNonNull(mensaje, "mensaje"));
    }

    public void logError(String mensaje) {
        LOGGER_JAVA.severe(Objects.requireNonNull(mensaje, "mensaje"));
    }
}
