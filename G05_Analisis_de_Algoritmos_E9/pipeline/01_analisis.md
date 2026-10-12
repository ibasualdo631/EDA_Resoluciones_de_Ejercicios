# Análisis: máximo elemento de una matriz

## Objetivo

Definir una solución en Java que recorra una matriz de enteros y encuentre su
mayor elemento, explicando el recorrido por filas y columnas y justificando su
complejidad temporal y espacial.

## Requerimientos funcionales

- Recibir una matriz `int[][]` y devolver el mayor valor almacenado.
- Recorrer las filas con un índice `i` y los elementos de cada fila con un
  índice `j`, mediante ciclos anidados.
- Considerar filas de longitudes distintas: en Java las matrices pueden ser
  irregulares, por lo que el límite de `j` debe ser la longitud de la fila
  actual.
- Contemplar `null`, matrices sin filas, filas vacías y matrices cuyas filas
  estén todas vacías. En estos casos no existe un máximo; el plan debe definir
  y documentar un comportamiento explícito.
- Inicializar el máximo a partir de un elemento real, no con cero, para que el
  resultado sea correcto cuando todos los valores sean negativos.
- Explicar por qué, en el caso general, hay que examinar todos los elementos:
  cualquier elemento no examinado podría ser mayor que el máximo encontrado.
- Analizar el tiempo como `O(F + E)` para una matriz posiblemente irregular,
  donde `F` es la cantidad de filas y `E` la cantidad total de elementos.
  Para una matriz rectangular de `F` filas y `C` columnas, esto equivale a
  `O(F * C)`. El espacio auxiliar debe ser `O(1)`.

## Reglas y decisiones para el plan

- Se asume que el dominio de entrada es `int[][]`.
- Como política recomendada para entradas sin elementos —incluyendo `null`—,
  lanzar `IllegalArgumentException`, ya que el método no puede devolver un
  máximo válido. El plan debe mantener esta política consistente y explicarla.
- Una matriz con filas vacías pero al menos una fila no vacía sí es válida; se
  deben ignorar las filas vacías y recorrer todos los valores existentes.
- No se debe ordenar ni modificar la matriz.

## Prompt técnico inicial para el planificador

Planificá la implementación en Java de un algoritmo que encuentre y devuelva
el mayor elemento de una matriz `int[][]`. La solución debe recorrer la matriz
por filas y columnas usando ciclos anidados con índices `i` y `j`. Considerá
que las filas pueden tener longitudes distintas y usá la longitud de cada fila
como límite del ciclo interior. El método debe contemplar entradas `null` y
matrices sin elementos (sin filas, con filas vacías o con todas las filas
vacías); definí una política explícita y documentada para estos casos. Como
política recomendada, lanzá `IllegalArgumentException` cuando no exista ningún
elemento. Inicializá el máximo a partir del primer elemento encontrado para
que también funcione con valores negativos. No modifiques ni ordenes la matriz.
El plan debe incluir el contrato del método, pseudocódigo o pasos de
implementación, casos de prueba para entradas válidas y bordes, una explicación
de por qué el caso general requiere examinar todos los elementos, y el análisis
de complejidad: `O(F + E)` para matrices irregulares (equivalente a
`O(F * C)` para matrices rectangulares) y `O(1)` de espacio auxiliar.