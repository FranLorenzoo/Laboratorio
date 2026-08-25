package laboratorio1;

import laboratorio.SuperClase;

/**
 * Esta clase NO es valida tal como la plantea el enunciado.
 *
 * OtraClase esta en laboratorio1 y NO es subclase de SuperClase, por lo que no
 * puede invocar su constructor protected mediante new.
 */
public class OtraClase {

    public OtraClase() {
    }

    public void getX() {

        // ---- Version del enunciado: NO COMPILA ----
        // new SuperClase();
        //   error: SuperClase() has protected access in SuperClase
        //
        // La invocacion directa con new de un constructor protected desde otro
        // paquete esta prohibida SIEMPRE, incluso si la clase fuera subclase.

        // ---- Solucion 1: usar una subclase, que si puede construirlo ----
        SuperClase obj = new SubClase();
        System.out.println("getX() creo un objeto vía SubClase: " + obj.getClass().getName());

        // ---- Solucion 2: subclase anonima ----
        // Es el unico uso de new permitido por el lenguaje sobre un
        // constructor protected de otro paquete.
        SuperClase anonima = new SuperClase() {
        };
        System.out.println("getX() creo una subclase anonima: " + anonima.getClass().getName());
    }
}
