/*
 * Prompt inicial:
 * Implementa una clase ColaEnteros que represente una cola FIFO de enteros
 * utilizando un arreglo de capacidad fija. Debe ofrecer enqueue(valor),
 * dequeue(), front(), isEmpty(), isFull() y size(). Utiliza los indices
 * front y rear para senalar, respectivamente, el primer elemento pendiente de
 * eliminacion y el ultimo elemento agregado. Explica su inicializacion y como
 * cambian al insertar y eliminar, manteniendo el estado correcto al vaciar y
 * volver a insertar.
 *
 * Implementa una cola lineal simple: no la conviertas en circular ni desplaces
 * elementos para reutilizar posiciones liberadas. Rechaza una capacidad no
 * positiva; informa claramente los intentos de consultar o eliminar cuando
 * esta vacia y de insertar cuando esta llena, sin usar valores centinela.
 * Mantiene las operaciones de la cola en O(1) y no utilices colecciones nativas.
 * Incluye un main que pruebe inserciones y extracciones secuenciales.
 */
public class ColaEnteros {

    private final int[] elementos;
    private final int capacidad;
    private int frontIndex;
    private int rearIndex;
    private int cantidad;

    public ColaEnteros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }

        this.capacidad = capacidad;
        this.elementos = new int[capacidad];
        this.frontIndex = 0;
        this.rearIndex = -1;
        this.cantidad = 0;
    }

    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("No se puede insertar: la cola esta llena.");
        }

        if (isEmpty()) {
            frontIndex = 0;
            rearIndex = 0;
        } else {
            rearIndex++;
        }

        elementos[rearIndex] = valor;
        cantidad++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede eliminar: la cola esta vacia.");
        }

        int valor = elementos[frontIndex];
        cantidad--;

        if (isEmpty()) {
            frontIndex = 0;
            rearIndex = -1;
        } else {
            frontIndex++;
        }

        return valor;
    }

    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede consultar: la cola esta vacia.");
        }

        return elementos[frontIndex];
    }

    public boolean isEmpty() {
        return cantidad == 0;
    }

    public boolean isFull() {
        return cantidad == capacidad
                || (!isEmpty() && rearIndex == capacidad - 1);
    }

    public int size() {
        return cantidad;
    }

    public static void main(String[] args) {
        ColaEnteros cola = new ColaEnteros(5);
        int[] valores = {10, 20, 30, 40, 50};

        System.out.println("Insertando elementos:");
        for (int valor : valores) {
            cola.enqueue(valor);
            System.out.println("enqueue(" + valor + ") - size: " + cola.size());
        }

        System.out.println("Frente: " + cola.front());
        System.out.println("Cola llena: " + cola.isFull());
        System.out.println("Extrayendo elementos:");
        while (!cola.isEmpty()) {
            System.out.println("dequeue() -> " + cola.dequeue()
                    + " - size: " + cola.size());
        }

        System.out.println("Cola vacia: " + cola.isEmpty());

        cola.enqueue(99);
        System.out.println("Reinsercion luego de vaciar: " + cola.dequeue());
    }
}
