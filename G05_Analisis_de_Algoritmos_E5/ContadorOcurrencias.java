/*
 * Prompt utilizado:
 * Implementá en Java un método que reciba un arreglo de enteros (int[]) y un
 * valor objetivo (int), y devuelva cuántas veces aparece ese valor en el
 * arreglo. Recorré todas las posiciones y aumentá un contador por cada
 * coincidencia. No uses break, return anticipado ni ninguna otra forma de
 * cortar el recorrido al encontrar una coincidencia: todavía podrían existir
 * más apariciones y se necesita el total. Para un arreglo vacío, devolvé 0;
 * si el arreglo es null, rechazá la entrada lanzando IllegalArgumentException;
 * si el objetivo no aparece, devolvé 0. Agregá pruebas o ejemplos que cubran
 * esos casos y también una coincidencia y varias coincidencias. Explicá por
 * qué el recorrido completo es obligatorio y justificá la complejidad: tiempo
 * O(n) en todos los casos y espacio adicional O(1).
 */
public class ContadorOcurrencias {

    public static int contarOcurrencias(int[] vector, int objetivo) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        int contador = 0;
        for (int elemento : vector) {
            // Se debe revisar cada posición para contar todas las apariciones;
            // una coincidencia no garantiza que no haya otras más adelante.
            if (elemento == objetivo) {
                contador++;
            }
        }

        return contador;
    }

    public static void main(String[] args) {
        int[] conVariasApariciones = {4, 2, 4, 4, 1};
        int[] sinApariciones = {1, 2, 3};
        int[] todosIguales = {7, 7, 7, 7};
        int[] vectorVacio = {};

        System.out.println("Varias apariciones (esperado 3): "
                + contarOcurrencias(conVariasApariciones, 4));
        System.out.println("Objetivo inexistente (esperado 0): "
                + contarOcurrencias(sinApariciones, 9));
        System.out.println("Todos iguales (esperado 4): "
                + contarOcurrencias(todosIguales, 7));
        System.out.println("Vector vacío (esperado 0): "
                + contarOcurrencias(vectorVacio, 4));
    }
}
