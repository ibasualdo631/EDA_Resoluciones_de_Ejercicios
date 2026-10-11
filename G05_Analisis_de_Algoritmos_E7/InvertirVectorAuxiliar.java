/**
 * Invierte un vector de enteros construyendo un arreglo auxiliar.
 */
public final class InvertirVectorAuxiliar {

    private InvertirVectorAuxiliar() {
    }

    /**
     * Copia los elementos en orden inverso a un nuevo vector sin modificar
     * el arreglo recibido.
     *
     * @param vector vector de origen; no puede ser {@code null}
     * @return un nuevo arreglo con los elementos en orden inverso
     * @throws IllegalArgumentException si {@code vector} es {@code null}
     *
     * @implNote Para vectores de longitud igual o mayor que dos, mejor,
     * promedio y peor caso son Θ(n) en tiempo, donde n es la longitud.
     * El espacio auxiliar es Θ(n). Para longitudes menores que dos,
     * el tiempo es Θ(1); se devuelve una copia, incluso si está vacío.
     */
    public static int[] invertir(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        if (vector.length < 2) {
            System.out.println(vector.length == 0
                    ? "Aviso: el vector está vacío; no se puede invertir."
                    : "Aviso: el vector tiene un solo elemento; no se puede invertir.");
        }

        int[] invertido = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            invertido[vector.length - 1 - i] = vector[i];
        }

        return invertido;
    }
}
