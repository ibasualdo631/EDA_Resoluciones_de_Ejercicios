/*
 * Prompt inicial:
 * Implementá en Java una lista enlazada simple de números enteros desde cero.
 * No utilices ArrayList, LinkedList ni otras colecciones de Java para almacenar
 * los elementos. Cada nodo debe guardar un int dato y una referencia al nodo
 * siguiente. La lista debe permitir insertar al inicio y al final, imprimir en
 * orden, consultar si está vacía y obtener su tamaño. Incluí un ejemplo de uso
 * que pruebe estos comportamientos.
 *
 * Ajustes técnicos:
 * - La implementación se denomina ListaEnlazadaSimple.
 * - Nodo es una clase interna estática y privada.
 * - La lista mantiene únicamente head y size, sin un puntero tail.
 * - insertarAlInicio opera en O(1); insertarAlFinal recorre desde head y opera
 *   en O(n) en el peor caso.
 * - size se actualiza una vez por inserción; getSize devuelve el atributo en O(1).
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

    public void insertarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = head;
        head = nuevo;
        size++;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (head == null) {
            head = nuevo;
        } else {
            Nodo actual = head;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }

        size++;
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

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();

        System.out.println("Lista vacia: " + lista.estaVacia());
        System.out.println("Tamanio inicial: " + lista.getSize());

        lista.insertarAlInicio(20);
        lista.insertarAlInicio(10);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        System.out.print("Elementos: ");
        lista.imprimir();
        System.out.println("Tamanio final: " + lista.getSize());
        System.out.println("Lista vacia: " + lista.estaVacia());
    }
}
