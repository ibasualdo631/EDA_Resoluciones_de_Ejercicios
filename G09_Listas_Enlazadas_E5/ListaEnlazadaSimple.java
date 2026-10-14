/*
 * Prompt inicial:
 * Implementá el método boolean eliminar(int dato) en una lista simplemente
 * enlazada de enteros. Debe quitar únicamente la primera aparición del valor,
 * devolver true si lo encuentra y false si no está, y contemplar lista vacía,
 * eliminación de la cabeza, nodo interno, último nodo y dato inexistente.
 * Incluí un main con pruebas exhaustivas de estos escenarios y los casos de
 * un único nodo y valores repetidos. En Java no se libera manualmente la
 * memoria del nodo: se lo desvincula para que pueda ser reclamado por el
 * Garbage Collector cuando ya no sea alcanzable.
 *
 * Ajustes técnicos:
 * - La lista se denomina ListaEnlazadaSimple, Nodo es una clase interna
 *   estática y privada, y la lista mantiene la referencia head.
 * - eliminar conserva las referencias anterior y actual durante una única
 *   búsqueda O(n), usa O(1) espacio auxiliar y solo elimina la primera
 *   coincidencia.
 * - La lista no conserva una referencia tail ni un contador de tamaño.
 * - main valida el resultado booleano y el contenido final para cada caso.
 */
public class ListaEnlazadaSimple {
    private static class Nodo {
        private int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
        }
    }

    private Nodo head;

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (head == null) {
            head = nuevo;
            return;
        }

        Nodo actual = head;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
    }

    public boolean buscar(int dato) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.dato == dato) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean eliminar(int dato) {
        Nodo anterior = null;
        Nodo actual = head;

        while (actual != null && actual.dato != dato) {
            anterior = actual;
            actual = actual.siguiente;
        }

        if (actual == null) {
            return false;
        }

        if (anterior == null) {
            head = actual.siguiente;
        } else {
            anterior.siguiente = actual.siguiente;
        }

        actual.siguiente = null;
        return true;
    }

    private static void verificarEliminacion(
            ListaEnlazadaSimple lista,
            int dato,
            boolean resultadoEsperado,
            int[] contenidoEsperado,
            String caso
    ) {
        boolean resultadoObtenido = lista.eliminar(dato);
        if (resultadoObtenido != resultadoEsperado) {
            throw new AssertionError(
                    caso + ": se esperaba " + resultadoEsperado
                            + " y se obtuvo " + resultadoObtenido
            );
        }

        verificarContenido(lista, contenidoEsperado, caso);
    }

    private static void verificarContenido(
            ListaEnlazadaSimple lista,
            int[] esperado,
            String caso
    ) {
        Nodo actual = lista.head;
        for (int indice = 0; indice < esperado.length; indice++) {
            if (actual == null) {
                throw new AssertionError(
                        caso + ": la lista termino antes del elemento " + indice
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

    public static void main(String[] args) {
        verificarEliminacion(
                new ListaEnlazadaSimple(),
                10,
                false,
                new int[] {},
                "Lista vacia"
        );

        ListaEnlazadaSimple listaCabeza = new ListaEnlazadaSimple();
        listaCabeza.insertarAlFinal(10);
        listaCabeza.insertarAlFinal(20);
        listaCabeza.insertarAlFinal(30);
        verificarEliminacion(
                listaCabeza,
                10,
                true,
                new int[] {20, 30},
                "Eliminar la cabeza"
        );

        ListaEnlazadaSimple listaUnSoloNodo = new ListaEnlazadaSimple();
        listaUnSoloNodo.insertarAlFinal(7);
        verificarEliminacion(
                listaUnSoloNodo,
                7,
                true,
                new int[] {},
                "Eliminar el unico nodo"
        );
        verificarEliminacion(
                listaUnSoloNodo,
                7,
                false,
                new int[] {},
                "Eliminar de nuevo en la lista vacia"
        );

        ListaEnlazadaSimple listaInterna = new ListaEnlazadaSimple();
        listaInterna.insertarAlFinal(10);
        listaInterna.insertarAlFinal(20);
        listaInterna.insertarAlFinal(30);
        listaInterna.insertarAlFinal(40);
        verificarEliminacion(
                listaInterna,
                30,
                true,
                new int[] {10, 20, 40},
                "Eliminar un nodo interno"
        );

        ListaEnlazadaSimple listaUltimo = new ListaEnlazadaSimple();
        listaUltimo.insertarAlFinal(10);
        listaUltimo.insertarAlFinal(20);
        listaUltimo.insertarAlFinal(30);
        verificarEliminacion(
                listaUltimo,
                30,
                true,
                new int[] {10, 20},
                "Eliminar el ultimo nodo"
        );

        ListaEnlazadaSimple listaDatoAusente = new ListaEnlazadaSimple();
        listaDatoAusente.insertarAlFinal(10);
        listaDatoAusente.insertarAlFinal(20);
        listaDatoAusente.insertarAlFinal(30);
        verificarEliminacion(
                listaDatoAusente,
                99,
                false,
                new int[] {10, 20, 30},
                "Dato inexistente sin modificar la lista"
        );

        ListaEnlazadaSimple listaConRepetidos = new ListaEnlazadaSimple();
        listaConRepetidos.insertarAlFinal(4);
        listaConRepetidos.insertarAlFinal(8);
        listaConRepetidos.insertarAlFinal(4);
        listaConRepetidos.insertarAlFinal(4);
        verificarEliminacion(
                listaConRepetidos,
                4,
                true,
                new int[] {8, 4, 4},
                "Eliminar solo la primera aparicion repetida"
        );

        if (!listaConRepetidos.buscar(4)) {
            throw new AssertionError(
                    "Deben permanecer las apariciones posteriores del valor repetido"
            );
        }

        System.out.println("Todas las pruebas finalizaron correctamente.");
    }
}
