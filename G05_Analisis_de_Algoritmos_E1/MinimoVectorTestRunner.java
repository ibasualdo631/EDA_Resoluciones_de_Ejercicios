import java.util.Arrays;

/**
 * Pruebas de consola sin dependencias externas para MinimoVector.
 * Ejecutar desde este directorio con:
 *   javac MinimoVector.java MinimoVectorTestRunner.java
 *   java MinimoVectorTestRunner
 */
public final class MinimoVectorTestRunner {

    private static int pruebasEjecutadas;

    private MinimoVectorTestRunner() {
    }

    public static void main(String[] args) {
        probarMinimo("Desordenado con valores variados",
                new int[]{12, -3, 25, 0, -11, 8}, -11);
        probarMinimo("Un elemento", new int[]{42}, 42);
        probarMinimo("Negativos", new int[]{-5, -20, -1}, -20);
        probarMinimo("Mínimo duplicado", new int[]{4, -8, 2, -8, 10}, -8);
        probarMinimo("Ascendente", new int[]{-7, -2, 0, 3, 9}, -7);
        probarMinimo("Descendente", new int[]{9, 3, 0, -2, -7}, -7);
        probarMinimo("Mínimo al inicio", new int[]{-10, 3, 0, 8}, -10);
        probarMinimo("Mínimo en medio", new int[]{6, 2, -4, 5, 8}, -4);
        probarMinimo("Mínimo al final", new int[]{5, 2, 9, -4}, -4);
        probarMinimo("Límite inferior de int",
                new int[]{0, Integer.MIN_VALUE, 7}, Integer.MIN_VALUE);
        probarExcepcion("Arreglo vacío", new int[0]);
        probarExcepcion("Arreglo null", null);

        System.out.println("Resultado: " + pruebasEjecutadas + " pruebas aprobadas.");
    }

    private static void probarMinimo(String nombre, int[] vector, int esperado) {
        int[] copiaOriginal = vector.clone();
        int obtenido = MinimoVector.encontrarMinimo(vector);

        afirmar(obtenido == esperado,
                nombre + ": esperado " + esperado + ", obtenido " + obtenido);
        afirmar(Arrays.equals(vector, copiaOriginal),
                nombre + ": el método modificó el arreglo");

        pruebasEjecutadas++;
        System.out.println("OK - " + nombre + ": mínimo = " + obtenido);
    }

    private static void probarExcepcion(String nombre, int[] vector) {
        try {
            MinimoVector.encontrarMinimo(vector);
            throw new AssertionError(nombre + ": se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException esperada) {
            pruebasEjecutadas++;
            System.out.println("OK - " + nombre + ": IllegalArgumentException");
        }
    }

    private static void afirmar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
    }
}
