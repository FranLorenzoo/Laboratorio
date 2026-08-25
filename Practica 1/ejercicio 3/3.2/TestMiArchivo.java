import laboratorio.MiArchivo;

public class TestMiArchivo {
    public static void main(String[] args) {

        System.out.println("--- Constructor con ruta explicita ---");
        MiArchivo a1 = new MiArchivo("datos/vacunas.txt");
        a1.mostrarDatos();

        System.out.println();
        System.out.println("--- Constructor sin argumentos ---");
        MiArchivo a2 = new MiArchivo();
        a2.mostrarDatos();

        System.out.println();
        System.out.println("--- Metodos heredados de java.io.File ---");
        System.out.println("a1 es un File? " + (a1 instanceof java.io.File));
        System.out.println("Nombre del padre: " + a1.getParent());
    }
}
