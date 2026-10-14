/**
 * Invierte una palabra utilizando una pila enlazada.
 *
 * La pila aplica la política LIFO (Last In, First Out): el último carácter
 * que se apila queda en la cima y es el primero en desapilarse. Por eso, al
 * apilar los caracteres de izquierda a derecha y luego extraerlos, se obtiene
 * su orden inverso. Por ejemplo, "algoritmo" se convierte en "omtirogla".
 */
import java.util.Scanner;

public class InvertirPila {

    private static class Nodo {
        private final char dato;
        private final Nodo siguiente;

        private Nodo(char dato, Nodo siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private static class PilaEnlazada {
        private Nodo cima;

        private void apilar(char caracter) {
            cima = new Nodo(caracter, cima);
        }

        private char desapilar() {
            if (estaVacia()) {
                throw new IllegalStateException("No se puede desapilar una pila vacía.");
            }

            char caracter = cima.dato;
            cima = cima.siguiente;
            return caracter;
        }

        private boolean estaVacia() {
            return cima == null;
        }
    }

    public static String invertir(String palabra) {
        if (palabra == null) {
            throw new IllegalArgumentException("La palabra no puede ser null.");
        }

        PilaEnlazada pila = new PilaEnlazada();
        for (int i = 0; i < palabra.length(); i++) {
            pila.apilar(palabra.charAt(i));
        }

        StringBuilder resultado = new StringBuilder(palabra.length());
        while (!pila.estaVacia()) {
            resultado.append(pila.desapilar());
        }

        return resultado.toString();
    }

    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            String palabra = entrada.hasNextLine() ? entrada.nextLine() : "";
            System.out.println(invertir(palabra));
        }
    }
}
