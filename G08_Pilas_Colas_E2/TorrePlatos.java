/*
 * Prompt: "Implementá TorrePlatos.java en un archivo único. Incluí la cabecera
 * de comentarios obligatoria y un main que simule apilar varios platos y
 * retirarlos uno a uno."
 *
 * Ajustes aplicados:
 * - Se reutiliza la lógica LIFO de PilaEnteros mediante un arreglo y el índice
 *   top, sin acoplar este ejercicio a una pila que solo almacena enteros.
 * - Se aceptan platos String o Number; null y otros tipos se rechazan.
 * - La capacidad es fija y positiva. Overflow lanza IllegalStateException;
 *   underflow al consultar o desapilar lanza NoSuchElementException.
 */
import java.util.NoSuchElementException;

public class TorrePlatos {

    private final Object[] platos;
    private int top;

    public TorrePlatos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }

        platos = new Object[capacidad];
        top = -1;
    }

    public void apilar(Object plato) {
        if (!(plato instanceof String) && !(plato instanceof Number)) {
            throw new IllegalArgumentException(
                    "El plato debe representarse con un nombre o un número.");
        }
        if (top == platos.length - 1) {
            throw new IllegalStateException("Overflow: la torre está llena.");
        }

        platos[++top] = plato;
    }

    public Object desapilar() {
        if (estaVacia()) {
            throw new NoSuchElementException("Underflow: la torre está vacía.");
        }

        Object platoSuperior = platos[top];
        platos[top--] = null;
        return platoSuperior;
    }

    public Object consultarSuperior() {
        if (estaVacia()) {
            throw new NoSuchElementException("Underflow: la torre está vacía.");
        }

        return platos[top];
    }

    public boolean estaVacia() {
        return top == -1;
    }

    public int cantidadPlatos() {
        return top + 1;
    }

    public static void main(String[] args) {
        TorrePlatos torre = new TorrePlatos(4);
        torre.apilar("Plato hondo");
        torre.apilar("Plato llano");
        torre.apilar("Plato de postre");

        System.out.println("Plato superior: " + torre.consultarSuperior());
        System.out.println("Retirando los platos:");
        while (!torre.estaVacia()) {
            System.out.println("- " + torre.desapilar());
        }

        System.out.println("¿La torre quedó vacía? " + torre.estaVacia());
        probarExcepcionesEnTorreVacia(torre);
        System.out.println("La ejecución continúa después de manejar las excepciones.");
    }

    private static void probarExcepcionesEnTorreVacia(TorrePlatos torre) {
        try {
            torre.desapilar();
            throw new AssertionError("Se esperaba NoSuchElementException al desapilar.");
        } catch (NoSuchElementException excepcion) {
            System.out.println("Desapilado vacío controlado: " + excepcion.getMessage());
        }

        try {
            torre.consultarSuperior();
            throw new AssertionError(
                    "Se esperaba NoSuchElementException al consultar la torre vacía.");
        } catch (NoSuchElementException excepcion) {
            System.out.println("Consulta vacía controlada: " + excepcion.getMessage());
        }
    }
}
