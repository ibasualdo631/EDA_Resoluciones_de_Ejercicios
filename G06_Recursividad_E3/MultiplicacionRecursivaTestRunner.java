public final class MultiplicacionRecursivaTestRunner {

    private static int pruebasEjecutadas;

    private MultiplicacionRecursivaTestRunner() {
    }

    public static void main(String[] args) {
        probar("Factor derecho cero", 7, 0, 0);
        probar("Ambos factores cero", 0, 0, 0);
        probar("Factor izquierdo negativo y derecho cero", -5, 0, 0);
        probar("Factor izquierdo cero y derecho positivo", 0, 4, 0);
        probar("Factor izquierdo cero y derecho negativo", 0, -4, 0);
        probar("Ambos factores positivos", 5, 3, 15);
        probar("Factor derecho negativo", 5, -3, -15);
        probar("Factor izquierdo negativo", -5, 3, -15);
        probar("Ambos factores negativos", -5, -3, 15);

        System.out.println("Resultado: " + pruebasEjecutadas + " pruebas aprobadas.");
    }

    private static void probar(String nombre, int a, int b, int esperado) {
        int obtenido = MultiplicacionRecursiva.multiplicar(a, b);
        if (obtenido != esperado) {
            throw new AssertionError(
                    nombre + ": esperado " + esperado + ", obtenido " + obtenido);
        }

        pruebasEjecutadas++;
        System.out.println("OK - " + nombre + ": (" + a + ", " + b + ") = " + obtenido);
    }
}
