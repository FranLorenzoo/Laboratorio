public class Circulo extends FiguraGeometrica {

    private static final long serialVersionUID = 1L;

    private final double radio;

    public Circulo(double radio) {
        super("Circulo(r=" + radio + ")");
        this.radio = radio;
    }

    @Override
    public double area() {
        return Math.PI * radio * radio;
    }

    @Override
    public double perimetro() {
        return 2 * Math.PI * radio;
    }
}
