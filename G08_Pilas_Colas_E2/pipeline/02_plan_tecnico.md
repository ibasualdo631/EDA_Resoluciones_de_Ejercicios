# Plan técnico — `TorrePlatos`

## Decisión de diseño

Implementar una clase Java `TorrePlatos` que conserva la lógica de `PilaEnteros`: un arreglo, un índice `top` que señala el último elemento ocupado, `top = -1` para representar el estado vacío y operaciones que solo actúan sobre el extremo superior.

`PilaEnteros` almacena exclusivamente `int`, mientras que la consigna admite números o nombres. Por eso, `TorrePlatos` no puede usarla directamente sin perder uno de esos formatos. Se mantiene la misma lógica de pila en una clase específica del ejercicio y se usa un arreglo `Object[]` que acepte únicamente valores `String` o `Number`. No es necesario modificar el ejercicio anterior ni introducir una cola.

Se conserva la capacidad fija y positiva usada por `PilaEnteros`. Al no indicar la consigna una política de crecimiento, la capacidad será un parámetro explícito del constructor; al llenarse la torre, apilar otro plato informa el error mediante una excepción y no altera la torre.

## Estado y constructor

- `private final Object[] platos`: almacenamiento desde la base hacia el tope.
- `private int top`: índice del plato superior; vale `-1` cuando no hay platos.
- `public TorrePlatos(int capacidad)`: crea una torre vacía.
  - Si `capacidad <= 0`, lanza `IllegalArgumentException`.
  - Inicializa el arreglo con la capacidad indicada y `top = -1`.

## API pública

| Método | Firma | Comportamiento |
|---|---|---|
| Apilar | `public void apilar(Object plato)` | Coloca el plato sobre el tope actual. Acepta valores `String` y `Number`; un valor `null` o de otro tipo lanza `IllegalArgumentException`. Si no queda espacio, lanza `IllegalStateException` (overflow). En caso de excepción, el contenido y el tope permanecen sin cambios. |
| Desapilar | `public Object desapilar()` | Retira y devuelve el plato superior, decrementando `top`. Si la torre está vacía, lanza `NoSuchElementException` (underflow), sin modificar el estado. |
| Consultar superior | `public Object consultarSuperior()` | Devuelve el plato superior sin retirarlo ni cambiar `top`. Si la torre está vacía, lanza `NoSuchElementException` (underflow), sin modificar el estado. |
| Consultar vacío | `public boolean estaVacia()` | Devuelve `true` si `top == -1`; no modifica la torre. |
| Consultar cantidad | `public int cantidadPlatos()` | Devuelve `top + 1`; no modifica la torre. |

Importaciones necesarias:

```java
import java.util.NoSuchElementException;
```

`IllegalArgumentException` e `IllegalStateException` pertenecen a `java.lang` y no requieren importación.

## Orden y secuencia de operaciones

1. Validar la capacidad en el constructor y crear la torre vacía.
2. En `apilar`, validar primero el valor y luego comprobar si está llena (`top == platos.length - 1`). Solo después de esas validaciones se escribe en `platos[++top]`.
3. En `desapilar` y `consultarSuperior`, comprobar primero si está vacía. Si no lo está, usar `platos[top]`; solo `desapilar` decrementa `top`.
4. Para una torre con `A`, `B`, `C` apilados en ese orden, `consultarSuperior()` devuelve `C`; los desapilados sucesivos devuelven `C`, `B`, `A`.

El orden de las validaciones en `apilar` garantiza que un tipo inválido se reporte como argumento inválido y que ningún fallo parcial cambie el estado.

## Excepciones y estado consistente

- `IllegalArgumentException`: capacidad no positiva al crear la torre; valor nulo o tipo distinto de `String`/`Number` al apilar.
- `IllegalStateException`: se intenta apilar cuando la capacidad fue alcanzada.
- `NoSuchElementException`: se intenta desapilar o consultar el plato superior cuando la torre está vacía.
- No capturar estas excepciones dentro de la clase: comunicar errores de uso al llamador. La interfaz de usuario, si existe, será responsable de capturarlas y presentar mensajes comprensibles.
- Toda operación que falla deja la torre igual que antes del llamado.

## Pruebas de aceptación propuestas

1. Una instancia nueva está vacía y su cantidad es cero.
2. Apilar `"A"`, `"B"`, `"C"` deja `"C"` como superior y la cantidad en tres.
3. Consultar varias veces devuelve `"C"` y no cambia la cantidad ni el próximo desapilado.
4. Desapilar en secuencia devuelve `"C"`, `"B"`, `"A"` y deja la torre vacía.
5. Con capacidad uno, el segundo apilado produce `IllegalStateException` y conserva el primer plato.
6. Consultar y desapilar sobre una torre vacía producen `NoSuchElementException`.
7. Capacidad cero o negativa produce `IllegalArgumentException`.
8. Apilar `null` o un objeto que no sea `String` ni `Number` produce `IllegalArgumentException` sin modificar la torre.
9. También se puede apilar y retirar un valor numérico, por ejemplo `7`.