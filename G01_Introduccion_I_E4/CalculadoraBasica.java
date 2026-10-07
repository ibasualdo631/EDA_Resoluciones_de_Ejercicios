/**
 * Calculadora básica en Java.
 * Lee dos números por consola y muestra el resultado de operaciones aritméticas.
 */
import java.util.Scanner;

public class CalculadoraBasica {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el primer número: ");
        double primerNumero = leerNumero(scanner);

        System.out.print("Ingrese el segundo número: ");
        double segundoNumero = leerNumero(scanner);

        System.out.println("Suma: " + sumar(primerNumero, segundoNumero));
        System.out.println("Resta: " + restar(primerNumero, segundoNumero));
        System.out.println("Multiplicación: " + multiplicar(primerNumero, segundoNumero));

        if (segundoNumero == 0) {
            System.out.println("No se puede dividir por cero.");
            System.out.println("Módulo: no se puede calcular porque el divisor es cero.");
        } else {
            System.out.println("División: " + dividir(primerNumero, segundoNumero));
            System.out.println("Módulo: " + modulo(primerNumero, segundoNumero));
        }

        scanner.close();
    }

    private static double leerNumero(Scanner scanner) {
        return scanner.nextDouble();
    }

    private static double sumar(double a, double b) {
        return a + b;
    }

    private static double restar(double a, double b) {
        return a - b;
    }

    private static double multiplicar(double a, double b) {
        return a * b;
    }

    private static double dividir(double a, double b) {
        return a / b;
    }

    private static double modulo(double a, double b) {
        return a % b;
    }
}
