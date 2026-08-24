public class TestVacunaInmutable {
    public static void main(String[] args) {
        VacunaInmutable[] vacunas = {
            new VacunaInmutable("Pfizer",      "EE.UU.",      "COVID-19",      2),
            new VacunaInmutable("Sputnik V",   "Rusia",       "COVID-19",      2),
            new VacunaInmutable("Sinopharm",   "China",       "COVID-19",      2),
            new VacunaInmutable("AstraZeneca", "Reino Unido", "COVID-19",      2),
            new VacunaInmutable("Sabin",       "EE.UU.",      "Poliomielitis", 3)
        };

        for (VacunaInmutable v : vacunas) {
            System.out.println(v);
        }

        // Intento de modificar la cantidad de dosis: NO COMPILA.
        // Descomentar cualquiera de estas lineas para verificar el error:
        //
        //   vacunas[0].setCantidadDosis(5);
        //       -> error: cannot find symbol (no existe setter)
        //
        //   vacunas[0].cantidadDosis = 5;
        //       -> error: cantidadDosis has private access in VacunaInmutable
        //          y ademas es final (cannot assign a value to final variable)
    }
}
