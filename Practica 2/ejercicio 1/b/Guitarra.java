// Muestra que una clase puede REDEFINIR el metodo default si le hace falta,
// sin que eso obligue a las demas.
public class Guitarra extends InstrumentoDeCuerda {

    public String queEs() {
        return "Guitarra";
    }

    @Override
    public void afinar() {
        System.out.println("Afinando Guitarra: E-A-D-G-B-E");
    }
}
