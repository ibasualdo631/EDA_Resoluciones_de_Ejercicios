# Ejercicio 10 — Análisis de requisitos

## Objetivo

Definir una implementación Java de Bubble Sort para arreglos `int[]`, con
métricas observables y explicación de su costo y ámbito de uso.

## Requerimientos funcionales

- Ordenar los elementos de un arreglo de enteros en orden ascendente.
- Rechazar explícitamente una referencia `null` con
  `IllegalArgumentException`; procesar arreglos vacíos, de un elemento y ya
  ordenados.
- Contabilizar por separado las comparaciones entre elementos y los
  intercambios realizados.
- Dejar claro si el método ordena en el lugar y cómo expone los contadores.
- Incluir pruebas que permitan comprobar tanto el orden final como las métricas.

## Reglas y decisiones para el prompt

- Si el arreglo es `null`, lanzar `IllegalArgumentException` antes de iniciar
  el ordenamiento. No se genera un resultado de telemetría para una entrada
  inválida.
- Para arreglos vacíos o con un solo elemento, no hay trabajo de ordenamiento:
  retornar cero comparaciones y cero intercambios.
- Definir “comparación” como una comparación entre dos valores del arreglo, y
  “intercambio” como el intercambio efectivo de dos elementos; no contar
  comparaciones de índices ni intercambios innecesarios.
- Incluir una salida temprana cuando una pasada no realice intercambios. Así,
  un arreglo ya ordenado requiere una pasada de verificación (n - 1
  comparaciones y cero intercambios para n > 1).
- Usar Bubble Sort como algoritmo principal, sin delegar el ordenamiento a
  `Arrays.sort` ni a otra biblioteca.
- Explicar que el costo promedio y el peor caso son O(n²), y que por ello el
  algoritmo solo es adecuado para fines didácticos o conjuntos pequeños; la
  mejora de salida temprana permite un mejor caso Θ(n) (y, por tanto, Ω(n)),
  pero no elimina el costo cuadrático promedio ni de peor caso. El uso
  auxiliar debe ser O(1).

## Casos de prueba requeridos

1. Referencia `null`: lanzar `IllegalArgumentException`.
2. Arreglo vacío: permanece vacío; cero comparaciones e intercambios.
3. Un elemento: permanece igual; cero comparaciones e intercambios.
4. Arreglo ya ordenado con al menos dos elementos: queda igual; n - 1
   comparaciones y cero intercambios con salida temprana.
5. Arreglo desordenado, incluyendo valores negativos y repetidos: queda
   ascendente y los contadores reflejan las operaciones efectivas.
6. Arreglo en orden inverso: valida el caso cuadrático; con el esquema indicado
   se esperan n(n - 1)/2 comparaciones e intercambios.

## Fuera de alcance

No cambiar otros ejercicios. La entrega es el plan técnico y la instrucción
para implementar y validar Bubble Sort.