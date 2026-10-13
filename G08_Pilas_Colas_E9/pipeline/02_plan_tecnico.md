# Plan técnico — Demostración de falso desbordamiento en cola lineal

## Objetivo

Diseñar un programa corto que opere una cola lineal de capacidad fija y muestre que puede rechazarse una inserción cuando `rear` alcanza el último índice, aunque queden celdas desocupadas al comienzo del arreglo.

## Diseño de la estructura

- **Almacenamiento:** arreglo de cinco posiciones, inicializado con un marcador de vacío (`_`).
- **Índices:** `front` señala el primer elemento y `rear` el último.
- **Estado inicial:** `front = 0`, `rear = -1`; este par indica que la cola está vacía.
- **Capacidad:** constante `CAPACIDAD = 5`.
- **Alcance:** cola lineal, sin circularidad ni corrimiento de elementos.

## Operaciones

### `encolar(cola, valor) -> booleano`

1. Si `rear == CAPACIDAD - 1`, rechazar la inserción y devolver `false`.
2. En otro caso, avanzar `rear`, guardar `valor` en `arreglo[rear]` y devolver `true`.
3. No buscar ni reutilizar las celdas anteriores a `front`.

Al rechazarse una inserción con celdas vacías todavía presentes, informar que es un **falso desbordamiento** de la cola lineal, no que el arreglo esté físicamente lleno.

### `desencolar(cola) -> elemento`

1. Si `front > rear`, informar que no hay elementos para extraer.
2. Guardar el elemento en `arreglo[front]`, limpiar esa celda con `_` y avanzar `front`.
3. Devolver el elemento extraído.

El programa demostrativo no vacía por completo la cola, por lo que no necesita reinicializar los índices al quedar vacía.

### `mostrarEstado(cola, operacion) -> nada`

Después de cada operación, imprimir:

- nombre de la operación y su resultado;
- todos los índices del arreglo y sus valores, incluidos los vacíos;
- valores de `front` y `rear`, indicando sus posiciones;
- cantidad de celdas vacías y, para el caso final, si están antes de `front`.

Para evitar que el marcador vacío se confunda con un dato, usar `_` exclusivamente para las celdas libres.

## Secuencia controlada

Ejecutar, en este orden, cada operación y mostrar el estado inmediatamente después:

1. `encolar(A)`
2. `encolar(B)`
3. `encolar(C)`
4. `encolar(D)`
5. `desencolar()` — extrae `A`.
6. `desencolar()` — extrae `B`.
7. `encolar(E)` — se agrega en el índice `4`.
8. `encolar(F)` — se rechaza porque `rear` ya está en el índice `4`.

## Estados clave esperados

Después de encolar `A`, `B`, `C` y `D`:

```text
Índice:   0  1  2  3  4
Arreglo:  A  B  C  D  _
front = 0 (índice 0), rear = 3 (índice 3)
```

Después de extraer `A` y `B`:

```text
Índice:   0  1  2  3  4
Arreglo:  _  _  C  D  _
front = 2 (índice 2), rear = 3 (índice 3)
```

Después de encolar `E`:

```text
Índice:   0  1  2  3  4
Arreglo:  _  _  C  D  E
front = 2 (índice 2), rear = 4 (índice 4)
```

El intento de encolar `F` debe dejar el estado sin cambios y explicar:

```text
Inserción rechazada: rear llegó al último índice (4).
Hay 2 celdas vacías (índices 0 y 1), pero están antes de front.
La cola lineal no las reutiliza: falso desbordamiento, no arreglo lleno.
```

## Organización sugerida

Mantener la representación y las operaciones de cola separadas de la secuencia demostrativa:

1. Definir una estructura `ColaLineal` con `arreglo`, `front` y `rear`.
2. Implementar `encolar`, `desencolar` y `mostrarEstado`.
3. En la rutina principal, crear la cola y ejecutar la secuencia fija anterior.

No se prescribe un lenguaje; las firmas se expresan de forma conceptual para adaptarlas al lenguaje utilizado en el ejercicio.

## Criterios de aceptación

- Las ocho operaciones se ejecutan en el orden especificado.
- Se imprime el arreglo completo y ambos índices tras cada operación.
- Las extracciones liberan los índices `0` y `1` y avanzan `front`.
- `E` se inserta en el índice `4`.
- `F` se rechaza sin alterar el arreglo ni los índices.
- El mensaje final distingue celdas libres a la izquierda de `front` de un arreglo realmente lleno.
- No se implementa una cola circular ni se compactan los elementos.
