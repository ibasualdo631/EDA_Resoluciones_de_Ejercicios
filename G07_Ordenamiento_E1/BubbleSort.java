/*
 * Prompt:
 * Implementa en Java un programa que ordene un arreglo de números enteros de
 * menor a mayor utilizando el algoritmo Bubble Sort. Declara en el programa un
 * arreglo de ejemplo, ya que no se especifica entrada interactiva. Antes de
 * ordenar, imprime el arreglo original. Luego, al finalizar cada pasada
 * completa, imprime el estado de todo el arreglo. En cada pasada compara
 * elementos adyacentes e intercámbialos únicamente cuando el de la izquierda
 * sea mayor que el de la derecha. Explica en comentarios breves o en una
 * explicación junto al código por qué el mayor elemento del tramo no ordenado
 * "flota" hacia su extremo derecho: cada intercambio lo desplaza una posición
 * a la derecha hasta quedar en su posición definitiva, lo que permite reducir
 * el rango de comparación en la pasada siguiente. Considera arreglos vacíos,
 * unitarios, ordenados y con valores repetidos; no agregues entrada interactiva
 * ni optimización de terminación anticipada. Mantén la solución clara y
 * adecuada para una práctica introductoria de estructuras de datos.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arreglo = {64, 34, 25, 12, 22, 11, 90};

        imprimirArreglo("Arreglo original: ", arreglo);
        bubbleSort(arreglo);
    }

    private static void imprimirArreglo(String etiqueta, int[] arreglo) {
        System.out.print(etiqueta + "[");
        for (int i = 0; i < arreglo.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(arreglo[i]);
        }
        System.out.println("]");
    }

    private static void bubbleSort(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - i - 1; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    int temporal = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temporal;
                }
            }

            // El mayor valor del tramo activo "flota" al extremo derecho.
            imprimirArreglo("Pasada " + (i + 1) + ": ", arreglo);
        }
    }
}
