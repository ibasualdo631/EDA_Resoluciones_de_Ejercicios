# Ejercicio 10 — Revisión y resultados de Bubble Sort

## Revisión

Se revisó `BubbleSort.java`: el método rechaza `null` con
`IllegalArgumentException`, cuenta comparaciones entre elementos e
intercambios efectivos, y detiene el algoritmo si una pasada no intercambia
elementos. El método ordena el arreglo in-place e imprime ambos contadores.
`main` verifica el resultado ordenado y maneja el caso `null`.

Se agregó a `main` la prueba explícita de un arreglo de un elemento, además de
los casos ya incluidos.

## Casos y métricas

| Caso | Entrada | Comparaciones esperadas | Comparaciones obtenidas | Intercambios esperados | Intercambios obtenidos |
|---|---|---:|---:|---:|---:|
| Ordenado | `[1, 2, 3, 4]` | 3 | 3 | 0 | 0 |
| Invertido | `[4, 3, 2, 1]` | 6 | 6 | 6 | 6 |
| Duplicados | `[5, 2, 5, 1, 2]` | 10 | 10 | 6 | 6 |
| Vacío | `[]` | 0 | 0 | 0 | 0 |
| Un elemento | `[7]` | 0 | 0 | 0 | 0 |

Las expectativas para el arreglo ya ordenado corresponden a una pasada de
verificación (`n - 1` comparaciones); para el inverso de longitud cuatro,
`n(n - 1)/2` comparaciones e intercambios. En el caso con duplicados, las seis
inversiones de la entrada requieren seis intercambios; el algoritmo ejecuta
10 comparaciones antes de detectar una pasada sin intercambios y terminar.

## Ejecución

Compilación con `javac` y ejecución de `main`: **correctas**. Los cinco casos
mostraron el arreglo ascendente esperado y las métricas de la tabla. La prueba
adicional de `null` confirmó que se lanza `IllegalArgumentException`.

Las métricas se imprimen y fueron contrastadas con las expectativas; el método
auxiliar de `main` verifica automáticamente el arreglo ordenado, pero no
compara los contadores con valores esperados.