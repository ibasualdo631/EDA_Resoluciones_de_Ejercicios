/*
 * Prompt empleado:
 * Desarrollador: Desarrolla en plan_tecnico generado por el agente
 * Planificador. Incluir un bloque de comentarios al principio con el prompt
 * empleado. Implementar los métodos invertirVectorInSitu e
 * InvertirVectorAuxiliar, recibiendo una referencia a un vector y retornando
 * el vector invertido. Si el vector es null, lanzar
 * IllegalArgumentException. Si la longitud es menor que dos, dejarlo igual
 * y avisar por consola si está vacío o tiene un solo elemento. Documentar
 * el código con Javadoc e incluir un método main con pruebas en consola
 * para vector vacío, vector unitario, vector null, vector de cinco elementos
 * y vector capicúa [1, 2, 3, 2, 1].
 */

import java.util.Arrays;

/**
 * Ejecuta pruebas de consola para las dos estrategias de inversión de vectores.
 */
public final class Ejercicio7 {

    private Ejercicio7() {
    }

    /**
     * Ejecuta los casos borde y ejemplos solicitados para ambas estrategias.
     *
     * @param args argumentos de línea de comandos, no utilizados
     */
    public static void main(String[] args) {
        probarVectorVacio();
        probarVectorUnitario();
        probarVectorNull();
        probarVectorDeCincoElementos();
        probarVectorDeLongitudPar();
        probarVectorCapicua();
        System.out.println("Todas las pruebas finalizaron correctamente.");
    }

    private static void probarVectorVacio() {
        System.out.println("\nPrueba: vector vacío");
        int[] vectorInSitu = {};
        int[] resultadoInSitu = invertirVectorInSitu.invertir(vectorInSitu);
        verificar("In situ: contenido vacío", resultadoInSitu, new int[]{});
        verificarMismaReferencia("In situ: misma referencia", vectorInSitu, resultadoInSitu);

        int[] vectorAuxiliar = {};
        int[] resultadoAuxiliar = InvertirVectorAuxiliar.invertir(vectorAuxiliar);
        verificar("Auxiliar: contenido vacío", resultadoAuxiliar, new int[]{});
        verificarReferenciaDistinta("Auxiliar: nueva referencia", vectorAuxiliar, resultadoAuxiliar);
    }

    private static void probarVectorUnitario() {
        System.out.println("\nPrueba: vector de un elemento");
        int[] vectorInSitu = {7};
        int[] resultadoInSitu = invertirVectorInSitu.invertir(vectorInSitu);
        verificar("In situ: contenido sin cambios", resultadoInSitu, new int[]{7});
        verificarMismaReferencia("In situ: misma referencia", vectorInSitu, resultadoInSitu);

        int[] vectorAuxiliar = {7};
        int[] resultadoAuxiliar = InvertirVectorAuxiliar.invertir(vectorAuxiliar);
        verificar("Auxiliar: contenido sin cambios", resultadoAuxiliar, new int[]{7});
        verificar("Auxiliar: entrada sin modificar", vectorAuxiliar, new int[]{7});
        verificarReferenciaDistinta("Auxiliar: nueva referencia", vectorAuxiliar, resultadoAuxiliar);
    }

    private static void probarVectorNull() {
        System.out.println("\nPrueba: vector null");
        verificarExcepcionNull("In situ", () -> invertirVectorInSitu.invertir(null));
        verificarExcepcionNull("Auxiliar", () -> InvertirVectorAuxiliar.invertir(null));
    }

