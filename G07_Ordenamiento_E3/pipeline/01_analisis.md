# Análisis — Ejercicio 3: Insertion Sort con arreglo casi ordenado

## Objetivo

Implementar Insertion Sort sobre el arreglo ` {1, 2, 3, 5, 4, 6, 7} `, mostrar el arreglo ordenado y reportar cuántos desplazamientos realiza el algoritmo. La solución también debe explicar por qué Insertion Sort es eficiente cuando el arreglo ya está casi ordenado.

## Requerimientos funcionales

- Usar Insertion Sort, insertando cada elemento en su posición dentro del prefijo previamente ordenado.
- Procesar el arreglo de ejemplo `{1, 2, 3, 5, 4, 6, 7}`.
- Mostrar el arreglo al terminar, que debe ser `{1, 2, 3, 4, 5, 6, 7}`.
- Mostrar la cantidad de desplazamientos que realiza el algoritmo.
- Explicar el costo del algoritmo en función del estado inicial de los datos y distinguir este caso del peor caso general.

## Definición para contar desplazamientos

Contar un desplazamiento cada vez que un elemento existente se copia una posición a la derecha para abrir espacio a la clave (`arreglo[j + 1] = arreglo[j]`). No contar como desplazamiento la escritura final de la clave en su posición.

Con esa definición, al insertar el `4` se mueve el `5` una posición a la derecha. Los demás elementos se insertan sin mover elementos existentes. El total esperado para el arreglo dado es **1 desplazamiento**.

## Análisis de rendimiento

El arreglo tiene una sola inversión: el par `(5, 4)`, que está en orden contrario al ascendente. Insertion Sort recorre los elementos y, en cada iteración, compara la clave con el final del prefijo ordenado. Para los elementos que ya están en su sitio basta una comparación y no hay desplazamientos; al insertar `4`, solo se desplaza `5`.

El trabajo de Insertion Sort puede expresarse como `O(n + I)`, donde `I` es la cantidad de inversiones que deben corregirse mediante desplazamientos. Para esta familia de entradas con una cantidad constante y pequeña de inversiones, el tiempo es lineal, `Θ(n)`. El algoritmo es óptimo en ese contexto porque necesita inspeccionar los elementos para confirmar su orden y lo hace con una cantidad lineal de trabajo. Esto no significa que Insertion Sort sea lineal para cualquier entrada casi ordenada: si hay muchas inversiones, los desplazamientos pueden crecer hasta `Θ(n²)`, que es su peor caso general.

## Suposiciones y alcance

- Se asume orden ascendente.
- La notación `int[]` del enunciado sugiere Java; el prompt pide usar Java.
- El programa debe informar los desplazamientos según la definición anterior. No se requiere cronometrar el algoritmo ni comparar implementaciones.
- La explicación debe referirse a la entrada concreta y evitar afirmar que el mejor comportamiento se cumple para todo arreglo descrito vagamente como “casi ordenado”.

## Prompt redactado

> Implementá en Java el algoritmo **Insertion Sort** para ordenar en forma ascendente el arreglo `int[] numeros = {1, 2, 3, 5, 4, 6, 7};`.
>
> El programa debe mostrar el arreglo ordenado y la cantidad de desplazamientos realizados. Para este ejercicio, contá un desplazamiento cada vez que un elemento existente se mueve una posición a la derecha para dejar lugar a la clave (`numeros[j + 1] = numeros[j]`); no cuentes la escritura final de la clave como desplazamiento.
>
> Después del código, explicá paso a paso cómo se procesa el `4`, cuál es el total de desplazamientos esperado y por qué Insertion Sort tiene un rendimiento cercano a `O(n)` con esta entrada. Relacioná el costo con la cantidad de inversiones: este arreglo tiene una sola (`5` antes de `4`), así que solo se necesita un desplazamiento y el resto de los elementos ya está en su posición relativa. Aclará que Insertion Sort puede llegar a `O(n²)` en el peor caso general, y que “casi ordenado” no garantiza por sí solo tiempo lineal si existen muchas inversiones.
>
> Mantené la solución sencilla, legible y adecuada para una persona que está aprendiendo ordenamiento por inserción.