/*
 * Para respetar la regla bancaria FIFO (primero en llegar, primero en ser atendido),
 * la cola es la única estructura lineal básica adecuada para expresar directamente
 * ese orden: cada llegada se enlaza al final y la atención siempre retira del frente.
 * Una pila invertiría el orden y una lista sin disciplina FIFO no lo garantizaría.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

public class SistemaBancario {
    private Nodo frente;
    private Nodo ultimo;
    private long siguienteTurno = 1;

    public static void main(String[] args) throws IOException {
        SistemaBancario sistema = new SistemaBancario();

        try (BufferedReader entrada = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            sistema.ejecutar(entrada);
        }
    }

    private void ejecutar(BufferedReader entrada) throws IOException {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            String opcion = entrada.readLine();

            if (opcion == null) {
                break;
            }

            switch (opcion.trim()) {
                case "1":
                    agregarCliente(entrada);
                    break;
                case "2":
                    atenderProximo();
                    break;
                case "3":
                    consultarSiguiente();
                    break;
                case "4":
                    imprimirFila();
                    break;
                case "5":
                    continuar = false;
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opción no válida. Ingrese un número del 1 al 5.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("=== Cola de atención bancaria ===");
        System.out.println("1. Agregar cliente a la fila");
        System.out.println("2. Atender al próximo cliente");
        System.out.println("3. Consultar quién sigue");
        System.out.println("4. Imprimir la fila actual");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void agregarCliente(BufferedReader entrada) throws IOException {
        if (siguienteTurno > Integer.MAX_VALUE) {
            System.out.println("No es posible asignar más turnos.");
            return;
        }

        String nombre = leerDatoObligatorio(entrada, "Nombre del cliente: ");
        if (nombre == null) {
            return;
        }

        String motivo = leerDatoObligatorio(entrada, "Motivo de consulta: ");
        if (motivo == null) {
            return;
        }

        Cliente cliente = new Cliente(nombre, (int) siguienteTurno, motivo);
        encolar(cliente);
        siguienteTurno++;
        System.out.println("Cliente agregado. Número de turno: " + cliente.numeroTurno);
    }

    private String leerDatoObligatorio(BufferedReader entrada, String mensaje) throws IOException {
        while (true) {
            System.out.print(mensaje);
            String dato = entrada.readLine();

            if (dato == null) {
                return null;
            }

            dato = dato.trim();
            if (!dato.isEmpty()) {
                return dato;
            }

            System.out.println("El dato no puede estar vacío.");
        }
    }

    private void encolar(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser null.");
        Nodo nuevo = new Nodo(cliente);

        if (estaVacia()) {
            frente = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }

        ultimo = nuevo;
    }

    private Optional<Cliente> desencolar() {
        if (estaVacia()) {
            return Optional.empty();
        }

        Cliente cliente = frente.cliente;
        frente = frente.siguiente;

        if (frente == null) {
            ultimo = null;
        }

        return Optional.of(cliente);
    }

    private Optional<Cliente> consultarSiguienteCliente() {
        return estaVacia() ? Optional.empty() : Optional.of(frente.cliente);
    }

    private boolean estaVacia() {
        return frente == null;
    }

    private void atenderProximo() {
        Optional<Cliente> cliente = desencolar();
        if (cliente.isPresent()) {
            System.out.println("Atendiendo a: " + cliente.get());
        } else {
            System.out.println("No hay clientes para atender; la fila está vacía.");
        }
    }

    private void consultarSiguiente() {
        Optional<Cliente> cliente = consultarSiguienteCliente();
        if (cliente.isPresent()) {
            System.out.println("El próximo cliente es: " + cliente.get());
        } else {
            System.out.println("No hay un próximo cliente; la fila está vacía.");
        }
    }

    private void imprimirFila() {
        if (estaVacia()) {
            System.out.println("La fila está vacía.");
            return;
        }

        System.out.println("Fila actual (orden de atención):");
        Nodo actual = frente;
        while (actual != null) {
            System.out.println(actual.cliente);
            actual = actual.siguiente;
        }
    }

    private static class Cliente {
        private final String nombre;
        private final int numeroTurno;
        private final String motivoConsulta;

        private Cliente(String nombre, int numeroTurno, String motivoConsulta) {
            this.nombre = nombre;
            this.numeroTurno = numeroTurno;
            this.motivoConsulta = motivoConsulta;
        }

        @Override
        public String toString() {
            return "Turno " + numeroTurno
                    + " | Nombre: " + nombre
                    + " | Motivo: " + motivoConsulta;
        }
    }

    private static class Nodo {
        private final Cliente cliente;
        private Nodo siguiente;

        private Nodo(Cliente cliente) {
            this.cliente = cliente;
        }
    }
}
