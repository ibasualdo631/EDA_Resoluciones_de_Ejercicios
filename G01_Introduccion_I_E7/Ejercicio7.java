public class Ejercicio7 {
    public static void main(String[] args) {
        int limite = 10;
        int contador = inicializarContador();

        mostrarNumerosHasta(limite, contador);
    }

    public static int inicializarContador() {
        return 1;
    }

    public static void mostrarNumerosHasta(int limite, int contador) {
        while (contador <= limite) {
            System.out.println(contador);
            contador++;
        }
    }
}
