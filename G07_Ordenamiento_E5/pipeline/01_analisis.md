# Análisis — Ejercicio 5: ShellSort explicando los gaps

## Objetivo

Implementar ShellSort en Java y hacer visible su progreso: mostrar el valor de `gap` de cada etapa y el estado del arreglo al terminar cada pasada. La solución también debe explicar por qué los saltos decrecientes permiten ordenar más eficientemente que Insertion Sort cuando los elementos están lejos de su posición definitiva.

## Requerimientos funcionales

- Ordenar un arreglo de enteros en forma ascendente mediante ShellSort.
- Usar una secuencia decreciente de gaps; al no estar especificada en la consigna, se asume la secuencia clásica `n / 2`, reduciendo a la mitad en cada etapa hasta llegar a `1`.
- Para cada gap, aplicar insertion sort sobre los elementos de cada subsecuencia separada por ese gap.
- Mostrar el gap utilizado y el arreglo completo una vez finalizada esa pasada.
- No delegar el ordenamiento a `Arrays.sort` ni a otro algoritmo de biblioteca.
- Explicar con claridad el papel de los gaps y la relación con Insertion Sort.

## Fundamento conceptual

Insertion Sort mueve un elemento hacia su posición correcta comparándolo e intercambiándolo con elementos contiguos. Si un valor está muy lejos de donde debe quedar, puede necesitar muchos movimientos de una posición por vez; en arreglos grandes o muy desordenados, ese es su cuello de botella.

ShellSort empieza con un gap grande y ordena elementos de cada subsecuencia espaciada por ese gap. Así, un elemento puede desplazarse varias posiciones en una operación de inserción y acercarse rápidamente a su zona final, aunque todavía no quede en el lugar exacto. Después se reduce el gap y se refina el orden. Cuando el gap llega a `1`, el arreglo ya está parcialmente ordenado y la pasada final de Insertion Sort suele requerir muchos menos desplazamientos.

Los gaps no eliminan el trabajo de inserción: permiten realizar primero movimientos largos para reducir el desorden global, y dejan los movimientos cortos para el ajuste final.

## Alcance y supuestos

- La consigna no especifica formato de entrada, arreglo de ejemplo ni versión de Java. El prompt propone un arreglo de ejemplo explícito y Java estándar, sin requerir bibliotecas externas.
- “Luego de cada pasada” se interpreta como mostrar el arreglo una vez procesados todos los grupos correspondientes a un mismo valor de gap.
- No se exige medir tiempos ni afirmar una complejidad fija: el rendimiento de ShellSort depende de la secuencia de gaps.

## Prompt generado

> Implementá en Java un programa educativo que ordene un arreglo de enteros usando ShellSort, sin usar `Arrays.sort` ni delegar el ordenamiento a una biblioteca.
>
> Usá la secuencia clásica de gaps: empezá con `n / 2` y dividí el gap por dos en cada etapa hasta llegar a `1`. Para cada gap, recorré el arreglo aplicando una inserción sobre los elementos separados por esa distancia. Al completar la pasada de cada gap, imprimí el valor del gap usado y el estado completo del arreglo.
>
> Incluí un arreglo de ejemplo desordenado que permita observar los cambios entre pasadas. Escribí el código claro y comentá únicamente las partes que ayuden a entender cómo se inserta un elemento dentro de su subsecuencia.
>
> Acompañá la implementación con una explicación conceptual: Insertion Sort convencional solo desplaza un elemento una posición por vez, por lo que un elemento muy lejos de su posición correcta puede requerir muchos movimientos. Explicá que un gap grande permite moverlo varias posiciones de una vez dentro de una subsecuencia; los gaps decrecientes reducen gradualmente el desorden, y la pasada final con gap `1` funciona como Insertion Sort sobre un arreglo que ya está casi ordenado. No presentes esto como una garantía de complejidad independiente de la secuencia de gaps.
>
> Verificá el resultado con arreglos vacío, de un elemento, ya ordenado, en orden inverso, con duplicados y con valores negativos. Indicá brevemente qué se espera observar para cada caso.