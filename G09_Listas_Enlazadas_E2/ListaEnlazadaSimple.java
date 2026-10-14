/*
 * Prompt inicial:
 * Implementá el método boolean buscar(int dato) para una lista simplemente
 * enlazada de enteros. El recorrido debe comenzar en head y avanzar nodo por
 * nodo siguiendo el enlace siguiente. Devolvé true al encontrar el valor y
 * false al llegar a null sin encontrarlo. Explicá por qué no se puede acceder
 * directamente a un nodo por índice como en un arreglo.
 *
 * Ajustes técnicos:
 * - La lista se denomina ListaEnlazadaSimple y Nodo es una clase interna
 *   estática y privada.
 * - buscar recorre la lista sin modificar sus nodos ni enlaces.
 * - La búsqueda opera en O(n) tiempo en el peor caso y utiliza O(1) espacio
 *   auxiliar.
 * - main prueba búsquedas exitosas al inicio, en el medio y al final, además
 *   de búsquedas fallidas, una lista vacía, una lista con un solo elemento y
 *   una lista con valores repetidos.
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

    private static void verificarBusqueda(
            ListaEnlazadaSimple lista,
            int dato,
            boolean resultadoEsperado,
            String caso
    ) {
        boolean resultadoObtenido = lista.buscar(dato);
        if (resultadoObtenido != resultadoEsperado) {
            throw new AssertionError(
                    caso + ": se esperaba " + resultadoEsperado
                            + " y se obtuvo " + resultadoObtenido
            );
        }
        System.out.println(caso + ": " + resultadoObtenido);
    }

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);

        verificarBusqueda(lista, 10, true, "Busqueda al inicio");
        verificarBusqueda(lista, 30, true, "Busqueda en el medio");
        verificarBusqueda(lista, 50, true, "Busqueda al final");
        verificarBusqueda(lista, 99, false, "Busqueda de elemento inexistente");

        verificarBusqueda(
                new ListaEnlazadaSimple(),
                10,
                false,
                "Busqueda en lista vacia"
        );

        ListaEnlazadaSimple listaUnitaria = new ListaEnlazadaSimple();
        listaUnitaria.insertarAlFinal(7);
        verificarBusqueda(
                listaUnitaria,
                7,
                true,
                "Busqueda exitosa en lista con un elemento"
        );
        verificarBusqueda(
                listaUnitaria,
                9,
                false,
                "Busqueda fallida en lista con un elemento"
        );

        ListaEnlazadaSimple listaConRepetidos = new ListaEnlazadaSimple();
        listaConRepetidos.insertarAlFinal(4);
        listaConRepetidos.insertarAlFinal(8);
        listaConRepetidos.insertarAlFinal(4);
        listaConRepetidos.insertarAlFinal(4);
        verificarBusqueda(
                listaConRepetidos,
                4,
                true,
                "Busqueda de valor repetido"
        );
        verificarBusqueda(
                listaConRepetidos,
                9,
                false,
                "Busqueda fallida en lista con valores repetidos"
        );
    }
}
