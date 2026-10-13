/*
 * Prompt empleado:
 * "Implementá ColaImpresion.java en un archivo único. Incluí el bloque de
 * comentarios requerido por la cátedra y un main que demuestre el envío y
 * procesamiento secuencial de documentos."
 *
 * Ajustes aplicados:
 * - Cada Documento almacena nombre y cantidad de páginas.
 * - La cola es estrictamente FIFO (lista enlazada con frente y fin): encolar y
 *   procesar son O(1) y la cantidad de páginas nunca altera la prioridad.
 * - Procesar una cola vacía devuelve Optional.empty() sin modificar el estado.
 */
import java.util.Optional;

public class ColaImpresion {

    private Nodo frente;
    private Nodo fin;

    /** Agrega un documento al final de la cola. */
    public void encolar(Documento documento) {
        if (documento == null) {
            throw new IllegalArgumentException("El documento no puede ser nulo.");
        }
        Nodo nuevo = new Nodo(documento);
        if (estaVacia()) {
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
    }

    /** Retira el documento del frente; vacío si no hay pendientes. */
    public Optional<Documento> procesarSiguiente() {
        if (estaVacia()) {
            return Optional.empty();
        }
        Documento documento = frente.documento;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return Optional.of(documento);
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public static void main(String[] args) {
        ColaImpresion cola = new ColaImpresion();

        System.out.println("Envío de documentos:");
        Documento[] documentos = {
            new Documento("Informe", 3),
            new Documento("Apuntes", 1),
            new Documento("TP", 5)
        };
        for (Documento d : documentos) {
            cola.encolar(d);
            System.out.println("  Encolado: " + d);
        }

        System.out.println("\nProcesamiento secuencial (orden de llegada):");
        Optional<Documento> procesado = cola.procesarSiguiente();
        while (procesado.isPresent()) {
            System.out.println("  Procesado: " + procesado.get());
            procesado = cola.procesarSiguiente();
        }

        System.out.println("\nIntento con la cola vacía:");
        System.out.println("  " + (cola.procesarSiguiente().isPresent()
                ? "Error: se procesó un documento inexistente"
                : "No hay documentos pendientes para imprimir"));
    }

    public static final class Documento {

        private final String nombre;
        private final int cantidadPaginas;

        public Documento(String nombre, int cantidadPaginas) {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            if (cantidadPaginas <= 0) {
                throw new IllegalArgumentException("Las páginas deben ser mayores a 0.");
            }
            this.nombre = nombre.trim();
            this.cantidadPaginas = cantidadPaginas;
        }

        public String obtenerNombre() {
            return nombre;
        }

        public int obtenerCantidadPaginas() {
            return cantidadPaginas;
        }

        @Override
        public String toString() {
            return nombre + " (" + cantidadPaginas + " páginas)";
        }
    }

    private static final class Nodo {

        private final Documento documento;
        private Nodo siguiente;

        private Nodo(Documento documento) {
            this.documento = documento;
        }
    }
}
