# Plan técnico — `PilaEnteros`

## 1. Diseño general

Implementar una clase Java `PilaEnteros` que encapsule una pila LIFO de capacidad fija, respaldada por un arreglo de `int`. La clase mantiene su estado interno privado y expone únicamente el constructor y las operaciones requeridas: `push`, `pop`, `peek`, `isEmpty`, `isFull` y `size`.

## 2. Estado y representación

| Atributo | Tipo | Visibilidad | Propósito |
|---|---|---|---|
| `elementos` | `int[]` | `private` | Almacenamiento de capacidad fija. |
| `top` | `int` | `private` | Índice del elemento superior; inicia en `-1`. |

La capacidad se obtiene de `elementos.length`, evitando mantener una segunda variable que pudiera desincronizarse.

### Invariante de estado

- `top == -1` representa la pila vacía.
- Si la pila no está vacía, `elementos[top]` es el elemento del tope.
- `-1 <= top < elementos.length`.
- La cantidad de elementos es siempre `top + 1`.
- La capacidad es positiva y no cambia después de construir la pila.

## 3. API propuesta

```text
PilaEnteros(int capacidad)
void push(int valor)
int pop()
int peek()
boolean isEmpty()
boolean isFull()
int size()
```

Los nombres de métodos usan camelCase. No se agrega una operación para modificar la capacidad, porque el arreglo debe permanecer fijo.

## 4. Validaciones y comportamiento

### Constructor

`PilaEnteros(int capacidad)`:

1. Rechazar `capacidad <= 0` con `IllegalArgumentException`, indicando que la capacidad debe ser positiva.
2. Crear el arreglo de longitud `capacidad`.
3. Inicializar `top` en `-1`.

### `push(int valor)`

1. Si `isFull()` es verdadero (`top == elementos.length - 1`), lanzar `IllegalStateException` para señalar Overflow.
2. Solo después de validar, incrementar `top` y guardar `valor` en `elementos[top]`.
3. No devuelve valor.

La comprobación se realiza antes de cambiar `top`, por lo que un Overflow no altera el estado.

### `pop()`

1. Si `isEmpty()` es verdadero (`top == -1`), lanzar `NoSuchElementException` para señalar Underflow.
2. Guardar `elementos[top]` en una variable local.
3. Decrementar `top` y devolver el valor guardado.

### `peek()`

1. Si `isEmpty()` es verdadero, lanzar `NoSuchElementException`.
2. Devolver `elementos[top]` sin modificar `top`.

### Consultas

- `isEmpty()`: devolver `top == -1`.
- `isFull()`: devolver `top == elementos.length - 1`.
- `size()`: devolver `top + 1`.

## 5. Complejidad

| Operación | Tiempo | Espacio auxiliar |
|---|---:|---:|
| `push` | O(1) | O(1) |
| `pop` | O(1) | O(1) |
| `peek` | O(1) | O(1) |
| `isEmpty` | O(1) | O(1) |
| `isFull` | O(1) | O(1) |
| `size` | O(1) | O(1) |

La creación del arreglo reserva O(capacidad) espacio; esta inicialización no cambia la complejidad O(1) de las primitivas de la pila.

## 6. Secuencia de uso

1. Construir la pila con una capacidad positiva.
2. Insertar con `push`; cada inserción ocupa el índice siguiente.
3. Consultar el tope con `peek` o extraerlo con `pop`.
4. Usar `isEmpty`, `isFull` y `size` para consultar el estado.
5. Cuando se extrae un elemento, `top` retrocede, liberando esa posición para una futura inserción.

## 7. Pruebas previstas

- Al construir con capacidad positiva: `isEmpty()` verdadero, `isFull()` falso y `size() == 0`.
- Rechazar capacidad cero y negativa con `IllegalArgumentException`.
- Insertar y extraer un único valor; verificar el resultado y el retorno al estado vacío.
- Insertar varios valores y comprobar que se extraen en orden LIFO.
- Confirmar que `peek()` devuelve el tope y no cambia `size()` ni el siguiente resultado de `pop()`.
- Llenar el arreglo exactamente: `isFull()` verdadero y `size()` igual a la capacidad.
- Intentar `push` estando llena: esperar `IllegalStateException` y verificar que `size()`, el tope y el contenido observable no cambiaron.
- Ejecutar `pop` y `peek` en una pila vacía: esperar `NoSuchElementException` y verificar que el estado siga vacío.
- Después de un `pop` desde una pila llena, insertar otro valor y comprobar que la posición liberada se reutiliza correctamente.

## 8. Alcance

Este plan cubre el diseño de la clase y su contrato observable. No incluye una clase de demostración ni cambios al resto del proyecto; la implementación debe seguir la estructura de archivos Java que se defina para el ejercicio.