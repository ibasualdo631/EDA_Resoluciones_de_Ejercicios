/*
 * Prompt: "Implementá PilaEnteros.java desde cero sin librerías nativas.
 * Incluí el bloque de comentarios obligatorio con el prompt y ajustes en la
 * cabecera, y un main con pruebas de inserción, extracción y desbordamiento."
 *
 * Ajustes aplicados:
 * - Se usa exclusivamente un arreglo int[] de capacidad fija como estructura.
 * - top representa el índice del último elemento ocupado e inicia en -1.
 * - Overflow se informa con IllegalStateException y Underflow con
 *   NoSuchElementException; las operaciones inválidas no modifican la pila.
 */
import java.util.NoSuchElementException;

public class PilaEnteros {

    private final int[] elementos;
    private int top;

    public PilaEnteros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }

        elementos = new int[capacidad];
        top = -1;
    }

    public void push(int valor) {
        if (isFull()) {
            throw new IllegalStateException("Overflow: la pila está llena.");
        }

        elementos[++top] = valor;
    }

    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow: la pila está vacía.");
        }

        return elementos[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow: la pila está vacía.");
        }

        return elementos[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == elementos.length - 1;
    }

    public int size() {
        return top + 1;
    }

    public static void main(String[] args) {
        probarEstadoInicialYCapacidadMinima();
        probarInsercionExtraccionYOrdenLifo();
        probarPeekSinExtraccion();
        probarDesbordamientoSinCambios();
        probarSubdesbordamientoSinCambios();
        probarReutilizacionTrasExtraccion();
        probarCapacidadInvalida();
        System.out.println("Todas las pruebas de PilaEnteros finalizaron correctamente.");
    }

    private static void probarEstadoInicialYCapacidadMinima() {
        PilaEnteros pila = new PilaEnteros(1);
        verificar(pila.isEmpty(), "Una pila nueva debe estar vacía.");
        verificar(!pila.isFull(), "Una pila nueva no debe estar llena.");
        verificar(pila.size() == 0, "El tamaño inicial debe ser cero.");

        pila.push(42);
        verificar(pila.isFull(), "La pila de capacidad uno debe llenarse al insertar.");
        verificar(pila.size() == 1, "El tamaño debe ser uno después de insertar.");
        verificar(pila.pop() == 42, "Debe extraerse el único elemento.");
        verificar(pila.isEmpty(), "La pila debe quedar vacía después de extraer.");
    }

    private static void probarInsercionExtraccionYOrdenLifo() {
        PilaEnteros pila = new PilaEnteros(3);
        pila.push(10);
        pila.push(20);
        pila.push(30);

        verificar(pila.size() == 3, "El tamaño debe reflejar las tres inserciones.");
        verificar(pila.pop() == 30, "La primera extracción debe devolver el último valor.");
        verificar(pila.pop() == 20, "La segunda extracción debe respetar el orden LIFO.");
        verificar(pila.pop() == 10, "La última extracción debe devolver el primer valor.");
        verificar(pila.isEmpty(), "La pila debe quedar vacía después de extraer todo.");
    }

    private static void probarPeekSinExtraccion() {
        PilaEnteros pila = new PilaEnteros(2);
        pila.push(7);
        pila.push(9);

        verificar(pila.peek() == 9, "peek debe devolver el elemento del tope.");
        verificar(pila.size() == 2, "peek no debe cambiar el tamaño.");
        verificar(pila.pop() == 9, "peek no debe quitar el elemento del tope.");
    }

    private static void probarDesbordamientoSinCambios() {
        PilaEnteros pila = new PilaEnteros(2);
        pila.push(4);
        pila.push(8);

        esperarExcepcion(IllegalStateException.class, () -> pila.push(12));
        verificar(pila.isFull(), "La pila debe seguir llena después del Overflow.");
        verificar(pila.size() == 2, "El Overflow no debe cambiar el tamaño.");
        verificar(pila.peek() == 8, "El Overflow no debe cambiar el elemento del tope.");
    }

    private static void probarSubdesbordamientoSinCambios() {
        PilaEnteros pila = new PilaEnteros(2);

        esperarExcepcion(NoSuchElementException.class, pila::pop);
        esperarExcepcion(NoSuchElementException.class, pila::peek);
        verificar(pila.isEmpty(), "El Underflow no debe cambiar el estado vacío.");
        verificar(pila.size() == 0, "El Underflow no debe cambiar el tamaño.");
    }

    private static void probarReutilizacionTrasExtraccion() {
        PilaEnteros pila = new PilaEnteros(2);
        pila.push(1);
        pila.push(2);
        verificar(pila.pop() == 2, "Debe extraerse el elemento superior.");
        pila.push(3);

        verificar(pila.isFull(), "La posición liberada debe poder reutilizarse.");
        verificar(pila.pop() == 3, "Debe extraerse el valor insertado en la posición liberada.");
        verificar(pila.pop() == 1, "El elemento restante debe conservarse.");
    }

    private static void probarCapacidadInvalida() {
        esperarExcepcion(IllegalArgumentException.class, () -> new PilaEnteros(0));
        esperarExcepcion(IllegalArgumentException.class, () -> new PilaEnteros(-1));
    }

    private static void verificar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
    }

    private static void esperarExcepcion(
            Class<? extends RuntimeException> tipoEsperado,
            Runnable operacion) {
        try {
            operacion.run();
        } catch (RuntimeException excepcion) {
            if (tipoEsperado.isInstance(excepcion)) {
                return;
            }
            throw new AssertionError(
                    "Se esperaba " + tipoEsperado.getSimpleName()
                            + " pero se recibió " + excepcion.getClass().getSimpleName(),
                    excepcion);
        }

        throw new AssertionError("Se esperaba " + tipoEsperado.getSimpleName() + ".");
    }
}
