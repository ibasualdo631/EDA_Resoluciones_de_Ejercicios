/*
 * Prompt utilizado:
 * "Implementá en Java una función recursiva que sume los enteros desde n
 * hasta 1. Usá suma(0) = 0 como caso base y, para n > 0, calculá
 * n + suma(n - 1). Rechazá los valores negativos con
 * IllegalArgumentException. Probá los valores 0, 1 y 5, cuyos resultados
 * esperados son 0, 1 y 15, respectivamente. No reemplaces la recursión por
 * una fórmula cerrada ni por un ciclo."
 *
 * La validación se realiza una sola vez en el método público; el método
 * auxiliar aplica la recurrencia sobre un argumento no negativo.
 */
public final class SumaNumeros {

    private SumaNumeros() {
    }

    public static long sumaRecursiva(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El argumento debe ser no negativo.");
        }
        return sumarDesde(n);
    }

    private static long sumarDesde(int n) {
        if (n == 0) {
            return 0;
        }
        return n + sumarDesde(n - 1);
    }

    public static void main(String[] args) {
        verificarResultado(0, 0);
        verificarResultado(1, 1);
        verificarResultado(5, 15);
        verificarRechazo(-1);

        System.out.println("sumaRecursiva(0) = " + sumaRecursiva(0));
        System.out.println("sumaRecursiva(1) = " + sumaRecursiva(1));
        System.out.println("sumaRecursiva(5) = " + sumaRecursiva(5));
    }

    private static void verificarResultado(int entrada, long esperado) {
        long resultado = sumaRecursiva(entrada);
        if (resultado != esperado) {
            throw new AssertionError(
                    "sumaRecursiva(" + entrada + "): se esperaba "
                            + esperado + " y se obtuvo " + resultado);
        }
    }

    private static void verificarRechazo(int entrada) {
        try {
            sumaRecursiva(entrada);
        } catch (IllegalArgumentException exception) {
            return;
        }
        throw new AssertionError(
                "sumaRecursiva(" + entrada + ") debía lanzar IllegalArgumentException");
    }
}
