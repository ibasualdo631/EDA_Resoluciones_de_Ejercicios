# Análisis del ejercicio: búsqueda de un elemento en un vector desordenado

## 1. Objetivo del problema

Se desea desarrollar un programa en Java que permita buscar un elemento dentro de un vector no ordenado. El algoritmo debe recorrer el arreglo y verificar si cada posición contiene el valor buscado.

La principal restricción es que el vector no está ordenado, por lo que no se pueden aplicar estrategias basadas en comparaciones de orden, como la búsqueda binaria. Por ello, la estrategia adecuada es una búsqueda secuencial (lineal).

## 2. Contexto y tipo de problema

Este ejercicio pertenece al análisis de algoritmos y está orientado a:

- comprender la diferencia entre búsqueda en arreglos ordenados y desordenados;
- justificar la elección de una estrategia según las condiciones del problema;
- analizar el costo temporal en términos de complejidad asintótica;
- medir cuántas posiciones se recorren antes de encontrar el valor o concluir que no existe.

## 3. Requisitos funcionales

El programa debe:

1. recibir o generar un vector desordenado;
2. solicitar el valor que se desea buscar;
3. recorrer el vector desde el inicio hasta el final;
4. comparar cada elemento con el valor buscado;
5. devolver si el valor fue encontrado o no;
6. informar cuántas posiciones fueron recorridas antes de encontrar el elemento o antes de determinar que no existe;
7. mostrar un mensaje claro sobre el resultado.

## 4. Reglas de negocio y comportamiento esperado

- Si el valor buscado aparece en la primera posición, el algoritmo debe detectar la coincidencia de inmediato.
- Si el valor aparece en una posición intermedia, el algoritmo debe seguir recorriendo hasta encontrarlo.
- Si el valor no existe, el algoritmo debe recorrer todo el vector y reportar que no se encontró.
- El número de posiciones recorridas debe ser exactamente el número de comparaciones realizadas en el peor caso o hasta el punto de éxito.
- El algoritmo no debe asumir que el vector está ordenado.

## 5. Estrategia adecuada

La estrategia correcta es la búsqueda secuencial o lineal.

### ¿Por qué es la adecuada?

Porque:

- el arreglo está desordenado;
- no existe relación de orden entre los elementos;
- para saber si un valor existe, hay que inspeccionar cada posición posible;
- la búsqueda binaria no se puede usar porque exige un arreglo ordenado.

En consecuencia, el algoritmo recorre el vector elemento por elemento hasta:

- encontrar el valor buscado, o
- llegar al final del arreglo, concluyendo que no existe.

## 6. Casos límite

### Caso mejor

El elemento buscado está en la primera posición del vector.

- Se compara con el primer elemento.
- Se encuentra de inmediato.
- Posiciones recorridas: 1.
- Complejidad: O(1).

### Caso peor

El elemento buscado no existe o está en la última posición.

- Se recorren todas las posiciones.
- Se hace un recorrido completo del vector.
- Posiciones recorridas: n.
- Complejidad: O(n).

### Caso promedio

El elemento puede estar en cualquier posición con igual probabilidad.

- El algoritmo suele recorrer aproximadamente la mitad del vector.
- Complejidad esperada: O(n).

## 7. Costo temporal del algoritmo

Sea n la cantidad de elementos del vector.

- Mejor caso: $O(1)$
- Peor caso: $O(n)$
- Caso promedio: $O(n)$

La razón es que, en un arreglo desordenado, en el peor escenario hay que comparar cada elemento antes de decidir si el valor existe o no.

## 8. Costo espacial

El algoritmo usa una cantidad fija de variables auxiliares (por ejemplo, índice, valor buscado, contador), por lo que su complejidad espacial es:

- $O(1)$

## 9. Resultado esperado del análisis

El problema requiere una solución simple, clara y correcta: una búsqueda lineal sobre un vector desordenado. Esa estrategia es la más adecuada porque no depende del orden de los elementos y garantiza la detección del valor o la confirmación de su ausencia.

Además, es necesario que el programa informe cuántas posiciones fueron recorridas, lo que permite evaluar el costo operativo del algoritmo y analizar mejor caso, peor caso y caso promedio.

## 10. Conclusión

El ejercicio se centra en la búsqueda secuencial en un arreglo desordenado, como ejemplo clásico de análisis de algoritmos. La solución óptima para esta condición es recorrer el vector elemento a elemento, justificando la estrategia por la ausencia de orden y calculando su complejidad en los distintos escenarios.
