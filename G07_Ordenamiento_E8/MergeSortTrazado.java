/*
 * MergeSortTrazado.java
 * Ejercicio 8: implementación visual de MergeSort en Java.
 * El programa muestra en consola la división recursiva, la condición de corte
 * y la fase de fusión paso a paso para comprender cómo funciona el algoritmo.
 */

import java.util.Arrays;

public class MergeSortTrazado {

    public static void main(String[] args) {
        int[] arreglo = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("=== MergeSort paso a paso ===");
        System.out.println("Arreglo original: " + Arrays.toString(arreglo));
        System.out.println();

        mergeSort(arreglo, 0, arreglo.length - 1);

        System.out.println();
        System.out.println("Arreglo final ordenado: " + Arrays.toString(arreglo));
    }

    private static void mergeSort(int[] arr, int inicio, int fin) {
        if (inicio >= fin) {
            int[] subArreglo = Arrays.copyOfRange(arr, inicio, fin + 1);
            System.out.println("Condición de corte: " + Arrays.toString(subArreglo)
                    + " => ya está ordenado, porque tiene 0 o 1 elemento.");
            return;
        }

        int medio = inicio + (fin - inicio) / 2;
        int[] izquierda = Arrays.copyOfRange(arr, inicio, medio + 1);
        int[] derecha = Arrays.copyOfRange(arr, medio + 1, fin + 1);

        System.out.println("Dividiendo subarreglo: " + Arrays.toString(Arrays.copyOfRange(arr, inicio, fin + 1))
                + " -> izquierda: " + Arrays.toString(izquierda)
                + " | derecha: " + Arrays.toString(derecha));

        mergeSort(arr, inicio, medio);
        mergeSort(arr, medio + 1, fin);

        merge(arr, inicio, medio, fin);
    }

    private static void merge(int[] arr, int inicio, int medio, int fin) {
        int[] auxiliar = new int[fin - inicio + 1];
        int i = inicio;
        int j = medio + 1;
        int k = 0;

        System.out.println("Fusionando: "
                + Arrays.toString(Arrays.copyOfRange(arr, inicio, medio + 1))
                + " con "
                + Arrays.toString(Arrays.copyOfRange(arr, medio + 1, fin + 1)));

        while (i <= medio && j <= fin) {
            if (arr[i] <= arr[j]) {
                auxiliar[k] = arr[i];
                i++;
            } else {
                auxiliar[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= medio) {
            auxiliar[k] = arr[i];
            i++;
            k++;
        }

        while (j <= fin) {
            auxiliar[k] = arr[j];
            j++;
            k++;
        }

        for (int p = 0; p < k; p++) {
            arr[inicio + p] = auxiliar[p];
        }

        System.out.println("Resultado de la fusión: " + Arrays.toString(Arrays.copyOfRange(arr, inicio, fin + 1)));
        System.out.println("Arreglo actual: " + Arrays.toString(arr));
        System.out.println();
    }
}
