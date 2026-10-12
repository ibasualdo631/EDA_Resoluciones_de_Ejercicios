# Análisis del Ejercicio 8: MergeSort paso a paso

## Objetivo

Implementar `MergeSort` en Java para que el programa muestre claramente:

- cómo se divide el arreglo;
- cuándo llega a la condición de corte;
- cómo se van fusionando los subarreglos.

El objetivo principal no es solo ordenar, sino comprender la lógica del algoritmo y la diferencia entre sus dos fases: división recursiva y fusión.

## Diferencia fundamental entre división y fusión

### 1) Fase de división recursiva

La división es la parte que “rompe” el problema original en problemas más pequeños.

- Se toma el arreglo completo.
- Si tiene más de un elemento, se divide en dos mitades.
- Se vuelve a llamar recursivamente a `MergeSort` sobre cada mitad.
- Esto continúa hasta que cada subarreglo tiene tamaño 1 o 0.

En esta fase no se ordena todavía el contenido de forma global. Lo que se hace es descomponer el problema hasta llegar a la mínima unidad posible. Es decir: la recursión prepara el terreno para que luego cada parte pueda combinarse en orden.

La condición de corte es clave:

- si el arreglo tiene 0 o 1 elemento, ya está ordenado;
- por eso la recursión deja de dividir.

### 2) Fase de fusión (merge)

La fusión es la parte que “reconstruye” la solución ordenada a partir de los pedazos ya resueltos.

- Cuando las mitades más pequeñas ya están resueltas, se comparan sus elementos.
- Se toma el menor de los dos primeros elementos de cada mitad y se guarda en un arreglo auxiliar.
- Se repite este proceso hasta ordenar completamente ambos subarreglos.
- Finalmente, el arreglo auxiliar se copia de vuelta al arreglo original.

Aquí sí ocurre la verdadera ordenación: se combinan los elementos de manera ordenada, asegurando que el resultado final quede ordenado de menor a mayor.

## En pocas palabras

- La división recursiva se encarga de reducir el problema.
- La fusión se encarga de recomponerlo de forma ordenada.

La idea central de `MergeSort` es: primero dividir hasta llegar a elementos indivisibles, y luego combinar esos elementos de manera ordenada hasta reconstruir el arreglo completo.

## Prompt inicial sugerido

```text
Implementá en Java un programa que muestre paso a paso el funcionamiento de MergeSort sobre un arreglo de enteros.

Requisitos:
1. El programa debe dividir el arreglo recursivamente en mitades.
2. Debe mostrar cuándo llega a la condición de corte (arreglo de tamaño 0 o 1).
3. Debe mostrar cómo se van formando los subarreglos.
4. Debe mostrar la fase de fusión (merge), comparando elementos de cada mitad y ordenándolos.
5. La salida debe ser clara y legible para entender la diferencia entre:
   - división recursiva;
   - fusión final.

Importante:
Explicá y visualizá en consola cada paso, no solo el resultado final.

Ejemplo de entrada: [38, 27, 43, 3, 9, 82, 10]

La solución debe estar bien documentada y usar una estructura clara en Java.
```

## Conclusión

`MergeSort` es un algoritmo de divide y vencerás. Su lógica se basa en dos fases complementarias:

- dividir el problema en partes más pequeñas;
- fusionar esas partes ya ordenadas para obtener el arreglo final ordenado.

Es precisamente esta diferencia entre “dividir” y “fusionar” la que debe quedar clara al momento de explicar el ejercicio.
