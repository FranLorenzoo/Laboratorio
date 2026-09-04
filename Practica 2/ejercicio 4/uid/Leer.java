import java.io.FileInputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

public class Leer {
    public static void main(String[] args) throws Exception {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("pieza.ser"))) {
            Pieza p = (Pieza) in.readObject();
            System.out.println("LEIDO OK: " + p);
        } catch (InvalidClassException e) {
            System.out.println("FALLO: InvalidClassException");
            System.out.println("  " + e.getMessage());
        }
    }
}
