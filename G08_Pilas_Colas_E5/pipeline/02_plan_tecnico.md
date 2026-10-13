# Plan técnico — Ejercicio 5: Cola de impresión

## Objetivo

Modelar los trabajos pendientes de una impresora mediante una cola FIFO (*First In, First Out*). Cada trabajo se representa con un objeto `Documento`; al procesar la cola se entrega primero el documento que lleva más tiempo esperando.

La cola modela la selección del próximo trabajo, no la comunicación con una impresora física. No se agregan prioridades, cancelación, concurrencia ni persistencia.

## Modelo de datos

### `Documento`

Objeto que contiene los datos de un trabajo de impresión:

- `id`: identificador del trabajo, útil para distinguir documentos con el mismo nombre.
- `nombre`: nombre o referencia del documento.
- `cantidadPaginas`: cantidad de páginas solicitadas.

Se recomienda mantener estos datos inmutables después de crear el documento, para evitar que un trabajo cambie mientras espera. Validar que el documento exista, que `nombre` no esté vacío y que `cantidadPaginas` sea positiva. Si el enunciado correcto define otros campos o no requiere alguno de estos, ajustar el modelo antes de implementar; el material disponible todavía no confirma esos detalles.

### `ColaImpresion`

Encapsula los documentos pendientes y ofrece las operaciones descritas abajo. Como implementación inicial se propone una lista enlazada con referencias al primer y al último nodo, más un contador:

- `frente`: primer trabajo pendiente.
- `final`: último trabajo pendiente.
- `cantidad`: número de documentos encolados.

Esta representación permite insertar y retirar en tiempo O(1), sin desplazar los documentos restantes. Una estructura de arreglo también es válida si la consigna la exige; en ese caso debe conservarse FIFO y evitar desplazamientos innecesarios. La capacidad fija o dinámica queda pendiente de confirmar con el enunciado.

## Contratos públicos

Las firmas siguientes son conceptuales y deben expresarse con los tipos y convenciones del lenguaje del proyecto.

### `encolar(documento: Documento) -> void`

- **Precondición:** `documento` es válido según las reglas del modelo.
- **Efecto:** agrega el documento al final de la cola sin modificar ni reordenar los trabajos existentes.
- **Postcondición:** la cantidad aumenta en uno; el documento recién agregado será atendido después de todos los que ya estaban pendientes.
- **Error:** un argumento inválido debe rechazarse explícitamente conforme al mecanismo idiomático del lenguaje (por ejemplo, excepción o resultado de error); no se debe ignorar ni insertar un valor inválido.
- **Complejidad esperada:** O(1).

### `procesarSiguiente() -> Resultado<Documento>`

- **Precondición:** ninguna; la cola puede estar vacía.
- **Efecto si hay trabajos:** retira y devuelve el documento del frente, que es el más antiguo. “Procesar” en este ejercicio significa seleccionar/retirar el siguiente trabajo, no ejecutar una impresión física.
- **Postcondición si hay trabajos:** la cantidad disminuye en uno y el frente pasa al próximo documento; si era el único, frente y final quedan vacíos.
- **Resultado si está vacía:** informa explícitamente que no hay un documento para procesar y no modifica el estado. En lenguajes con tipos opcionales puede expresarse como `Documento opcional`; en otros, usar un resultado discriminado o un error documentado. No devolver un documento ficticio ni depender de un valor centinela ambiguo.
- **Complejidad esperada:** O(1).

### Consultas auxiliares

- `estaVacia() -> boolean`: devuelve si no hay trabajos pendientes, en O(1).
- `verFrente() -> Resultado<Documento>` (solo si se requiere consultar sin retirar): devuelve el documento más antiguo sin alterar la cola; en vacío informa explícitamente que no hay elemento.
- `cantidad() -> entero` (solo si la interfaz solicitada la necesita): devuelve el número de trabajos pendientes.

No exponer los nodos ni permitir que código externo modifique directamente el estado interno.

## Secuencia de ejecución

1. Crear una `ColaImpresion` vacía.
2. Crear cada `Documento` con sus datos validados.
3. Invocar `encolar` al recibir un trabajo.
4. Mientras haya trabajos, invocar `procesarSiguiente` y consumir el documento devuelto.
5. Cuando la cola esté vacía, informar que no quedan trabajos; no intentar acceder al frente.

Para la lista enlazada, al encolar en una cola vacía el nuevo nodo será simultáneamente frente y final. Al retirar el último elemento se deben restablecer ambas referencias a vacío, manteniendo coherente el contador.

## Pruebas previstas

1. **FIFO:** encolar A, B y C; procesar tres veces y comprobar el orden A, B, C.
2. **Procesamiento en vacío:** procesar una cola vacía; comprobar el resultado explícito de vacío y que el estado siga vacío.
3. **Vaciar y reutilizar:** encolar A, procesarlo, verificar cola vacía, luego encolar B y comprobar que B se procesa correctamente.
4. **Validación de documento:** probar documento nulo/inexistente, nombre vacío y cantidad de páginas no positiva, según los tipos del lenguaje; verificar rechazo sin alterar la cola.
5. **Consulta del frente (si se implementa):** comprobar que consultar no retira ni cambia el orden.

## Decisiones pendientes de contrastar

El único material de consigna detectado hasta ahora es una imagen sobre una pila genérica `Pila<T>`, no sobre una cola de impresión. Antes de codificar, confirmar el enunciado correcto y las convenciones del proyecto para fijar:

- lenguaje, nombres de clases y firmas concretas;
- campos requeridos de `Documento`;
- representación exigida (arreglo, lista enlazada u otra) y capacidad;
- forma esperada de representar errores/cola vacía;
- si se requieren consultas, impresión por consola o únicamente la estructura de datos.

Hasta confirmar esos puntos, las firmas y campos de este documento son una propuesta técnica explícita, no requisitos atribuidos al enunciado ausente.