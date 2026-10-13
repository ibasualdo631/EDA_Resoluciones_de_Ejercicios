/*
 * Prompt empleado:
 * "Implementá DesperdicioCola.java como una cola lineal simple almacenada en
 * un arreglo de cinco posiciones. Incluí encolar y desencolar, y un main que
 * muestre visualmente el arreglo completo, sus índices y el estado de front
 * y rear después de cada operación. Demostrá que las posiciones libres a la
 * izquierda de front no se reutilizan y que, al llegar rear al final, se
 * produce un falso desbordamiento aunque queden celdas vacías."
 *
 * La cola es lineal: no desplaza elementos ni vuelve circularmente al inicio.
 */
public class DesperdicioCola {

    private static final int CAPACIDAD = 5;
    private static final String VACIO = "_";

    private final String[] elementos;
    private int front;
    private int rear;

    public DesperdicioCola() {
        elementos = new String[CAPACIDAD];
        for (int indice = 0; indice < elementos.length; indice++) {
            elementos[indice] = VACIO;
        }
        front = 0;
        rear = -1;
    }

    /**
     * Agrega un valor después de rear, sin reutilizar espacios anteriores a front.
     *
     * @param valor elemento que se agrega
     * @return {@code true} si se insertó; {@code false} si rear llegó al final
     */
    public boolean encolar(String valor) {
        if (valor == null || valor.trim().isEmpty() || VACIO.equals(valor)) {
            throw new IllegalArgumentException(
                    "El valor debe ser no nulo, no vacío y distinto de \"_\".");
        }
        if (rear == elementos.length - 1) {
            return false;
        }

        elementos[++rear] = valor;
        return true;
    }

    /**
     * Retira y devuelve el elemento ubicado en front.
     *
     * @return elemento retirado
     * @throws IllegalStateException si la cola está vacía
     */
    public String desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException(
                    "No se puede desencolar: la cola está vacía.");
        }

        String valor = elementos[front];
        elementos[front] = VACIO;
        front++;
        return valor;
    }

    private boolean estaVacia() {
        return front > rear;
    }

    private void mostrarEstado(String operacion, String resultado) {
        System.out.println("\n" + operacion + " -> " + resultado);

        System.out.print("Índice:  ");
        for (int indice = 0; indice < elementos.length; indice++) {
            System.out.printf("%-4d", indice);
        }
        System.out.println();

        System.out.print("Arreglo: ");
        for (String elemento : elementos) {
            System.out.printf("%-4s", elemento);
        }
        System.out.println();

        System.out.println("front = " + front + " ("
                + (front < elementos.length ? "índice " + front : "fuera del arreglo")
                + "), rear = " + rear + " ("
                + (rear >= 0 ? "índice " + rear : "cola sin elementos encolados") + ")");

        int vacias = 0;
        StringBuilder indicesVacios = new StringBuilder();
        for (int indice = 0; indice < elementos.length; indice++) {
            if (VACIO.equals(elementos[indice])) {
                vacias++;
                if (indicesVacios.length() > 0) {
                    indicesVacios.append(", ");
                }
                indicesVacios.append(indice);
            }
        }
        System.out.println("Celdas vacías: " + vacias
                + (vacias > 0 ? " (índices " + indicesVacios + ")" : ""));
    }

    public static void main(String[] args) {
        DesperdicioCola cola = new DesperdicioCola();

        mostrarEncolado(cola, "A");
        mostrarEncolado(cola, "B");
        mostrarEncolado(cola, "C");
        mostrarEncolado(cola, "D");

        String extraido = cola.desencolar();
        cola.mostrarEstado("desencolar()", "se extrajo " + extraido);

        extraido = cola.desencolar();
        cola.mostrarEstado("desencolar()", "se extrajo " + extraido);

        mostrarEncolado(cola, "E");

        boolean insertado = cola.encolar("F");
        String resultado;
        if (insertado) {
            resultado = "F se insertó";
        } else {
            resultado = "FALSO OVERFLOW: encolar(F) se rechaza en este instante "
                    + "porque rear = " + cola.rear + " alcanzó el último índice "
                    + (CAPACIDAD - 1) + ". El arreglo todavía tiene celdas vacías "
                    + "en " + indicesVaciosAntesDeFront(cola)
                    + ", pero están antes de front y la cola lineal no las reutiliza; "
                    + "no es un arreglo físicamente lleno.";
        }
        cola.mostrarEstado("encolar(F)", resultado);
    }

    private static void mostrarEncolado(DesperdicioCola cola, String valor) {
        boolean insertado = cola.encolar(valor);
        cola.mostrarEstado(
                "encolar(" + valor + ")",
                insertado ? valor + " se insertó" : "inserción rechazada");
    }

    private static String indicesVaciosAntesDeFront(DesperdicioCola cola) {
        StringBuilder indices = new StringBuilder();
        for (int indice = 0; indice < cola.front; indice++) {
            if (VACIO.equals(cola.elementos[indice])) {
                if (indices.length() > 0) {
                    indices.append(" y ");
                }
                indices.append("índice ").append(indice);
            }
        }
        return indices.length() == 0 ? "ninguna celda" : indices.toString();
    }
}
