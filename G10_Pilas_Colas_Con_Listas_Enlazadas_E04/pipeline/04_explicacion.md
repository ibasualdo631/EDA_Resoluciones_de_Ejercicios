# Explicación: cola enlazada genérica

## Idea principal

Una cola aplica la regla **FIFO** (*First In, First Out*): el primer elemento que entra es el primero que sale. En esta solución, `ColaEnlazada<T>` puede guardar distintos tipos de elementos sin cambiar esa regla. El parámetro genérico `T` informa al compilador qué tipo usa una instancia; los enlaces de los nodos determinan el orden.

Por ejemplo:

```java
ColaEnlazada<String> nombres = new ColaEnlazada<>();
ColaEnlazada<Cliente> clientes = new ColaEnlazada<>();
```

La primera cola solo recibe y devuelve `String`; la segunda solo recibe y devuelve `Cliente`. La estructura es la misma, pero cada instancia conserva su propio tipo y sus propios nodos.

## Qué representa cada clase

### `ColaEnlazada<T>`

Es la estructura que administra el inicio y el final de la cadena de nodos.

- `head` apunta al nodo del frente, es decir, el elemento más antiguo que todavía no se ha retirado.
- `tail` apunta al nodo final, donde se agrega el próximo elemento.
- `estaVacia()` detecta si no hay frente.
- `encolar(T valor)` agrega al final.
- `desencolar()` devuelve y retira el elemento del frente.
- `frente()` consulta el elemento del frente sin retirarlo.
- `contiene(T valor)` recorre los nodos y compara sus valores con `Objects.equals`, útil tanto para valores como para referencias nulas.
- `imprimir()` recorre la cadena desde el frente al final para mostrar los valores.

Los nombres `head` y `tail` son equivalentes a “frente” y “final”. Son variables de referencia: no contienen los elementos directamente, sino que apuntan a los nodos que los contienen.

### `Nodo<T>`

Cada nodo es una pieza de la cadena:

- `valor` contiene el elemento de tipo `T`.
- `siguiente` apunta al nodo que llegó después.

El nodo está anidado y es privado porque forma parte de la implementación interna. Quien usa la cola trabaja con operaciones como encolar y desencolar, sin manipular directamente los enlaces.

### `Cliente`

`Cliente` es una clase de ejemplo con un nombre. La cola no depende de esa propiedad, ni necesita conocer cómo está construido el objeto. Al guardar un `Cliente`, almacena su referencia y más adelante devuelve esa misma referencia.

## Cómo interactúan genéricos y referencias

En `Nodo<T>`, el campo `valor` tiene tipo `T`. En `ColaEnlazada<T>`, tanto `head` como `tail` apuntan a `Nodo<T>`. Esto mantiene el tipo coherente por toda la estructura:

```text
ColaEnlazada<Cliente>
       head
        |
        v
 Nodo<Cliente> -> Nodo<Cliente> -> null
       ^                              ^
     frente                          tail
```

En una cola de `String`, la forma de la cadena es igual, pero sus nodos son `Nodo<String>`. No hace falta crear una lógica distinta para cada tipo.

Además, la comprobación genérica evita errores antes de ejecutar el programa. Por ejemplo, `clientes.encolar("Ana")` no compila porque `"Ana"` es `String`, no `Cliente`. Al desencolar de `ColaEnlazada<Cliente>`, el resultado ya es `Cliente`: no se necesita convertirlo con un *cast*.

## Recorrido de las operaciones

### Encolar

1. Se crea un nuevo `Nodo<T>` con el valor recibido.
2. Si la cola está vacía, ese nodo es simultáneamente `head` y `tail`.
3. Si ya contiene nodos, el nodo actual de `tail` enlaza el nuevo nodo en `tail.siguiente`.
4. Se actualiza `tail` para que apunte al nodo recién agregado.

Ejemplo al encolar `"Ana"`, `"Luis"` y `"Mora"`:

```text
head                                  tail
 |                                     |
 v                                     v
[Ana] -> [Luis] -> [Mora] -> null
```

### Desencolar

1. Se verifica que la cola no esté vacía.
2. Se guarda el valor que contiene `head`.
3. `head` avanza al siguiente nodo.
4. Si no queda nodo al frente, también se asigna `null` a `tail`.
5. Se devuelve el valor guardado.

Después de retirar `"Ana"`:

```text
head                     tail
 |                        |
 v                        v
[Luis] -> [Mora] -> null
```

Cuando se retira el último elemento, `head` queda en `null` y también `tail`. Esta sincronización es importante: si el final conservara la referencia vieja, al volver a encolar se enlazaría el nuevo nodo a una cola que ya está vacía.

### Consultar el frente

`frente()` devuelve el valor de `head` pero no mueve la referencia ni altera el nodo. Por eso se puede consultar repetidamente sin cambiar el orden de las próximas extracciones.

## Por qué FIFO no depende del tipo

La cola no decide el orden leyendo el nombre de un `String`, comparando campos de `Cliente` ni preguntando la clase concreta del objeto. El orden se deriva únicamente de dos reglas estructurales:

1. todo elemento nuevo se conecta después del nodo final;
2. toda extracción comienza en el nodo del frente.

Por eso, tanto si la cola contiene nombres como si contiene objetos `Cliente`, el elemento que lleva más tiempo esperando es el primero que se retira. `T` restringe qué tipo puede almacenarse; no altera los punteros `head`, `tail` y `siguiente`.

## Casos importantes

- **Cola vacía:** `head == null` y `tail == null`.
- **Un elemento:** `head` y `tail` apuntan al mismo nodo y su enlace `siguiente` es `null`.
- **Varios elementos:** `head` apunta al más antiguo, cada `siguiente` avanza hacia elementos más recientes y `tail.siguiente` es `null`.
- **Retirar el único elemento:** ambas referencias vuelven a `null`.
- **Reutilizar después de vaciar:** el próximo elemento insertado vuelve a ser tanto frente como final.
- **Dos colas distintas:** cada instancia tiene sus propios extremos; modificar una no cambia la cadena de la otra.
- **Desencolar o consultar cuando está vacía:** `asegurarNoVacia` lanza `IllegalStateException` antes de acceder a `head`.

## Costos de las operaciones

`encolar`, `desencolar`, `frente` y `estaVacia` realizan una cantidad constante de trabajo: **O(1)**. La búsqueda de `contiene` y la impresión recorren los nodos: **O(n)**, donde `n` es la cantidad de elementos.

## Cómo leer las pruebas

En `Main.java`:

- `probarColaDeNombres()` comprueba la secuencia `Ana`, `Luis`, `Mora` y que consultar el frente no retire el elemento.
- `probarColaDeClientes()` comprueba que salen las mismas instancias de `Cliente` en el orden insertado, comparando las referencias con `==`.
- `probarAislamientoEntreColas()` intercala actividad en una cola de nombres y otra de clientes.
- `probarVaciadoYReutilizacion()` comprueba las excepciones de cola vacía, el retiro del único elemento y una inserción posterior.

Estas pruebas reflejan la separación central del diseño: los valores y tipos pueden variar, mientras la cadena de referencias conserva la misma semántica FIFO.
