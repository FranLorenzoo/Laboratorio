package laboratorio1;

import laboratorio.SuperClase; // sin este import la clase no compila

/**
 * Esta clase SI es valida: el super() implicito puede invocar al constructor
 * protected de SuperClase aunque esten en paquetes distintos, porque el acceso
 * ocurre a traves de una invocacion de constructor de superclase.
 */
public class SubClase extends SuperClase {

    public SubClase() {
        // super();  <- llamada implicita que agrega el compilador. Es valida.
        System.out.println("Constructor de SubClase ejecutado");
    }
}
