import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese la nota del alumno: ");
        double nota = entrada.nextDouble();

        if (esNotaValida(nota)) {
            String estado = determinarEstado(nota);
            System.out.println("El alumno esta " + estado + ".");
        } else {
            System.out.println("La nota ingresada es invalida.");
        }

        entrada.close();
    }

    public static boolean esNotaValida(double nota) {
        return nota >= 0 && nota <= 10;
    }

    public static String determinarEstado(double nota) {
        if (nota < 4) {
            return "desaprobado";
        } else if (nota >= 4 && nota <= 6) {
            return "aprobado";
        } else {
            return "promocionado";
        }
    }
}
