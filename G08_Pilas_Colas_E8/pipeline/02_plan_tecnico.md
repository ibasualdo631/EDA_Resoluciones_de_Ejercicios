# Plan técnico — Cola de impresión

## Objetivo

Modelar documentos pendientes de impresión y definir operaciones que preserven estrictamente el orden FIFO indicado en [01_analisis.md](./01_analisis.md). No se simula la impresión física ni se usa la cantidad de páginas para cambiar prioridades.

## Organización

El directorio no contiene código fuente ni manifiestos que indiquen un lenguaje o una estructura de proyecto. Por eso, este plan define contratos independientes del lenguaje. Al implementar, ubicar las definiciones en los módulos existentes si aparecen; si no, mantener el modelo `Documento` y la cola de impresión separados del punto de entrada o interfaz.

## Modelo de datos

### `Documento`

Representa un trabajo individual:

- `nombre: texto`: nombre identificador para mostrar.
- `cantidadPaginas: entero`: número de páginas del documento.

Invariantes: el nombre no está vacío y `cantidadPaginas > 0`. El documento no almacena prioridad; su posición depende exclusivamente del momento en que se encola.

### `ColaImpresion`

Usa una cola FIFO, respaldada por una cola estándar del lenguaje o una estructura equivalente. La cola conserva referencias/valores `Documento` en orden de llegada:

- insertar siempre al final;
- retirar siempre desde el frente;
- no ordenar ni inspeccionar los trabajos para priorizarlos.

Una cola estándar es preferible a implementar nodos manualmente si el ejercicio no exige implementar la estructura desde cero. Las operaciones de encolar y desencolar deben ser O(1) según la implementación elegida.

## Contratos de operaciones

### `encolar(documento: Documento) -> resultado`

- **Precondición:** `documento` existe y satisface las invariantes del modelo.
- **Efecto:** agrega el documento al final sin alterar los ya pendientes.
- **Postcondición:** el documento agregado queda detrás de todos los documentos que estaban en la cola; no se pierde ni se reordena ningún trabajo.
- **Complejidad esperada:** O(1).

### `procesarSiguiente() -> Documento | ColaVacia`

- **Si hay trabajos:** retira y devuelve exactamente el documento del frente. La acción representa el procesamiento/impresión lógica; no requiere simular página por página.
- **Si está vacía:** no modifica la cola y devuelve el resultado explícito `ColaVacia`, que la interfaz o el llamador comunica con un mensaje claro, por ejemplo: “No hay documentos pendientes para imprimir”.
- **Postcondición:** los documentos restantes conservan su orden relativo.
- **Complejidad esperada:** O(1).

La unión `Documento | ColaVacia` es notación de contrato, no una exigencia de sintaxis. En el lenguaje elegido puede expresarse mediante un tipo resultado, una opción/valor opcional o un retorno booleano acompañado del documento de salida. Evitar un valor por defecto que confunda una cola vacía con un documento válido.

### `estaVacia() -> booleano` (opcional)

Devuelve si no hay trabajos pendientes y no modifica la cola. Solo agregar esta operación si la interfaz o los contratos la necesitan; `procesarSiguiente` debe seguir manejando por sí mismo el caso vacío.

## Secuencia de uso

1. Crear la cola vacía.
2. Construir cada `Documento` con nombre y páginas, y encolarlo.
3. Invocar `procesarSiguiente` hasta obtener `ColaVacia` o hasta que el llamador decida detenerse.
4. Mostrar cada documento devuelto como procesado; no usar las páginas para alterar el orden.

Ejemplo: al encolar `Informe (3)`, `Apuntes (1)` y `TP (5)`, las llamadas sucesivas a `procesarSiguiente` devuelven `Informe`, `Apuntes`, `TP`; la siguiente devuelve `ColaVacia`.

## Criterios de aceptación

- Un documento mantiene sus dos atributos: nombre y cantidad de páginas.
- Los documentos se procesan en el mismo orden en que se encolaron, incluso si tienen distinta cantidad de páginas.
- Procesar una cola vacía produce el resultado/mensaje explícito y no cambia el estado.
- Cada procesamiento exitoso retira un único documento y la cola queda lista para la siguiente operación.