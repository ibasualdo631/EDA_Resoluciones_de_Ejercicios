/*
 * ============================================================================
 * Prompt inicial utilizado:
 * Programá QuickSortPeorCaso.java. El main debe ejecutar el algoritmo sobre el 
 * arreglo ordenado de ejemplo e imprimir el contador final de llamadas recursivas.
 *
 * Ajustes y Refinamientos realizados:
 * - Se incorporó un contador estático de telemetría para medir el impacto de
 *   elegir el primer elemento como pivote en un arreglo ya ordenado.
 * - Se estructuró el código separando el método de encapsulamiento público del
 *   método recursivo privado, garantizando que el contador inicie en 0.
 * ============================================================================
 */

public class QuickSortPeorCaso {

    // Contador estático para registrar las llamadas al método recursivo
    private static int contadorLlamadas = 0;

    public static void quicksort(int[] arreglo) {
        contadorLlamadas = 0; // Se reinicia el contador al inicio
        if (arreglo != null && arreglo.length > 0) {
            quicksortRecursivo(arreglo, 0, arreglo.length - 1);
        }
        System.out.println("Cantidad de llamadas recursivas: " + contadorLlamadas);
    }

    private static void quicksortRecursivo(int[] arreglo, int izquierda, int derecha) {
        // Se incrementa el contador al entrar a cada nuevo marco de la pila (Call Stack)
        contadorLlamadas++;

        // CASO BASE: Si la partición tiene 0 o 1 elemento, se detiene la recursión
        if (izquierda >= derecha) {
            return;
        }

        // PIVOTE: Se elige siempre el primer elemento de la sublista actual
        int pivote = arreglo[izquierda];
        int i = izquierda + 1;
        int j = derecha;

        // Proceso de partición (agrupación de menores a la izq. y mayores a la der.)
        while (i <= j) {
            while (i <= j && arreglo[i] <= pivote) {
                i++;
            }
            while (i <= j && arreglo[j] > pivote) {
                j--;
            }
            if (i <= j) {
                intercambiar(arreglo, i, j);
                i++;
                j--;
            }
        }

        // Colocar el pivote en su posición definitiva intercalada
        intercambiar(arreglo, izquierda, j);

        // CASO RECURSIVO: Llamadas sobre los subarreglos resultantes
        quicksortRecursivo(arreglo, izquierda, j - 1);
        quicksortRecursivo(arreglo, j + 1, derecha);
    }

    private static void intercambiar(int[] arreglo, int i, int j) {
        int aux = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = aux;
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBA: PEOR CASO DE QUICKSORT ===");
        
        // Arreglo de ejemplo ya ordenado de forma ascendente
        int[] numeros = {1, 2, 3, 4, 5, 6, 7};
        
        System.out.print("Arreglo original: ");
        imprimirArreglo(numeros);
        
        // Ejecutar el ordenamiento y mostrar las métricas
        quicksort(numeros);
        
        System.out.print("Arreglo (ya estaba) ordenado: ");
        imprimirArreglo(numeros);
    }
    
    // Método auxiliar para visualizar arreglos
    private static void imprimirArreglo(int[] arreglo) {
        System.out.print("[");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i]);
            if (i < arreglo.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}