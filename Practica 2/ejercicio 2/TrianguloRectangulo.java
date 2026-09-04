public class TrianguloRectangulo extends FiguraGeometrica {

    private final double base;
    private final double altura;

    public TrianguloRectangulo(double base, double altura) {
        super("Triangulo(" + base + "x" + altura + ")");
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double area() {
        return (base * altura) / 2;
    }

    @Override
    public double perimetro() {
        return base + altura + Math.hypot(base, altura);
    }
}
