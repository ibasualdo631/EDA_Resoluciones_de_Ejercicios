# Explicación breve: Bubble Sort

Bubble Sort compara elementos vecinos y los intercambia cuando están en el orden incorrecto. En cada pasada, el valor más grande del tramo todavía desordenado avanza hasta el final.

Es poco eficiente con muchos datos porque repite muchas comparaciones: su tiempo de ejecución crece aproximadamente como `n²`. Si se duplica la cantidad de elementos, el trabajo puede multiplicarse por cerca de cuatro. Por eso, para arreglos grandes conviene usar algoritmos más eficientes.

## Mejor y peor caso en esta implementación

- **Mejor caso:** el arreglo ya está ordenado. No se realizan intercambios, pero el código igualmente completa las pasadas previstas y hace `n(n - 1) / 2` comparaciones. Su tiempo es `O(n²)`.
- **Peor caso:** el arreglo está en orden inverso. Se realizan muchas comparaciones y hasta `n(n - 1) / 2` intercambios. Su tiempo también es `O(n²)`.

## Preguntas teóricas de examen

1. Si el arreglo ya está ordenado, ¿cuántas comparaciones realiza esta implementación y cuántos intercambios?
2. Si el arreglo está en orden inverso, ¿por qué se considera el peor caso y cómo crece la cantidad de trabajo al aumentar `n`?