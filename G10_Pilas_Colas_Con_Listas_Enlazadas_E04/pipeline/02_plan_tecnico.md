# Plan técnico: cola genérica

## Objetivo

Generalizar la cola enlazada de Java para almacenar valores de cualquier tipo de referencia usando un parámetro de tipo `T`, sin alterar sus operaciones ni el orden FIFO. La misma implementación debe servir para colas distintas, por ejemplo `Cola<String>` y `Cola<Cliente>`.

## Diseño propuesto

### Organización y tipos

- **`Cola<T>`**: abstracción pública que administra los extremos de la secuencia y expone las operaciones actuales de la cola, tipadas con `T`.
- **`Nodo<T>`**: nodo enlazado que almacena un elemento `T` y una referencia al siguiente `Nodo<T>`. Debe permanecer como detalle interno de implementación (clase privada anidada o clase de visibilidad restringida según la organización existente).
- **`Cliente`**: tipo de dominio usado por la demostración y las pruebas. La cola solo almacena y devuelve las referencias a estos objetos; no necesita conocer sus atributos ni implementar comparación.

No se encontró código fuente Java para determinar los nombres de clase, campos u operaciones actuales. Por tanto, se deben conservar los nombres y convenciones del ejercicio si difieren de los ejemplos siguientes, manteniendo sus contratos.

### Parámetro genérico y datos

La clase de la cola recibe un parámetro de tipo, conceptualmente `Cola<T>`. El nodo también se parametriza con el mismo `T`, así los elementos no se degradan a `Object` en la estructura ni requieren conversiones explícitas al recuperarse.

Referencias que mantienen la estructura:

- `frente`: nodo del elemento más antiguo pendiente.
- `final`: nodo del elemento agregado más recientemente.
- `siguiente`: referencia que enlaza un nodo con el que ingresó después.

Los dos extremos pertenecen a cada instancia, no son estáticos ni compartidos. En una cola vacía ambos deben estar nulos; en una cola no vacía `frente` y `final` deben referirse a nodos de su propia cadena, y el siguiente de `final` debe ser nulo.

## Operaciones y firmas conceptuales

Se deben adaptar las operaciones ya existentes para que acepten y retornen `T`. Los nombres siguientes son ilustrativos y no obligan a renombrar la API:

| Operación | Firma conceptual | Responsabilidad |
|---|---|---|
| Insertar | `void encolar(T elemento)` | Agrega el valor al final de la cadena. |
| Retirar | `T desencolar()` | Devuelve y desvincula el valor ubicado en el frente. |
| Consultar frente (si ya existe) | `T frente()` | Devuelve el primer valor sin retirar ni modificar enlaces. |
| Consultar vacío (si ya existe) | `boolean estaVacia()` | Indica si el frente no tiene nodo. |

No se deben agregar operaciones nuevas solo por este cambio. La consulta de frente y las comprobaciones de vacío se implementan únicamente si forman parte de la API actual o son necesarias para sus métodos existentes.

## Abstracción que mantiene FIFO

FIFO depende exclusivamente de qué extremo se usa para insertar y cuál para retirar, no del tipo del elemento:

1. **Encolar** crea un nodo con el elemento `T`. Si la cola está vacía, el nuevo nodo pasa a ser tanto `frente` como `final`. Si ya hay elementos, se enlaza desde `final.siguiente` y luego se actualiza `final`.
2. **Desencolar** toma el elemento de `frente`, avanza `frente` a `frente.siguiente` y devuelve el elemento guardado. Si al avanzar la cola queda vacía, también debe ponerse `final` en nulo.
3. **Consultar frente**, cuando exista, lee el dato de `frente` sin alterar la cadena.

La abstracción conserva el orden porque todo elemento nuevo entra detrás del último y toda extracción sale desde el primero. `T` permite guardar y recuperar clases distintas, pero no interviene en los enlaces ni en la política de orden. No se debe ordenar, comparar, convertir ni ramificar según la clase del objeto.

## Invariantes

- Cola vacía: `frente == null` y `final == null`.
- Cola con al menos un nodo: `frente != null` y `final != null`.
- El siguiente enlace de `final` es siempre nulo.
- Al retirar el único elemento, ambos extremos vuelven a nulo.
- Encolar conserva los elementos pendientes y su orden relativo.
- Desencolar conserva todos los elementos salvo el que estaba al frente.
- La lista de enlaces y los extremos de una cola no se comparten con otras instancias.
- El dato de cada nodo y el elemento de cada operación pública usan el mismo parámetro `T`.

## Secuencia de implementación

1. Revisar la implementación existente y registrar nombres de clases, métodos y comportamiento para los casos de cola vacía y valores `null`; preservarlos.
2. Parametrizar la clase de cola y el nodo con `<T>`. Cambiar los datos que actualmente usen un tipo concreto o `Object` para que usen `T`.
3. Tipar con `T` los parámetros y valores de retorno de las operaciones existentes, sin alterar su semántica.
4. Verificar los casos de inserción en cola vacía, inserción con elementos, retirada hasta vaciar y reinserción, en especial la sincronización de `frente` y `final`.
5. Actualizar el ejemplo/cliente de uso para instanciar una cola de nombres y una cola de `Cliente`.
6. Ejecutar las verificaciones de tipos y comportamiento definidas en [01_analisis.md](./01_analisis.md) y registrar resultados en [03_pruebas.md](./03_pruebas.md).

## Pruebas técnicas previstas

- Compilar instancias `Cola<String>` y `Cola<Cliente>` y asignar los resultados de extracción directamente a las variables tipadas correspondientes, sin casts.
- Confirmar que intentar insertar un `Cliente` en `Cola<String>` o un `String` en `Cola<Cliente>` produce un error de compilación en ejemplos negativos independientes.
- En ambas colas, insertar tres elementos y verificar que se recuperan estrictamente en orden de inserción.
- Para `Cliente`, comprobar las mismas instancias en la secuencia de salida; no requerir que el tipo implemente `Comparable` ni que la cola inspeccione sus campos.
- Validar consulta del frente sin mutación, solo si la operación está presente.
- Validar vacío, elemento único, vaciado total, nueva inserción tras vaciar e independencia entre dos instancias.
- Confirmar que las respuestas/errores para operaciones sobre cola vacía y `null` siguen la implementación previa; no introducir una política distinta.

## Nota para la implementación

El archivo Java que implemente la cola debe comenzar con un comentario breve indicando que FIFO se mantiene insertando al final y retirando del frente, mientras que el parámetro genérico solo define el tipo almacenado y no modifica el orden.
