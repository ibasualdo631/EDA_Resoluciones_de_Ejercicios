/*
 * Ejercicio 4 — Detectar elementos duplicados en un vector
 * Lenguaje: Java
 * Objetivo: implementar dos métodos equivalentes para detectar duplicados.
 * Estrategias: fuerza bruta (O(1) de espacio auxiliar) y HashSet (O(n) de espacio).
 *
 * Prompt inicial utilizado:
 * "Diseña una clase Java DetectorDuplicados con los métodos públicos estáticos
 * tieneDuplicadosFuerzaBruta(int[] vector) y tieneDuplicadosHashSet(int[] vector).
 * La fuerza bruta compara cada par (i, j), con j desde i + 1, sin memoria auxiliar;
 * HashSet recorre el vector una vez y detecta duplicados cuando add(valor) devuelve
 * false. Ambas soluciones informan si existe al menos un duplicado."
 *
 * Ajustes incorporados desde el plan técnico:
 * - null lanza IllegalArgumentException; vectores de longitud <= 1 retornan false.
 * - La entrada no se modifica y ambas estrategias cortan al hallar una coincidencia.
 * - HashSet se describe con tiempo O(n) promedio bajo la hipótesis de baja colisión.
 * - El banco de pruebas comprueba los resultados esperados, su paridad y el contrato null.
 */
import java.util.HashSet;

public final class DetectorDuplicados {

    private DetectorDuplicados() {
    }

    private static void validarVector(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }
    }

    /**
     * Busca pares iguales comparando cada elemento con los que le siguen.
     * En el caso sin duplicados toma O(n^2) tiempo y O(1) espacio auxiliar.
     */
    public static boolean tieneDuplicadosFuerzaBruta(int[] vector) {
        validarVector(vector);

        int n = vector.length;
        if (n <= 1) {
            return false;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (vector[i] == vector[j]) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Conserva los valores ya vistos: O(n) tiempo promedio bajo la hipótesis
     * de baja colisión y O(n) espacio auxiliar, frente al O(1) de fuerza bruta.
     */
    public static boolean tieneDuplicadosHashSet(int[] vector) {
        validarVector(vector);

        if (vector.length <= 1) {
            return false;
        }

        HashSet<Integer> vistos = new HashSet<>();
        for (int valor : vector) {
            if (!vistos.add(valor)) {
                return true;
            }
        }

        return false;
    }

    private static void verificarCaso(String nombre, int[] vector, boolean esperado) {
        boolean resultadoFuerzaBruta = tieneDuplicadosFuerzaBruta(vector);
        boolean resultadoHashSet = tieneDuplicadosHashSet(vector);

        if (resultadoFuerzaBruta != esperado || resultadoHashSet != esperado) {
            throw new AssertionError(
                    nombre + ": esperado " + esperado
                            + ", fuerza bruta=" + resultadoFuerzaBruta
                            + ", HashSet=" + resultadoHashSet);
        }

        System.out.println("OK: " + nombre);
    }

    private static void verificarRechazoDeNull() {
        boolean fuerzaBrutaRechazaNull = false;
        try {
            tieneDuplicadosFuerzaBruta(null);
        } catch (IllegalArgumentException exception) {
            fuerzaBrutaRechazaNull = true;
        }

        boolean hashSetRechazaNull = false;
        try {
            tieneDuplicadosHashSet(null);
        } catch (IllegalArgumentException exception) {
            hashSetRechazaNull = true;
        }

        if (!fuerzaBrutaRechazaNull || !hashSetRechazaNull) {
            throw new AssertionError("Ambos métodos deben rechazar null.");
        }

        System.out.println("OK: ambos métodos rechazan null");
    }

    public static void main(String[] args) {
        verificarCaso("duplicado adyacente al inicio", new int[]{4, 4, 2}, true);
        verificarCaso("duplicado en los extremos", new int[]{7, 3, 5, 7}, true);
        verificarCaso("todos los elementos distintos", new int[]{1, 2, 3, 4}, false);
        verificarCaso("vector de un elemento", new int[]{9}, false);
        verificarCaso("vector vacío", new int[]{}, false);
        verificarCaso("negativos y positivos con duplicado", new int[]{-3, 10, 5, -3}, true);
        verificarCaso("múltiples duplicados", new int[]{1, 2, 1, 2}, true);

        int[] vectorGrandeSinDuplicados = new int[1_000];
        for (int i = 0; i < vectorGrandeSinDuplicados.length; i++) {
            vectorGrandeSinDuplicados[i] = i;
        }
        verificarCaso("vector grande sin duplicados", vectorGrandeSinDuplicados, false);

        verificarRechazoDeNull();
        System.out.println("Todas las pruebas finalizaron correctamente.");
    }
}
