/*
 * Prompt inicial:
 * "Implementá el método void eliminarEnPosicion(int posicion) para una lista
 * simplemente enlazada que mantiene una referencia a su cabeza y un atributo
 * size. Considerá que las posiciones son de base cero y que solo son válidas
 * las posiciones comprendidas entre 0 y size - 1, inclusive. Validá la
 * posición antes de modificar la lista; si es negativa o está fuera de rango,
 * lanzá IndexOutOfBoundsException y conservá intactos los enlaces y el tamaño.
 * Para eliminar la posición cero, actualizá la cabeza para que apunte al
 * segundo nodo. Para las demás posiciones, recorré la lista hasta encontrar
 * el nodo anterior al que se eliminará y hacé que su enlace apunte al nodo
 * siguiente del eliminado. Contemplá las listas vacías, de un solo elemento
 * y la eliminación del último nodo. Decrementá size exactamente una vez
 * después de una eliminación válida. Explicá el recorrido y los casos límite."
 *
 * Ajustes de implementación:
 * - Los índices inválidos lanzan IndexOutOfBoundsException antes de cambiar
 *   enlaces o size.
 * - La lista almacena enteros y usa una clase Nodo interna.
 * - No se mantiene una referencia a la cola; la estructura conserva cabeza
 *   y size, como pide el ejercicio.
 */
public class ListaEnlazada {
    private Nodo cabeza;
    private int size;

    private static class Nodo {
        private final int valor;
        private Nodo siguiente;

        private Nodo(int valor) {
            this.valor = valor;
        }
    }

    public int obtenerSize() {
        return size;
    }

    public void agregarAlFinal(int valor) {
        Nodo nuevo = new Nodo(valor);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }

        size++;
    }

    public void eliminarEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posición: " + posicion + ", tamaño: " + size);
        }

        if (posicion == 0) {
            cabeza = cabeza.siguiente;
        } else {
            Nodo anterior = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                anterior = anterior.siguiente;
            }

            Nodo eliminado = anterior.siguiente;
            anterior.siguiente = eliminado.siguiente;
        }

        size--;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("[");
        Nodo actual = cabeza;

        while (actual != null) {
            resultado.append(actual.valor);
            actual = actual.siguiente;
            if (actual != null) {
                resultado.append(", ");
            }
        }

        return resultado.append(']').toString();
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.agregarAlFinal(10);
        lista.agregarAlFinal(20);
        lista.agregarAlFinal(30);
        lista.agregarAlFinal(40);
        lista.agregarAlFinal(50);

        lista.eliminarEnPosicion(0);
        verificarEstado(lista, "[20, 30, 40, 50]", 4, "eliminar al inicio");

        lista.eliminarEnPosicion(1);
        verificarEstado(lista, "[20, 40, 50]", 3, "eliminar en el medio");

        lista.eliminarEnPosicion(lista.obtenerSize() - 1);
        verificarEstado(lista, "[20, 40]", 2, "eliminar al final");

        verificarIndiceInvalidoSinCambios(lista, -1);
        verificarIndiceInvalidoSinCambios(lista, lista.obtenerSize());

        ListaEnlazada unElemento = new ListaEnlazada();
        unElemento.agregarAlFinal(99);
        unElemento.eliminarEnPosicion(0);
        verificarEstado(unElemento, "[]", 0, "eliminar el único elemento");

        ListaEnlazada vacia = new ListaEnlazada();
        verificarIndiceInvalidoSinCambios(vacia, 0);

        System.out.println("Todas las pruebas de eliminarEnPosicion pasaron.");
    }

    private static void verificarEstado(
            ListaEnlazada lista, String contenidoEsperado, int sizeEsperado, String caso) {
        if (!contenidoEsperado.equals(lista.toString())
                || sizeEsperado != lista.obtenerSize()) {
            throw new IllegalStateException(
                    "Falló " + caso + ": lista=" + lista + ", size=" + lista.obtenerSize());
        }
        System.out.println("OK: " + caso + " -> " + lista + ", size=" + lista.obtenerSize());
    }

    private static void verificarIndiceInvalidoSinCambios(ListaEnlazada lista, int posicion) {
        String contenidoOriginal = lista.toString();
        int sizeOriginal = lista.obtenerSize();

        try {
            lista.eliminarEnPosicion(posicion);
            throw new IllegalStateException(
                    "Se esperaba IndexOutOfBoundsException para la posición " + posicion);
        } catch (IndexOutOfBoundsException esperada) {
            if (!contenidoOriginal.equals(lista.toString()) || sizeOriginal != lista.obtenerSize()) {
                throw new IllegalStateException(
                        "La lista cambió después de rechazar la posición " + posicion);
            }
            System.out.println("OK: índice inválido " + posicion + " rechazado sin cambios");
        }
    }
}
