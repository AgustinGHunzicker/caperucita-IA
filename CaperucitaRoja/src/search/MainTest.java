package search;

public class MainTest {
    public static void main(String[] args) {

        EstadoAmbiente e = new EstadoAmbiente();
        e.getEscenario().generarEscenario(2);

        System.out.println(e.getEscenario());
        System.out.println(e.getPosicionesFlores());
    }
}
