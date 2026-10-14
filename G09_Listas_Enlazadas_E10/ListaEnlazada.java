/*
 * Prompt inicial:
 * Converti la lista enlazada simple de enteros en una implementacion generica
 * ListaEnlazada<T> con nodos Nodo<T>. Conserva la insercion al inicio y al
 * final, el recorrido, la consulta de vaciedad y el tamano. Permiti eliminar
 * la primera ocurrencia de un elemento usando igualdad, sin requerir que T
 * sea numerico ni ordenable. Inclui un main que pruebe Integer, String y un
 * objeto Alumno.
 *
 * Ajustes tecnicos:
 * - Nodo<T> es una clase interna estatica y privada; la lista mantiene head y
 *   size, como en la implementacion simple de referencia.
 * - insertarAlInicio opera en O(1); insertarAlFinal y eliminar operan en O(n)
 *   en el peor caso; imprimir y estaVacia operan en O(n) y O(1),
 *   respectivamente; getSize opera en O(1).
 * - eliminar quita la primera coincidencia segun Objects.equals y retorna si
 *   encontro el elemento. Los elementos null estan permitidos.
 * - main comprueba insercion, recorrido, vaciedad, tamano y eliminacion con
 *   Integer, String y Alumno, incluidos algunos casos limite.
 */
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class ListaEnlazada<T> {
    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        private Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> head;
    private int size;

    public ListaEnlazada() {
        head = null;
        size = 0;
    }

    public void insertarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = head;
        head = nuevo;
        size++;
    }

    public void insertarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (head == null) {
            head = nuevo;
        } else {
            Nodo<T> actual = head;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }

        size++;
    }

    public boolean eliminar(T dato) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.dato, dato)) {
            head = head.siguiente;
            size--;
            return true;
        }

        Nodo<T> anterior = head;
        Nodo<T> actual = head.siguiente;
        while (actual != null) {
            if (Objects.equals(actual.dato, dato)) {
                anterior.siguiente = actual.siguiente;
                size--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }

        return false;
    }

    public void imprimir() {
        Nodo<T> actual = head;
        while (actual != null) {
            System.out.print(String.valueOf(actual.dato));
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    private static <T> void verificar(
            boolean condicion,
            String caso
    ) {
        if (!condicion) {
            throw new AssertionError("Fallo: " + caso);
        }
        System.out.println("OK: " + caso);
    }

    private static String capturarRecorrido(ListaEnlazada<?> lista) {
        ByteArrayOutputStream contenido = new ByteArrayOutputStream();
        PrintStream salidaOriginal = System.out;

        try (PrintStream captura = new PrintStream(contenido)) {
            System.setOut(captura);
            lista.imprimir();
        } finally {
            System.setOut(salidaOriginal);
        }

        return contenido.toString();
    }

    private static void probarInteger() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        verificar(lista.estaVacia(), "Integer: comienza vacia");
        verificar(!lista.eliminar(10), "Integer: eliminar de lista vacia");

        lista.insertarAlInicio(20);
        lista.insertarAlInicio(10);
        lista.insertarAlFinal(30);
        verificar(
                capturarRecorrido(lista).equals("10 -> 20 -> 30"
                        + System.lineSeparator()),
                "Integer: recorrido conserva orden y valores"
        );
        verificar(lista.getSize() == 3, "Integer: inserciones y tamano");
        verificar(lista.eliminar(20), "Integer: eliminar elemento del medio");
        verificar(lista.eliminar(10), "Integer: eliminar cabeza");
        verificar(lista.eliminar(30), "Integer: eliminar ultimo elemento");
        verificar(lista.estaVacia() && lista.getSize() == 0,
                "Integer: queda vacia al eliminar todos los elementos");
    }

    private static void probarString() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.insertarAlFinal("mundo");
        lista.insertarAlInicio("hola");
        lista.insertarAlFinal("!");
        verificar(
                capturarRecorrido(lista).equals("hola -> mundo -> !"
                        + System.lineSeparator()),
                "String: recorrido conserva orden y valores"
        );
        verificar(lista.getSize() == 3, "String: inserciones y tamano");
        verificar(lista.eliminar("mundo"), "String: eliminar por igualdad");
        verificar(!lista.eliminar("ausente"), "String: valor inexistente");

        lista.insertarAlFinal(null);
        verificar(lista.eliminar(null), "String: admite y elimina null");
    }

    private static void probarAlumno() {
        ListaEnlazada<Alumno> lista = new ListaEnlazada<>();
        Alumno ana = new Alumno(101, "Ana");
        lista.insertarAlInicio(ana);
        Alumno luis = new Alumno(202, "Luis");
        lista.insertarAlFinal(luis);
        verificar(
                capturarRecorrido(lista).equals(
                        "Ana (legajo 101) -> Luis (legajo 202)"
                                + System.lineSeparator()
                ),
                "Alumno: recorrido conserva objetos, datos y orden"
        );

        verificar(lista.getSize() == 2, "Alumno: inserciones y tamano");
        verificar(lista.eliminar(new Alumno(101, "Ana")),
                "Alumno: eliminar por igualdad logica");
        verificar(!lista.eliminar(new Alumno(303, "Eva")),
                "Alumno: no elimina un alumno inexistente");

        ListaEnlazada<Alumno> unitaria = new ListaEnlazada<>();
        unitaria.insertarAlFinal(new Alumno(404, "Mia"));
        verificar(
                capturarRecorrido(unitaria).equals(
                        "Mia (legajo 404)" + System.lineSeparator()
                ),
                "Alumno: recorrido de un unico objeto"
        );
        verificar(unitaria.eliminar(new Alumno(404, "Mia"))
                        && unitaria.estaVacia(),
                "Alumno: elimina el unico objeto"
        );
    }

    public static void main(String[] args) {
        probarInteger();
        probarString();
        probarAlumno();
        System.out.println("Todas las pruebas finalizaron correctamente.");
    }

    private static final class Alumno {
        private final int legajo;
        private final String nombre;

        private Alumno(int legajo, String nombre) {
            this.legajo = legajo;
            this.nombre = nombre;
        }

        @Override
        public boolean equals(Object objeto) {
            if (this == objeto) {
                return true;
            }
            if (!(objeto instanceof Alumno)) {
                return false;
            }
            Alumno otro = (Alumno) objeto;
            return legajo == otro.legajo;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(legajo);
        }

        @Override
        public String toString() {
            return nombre + " (legajo " + legajo + ")";
        }
    }
}
