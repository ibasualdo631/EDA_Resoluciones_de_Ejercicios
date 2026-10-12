/*
 * ShellSort ordena el arreglo mediante pasadas de inserción con gaps
 * decrecientes. Los gaps grandes pueden mover elementos muchas posiciones
 * en una sola inserción y reducen pronto el desorden global. Así, al llegar
 * al gap 1, quedan menos elementos lejos de su posición: la inserción final
 * suele desplazar menos valores y hacer menos comparaciones que si se
 * aplicara Insertion Sort al arreglo original. El gap 1 asegura el orden
 * completo; el ahorro concreto depende de la entrada y de la secuencia usada.
 */
import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        ejecutarCaso("Longitud impar", new int[]{8, 1, 7, 2, 6, 3, 5, 4, 9});
        ejecutarCaso("Longitud par", new int[]{8, 3, 7, 4, 6, 2, 5, 1});
    }

    private static void ejecutarCaso(String nombre, int[] arreglo) {
        System.out.println(nombre + ":");
        System.out.println("Arreglo inicial: " + Arrays.toString(arreglo));
        shellSort(arreglo);
        System.out.println("Arreglo ordenado: " + Arrays.toString(arreglo));
        System.out.println();
    }

    private static void shellSort(int[] arreglo) {
        for (int gap = arreglo.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arreglo.length; i++) {
                int valor = arreglo[i];
                int j = i;

                while (j >= gap && arreglo[j - gap] > valor) {
                    arreglo[j] = arreglo[j - gap];
                    j -= gap;
                }

                arreglo[j] = valor;
            }

            System.out.println("Gap actual: " + gap);
            System.out.println(Arrays.toString(arreglo));
        }
    }
}
