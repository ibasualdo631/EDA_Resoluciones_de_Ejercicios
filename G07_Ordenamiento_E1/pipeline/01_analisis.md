# Análisis — Ejercicio 1: Ordenamiento burbuja paso a paso

## Consigna interpretada

Implementar en Java un programa que ordene un arreglo de números enteros mediante Bubble Sort. El programa debe mostrar primero el arreglo original y luego su estado al finalizar cada pasada. La explicación debe aclarar por qué el elemento mayor del tramo todavía no ordenado se desplaza hacia el final.

## Objetivo

Practicar el recorrido de un arreglo, la comparación de elementos adyacentes y el intercambio condicional, haciendo visible el avance del algoritmo después de cada pasada.

## Requerimientos funcionales

1. Mantener un arreglo de números enteros como entrada del algoritmo.
2. Mostrar el arreglo inicial antes de ordenarlo.
3. Ordenar el arreglo de menor a mayor con Bubble Sort, comparando pares adyacentes e intercambiándolos cuando estén en orden descendente.
4. Mostrar el arreglo completo inmediatamente después de cada pasada del algoritmo.
5. Reducir el tramo que se compara en cada pasada, porque el mayor elemento de ese tramo queda colocado en su posición final.
6. Al terminar, dejar el arreglo ordenado de menor a mayor.

## Explicación conceptual del desplazamiento

En una pasada se avanza de izquierda a derecha por el tramo que aún puede estar desordenado. En cada par adyacente, si el elemento de la izquierda es mayor que el de la derecha, se intercambian. Por eso, el mayor valor encontrado hasta ese punto va avanzando una posición hacia la derecha cada vez que se compara con un valor menor. Al llegar al extremo del tramo, ese valor ocupa la última posición todavía no ordenada.

Así, tras la primera pasada, el máximo del arreglo queda al final; tras la segunda, el máximo de las posiciones restantes queda justo antes; y el mismo razonamiento se repite. Los elementos que ya quedaron al final no necesitan volver a compararse, por lo que el rango de comparación se reduce en una posición por pasada.

## Reglas y decisiones de comportamiento

- Una **pasada** consiste en completar el recorrido de comparaciones adyacentes del tramo activo, no en realizar un único intercambio.
- La impresión de cada estado debe ocurrir al terminar la pasada correspondiente, no después de cada comparación individual.
- Para conservar el carácter estable de Bubble Sort, intercambiar solo cuando el elemento izquierdo sea estrictamente mayor; los valores iguales no se permutan.
- Si el arreglo tiene cero o un elemento, ya está ordenado y no hay pasadas que mostrar.
- La consigna no especifica el origen de los datos ni el formato exacto de salida. Para el prompt inicial se asume un arreglo de ejemplo definido en el programa y una representación legible en consola; se evita agregar entrada interactiva no solicitada.
- Se asume que se muestran las pasadas del algoritmo hasta completar el ordenamiento. No se requiere optimización por terminación anticipada en una pasada sin intercambios.

## Casos relevantes para verificar

- Arreglo desordenado en orden inverso: cada pasada debe ubicar el mayor restante al final de su tramo activo.
- Arreglo ya ordenado: cada estado impreso debe permanecer igual al original.
- Valores repetidos: deben conservarse todos y no se exige reordenar entre sí valores iguales.
- Arreglo vacío o unitario: debe mostrarse el estado inicial y finalizar sin intentar una pasada.
- Después de cada pasada, el sufijo ya colocado al final debe permanecer ordenado y no volver a formar parte de las comparaciones.

## Complejidad esperada

Bubble Sort usa tiempo O(n²) en el caso general y espacio auxiliar O(1) si ordena el arreglo en el lugar. Estas características son informativas; no requieren estructuras adicionales.

## Prompt inicial formal

> Implementa en Java un programa que ordene un arreglo de números enteros de menor a mayor utilizando el algoritmo Bubble Sort. Declara en el programa un arreglo de ejemplo, ya que no se especifica entrada interactiva. Antes de ordenar, imprime el arreglo original. Luego, al finalizar cada pasada completa, imprime el estado de todo el arreglo. En cada pasada compara elementos adyacentes e intercámbialos únicamente cuando el de la izquierda sea mayor que el de la derecha. Explica en comentarios breves o en una explicación junto al código por qué el mayor elemento del tramo no ordenado “flota” hacia su extremo derecho: cada intercambio lo desplaza una posición a la derecha hasta quedar en su posición definitiva, lo que permite reducir el rango de comparación en la pasada siguiente. Considera arreglos vacíos, unitarios, ordenados y con valores repetidos; no agregues entrada interactiva ni optimización de terminación anticipada. Mantén la solución clara y adecuada para una práctica introductoria de estructuras de datos.