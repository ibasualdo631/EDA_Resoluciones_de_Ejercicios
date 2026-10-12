/*
 * Prompt:
 * Implementá en Java una función recursiva que imprima los enteros desde n
 * hasta 0, inclusive. Si n es negativo, debe terminar sin imprimir. Para
 * n >= 0, imprimí el valor actual y llamá recursivamente a la función con
 * n - 1. Desde main, imprimí un conteo iniciado en 0 y otro iniciado en 3.
 *
 * Usá n < 0 como caso base. La llamada recursiva debe usar n - 1 para
 * acercarse al caso base; usar n + 1 alejaría los valores no negativos del
 * caso base y podría provocar un desbordamiento de pila.
 */
public final class ConteoRegresivo {

    private ConteoRegresivo() {
    }

    public static void imprimirConteoRegresivo(int n) {
        if (n < 0) {
            return;
        }

        System.out.println(n);
        imprimirConteoRegresivo(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("Conteo desde 0:");
        imprimirConteoRegresivo(0);

        System.out.println("Conteo desde 3:");
        imprimirConteoRegresivo(3);
    }
}
