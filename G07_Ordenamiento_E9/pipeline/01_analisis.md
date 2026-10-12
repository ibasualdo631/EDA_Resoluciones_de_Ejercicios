# Análisis: comparación de algoritmos simples de ordenamiento

## Objetivo

Implementar en Java Bubble Sort, Selection Sort e Insertion Sort para ordenar copias idénticas de un mismo arreglo. La solución debe permitir contrastar el funcionamiento interno y las métricas de cada algoritmo, además de comprobar que los tres producen el mismo resultado ordenado.

## Usuario y contexto

El usuario principal es un estudiante de Estructuras de Datos y Algoritmos. El programa tiene un propósito didáctico: hacer visibles las diferencias entre las estrategias de ordenamiento, no solo mostrar que los valores quedan ordenados.

## Requerimientos funcionales

1. Definir un arreglo inicial desordenado y conservarlo sin modificar.
2. Crear una copia independiente del arreglo inicial para cada algoritmo.
3. Ordenar las tres copias de forma ascendente, usando las implementaciones propias de Bubble Sort, Selection Sort e Insertion Sort.
4. Registrar por separado para cada método:
   - las comparaciones entre elementos realizadas para decidir el orden;
   - los intercambios o desplazamientos efectuados.
5. Mostrar el arreglo ordenado por cada método y sus métricas, de forma que puedan compararse fácilmente.
6. Mantener el mismo arreglo de entrada para los tres métodos y verificar que los resultados coincidan.

## Reglas y criterios de medición

- Cada comparación entre valores del arreglo que determine el orden cuenta como una comparación. No se cuentan las comprobaciones de índices o condiciones de bucle.
- Para Bubble Sort y Selection Sort, contar cada intercambio real entre dos posiciones como un movimiento; no contar intercambios consigo mismo.
- Para Insertion Sort, contar cada desplazamiento de un elemento hacia la derecha como un movimiento. No contar la lectura o guardado temporal de la clave como desplazamiento.
- Informar estas definiciones junto a los resultados o en la explicación para evitar comparar métricas incompatibles.
- Las copias deben ser independientes para que un algoritmo no reciba como entrada el resultado ya ordenado por otro.

## Casos y límites relevantes

- Arreglo vacío y arreglo con un elemento: deben permanecer sin cambios y no provocar errores.
- Arreglo ya ordenado: permite observar el mejor comportamiento de Insertion Sort y de Bubble Sort con corte temprano, si se implementa esa optimización.
- Arreglo invertido y valores repetidos: ayudan a observar movimientos e identificar errores en el ordenamiento.
- El arreglo de prueba debe ser idéntico para los tres métodos; elegir un único arreglo fijo es suficiente para el ejercicio y mantiene la salida reproducible.

## Fuera de alcance

- No se requiere entrada interactiva, interfaz gráfica, benchmarking de tiempo ni uso de las funciones de ordenamiento de bibliotecas.
- No se deben presentar conclusiones generales de rendimiento a partir de una sola ejecución; las conclusiones deben relacionarse con el arreglo usado y la estrategia de cada algoritmo.

## Prompt base

Implementá en Java un programa didáctico que compare Bubble Sort, Selection Sort e Insertion Sort. Usá un único arreglo inicial desordenado y ordená copias independientes e idénticas de ese arreglo, en orden ascendente, con cada algoritmo implementado manualmente. Para cada método, mostrá el arreglo ordenado, la cantidad de comparaciones entre valores y la cantidad de intercambios o desplazamientos. Definí y aplicá una métrica consistente: contá cada comparación entre elementos usada para decidir su orden; en Bubble Sort y Selection Sort contá solo los intercambios reales entre posiciones distintas; en Insertion Sort contá cada desplazamiento de un elemento hacia la derecha, sin contar la variable temporal que guarda la clave. Presentá al final una tabla comparativa y explicá brevemente las diferencias entre las estrategias. Asegurate de que los tres métodos reciban los mismos valores y produzcan el mismo arreglo ordenado. No uses métodos de ordenamiento de bibliotecas ni agregues entrada interactiva o mediciones de tiempo. Incluí un caso de prueba reproducible y contemplá arreglos vacíos, de un elemento, ya ordenados, invertidos y con valores repetidos.