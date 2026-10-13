# Explicación — Desperdicio de espacio en una cola lineal

## Qué demuestra el programa

`DesperdicioCola` implementa deliberadamente una **cola lineal** sobre un arreglo de cinco posiciones. `front` señala el elemento que se desencola a continuación y `rear`, el último elemento encolado. Al desencolar, el programa vacía la celda de `front` y avanza ese índice; no modifica las posiciones de los demás elementos.

La secuencia de `main` termina con este estado:

```text
Índice:   0  1  2  3  4
Arreglo:  _  _  C  D  E
front = 2, rear = 4
```

Aunque las posiciones `0` y `1` estén libres, el intento de encolar `F` se rechaza: la condición de `encolar` comprueba si `rear` llegó al último índice. Por eso el rechazo es un **falso desbordamiento**: no se llenó físicamente el arreglo, pero la cola lineal ya no puede avanzar `rear`.

## Por qué no se desplazan los elementos hacia la izquierda

Se podría compactar la cola después de desencolar: mover `C` de la posición `2` a la `0`, `D` de la `3` a la `1`, y así sucesivamente. Eso permitiría recuperar espacio al final, pero hay que copiar los elementos que siguen en la cola. Si quedan `n` elementos, el corrimiento puede requerir hasta `n` movimientos, por lo que cuesta **O(n)**.

Ese costo se repetiría cada vez que se compactara. En cambio, encolar y desencolar en una cola circular pueden actualizar índices en **O(1)**, sin mover los elementos almacenados. Además, desplazar en esta demostración ocultaría precisamente el problema que se quiere observar: el desperdicio de las celdas liberadas en una cola lineal.

## Por qué conviene una estructura circular

Una cola circular interpreta el arreglo como si su último índice estuviera conectado con el primero. Cuando `rear` alcanza el final, la siguiente posición se calcula con el resto de la división:

```text
(rear + 1) % capacidad
```

Con capacidad `5`, después del índice `4` se vuelve al índice `0`. En el estado de la traza, como `front = 2`, las posiciones `0` y `1` ya quedaron atrás de los elementos activos; la cola circular puede reutilizarlas al llegar `rear` al final, sin desplazar `C`, `D` ni `E`. Así se aprovecha el espacio liberado y se evita el falso desbordamiento, siempre que la cola no esté realmente llena.

En una implementación circular hay que distinguir correctamente **vacía** de **llena**, porque `front` y `rear` pueden coincidir en ambos casos según la convención usada. Una solución habitual mantiene un contador de elementos; otra reserva una celda sin usar. El programa de este ejercicio no implementa esa lógica circular: muestra intencionalmente la limitación de la cola lineal.

## Idea central

- **Cola lineal sin corrimiento:** desencolar avanza `front`, pero las posiciones liberadas no se reutilizan; la inserción puede fallar aunque haya celdas vacías.
- **Compactar hacia la izquierda:** recupera espacio, pero mueve elementos y puede costar **O(n)**.
- **Cola circular:** reutiliza las celdas liberadas al recorrer el arreglo en ciclo, sin mover elementos y con operaciones de índice **O(1)**.

Por eso, cuando el objetivo es reutilizar eficientemente todas las posiciones de un arreglo de capacidad fija, la estructura adecuada es una **cola circular**, no una cola lineal que desplaza sus elementos.