    private static void probarVectorDeCincoElementos() {
        System.out.println("\nPrueba: vector de cinco elementos");
        int[] vectorInSitu = {1, 2, 3, 4, 5};
        int[] resultadoInSitu = invertirVectorInSitu.invertir(vectorInSitu);
        verificar("In situ: contenido invertido", resultadoInSitu, new int[]{5, 4, 3, 2, 1});
        verificarMismaReferencia("In situ: misma referencia", vectorInSitu, resultadoInSitu);

        int[] vectorAuxiliar = {1, 2, 3, 4, 5};
        int[] resultadoAuxiliar = InvertirVectorAuxiliar.invertir(vectorAuxiliar);
        verificar("Auxiliar: contenido invertido", resultadoAuxiliar, new int[]{5, 4, 3, 2, 1});
        verificar("Auxiliar: entrada sin modificar", vectorAuxiliar, new int[]{1, 2, 3, 4, 5});
        verificarReferenciaDistinta("Auxiliar: nueva referencia", vectorAuxiliar, resultadoAuxiliar);
    }

    private static void probarVectorCapicua() {
        System.out.println("\nPrueba: vector capicúa");
        int[] esperado = {1, 2, 3, 2, 1};

        int[] vectorInSitu = {1, 2, 3, 2, 1};
        int[] resultadoInSitu = invertirVectorInSitu.invertir(vectorInSitu);
        verificar("In situ: vector capicúa invertido", resultadoInSitu, esperado);
        verificarMismaReferencia("In situ: misma referencia", vectorInSitu, resultadoInSitu);

        int[] vectorAuxiliar = {1, 2, 3, 2, 1};
        int[] resultadoAuxiliar = InvertirVectorAuxiliar.invertir(vectorAuxiliar);
        verificar("Auxiliar: vector capicúa invertido", resultadoAuxiliar, esperado);
        verificar("Auxiliar: entrada sin modificar", vectorAuxiliar, esperado);
        verificarReferenciaDistinta("Auxiliar: nueva referencia", vectorAuxiliar, resultadoAuxiliar);
    }

    private static void probarVectorDeLongitudPar() {
        System.out.println("\nPrueba: vector de longitud par");
        int[] esperado = {4, 3, 2, 1};

        int[] vectorInSitu = {1, 2, 3, 4};
        int[] resultadoInSitu = invertirVectorInSitu.invertir(vectorInSitu);
        verificar("In situ: contenido invertido", resultadoInSitu, esperado);
        verificarMismaReferencia("In situ: misma referencia", vectorInSitu, resultadoInSitu);

        int[] vectorAuxiliar = {1, 2, 3, 4};
        int[] resultadoAuxiliar = InvertirVectorAuxiliar.invertir(vectorAuxiliar);
        verificar("Auxiliar: contenido invertido", resultadoAuxiliar, esperado);
        verificar("Auxiliar: entrada sin modificar", vectorAuxiliar, new int[]{1, 2, 3, 4});
        verificarReferenciaDistinta("Auxiliar: nueva referencia", vectorAuxiliar, resultadoAuxiliar);
    }

    private static void verificarExcepcionNull(String caso, Runnable operacion) {
        try {
            operacion.run();
            throw new AssertionError(caso + ": se esperaba IllegalArgumentException.");
        } catch (IllegalArgumentException exception) {
            if (exception.getMessage() == null || exception.getMessage().isEmpty()) {
                throw new AssertionError(caso + ": la excepción debe incluir un mensaje.");
            }
            System.out.println(caso + ": IllegalArgumentException esperada");
        }
    }

    private static void verificar(String caso, int[] resultado, int[] esperado) {
        if (!Arrays.equals(resultado, esperado)) {
            throw new AssertionError(caso + ": se esperaba " + Arrays.toString(esperado)
                    + ", se obtuvo " + Arrays.toString(resultado));
        }
        System.out.println(caso + ": " + Arrays.toString(resultado));
    }

    private static void verificarMismaReferencia(String caso, int[] original, int[] resultado) {
        if (original != resultado) {
            throw new AssertionError(caso + ": se esperaba conservar la referencia.");
        }
        System.out.println(caso + ": correcto");
    }

    private static void verificarReferenciaDistinta(String caso, int[] original, int[] resultado) {
        if (original == resultado) {
            throw new AssertionError(caso + ": se esperaba una referencia nueva.");
        }
        System.out.println(caso + ": correcto");
    }
}
