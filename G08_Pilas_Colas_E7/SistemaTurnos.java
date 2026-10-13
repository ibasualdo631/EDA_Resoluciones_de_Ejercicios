/*
 * Prompt empleado:
 * "Codificá el sistema de turnos en SistemaTurnos.java. Añade la cabecera
 * obligatoria de comentarios y un método main que simule la llegada y atención
 * ordenada de varios clientes."
 *
 * Ajustes aplicados:
 * - Se implementa un modelo orientado a objetos compuesto por Persona, Turno,
 *   ColaTurnos y el orquestador SistemaTurnos.
 * - La disciplina de atención es estrictamente FIFO (First In, First Out) mediante
 *   una lista simplemente enlazada con referencias a frente y fin.
 * - Cada llegada valida el nombre de la persona, genera una instancia de Persona
 *   y le asigna un Turno con número correlativo único.
 * - Las operaciones de consulta y atención en cola vacía retornan Optional.empty()
 *   para garantizar un manejo de borde robusto sin mutaciones ni excepciones espurias.
 * - Se incluye prevención de desbordamiento para el contador de turnos y validaciones
 *   de parámetros no nulos ni vacíos.
 * - El método main simula el ciclo de vida completo: llegadas sucesivas, inspección
 *   del frente sin alteración, atención ordenada FIFO, atención sobre cola vacía
 *   y reutilización posterior.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SistemaTurnos {

    private final ColaTurnos cola;
    private int proximoNumero;

    /**
     * Construye un nuevo sistema de turnos con la cola vacía y contador inicializado en 1.
     */
    public SistemaTurnos() {
        this.cola = new ColaTurnos();
        this.proximoNumero = 1;
    }

    /**
     * Registra la llegada de una persona asignándole el siguiente turno correlativo
     * y encolándola al final de la fila.
     *
     * @param nombre nombre de la persona que llega
     * @return el turno asignado y encolado
     * @throws IllegalArgumentException si el nombre es nulo o está en blanco
     * @throws IllegalStateException si se ha agotado el rango de números disponibles
     */
    public Turno registrarLlegada(String nombre) {
        Persona persona = new Persona(nombre);

        if (proximoNumero == Integer.MAX_VALUE) {
            throw new IllegalStateException("Se ha alcanzado el límite máximo de turnos disponibles.");
        }

        Turno turno = new Turno(proximoNumero, persona);
        cola.encolar(turno);
        proximoNumero++;
        return turno;
    }

    /**
     * Alias equivalente a {@link #registrarLlegada(String)} para compatibilidad con llamadas de clientes.
     *
     * @param nombreCliente nombre del cliente
     * @return turno asignado
     */
    public Turno registrarCliente(String nombreCliente) {
        return registrarLlegada(nombreCliente);
    }

    /**
     * Atiende a la siguiente persona en espera, retirando su turno del frente de la cola (FIFO).
     *
     * @return un {@link Optional} con el turno atendido, o {@code Optional.empty()} si no hay nadie en espera
     */
    public Optional<Turno> atenderSiguiente() {
        return cola.desencolar();
    }

    /**
     * Consulta quién es la próxima persona a atender sin retirarla de la fila.
     *
     * @return un {@link Optional} con el turno al frente, o {@code Optional.empty()} si la cola está vacía
     */
    public Optional<Turno> consultarSiguiente() {
        return cola.consultarFrente();
    }

    /**
     * Indica si hay personas esperando ser atendidas.
     *
     * @return {@code true} si hay al menos una persona en espera, {@code false} en caso contrario
     */
    public boolean hayPersonasEsperando() {
        return !cola.estaVacia();
    }

    /**
     * Indica si la cola de turnos se encuentra vacía.
     *
     * @return {@code true} si no hay turnos pendientes
     */
    public boolean estaVacio() {
        return cola.estaVacia();
    }

    /**
     * Devuelve la cantidad de turnos actualmente en espera.
     *
     * @return cantidad de personas en espera
     */
    public int obtenerCantidadEsperando() {
        return cola.tamano();
    }

    /**
     * Devuelve una lista inmutable con los turnos pendientes en estricto orden de llegada.
     *
     * @return lista de turnos en espera
     */
    public List<Turno> listarPendientes() {
        return cola.listarPendientes();
    }

    // =========================================================================
    // Clases del Dominio y Estructura de Datos
    // =========================================================================

    /**
     * Representa a un individuo que solicita atención en la oficina.
     */
    public static final class Persona {
        private final String nombre;

        public Persona(String nombre) {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre de la persona no puede ser nulo ni estar vacío.");
            }
            this.nombre = nombre.trim();
        }

        public String obtenerNombre() {
            return nombre;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Persona persona = (Persona) o;
            return Objects.equals(nombre, persona.nombre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre);
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    /**
     * Asocia una persona con un identificador numérico correlativo de atención.
     */
    public static final class Turno {
        private final int numero;
        private final Persona persona;

        public Turno(int numero, Persona persona) {
            if (numero <= 0) {
                throw new IllegalArgumentException("El número de turno debe ser positivo.");
            }
            if (persona == null) {
                throw new IllegalArgumentException("La persona asociada al turno no puede ser nula.");
            }
            this.numero = numero;
            this.persona = persona;
        }

        public int obtenerNumero() {
            return numero;
        }

        public int getNumero() {
            return numero;
        }

        public Persona obtenerPersona() {
            return persona;
        }

        public Persona getPersona() {
            return persona;
        }

        public String obtenerNombreCliente() {
            return persona.obtenerNombre();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Turno turno = (Turno) o;
            return numero == turno.numero && Objects.equals(persona, turno.persona);
        }

        @Override
        public int hashCode() {
            return Objects.hash(numero, persona);
        }

        @Override
        public String toString() {
            return "Turno #" + numero + " - " + persona.obtenerNombre();
        }
    }

    /**
     * Nodo interno para la estructura enlazada de la cola FIFO.
     */
    private static final class Nodo {
        private final Turno valor;
        private Nodo siguiente;

        private Nodo(Turno valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    /**
     * Implementación propia de cola FIFO simplemente enlazada con referencias a frente y fin.
     */
    public static final class ColaTurnos {
        private Nodo frente;
        private Nodo fin;
        private int cantidad;

        public ColaTurnos() {
            this.frente = null;
            this.fin = null;
            this.cantidad = 0;
        }

        /**
         * Inserta un turno al final de la cola.
         *
         * @param turno el turno a encolar
         * @throws IllegalArgumentException si el turno es nulo
         */
        public void encolar(Turno turno) {
            if (turno == null) {
                throw new IllegalArgumentException("El turno a encolar no puede ser nulo.");
            }

            Nodo nuevo = new Nodo(turno);
            if (estaVacia()) {
                frente = nuevo;
            } else {
                fin.siguiente = nuevo;
            }
            fin = nuevo;
            cantidad++;
        }

        /**
         * Retira y devuelve el turno ubicado al frente de la cola.
         *
         * @return {@link Optional} con el turno desencolado, o {@code Optional.empty()} si la cola está vacía
         */
        public Optional<Turno> desencolar() {
            if (estaVacia()) {
                return Optional.empty();
            }

            Turno turnoAtendido = frente.valor;
            frente = frente.siguiente;
            cantidad--;

            if (frente == null) {
                fin = null;
            }

            return Optional.of(turnoAtendido);
        }

        /**
         * Retorna el turno ubicado al frente sin removerlo.
         *
         * @return {@link Optional} con el turno al frente, o {@code Optional.empty()} si la cola está vacía
         */
        public Optional<Turno> consultarFrente() {
            if (estaVacia()) {
                return Optional.empty();
            }
            return Optional.of(frente.valor);
        }

        /**
         * Verifica si la cola no contiene elementos.
         *
         * @return {@code true} si está vacía
         */
        public boolean estaVacia() {
            return cantidad == 0;
        }

        /**
         * Devuelve la cantidad de elementos presentes en la cola.
         *
         * @return cantidad actual
         */
        public int tamano() {
            return cantidad;
        }

        /**
         * Recorre la cola y retorna una lista inmutable con los turnos en orden FIFO.
         *
         * @return lista de turnos en orden de llegada
         */
        public List<Turno> listarPendientes() {
            List<Turno> lista = new ArrayList<>(cantidad);
            Nodo actual = frente;
            while (actual != null) {
                lista.add(actual.valor);
                actual = actual.siguiente;
            }
            return Collections.unmodifiableList(lista);
        }
    }

    // =========================================================================
    // Simulación en Método Principal (main)
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   SIMULACIÓN DEL SISTEMA DE TURNOS (COLA FIFO)   ");
        System.out.println("==================================================\n");

        SistemaTurnos sistema = new SistemaTurnos();

        // 1. Verificación inicial de cola vacía
        System.out.println("--- 1. Estado inicial de la oficina ---");
        System.out.println("¿Hay personas esperando?: " + sistema.hayPersonasEsperando());
        System.out.println("Cantidad en espera: " + sistema.obtenerCantidadEsperando());

        System.out.print("Intento de consultar próximo en cola vacía: ");
        sistema.consultarSiguiente().ifPresentOrElse(
            t -> System.out.println(t),
            () -> System.out.println("[INFO] No hay turnos en espera.")
        );

        System.out.print("Intento de atender en cola vacía: ");
        sistema.atenderSiguiente().ifPresentOrElse(
            t -> System.out.println("Atendido: " + t),
            () -> System.out.println("[INFO] No hay personas para atender.")
        );
        System.out.println();

        // 2. Llegada ordenada de clientes
        System.out.println("--- 2. Llegada secuencial de clientes ---");
        String[] llegadas = {"Ana Gómez", "Bruno Díaz", "Carla Rossi", "Diego Fernández", "Ana Gómez"};

        for (String nombre : llegadas) {
            Turno turno = sistema.registrarLlegada(nombre);
            System.out.println("-> Llegó: " + turno.obtenerPersona()
                    + " | Asignado: " + turno
                    + " (En espera: " + sistema.obtenerCantidadEsperando() + ")");
        }
        System.out.println();

        // 3. Inspección del estado de espera (respetando orden FIFO)
        System.out.println("--- 3. Fila actual de espera (orden FIFO) ---");
        int pos = 1;
        for (Turno t : sistema.listarPendientes()) {
            System.out.println("  Puesto " + pos++ + " -> " + t);
        }
        System.out.println();

        // 4. Consulta del frente sin alterar la cola
        System.out.println("--- 4. Consulta del próximo a atender (sin retirarlo) ---");
        sistema.consultarSiguiente().ifPresent(t ->
            System.out.println("Próximo en ser llamado: " + t)
        );
        System.out.println("Cantidad en espera tras consulta: " + sistema.obtenerCantidadEsperando());
        System.out.println();

        // 5. Proceso de atención ordenada
        System.out.println("--- 5. Atención ordenada de los clientes ---");
        while (sistema.hayPersonasEsperando()) {
            Optional<Turno> atendido = sistema.atenderSiguiente();
            atendido.ifPresent(t ->
                System.out.println("<< Atendiendo a " + t.obtenerPersona()
                        + " con " + t
                        + " | Restantes en fila: " + sistema.obtenerCantidadEsperando())
            );
        }
        System.out.println();

        // 6. Verificación posterior al vaciado
        System.out.println("--- 6. Estado final tras atender a todos ---");
        System.out.println("¿Hay personas esperando?: " + sistema.hayPersonasEsperando());
        System.out.print("Intento de atender nuevamente: ");
        sistema.atenderSiguiente().ifPresentOrElse(
            t -> System.out.println("Atendido: " + t),
            () -> System.out.println("[INFO] No hay personas para atender (cola vacía controlada).")
        );
        System.out.println();

        // 7. Reutilización del sistema tras vaciarse
        System.out.println("--- 7. Nueva llegada posterior al vaciado (reutilización) ---");
        Turno nuevoTurno = sistema.registrarLlegada("Elena Vega");
        System.out.println("-> Nueva llegada: " + nuevoTurno);
        System.out.println("Próximo a atender: " + sistema.consultarSiguiente().orElse(null));
        System.out.println("Atendiendo: " + sistema.atenderSiguiente().orElse(null));
        System.out.println("¿Cola vacía?: " + sistema.estaVacio());
    }
}
