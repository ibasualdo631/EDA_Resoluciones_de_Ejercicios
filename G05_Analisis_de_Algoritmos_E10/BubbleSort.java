/*
 * Prompt inicial:
 * "Construí un prompt para OpenCode que solicite la implementación en Java del
 * algoritmo Bubble Sort. El prompt debe explicar por qué este algoritmo
 * resulta adecuado únicamente para fines didácticos o conjuntos pequeños de
 * datos y solicitar que el programa contabilice comparaciones e intercambios."
 *
 * Ajustes acordados:
 * - Implementar BubbleSort en un único archivo Java.
 * - Rechazar un arreglo null con IllegalArgumentException.
 * - Exponer bubbleSort(int[] arr) como método estático void e imprimir las
 *   comparaciones e intercambios efectuados.
 * - Usar una bandera para terminar cuando una pasada no realiza intercambios.
 * - Probar entradas ordenadas, invertidas, con duplicados y vacías en main.
 *
 * Complejidad: peor caso O(n^2), mejor caso Θ(n) con la bandera de corte
 * (y, por lo tanto, Ω(n)); espacio auxiliar O(1).
 *
 * Bubble Sort se recomienda para fines didácticos o conjuntos pequeños:
 * en el caso promedio y el peor debe comparar elementos en múltiples pasadas,
 * con un costo que crece cuadráticamente. Al duplicar el tamaño de la entrada,
 * el trabajo puede crecer aproximadamente cuatro veces, por lo que no resulta
 * práctico para grandes volúmenes de datos.
 */
public final class BubbleSort {

    private BubbleSort() {
    }

    /**
     * Ordena el arreglo recibido en orden ascendente e imprime las métricas.
     *
     * @param arr arreglo de enteros que se ordenará en el lugar
     * @throws IllegalArgumentException si arr es null
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null.");
        }

        long comparaciones = 0;
        long intercambios = 0;

        for (int limite = arr.length - 1; limite > 0; limite--) {
            boolean huboIntercambios = false;

            for (int i = 0; i < limite; i++) {
                comparaciones++;
                if (arr[i] > arr[i + 1]) {
                    int temporal = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temporal;
                    intercambios++;
                    huboIntercambios = true;
                }
            }

            if (!huboIntercambios) {
                break;
            }
        }

        System.out.println("Comparaciones: " + comparaciones);
        System.out.println("Intercambios: " + intercambios);
    }

    public static void main(String[] args) {
        probarCaso("Vector ordenado", new int[]{1, 2, 3, 4},
                new int[]{1, 2, 3, 4});
        probarCaso("Vector invertido", new int[]{4, 3, 2, 1},
                new int[]{1, 2, 3, 4});
        probarCaso("Vector con duplicados", new int[]{5, 2, 5, 1, 2},
                new int[]{1, 2, 2, 5, 5});
        probarCaso("Vector vacío", new int[]{}, new int[]{});
        probarCaso("Vector de un elemento", new int[]{7}, new int[]{7});

        try {
            bubbleSort(null);
            throw new AssertionError("Se esperaba IllegalArgumentException para null.");
        } catch (IllegalArgumentException esperado) {
            System.out.println("Vector null: IllegalArgumentException correctamente lanzada.");
        }
    }

    private static void probarCaso(String nombre, int[] arr, int[] esperado) {
        bubbleSort(arr);
        if (!java.util.Arrays.equals(arr, esperado)) {
            throw new AssertionError(nombre + ": el resultado no coincide con el esperado.");
        }
        System.out.println(nombre + ": " + java.util.Arrays.toString(arr) + " [OK]");
    }
}
