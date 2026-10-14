# Plan técnico — Verificador de paréntesis balanceados

## Diseño general

El programa leerá una expresión como una cadena y recorrerá sus caracteres de izquierda a derecha. Usará una pila implementada mediante nodos enlazados para recordar las aperturas `(` que todavía no tienen pareja. La pila seguirá el principio LIFO: cada `)` se empareja con la apertura pendiente más reciente.

Solo se validará el balance de los paréntesis redondos. Los demás caracteres se ignorarán, de acuerdo con el alcance definido en `01_analisis.md`.

## Estructuras de datos

### Nodo de la pila

Cada nodo almacena un carácter de apertura y una referencia al siguiente nodo:

- `char dato`: apertura almacenada (`'('`).
- `Nodo siguiente`: nodo que queda debajo en la pila.

### Pila enlazada

La pila mantiene una referencia `tope` al nodo superior. Estará vacía cuando `tope == null`.

- **Apilar (`push`)**: crear un nodo con la apertura y enlazarlo antes del tope actual; actualizar `tope`.
- **Desapilar (`pop`)**: retirar el nodo apuntado por `tope` y actualizar `tope` a su siguiente nodo. Esta operación se realiza solo si la pila no está vacía.
- **Consultar si está vacía (`isEmpty`)**: comprobar si `tope == null`.

`push`, `pop` e `isEmpty` tienen costo `O(1)`. No se requiere almacenar la posición ni el contenido de toda la expresión en la pila.

## Módulos y contratos propuestos

Las firmas son una guía para la implementación Java; pueden ajustarse a las convenciones del proyecto sin cambiar el algoritmo:

| Componente | Firma propuesta | Responsabilidad |
|---|---|---|
| `Nodo` | `Nodo(char dato, Nodo siguiente)` | Representar un elemento de la pila. |
| `PilaCaracteres` | `void push(char dato)` | Insertar una apertura en el tope. |
| `PilaCaracteres` | `char pop()` | Retirar y devolver el carácter del tope; precondición: pila no vacía. |
| `PilaCaracteres` | `boolean isEmpty()` | Indicar si no hay aperturas pendientes. |
| Validador | `boolean estaBalanceada(String expresion)` | Recorrer la expresión y decidir si sus paréntesis están balanceados. |
| Entrada/salida | `public static void main(String[] args)` | Leer o definir la expresión de entrada, invocar el validador y mostrar el resultado. |

El método validador asume una referencia de expresión no nula. Si la interfaz de entrada puede producir `null`, el programa debe tratarlo explícitamente en la capa de entrada; no se interpretará silenciosamente como una expresión vacía.

## Algoritmo de validación

1. Crear una pila vacía.
2. Recorrer cada carácter de la expresión, desde el índice `0` hasta `length - 1`.
3. Si el carácter es `(`, apilarlo. Así se registra que hay una apertura pendiente.
4. Si el carácter es `)`:
   - Si la pila está vacía, devolver `false` inmediatamente: este cierre no puede emparejarse con ninguna apertura anterior.
   - Si la pila no está vacía, desapilar una apertura. Por LIFO, es la apertura pendiente más cercana y evita aceptar un orden de anidamiento incorrecto.
5. Ignorar cualquier otro carácter.
6. Al acabar el recorrido, devolver `true` si la pila está vacía; en caso contrario, devolver `false` porque quedaron aperturas sin cierre.

Pseudocódigo:

```text
estaBalanceada(expresion):
    pila = nueva PilaCaracteres()

    para cada caracter en expresion:
        si caracter == '(':
            pila.push(caracter)
        si no, si caracter == ')':
            si pila.isEmpty():
                devolver false
            pila.pop()

    devolver pila.isEmpty()
```

## Secuencia de ejecución

1. Obtener la expresión.
2. Invocar `estaBalanceada(expresion)`.
3. El validador consume la cadena una sola vez y usa la pila enlazada para seguir las aperturas pendientes.
4. Mostrar un resultado claro según el booleano retornado, por ejemplo, “Paréntesis balanceados” o “Paréntesis no balanceados”.

## Corrección e invariantes

- Después de procesar cualquier prefijo sin error, la pila contiene exactamente las aperturas de ese prefijo que aún no han sido emparejadas, en orden de aparición.
- Si se encuentra un cierre con la pila vacía, existe un prefijo con más cierres que aperturas; no puede balancearse agregando caracteres posteriores, por lo que se puede rechazar inmediatamente.
- Al terminar, pila vacía significa que cada cierre encontró su apertura y que no quedaron aperturas pendientes. El emparejamiento LIFO garantiza el orden anidado.

## Complejidad

Para una expresión de longitud `n`, cada carácter se procesa una vez y cada paréntesis se apila o desapila como máximo una vez:

- Tiempo: `O(n)`.
- Espacio adicional: `O(n)` en el peor caso, cuando todas las posiciones son aperturas; la pila enlazada crece solo según las aperturas pendientes.

## Pruebas que deben cubrir la implementación

- Ejemplos válidos del enunciado: `(2 + 3) * (5 - 1)` y `((a + b) * c)`.
- Cierre inicial o sin apertura: `)(` y `())`.
- Aperturas sobrantes: `(2 + 3`.
- Sin paréntesis y cadena vacía: deben resultar balanceadas bajo este validador, porque se comprueba exclusivamente el equilibrio de delimitadores.