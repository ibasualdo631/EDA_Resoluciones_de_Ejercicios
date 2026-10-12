/*
 * Prompt inicial:
 *
 * Implementá en Java Selection Sort para ordenar alfabéticamente un arreglo
 * String[] de nombres en el mismo arreglo, sin utilizar métodos de
 * ordenamiento de biblioteca. Compará los nombres no nulos con compareTo().
 * Rechazá un arreglo null y ubicá los elementos null al final. En el main,
 * probá el arreglo {"Lucia", "Ana", "Pedro", "Juan"} e imprimí el resultado.
 */
import java.util.Arrays;
import java.util.Objects;

public class SelectionSortNombres {

    public static void main(String[] args) {
        String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"};

        ordenarNombres(nombres);

        System.out.println("Nombres ordenados: " + Arrays.toString(nombres));
    }

    public static void ordenarNombres(String[] nombres) {
        Objects.requireNonNull(nombres, "nombres no puede ser null");

        for (int i = 0; i < nombres.length - 1; i++) {
            int indiceMenor = i;

            for (int j = i + 1; j < nombres.length; j++) {
                if (compararNombres(nombres[j], nombres[indiceMenor]) < 0) {
                    indiceMenor = j;
                }
            }

            if (indiceMenor != i) {
                String temporal = nombres[i];
                nombres[i] = nombres[indiceMenor];
                nombres[indiceMenor] = temporal;
            }
        }
    }

    private static int compararNombres(String primero, String segundo) {
        if (primero == segundo) {
            return 0;
        }
        if (primero == null) {
            return 1;
        }
        if (segundo == null) {
            return -1;
        }
        return primero.compareTo(segundo);
    }
}
