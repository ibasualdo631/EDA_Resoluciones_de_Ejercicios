# Plan técnico — Pila enlazada de enteros

## 1. Decisiones de arquitectura

La solución se organizará en una clase que encapsula la pila y sus nodos, y una clase de demostración opcional con el punto de entrada `main`. Así, la estructura de datos puede probarse sin depender de la interfaz de consola.

- **`PilaEnteros`**: administra el estado y expone las operaciones de pila.
- **`Nodo`**: tipo interno privado que almacena un `int` y una referencia al siguiente nodo. No se expone fuera de `PilaEnteros`.
- **`Main` o clase de demostración**: instancia la pila y muestra el uso de sus operaciones si el ejercicio requiere una ejecución interactiva o de ejemplo. El enunciado no fija un menú ni un protocolo de entrada, por lo que no se añade uno como requisito.

No se mantendrá una referencia a la base ni un contador de elementos: ninguna operación requerida los necesita y evitarlos mantiene la implementación mínima. Si posteriormente se necesitara consultar el tamaño, podría agregarse un contador actualizado en cada inserción y extracción.

## 2. Representación e invariantes

```text
head
  |
  v
[tope | next] -> [elemento | next] -> ... -> [base | null]
```

- `head` es una referencia privada al primer nodo.
- Si la pila está vacía, `head == null`.
- En una pila no vacía, `head.valor` es el tope.
- El campo `siguiente` de cada nodo enlaza con el elemento que está inmediatamente debajo.
- El nodo de la base tiene `siguiente == null`.
- Los métodos de consulta no modifican enlaces ni cambian `head`.

El tope se ubica en `head` porque en una lista simplemente enlazada el primer nodo puede insertarse o retirarse sin buscar un predecesor. Esto alinea las operaciones más frecuentes de una pila con el extremo de acceso directo de la lista.

## 3. API y responsabilidades

Firmas propuestas para Java:

| Firma | Responsabilidad |
|---|---|
| `void push(int valor)` | Crea un nodo y lo coloca en el tope. |
| `int pop()` | Devuelve y retira el valor del tope. Si la pila está vacía, lanza `IllegalStateException` antes de modificar el estado. |
| `int peek()` | Devuelve el valor del tope sin retirarlo. Si está vacía, lanza `IllegalStateException`. |
| `boolean isEmpty()` | Indica si `head == null`. |
| `boolean contains(int valor)` | Busca el valor recorriendo los nodos desde `head`; no altera la pila. |
| `void imprimir(PrintStream salida)` | Escribe los elementos de tope a base; para una pila vacía, escribe un mensaje explícito. No altera la pila. |

`PrintStream` permite separar la estructura de la salida concreta y hace posible dirigir la impresión a consola o a un flujo de prueba. La clase de demostración puede invocar `imprimir(System.out)`.

## 4. Algoritmos principales

### `push(int valor)`

1. Crear `nuevo` con el valor recibido.
2. Asignar `nuevo.siguiente = head`.
3. Asignar `head = nuevo`.

Solo se ejecutan asignaciones y creación de un nodo, sin importar cuántos elementos haya: **O(1)**.

### `pop()`

1. Si `head == null`, lanzar `IllegalStateException`.
2. Guardar `head.valor` en una variable local.
3. Avanzar `head = head.siguiente`.
4. Devolver el valor guardado.

Se comprueba y actualiza una cantidad fija de referencias; no se recorre la lista: **O(1)**. Al retirar el único nodo, su enlace siguiente es `null`, por lo que `head` queda correctamente en estado vacío.

### Consultas y recorrido

- `peek()` consulta `head.valor` tras verificar que exista un nodo: **O(1)**.
- `isEmpty()` compara `head` con `null`: **O(1)**.
- `contains(int valor)` visita nodos hasta hallar el valor o alcanzar `null`: **O(n)** en el peor caso.
- `imprimir(PrintStream salida)` visita cada nodo una vez desde el tope hacia la base: **O(n)**.

## 5. Secuencia de ejecución

1. Crear una instancia de `PilaEnteros`; su `head` comienza en `null`.
2. Invocar `push` por cada entero que se quiera agregar.
3. Usar `peek` o `contains` para consultar sin retirar elementos.
4. Usar `imprimir` para recorrer el estado actual de tope a base.
5. Invocar `pop` para retirar elementos en orden LIFO.
6. Consultar `isEmpty` para reconocer el estado vacío, especialmente antes de una extracción cuando el llamador quiera evitar la excepción.

La política de excepción protege la API ante el uso incorrecto y evita confundir un entero válido —incluido `0`— con una señal de “no hay elemento”.

## 6. Complejidad y recursos

| Operación | Tiempo | Espacio adicional |
|---|---:|---:|
| `push` | O(1) | O(1) por nodo agregado |
| `pop` | O(1) | O(1) |
| `peek` | O(1) | O(1) |
| `isEmpty` | O(1) | O(1) |
| `contains` | O(n) | O(1) |
| `imprimir` | O(n) | O(1), sin contar el almacenamiento del flujo de salida |

La pila ocupa O(n) memoria para sus n nodos. Cada nodo usa espacio constante.

## 7. Correspondencia con pruebas

La implementación debe poder verificarse con estas secuencias:

1. **Vacía al iniciar:** comprobar `isEmpty()`, búsqueda negativa e impresión segura; `peek` y `pop` deben lanzar la excepción definida.
2. **Un elemento:** apilarlo, consultarlo, desapilarlo y comprobar que la pila vuelve a estar vacía.
3. **Orden LIFO:** apilar varios valores y confirmar que se desapilan en orden inverso.
4. **Búsqueda:** buscar valores en tope, interior y base, además de un valor ausente; confirmar que la estructura queda intacta.
5. **Valores límite prácticos:** usar negativos, cero y duplicados.
6. **Impresión no destructiva:** capturar la salida y verificar orden de tope a base, tanto con elementos como vacía; luego comprobar que los elementos siguen disponibles.

Los detalles de pruebas y resultados se documentarán en `pipeline/03_pruebas.md`.