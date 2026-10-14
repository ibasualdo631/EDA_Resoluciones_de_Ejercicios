public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ColaEnlazada cola = new ColaEnlazada();
        System.out.println("¿Está vacía? " + cola.estaVacia());

        cola.encolar(10);
        cola.encolar(-3);
        cola.encolar(0);

        System.out.print("Contenido: ");
        cola.imprimir();
        System.out.println("Frente: " + cola.frente());
        System.out.println("¿Contiene -3? " + cola.contiene(-3));
        System.out.println("¿Contiene 7? " + cola.contiene(7));

        while (!cola.estaVacia()) {
            System.out.println("Desencolado: " + cola.desencolar());
        }

        System.out.println("¿Está vacía después de retirar todo? " + cola.estaVacia());
        cola.encolar(42);
        System.out.print("Después de volver a encolar: ");
        cola.imprimir();
    }
}
