/*
 * Prompt empleado:
 * "Implementá ColaImpresion.java en un archivo único. Incluí el bloque de
 * comentarios inicial con el prompt empleado y un main que demuestre el envío
 * y procesamiento secuencial de documentos."
 *
 * Se modela la cola de impresión con disciplina FIFO: los documentos se
 * agregan al final y se procesan desde el frente, respetando el orden de llegada.
 */
import java.util.NoSuchElementException;

public class ColaImpresion {

    private Nodo frente;
    private Nodo fin;
    private int cantidad;

    /**
     * Agrega un documento al final de la cola.
     *
     * @param documento documento que se enviará a imprimir
     * @throws IllegalArgumentException si el documento es nulo
     */
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
        cantidad++;
    }

    /**
     * Retira y devuelve el documento que lleva más tiempo esperando.
     *
     * @return documento procesado
     * @throws NoSuchElementException si no hay documentos pendientes
     */
    public Documento procesarSiguiente() {
        if (estaVacia()) {
            throw new NoSuchElementException(
                    "No se puede procesar: no hay documentos pendientes.");
        }

        Documento documento = frente.documento;
        frente = frente.siguiente;
        cantidad--;
        if (frente == null) {
            fin = null;
        }
        return documento;
    }

    /**
     * Consulta el próximo documento sin retirarlo de la cola.
     *
     * @return documento ubicado al frente
     * @throws NoSuchElementException si no hay documentos pendientes
     */
    public Documento verFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay documentos pendientes.");
        }
        return frente.documento;
    }

    /**
     * Indica si no hay documentos pendientes.
     *
     * @return {@code true} si la cola está vacía
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Devuelve la cantidad de documentos pendientes.
     *
     * @return cantidad actual de documentos
     */
    public int obtenerCantidad() {
        return cantidad;
    }

    public static void main(String[] args) {
        ColaImpresion cola = new ColaImpresion();
        Documento[] documentos = {
            new Documento(1, "Informe final.pdf", 12),
            new Documento(2, "Presentacion.pptx", 24),
            new Documento(3, "Apuntes.pdf", 8)
        };

        System.out.println("Envío de documentos a la cola:");
        for (Documento documento : documentos) {
            cola.encolar(documento);
            System.out.println("  Enviado: " + documento);
        }

        System.out.println("\nProcesamiento secuencial:");
        while (!cola.estaVacia()) {
            System.out.println("  Imprimiendo: " + cola.procesarSiguiente());
        }

        System.out.println("\nDocumentos pendientes: " + cola.obtenerCantidad());
    }

    public static final class Documento {

        private final int id;
        private final String nombre;
        private final int cantidadPaginas;

        public Documento(int id, String nombre, int cantidadPaginas) {
            if (id <= 0) {
                throw new IllegalArgumentException("El identificador debe ser positivo.");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "El nombre del documento no puede estar vacío.");
            }
            if (cantidadPaginas <= 0) {
                throw new IllegalArgumentException(
                        "La cantidad de páginas debe ser positiva.");
            }

            this.id = id;
            this.nombre = nombre.trim();
            this.cantidadPaginas = cantidadPaginas;
        }

        public int obtenerId() {
            return id;
        }

        public String obtenerNombre() {
            return nombre;
        }

        public int obtenerCantidadPaginas() {
            return cantidadPaginas;
        }

        @Override
        public String toString() {
            return "Documento " + id + " - " + nombre
                    + " (" + cantidadPaginas + " páginas)";
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
