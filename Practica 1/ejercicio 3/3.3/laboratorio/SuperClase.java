package laboratorio;

/**
 * Caso "constructores protegidos" del ejercicio 3.3.
 *
 * Al ser protected, el constructor puede invocarse:
 *   - desde cualquier clase del paquete laboratorio;
 *   - desde una subclase de otro paquete, pero SOLO a traves de super().
 */
public class SuperClase {

    protected SuperClase() {
        System.out.println("Constructor protegido de SuperClase ejecutado");
    }
}
