/*
 * Prompt inicial:
 * "Implementá contarOcurrencias(int dato) en una lista simplemente enlazada
 * de enteros. Recorré todos los nodos mediante un acumulador, sin detenerte
 * en la primera coincidencia, y devolvé la cantidad total de apariciones.
 * Incluí la cabecera de comentarios y un main que pruebe valores existentes,
 * repetidos y ausentes."
 *
 * Ajustes de implementación:
 * - Se mantiene el diseño usado en los ejercicios anteriores: clase
 *   ListaEnlazada, nodo interno, referencia cabeza y atributo size.
 * - contarOcurrencias recorre hasta null y solo lee la lista.
 * - main verifica listas vacías y unitarias, valores repetidos y ausentes;
 *   falla explícitamente si un resultado no es el esperado.
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

    public int contarOcurrencias(int dato) {
        int contador = 0;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.dato == dato) {
                contador++;
            }
            actual = actual.siguiente;
        }

        return contador;
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.agregarAlFinal(10);
        lista.agregarAlFinal(20);
        lista.agregarAlFinal(10);
        lista.agregarAlFinal(30);
        lista.agregarAlFinal(10);

        verificarOcurrencias(lista, 10, 3, "valor repetido");
        verificarOcurrencias(lista, 20, 1, "valor existente una vez");
        verificarOcurrencias(lista, 99, 0, "valor ausente");
        verificarOcurrencias(new ListaEnlazada(), 10, 0, "lista vacia");

        ListaEnlazada unElemento = new ListaEnlazada();
        unElemento.agregarAlFinal(42);
        verificarOcurrencias(unElemento, 42, 1, "elemento presente en lista unitaria");
        verificarOcurrencias(unElemento, 7, 0, "elemento ausente en lista unitaria");

        System.out.println("Todas las pruebas de contarOcurrencias pasaron.");
    }

    private static void verificarOcurrencias(
            ListaEnlazada lista, int dato, int esperado, String caso) {
        int resultado = lista.contarOcurrencias(dato);
        if (resultado != esperado) {
            throw new IllegalStateException(
                    "Fallo " + caso + ": se esperaban " + esperado
                            + " ocurrencias de " + dato + ", pero se obtuvo " + resultado);
        }
        System.out.println("OK: " + caso + " -> " + resultado);
    }
}
