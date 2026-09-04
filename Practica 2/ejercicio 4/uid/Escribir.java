import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Escribir {
    public static void main(String[] args) throws Exception {
        Pieza p = new Pieza("ABC-123", 7);
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("pieza.ser"))) {
            out.writeObject(p);
        }
        System.out.println("Escrito con v1: " + p);
    }
}
