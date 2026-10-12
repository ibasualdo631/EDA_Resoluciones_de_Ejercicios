# Análisis del Ejercicio 6: Quicksort con primer elemento como pivote

## 1. Objetivo del problema

Implementar el algoritmo Quicksort utilizando como pivote siempre el primer elemento del arreglo. El ejercicio busca comprender la lógica recursiva del algoritmo, la partición del problema y la forma en que se ordena un conjunto de datos mediante la estrategia de dividir y conquistar.

El propósito principal no es solo obtener un arreglo ordenado, sino también visualizar cómo se selecciona el pivote, cómo se separan los elementos menores y mayores, y cómo se reconstruye la solución final.

## 2. Contexto del ejercicio

El problema plantea que la programación debe mostrar:

- el pivote elegido,
- el subarreglo izquierdo,
- el subarreglo derecho,
- el arreglo final ordenado.

Esto permite observar en forma clara cómo evoluciona la ejecución del algoritmo y cómo se resuelven los subproblemas generados por la recursión.

## 3. Requerimientos funcionales

1. Seleccionar el primer elemento del arreglo como pivote.
2. Dividir el arreglo en dos subconjuntos:
   - elementos menores que el pivote,
   - elementos mayores o iguales que el pivote.
3. Aplicar recursivamente el mismo procedimiento a cada subarreglo.
4. Reconstruir la solución completa como:
   - izquierda + pivote + derecha.
5. Mostrar el estado del proceso para que el estudiante pueda seguir la ejecución.
6. Entregar como resultado final el arreglo ordenado.

## 4. Regla de negocio o lógica aplicada

La regla central del algoritmo es:

- el elemento pivote queda fijo en su posición final,
- todos los elementos menores quedan a la izquierda,
- todos los elementos mayores quedan a la derecha,
- y luego se repite la operación en cada subarreglo.

En términos de ordenamiento, el pivote sirve como punto de referencia para particionar el problema.

## 5. Casos límite a considerar

- Arreglo vacío.
- Arreglo de un solo elemento.
- Arreglo ya ordenado.
- Arreglo con elementos repetidos.
- Arreglo con valores negativos.
- Arreglo desordenado con varios tamaños distintos.

Estos casos son importantes porque la estrategia del pivote fijo puede comportarse de manera distinta según la distribución de los datos. En particular, si el arreglo ya está ordenado, esta variante del Quicksort puede degradar su eficiencia y tener peor rendimiento.

## 6. Relación con la estrategia de dividir y conquistar

El algoritmo Quicksort es un ejemplo clásico de la técnica de divide y conquistar.

### a) Divide
El problema original se divide en dos subproblemas más pequeños:

- un lado con elementos menores al pivote,
- otro lado con elementos mayores.

### b) Conquista
Cada subarreglo se resuelve recursivamente aplicando la misma estrategia.

### c) Combina
Cuando los subarreglos ya están ordenados, se combinan con el pivote en el centro:

- subarreglo izquierdo ordenado,
- pivote,
- subarreglo derecho ordenado.

Es decir, la solución final se construye a partir de soluciones parciales de los subproblemas.

## 7. Concepto pedagógico del ejercicio

Este ejercicio busca que el estudiante comprenda que:

- la recursión permite resolver un problema grande a partir de versiones más pequeñas del mismo,
- el pivote es el eje de la partición,
- la lógica de Quicksort no depende de ordenar todo de una vez, sino de dividir el problema en partes manejables,
- y la combinación final conserva el orden total del conjunto.

La visualización de las particiones explica la esencia del algoritmo: cada llamada recursiva reduce el tamaño del problema y lleva a una solución eficiente en la mayoría de los casos.

## 8. Observación importante sobre eficiencia

Aunque el ejercicio propone usar siempre el primer elemento como pivote, esta variante no siempre es la más conveniente. En arreglos ya ordenados o casi ordenados, la partición puede ser muy desbalanceada y el rendimiento puede acercarse a O(n²).

Esto no invalida el ejercicio, porque su objetivo es didáctico y conceptual: entender cómo funciona Quicksort y cómo se relaciona con dividir y conquistar, más que optimizar el pivote en todos los escenarios.

## 9. Resultado esperado del análisis

Se espera que la implementación:

- seleccione el pivote correctamente,
- particione el arreglo en dos grupos,
- muestre el proceso paso a paso,
- aplique recursión,
- y presente el arreglo ordenado al final.

## 10. Conclusión

El ejercicio 6 tiene un valor formativo importante porque ilustra una de las ideas centrales de la programación y el análisis de algoritmos: transformar un problema complejo en subproblemas más simples, resolverlos de manera recursiva y recomponer la solución final. Quicksort con pivote en el primer elemento es una variante clara y accesible para aprender este enfoque.
