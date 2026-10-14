/**
 * Cola FIFO de enteros implementada con una lista simplemente enlazada.
 *
 * head apunta al frente para consultar y desencolar en O(1), mientras que
 * tail apunta al final para encolar en O(1). Si solo se mantuviera head,
 * encolar exigiría recorrer toda la lista para hallar el último nodo:
 * O(n) por inserción y hasta O(n^2) al encolar n elementos sucesivamente.
 */
public final class ColaEnlazada {
    private static final class Nodo {
        private final int valor;
        private Nodo siguiente;

        private Nodo(int valor) {
            this.valor = valor;
        }
    }

    private Nodo head;
    private Nodo tail;

    public ColaEnlazada() {
        head = null;
        tail = null;
    }

    public void encolar(int valor) {
        Nodo nuevo = new Nodo(valor);

        if (estaVacia()) {
            head = nuevo;
            tail = nuevo;
            return;
        }

        tail.siguiente = nuevo;
        tail = nuevo;
    }

    public int desencolar() {
        asegurarNoVacia("desencolar");

        int valorFrente = head.valor;
        head = head.siguiente;
        if (head == null) {
            tail = null;
        }

        return valorFrente;
    }

    public int frente() {
        asegurarNoVacia("consultar el frente");
        return head.valor;
    }

    public boolean estaVacia() {
        return head == null;
    }

    public boolean contiene(int valor) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.valor == valor) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void imprimir() {
        System.out.print("[");
        Nodo actual = head;

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
