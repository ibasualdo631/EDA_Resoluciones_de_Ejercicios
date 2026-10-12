public final class MultiplicacionRecursiva {

    private MultiplicacionRecursiva() {
    }

    public static int multiplicar(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b > 0) {
            return a + multiplicar(a, b - 1);
        }
        return -a + multiplicar(a, b + 1);
    }
}
