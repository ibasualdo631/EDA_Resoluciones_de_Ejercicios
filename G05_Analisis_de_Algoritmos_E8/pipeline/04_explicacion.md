# Explicación simple — SumaMatriz

## ¿Qué hace?

La clase `SumaMatriz` recibe una matriz de enteros, suma sus valores y lleva tres contadores:

- `lecturas`: cuántas celdas leyó.
- `sumas`: cuántas veces agregó un valor al acumulador.
- `operacionesTotales`: cuántas celdas procesó, contando una operación total por celda.

Los contadores son `static`, así que se consultan desde la clase con `getLecturas()`, `getSumas()` y `getOperacionesTotales()`. Sus valores corresponden a la última llamada a `sumar`.

## Cómo recorre la matriz

El primer `for` usa `i` para recorrer las filas. Dentro, el segundo `for` usa `j` para recorrer las columnas de esa fila. En cada vuelta:

1. `matriz[i][j]` lee el valor actual y lo guarda en `valor`.
2. Aumenta el contador de lecturas.
3. Agrega `valor` a `suma`.
4. Aumenta el contador de sumas acumuladas.
5. Aumenta el total de operaciones una vez por esa celda.

Como el límite del ciclo interno se obtiene de `matriz[i].length`, el algoritmo puede trabajar con matrices rectangulares y con filas de longitudes diferentes. Una fila vacía simplemente no ejecuta el ciclo interno.

## Validación y pruebas

Antes de recorrer, `sumar` reinicia los contadores. Si la matriz es `null` o no tiene filas, lanza `IllegalArgumentException`. El `main` muestra ejemplos de una matriz 3×3, una rectangular, una con negativos y los casos inválidos `null` y sin filas. El `try/catch` permite mostrar el mensaje de error sin detener el programa.

## Entender los contadores y el costo

Si hay `N` celdas, `lecturas` vale `N`, `sumas` vale `N` y `operacionesTotales` también vale `N`: este último cuenta cada celda una sola vez, no suma entre sí los dos contadores anteriores.

El algoritmo visita cada celda una vez, así que su tiempo de recorrido es `O(R + N)`, donde `R` es el número de filas. Para una matriz no vacía habitual se expresa como `O(N)`. Usa una cantidad fija de variables auxiliares, por lo que el espacio auxiliar es `O(1)`.

## Preguntas para comprobar la comprensión

1. Si la matriz tiene 2 filas y 4 columnas, ¿cuánto valen `lecturas`, `sumas` y `operacionesTotales`?
Los valores serían:
    a) Lecturas = 8
    b) Sumas = 8
    c) Operaciones Totales = 16

2. ¿Por qué una matriz `null` produce una excepción, mientras una fila vacía dentro de una matriz válida no detiene el recorrido?

Ya que una matriz con referencia null no apunta a ninguna ubicación de la memoria, por otro lado si una fila está vacía, puede que no tenga elementos pero por lo menos si apunta a una dirección en memoria.