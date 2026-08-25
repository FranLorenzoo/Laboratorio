

public class Vacuna{
    private String marca;
    private String pais_origen;
    private String enfermedad_cura;
    private int cantidad_dosis;

    public Vacuna(String marca, String pais_origen, String enfermedad_cura, int cantidad_dosis){
        this.marca = marca;
        this.pais_origen = pais_origen;
        this.enfermedad_cura = enfermedad_cura;
        this.cantidad_dosis = cantidad_dosis;
    }

    public String getMarca() {
        return marca;
    }

    public String getPais_origen() {
        return pais_origen;
    }

    public String getEnfermedad_cura() {
        return enfermedad_cura;
    }

    public int getCantidad_dosis() {
        return cantidad_dosis;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPais_origen(String pais_origen) {
        this.pais_origen = pais_origen;
    }

    public void setEnfermedad_cura(String enfermedad_cura) {
        this.enfermedad_cura = enfermedad_cura;
    }

    public void setCantidad_dosis(int cantidad_dosis) {
        this.cantidad_dosis = cantidad_dosis;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Vacuna [marca=").append(marca)
          .append(", pais de origen=").append(pais_origen)
          .append(", enfermedad que previene=").append(enfermedad_cura)
          .append(", cantidad de dosis=").append(cantidad_dosis)
          .append("]");
        return sb.toString();
    }
}