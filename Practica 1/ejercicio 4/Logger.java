import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ejercicio 4: implementacion del patron Singleton.
 *
 * Garantiza que exista una unica instancia de Logger durante toda la
 * ejecucion de la aplicacion. Las tres piezas del patron son:
 *
 *   1. el atributo estatico que guarda la unica instancia;
 *   2. el constructor privado, que impide hacer new desde afuera;
 *   3. el metodo estatico getInstance(), unico punto de acceso.
 */
public class Logger {

    // 1. La unica instancia. Es static: pertenece a la clase, no a los objetos.
    private static Logger instancia;

    // Estado propio del logger, compartido por toda la aplicacion.
    private int cantidadMensajes;

    private static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    // 2. Constructor PRIVADO: nadie fuera de esta clase puede escribir
    //    new Logger(). Es la clave del patron.
    private Logger() {
        this.cantidadMensajes = 0;
        System.out.println(">> Se creo la unica instancia de Logger <<");
    }

    // 3. Unico punto de acceso a la instancia.
    //    Es static porque debe poder llamarse sin tener todavia un objeto.
    public static Logger getInstance() {
        if (instancia == null) {        // solo la primera vez
            instancia = new Logger();   // aca si se puede: estamos dentro de Logger
        }
        return instancia;
    }

    // --- Metodos de registro pedidos por el enunciado ---

    public void logInfo(String mensaje) {
        registrar("INFO", mensaje);
    }

    public void logWarning(String mensaje) {
        registrar("WARNING", mensaje);
    }

    public void logError(String mensaje) {
        registrar("ERROR", mensaje);
    }

    /**
     * Logica comun de los tres metodos anteriores. Es private porque es un
     * detalle interno: desde afuera solo se usan logInfo/logWarning/logError.
     */
    private void registrar(String nivel, String mensaje) {
        cantidadMensajes++;
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(LocalDateTime.now().format(FORMATO_HORA)).append("]")
          .append(" [").append(nivel).append("] ")
          .append(mensaje);
        System.out.println(sb.toString());
    }

    /** Permite comprobar que el estado se comparte entre todas las referencias. */
    public int getCantidadMensajes() {
        return cantidadMensajes;
    }
}
