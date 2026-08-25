public class TestVacunas {
    public static void main(String[] args) {

        VacunaPatogenoIntegro v1 = new VacunaPatogenoIntegro(
                "Sinopharm", "China", "COVID-19", 2, "SARS-CoV-2 inactivado");

        VacunaSubunidadAntigenica v2 = new VacunaSubunidadAntigenica(
                "Novavax", "EE.UU.", "COVID-19", 2, 1, "Proteina recombinante con adyuvante");

        VacunaGenetica v3 = new VacunaGenetica(
                "Pfizer", "EE.UU.", "COVID-19", 2, -80.0, -60.0);

        System.out.println("--- toString() de cada subclase ---");
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);

        System.out.println();
        System.out.println("--- Getters heredados + getters propios ---");
        System.out.println("Marca heredada de Vacuna: " + v3.getMarca());
        System.out.println("Temperatura minima propia: " + v3.getTemperaturaMinima() + "C");

        System.out.println();
        System.out.println("--- Setters ---");
        v1.setNombreVirus("SARS-CoV-2 atenuado");
        v1.setCantidad_dosis(3); // setter heredado de Vacuna
        System.out.println(v1);

        System.out.println();
        System.out.println("--- Polimorfismo: arreglo del tipo de la superclase ---");
        Vacuna[] vacunas = { v1, v2, v3 };
        for (Vacuna v : vacunas) {
            // El tipo declarado es Vacuna, pero en tiempo de ejecucion se
            // invoca el toString() de la subclase correspondiente.
            System.out.println(v);
        }
    }
}
