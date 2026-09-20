package practica3;

import java.util.Arrays;
import java.util.Comparator;

public class Estudiante {
    private final String apellido;
    private final String nombre;
    private final int edad;
    private final int legajo;
    private final int materiasAprobadas;

    public Estudiante(String apellido, String nombre, int edad,
                      int legajo, int materiasAprobadas) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.edad = edad;
        this.legajo = legajo;
        this.materiasAprobadas = materiasAprobadas;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getLegajo() {
        return legajo;
    }

    public int getMateriasAprobadas() {
        return materiasAprobadas;
    }

    @Override
    public String toString() {
        return "%s %s - edad=%d, legajo=%d, aprobadas=%d".formatted(
                nombre, apellido, edad, legajo, materiasAprobadas);
    }

    public static void main(String[] args) {
        Estudiante[] estudiantes = {
            new Estudiante("Gomez", "Ana", 22, 14503, 14),
            new Estudiante("Perez", "Bruno", 25, 13220, 21),
            new Estudiante("Alvarez", "Carla", 20, 15780, 8),
            new Estudiante("Suarez", "Ana", 24, 14010, 14),
            new Estudiante("Diaz", "Diego", 22, 15100, 10)
        };

        ordenarEImprimir(estudiantes, "Materias aprobadas (ascendente)",
                new Comparator<Estudiante>() {
                    @Override
                    public int compare(Estudiante uno, Estudiante otro) {
                        return Integer.compare(uno.materiasAprobadas, otro.materiasAprobadas);
                    }
                });

        ordenarEImprimir(estudiantes, "Edad (descendente)",
                new Comparator<Estudiante>() {
                    @Override
                    public int compare(Estudiante uno, Estudiante otro) {
                        return Integer.compare(otro.edad, uno.edad);
                    }
                });

        ordenarEImprimir(estudiantes, "Legajo (ascendente)",
                new Comparator<Estudiante>() {
                    @Override
                    public int compare(Estudiante uno, Estudiante otro) {
                        return Integer.compare(uno.legajo, otro.legajo);
                    }
                });

        ordenarEImprimir(estudiantes, "Nombre y apellido (descendente)",
                new Comparator<Estudiante>() {
                    @Override
                    public int compare(Estudiante uno, Estudiante otro) {
                        int porNombre = otro.nombre.compareToIgnoreCase(uno.nombre);
                        if (porNombre != 0) {
                            return porNombre;
                        }
                        return otro.apellido.compareToIgnoreCase(uno.apellido);
                    }
                });
    }

    private static void ordenarEImprimir(Estudiante[] originales, String titulo,
                                         Comparator<Estudiante> criterio) {
        Estudiante[] copia = Arrays.copyOf(originales, originales.length);
        Arrays.sort(copia, criterio);

        System.out.println("\n--- " + titulo + " ---");
        for (Estudiante estudiante : copia) {
            System.out.println(estudiante);
        }
    }
}
