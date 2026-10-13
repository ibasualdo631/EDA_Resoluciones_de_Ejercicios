/*
 * Prompt empleado:
 * "Implementá en Java una clase ColaEnteros que represente una cola FIFO de
 * enteros usando únicamente un arreglo de tamaño fijo, sin colecciones nativas.
 * Incluí un constructor que reciba la capacidad, los índices front y rear, y
 * métodos para encolar, desencolar, consultar si está vacía y consultar si está
 * llena. Usá front = 0 y rear = -1 para la cola vacía: front señala el próximo
 * elemento a extraer y rear señala el último elemento encolado. Al encolar,
 * verificá primero el desbordamiento, incrementá rear y guardá el valor. Al
 * desencolar, verificá primero el subdesbordamiento, devolvé arreglo[front] e
 * incrementá front. Si se extrae el último elemento, restablecé ambos índices
 * a front = 0 y rear = -1. Rechazá capacidades no positivas y señalá los
 * intentos de encolar en una cola llena o desencolar una vacía. Agregá un main
 * que pruebe inserciones y extracciones FIFO, vaciado y reutilización. Mantené
 * O(1) para encolar y desencolar, sin desplazar elementos."
 */
public class ColaEnteros {

    private final int[] elementos;
    private int front;
    private int rear;

    /**
     * Crea una cola lineal de capacidad fija.
     *
     * @param capacidad cantidad máxima de posiciones del arreglo
     * @throws IllegalArgumentException si la capacidad no es positiva
     */
    public ColaEnteros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }

        elementos = new int[capacidad];
        front = 0;
        rear = -1;
    }

    /**
     * Agrega un valor al final de la cola.
     *
     * @param valor entero que se agrega
     * @throws IllegalStateException si no quedan posiciones al final del arreglo
     */
    public void encolar(int valor) {
        if (estaLlena()) {
            throw new IllegalStateException("No se puede encolar: la cola está llena.");
        }

        rear++;
        elementos[rear] = valor;
    }

    /**
     * Retira y devuelve el elemento más antiguo de la cola.
     *
     * @return elemento que estaba en el frente
     * @throws IllegalStateException si la cola está vacía
     */
    public int desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("No se puede desencolar: la cola está vacía.");
        }

        int valor = elementos[front];
        front++;

        if (front > rear) {
            front = 0;
            rear = -1;
        }

        return valor;
    }

    /**
     * Indica si no hay elementos pendientes.
     *
     * @return {@code true} si la cola está vacía
     */
    public boolean estaVacia() {
        return rear == -1;
    }

    /**
     * Indica si el índice rear llegó al último espacio físico.
     *
     * @return {@code true} si no se puede agregar otro elemento
     */
    public boolean estaLlena() {
        return rear == elementos.length - 1;
    }

    public static void main(String[] args) {
        ColaEnteros cola = new ColaEnteros(3);

        comprobar(cola.estaVacia(), "La cola debe iniciar vacía.");
        cola.encolar(10);
        cola.encolar(20);
        cola.encolar(30);
        comprobar(cola.estaLlena(), "La cola debe estar llena tras tres inserciones.");

        comprobar(cola.desencolar() == 10, "El primer valor extraído debe ser 10.");
        comprobar(cola.desencolar() == 20, "El segundo valor extraído debe ser 20.");

        // En una cola lineal, los espacios anteriores a front no se reutilizan.
        comprobarLanzaIllegalState(
                () -> cola.encolar(40),
                "Debe rechazar la inserción si rear llegó al final del arreglo.");

        comprobar(cola.desencolar() == 30, "El tercer valor extraído debe ser 30.");
        comprobar(cola.estaVacia(), "La cola debe quedar vacía después de extraer todo.");

        // Al vaciarse se reinician los índices y se puede reutilizar el arreglo.
        cola.encolar(40);
        cola.encolar(50);
        comprobar(cola.desencolar() == 40, "La cola reutilizada debe conservar FIFO.");
        comprobar(cola.desencolar() == 50, "La cola reutilizada debe conservar FIFO.");
        comprobar(cola.estaVacia(), "La cola reutilizada debe poder vaciarse.");
        comprobarLanzaIllegalState(
                cola::desencolar,
                "Debe rechazar la extracción de una cola vacía.");

        comprobarLanzaIllegalArgument(
                () -> new ColaEnteros(0),
                "La capacidad cero debe rechazarse.");

        System.out.println("Pruebas de ColaEnteros completadas correctamente.");
    }

    private static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
    }

    private static void comprobarLanzaIllegalState(
            Runnable operacion, String mensaje) {
        try {
            operacion.run();
        } catch (IllegalStateException excepcion) {
            return;
        }
        throw new AssertionError(mensaje);
    }

    private static void comprobarLanzaIllegalArgument(
            Runnable operacion, String mensaje) {
        try {
            operacion.run();
        } catch (IllegalArgumentException excepcion) {
            return;
        }
        throw new AssertionError(mensaje);
    }
}
