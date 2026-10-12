# Plan técnico — Bubble Sort

## Objetivo

Ordenar un arreglo de enteros en forma ascendente mediante comparaciones e intercambios entre elementos vecinos, mostrando el arreglo al finalizar cada ciclo externo.

## Estructura mínima

- Implementar `bubbleSort(int[] array)`; modifica el arreglo recibido y no devuelve un valor.
- Usar un ciclo externo para las pasadas (`i` desde `0` hasta `array.length - 2`).
- En cada pasada, usar un ciclo interno desde `0` hasta `array.length - 2 - i`. Comparar `array[j]` con `array[j + 1]`.
- Si `array[j] > array[j + 1]`, intercambiar ambos elementos usando una variable temporal.
- Al terminar cada ciclo interno, imprimir el número de pasada y el estado actual del arreglo. En Java se puede usar `Arrays.toString(array)`.
- No agregar una salida anticipada cuando una pasada no realiza intercambios; se deben conservar las pasadas previstas para esta versión básica.

## Secuencia por pasada

1. Comparar cada par de vecinos de la parte no ordenada.
2. Intercambiar el par cuando esté en orden descendente.
3. Al terminar las comparaciones de esa pasada, imprimir el arreglo completo.
4. Continuar: el mayor valor de la parte no ordenada queda al final de esa sección.

El estado se imprime una sola vez por ciclo externo, después de concluir el ciclo interno; no después de cada comparación o intercambio. Con arreglos vacíos o de un solo elemento no hay ciclos externos y, por lo tanto, no se imprimen pasadas.

## Complejidad

- **Temporal:** `O(n²)` en el caso promedio y peor caso, por las comparaciones de pares vecinos en ciclos anidados. Esta versión sin salida anticipada también mantiene `O(n²)` en el mejor caso.
- **Espacial:** `O(1)` adicional; ordena en el mismo arreglo y usa una variable temporal para intercambiar.

## Instrucciones para el desarrollador

Implementá el método con los dos ciclos descritos, el intercambio condicional y una impresión al final de cada pasada externa. Probalo con un arreglo desordenado y verificá que cada línea impresa corresponda al estado tras una pasada completa, que el resultado final esté ordenado ascendentemente y que la firma del método sea `bubbleSort(int[] array)`.