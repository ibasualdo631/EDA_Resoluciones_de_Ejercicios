/*
 * Prompt empleado:
 * Implementa en lenguaje Java la clase CompararVectores. Incluí este bloque
 * de comentarios al inicio y un método compararVectores(). Si uno de los
 * vectores es null, lanzá IllegalArgumentException. Comentá por qué no es
 * necesario (aunque sí puede serlo) comparar los dos vectores por completo.
 * Incluí un método main con pruebas de vector nulo, vectores iguales,
 * vectores distintos y vectores con los mismos elementos iniciales pero
 * distinto tamaño, como [1, 2, 3] y [1, 2, 3, 4, 5].
 */
public class CompararVectores {

    public static boolean compararVectores(int[] vectorA, int[] vectorB) {
        if (vectorA == null || vectorB == null) {
            throw new IllegalArgumentException("Los vectores no pueden ser null.");
        }

        if (vectorA.length != vectorB.length) {
            return false;
        }

        for (int i = 0; i < vectorA.length; i++) {
            if (vectorA[i] != vectorB[i]) {
                // Basta encontrar una diferencia para demostrar que no son iguales.
                // Solo es necesario comparar todos los elementos si coinciden hasta el final.
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        verificarNull("Vector A nulo", null, new int[]{1, 2, 3});
        verificarNull("Vector B nulo", new int[]{1, 2, 3}, null);
        verificarNull("Ambos vectores nulos", null, null);
        verificar("Vectores iguales", compararVectores(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3}), true);
        verificar("Vectores distintos", compararVectores(
                new int[]{1, 2, 3},
                new int[]{1, 9, 3}), false);
        verificar("Mismos elementos iniciales y distinto tamaño", compararVectores(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3, 4, 5}), false);
        verificar("Vectores vacíos", compararVectores(
                new int[]{},
                new int[]{}), true);
    }

    private static void verificarNull(String caso, int[] vectorA, int[] vectorB) {
        try {
            compararVectores(vectorA, vectorB);
            throw new AssertionError(caso + ": se esperaba IllegalArgumentException.");
        } catch (IllegalArgumentException exception) {
            System.out.println(caso + ": excepción esperada");
        }
    }

    private static void verificar(String caso, boolean resultado, boolean esperado) {
        if (resultado != esperado) {
            throw new AssertionError(caso + ": se esperaba " + esperado + ", se obtuvo " + resultado);
        }

        System.out.println(caso + ": " + resultado);
    }
}
