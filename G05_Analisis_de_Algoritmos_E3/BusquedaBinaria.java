/**
 * Institución: [Nombre de la institución]
 * Carrera / asignatura: [Carrera] — Estructuras de Datos y Algoritmos
 * Trabajo práctico: G05 — Análisis de Algoritmos
 * Ejercicio: 3 — Búsqueda binaria en un vector ordenado
 * Estudiante(s): [Nombre(s) y apellido(s)]
 * Docente: [Nombre del/de la docente]
 * Fecha: [Fecha de entrega]
 *
 * Prompt inicial utilizado:
 * "Implementa en Java una búsqueda binaria iterativa sobre un vector ordenado.
 * Explica su eficiencia frente a la búsqueda lineal y muestra paso a paso las
 * variables de búsqueda."
 *
 * Ajustes del blueprint:
 * - Clase BusquedaBinaria y firma buscar(int[], int).
 * - Límites inclusivos y cálculo seguro del medio.
 * - null produce IllegalArgumentException; vector vacío retorna -1.
 * - Traza por iteración y banco de pruebas ejecutable desde main.
 */
public final class BusquedaBinaria {

    private BusquedaBinaria() {
        // Clase de utilidad: no requiere instancias.
    }

    /**
     * Busca un objetivo en un vector ordenado ascendentemente.
     *
     * @param vector vector ordenado ascendentemente; no puede ser null
     * @param objetivo valor que se desea localizar
     * @return índice de una coincidencia o -1 si no existe
     * @throws IllegalArgumentException si vector es null
     */
    public static int buscar(int[] vector, int objetivo) {
        if (vector == null) {
            System.err.println("Error: el vector no puede ser null.");
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        int low = 0;
        int high = vector.length - 1;
        int paso = 1;

        /*
         * La precondición de orden ascendente permite descartar una mitad completa
         * tras cada comparación. Por eso el peor caso es O(log n), frente a O(n)
         * de una búsqueda lineal que puede revisar todos los elementos.
         */
        System.out.println("Objetivo: " + objetivo + " | Tamaño del vector: " + vector.length);
        System.out.println("Iteración | inicio | fin | medio | valor medio | comparativa con " + objetivo);

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int valorMedio = vector[mid];
            String comparativa;

            if (valorMedio == objetivo) {
                comparativa = "igual; encontrado";
            } else if (objetivo < valorMedio) {
                comparativa = "objetivo < valor; fin = " + (mid - 1);
            } else {
                comparativa = "objetivo > valor; inicio = " + (mid + 1);
            }

            System.out.printf(
                    "[Paso %d] inicio: %d | fin: %d | medio: %d | valor medio: %d | comparativa con %d: %s%n",
                    paso, low, high, mid, valorMedio, objetivo, comparativa);

            if (valorMedio == objetivo) {
                System.out.println("Resultado: índice " + mid);
                return mid;
            }

            if (objetivo < valorMedio) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            paso++;
        }

        System.out.println("Intervalo agotado: inicio: " + low + " | fin: " + high);
        System.out.println("Resultado: no encontrado (-1)");
        return -1;
    }

    public static void main(String[] args) {
        ejecutarPrueba("Elemento en el medio", new int[] {2, 5, 8, 12, 16}, 8, 2);
        ejecutarPrueba("Elemento en el extremo inicial", new int[] {2, 5, 8, 12, 16}, 2, 0);
        ejecutarPrueba("Elemento en el extremo final", new int[] {2, 5, 8, 12, 16}, 16, 4);
        ejecutarPrueba("Inexistente debajo del mínimo", new int[] {2, 5, 8}, 1, -1);
        ejecutarPrueba("Inexistente encima del máximo", new int[] {2, 5, 8}, 9, -1);
        ejecutarPrueba("Inexistente entre elementos", new int[] {2, 5, 8, 12}, 9, -1);
        ejecutarPrueba("Vector unitario coincidente", new int[] {7}, 7, 0);
        ejecutarPrueba("Vector unitario no coincidente", new int[] {7}, 4, -1);
        ejecutarPrueba("Vector vacío", new int[] {}, 4, -1);
        ejecutarPruebaDuplicados();
        probarVectorNull();
    }

    private static void ejecutarPrueba(String nombre, int[] vector, int objetivo, int resultadoEsperado) {
        System.out.println("\n=== " + nombre + " ===");
        int[] copiaOriginal = vector.clone();
        int resultado = buscar(vector, objetivo);
        boolean correcto = resultado == resultadoEsperado
                && java.util.Arrays.equals(vector, copiaOriginal);

        System.out.println((correcto ? "PRUEBA OK" : "PRUEBA FALLIDA")
                + " | esperado: " + resultadoEsperado
                + " | obtenido: " + resultado
                + " | vector sin modificar: " + java.util.Arrays.equals(vector, copiaOriginal));

        if (!correcto) {
            throw new AssertionError("Falló la prueba: " + nombre);
        }
    }

    private static void ejecutarPruebaDuplicados() {
        String nombre = "Vector con duplicados";
        int[] vector = {1, 3, 3, 3, 9};
        int objetivo = 3;
        int[] copiaOriginal = vector.clone();

        System.out.println("\n=== " + nombre + " ===");
        int resultado = buscar(vector, objetivo);
        boolean correcto = resultado >= 0
                && resultado < vector.length
                && vector[resultado] == objetivo
                && java.util.Arrays.equals(vector, copiaOriginal);

        System.out.println((correcto ? "PRUEBA OK" : "PRUEBA FALLIDA")
                + " | esperado: cualquier índice con valor " + objetivo
                + " | obtenido: " + resultado);

        if (!correcto) {
            throw new AssertionError("Falló la prueba: " + nombre);
        }
    }

    private static void probarVectorNull() {
        System.out.println("\n=== Vector null ===");
        try {
            buscar(null, 4);
            throw new AssertionError("Se esperaba IllegalArgumentException para null.");
        } catch (IllegalArgumentException expected) {
            System.out.println("PRUEBA OK | IllegalArgumentException: " + expected.getMessage());
        }
    }
}
