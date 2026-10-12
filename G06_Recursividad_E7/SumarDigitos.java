// Calcula recursivamente la suma de los dígitos de un número entero.
public class SumarDigitos {

    public static int sumarDigitos(int n) {
        if (n < 0) {
            return sumarDigitos(-n);
        }
        if (n < 10) {
            return n;
        }

        int ultimoDigito = n % 10;
        int numeroReducido = n / 10;

        return ultimoDigito + sumarDigitos(numeroReducido);
    }

    public static void main(String[] args) {
        System.out.println("=== BATERÍA DE PRUEBAS: Sumar Dígitos ===");

        // Límite: números de un solo dígito
        for (int i = 0; i <= 9; i++) {
            probarCaso(i, i);
        }

        // Casos de control
        probarCaso(24, 6);
        probarCaso(1234, 10);
    }

    private static void probarCaso(int numero, int resultadoEsperado) {
        int resultado = sumarDigitos(numero);

        if (resultado != resultadoEsperado) {
            throw new AssertionError(
                    "sumarDigitos(" + numero + "): se esperaba "
                            + resultadoEsperado + ", pero se obtuvo " + resultado
            );
        }

        System.out.println("sumarDigitos(" + numero + ") = " + resultado + " [OK]");
    }
}
