import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Pruebas ejecutables sin dependencias externas para {@link PilaEnteros}.
 */
public final class PruebasPilaEnteros {
    private static int pruebasEjecutadas;

    private PruebasPilaEnteros() {
    }

    public static void main(String[] args) {
        probarPilaVacia();
        probarInsercionYExtraccionDeUnElemento();
        probarOrdenLifoYValoresEnteros();
        probarBusquedaSinModificarLaPila();
        probarImpresionSinModificarLaPila();
        probarReutilizacionTrasVaciar();
        probarFlujoNulo();

        System.out.println("Pruebas superadas: " + pruebasEjecutadas);
    }

    private static void probarPilaVacia() {
        PilaEnteros pila = new PilaEnteros();
        afirmarVerdadero(pila.isEmpty(), "una pila nueva debe estar vacia");
        afirmarFalso(pila.contains(5), "la busqueda en vacio debe devolver false");
        afirmarLanza(IllegalStateException.class, pila::pop,
                "pop en vacio debe lanzar IllegalStateException");
        afirmarVerdadero(pila.isEmpty(), "pop fallido no debe alterar la pila");
        afirmarLanza(IllegalStateException.class, pila::peek,
                "peek en vacio debe lanzar IllegalStateException");
    }

    private static void probarInsercionYExtraccionDeUnElemento() {
        PilaEnteros pila = new PilaEnteros();
        pila.push(42);

        afirmarFalso(pila.isEmpty(), "la pila debe dejar de estar vacia tras push");
        afirmarIgual(42, pila.peek(), "peek debe consultar el unico elemento");
        afirmarFalso(pila.isEmpty(), "peek no debe retirar el elemento");
        afirmarIgual(42, pila.pop(), "pop debe devolver el elemento apilado");
        afirmarVerdadero(pila.isEmpty(), "pop del ultimo elemento debe vaciar la pila");
    }

    private static void probarOrdenLifoYValoresEnteros() {
        PilaEnteros pila = new PilaEnteros();
        pila.push(-7);
        pila.push(0);
        pila.push(0);
        pila.push(Integer.MAX_VALUE);
        pila.push(Integer.MIN_VALUE);

        afirmarIgual(Integer.MIN_VALUE, pila.pop(), "debe admitir Integer.MIN_VALUE");
        afirmarIgual(Integer.MAX_VALUE, pila.pop(), "debe admitir Integer.MAX_VALUE");
        afirmarIgual(0, pila.pop(), "los duplicados deben conservar orden LIFO");
        afirmarIgual(0, pila.pop(), "debe devolver el segundo cero");
        afirmarIgual(-7, pila.pop(), "debe devolver valores negativos");
        afirmarVerdadero(pila.isEmpty(), "la pila debe quedar vacia tras extraer todo");
    }

    private static void probarBusquedaSinModificarLaPila() {
        PilaEnteros pila = new PilaEnteros();
        pila.push(-3);
        pila.push(8);
        pila.push(15);

        afirmarVerdadero(pila.contains(15), "debe encontrar el valor del tope");
        afirmarVerdadero(pila.contains(8), "debe encontrar un valor intermedio");
        afirmarVerdadero(pila.contains(-3), "debe encontrar el valor de la base");
        afirmarFalso(pila.contains(99), "debe rechazar un valor ausente");
        afirmarIgual(15, pila.peek(), "contains no debe alterar el tope");
        afirmarIgual(15, pila.pop(), "contains no debe alterar el orden");
    }

    private static void probarImpresionSinModificarLaPila() {
        PilaEnteros pila = new PilaEnteros();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream salida = new PrintStream(bytes, true, StandardCharsets.UTF_8);

        pila.imprimir(salida);
        afirmarIgual("Pila vacia" + System.lineSeparator(), texto(bytes),
                "la impresion de una pila vacia debe ser explicita");

        bytes.reset();
        pila.push(3);
        pila.push(2);
        pila.push(1);
        pila.imprimir(salida);
        afirmarIgual("Tope -> 1 -> 2 -> 3 -> Base" + System.lineSeparator(), texto(bytes),
                "la impresion debe recorrer del tope a la base");
        afirmarIgual(1, pila.peek(), "imprimir no debe alterar el tope");
        afirmarIgual(1, pila.pop(), "imprimir no debe alterar el orden");

        salida.close();
    }

    private static void probarReutilizacionTrasVaciar() {
        PilaEnteros pila = new PilaEnteros();
        pila.push(4);
        pila.pop();
        pila.push(9);

        afirmarIgual(9, pila.peek(), "debe poder reutilizarse tras quedar vacia");
        afirmarIgual(9, pila.pop(), "la pila reutilizada debe desapilar correctamente");
    }

    private static void probarFlujoNulo() {
        PilaEnteros pila = new PilaEnteros();
        afirmarLanza(NullPointerException.class, () -> pila.imprimir(null),
                "imprimir con flujo nulo debe informar el argumento invalido");
    }

    private static String texto(ByteArrayOutputStream bytes) {
        return bytes.toString(StandardCharsets.UTF_8);
    }

    private static void afirmarVerdadero(boolean condicion, String mensaje) {
        afirmar(condicion, mensaje);
    }

    private static void afirmarFalso(boolean condicion, String mensaje) {
        afirmar(!condicion, mensaje);
    }

    private static void afirmarIgual(int esperado, int actual, String mensaje) {
        afirmar(esperado == actual, mensaje + " (esperado: " + esperado + ", actual: " + actual + ")");
    }

    private static void afirmarIgual(String esperado, String actual, String mensaje) {
        afirmar(esperado.equals(actual), mensaje + " (esperado: " + esperado + ", actual: " + actual + ")");
    }

    private static void afirmarLanza(Class<? extends Throwable> tipo, Runnable operacion, String mensaje) {
        try {
            operacion.run();
        } catch (Throwable error) {
            afirmar(tipo.isInstance(error), mensaje + " (tipo recibido: " + error.getClass().getSimpleName() + ")");
            return;
        }
        afirmar(false, mensaje + " (no se lanzo ninguna excepcion)");
    }

    private static void afirmar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
        pruebasEjecutadas++;
    }
}
