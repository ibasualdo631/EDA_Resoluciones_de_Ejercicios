import java.util.Scanner;

public class MenuOpciones {
    public static void main(String[] args) {
        mostrarMenu();
        int opcion = leerOpcion();
        procesarOpcion(opcion);
    }

    public static void mostrarMenu() {
        System.out.println("=== MENÚ DE OPCIONES ===");
        System.out.println("1. Opción 1");
        System.out.println("2. Opción 2");
        System.out.println("3. Opción 3");
        System.out.println("4. Opción 4");
        System.out.print("Seleccione una opción: ");
    }

    public static int leerOpcion() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Entrada inválida. Debe ingresar un número.");
            scanner.next();
        }
    }

    public static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("Elegiste la opción 1.");
                break;
            case 2:
                System.out.println("Elegiste la opción 2.");
                break;
            case 3:
                System.out.println("Elegiste la opción 3.");
                break;
            case 4:
                System.out.println("Elegiste la opción 4.");
                break;
            default:
                System.out.println("Opción inválida. Debe elegir un número entre 1 y 4.");
                break;
        }
    }
}
