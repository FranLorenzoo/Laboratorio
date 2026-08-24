public class TestVacuna {
    public static void main(String[] args) {
        Vacuna[] vacunas = new Vacuna[5];
        vacunas[0] = new Vacuna("Pfizer",      "EE.UU.",      "COVID-19",      2);
        vacunas[1] = new Vacuna("Sputnik V",   "Rusia",       "COVID-19",      2);
        vacunas[2] = new Vacuna("Sinopharm",   "China",       "COVID-19",      2);
        vacunas[3] = new Vacuna("AstraZeneca", "Reino Unido", "COVID-19",      2);
        vacunas[4] = new Vacuna("Sabin",       "EE.UU.",      "Poliomielitis", 3);

        System.out.println("--- Punto b: recorrido del arreglo ---");
        for (Vacuna v : vacunas) {
            System.out.println(v); // invoca toString() implicitamente
        }

        System.out.println();
        System.out.println("--- Punto d: comparacion ---");
        Vacuna otra = new Vacuna("Pfizer", "EE.UU.", "COVID-19", 2);
        System.out.println("vacunas[0] == otra?      " + (vacunas[0] == otra));
        System.out.println("vacunas[0].equals(otra)? " + vacunas[0].equals(otra));
        // El metodo de Object usado para comparar por contenido es equals(Object).
        // Como no lo redefinimos, se comporta como == (compara referencias).
    }
}

/*
 * ---------------------------------------------------------------------------
 * Respuestas teoricas
 * ---------------------------------------------------------------------------
 * c) Al comentar toString() en Vacuna, System.out.println(v) usa la
 *    implementacion heredada de Object, que devuelve algo como
 *    "Vacuna@1540e19d" (nombre de la clase + '@' + hashCode en hexa).
 *    Diferencia con b): en b) se ven los datos legibles de cada vacuna;
 *    en c) solo se ve la referencia con el hash.
 *
 * d) El metodo de Object usado para la comparacion por contenido es
 *    equals(Object o). Por defecto compara referencias (igual que ==);
 *    para comparar por contenido hay que sobre-escribirlo.
 *
 * e) La variable de entorno CLASSPATH le indica a la JVM en que
 *    directorios y archivos .jar debe buscar las clases compiladas
 *    (.class). Al ejecutar fuera del IDE, si las clases no estan en el
 *    directorio actual, hay que agregarlas al CLASSPATH o pasarlas con
 *    la opcion -cp / -classpath del comando java.
 *
 * f) La clase que contiene el metodo main se especifica en el archivo
 *    META-INF/MANIFEST.MF del JAR, mediante el atributo:
 *         Main-Class: TestVacuna
 *    Luego se ejecuta con:  java -jar vacuna.jar
 * ---------------------------------------------------------------------------
 */
