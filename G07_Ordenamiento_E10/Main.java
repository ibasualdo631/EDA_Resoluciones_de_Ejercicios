import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static class Jugador {
        private final String nombre;
        private final int puntaje;

        private Jugador(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }
    }

    public static void main(String[] args) {
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Ana", 1200));
        jugadores.add(new Jugador("Pedro", 900));
        jugadores.add(new Jugador("Lucia", 1500));

        jugadores.sort(Comparator.comparingInt((Jugador jugador) -> jugador.puntaje).reversed());

        System.out.println("Ranking:");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            System.out.println((i + 1) + ". " + jugador.nombre + " - " + jugador.puntaje);
        }
    }
}
