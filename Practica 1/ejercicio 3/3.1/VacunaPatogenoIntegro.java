public class VacunaPatogenoIntegro extends Vacuna {

    // Variable de instancia propia de la subclase
    private String nombreVirus; // virus patogeno inactivado o atenuado

    // Punto b: el constructor recibe los parametros propios de esta clase
    // MAS los que necesita la superclase, porque Vacuna no tiene
    // constructor sin argumentos (ver respuesta del punto c).
    public VacunaPatogenoIntegro(String marca, String pais_origen, String enfermedad_cura,
                                 int cantidad_dosis, String nombreVirus) {
        super(marca, pais_origen, enfermedad_cura, cantidad_dosis);
        this.nombreVirus = nombreVirus;
    }

    // Punto a: getter y setter de la variable propia
    public String getNombreVirus() {
        return nombreVirus;
    }

    public void setNombreVirus(String nombreVirus) {
        this.nombreVirus = nombreVirus;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("VacunaPatogenoIntegro -> ").append(super.toString())
          .append(" [virus patogeno=").append(nombreVirus).append("]");
        return sb.toString();
    }
}
