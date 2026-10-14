/*
 * FIFO se mantiene insertando cada elemento al final y retirándolo del frente.
 * El parámetro T solo define el tipo de dato almacenado; no interviene en los
 * enlaces ni altera el orden de llegada y salida.
 */
import java.util.Objects;

public final class ColaEnlazada<T> {
    private static final class Nodo<T> {
        private final T valor;
        private Nodo<T> siguiente;

        private Nodo(T valor) {
            this.valor = valor;
        }
    }

    private Nodo<T> head;
    private Nodo<T> tail;

    public void encolar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);

        if (estaVacia()) {
            head = nuevo;
            tail = nuevo;
            return;
        }

        tail.siguiente = nuevo;
        tail = nuevo;
    }

    public T desencolar() {
        asegurarNoVacia("desencolar");

        T valorFrente = head.valor;
        head = head.siguiente;
        if (head == null) {
            tail = null;
        }

        return valorFrente;
    }

    public T frente() {
        asegurarNoVacia("consultar el frente");
        return head.valor;
    }

    public boolean estaVacia() {
        return head == null;
    }

    public boolean contiene(T valor) {
        Nodo<T> actual = head;
        while (actual != null) {
            if (Objects.equals(actual.valor, valor)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void imprimir() {
        System.out.print("[");
        Nodo<T> actual = head;

        while (actual != null) {
            if (actual != head) {
                System.out.print(", ");
            }
            System.out.print(actual.valor);
            actual = actual.siguiente;
        }

        System.out.println("]");
    }

    private void asegurarNoVacia(String operacion) {
        if (estaVacia()) {
            throw new IllegalStateException(
                    "No se puede " + operacion + ": la cola está vacía.");
        }
    }
}
