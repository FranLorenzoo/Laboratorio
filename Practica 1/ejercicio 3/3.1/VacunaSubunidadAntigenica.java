public class VacunaSubunidadAntigenica extends Vacuna {

    // Variables de instancia propias de la subclase
    private int cantidadAntigenos;
    private String tipoProceso;

    // Punto b
    public VacunaSubunidadAntigenica(String marca, String pais_origen, String enfermedad_cura,
                                     int cantidad_dosis, int cantidadAntigenos, String tipoProceso) {
        super(marca, pais_origen, enfermedad_cura, cantidad_dosis);
        this.cantidadAntigenos = cantidadAntigenos;
        this.tipoProceso = tipoProceso;
    }

    // Punto a: getters y setters
    public int getCantidadAntigenos() {
        return cantidadAntigenos;
    }

    public void setCantidadAntigenos(int cantidadAntigenos) {
        this.cantidadAntigenos = cantidadAntigenos;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("VacunaSubunidadAntigenica -> ").append(super.toString())
          .append(" [cantidad de antigenos=").append(cantidadAntigenos)
          .append(", tipo de proceso=").append(tipoProceso).append("]");
        return sb.toString();
    }
}
