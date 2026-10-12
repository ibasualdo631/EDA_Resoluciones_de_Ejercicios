/*
 * Prompt inicial:
 *
 * Implementá en Java el algoritmo Insertion Sort para ordenar en forma
 * ascendente el arreglo int[] numeros = {1, 2, 3, 5, 4, 6, 7}.
 *
 * El programa debe mostrar el arreglo ordenado y la cantidad de
 * desplazamientos realizados. Para este ejercicio, contá un desplazamiento
 * cada vez que un elemento existente se mueve una posición a la derecha para
 * dejar lugar a la clave (numeros[j + 1] = numeros[j]); no cuentes la escritura
 * final de la clave como desplazamiento.
 *
 * Después del código, explicá paso a paso cómo se procesa el 4, cuál es el
 * total de desplazamientos esperado y por qué Insertion Sort tiene un
 * rendimiento cercano a O(n) con esta entrada. Relacioná el costo con la
 * cantidad de inversiones: este arreglo tiene una sola (5 antes de 4), así
 * que solo se necesita un desplazamiento y el resto de los elementos ya está
 * en su posición relativa. Aclará que Insertion Sort puede llegar a O(n²) en
 * el peor caso general, y que "casi ordenado" no garantiza por sí solo tiempo
 * lineal si existen muchas inversiones.
 *
 * Mantené la solución sencilla, legible y adecuada para una persona que está
 * aprendiendo ordenamiento por inserción.
 */
public class InsertionSortCasiOrdenado {

    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 5, 4, 6, 7};

        int desplazamientos = insertionSort(numeros);

        System.out.print("Arreglo ordenado: ");
        mostrarArreglo(numeros);
        System.out.println("Desplazamientos realizados: " + desplazamientos);
    }

    private static int insertionSort(int[] numeros) {
        int desplazamientos = 0;

        for (int i = 1; i < numeros.length; i++) {
            int clave = numeros[i];
            int j = i - 1;

            while (j >= 0 && numeros[j] > clave) {
                numeros[j + 1] = numeros[j];
                desplazamientos++;
                j--;
            }

            numeros[j + 1] = clave;
        }

        return desplazamientos;
    }

    private static void mostrarArreglo(int[] numeros) {
        System.out.print("{");
        for (int i = 0; i < numeros.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(numeros[i]);
        }
        System.out.println("}");
    }
}
