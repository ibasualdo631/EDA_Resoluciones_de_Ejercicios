# Plan técnico — Historial de navegación con pila enlazada

## 1. Arquitectura general

Implementar el gestor en Java dentro de un único archivo y una única clase pública, `HistorialNavegacion`, que concentre el estado del historial, sus operaciones y la interacción por consola. La pila se construirá manualmente con nodos enlazados, sin depender de una colección que oculte su funcionamiento.

El estado esencial será una referencia `tope` al nodo que representa la página actual. Cada nodo almacenará el nombre o URL de una página y una referencia `siguiente` al nodo inferior de la pila. Un `tope` igual a `null` representa un historial vacío.

## 2. Justificación de la estructura

La pila reproduce directamente la navegación hacia atrás. Al visitar A, luego B y luego C, el orden interno es `C -> B -> A`: la visita más reciente está en el tope. Volver atrás retira C mediante `pop`, dejando B en el tope, que es precisamente la página visitada inmediatamente antes. Aplicar LIFO (último en entrar, primero en salir) permite deshacer las visitas en orden inverso al que ocurrieron, sin buscar ni reordenar elementos.

La lista enlazada permite agregar y quitar el nodo superior en tiempo constante, conservando las páginas anteriores. Las operaciones de apilar y desapilar cuestan O(1); consultar la página actual cuesta O(1); imprimir el historial cuesta O(n), donde n es el número de páginas almacenadas.

## 3. Componentes y datos

- `HistorialNavegacion`: clase pública que contiene el punto de entrada, el estado, la lógica de la pila y el menú.
- `Nodo`: representación privada de cada página, con:
  - `String pagina`
  - `Nodo siguiente`
- `Nodo tope`: referencia a la página actual y comienzo de la cadena enlazada.

La representación de nodos será un detalle interno de la implementación del gestor; no se expondrán referencias ni operaciones de modificación directa a quien use la clase.

## 4. Operaciones previstas

- `void visitar(String pagina)`: crea un nodo y lo enlaza antes del tope actual; el nuevo nodo pasa a ser el tope.
- `boolean volverAtras()`: si hay al menos dos nodos, elimina el tope y devuelve `true`; si hay cero o uno, conserva el estado y devuelve `false`.
- `String obtenerPaginaActual()`: devuelve el dato del tope, o `null` si el historial está vacío. La capa de consola traduce el caso vacío a un mensaje comprensible.
- `void imprimirHistorial()`: recorre desde el tope hasta el final, imprime cada página y no modifica enlaces ni tope. Si el tope es `null`, informa que el historial está vacío.
- `static void main(String[] args)`: crea el gestor y presenta un menú repetitivo para visitar, volver atrás, consultar la página actual, imprimir el historial o salir.

La validación de entradas de consola debe evitar ejecutar una opción inexistente y permitir que el usuario continúe usando el menú. Si se ingresa un valor que no representa una página, no se agregará al historial.

## 5. Secuencia lógica

1. Inicializar `tope` en `null`.
2. Mostrar las opciones del menú y leer una opción.
3. Ejecutar la operación correspondiente:
   - Visitar: solicitar una página y colocarla en el tope.
   - Volver atrás: verificar que existan al menos dos nodos antes de retirar el actual; informar si no hay una página anterior.
   - Ver actual: consultar el tope sin modificar la pila; informar si está vacía.
   - Imprimir: recorrer desde el tope hacia el fondo, sin desapilar.
   - Salir: terminar el ciclo.
4. Repetir hasta seleccionar salir.

## 6. Invariantes y decisiones

- `tope == null` si y solo si no hay páginas en el historial.
- Si hay páginas, `tope.pagina` siempre es la página actual.
- `tope.siguiente` referencia la visita previa más reciente.
- Consultar e imprimir no cambian la estructura.
- «Volver atrás» con cero o una página no cambia la estructura: sin una página anterior, el historial debe permanecer igual.
- El orden de impresión será desde la página actual (tope) hacia las anteriores, dado que el enunciado no fija otro orden.