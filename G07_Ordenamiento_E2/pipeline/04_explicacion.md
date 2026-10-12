# Resultados por consola

Los dos algoritmos reciben una copia del mismo arreglo y lo ordenan de menor a mayor:
`[64, 34, 25, 12, 22, 11, 90]`.

- **Comparaciones: 21 en ambos.** Para 7 elementos, los ciclos comparan `6 + 5 + 4 + 3 + 2 + 1 = 21` pares.
- **Bubble Sort: 14 intercambios.** Intercambia elementos vecinos que están al revés.
- **Selection Sort: 4 intercambios.** En cada pasada busca el menor del tramo que falta y lo pone en su lugar.
- **Arreglo ordenado en ambos:** `[11, 12, 22, 25, 34, 64, 90]`.

## Por qué Selection Sort escribe menos

En esta implementación, cada intercambio escribe dos posiciones del arreglo. Bubble Sort realiza un intercambio por cada inversión del arreglo inicial (un par que aparece en orden descendente). Aquí hay 14 inversiones, así que hace `14 × 2 = 28` escrituras en el arreglo.

Selection Sort hace como máximo un intercambio por pasada y hay `n - 1` pasadas. Por lo tanto, hace como máximo `n - 1` intercambios, es decir, como máximo `2(n - 1)` escrituras en el arreglo. Para `n = 7`, el límite es 12 escrituras; en este caso no necesita intercambiar en dos pasadas, así que realiza 4 intercambios, o 8 escrituras.

Formalmente, la cota máxima de escrituras de Selection Sort es lineal, `2(n - 1)`, mientras que Bubble Sort puede hacer hasta `2 × n(n - 1)/2 = n(n - 1)` escrituras. Por eso Selection Sort reduce las escrituras en el peor caso. No significa que siempre haya una diferencia estricta: si el arreglo ya está ordenado, ambos pueden hacer cero intercambios.