/*
 * Prompt:
 * Implementá en Java una función recursiva que calcule base^exp sin usar
 * Math.pow ni otra función de biblioteca que calcule potencias.
 *
 * Explicá por qué una potencia puede verse como multiplicaciones sucesivas.
 * Usá exp == 0 como caso base y devolvé 1, el elemento neutro de la
 * multiplicación. Para exp > 0, reducí el exponente en uno y multiplicá
 * por potencia(base, exp - 1), acercándote así al caso base. Detectá y
 * reportá con ArithmeticException los resultados que excedan el rango de int.
 *
 * Considerá exponentes enteros no negativos y rechazá explícitamente los
 * negativos con IllegalArgumentException. Incluí ejemplos verificables para
 * potencia(5, 0) = 1, potencia(5, 1) = 5, potencia(2, 3) = 8,
 * potencia(0, 4) = 0 y potencia(-2, 3) = -8.
 */
public class Potencia {

    public static int potencia(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("El exponente no puede ser negativo.");
        }
        if (exp == 0) {
            return 1;
        }
        return Math.multiplyExact(base, potencia(base, exp - 1));
    }

    public static void main(String[] args) {
        verificarResultado(5, 0, 1);
        verificarResultado(5, 1, 5);
        verificarResultado(2, 3, 8);
        verificarResultado(0, 4, 0);
        verificarResultado(-2, 3, -8);
        verificarResultado(2, 30, 1_073_741_824);
        verificarExponenteNegativo();
        verificarDesbordamiento();

        System.out.println("Todas las verificaciones finalizaron correctamente.");
    }

    private static void verificarResultado(int base, int exp, int esperado) {
        int resultado = potencia(base, exp);
        if (resultado != esperado) {
            throw new AssertionError(
                    "potencia(" + base + ", " + exp + "): se esperaba "
                            + esperado + " y se obtuvo " + resultado);
        }
    }

    private static void verificarExponenteNegativo() {
        try {
            potencia(2, -1);
        } catch (IllegalArgumentException exception) {
            return;
        }
        throw new AssertionError("potencia(2, -1) debía lanzar IllegalArgumentException");
    }

    private static void verificarDesbordamiento() {
        try {
            potencia(2, 31);
        } catch (ArithmeticException exception) {
            return;
        }
        throw new AssertionError("potencia(2, 31) debía lanzar ArithmeticException");
    }
}
