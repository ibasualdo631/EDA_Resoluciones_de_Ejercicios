# Explicación — Invertir una palabra con pila enlazada

## Idea principal: LIFO invierte el orden

Una pila trabaja con la regla LIFO (*Last In, First Out*, «último en entrar, primero en salir»). Al insertar una secuencia de caracteres de izquierda a derecha, cada carácter nuevo queda arriba del anterior. Al retirar los elementos desde la cima, salen en orden inverso. Por ejemplo, si se apilan `a`, `l`, `g`, `o`, `r`, `i`, `t`, `m`, `o`, el desapilado produce `o`, `m`, `t`, `i`, `r`, `o`, `g`, `l`, `a`: `algoritmo` se convierte en `omtirogla`.

## Cómo se almacena cada carácter

La clase interna `Nodo` representa un elemento de la lista enlazada:

- `dato` guarda un carácter (`char`).
- `siguiente` referencia al nodo que estaba en la cima antes de insertar este nodo.

Cuando se apila un carácter, se crea un nodo nuevo cuyo enlace `siguiente` apunta a la cima anterior, y la referencia `cima` pasa a apuntar al nodo nuevo. Por ejemplo:

```text
Apilar 'a': cima → [a | null]
Apilar 'l': cima → [l | •] → [a | null]
Apilar 'g': cima → [g | •] → [l | •] → [a | null]
```

Así, `cima` siempre identifica el elemento que debe salir primero. No se recorren los nodos para apilar: insertar en la cima es una operación O(1).

## Cómo funciona cada parte del programa

### Clase `PilaEnlazada`

Esta clase encapsula el comportamiento de la pila usando nodos propios, sin depender de una colección de pila ya implementada.

- `apilar(char caracter)` crea el nodo nuevo y actualiza la cima.
- `desapilar()` guarda el dato de la cima, mueve la cima al nodo siguiente y devuelve el carácter guardado. Si la pila está vacía, genera una `IllegalStateException` para evitar una lectura inválida.
- `estaVacia()` comprueba si `cima` es `null`.

Al desapilar, el nodo retirado deja de estar alcanzable desde la cima. Si era el último, `cima` queda en `null`.

### Método `invertir(String palabra)`

1. Comprueba que la referencia recibida no sea `null`. En caso de serlo, informa el error con `IllegalArgumentException`.
2. Recorre la cadena por índice, desde `0` hasta `palabra.length() - 1`. En cada vuelta, `charAt(i)` obtiene un carácter y `apilar` lo agrega a la cima.
3. Crea un `StringBuilder` con capacidad inicial igual a la longitud de la entrada. Este objeto permite añadir caracteres sucesivamente sin crear una cadena nueva en cada paso.
4. Mientras la pila tenga elementos, desapila el carácter de la cima y lo agrega al final del `StringBuilder`.
5. Convierte el `StringBuilder` a `String` y retorna el resultado.

El método no modifica la cadena recibida: crea nodos para almacenar los caracteres temporalmente y construye una cadena de salida nueva.

### Método `main`

`Scanner` lee una línea completa, por lo que también puede recibir espacios. Si la entrada termina sin proporcionar una línea, usa la cadena vacía. Luego llama a `invertir` e imprime el resultado. El bloque `try` con recursos cierra el `Scanner` al terminar.

## Ejemplo completo, paso a paso

Con entrada `algoritmo`, los nodos quedan enlazados desde la cima así:

```text
cima → [o] → [m] → [t] → [i] → [r] → [o] → [g] → [l] → [a] → null
```

Al desapilar se leen esos datos de izquierda a derecha. Cada carácter se añade al final del `StringBuilder`:

| Carácter extraído | Contenido acumulado |
|---|---|
| `o` | `o` |
| `m` | `om` |
| `t` | `omt` |
| `i` | `omti` |
| `r` | `omtir` |
| `o` | `omtiro` |
| `g` | `omtirog` |
| `l` | `omtirogl` |
| `a` | `omtirogla` |

La pila termina vacía y el `StringBuilder` contiene la palabra invertida.

## Casos límite

- **Cadena vacía (`""`):** el ciclo de apilado no se ejecuta y la pila está vacía desde el inicio. Tampoco se ejecuta el ciclo de desapilado; se retorna `""`.
- **Una letra (por ejemplo, `"x"`):** se apila y se retira una sola vez, por lo que el resultado sigue siendo `"x"`.
- **Letras repetidas:** cada aparición ocupa su propio nodo; no se descarta ninguna aunque los valores coincidan.
- **`null`:** no equivale a una cadena vacía; el método lo rechaza explícitamente con `IllegalArgumentException`.

## Complejidad

Para `n` caracteres se realizan `n` apilados y `n` desapilados, con tiempo total O(n). Los nodos de la pila y el contenido del `StringBuilder` ocupan O(n) espacio auxiliar.