public class VacunaGenetica extends Vacuna {

    // Variables de instancia propias de la subclase
    private double temperaturaMinima; // en grados Celsius
    private double temperaturaMaxima; // en grados Celsius

    // Punto b
    public VacunaGenetica(String marca, String pais_origen, String enfermedad_cura,
                          int cantidad_dosis, double temperaturaMinima, double temperaturaMaxima) {
        super(marca, pais_origen, enfermedad_cura, cantidad_dosis);
        this.temperaturaMinima = temperaturaMinima;
        this.temperaturaMaxima = temperaturaMaxima;
    }

    // Punto a: getters y setters
    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("VacunaGenetica -> ").append(super.toString())
          .append(" [temperatura minima=").append(temperaturaMinima).append("C")
          .append(", temperatura maxima=").append(temperaturaMaxima).append("C]");
        return sb.toString();
    }
}
