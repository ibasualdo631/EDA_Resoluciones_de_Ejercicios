/**
 * Invierte un vector de enteros modificando el arreglo recibido.
 */
public final class invertirVectorInSitu {

    private invertirVectorInSitu() {
    }

    /**
     * Invierte el vector intercambiando sus elementos desde los extremos
     * hacia el centro.
     *
     * @param vector vector que se modificará; no puede ser {@code null}
     * @return la misma referencia recibida, con sus elementos invertidos;
     *         si tiene menos de dos elementos, la devuelve sin cambios
     * @throws IllegalArgumentException si {@code vector} es {@code null}
     *
     * @implNote Para vectores de longitud igual o mayor que dos, mejor,
     * promedio y peor caso son Θ(n) en tiempo, donde n es la longitud.
     * El espacio auxiliar es Θ(1). Para longitudes menores que dos,
     * el tiempo es Θ(1).
     */
    public static int[] invertir(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser null.");
        }

        if (vector.length < 2) {
            System.out.println(vector.length == 0
                    ? "Aviso: el vector está vacío; no se puede invertir."
                    : "Aviso: el vector tiene un solo elemento; no se puede invertir.");
            return vector;
        }

        int izquierda = 0;
        int derecha = vector.length - 1;

        while (izquierda < derecha) {
            int temporal = vector[izquierda];
            vector[izquierda] = vector[derecha];
            vector[derecha] = temporal;
            izquierda++;
            derecha--;
        }

        return vector;
    }
}
