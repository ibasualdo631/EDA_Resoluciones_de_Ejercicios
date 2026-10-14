import java.util.Objects;

/**
 * Lista doblemente enlazada genérica.
 *
 * Cada nodo mantiene dos referencias: {@code anterior} apunta al nodo previo y
 * {@code siguiente} al nodo posterior. Se necesitan ambas para recorrer desde
 * el inicio hacia el final y también desde el fin hacia el inicio.
 *
 * Al eliminar, se contemplan estos casos: si la lista está vacía o el dato no
 * existe, no se modifica; si se elimina el único nodo, {@code inicio} y
 * {@code fin} quedan en {@code null}; si se elimina el primero, se actualiza
 * {@code inicio} y el nuevo primero queda sin anterior; si se elimina el
 * último, se actualiza {@code fin} y el nuevo último queda sin siguiente; si
 * se elimina un nodo intermedio, se enlazan entre sí sus vecinos mediante
 * {@code previo.siguiente = posterior} y
 * {@code posterior.anterior = previo}. En todos los casos en que sí se elimina
 * un nodo, sus dos referencias se limpian para dejarlo desvinculado.
 *
 * @param <T> tipo de los elementos almacenados
 */
public class ListaDoble<T> {
    private static final class Nodo<E> {
        private final E dato;
        private Nodo<E> anterior;
        private Nodo<E> siguiente;

        private Nodo(E dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> inicio;
    private Nodo<T> fin;

    /**
     * Inserta un elemento al principio de la lista.
     *
     * @param dato elemento que se agregará
     */
    public void insertarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
            return;
        }

        nuevo.siguiente = inicio;
        inicio.anterior = nuevo;
        inicio = nuevo;
    }

    /**
     * Inserta un elemento al final de la lista.
     *
     * @param dato elemento que se agregará
     */
    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (fin == null) {
            inicio = nuevo;
            fin = nuevo;
            return;
        }

        nuevo.anterior = fin;
        fin.siguiente = nuevo;
        fin = nuevo;
    }

    /**
     * Elimina la primera coincidencia del elemento indicado.
     *
     * @param dato elemento que se buscará
     * @return {@code true} si se eliminó un elemento; {@code false} si no se
     *         encontró
     */
    public boolean eliminar(T dato) {
        Nodo<T> actual = inicio;

        while (actual != null && !Objects.equals(actual.dato, dato)) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            return false;
        }

        Nodo<T> previo = actual.anterior;
        Nodo<T> posterior = actual.siguiente;

        if (previo == null) {
            inicio = posterior;
        } else {
            previo.siguiente = posterior;
        }

        if (posterior == null) {
            fin = previo;
        } else {
            posterior.anterior = previo;
        }

        actual.anterior = null;
        actual.siguiente = null;
        return true;
    }

    /**
     * Imprime los elementos desde el inicio hasta el final.
     */
    public void imprimirAdelante() {
        StringBuilder resultado = new StringBuilder("[");
        Nodo<T> actual = inicio;

        while (actual != null) {
            if (actual != inicio) {
                resultado.append(", ");
            }
            resultado.append(String.valueOf(actual.dato));
            actual = actual.siguiente;
        }

        System.out.println(resultado.append(']'));
    }

    /**
     * Imprime los elementos desde el final hasta el inicio.
     */
    public void imprimirAtras() {
        StringBuilder resultado = new StringBuilder("[");
        Nodo<T> actual = fin;
        boolean primero = true;

        while (actual != null) {
            if (!primero) {
                resultado.append(", ");
            }
            resultado.append(String.valueOf(actual.dato));
            primero = false;
            actual = actual.anterior;
        }

        System.out.println(resultado.append(']'));
    }
}
