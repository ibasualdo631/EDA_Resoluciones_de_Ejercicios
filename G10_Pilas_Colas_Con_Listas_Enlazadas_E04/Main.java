import java.util.Objects;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        probarColaDeNombres();
        probarColaDeClientes();
        probarAislamientoEntreColas();
        probarVaciadoYReutilizacion();
        System.out.println("Todas las pruebas de la cola genérica pasaron.");
    }

    private static void probarColaDeNombres() {
        ColaEnlazada<String> nombres = new ColaEnlazada<>();
        nombres.encolar("Ana");
        nombres.encolar("Luis");
        nombres.encolar("Mora");

        afirmarIgual("Ana", nombres.frente(), "frente inicial de nombres");
        afirmarIgual("Ana", nombres.desencolar(), "primer nombre en salir");
        afirmarIgual("Luis", nombres.frente(), "consultar frente no retira");
        afirmarIgual("Luis", nombres.desencolar(), "segundo nombre en salir");
        afirmarIgual("Mora", nombres.desencolar(), "tercer nombre en salir");
        afirmar(nombres.estaVacia(), "la cola de nombres debe quedar vacía");
    }

    private static void probarColaDeClientes() {
        ColaEnlazada<Cliente> clientes = new ColaEnlazada<>();
        Cliente clienteA = new Cliente("Cliente A");
        Cliente clienteB = new Cliente("Cliente B");
        Cliente clienteC = new Cliente("Cliente C");

        clientes.encolar(clienteA);
        clientes.encolar(clienteB);
        clientes.encolar(clienteC);

        afirmar(clientes.frente() == clienteA, "el primer cliente debe estar al frente");
        afirmar(clientes.desencolar() == clienteA, "debe salir primero clienteA");
        afirmar(clientes.desencolar() == clienteB, "debe salir luego clienteB");
        afirmar(clientes.desencolar() == clienteC, "debe salir al final clienteC");
        afirmar(clientes.estaVacia(), "la cola de clientes debe quedar vacía");
    }

    private static void probarAislamientoEntreColas() {
        ColaEnlazada<String> nombres = new ColaEnlazada<>();
        ColaEnlazada<Cliente> clientes = new ColaEnlazada<>();
        Cliente cliente = new Cliente("Cliente independiente");

        nombres.encolar("Ana");
        clientes.encolar(cliente);
        nombres.encolar("Luis");

        afirmarIgual("Ana", nombres.desencolar(), "la cola de nombres mantiene su FIFO");
        afirmar(clientes.desencolar() == cliente, "la cola de clientes mantiene su FIFO");
        afirmarIgual("Luis", nombres.desencolar(), "la otra cola conserva sus pendientes");
        afirmar(nombres.estaVacia() && clientes.estaVacia(),
                "ambas colas deben poder vaciarse independientemente");
    }

    private static void probarVaciadoYReutilizacion() {
        ColaEnlazada<String> cola = new ColaEnlazada<>();
        afirmarLanzaIllegalState(cola::desencolar, "desencolar una cola vacía");
        afirmarLanzaIllegalState(cola::frente, "consultar el frente de una cola vacía");

        cola.encolar("Primero");
        afirmarIgual("Primero", cola.desencolar(), "retirar el único elemento");
        afirmar(cola.estaVacia(), "retirar el único elemento debe vaciar la cola");

        cola.encolar("Después");
        afirmarIgual("Después", cola.desencolar(), "reutilizar la cola vaciada");
        afirmar(cola.estaVacia(), "la cola reutilizada debe quedar vacía");
    }

    private static void afirmarIgual(Object esperado, Object actual, String caso) {
        if (!Objects.equals(esperado, actual)) {
            throw new AssertionError(
                    caso + " (esperado: " + esperado + ", actual: " + actual + ")");
        }
    }

    private static void afirmar(boolean condicion, String caso) {
        if (!condicion) {
            throw new AssertionError(caso);
        }
    }

    private static void afirmarLanzaIllegalState(Runnable operacion, String caso) {
        try {
            operacion.run();
        } catch (IllegalStateException esperada) {
            return;
        }
        throw new AssertionError(caso + " debe lanzar IllegalStateException");
    }
}
