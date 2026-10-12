/*
 * Prompt utilizado:
 * "Desarrollador: En base al plan tecnico, desarrolla el algoritmo. Respeta
 * estas consignas: bloque de comentarios al inicio con el prompt utilizado y
 * ajustes; método con dos bucles for anidados; lanzar IllegalArgumentException
 * si la matriz es null o está vacía; incluir un main de prueba con matriz 3x3,
 * matriz irregular, números negativos y positivos, matriz null y elementos
 * iguales."
 *
 * Ajustes según el plan técnico:
 * - Las filas null y vacías se omiten; si no hay ningún elemento, se lanza
 *   IllegalArgumentException.
 * - Se admite que las filas tengan longitudes distintas.
 * - El máximo se inicializa con el primer elemento real para admitir valores
 *   negativos y no se modifica la matriz recibida.
 */
public class ElementoMayorMatriz {

    private ElementoMayorMatriz() {
    }

    /**
     * Busca y devuelve el mayor elemento de una matriz de enteros.
     *
     * @param matriz matriz que se va a recorrer
     * @return el mayor elemento de la matriz
     * @throws IllegalArgumentException si la matriz es null o no contiene
     *                                  elementos
     */
    public static int buscarMayor(int[][] matriz) {
        if (matriz == null) {
            throw new IllegalArgumentException("La matriz no puede ser null.");
        }

        boolean encontrado = false;
        int mayor = 0;

        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i] == null) {
                continue;
            }

            for (int j = 0; j < matriz[i].length; j++) {
                int valorActual = matriz[i][j];
                if (!encontrado || valorActual > mayor) {
                    mayor = valorActual;
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            throw new IllegalArgumentException(
                    "La matriz debe contener al menos un elemento.");
        }

        return mayor;
    }

    public static void main(String[] args) {
        String[][] resultados = new String[11][3];
        int indice = 0;

        int[][] matriz3x3 = {
            {4, 12, 7},
            {3, 9, 2},
            {6, 1, 5}
        };
        resultados[indice++] = probarResultado("Matriz 3x3", matriz3x3, 12);

        int[][] matrizIrregular = {
            {3, 9, 1, 4},
            {2},
            {-5, 7}
        };
        resultados[indice++] = probarResultado("Matriz irregular", matrizIrregular, 9);

        int[][] matrizMixta = {
            {-8, 3},
            {-2, 0, 11},
            {-15, 6}
        };
        resultados[indice++] = probarResultado("Negativos y positivos", matrizMixta, 11);

        int[][] matrizIguales = {
            {5, 5},
            {5, 5, 5}
        };
        resultados[indice++] = probarResultado("Todos iguales", matrizIguales, 5);

        resultados[indice++] = probarExcepcion("Matriz null", null);
        resultados[indice++] = probarExcepcion("Sin filas", new int[0][]);
        resultados[indice++] = probarExcepcion("Filas vacías", new int[][] {{}, {}});
        resultados[indice++] = probarExcepcion("Filas null", new int[][] {null, null});
        resultados[indice++] = probarResultado(
                "Fila vacía/null y datos", new int[][] {{}, null, {-3, 7}}, 7);
        resultados[indice++] = probarResultado(
                "Solo negativos", new int[][] {{-8, -2}, {-7}}, -2);
        resultados[indice++] = probarResultado(
                "Integer.MIN_VALUE", new int[][] {{Integer.MIN_VALUE}}, Integer.MIN_VALUE);

        System.out.printf("%-25s | %-30s | %-10s%n", "Caso", "Esperado / obtenido", "Estado");
        System.out.println("--------------------------+--------------------------------+-----------");
        int fallos = 0;
        for (String[] resultado : resultados) {
            System.out.printf("%-25s | %-30s | %-10s%n",
                    resultado[0], resultado[1], resultado[2]);
            if ("FALLO".equals(resultado[2])) {
                fallos++;
            }
        }
        System.out.println("Total: " + (resultados.length - fallos) + " PASO, "
                + fallos + " FALLO.");
        if (fallos > 0) {
            throw new AssertionError("Fallaron " + fallos + " caso(s) de prueba.");
        }
    }

    private static String[] probarResultado(String nombre, int[][] matriz, int esperado) {
        try {
            int obtenido = buscarMayor(matriz);
            boolean correcto = obtenido == esperado;
            return new String[] {
                nombre,
                esperado + " / " + obtenido,
                correcto ? "PASO" : "FALLO"
            };
        } catch (RuntimeException excepcion) {
            return new String[] {
                nombre,
                esperado + " / lanzó " + excepcion.getClass().getSimpleName(),
                "FALLO"
            };
        }
    }

    private static String[] probarExcepcion(String nombre, int[][] matriz) {
        try {
            int obtenido = buscarMayor(matriz);
            return new String[] {
                nombre,
                "IllegalArgumentException / " + obtenido,
                "FALLO"
            };
        } catch (IllegalArgumentException excepcion) {
            return new String[] {
                nombre,
                "IllegalArgumentException / IllegalArgumentException",
                "PASO"
            };
        } catch (RuntimeException excepcion) {
            return new String[] {
                nombre,
                "IllegalArgumentException / " + excepcion.getClass().getSimpleName(),
                "FALLO"
            };
        }
    }
}
