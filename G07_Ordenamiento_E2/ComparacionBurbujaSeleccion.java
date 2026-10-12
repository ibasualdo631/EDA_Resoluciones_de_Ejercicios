/*
 * Ejercicio: comparar los algoritmos Bubble Sort y Selection Sort.
 * Ambos ordenan el arreglo en forma ascendente e informan sus comparaciones
 * e intercambios.
 */
import java.util.Arrays;

public class ComparacionBurbujaSeleccion {

    public static void main(String[] args) {
        int[] arregloOriginal = {64, 34, 25, 12, 22, 11, 90};
        int[] arregloBurbuja = arregloOriginal.clone();
        int[] arregloSeleccion = arregloOriginal.clone();

        System.out.println("Bubble Sort:");
        bubbleSort(arregloBurbuja);
        System.out.println("Arreglo ordenado: " + Arrays.toString(arregloBurbuja));

        System.out.println("\nSelection Sort:");
        selectionSort(arregloSeleccion);
        System.out.println("Arreglo ordenado: " + Arrays.toString(arregloSeleccion));
    }

    private static void bubbleSort(int[] array) {
        long comparaciones = 0;
        long intercambios = 0;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                comparaciones++;
                if (array[j] > array[j + 1]) {
                    int temporal = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temporal;
                    intercambios++;
                }
            }
        }

        System.out.println("Comparaciones: " + comparaciones);
        System.out.println("Intercambios: " + intercambios);
    }

    private static void selectionSort(int[] array) {
        long comparaciones = 0;
        long intercambios = 0;

        for (int i = 0; i < array.length - 1; i++) {
            int indiceMinimo = i;

            for (int j = i + 1; j < array.length; j++) {
                comparaciones++;
                if (array[j] < array[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }

            if (indiceMinimo != i) {
                int temporal = array[i];
                array[i] = array[indiceMinimo];
                array[indiceMinimo] = temporal;
                intercambios++;
            }
        }

        System.out.println("Comparaciones: " + comparaciones);
        System.out.println("Intercambios: " + intercambios);
    }
}
