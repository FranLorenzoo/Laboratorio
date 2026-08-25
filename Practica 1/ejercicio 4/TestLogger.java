public class TestLogger {
    public static void main(String[] args) {

        System.out.println("--- Primer acceso: se crea la instancia ---");
        Logger log1 = Logger.getInstance();

        System.out.println();
        System.out.println("--- Los tres tipos de mensaje ---");
        log1.logInfo("Aplicacion iniciada correctamente");
        log1.logWarning("La conexion tardo mas de lo esperado");
        log1.logError("No se pudo abrir el archivo de configuracion");

        System.out.println();
        System.out.println("--- Segundo acceso: NO se crea otra instancia ---");
        Logger log2 = Logger.getInstance();
        // Notar que no vuelve a imprimirse el mensaje del constructor.

        System.out.println("log1 == log2 ?          " + (log1 == log2));
        System.out.println("log1.equals(log2) ?     " + log1.equals(log2));
        System.out.println("hashCode de log1:       " + log1.hashCode());
        System.out.println("hashCode de log2:       " + log2.hashCode());

        System.out.println();
        System.out.println("--- El estado es compartido ---");
        log2.logInfo("Mensaje escrito a traves de log2");
        System.out.println("Mensajes contados por log1: " + log1.getCantidadMensajes());
        System.out.println("Mensajes contados por log2: " + log2.getCantidadMensajes());

        System.out.println();
        System.out.println("--- Uso tipico desde otra clase, sin guardar la referencia ---");
        Servicio s = new Servicio();
        s.procesar();
        System.out.println("Total de mensajes registrados: " + Logger.getInstance().getCantidadMensajes());

        // La linea siguiente NO compila: el constructor es private.
        // Logger log3 = new Logger();
        //   error: Logger() has private access in Logger
    }
}

/** Clase auxiliar: muestra como cualquier parte del programa accede al mismo Logger. */
class Servicio {
    public void procesar() {
        Logger.getInstance().logInfo("Servicio.procesar() en ejecucion");
        Logger.getInstance().logWarning("Servicio.procesar() termino con advertencias");
    }
}
