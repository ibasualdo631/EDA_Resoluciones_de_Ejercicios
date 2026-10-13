# Plan técnico — `ColaEnteros`

## 1. Arquitectura y representación

Implementar una única clase `ColaEnteros` con arreglo interno de enteros y capacidad fija suministrada al constructor. La estructura es una **cola lineal**, no circular: conserva el orden FIFO y no desplaza elementos al desencolar.

Estado privado:

- `elementos`: arreglo de enteros de longitud `capacidad`.
- `capacidad`: cantidad de posiciones del arreglo.
- `frontIndex`: índice del elemento que se desencolará a continuación; corresponde al índice conceptual `front`.
- `rearIndex`: índice del último elemento encolado; corresponde al índice conceptual `rear`.
- `cantidad`: número actual de elementos. Se mantiene al insertar/eliminar para que `size()` e `isEmpty()` sean O(1), sin recorrer el arreglo.

Invariantes:

- La capacidad es estrictamente positiva.
- `cantidad` está entre `0` y `capacidad`, inclusive.
- Cola vacía: `cantidad == 0`, `frontIndex == 0`, `rearIndex == -1`.
- Cola no vacía: `0 <= frontIndex <= rearIndex < capacidad` y `cantidad == rearIndex - frontIndex + 1`.
- Los elementos activos ocupan el intervalo contiguo `[frontIndex, rearIndex]`.

Se usa `-1` como centinela de `rearIndex` exclusivamente en el estado vacío; no es un valor de elemento. El nombre `frontIndex` diferencia el índice interno del método público `front()`.

## 2. Gestión de índices

### Inicialización

El constructor valida que `capacidad > 0`, crea el arreglo y establece `frontIndex = 0`, `rearIndex = -1`, `cantidad = 0`. Una capacidad no válida produce el error idiomático del lenguaje (por ejemplo, argumento ilegal).

### `enqueue(valor)`

1. Si `isFull()` es verdadero, informar desbordamiento sin modificar el estado.
2. Si la cola está vacía, asignar `frontIndex = 0` y `rearIndex = 0`.
3. Si ya contiene elementos, incrementar `rearIndex` en uno.
4. Guardar `valor` en `elementos[rearIndex]` e incrementar `cantidad`.

`frontIndex` permanece estable al agregar mientras la cola ya tenga elementos. El caso vacío inicia una nueva secuencia en la primera posición disponible.

### `dequeue()`

1. Si `isEmpty()` es verdadero, informar subdesbordamiento sin modificar el estado.
2. Guardar y devolver `elementos[frontIndex]`.
3. Decrementar `cantidad`.
4. Si la cantidad quedó en cero, normalizar a `frontIndex = 0` y `rearIndex = -1`, de modo que una futura inserción empiece otra vez al principio.
5. Si quedan elementos, incrementar `frontIndex` en uno. `rearIndex` no cambia.

### `front()`

Si está vacía, informar subdesbordamiento. En otro caso, devolver `elementos[frontIndex]` sin modificar arreglo, índices ni cantidad.

## 3. Métodos y complejidad

| Método | Comportamiento / retorno | Complejidad |
|---|---|---|
| Constructor `(capacidad)` | Valida y asigna el arreglo de longitud `capacidad` | O(capacidad) por la inicialización del arreglo |
| `enqueue(entero)` | Añade al final; no devuelve valor. Error si está llena | O(1) |
| `dequeue()` | Devuelve y elimina el elemento del frente. Error si está vacía | O(1) |
| `front()` | Devuelve el elemento del frente sin eliminarlo. Error si está vacía | O(1) |
| `isEmpty()` | Devuelve `cantidad == 0` | O(1) |
| `isFull()` | Informa si no se puede agregar otro elemento según los límites lineales | O(1) |
| `size()` | Devuelve `cantidad` | O(1) |

El uso de memoria de la instancia es O(capacidad); cada operación indicada toma O(1) tiempo y no recorre ni desplaza los elementos.

## 4. Condiciones de borde y definición de llena

- **Capacidad no positiva:** rechazarla en el constructor.
- **Desencolar o consultar frente estando vacía:** informar el error según el lenguaje; no devolver un entero centinela.
- **Encolar estando llena:** informar el error y conservar íntegro el estado.
- **Llenado físico:** la cola está llena si no queda una posición posterior disponible en el arreglo lineal. Operativamente:
  `cantidad == capacidad || (!isEmpty() && rearIndex == capacidad - 1)`.
  Con los invariantes y el estado normalizado aquí descritos, la segunda condición alcanza los casos en que el extremo final fue consumido; la primera deja explícita la capacidad máxima.
- **Espacio liberado antes de `frontIndex`:** no se reutiliza mientras queden elementos. Por ello puede haber posiciones libres al principio y, aun así, `isFull()` ser verdadero. Esto es una limitación deliberada de la cola lineal y no un error de conteo: `size()` sigue devolviendo el número real de elementos.
- **Eliminación del último elemento:** normalizar los índices permite reiniciar el uso desde la posición cero, sin desplazar datos.
- **FIFO:** siempre se devuelve el elemento de `frontIndex`; nunca se elimina desde `rearIndex`.

Ejemplo, con capacidad 4: se encolan `10, 20, 30`, se desencola `10` (índices `frontIndex=1`, `rearIndex=2`, tamaño 2), y se encola `40` en el índice 3. Ahora `rearIndex=3`, `size()` es 3 e `isFull()` es verdadero: hay una celda libre antes de `frontIndex`, pero la cola lineal no la reutiliza mientras queden elementos.

## 5. Secuencia de implementación

1. Crear el arreglo y validar la capacidad en el constructor.
2. Implementar `isEmpty`, `isFull` y `size` a partir del estado privado.
3. Implementar `front` con validación de vacío.
4. Implementar `enqueue` y `dequeue`, manteniendo los invariantes en transiciones vacía/no vacía.
5. Probar FIFO, cambios de índices, consultas no destructivas, tamaño, cola vacía/llena, errores de borde y reinserción después de vaciar.

## 6. Nota sobre la interpretación de la consigna

La imagen pide una cola simple basada en arreglo y operaciones constantes, pero no especifica que sea circular. Este diseño sigue la interpretación lineal planteada en `01_analisis.md`. Una cola circular también ofrecería O(1) y reutilizaría huecos, pero cambiaría la regla de actualización de índices y no forma parte de esta arquitectura.