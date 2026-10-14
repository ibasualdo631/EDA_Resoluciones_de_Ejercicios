# Plan técnico — Invertir una palabra con pila enlazada

## Diseño de la solución

Implementar la estructura de pila con nodos enlazados propios y usarla para invertir una cadena en dos recorridos:

1. Recorrer la entrada de izquierda a derecha y apilar cada carácter.
2. Mientras la pila no esté vacía, desapilar un carácter y añadirlo al resultado.

El segundo recorrido produce los caracteres en orden contrario al primero porque una pila opera según LIFO (*Last In, First Out*): el último carácter que entra queda en la cima y es el primero que sale. Así, para `algoritmo`, el desapilado produce `omtirogla`.

## Componentes y responsabilidades

### `Nodo`

Representa un elemento de la pila:

- `char dato`: carácter almacenado.
- `Nodo siguiente`: referencia al nodo que estaba debajo.

### `PilaEnlazada`

Mantiene `Nodo cima`, que será `null` cuando no haya elementos.

- `void apilar(char caracter)`: crea un nodo cuyo enlace apunta a la cima actual y actualiza la cima.
- `char desapilar()`: obtiene el carácter de la cima y la mueve al nodo siguiente. Se invoca solo cuando la pila no está vacía.
- `boolean estaVacia()`: informa si la cima es `null`.

Cada operación modifica o consulta únicamente la cima, por lo que apilar y desapilar tienen costo O(1).

### Operación de inversión

Firma propuesta: `static String invertir(String palabra)`.

Responsabilidades:

1. Crear una `PilaEnlazada` vacía.
2. Recorrer `palabra` por índice desde `0` hasta `palabra.length() - 1`; apilar `palabra.charAt(i)`.
3. Crear un `StringBuilder`.
4. Mientras `!pila.estaVacia()`, desapilar un carácter y añadirlo al `StringBuilder`.
5. Retornar `resultado.toString()`.

`StringBuilder` evita reconstruir repetidamente una cadena inmutable durante la concatenación.

### Interfaz de consola

En `main`, leer la palabra según la interfaz que se defina para el ejercicio, llamar a `invertir` y mostrar el resultado. El enunciado no determina el formato de entrada/salida; si se decide aceptar espacios, deberá leerse una línea completa en lugar de un único token.

## Secuencia y ejemplo de ejecución

Entrada: `algoritmo`

| Paso | Operación | Cima después de apilar / carácter extraído |
|---|---|---|
| 1 | Apilar `a` | `a` |
| 2 | Apilar `l` | `l` |
| 3 | Apilar `g` | `g` |
| 4 | Apilar `o` | `o` |
| 5 | Apilar `r` | `r` |
| 6 | Apilar `i` | `i` |
| 7 | Apilar `t` | `t` |
| 8 | Apilar `m` | `m` |
| 9 | Apilar `o` | `o` |
| 10 | Desapilar hasta vaciar | `o`, `m`, `t`, `i`, `r`, `o`, `g`, `l`, `a` |

Al agregar los caracteres extraídos en ese orden, el resultado es `omtirogla`.

## Casos límite y seguridad de operaciones

- **Cadena vacía:** ambos recorridos no procesan elementos; se retorna `""`. No se llama a `desapilar` con la pila vacía.
- **Un carácter:** se apila y se desapila una vez; el resultado coincide con la entrada.
- **Caracteres repetidos:** se almacenan en nodos separados y se conservan todas las apariciones.
- **Signos y mayúsculas:** se mantienen literalmente; no se normaliza ni transforma el carácter.

## Complejidad y recursos

Para una entrada de longitud `n`, se realizan `n` operaciones de apilado y `n` de desapilado: tiempo O(n). Los nodos requieren O(n) espacio auxiliar; el `StringBuilder` de salida requiere O(n).

## Verificaciones para la implementación

- La salida de `invertir("algoritmo")` es `"omtirogla"`.
- La salida de `invertir("")` es `""`.
- La salida de `invertir("x")` es `"x"`.
- La cima se actualiza correctamente en cada apilado y desapilado, y una pila vacía queda representada por `cima == null`.
- La implementación usa efectivamente la pila enlazada, no una inversión por índices ni una función de inversión de biblioteca.