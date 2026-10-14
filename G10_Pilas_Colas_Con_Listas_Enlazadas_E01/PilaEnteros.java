import java.io.PrintStream;
import java.util.Objects;

/**
 * Pila de enteros implementada con una lista simplemente enlazada.
 *
 * {@code head} representa el tope porque insertar o retirar el primer nodo
 * solo requiere cambiar un numero constante de referencias, sin recorrer la
 * lista; por eso {@code push} y {@code pop} son O(1).
 *
 * Ajustes: {@code pop} y {@code peek} lanzan IllegalStateException cuando la
 * pila esta vacia; {@code imprimir} recibe el flujo de salida como parametro
 * para desacoplar la estructura de la consola y facilitar las pruebas.
 */
public class PilaEnteros {
    private static final String MENSAJE_PILA_VACIA = "Pila vacia";

    private Nodo head;

    private static final class Nodo {
        private final int valor;
        private final Nodo siguiente;

        private Nodo(int valor, Nodo siguiente) {
            this.valor = valor;
            this.siguiente = siguiente;
        }
    }

    /**
     * Agrega un valor al tope de la pila.
     *
     * @param valor entero que se agrega
     */
    public void push(int valor) {
        head = new Nodo(valor, head);
    }

    /**
     * Retira y devuelve el valor del tope.
     *
     * @return valor que estaba en el tope
     * @throws IllegalStateException si la pila esta vacia
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede desapilar una pila vacia");
        }

        int valor = head.valor;
        head = head.siguiente;
        return valor;
    }

    /**
     * Devuelve el valor del tope sin retirarlo.
     *
     * @return valor del tope
     * @throws IllegalStateException si la pila esta vacia
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("No hay tope: la pila esta vacia");
        }

        return head.valor;
    }

    /**
     * Indica si la pila no contiene elementos.
     *
     * @return {@code true} si esta vacia; de lo contrario, {@code false}
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Comprueba si la pila contiene el valor indicado sin modificarla.
     *
     * @param valor valor que se busca
     * @return {@code true} si el valor esta presente
     */
    public boolean contains(int valor) {
        for (Nodo actual = head; actual != null; actual = actual.siguiente) {
            if (actual.valor == valor) {
                return true;
            }
        }
        return false;
    }

    /**
     * Imprime los elementos desde el tope hasta la base sin modificar la pila.
     *
     * @param salida flujo en el que se imprimen los elementos
     * @throws NullPointerException si {@code salida} es {@code null}
     */
    public void imprimir(PrintStream salida) {
        Objects.requireNonNull(salida, "El flujo de salida no puede ser null");

        if (isEmpty()) {
            salida.println(MENSAJE_PILA_VACIA);
            return;
        }

        salida.print("Tope -> ");
        for (Nodo actual = head; actual != null; actual = actual.siguiente) {
            salida.print(actual.valor);
            if (actual.siguiente != null) {
                salida.print(" -> ");
            }
        }
        salida.println(" -> Base");
    }
}
