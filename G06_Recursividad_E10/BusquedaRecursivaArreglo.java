/*
 * Prompt inicial:
 * Implementá en Java una búsqueda recursiva en un arreglo de enteros, sin
 * utilizar ciclos. La función debe devolver el índice de la primera
 * coincidencia o -1 si el valor no aparece. Usá un método público que inicie
 * la búsqueda en el índice 0 y un método auxiliar privado que avance
 * recursivamente por el arreglo.
 *
 * Incluí en el main un arreglo estático con los valores [10, 25, 4, 8, 30]
 * y buscá los valores 4, 10 y 99.
 */
public final class BusquedaRecursivaArreglo {
    private static final int[] ARREGLO = {10, 25, 4, 8, 30};

    private BusquedaRecursivaArreglo() {
    }

    public static int buscar(int[] arreglo, int valor) {
        if (arreglo == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null.");
        }
        return buscarDesde(arreglo, valor, 0);
    }

    private static int buscarDesde(int[] arreglo, int valor, int indice) {
        if (indice >= arreglo.length) {
            return -1;
        }
        if (arreglo[indice] == valor) {
            return indice;
        }
        return buscarDesde(arreglo, valor, indice + 1);
    }

    public static void main(String[] args) {
        verificarResultado(4, 2);
        verificarResultado(10, 0);
        verificarResultado(99, -1);

        mostrarResultado(4);
        mostrarResultado(10);
        mostrarResultado(99);
    }

    private static void verificarResultado(int valor, int indiceEsperado) {
        int indiceObtenido = buscar(ARREGLO, valor);
        if (indiceObtenido != indiceEsperado) {
            throw new AssertionError(
                    "buscar(" + valor + "): se esperaba el índice " + indiceEsperado
                            + " y se obtuvo " + indiceObtenido);
        }
    }

    private static void mostrarResultado(int valor) {
        int indice = buscar(ARREGLO, valor);
        if (indice == -1) {
            System.out.println("El valor " + valor + " no se encontró.");
        } else {
            System.out.println("El valor " + valor + " se encontró en el índice " + indice + ".");
        }
    }
}
