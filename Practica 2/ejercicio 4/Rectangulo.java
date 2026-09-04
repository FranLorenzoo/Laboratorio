public class Rectangulo extends FiguraGeometrica {

    private static final long serialVersionUID = 1L;

    private final double base;
    private final double altura;

    public Rectangulo(double base, double altura) {
        super("Rectangulo(" + base + "x" + altura + ")");
        this.base = base;
        this.altura = altura;
    }

    /** Para que Cuadrado pueda dar su propio nombre. */
    protected Rectangulo(String nombre, double base, double altura) {
        super(nombre);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double area() {
        return base * altura;
    }

    @Override
    public double perimetro() {
        return 2 * (base + altura);
    }
}
