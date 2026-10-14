/*
 * Prompt inicial:
 * Implementa el metodo int obtener(int posicion) en una lista simplemente
 * enlazada de enteros. Usa indices desde cero, valida el rango antes de
 * recorrer y lanza IndexOutOfBoundsException si posicion es negativa o mayor
 * o igual que el tamano. Recorre nodo por nodo desde head sin modificar la
 * lista ni copiarla a un arreglo. Incluye un main con pruebas para posiciones
 * validas, casos de frontera, lista vacia e indices invalidos.
 *
 * Ajustes tecnicos:
 * - La lista se denomina ListaEnlazadaSimple y mantiene head y size.
 * - obtener valida primero que 0 <= posicion < size; los indices invalidos
 *   lanzan IndexOutOfBoundsException antes de acceder a cualquier nodo.
 * - La consulta recorre enlaces desde head, retorna el dato solicitado y no
 *   modifica la estructura: O(n) tiempo en el peor caso y O(1) espacio.
 * - main comprueba posiciones validas y de frontera, indices invalidos,
 *   excepciones en lista vacia y que la lista permanezca intacta.
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

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posicion: " + posicion + ", tamano: " + size
            );
        }

        Nodo actual = head;
        int indiceActual = 0;
        while (indiceActual < posicion) {
            actual = actual.siguiente;
            indiceActual++;
        }

        return actual.dato;
    }

    public int getSize() {
        return size;
    }

    private static void verificarObtener(
            ListaEnlazadaSimple lista,
            int posicion,
            int esperado,
            String caso
    ) {
        int obtenido = lista.obtener(posicion);
        if (obtenido != esperado) {
            throw new AssertionError(
                    caso + ": se esperaba " + esperado + " y se obtuvo " + obtenido
            );
        }
        System.out.println(caso + ": " + obtenido);
    }

    private static void verificarIndiceInvalido(
            ListaEnlazadaSimple lista,
            int posicion,
            String caso
    ) {
        int tamanoAntes = lista.getSize();
        int primeroAntes = tamanoAntes == 0 ? 0 : lista.obtener(0);
        int ultimoAntes = tamanoAntes == 0 ? 0 : lista.obtener(tamanoAntes - 1);

        try {
            lista.obtener(posicion);
            throw new AssertionError(
                    caso + ": se esperaba IndexOutOfBoundsException"
            );
        } catch (IndexOutOfBoundsException excepcionEsperada) {
            if (lista.getSize() != tamanoAntes) {
                throw new AssertionError(caso + ": cambio el tamano de la lista");
            }
            if (tamanoAntes > 0
                    && (lista.obtener(0) != primeroAntes
                    || lista.obtener(tamanoAntes - 1) != ultimoAntes)) {
                throw new AssertionError(caso + ": cambio el contenido de la lista");
            }
            System.out.println(caso + ": IndexOutOfBoundsException");
        }
    }

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        if (!lista.estaVacia()) {
            throw new AssertionError("La lista nueva debe estar vacia");
        }
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);
        lista.insertarAlInicio(5);

        verificarObtener(lista, 0, 5, "Primera posicion");
        verificarObtener(lista, 3, 30, "Posicion intermedia");
        verificarObtener(lista, lista.getSize() - 1, 50, "Ultima posicion valida");
        verificarIndiceInvalido(lista, -1, "Indice negativo");
        verificarIndiceInvalido(lista, lista.getSize(), "Indice igual al tamano");
        verificarIndiceInvalido(lista, lista.getSize() + 1, "Indice mayor al tamano");

        ListaEnlazadaSimple listaUnitaria = new ListaEnlazadaSimple();
        listaUnitaria.insertarAlFinal(7);
        verificarObtener(listaUnitaria, 0, 7, "Unico elemento");
        verificarIndiceInvalido(listaUnitaria, 1, "Despues del unico elemento");

        ListaEnlazadaSimple listaVacia = new ListaEnlazadaSimple();
        verificarIndiceInvalido(listaVacia, 0, "Indice cero en lista vacia");
        verificarIndiceInvalido(listaVacia, -1, "Indice negativo en lista vacia");

        System.out.println("Todas las pruebas finalizaron correctamente.");
        System.out.print("Lista resultante: ");
        lista.imprimir();
    }
}
