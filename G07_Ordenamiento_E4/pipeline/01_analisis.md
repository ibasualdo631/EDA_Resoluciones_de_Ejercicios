# Análisis — Ejercicio 4: Ordenar nombres con Selection Sort

## Objetivo

Implementar Selection Sort para ordenar alfabéticamente un arreglo de nombres (`String[]`). En cada pasada se busca el menor nombre de la parte todavía no ordenada y se intercambia con el elemento que ocupa el comienzo de esa parte.

Ejemplo de entrada:

```java
String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"};
```

Resultado esperado bajo el orden natural de `String` en Java:

```text
Ana, Juan, Lucia, Pedro
```

## Requerimientos funcionales

- Recibir un arreglo de cadenas y ordenarlo en el mismo arreglo.
- Aplicar Selection Sort, no reemplazarlo por un método de biblioteca ni por otro algoritmo.
- Seleccionar el menor elemento restante en cada iteración.
- Comparar nombres lexicográficamente, recorriendo caracteres hasta encontrar una diferencia o llegar al final de una cadena.
- Intercambiar referencias del arreglo; no reconstruir ni modificar el contenido de los objetos `String`.
- Explicar el algoritmo con el ejemplo y distinguir la cantidad de comparaciones entre elementos del costo de comparar sus caracteres.

## Evaluación algorítmica: números primitivos frente a cadenas

Para `n` elementos, la implementación estándar hace exactamente `n(n-1)/2` comparaciones entre elementos, tanto si estos son números como si son cadenas. Por lo tanto, la cantidad de comparaciones del algoritmo sigue siendo Θ(n²), independientemente de que la entrada ya esté ordenada.

Con números primitivos, comparar dos valores suele modelarse como O(1), por lo que el tiempo total de Selection Sort es Θ(n²). Con cadenas, una comparación lexicográfica puede inspeccionar varios caracteres. Si `L` es la longitud máxima de los nombres, el peor caso cuesta O(n²·L) operaciones sobre caracteres; más precisamente, depende de los prefijos comunes de cada par comparado. Si las longitudes están acotadas por una constante, el costo vuelve a expresarse como Θ(n²) respecto de `n`.

La inmutabilidad de `String` **no** hace que la comparación por sí misma sea más costosa ni obliga a copiar el texto al ordenar. El arreglo contiene referencias a objetos inmutables: el intercambio reasigna dos referencias en O(1), y el espacio auxiliar sigue siendo O(1), aparte del arreglo de entrada. Si se construyeran cadenas nuevas para intercambiar o comparar, eso sería trabajo adicional innecesario.

## Supuestos y límites

- El ejemplo utiliza sintaxis Java (`String[]`), así que el prompt propone Java.
- “Alfabéticamente” se interpreta como el orden natural de `String` en Java (`compareTo`): comparación lexicográfica, sensible a mayúsculas y basada en unidades UTF-16. No equivale necesariamente al orden lingüístico local, especialmente con acentos o diferencias de mayúsculas/minúsculas. El ejercicio no especifica reglas de locale; no se agregan.
- Si la referencia del arreglo es `null`, el método debe rechazarla con `NullPointerException` mediante `Objects.requireNonNull`. Si hay elementos `null`, deben ubicarse al final; solo se debe llamar a `compareTo` cuando ambos nombres sean no nulos.
- Selection Sort estándar no es estable en general. La consigna no exige estabilidad.

## Casos de prueba recomendados

- El ejemplo dado.
- Arreglo vacío y arreglo de un solo nombre.
- Nombres ya ordenados y en orden inverso.
- Cadenas repetidas y cadenas donde una sea prefijo de otra (por ejemplo, `"Ana"` y `"Analia"`).
- Elementos `null` intercalados y múltiples `null`; verificar que queden al final. Verificar también que un arreglo nulo se rechace.
- Aclarar que mayúsculas y acentos siguen el orden natural de Java, no un colacionado regional.

## Prompt inicial

> Implementá en Java el algoritmo Selection Sort para ordenar alfabéticamente un arreglo `String[]` de nombres, modificando el arreglo recibido y sin usar métodos de ordenamiento de biblioteca. Compará nombres no nulos con `String.compareTo()`. Rechazá una referencia de arreglo `null` con `Objects.requireNonNull`; ubicá los elementos `null` al final y nunca invoques `compareTo()` con un operando nulo. Mostrá el código completo con el arreglo `{"Lucia", "Ana", "Pedro", "Juan"}` y su resultado ordenado.
>
> Después, explicá paso a paso cómo Selection Sort encuentra el menor nombre restante y cómo realiza cada intercambio. Analizá su costo distinguiendo: (1) la cantidad de comparaciones entre elementos, que es n(n-1)/2; (2) el costo de cada comparación lexicográfica de cadenas, que puede inspeccionar varios caracteres y en el peor caso es O(L), siendo L la longitud máxima; y (3) el costo de intercambio, que en Java mueve referencias y es O(1), sin copiar ni modificar los objetos `String`. Indicá el tiempo resultante (O(n²·L) en el peor caso) y el espacio auxiliar (O(1)). Aclarà que `String` es inmutable pero eso no elimina el costo de inspeccionar caracteres, y que `String.compareTo` no necesariamente coincide con el orden lingüístico local para mayúsculas o acentos. Incluí una breve traza del ejemplo y considerá nombres duplicados, prefijos y la política para valores nulos (arreglo nulo rechazado, elementos nulos al final).