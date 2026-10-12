# Análisis — Ejercicio 2: Burbuja vs. Selección

## Objetivo

Implementar en Java `bubbleSort(int[] array)` y `selectionSort(int[] array)` para ordenar en forma ascendente el mismo arreglo de valores y comparar cuántas comparaciones e intercambios realiza cada algoritmo.

## Diferencia central

- **Burbuja:** recorre pares de elementos vecinos y los intercambia cuando están en el orden incorrecto. Un recorrido puede producir muchos intercambios; los valores mayores van desplazándose hacia el final.
- **Selección:** en cada pasada busca el menor elemento de la parte todavía no ordenada y, al terminar la búsqueda, lo intercambia una sola vez con la primera posición de esa parte. Hace muchas comparaciones, pero como máximo un intercambio por pasada.

Para que ambos trabajen sobre los mismos datos, cada método debe recibir una copia independiente del arreglo original; de lo contrario, el segundo recibiría el arreglo ya ordenado por el primero.

## Requerimientos

- Implementar los dos métodos solicitados en Java y ordenar ascendentemente.
- Contabilizar las comparaciones entre valores y los intercambios efectuados por cada algoritmo.
- Usar el mismo conjunto inicial de valores para ambas ejecuciones.
- Mostrar el arreglo ordenado y los contadores de cada método para poder compararlos.
- Evitar contar como intercambio un elemento que no cambia de posición.

## Prompt base

> En Java, implementá `bubbleSort(int[] array)` y `selectionSort(int[] array)` para ordenar ascendentemente. Probá ambos con copias del mismo arreglo e informá, para cada algoritmo, el arreglo resultante, la cantidad de comparaciones y la cantidad de intercambios. Burbuja debe comparar e intercambiar vecinos; Selección debe buscar el menor de la parte no ordenada y hacer como máximo un intercambio por pasada. Incluí un ejemplo de ejecución breve.