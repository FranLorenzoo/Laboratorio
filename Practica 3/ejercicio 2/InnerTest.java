package practica3;

import java.util.Scanner;

public class InnerTest {
    public static void main(String[] args) {
        double radio;

        if (args.length > 0) {
            radio = Double.parseDouble(args[0]);
        } else {
            try (Scanner entrada = new Scanner(System.in)) {
                System.out.print("Ingrese el radio: ");
                radio = entrada.nextDouble();
            }
        }

        InnerStatic.Circulo circulo = new InnerStatic.Circulo(radio);
        System.out.printf("Radio: %.2f%n", circulo.getRadio());
        System.out.printf("Area: %.4f%n", circulo.getArea());
        System.out.printf("Longitud: %.4f%n", circulo.getLongitudCircunferencia());
    }
}
