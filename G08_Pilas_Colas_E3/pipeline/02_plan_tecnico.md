# Plan técnico — `ColaEnteros`

## 1. Diseño

Implementar una cola FIFO (First In, First Out) de capacidad fija mediante un arreglo `int[]`. La clase administra el almacenamiento y las operaciones de inserción/extracción; no usa colecciones ni desplaza elementos.

La cola es **lineal, no circular**: `front` y `rear` avanzan hacia índices mayores. El arreglo vuelve a estar disponible desde el índice cero cuando se desencola el último elemento y la cola queda vacía.

## 2. Estado y significado de los índices

Campos privados:

- `int[] elementos`: almacenamiento de capacidad fija.
- `int front`: índice del próximo elemento que se desencolará.
- `int rear`: índice del último elemento encolado.

Invariante:

- Vacía: `front == 0` y `rear == -1`.
- No vacía: `0 <= front <= rear < elementos.length`.
- `elementos[front..rear]` representa los valores pendientes, en orden FIFO.

El constructor recibe la capacidad, la rechaza si es menor o igual que cero (`IllegalArgumentException`), crea el arreglo e inicializa `front = 0`, `rear = -1`.

## 3. API propuesta

| Firma | Responsabilidad |
|---|---|
| `ColaEnteros(int capacidad)` | Crear una cola vacía de capacidad fija positiva. |
| `void encolar(int valor)` | Insertar `valor` al final. Si no hay espacio, lanzar `IllegalStateException`. |
| `int desencolar()` | Retirar y devolver el elemento del frente. Si está vacía, lanzar `IllegalStateException`. |
| `boolean estaVacia()` | Informar si no hay elementos pendientes. |
| `boolean estaLlena()` | Informar si `rear` llegó al último índice del arreglo. |

## 4. Transiciones de estado

### Encolar

1. Consultar `estaLlena()`, definida como `rear == elementos.length - 1`.
2. Si está llena, lanzar `IllegalStateException` sin alterar el estado.
3. Incrementar `rear`.
4. Guardar `valor` en `elementos[rear]`.

Solo `rear` avanza durante una inserción. Si la cola estaba vacía, el incremento lo mueve de `-1` a `0`; `front` ya es `0`.

### Desencolar

1. Consultar `estaVacia()`, definida como `rear == -1` (equivalente al estado reiniciado).
2. Si está vacía, lanzar `IllegalStateException` sin alterar el estado.
3. Guardar `elementos[front]` en una variable local.
4. Incrementar `front`.
5. Si ahora `front > rear`, se retiró el último elemento: reiniciar `front = 0` y `rear = -1`.
6. Devolver el valor guardado.

Solo `front` avanza durante una extracción mientras queden elementos. `rear` no retrocede por cada desencolado; ambos índices se reinician juntos al vaciarse.

## 5. Condiciones de borde

- **Capacidad inválida:** capacidad `<= 0`; el constructor falla con `IllegalArgumentException`.
- **Desencolar vacía:** lanzar `IllegalStateException`; los índices permanecen `front = 0`, `rear = -1`.
- **Encolar llena:** lanzar `IllegalStateException`; el arreglo y los índices no cambian.
- **Extraer el último elemento:** después de devolverlo, restablecer el estado vacío a `front = 0`, `rear = -1`.
- **Espacios libres antes de `front`:** no se reutilizan en esta variante. Si `rear == elementos.length - 1`, la cola está llena aunque existan huecos a la izquierda. Se recupera la capacidad completa cuando la cola se vacía y se reinicia.
- **Capacidad uno:** permite una inserción; al extraerla, la cola vuelve al estado inicial y puede reutilizarse.

## 6. Complejidad

`encolar`, `desencolar`, `estaVacia` y `estaLlena` realizan una cantidad fija de operaciones, por lo que cada una cuesta **O(1)** en tiempo. El almacenamiento ocupa **O(n)** espacio para una capacidad `n`. No hay recorridos ni corrimientos del arreglo.

## 7. Secuencia de prueba manual

Con capacidad `3`:

1. Estado inicial: `front = 0`, `rear = -1`.
2. Encolar `10`, `20`, `30`: `front = 0`, `rear = 2`; queda llena.
3. Desencolar devuelve `10` y luego `20`: `front = 2`, `rear = 2`.
4. Aunque hay dos posiciones libres antes de `front`, encolar falla porque esta cola lineal no circular tiene `rear` al final.
5. Desencolar devuelve `30`: estado reiniciado `front = 0`, `rear = -1`.
6. Encolar de nuevo confirma que el arreglo puede reutilizarse después de vaciarlo.