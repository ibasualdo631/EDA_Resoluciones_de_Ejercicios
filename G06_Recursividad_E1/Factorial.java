/*
 * Prompt inicial:
 * Implementá en Java una función recursiva que calcule el factorial de un
 * número entero no negativo. Usá una función con firma
 * public static long factorial(int n).
 *
 * Explicá en comentarios breves o en una explicación posterior por qué el
 * factorial puede resolverse recursivamente: cada caso se expresa usando el
 * factorial de un valor menor.
 *
 * Incluí los casos base n == 0 y n == 1, que deben devolver 1. Para n > 1,
 * reducí el problema devolviendo n * factorial(n - 1). Explicá que n disminuye
 * en cada llamada hasta alcanzar el caso base.
 *
 * Rechazá los valores negativos con IllegalArgumentException. Para evitar
 * desbordamientos del tipo long, aceptá únicamente valores entre 0 y 20,
 * inclusive, y rechazá valores mayores indicando claramente el límite.
 *
 * Agregá pruebas o ejemplos verificables para factorial(0) = 1,
 * factorial(1) = 1, factorial(5) = 120 y
 * factorial(20) = 2432902008176640000. Incluí también comprobaciones de que
 * -1 y 21 se rechazan. Mantené la solución enfocada en la recursión y no
 * reemplaces el cálculo por un ciclo.
 */
public class Factorial {
    private static final int MAXIMO_FACTORIAL_LONG = 20;

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El argumento no puede ser negativo.");
        }
        if (n > MAXIMO_FACTORIAL_LONG) {
            throw new IllegalArgumentException(
                    "El argumento no puede ser mayor que " + MAXIMO_FACTORIAL_LONG + ".");
        }
        if (n == 0 || n == 1) {
            return 1;
        }

        // Cada llamada reduce n hasta llegar a uno de los casos base.
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        verificarResultado(0, 1);
        verificarResultado(1, 1);
        verificarResultado(5, 120);
        verificarResultado(20, 2432902008176640000L);
        verificarRechazo(-1);
        verificarRechazo(21);

        System.out.println("factorial(0) = " + factorial(0));
        System.out.println("factorial(1) = " + factorial(1));
        System.out.println("factorial(5) = " + factorial(5));
    }

    private static void verificarResultado(int entrada, long esperado) {
        long resultado = factorial(entrada);
        if (resultado != esperado) {
            throw new AssertionError(
                    "factorial(" + entrada + "): se esperaba " + esperado + " y se obtuvo " + resultado);
        }
    }

    private static void verificarRechazo(int entrada) {
        try {
            factorial(entrada);
        } catch (IllegalArgumentException exception) {
            return;
        }
        throw new AssertionError("factorial(" + entrada + ") debía lanzar IllegalArgumentException");
    }
}
