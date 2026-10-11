/*
 * Prompt inicial utilizado:
 * "Actúa como Desarrollador Java Senior. Implementa una solución para encontrar
 * el valor mínimo de un vector de enteros. Usa la clase MinimoVector y el método
 * público estático encontrarMinimo(int[] vector). Valida las entradas, inicializa
 * minimo con vector[0], recorre iterativamente desde el índice 1 sin modificar
 * ni ordenar el vector, explica la complejidad y agrega pruebas de consola."
 *
 * Ajustes aplicados:
 * - Se lanza IllegalArgumentException para arreglos null y vacíos.
 * - Se agregan comprobaciones ejecutables para los casos válidos y entradas
 *   inválidas, incluidos valores negativos repetidos y Integer.MIN_VALUE.
 */
public final class MinimoVector {

    private MinimoVector() {
    }

    /**
     * Devuelve el menor valor del vector.
     *
     * @param vector arreglo no nulo y con al menos un elemento
     * @return el valor mínimo
     * @throws IllegalArgumentException si el vector es null o está vacío
     */
    public static int encontrarMinimo(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }
        if (vector.length == 0) {
            throw new IllegalArgumentException("El vector no puede estar vacío.");
        }

        /*
         * No se ordena el vector para obtener el mínimo. Un ordenamiento
         * eficiente por comparaciones suele costar O(n log n), y aquí solo
         * necesitamos conocer el menor valor, no el orden de todos los datos.
         * Este recorrido inspecciona cada elemento una vez, por lo que cuesta
         * O(n), usa O(1) espacio auxiliar y deja intacto el arreglo. Además,
         * no se puede detener antes: cualquier elemento restante podría ser
         * menor que el candidato actual.
         */
        int minimo = vector[0];
        for (int i = 1; i < vector.length; i++) {
            if (vector[i] < minimo) {
                minimo = vector[i];
            }
        }
        return minimo;
    }

    public static void main(String[] args) {
        probarMinimo("Positivos desordenados", new int[]{7, 12, 3, 9, 15}, 3);
        probarMinimo("Negativos", new int[]{-5, -20, -1}, -20);
        probarMinimo("Mínimo repetido", new int[]{4, -8, 2, -8, 10}, -8);
        probarMinimo("Mínimo al inicio", new int[]{-10, 3, 0, 8}, -10);
        probarMinimo("Mínimo al final", new int[]{5, 2, 9, -4}, -4);
        probarMinimo("Un solo elemento", new int[]{42}, 42);
        probarMinimo("Integer.MIN_VALUE", new int[]{0, Integer.MIN_VALUE, 7},
                Integer.MIN_VALUE);

        probarEntradaInvalida("Vector null", null);
        probarEntradaInvalida("Vector vacío", new int[0]);
    }

    private static void probarMinimo(String nombre, int[] vector, int esperado) {
        int obtenido = encontrarMinimo(vector);
        if (obtenido != esperado) {
            throw new AssertionError(
                    nombre + ": esperado " + esperado + ", obtenido " + obtenido);
        }
        System.out.println("OK - " + nombre + ": mínimo = " + obtenido);
    }

    private static void probarEntradaInvalida(String nombre, int[] vector) {
        try {
            encontrarMinimo(vector);
            throw new AssertionError(nombre + ": se esperaba IllegalArgumentException.");
        } catch (IllegalArgumentException excepcion) {
            System.out.println("OK - " + nombre + ": IllegalArgumentException");
        }
    }
}
