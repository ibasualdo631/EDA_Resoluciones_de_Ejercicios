public class BusquedaVectorDesordenado {

    /**
     * Busca un valor en un vector desordenado mediante recorrido secuencial.
     *
     * @param vector Arreglo de enteros en el que se realizará la búsqueda.
     * @param valorBuscado Número que se desea localizar.
     * @return La posición del valor dentro del vector; si no existe, devuelve -1.
     */
    public static int buscarElemento(int[] vector, int valorBuscado) {
        validarVector(vector);

        int posicionesRecorridas = 0;

        for (int i = 0; i < vector.length; i++) {
            posicionesRecorridas++;

            if (vector[i] == valorBuscado) {
                System.out.println("Elemento encontrado en la posición: " + i);
                System.out.println("Posiciones recorridas: " + posicionesRecorridas);
                return i;
            }
        }

        System.out.println("El elemento no existe en el vector.");
        System.out.println("Posiciones recorridas: " + posicionesRecorridas);
        return -1;
    }

    /**
     * Verifica que el vector recibido sea válido para la búsqueda.
     *
     * @param vector Arreglo a validar.
     */
    private static void validarVector(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser nulo.");
        }

        if (vector.length == 0) {
            throw new IllegalArgumentException("El vector no puede estar vacío.");
        }
    }

    public static void main(String[] args) {
        int[] vector = {7, 12, 3, 9, 15, 2, 8};
        int valorBuscado = 9;

        int resultado = buscarElemento(vector, valorBuscado);

        if (resultado != -1) {
            System.out.println("El elemento " + valorBuscado + " sí está en el vector.");
        } else {
            System.out.println("El elemento " + valorBuscado + " no está en el vector.");
        }
    }
}
