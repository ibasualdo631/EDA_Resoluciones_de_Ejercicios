/*
 * Prompt:
 * Implementá ColaCircular.java como una cola circular de capacidad fija
 * utilizando un arreglo. Define la aritmética modular de front y rear, los
 * contratos de inserción y extracción, y garantiza O(1) estricta para las
 * operaciones sobre la cola. Incluye un main que demuestre el rebote de los
 * punteros al principio del arreglo y la reutilización de celdas.
 *
 * Ajustes aplicados:
 * - front señala el próximo elemento a extraer; rear, la próxima celda libre.
 * - count distingue cola vacía (count == 0) de cola llena (count == capacidad).
 * - La capacidad es fija; encolar y desencolar no recorren ni desplazan el
 *   arreglo y avanzan sus índices con módulo.
 * - Insertar en una cola llena lanza IllegalStateException y extraer o
 *   consultar una cola vacía lanza NoSuchElementException; los errores no
 *   modifican el estado.
 */
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Random;

public class ColaCircular {

    private final int[] elementos;
    private int front;
    private int rear;
    private int count;

    public ColaCircular(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }

        elementos = new int[capacidad];
        front = 0;
        rear = 0;
        count = 0;
    }

    public void encolar(int valor) {
        if (estaLlena()) {
            throw new IllegalStateException("Overflow: la cola está llena.");
        }

        elementos[rear] = valor;
        rear = (rear + 1) % elementos.length;
        count++;
    }

    public int desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("Underflow: la cola está vacía.");
        }

        int valor = elementos[front];
        front = (front + 1) % elementos.length;
        count--;
        return valor;
    }

    public int consultarFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("Underflow: la cola está vacía.");
        }

        return elementos[front];
    }

    public boolean estaVacia() {
        return count == 0;
    }

    public boolean estaLlena() {
        return count == elementos.length;
    }

    public int cantidadElementos() {
        return count;
    }

    public static void main(String[] args) {
        demostrarReboteYReutilizacion();
        probarErroresSinCambios();
        probarCapacidadUnitaria();
        probarEstresDeReutilizacionCircular();
        System.out.println("Todas las pruebas de ColaCircular finalizaron correctamente.");
    }

    private static void demostrarReboteYReutilizacion() {
        ColaCircular cola = new ColaCircular(5);
        for (int valor = 10; valor <= 50; valor += 10) {
            cola.encolar(valor);
        }
        imprimirEstado("Después de llenar la cola (rear vuelve a 0)", cola);
        verificar(cola.rear == 0, "rear debe volver a cero al completar una vuelta.");

        verificar(cola.desencolar() == 10, "Debe salir primero el valor 10.");
        verificar(cola.desencolar() == 20, "Debe salir luego el valor 20.");
        verificar(cola.desencolar() == 30, "Debe salir luego el valor 30.");
        imprimirEstado("Después de liberar las celdas 0, 1 y 2", cola);

        cola.encolar(60);
        cola.encolar(70);
        imprimirEstado("Después de reutilizar las celdas 0 y 1", cola);
        verificar(cola.rear == 2, "rear debe avanzar circularmente tras reutilizar celdas.");

        verificar(cola.desencolar() == 40, "El orden FIFO debe conservar el valor 40.");
        verificar(cola.desencolar() == 50, "El orden FIFO debe conservar el valor 50.");
        verificar(cola.desencolar() == 60, "La celda reutilizada debe contener 60.");
        verificar(cola.desencolar() == 70, "La última inserción debe salir al final.");
        verificar(cola.estaVacia(), "La cola debe quedar vacía.");
        verificar(cola.front == 2, "front debe haber rebotado al principio del arreglo.");
        imprimirEstado("Después de extraer todo (front también dio la vuelta)", cola);
    }

    private static void probarErroresSinCambios() {
        ColaCircular cola = new ColaCircular(2);
        esperarExcepcion(NoSuchElementException.class, cola::desencolar);
        esperarExcepcion(NoSuchElementException.class, cola::consultarFrente);
        verificar(cola.estaVacia(), "Una extracción inválida no debe alterar la cola vacía.");

        cola.encolar(1);
        cola.encolar(2);
        esperarExcepcion(IllegalStateException.class, () -> cola.encolar(3));
        verificar(cola.estaLlena(), "Una inserción inválida no debe alterar la cola llena.");
        verificar(cola.desencolar() == 1, "El overflow no debe sobrescribir el frente.");
        verificar(cola.desencolar() == 2, "El overflow no debe cambiar el orden restante.");

        esperarExcepcion(IllegalArgumentException.class, () -> new ColaCircular(0));
        esperarExcepcion(IllegalArgumentException.class, () -> new ColaCircular(-1));
    }

    private static void probarCapacidadUnitaria() {
        ColaCircular cola = new ColaCircular(1);
        cola.encolar(42);
        verificar(cola.estaLlena(), "La cola unitaria debe llenarse al insertar.");
        verificar(cola.consultarFrente() == 42, "La consulta no debe quitar el elemento.");
        verificar(cola.desencolar() == 42, "Debe extraerse el único elemento.");
        verificar(cola.estaVacia(), "La cola unitaria debe quedar vacía.");
        cola.encolar(84);
        verificar(cola.desencolar() == 84, "La celda unitaria debe poder reutilizarse.");
    }

    private static void probarEstresDeReutilizacionCircular() {
        int capacidad = 31;
        ColaCircular cola = new ColaCircular(capacidad);
        ArrayDeque<Integer> referencia = new ArrayDeque<>();
        int siguienteValor = 0;

        for (int vuelta = 0; vuelta < 10_000; vuelta++) {
            while (referencia.size() < capacidad) {
                cola.encolar(siguienteValor);
                referencia.addLast(siguienteValor++);
                verificarEstadoContraReferencia(cola, referencia);
            }

            for (int i = 0; i < 13; i++) {
                verificar(cola.desencolar() == referencia.removeFirst(),
                        "La extracción debe coincidir con el modelo durante el estrés circular.");
                verificarEstadoContraReferencia(cola, referencia);
            }

            while (referencia.size() < capacidad) {
                cola.encolar(siguienteValor);
                referencia.addLast(siguienteValor++);
                verificarEstadoContraReferencia(cola, referencia);
            }

            esperarExcepcion(IllegalStateException.class, () -> cola.encolar(-1));
            verificarEstadoContraReferencia(cola, referencia);
        }

        Random aleatorio = new Random(810);
        for (int operacion = 0; operacion < 250_000; operacion++) {
            boolean insertar = aleatorio.nextBoolean();
            if (insertar && referencia.size() < capacidad) {
                cola.encolar(siguienteValor);
                referencia.addLast(siguienteValor++);
            } else if (!referencia.isEmpty()) {
                verificar(cola.desencolar() == referencia.removeFirst(),
                        "La extracción aleatoria debe conservar el orden FIFO.");
            } else {
                cola.encolar(siguienteValor);
                referencia.addLast(siguienteValor++);
            }

            verificarEstadoContraReferencia(cola, referencia);
        }

        while (!referencia.isEmpty()) {
            verificar(cola.desencolar() == referencia.removeFirst(),
                    "El vaciado final debe coincidir con el modelo.");
            verificarEstadoContraReferencia(cola, referencia);
        }

        System.out.println("Prueba de estrés superada: 10 000 ciclos de wrap-around "
                + "y 250 000 operaciones contrastadas con ArrayDeque.");
    }

    private static void verificarEstadoContraReferencia(
            ColaCircular cola,
            ArrayDeque<Integer> referencia) {
        verificar(cola.cantidadElementos() == referencia.size(),
                "El contador debe coincidir con el tamaño de referencia.");
        verificar(cola.estaVacia() == referencia.isEmpty(),
                "El estado vacío debe coincidir con la referencia.");
        verificar(cola.estaLlena() == (referencia.size() == cola.elementos.length),
                "El estado lleno debe coincidir con la capacidad real.");
        verificar(cola.front >= 0 && cola.front < cola.elementos.length,
                "front debe permanecer dentro del arreglo.");
        verificar(cola.rear >= 0 && cola.rear < cola.elementos.length,
                "rear debe permanecer dentro del arreglo.");
        if (!referencia.isEmpty()) {
            verificar(cola.consultarFrente() == referencia.peekFirst(),
                    "El frente debe coincidir con el próximo valor FIFO.");
        }
    }

    private static void imprimirEstado(String etiqueta, ColaCircular cola) {
        System.out.println(etiqueta);
        System.out.println("  arreglo = " + Arrays.toString(cola.elementos));
        System.out.println("  front = " + cola.front + ", rear = " + cola.rear
                + ", count = " + cola.count);
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
