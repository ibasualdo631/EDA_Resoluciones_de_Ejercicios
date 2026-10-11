/*
 * Prompt utilizado:
 * "Implementá en Java un algoritmo que sume todos los elementos de una matriz
 * int[][] usando dos ciclos for anidados. Contabilizá las lecturas de elementos
 * y las sumas acumuladas, rechazá matrices null o sin filas, devolvé la suma y
 * los contadores, y probá matrices 3x3, no cuadradas, con negativos y null."
 *
 * Ajustes:
 * - La suma se devuelve como long y los contadores quedan disponibles mediante
 *   accesores estáticos de esta misma clase; no se crea un DTO separado.
 * - Los contadores corresponden a la invocación más reciente de sumar.
 * - Operaciones totales cuenta una iteración por cada celda evaluada. Las
 *   lecturas y las sumas acumuladas se exponen adicionalmente por separado.
 * - Se admiten filas vacías dentro de una matriz que tenga al menos una fila.
 */
public final class SumaMatriz {

    private static long lecturas;
    private static long sumas;
    private static long operacionesTotales;

    private SumaMatriz() {
    }

    /**
     * Suma los elementos y cuenta cada lectura de celda y suma acumulada.
     *
     * Tiempo: O(R + N), donde R es la cantidad de filas y N el total de celdas;
     * espacio auxiliar: O(1).
     *
     * @param matriz matriz de enteros con al menos una fila
     * @return suma de todos los elementos
     * @throws IllegalArgumentException si matriz es null o no contiene filas
     */
    public static long sumar(int[][] matriz) {
        lecturas = 0;
        sumas = 0;
        operacionesTotales = 0;

        if (matriz == null || matriz.length == 0) {
            throw new IllegalArgumentException(
                    "La matriz no puede ser null ni estar vacía (sin filas).");
        }

        long suma = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                int valor = matriz[i][j];
                lecturas++;
                suma += valor;
                sumas++;
                operacionesTotales++;
            }
        }

        return suma;
    }

    public static long getLecturas() {
        return lecturas;
    }

    public static long getSumas() {
        return sumas;
    }

    public static long getOperacionesTotales() {
        return operacionesTotales;
    }

    public static void main(String[] args) {
        probarMatriz("a) Matriz 3x3 normal", new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });

        probarMatriz("b) Matriz no cuadrada 2x4", new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        });

        probarMatriz("c) Matriz con números negativos", new int[][]{
                {-5, 2},
                {-3, -1}
        });

        System.out.println("d) Matriz null");
        try {
            sumar(null);
        } catch (IllegalArgumentException exception) {
            System.out.println("Excepción atrapada: " + exception.getMessage());
        }

        System.out.println("e) Matriz sin filas");
        try {
            sumar(new int[0][0]);
        } catch (IllegalArgumentException exception) {
            System.out.println("Excepción atrapada: " + exception.getMessage());
        }
    }

    private static void probarMatriz(String descripcion, int[][] matriz) {
        long suma = sumar(matriz);
        System.out.println(descripcion);
        System.out.println("Suma: " + suma);
        System.out.println("Operaciones totales: "
                + getOperacionesTotales()
                + " (lecturas: " + getLecturas()
                + ", sumas acumuladas: " + getSumas() + ")");
        System.out.println();
    }
}
