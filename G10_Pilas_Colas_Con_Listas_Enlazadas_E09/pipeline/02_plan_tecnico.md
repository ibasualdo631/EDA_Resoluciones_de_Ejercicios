# Plan técnico — Cola de impresión

## 1. Diseño general

Implementar una cola FIFO en memoria usando una **lista simplemente enlazada**. La cola conserva una referencia al primer nodo pendiente (`frente`) y otra al último (`final`), de modo que agregar al final y retirar del frente no requiera recorrer la lista.

El diseño es independiente de un lenguaje concreto, ya que el enunciado no fija uno. La operación de impresión se modelará como el procesamiento del trabajo retirado (por ejemplo, mostrar sus datos); no se presupone una conexión con una impresora física.

## 2. Estructuras y responsabilidades

### `TrabajoImpresion`

Representa los datos inmutables de un trabajo:

- `archivo: cadena`
- `paginas: entero`
- `usuario: cadena`

Validar antes de construirlo: archivo y usuario no vacíos y páginas mayores que cero.

### `NodoTrabajo`

Elemento interno de la lista:

- `trabajo: TrabajoImpresion`
- `siguiente: NodoTrabajo o nulo`

### `ColaImpresion`

Administra los nodos y expone las operaciones del sistema:

- `frente: NodoTrabajo o nulo`
- `final: NodoTrabajo o nulo`
- Opcional: `cantidad: entero`, útil para informar si la cola está vacía y llevar su tamaño.

Invariante: si la cola está vacía, `frente` y `final` son nulos; si contiene elementos, `frente` apunta al más antiguo, `final` al más reciente y `final.siguiente` es nulo.

## 3. Operaciones y firmas conceptuales

Las firmas son pseudotipos; se adaptarán a las convenciones del lenguaje elegido.

| Operación | Firma conceptual | Responsabilidad |
| --- | --- | --- |
| Agregar | `agregar(archivo: cadena, paginas: entero, usuario: cadena): resultado` | Validar y enlazar un nuevo nodo después del actual `final`. |
| Imprimir próximo | `imprimirProximo(): TrabajoImpresion o vacío` | Retirar el nodo de `frente` y devolver/procesar su trabajo; informar cola vacía si no hay uno. |
| Consultar próximo archivo | `consultarProximoArchivo(): cadena o vacío` | Leer el archivo de `frente` sin cambiar enlaces. |
| Listar pendientes | `listarPendientes(): lista de TrabajoImpresion` | Recorrer desde `frente` hasta el final y devolver los trabajos en orden, sin retirarlos. |
| Consultar si está vacía | `estaVacia(): booleano` | Determinar si `frente` es nulo. |

La interfaz de usuario puede traducir los resultados vacíos o inválidos en mensajes adecuados. La estructura de datos no debe inventar un trabajo ni modificar la cola cuando una operación no puede completarse.

## 4. Algoritmos

### Agregar trabajo

1. Validar los datos.
2. Crear un nodo cuyo enlace `siguiente` sea nulo.
3. Si está vacía, asignar el nodo tanto a `frente` como a `final`.
4. En caso contrario, enlazar `final.siguiente` al nodo nuevo y actualizar `final`.
5. Incrementar `cantidad`, si se mantiene.

### Imprimir el próximo trabajo

1. Si está vacía, informar que no hay trabajos y terminar sin modificar el estado.
2. Guardar el trabajo ubicado en `frente`.
3. Avanzar `frente` a `frente.siguiente`.
4. Si ahora `frente` es nulo, asignar también nulo a `final`.
5. Decrementar `cantidad`, si se mantiene.
6. Procesar e informar el trabajo guardado.

### Consultar el próximo archivo

Si está vacía, devolver el resultado vacío para que se informe que no hay próximo archivo. En caso contrario, leer `frente.trabajo.archivo`. No se deben modificar referencias ni tamaño.

### Listar pendientes

Recorrer con un cursor temporal desde `frente`, agregando cada trabajo a la salida hasta alcanzar el nulo. No modificar los enlaces de la cola.

## 5. Por qué el spooler debe regirse por FIFO

En este ejercicio, FIFO es una **regla funcional obligatoria**, no una optimización opcional: la impresora atiende primero el trabajo que lleva más tiempo esperando. Así se conserva el orden de llegada, se evita que nuevos envíos posterguen indefinidamente a los anteriores y el usuario puede prever qué trabajo sigue. Si el sistema reordenara trabajos por usuario, tamaño u otro criterio, dejaría de cumplir el comportamiento especificado y podría perjudicar a quienes ya estaban esperando. Por eso, agregar siempre al final e imprimir siempre desde el frente son invariantes del diseño.

## 6. Complejidad

- Agregar: `O(1)` por las referencias a frente y final.
- Imprimir próximo: `O(1)`.
- Consultar próximo archivo: `O(1)`.
- Listar pendientes: `O(n)` para `n` trabajos.
- Espacio: `O(n)`, un nodo por trabajo pendiente.

## 7. Secuencia de ejecución prevista

1. Inicializar `frente` y `final` como nulos y `cantidad` como cero, si se usa.
2. Recibir la selección del usuario.
3. Para agregar, recopilar y validar los tres datos; luego insertar al final.
4. Para imprimir, retirar y procesar el trabajo del frente.
5. Para consultar el próximo archivo o listar, leer la cola sin alterarla.
6. Repetir las operaciones mientras el sistema esté activo.

## 8. Pruebas técnicas derivadas

- Insertar el primer elemento: `frente` y `final` deben apuntar al mismo nodo.
- Insertar varios trabajos: al retirarlos, deben salir en el mismo orden de inserción.
- Retirar el único elemento: ambos extremos deben quedar nulos.
- Consultar y listar: el tamaño y el orden de la cola deben permanecer iguales.
- Ejecutar impresión, consulta y listado con la cola vacía: deben producir el resultado vacío/mensaje correspondiente sin alterar la estructura.
- Rechazar archivo o usuario vacíos y páginas menores o iguales a cero, sin insertar nodo alguno.