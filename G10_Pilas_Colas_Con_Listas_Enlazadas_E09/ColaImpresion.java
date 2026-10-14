import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ColaImpresion {
    // FIFO preserves arrival order, so newer jobs cannot overtake or indefinitely delay older ones.
    private Nodo frente;
    private Nodo ultimo;

    private static final class Nodo {
        private final String archivo;
        private final int paginas;
        private final String usuario;
        private Nodo siguiente;

        private Nodo(String archivo, int paginas, String usuario) {
            this.archivo = archivo;
            this.paginas = paginas;
            this.usuario = usuario;
        }

        private String describir() {
            return "Archivo: " + archivo + ", páginas: " + paginas + ", usuario: " + usuario;
        }
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void agregarTrabajo(String archivo, int paginas, String usuario) {
        if (archivo == null || archivo.isBlank()) {
            throw new IllegalArgumentException("El nombre del archivo no puede estar vacío.");
        }
        if (paginas <= 0) {
            throw new IllegalArgumentException("La cantidad de páginas debe ser positiva.");
        }
        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("El usuario no puede estar vacío.");
        }

        Nodo nuevo = new Nodo(archivo.trim(), paginas, usuario.trim());
        if (estaVacia()) {
            frente = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
    }

    public Optional<String> imprimirProximo() {
        if (estaVacia()) {
            return Optional.empty();
        }

        Nodo trabajoImpreso = frente;
        frente = frente.siguiente;
        if (frente == null) {
            ultimo = null;
        }
        trabajoImpreso.siguiente = null;
        return Optional.of(trabajoImpreso.describir());
    }

    public Optional<String> consultarProximoArchivo() {
        return estaVacia()
                ? Optional.empty()
                : Optional.of(frente.archivo);
    }

    public List<String> listarPendientes() {
        List<String> trabajos = new ArrayList<>();
        Nodo actual = frente;
        while (actual != null) {
            trabajos.add(actual.describir());
            actual = actual.siguiente;
        }
        return trabajos;
    }

    public static void main(String[] args) {
        ColaImpresion spooler = new ColaImpresion();
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            String opcion = leerLinea(entrada, "Seleccione una opción: ");

            switch (opcion) {
                case "1":
                    agregarDesdeConsola(spooler, entrada);
                    break;
                case "2":
                    spooler.imprimirProximo().ifPresentOrElse(
                            trabajo -> System.out.println("Trabajo impreso: " + trabajo),
                            () -> System.out.println("No hay trabajos pendientes.")
                    );
                    break;
                case "3":
                    spooler.consultarProximoArchivo().ifPresentOrElse(
                            archivo -> System.out.println("Próximo archivo: " + archivo),
                            () -> System.out.println("No hay un próximo archivo.")
                    );
                    break;
                case "4":
                    listarDesdeConsola(spooler);
                    break;
                case "0":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        System.out.println("Spooler finalizado.");
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Cola de impresión ---");
        System.out.println("1. Agregar trabajo");
        System.out.println("2. Imprimir próximo trabajo");
        System.out.println("3. Consultar próximo archivo");
        System.out.println("4. Listar trabajos pendientes");
        System.out.println("0. Salir");
    }

    private static void agregarDesdeConsola(ColaImpresion spooler, Scanner entrada) {
        String archivo = leerLinea(entrada, "Nombre del archivo: ");
        String textoPaginas = leerLinea(entrada, "Cantidad de páginas: ");
        String usuario = leerLinea(entrada, "Usuario: ");

        try {
            int paginas = Integer.parseInt(textoPaginas);
            spooler.agregarTrabajo(archivo, paginas, usuario);
            System.out.println("Trabajo agregado a la cola.");
        } catch (NumberFormatException exception) {
            System.out.println("La cantidad de páginas debe ser un número entero.");
        } catch (IllegalArgumentException exception) {
            System.out.println("No se pudo agregar el trabajo: " + exception.getMessage());
        }
    }

    private static void listarDesdeConsola(ColaImpresion spooler) {
        List<String> trabajos = spooler.listarPendientes();
        if (trabajos.isEmpty()) {
            System.out.println("No hay trabajos pendientes.");
            return;
        }

        System.out.println("Trabajos pendientes (en orden de impresión):");
        for (int i = 0; i < trabajos.size(); i++) {
            System.out.println((i + 1) + ". " + trabajos.get(i));
        }
    }

    private static String leerLinea(Scanner entrada, String mensaje) {
        System.out.print(mensaje);
        return entrada.hasNextLine() ? entrada.nextLine() : "0";
    }
}
