/*
 * Prompt inicial:
 * "Implementá el método void modificar(int posicion, int nuevoDato) en una
 * lista simplemente enlazada de enteros que mantiene una referencia a su
 * cabeza y un atributo size. Usá índices de base cero y validá que la posición
 * esté entre 0 y size - 1 antes de iniciar el recorrido. Si es negativa o está
 * fuera de rango, lanzá IndexOutOfBoundsException sin modificar la lista.
 * Para una posición válida, recorré hasta el nodo correspondiente y cambiá
 * únicamente su dato, sin alterar enlaces, cabeza ni tamaño. Incluí un main
 * con pruebas en distintas posiciones, extremos y posiciones inválidas."
 *
 * Ajustes de implementación:
 * - Se sigue el diseño de ListaEnlazada del ejercicio anterior: Nodo interno,
 *   referencia cabeza, contador size y método obtenerSize().
 * - Los índices inválidos se rechazan antes del recorrido con
 *   IndexOutOfBoundsException.
 * - El campo dato es mutable; siguiente conserva el enlace original.
 * - Las pruebas verifican contenido y tamaño, también después de índices
 *   inválidos, para confirmar que la estructura no cambió.
 */
public class ListaEnlazada {
    private Nodo cabeza;
    private int size;

    private static class Nodo {
        private int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
        }
    }

    public int obtenerSize() {
        return size;
    }

    public void agregarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

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

    public void modificar(int posicion, int nuevoDato) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posición: " + posicion + ", tamaño: " + size);
        }

        Nodo actual = cabeza;
        for (int indice = 0; indice < posicion; indice++) {
            actual = actual.siguiente;
        }

        actual.dato = nuevoDato;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("[");
        Nodo actual = cabeza;

        while (actual != null) {
            resultado.append(actual.dato);
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

        verificarModificacionSoloDato(lista, 0, 11, "modificar el primero");
        verificarEstado(lista, "[11, 20, 30, 40, 50]", 5, "modificar el primero");

        verificarModificacionSoloDato(lista, 2, 33, "modificar una posición intermedia");
        verificarEstado(lista, "[11, 20, 33, 40, 50]", 5, "modificar una posición intermedia");

        verificarModificacionSoloDato(
                lista, lista.obtenerSize() - 1, 55, "modificar el último");
        verificarEstado(lista, "[11, 20, 33, 40, 55]", 5, "modificar el último");

        verificarIndiceInvalidoSinCambios(lista, -1);
        verificarIndiceInvalidoSinCambios(lista, lista.obtenerSize());

        ListaEnlazada unElemento = new ListaEnlazada();
        unElemento.agregarAlFinal(99);
        verificarModificacionSoloDato(unElemento, 0, 100, "modificar el único elemento");
        verificarEstado(unElemento, "[100]", 1, "modificar el único elemento");

        ListaEnlazada vacia = new ListaEnlazada();
        verificarIndiceInvalidoSinCambios(vacia, 0);

        System.out.println("Todas las pruebas de modificar pasaron.");
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
        InstantaneaLista original = new InstantaneaLista(lista);

        try {
            lista.modificar(posicion, 999);
            throw new IllegalStateException(
                    "Se esperaba IndexOutOfBoundsException para la posición " + posicion);
        } catch (IndexOutOfBoundsException esperada) {
            original.verificarSinCambios(lista, "posición inválida " + posicion);
            System.out.println("OK: índice inválido " + posicion + " rechazado sin cambios");
        }
    }

    private static void verificarModificacionSoloDato(
            ListaEnlazada lista, int posicion, int nuevoDato, String caso) {
        InstantaneaLista original = new InstantaneaLista(lista);
        lista.modificar(posicion, nuevoDato);
        original.verificarSoloDatoModificado(lista, posicion, nuevoDato, caso);
        System.out.println("OK: solo cambió dato en posición " + posicion + " (" + caso + ")");
    }

    private static final class InstantaneaLista {
        private final Nodo cabezaOriginal;
        private final int sizeOriginal;
        private final Nodo[] nodos;
        private final Nodo[] siguientes;
        private final int[] datos;

        private InstantaneaLista(ListaEnlazada lista) {
            cabezaOriginal = lista.cabeza;
            sizeOriginal = lista.size;
            nodos = new Nodo[sizeOriginal];
            siguientes = new Nodo[sizeOriginal];
            datos = new int[sizeOriginal];

            Nodo actual = cabezaOriginal;
            for (int i = 0; i < sizeOriginal; i++) {
                if (actual == null) {
                    throw new IllegalStateException("El tamaño no coincide con los nodos de la lista");
                }
                nodos[i] = actual;
                siguientes[i] = actual.siguiente;
                datos[i] = actual.dato;
                actual = actual.siguiente;
            }
            if (actual != null) {
                throw new IllegalStateException("La lista contiene nodos adicionales o un ciclo");
            }
        }

        private void verificarSinCambios(ListaEnlazada lista, String caso) {
            verificarEstructura(lista, caso);
            for (int i = 0; i < sizeOriginal; i++) {
                if (nodos[i].dato != datos[i]) {
                    throw new IllegalStateException(
                            "Cambió el dato del nodo " + i + " tras " + caso);
                }
            }
        }

        private void verificarSoloDatoModificado(
                ListaEnlazada lista, int posicion, int nuevoDato, String caso) {
            verificarEstructura(lista, caso);
            for (int i = 0; i < sizeOriginal; i++) {
                int datoEsperado = i == posicion ? nuevoDato : datos[i];
                if (nodos[i].dato != datoEsperado) {
                    throw new IllegalStateException(
                            "Se alteró un dato inesperado en la posición " + i + " durante " + caso);
                }
            }
        }

        private void verificarEstructura(ListaEnlazada lista, String caso) {
            if (lista.cabeza != cabezaOriginal || lista.size != sizeOriginal) {
                throw new IllegalStateException("Cambió la cabeza o el tamaño durante " + caso);
            }

            Nodo actual = lista.cabeza;
            for (int i = 0; i < sizeOriginal; i++) {
                if (actual != nodos[i] || actual.siguiente != siguientes[i]) {
                    throw new IllegalStateException(
                            "Cambió la identidad o el enlace del nodo " + i + " durante " + caso);
                }
                actual = actual.siguiente;
            }
            if (actual != null) {
                throw new IllegalStateException("Cambió el fin de la lista durante " + caso);
            }
        }
    }
}
