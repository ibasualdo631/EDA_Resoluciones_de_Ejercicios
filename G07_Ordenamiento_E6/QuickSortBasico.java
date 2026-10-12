/*
 Prompt:
 Implementar Quicksort usando siempre el primer elemento como pivote.
 El programa debe mostrar por consola:
 1) pivote elegido;
 2) subarreglo izquierdo;
 3) subarreglo derecho;
 4) arreglo final ordenado.
 El objetivo es relacionar el algoritmo con la estrategia de dividir y conquistar y con la recursividad.
 */

import java.util.Arrays;

public class QuickSortBasico {

    public static void main(String[] args) {
        int[][] casosNormales = {
            {12, 5, 8, 3, 1, 9, 7, 2, 6, 10, 4},
            {9, 1, 5, 8, 2, 7},
            {3, 2, 1},
            {1, 2, 3, 4},
            {4, 3, 2, 1},
            {6, 2, 7, 1, 5, 3}
        };

        System.out.println("=== Validacion de particion ===");
        validarMatrizCasos(casosNormales);

        System.out.println("\n=== Demostracion del QuickSort ===");
        int[] arreglo = {12, 5, 8, 3, 1, 9, 7, 2, 6, 10, 4};
        quickSort(arreglo, 0, arreglo.length - 1);
    }

    public static void validarMatrizCasos(int[][] casosNormales) {
        for (int i = 0; i < casosNormales.length; i++) {
            int[] caso = Arrays.copyOf(casosNormales[i], casosNormales[i].length);
            int pivote = caso[0];
            int posicionPivote = particionar(caso, 0, caso.length - 1);

            boolean agrupacionCorrecta = true;
            for (int indice = 0; indice < posicionPivote; indice++) {
                if (caso[indice] > pivote) {
                    agrupacionCorrecta = false;
                }
            }
            for (int indice = posicionPivote + 1; indice < caso.length; indice++) {
                if (caso[indice] <= pivote) {
                    agrupacionCorrecta = false;
                }
            }

            System.out.println("Caso " + (i + 1) + ": " + Arrays.toString(casosNormales[i]));
            System.out.println("Pivote: " + pivote + " | posicion final: " + posicionPivote + " | agrupacion correcta: " + agrupacionCorrecta);
            System.out.println("Resultado particion: " + Arrays.toString(caso));
            System.out.println();
        }
    }

    public static void quickSort(int[] arreglo, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        int posicionPivote = particionar(arreglo, inicio, fin);

        int[] izquierdo = Arrays.copyOfRange(arreglo, inicio, posicionPivote);
        int[] derecho = Arrays.copyOfRange(arreglo, posicionPivote + 1, fin + 1);

        mostrarEstado(arreglo, arreglo[posicionPivote], izquierdo, derecho);

        quickSort(arreglo, inicio, posicionPivote - 1);
        quickSort(arreglo, posicionPivote + 1, fin);
    }

    public static int particionar(int[] arreglo, int inicio, int fin) {
        int pivote = arreglo[inicio];
        int i = inicio + 1;
        int j = fin;

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

        intercambiar(arreglo, inicio, j);
        return j;
    }

    public static void mostrarEstado(int[] arreglo, int pivote, int[] izquierdo, int[] derecho) {
        System.out.println("Pivote elegido: " + pivote);
        System.out.println("Subarreglo izquierdo: " + Arrays.toString(izquierdo));
        System.out.println("Subarreglo derecho: " + Arrays.toString(derecho));
        System.out.println("Arreglo final ordenado: " + Arrays.toString(arreglo));
    }

    public static void intercambiar(int[] arreglo, int i, int j) {
        int aux = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = aux;
    }
}
