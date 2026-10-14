/*
 * Prompt inicial:
 * Implementa insertarEnPosicion(int dato, int posicion) para una lista
 * simplemente enlazada de enteros. Usa indices desde cero, permitiendo
 * insertar desde la posicion 0 hasta la posicion igual al tamano actual.
 * Rechaza indices invalidos sin modificar la lista. Asegura la reconexion
 * correcta de referencias al insertar en el inicio, medio y final, explica
 * por que primero se conserva el sucesor en nuevo.siguiente y despues se
 * actualiza actual.siguiente, e incluye un main que pruebe varias ubicaciones
 * y los limites.
 *
 * Ajustes tecnicos:
 * - La lista se denomina ListaEnlazadaSimple y mantiene head y size.
 * - insertarEnPosicion lanza IndexOutOfBoundsException cuando posicion es
 *   negativa o mayor que size; el rango valido es inclusivo [0, size].
 * - En posiciones mayores que cero, la reconexion se realiza en el orden
 *   nuevo.siguiente = actual.siguiente y actual.siguiente = nuevo, evitando
 *   perder la cadena restante o crear un ciclo.
 * - main verifica inserciones al inicio, en medio, al final y en una lista
 *   vacia, ademas de indices invalidos y la integridad de los elementos.
 */
public class ListaEnlazadaSimple {
    private static class Nodo {
        private int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo head;
    private int size;

    public ListaEnlazadaSimple() {
        head = null;
        size = 0;
    }

    public void insertarEnPosicion(int dato, int posicion) {
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException(
                    "Posicion: " + posicion + ", tamano: " + size
            );
        }

        Nodo nuevo = new Nodo(dato);

        if (posicion == 0) {
            nuevo.siguiente = head;
            head = nuevo;
        } else {
            Nodo actual = head;
            for (int indice = 0; indice < posicion - 1; indice++) {
                actual = actual.siguiente;
            }

            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }

        size++;
    }

    public int getSize() {
        return size;
    }

    public void imprimir() {
        Nodo actual = head;
        while (actual != null) {
            System.out.print(actual.dato);
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
            actual = actual.siguiente;
        }
        System.out.println();
    }

    private static void verificarContenido(
            ListaEnlazadaSimple lista,
            int[] esperado,
            String caso
    ) {
        if (lista.getSize() != esperado.length) {
            throw new AssertionError(
                    caso + ": tamano esperado " + esperado.length
                            + ", obtenido " + lista.getSize()
            );
        }

        Nodo actual = lista.head;
        for (int indice = 0; indice < esperado.length; indice++) {
            if (actual == null) {
                throw new AssertionError(
                        caso + ": la cadena termino antes del elemento " + indice
                );
            }
            if (actual.dato != esperado[indice]) {
                throw new AssertionError(
                        caso + ": en indice " + indice + " se esperaba "
                                + esperado[indice] + " y se obtuvo " + actual.dato
                );
            }
            actual = actual.siguiente;
        }

        if (actual != null) {
            throw new AssertionError(
                    caso + ": hay nodos adicionales o un ciclo en la cadena"
            );
        }
        System.out.println(caso + ": correcto");
    }

    private static void verificarIndiceInvalido(
            ListaEnlazadaSimple lista,
            int posicion,
            int[] contenidoEsperado,
            String caso
    ) {
        try {
            lista.insertarEnPosicion(999, posicion);
            throw new AssertionError(
                    caso + ": se esperaba IndexOutOfBoundsException"
            );
        } catch (IndexOutOfBoundsException excepcionEsperada) {
            verificarContenido(lista, contenidoEsperado, caso);
        }
    }

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarEnPosicion(10, 0);
        lista.insertarEnPosicion(20, 1);
        lista.insertarEnPosicion(30, 2);

        lista.insertarEnPosicion(5, 0);
        verificarContenido(
                lista,
                new int[] {5, 10, 20, 30},
                "Insercion al inicio"
        );

        lista.insertarEnPosicion(15, 2);
        verificarContenido(
                lista,
                new int[] {5, 10, 15, 20, 30},
                "Insercion en el medio"
        );

        lista.insertarEnPosicion(40, lista.getSize());
        verificarContenido(
                lista,
                new int[] {5, 10, 15, 20, 30, 40},
                "Insercion al final"
        );

        verificarIndiceInvalido(
                lista,
                -1,
                new int[] {5, 10, 15, 20, 30, 40},
                "Indice negativo"
        );
        verificarIndiceInvalido(
                lista,
                lista.getSize() + 1,
                new int[] {5, 10, 15, 20, 30, 40},
                "Indice mayor al tamano"
        );

        ListaEnlazadaSimple listaVacia = new ListaEnlazadaSimple();
        listaVacia.insertarEnPosicion(7, 0);
        verificarContenido(
                listaVacia,
                new int[] {7},
                "Insercion en lista vacia"
        );

        System.out.print("Lista final: ");
        lista.imprimir();
        System.out.println("Todas las pruebas finalizaron correctamente.");
    }
}
