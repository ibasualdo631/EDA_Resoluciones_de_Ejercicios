import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VerificadorParentesis {

    private static boolean estaBalanceada(String expresion) {
        if (expresion == null) {
            throw new IllegalArgumentException("La expresión no puede ser null.");
        }

        class Nodo {
            private final char dato;
            private final Nodo siguiente;

            private Nodo(char dato, Nodo siguiente) {
                this.dato = dato;
                this.siguiente = siguiente;
            }
        }

        Nodo tope = null;

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            if (caracter == '(') {
                // Cada apertura se apila encima de las aperturas que siguen pendientes.
                // El enlace al tope anterior conserva la pila como una lista enlazada.
                tope = new Nodo(caracter, tope);
            } else if (caracter == ')') {
                // El cierre debe emparejarse con la apertura pendiente más reciente.
                // Si no hay ninguna en el tope, este cierre no tiene pareja previa:
                // la expresión es inválida y no hace falta seguir recorriéndola.
                if (tope == null) {
                    return false;
                }

                if (tope.dato != '(') {
                    return false;
                }

                // Desapilar retira justamente esa apertura más reciente (LIFO).
                // Así, los cierres respetan el orden de anidamiento de los paréntesis.
                tope = tope.siguiente;
            }
            // Los demás caracteres no intervienen en la validación del balance.
        }

        // Si quedan nodos, representan aperturas que nunca encontraron un cierre.
        return tope == null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String expresion = lector.readLine();

        if (expresion == null) {
            System.err.println("Error: no se recibió una expresión.");
            return;
        }

        if (estaBalanceada(expresion)) {
            System.out.println("Paréntesis balanceados.");
        } else {
            System.out.println("Paréntesis no balanceados.");
        }
    }
}
