import java.util.Arrays;
import java.util.Locale;

/*
 * Prompt:
 * Implementá la lógica en Palindromo.java. Incluí el bloque de comentarios
 * requerido. Probá con 'a', 'radar', 'reconocer' y 'java'.
 */
public final class Palindromo {

    private Palindromo() {
    }

    public static boolean esPalindromo(String palabra) {
        return esPalindromoNormalizado(palabra.toLowerCase(Locale.ROOT));
    }

    private static boolean esPalindromoNormalizado(String palabra) {
        if (palabra.length() <= 1) {
            return true;
        }

        int ultimoIndice = palabra.length() - 1;
        if (palabra.charAt(0) != palabra.charAt(ultimoIndice)) {
            return false;
        }

        return esPalindromoNormalizado(palabra.substring(1, ultimoIndice));
    }

    public static void main(String[] args) {
        verificarResultado("cadena vacía (caso base)", "", true);
        verificarResultado("un carácter minúsculo (caso base)", "a", true);
        verificarResultado("un carácter mayúsculo (caso base)", "A", true);
        verificarResultado("palíndromo en mayúsculas", "RADAR", true);
        verificarResultado("palíndromo con mayúsculas mezcladas", "rAdAr", true);
        verificarResultado("palíndromo largo con mayúsculas mezcladas", "ReCoNoCeR", true);
        verificarResultado("extremos distintos (corte anticipado)", "RaDaX", false);
        verificarResultado("desacuerdo interior tras coincidir extremos", "raXbr", false);
        verificarResultado("no palíndromo existente", "java", false);

        char[] caracteres = new char[100_000];
        Arrays.fill(caracteres, 'a');
        caracteres[caracteres.length - 1] = 'b';
        verificarResultado(
                "cadena larga con extremos distintos (corte anticipado)",
                new String(caracteres),
                false);

        Locale localeOriginal = Locale.getDefault();
        try {
            Locale.setDefault(Locale.forLanguageTag("tr-TR"));
            verificarResultado("resultado independiente del locale predeterminado", "Ii", true);
        } finally {
            Locale.setDefault(localeOriginal);
        }
    }

    private static void verificarResultado(String descripcion, String palabra, boolean esperado) {
        boolean resultado = esPalindromo(palabra);
        if (resultado != esperado) {
            throw new AssertionError(
                    descripcion + ": se esperaba "
                            + esperado + " y se obtuvo " + resultado);
        }

        System.out.println(descripcion + " = " + resultado + " [OK]");
    }
}
