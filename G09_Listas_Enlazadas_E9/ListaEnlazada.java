/*
 * Prompt inicial:
 * "Implementa el metodo void invertir() para una lista enlazada simple.
 * Invierte los enlaces en el lugar usando las referencias anterior, actual
 * y siguiente. Guarda primero el siguiente nodo antes de redirigir el enlace
 * actual, para no perder el resto de la lista. Incluye un main que imprima la
 * lista antes y despues de invertirla."
 *
 * Ajustes de implementacion:
 * - Se mantiene el diseno de los ejercicios anteriores: clase ListaEnlazada,
 *   nodo interno, referencia cabeza y atributo size.
 * - invertir() recorre la lista una vez y usa espacio auxiliar constante.
 * - main muestra el ejemplo 10 -> 20 -> 30 -> 40 antes y despues de invertir.
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

    public void invertir() {
        Nodo anterior = null;
        Nodo actual = cabeza;

        while (actual != null) {
            Nodo siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }

        cabeza = anterior;
    }

    public void imprimir() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato);
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
            actual = actual.siguiente;
        }
        System.out.println(" -> null");
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.agregarAlFinal(10);
        lista.agregarAlFinal(20);
        lista.agregarAlFinal(30);
        lista.agregarAlFinal(40);

        System.out.print("Antes de invertir:    ");
        lista.imprimir();

        lista.invertir();

        System.out.print("Despues de invertir:  ");
        lista.imprimir();
    }
}
