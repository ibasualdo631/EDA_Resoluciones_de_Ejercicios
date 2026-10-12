/*
 * Prompt: Implementá en Java una función recursiva que invierta un String.
 * Usá como casos base la cadena vacía y la cadena de un carácter. En cada
 * llamada separá el primer carácter del resto con charAt(0) y substring(1),
 * invertí recursivamente el resto y agregá el primer carácter al final del
 * resultado. No uses ciclos ni utilidades de biblioteca para invertir.
 * Validá en main las entradas "", "a", "hola" y "recursivo".
 */
import java.util.Objects;

public final class InvertirPalabra {

    private InvertirPalabra() {
    }

    public static String invertir(String cadena) {
        if (cadena == null) {
            return null;
        }

        if (cadena.length() <= 1) {
            return cadena;
        }

        char primerCaracter = cadena.charAt(0);
        String resto = cadena.substring(1);
        return invertir(resto) + primerCaracter;
    }

    public static void main(String[] args) {
        probarCaso(null, null);
        probarCaso("", "");
        probarCaso("a", "a");
        probarCaso("sol", "los");
        probarCaso("reconocer", "reconocer");
        probarCaso("a b", "b a");
        probarCaso("hola", "aloh");
        probarCaso("recursivo", "ovisrucer");
    }

    private static void probarCaso(String entrada, String esperado) {
        String resultado = invertir(entrada);
        String entradaVisible = entrada == null ? "null" : "\"" + entrada + "\"";
        String esperadoVisible = esperado == null ? "null" : "\"" + esperado + "\"";
        String resultadoVisible = resultado == null ? "null" : "\"" + resultado + "\"";

        if (!Objects.equals(resultado, esperado)) {
            throw new AssertionError(
                    "invertir(" + entradaVisible + "): se esperaba "
                            + esperadoVisible + ", pero se obtuvo " + resultadoVisible
            );
        }

        System.out.println("invertir(" + entradaVisible + ") = "
                + resultadoVisible + " [OK]");
    }
}
