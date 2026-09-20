package practica3;

import static java.lang.Math.pow;

public class InnerStatic {
    static final double PI = Math.PI;

    /** Clase anidada estática: no necesita una instancia de InnerStatic. */
    public static class Circulo {
        private final double radio;

        public Circulo(double radio) {
            if (!Double.isFinite(radio) || radio <= 0) {
                throw new IllegalArgumentException("El radio debe ser un numero positivo");
            }
            this.radio = radio;
        }

        public double getArea() {
            var a = PI * pow(radio, 2);
            return a;
        }

        public double getLongitudCircunferencia() {
            var l = 2 * PI * radio;
            return l;
        }

        public double getRadio() {
            return radio;
        }
    }
}
