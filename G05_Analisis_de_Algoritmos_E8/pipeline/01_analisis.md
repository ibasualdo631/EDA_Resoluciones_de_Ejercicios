# Análisis — Ejercicio 8: sumar todos los elementos de una matriz

## Objetivo

Construir un prompt técnico para solicitar la implementación en Java de un algoritmo que recorra una matriz de enteros y calcule la suma de todos sus elementos. El programa también debe contabilizar las operaciones elementales solicitadas y explicar la complejidad.

## Requerimientos funcionales

- Recibir una matriz Java de enteros (`int[][]`).
- Recorrerla por filas y, dentro de cada fila, por columnas mediante dos ciclos anidados.
- Acumular en una variable la suma de todos los valores.
- Contabilizar por separado:
  - **Lecturas de elementos:** una por cada acceso a una celda `matriz[i][j]`.
  - **Sumas acumuladas:** una por cada operación de adición de un elemento al acumulador.
- Informar el resultado y los dos contadores de forma clara.
- Explicar la complejidad temporal y espacial, indicando que `N` representa el número total de elementos de la matriz, no necesariamente el producto de dimensiones fijas.

## Reglas y decisiones

- Las filas se recorren de `0` a `matriz.length - 1`; las columnas, de `0` a `matriz[i].length - 1`. Esta forma también admite matrices irregulares (filas de longitudes distintas).
- Las condiciones de los ciclos, lecturas de longitudes, inicializaciones y actualizaciones de índices no se incluyen en el conteo pedido: se cuentan exclusivamente accesos a celdas y sumas al acumulador.
- Se cuenta una suma acumulada por celda, incluso la primera suma con acumulador inicializado en cero. Para `N` celdas, los contadores esperados son `N` lecturas de elementos y `N` sumas acumuladas.
- Una referencia `null` se considera entrada inválida y debe producir `IllegalArgumentException`, en vez de confundirse con una matriz vacía.
- Una matriz sin filas o con filas vacías es válida: su suma y ambos contadores son cero. La lógica de recorrido también debe tolerar filas vacías dentro de una matriz con otras filas no vacías.
- Usar `long` para la suma y los contadores para reducir el riesgo de desbordamiento del resultado o del conteo. El tipo de los elementos de entrada sigue siendo `int`.

## Complejidad

- **Temporal:** `O(filas + N)` en general, porque se inspecciona cada fila y se visita cada elemento una sola vez. Se puede expresar como `O(N)` para matrices no vacías en las que la cantidad de filas está acotada por la cantidad total de elementos. Si todas las filas están vacías, el costo es `O(filas)`.
- **Espacial auxiliar:** `O(1)`, porque se usan únicamente acumuladores, contadores e índices, sin crear estructuras cuyo tamaño dependa de la cantidad de elementos.

## Casos de prueba a contemplar

1. Matriz rectangular con valores positivos y negativos; verificar suma y contadores.
2. Matriz con una sola celda.
3. Matriz con cero filas (`new int[0][0]`); resultado y contadores en cero.
4. Matriz con filas vacías, incluidas filas vacías junto con filas no vacías.
5. Referencia `null`; verificar que se informa el argumento inválido mediante la excepción especificada.

## Prompt técnico inicial

> Implementá en Java un algoritmo que calcule la suma de todos los elementos de una matriz de enteros representada como `int[][]`.
>
> Recorré la matriz por filas y columnas con dos ciclos anidados. Usá la longitud de cada fila para determinar cuántas columnas recorrer, de modo que también se admitan matrices irregulares. Acumulá los elementos en una variable de tipo `long`.
>
> Además del resultado, contabilizá y mostrá por separado:
> - las lecturas de elementos de la matriz (un acceso a `matriz[i][j]` cuenta como una lectura);
> - las sumas acumuladas (cada operación que agrega un elemento al acumulador cuenta como una suma, incluida la primera suma con el acumulador inicializado en cero).
>
> No incluyas en esos contadores las comprobaciones de los ciclos, las lecturas de longitudes, las inicializaciones ni los incrementos de índices. Para una matriz con `N` elementos, el conteo esperado es `N` lecturas de elementos y `N` sumas acumuladas.
>
> Si la referencia recibida es `null`, rechazala lanzando `IllegalArgumentException`. Si la matriz no tiene filas o todas sus filas están vacías, devolvé suma cero y contadores en cero. También manejá filas vacías junto con filas no vacías.
>
> Presentá el código completo, explicá brevemente el recorrido y la política para los casos borde, y analizá la complejidad temporal y espacial. Definí `N` como el número total de elementos: el tiempo es `O(filas + N)` en general y `O(N)` cuando la cantidad de filas está acotada por el total de elementos; el espacio auxiliar es `O(1)`.