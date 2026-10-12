/*
 * Prompt:
 * Implementá el código en ContarDigitos.java. Incluí el bloque de comentarios
 * obligatorio con el prompt en la cabecera. Probá en el main los valores 7,
 * 45 y 98765.
 */
public class ContarDigitos {

    public static int contarDigitos(int numero) {
        if (numero == 0) {
            throw new IllegalArgumentException("El número no puede ser cero.");
        }

        long magnitud = Math.abs((long) numero);
        return contarDigitosPositivo(magnitud);
    }

    private static int contarDigitosPositivo(long numero) {
        if (numero < 10) {
            return 1;
        }

        return 1 + contarDigitosPositivo(numero / 10);
    }

    public static void main(String[] args) {
        int[] valores = {7, 45, 98765};

        for (int valor : valores) {
            System.out.println(valor + " tiene " + contarDigitos(valor) + " digito(s).");
        }
    }
}
