# Explicación — `PilaEnteros`

## ¿Por qué `top` empieza en `-1`?

`top` guarda el **índice** del último elemento ocupado; no guarda el tamaño. En
Java, los índices de un arreglo empiezan en `0`. Al construir la pila todavía
no hay ningún índice ocupado, así que se usa `-1` como valor centinela para
representar “vacía”.

Esto hace que las operaciones y las consultas encajen:

- Vacía: `top == -1`.
- Tamaño: `top + 1`; por eso una pila vacía tiene tamaño `0`.
- Inserción: `++top` lleva el índice de `-1` a `0`, y luego se escribe en
  `elementos[0]`.
- Al quitar el último elemento, `top--` lo devuelve de `0` a `-1`.
- Llena: `top == elementos.length - 1`, que es el último índice válido.

Usar `0` para representar la pila vacía confundiría el estado vacío con una
pila que ya tiene un elemento en `elementos[0]`.

## Cómo se representa la pila en memoria

El constructor crea `elementos` con `new int[capacidad]`. En el modelo habitual
de Java, el objeto `PilaEnteros` y el objeto arreglo se alojan en el **Heap**.
El campo `elementos` mantiene la referencia al arreglo, y `top` guarda el índice
de la cima. Cada casilla del arreglo contiene directamente un valor primitivo
`int`; no hace falta crear un objeto `Integer` por cada valor.

El arreglo ofrece posiciones indexadas de `0` hasta `capacidad - 1`. Es útil
imaginar sus componentes como una secuencia contigua de casillas:

```text
índice:      0      1      2      3
elementos: [ 12 ] [ 25 ] [    ] [    ]
                         ^
                         top = 1
```

Esta contigüidad describe cómo se accede al arreglo por índices y por qué
acceder a `elementos[top]` es una operación directa de tiempo constante. Es una
abstracción del lenguaje: Java no garantiza que el arreglo ocupe direcciones
físicas consecutivas específicas; el layout y la gestión real de memoria los
decide la JVM.

Cuando se hace `pop`, la implementación decrementa `top`; no necesita desplazar
los otros valores ni achicar el arreglo. La casilla pasa a considerarse libre y
el próximo `push` puede sobrescribirla. El dato antiguo que quede físicamente
en esa casilla está fuera de la pila lógica porque el índice ya no está ocupado.

## Operaciones principales

- **`push(valor)`**: verifica primero `isFull()`. Si hay espacio, incrementa
  `top` y almacena el valor. Si está llena, lanza `IllegalStateException` antes
  de cambiar el estado.
- **`pop()`**: verifica primero `isEmpty()`. Si hay elementos, devuelve
  `elementos[top]` y decrementa `top`. Si está vacía, lanza
  `NoSuchElementException`.
- **`peek()`**: devuelve `elementos[top]` sin modificar `top`; también rechaza
  una pila vacía.
- **`isEmpty()`, `isFull()` y `size()`**: consultan el estado a partir de `top`
  y la longitud del arreglo.

Como se inserta y se extrae desde la misma cima, la pila respeta LIFO (*Last In,
First Out*): el último elemento que entra es el primero que sale.

## Tres preguntas cortas

1. **¿Qué representa `top == -1`?**  
   Que la pila está vacía.

2. **¿Qué devuelve `pop()` después de insertar `5` y luego `8`?**  
   Devuelve `8`, porque es el último valor insertado.

3. **¿Qué excepción indica Overflow en esta implementación?**  
   `IllegalStateException`.