import java.util.Scanner;

/**
 * Gestor de historial de navegación basado en una pila enlazada.
 *
 * La política LIFO es ideal para volver atrás: la última página visitada es
 * la primera que se retira, dejando como actual la página visitada justo antes.
 * Así, cada retroceso deshace la navegación más reciente sin buscar ni reordenar
 * las páginas anteriores.
 */
public class HistorialNavegacion {
    private static final class Nodo {
        private final String pagina;
        private final Nodo siguiente;

        private Nodo(String pagina, Nodo siguiente) {
            this.pagina = pagina;
            this.siguiente = siguiente;
        }
    }

    private Nodo tope;

    /**
     * Agrega una página al historial y la convierte en la página actual.
     *
     * @param pagina nombre o dirección de la página
     * @throws IllegalArgumentException si la página es null o está vacía
     */
    public void visitar(String pagina) {
        if (pagina == null || pagina.trim().isEmpty()) {
            throw new IllegalArgumentException("La página no puede estar vacía.");
        }

        tope = new Nodo(pagina.trim(), tope);
    }

    /**
     * Vuelve a la página anterior si existe.
     *
     * @return {@code true} si se retiró la página actual; {@code false} si no
     *         hay una página anterior
     */
    public boolean volverAtras() {
        if (tope == null || tope.siguiente == null) {
            return false;
        }

        tope = tope.siguiente;
        return true;
    }

    /**
     * Obtiene la página actual sin modificar el historial.
     *
     * @return página actual o {@code null} si el historial está vacío
     */
    public String obtenerPaginaActual() {
        return tope == null ? null : tope.pagina;
    }

    /**
     * Imprime las páginas desde la actual hasta la más antigua sin modificar
     * la pila.
     */
    public void imprimirHistorial() {
        if (tope == null) {
            System.out.println("El historial está vacío.");
            return;
        }

        System.out.println("Historial (actual a más antigua):");
        for (Nodo actual = tope; actual != null; actual = actual.siguiente) {
            System.out.println("- " + actual.pagina);
        }
    }

    public static void main(String[] args) {
        HistorialNavegacion historial = new HistorialNavegacion();
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        while (continuar && entrada.hasNextLine()) {
            mostrarMenu();
            String opcion = entrada.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.print("Ingrese la página a visitar: ");
                    if (!entrada.hasNextLine()) {
                        continuar = false;
                        break;
                    }

                    String pagina = entrada.nextLine().trim();
                    if (pagina.isEmpty()) {
                        System.out.println("La página no puede estar vacía.");
                    } else {
                        historial.visitar(pagina);
                        System.out.println("Página visitada: " + pagina);
                    }
                    break;
                case "2":
                    if (historial.volverAtras()) {
                        System.out.println("Página actual: " + historial.obtenerPaginaActual());
                    } else {
                        System.out.println("No hay una página anterior en el historial.");
                    }
                    break;
                case "3":
                    String actual = historial.obtenerPaginaActual();
                    if (actual == null) {
                        System.out.println("El historial está vacío; no hay página actual.");
                    } else {
                        System.out.println("Página actual: " + actual);
                    }
                    break;
                case "4":
                    historial.imprimirHistorial();
                    break;
                case "5":
                    continuar = false;
                    System.out.println("Saliendo del historial.");
                    break;
                default:
                    System.out.println("Opción inválida. Ingrese un número del 1 al 5.");
                    break;
            }
            System.out.println();
        }
    }

    private static void mostrarMenu() {
        System.out.println("=== Historial de navegación ===");
        System.out.println("1. Visitar una página");
        System.out.println("2. Volver atrás");
        System.out.println("3. Ver página actual");
        System.out.println("4. Imprimir historial");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
