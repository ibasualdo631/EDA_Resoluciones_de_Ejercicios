public class ValorMinimoVector {

    public static int encontrarMinimo(int[] numeros) {
        validarArreglo(numeros);

        int minimo = numeros[0];

        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] < minimo) {
                minimo = numeros[i];
            }
        }

        return minimo;
    }

    private static void validarArreglo(int[] numeros) {
        if (numeros == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        }

        if (numeros.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede estar vacío.");
        }
    }

    public static void main(String[] args) {
        int[] datos = {12, 7, 3, 21, 5, 9};
        int minimo = encontrarMinimo(datos);
        System.out.println("El valor mínimo es: " + minimo);
    }
}
