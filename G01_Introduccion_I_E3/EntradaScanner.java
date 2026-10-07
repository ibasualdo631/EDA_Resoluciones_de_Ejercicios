import java.util.Scanner;

public class EntradaScanner {
    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del teclado.
        Scanner entrada = new Scanner(System.in);

        // Pedir y leer el nombre del usuario.
        System.out.print("Ingrese su nombre: ");
        String nombre = entrada.nextLine();

        // Pedir y leer la edad del usuario.
        System.out.print("Ingrese su edad: ");
        int edad = entrada.nextInt();

        // Mostrar un mensaje personalizado con los datos ingresados.
        System.out.println("Hola " + nombre + ", tienes " + edad + " anios.");

        // Cerrar el Scanner para liberar recursos.
        entrada.close();
    }
}
