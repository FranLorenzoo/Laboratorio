// Punto g) Version inmutable de Vacuna.
// Se elige "final class" con todos los campos private final, solo constructor
// y getters (sin setters). Es la forma clasica de garantizar inmutabilidad
// en Java: una vez cargados los datos, no pueden modificarse.
//   - final class     -> nadie puede heredarla y romper la inmutabilidad
//   - private final   -> los campos no se pueden reasignar
//   - sin setters     -> no hay forma de mutar el estado
public final class VacunaInmutable {
    private final String marca;
    private final String paisOrigen;
    private final String enfermedadCura;
    private final int cantidadDosis;

    public VacunaInmutable(String marca, String paisOrigen,
                           String enfermedadCura, int cantidadDosis) {
        this.marca = marca;
        this.paisOrigen = paisOrigen;
        this.enfermedadCura = enfermedadCura;
        this.cantidadDosis = cantidadDosis;
    }

    public String getMarca()           { return marca; }
    public String getPaisOrigen()      { return paisOrigen; }
    public String getEnfermedadCura()  { return enfermedadCura; }
    public int    getCantidadDosis()   { return cantidadDosis; }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("VacunaInmutable [marca=").append(marca)
          .append(", pais de origen=").append(paisOrigen)
          .append(", enfermedad que previene=").append(enfermedadCura)
          .append(", cantidad de dosis=").append(cantidadDosis)
          .append("]");
        return sb.toString();
    }
}
