/*
 * Prompt empleado:
 * "Codificá el sistema de turnos en SistemaTurnos.java. Añade la cabecera
 * obligatoria de comentarios y un método main que simule la llegada y atención
 * ordenada de varios clientes."
 *
 * Ajustes aplicados:
 * - Se representa cada solicitud con un turno de código secuencial y nombre
 *   validado.
 * - Los turnos se almacenan en una cola enlazada FIFO sin capacidad fija.
 * - La atención y la consulta del frente informan explícitamente si la cola
 *   está vacía, y el listado de pendientes conserva el orden de llegada.
 * - El método main simula varias llegadas, muestra los pendientes y atiende a
 *   los clientes en orden.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SistemaTurnos {

    private final ColaTurnos cola;
    private long siguienteCodigo;

    public SistemaTurnos() {
        cola = new ColaTurnos();
        siguienteCodigo = 1;
    }

    /**
     * Registra un cliente y devuelve el turno asignado.
     *
     * @param nombreCliente nombre de la persona que solicita atención
     * @return turno agregado al final de la cola
     * @throws IllegalArgumentException si el nombre no es válido
     * @throws IllegalStateException si se agotaron los códigos positivos
     */
    public Turno registrarCliente(String nombreCliente) {
        if (siguienteCodigo > Integer.MAX_VALUE) {
            throw new IllegalStateException("No quedan códigos de turno disponibles.");
        }

        Turno turno = new Turno((int) siguienteCodigo, nombreCliente);
        cola.encolar(turno);
        siguienteCodigo++;
        return turno;
    }

    /**
     * Retira y devuelve al cliente que llegó primero.
     *
     * @return turno atendido
     * @throws NoSuchElementException si no hay clientes esperando
     */
    public Turno atenderSiguiente() {
        return cola.atender();
    }

    /**
     * Consulta el turno que será atendido a continuación, sin retirarlo.
     *
     * @return turno ubicado al frente de la cola
     * @throws NoSuchElementException si no hay clientes esperando
     */
    public Turno consultarSiguiente() {
        return cola.consultarFrente();
    }

    /**
     * Devuelve una copia de los turnos pendientes, en orden de llegada.
     *
     * @return lista independiente de turnos pendientes
     */
    public List<Turno> listarPendientes() {
        return cola.listarPendientes();
    }

    public boolean estaVacio() {
        return cola.estaVacia();
    }

    public int obtenerCantidadPendiente() {
        return cola.obtenerCantidad();
    }

    public static void main(String[] args) {
        SistemaTurnos sistema = new SistemaTurnos();
        String[] clientes = {"Ana", "Bruno", "Carla", "Diego"};

        System.out.println("Llegada de clientes:");
        for (String cliente : clientes) {
            Turno turno = sistema.registrarCliente(cliente);
            System.out.println("  Turno " + turno.obtenerCodigo()
                    + ": " + turno.obtenerNombreCliente());
        }

        System.out.println("\nClientes en espera (orden FIFO):");
        for (Turno turno : sistema.listarPendientes()) {
            System.out.println("  Turno " + turno.obtenerCodigo()
                    + ": " + turno.obtenerNombreCliente());
        }

        System.out.println("\nAtención de clientes:");
        while (!sistema.estaVacio()) {
            Turno turno = sistema.atenderSiguiente();
            System.out.println("  Atendiendo turno " + turno.obtenerCodigo()
                    + ": " + turno.obtenerNombreCliente());
        }

        System.out.println("\nTurnos pendientes: "
                + sistema.obtenerCantidadPendiente());
    }

    public static final class Turno {

        private final int codigo;
        private final String nombreCliente;

        public Turno(int codigo, String nombreCliente) {
            if (codigo <= 0) {
                throw new IllegalArgumentException("El código debe ser positivo.");
            }
            if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "El nombre del cliente no puede estar vacío.");
            }

            this.codigo = codigo;
            this.nombreCliente = nombreCliente.trim();
        }

        public int obtenerCodigo() {
            return codigo;
        }

        public String obtenerNombreCliente() {
            return nombreCliente;
        }
    }

    private static final class ColaTurnos {

        private NodoTurno frente;
        private NodoTurno fin;
        private int cantidad;

        private void encolar(Turno turno) {
            if (turno == null) {
                throw new IllegalArgumentException("El turno no puede ser nulo.");
            }

            NodoTurno nuevo = new NodoTurno(turno);
            if (estaVacia()) {
                frente = nuevo;
            } else {
                fin.siguiente = nuevo;
            }
            fin = nuevo;
            cantidad++;
        }

        private Turno atender() {
            if (estaVacia()) {
                throw new NoSuchElementException(
                        "No se puede atender: no hay clientes esperando.");
            }

            Turno turnoAtendido = frente.valor;
            frente = frente.siguiente;
            cantidad--;
            if (frente == null) {
                fin = null;
            }
            return turnoAtendido;
        }

        private Turno consultarFrente() {
            if (estaVacia()) {
                throw new NoSuchElementException(
                        "No hay clientes esperando.");
            }
            return frente.valor;
        }

        private boolean estaVacia() {
            return frente == null;
        }

        private int obtenerCantidad() {
            return cantidad;
        }

        private List<Turno> listarPendientes() {
            List<Turno> pendientes = new ArrayList<>(cantidad);
            NodoTurno actual = frente;
            while (actual != null) {
                pendientes.add(actual.valor);
                actual = actual.siguiente;
            }
            return pendientes;
        }
    }

    private static final class NodoTurno {

        private final Turno valor;
        private NodoTurno siguiente;

        private NodoTurno(Turno valor) {
            this.valor = valor;
        }
    }
}
