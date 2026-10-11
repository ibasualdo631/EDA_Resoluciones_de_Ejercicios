# Explicación conceptual: encontrar el mínimo de un vector

## 1. La idea del algoritmo

El método `MinimoVector.encontrarMinimo(int[] vector)` devuelve el valor más pequeño del arreglo. Primero verifica que el arreglo no sea `null` ni vacío; después guarda el primer elemento como candidato y compara con él todos los elementos restantes:

```java
int minimo = vector[0];
for (int i = 1; i < vector.length; i++) {
    if (vector[i] < minimo) {
        minimo = vector[i];
    }
}
return minimo;
```

Por ejemplo, para `{8, 3, 6, -2, 5}`:

| Índice | Valor leído | `minimo` antes | `minimo` después |
|---:|---:|---:|---:|
| Inicial | 8 | — | 8 |
| 1 | 3 | 8 | 3 |
| 2 | 6 | 3 | 3 |
| 3 | -2 | 3 | -2 |
| 4 | 5 | -2 | -2 |

Al terminar, devuelve `-2`. La variable de seguimiento siempre contiene el menor valor visto hasta ese momento. Esa es la propiedad que permite razonar sobre la corrección: después de examinar el índice `i`, `minimo` es el menor valor del segmento `vector[0..i]`.

## 2. Qué ocurre en memoria: Stack y Heap

Para explicar la ejecución se suele usar el modelo didáctico de Stack (pila) y Heap (montículo). La especificación de Java no exige que una implementación concreta disponga físicamente cada dato exactamente así; la JVM puede optimizar la ejecución, por ejemplo manteniendo variables locales en registros.

En el modelo habitual:

- El arreglo `int[]` es un objeto creado en el Heap. Allí se encuentran sus elementos enteros, accesibles por índices desde `0` hasta `length - 1`.
- Al llamar `encontrarMinimo(vector)`, se crea un marco de ejecución para el método. El parámetro local `vector` contiene una referencia al arreglo; no se copia todo el arreglo.
- Las variables locales `minimo` e `i` pertenecen a la ejecución del método (conceptualmente, al Stack). `minimo` guarda un solo entero candidato y `i` el índice actual.
- En cada iteración, `vector[i]` accede al elemento del Heap indicado por la referencia y el índice. En el modelo RAM, el acceso por índice y la comparación de dos `int` cuestan `O(1)`.

La variable `minimo` no almacena todos los elementos ni una copia del vector: se actualiza cuando encuentra un valor menor. Por eso el método requiere espacio auxiliar constante, `O(1)`. El arreglo de entrada ocupa `O(n)` memoria, pero esa memoria ya existía y no se cuenta como espacio auxiliar adicional del algoritmo.

## 3. Por qué el algoritmo debe examinar todos los elementos

No hay una regla que permita descartar una posición sin leerla: cualquiera de los elementos que faltan podría ser menor que el candidato guardado. Esto sigue siendo cierto incluso si el mínimo actual parece muy pequeño o está en la primera posición.

Una justificación por indistinguibilidad:

1. Supongamos que un algoritmo no inspecciona una posición `j`.
2. Consideremos dos arreglos que coinciden en todas las posiciones que sí inspeccionó.
3. En el segundo arreglo, cambiamos solamente `vector[j]` por un entero menor que el candidato observado.
4. El algoritmo no puede distinguir los arreglos porque nunca leyó `j`, pero el mínimo verdadero sí cambia.
5. Por lo tanto, no puede garantizar una respuesta correcta en ambos casos.

En consecuencia, para cualquier arreglo válido el método debe inspeccionar las `n` posiciones. El bucle hace exactamente `n - 1` comparaciones: la primera posición inicializa el candidato y cada una de las otras posiciones se compara una vez. Si `n = 1`, el bucle no se ejecuta, pero sí se lee el único elemento y se devuelve.

### Dictamen de complejidad temporal

Sea `n` la cantidad de elementos:

| Caso | Cota | Demostración |
|---|---:|---|
| Mejor caso | `Ω(n)` | Aunque el primer elemento sea el mínimo, se deben inspeccionar todos los restantes para descartar que alguno sea menor. El número de inspecciones es `n`. |
| Peor caso | `O(n)` | El algoritmo nunca procesa más de `n` elementos y hace `n - 1` comparaciones. |
| Promedio | `Θ(n)` | La cantidad de trabajo es `n` inspecciones independientemente de dónde esté el mínimo o de cómo se distribuyan los valores. |

Juntas, las cotas inferior y superior muestran que el tiempo es `Θ(n)` (lineal). `Ω(n)` describe la cota inferior asintótica del mejor caso, mientras `O(n)` describe una cota superior del peor caso. La ubicación del mínimo no permite terminar antes: el algoritmo debe seguir hasta el final. No se debe confundir esta tarea con una búsqueda lineal que sí puede detenerse al encontrar un elemento buscado.

## 4. El ordenamiento previo: trabajo que no aporta

Una tentación es ejecutar `Arrays.sort(vector)` y luego leer `vector[0]`. Eso funciona para un arreglo no vacío, pero resuelve una tarea más amplia (ordenar todos los valores) cuando solo se necesita conocer uno. Un ordenamiento por comparaciones eficiente suele requerir `O(n log n)` tiempo; encontrar el mínimo con una pasada requiere `Θ(n)`. Para `n` grande, `n log n` crece más rápido que `n`, así que el ordenamiento añade trabajo sin mejorar el resultado.

Además, `Arrays.sort` ordena el arreglo recibido y, por tanto, cambia su orden. Si se quisiera conservar el original, habría que copiarlo y ordenar la copia, lo que agrega trabajo y memoria para la copia.

**Precisión importante sobre memoria:** no es correcto afirmar que todo ordenamiento necesariamente usa `O(n)` espacio adicional. El espacio auxiliar depende del algoritmo concreto y de su implementación. Por ejemplo, algoritmos distintos pueden usar arreglos auxiliares, recursión o particionar en el mismo arreglo. La comparación válida aquí es que la solución directa usa `O(1)` espacio auxiliar garantizado y evita el costo de ordenar; no que cualquier `Arrays.sort()` requiera universalmente `O(n)` espacio.

| Enfoque | Tiempo | Espacio auxiliar | ¿Modifica/ordena el vector? |
|---|---:|---:|---|
| Recorrido para hallar el mínimo | `Θ(n)` | `O(1)` | No |
| Ordenar y leer el primer elemento | Normalmente `O(n log n)` para ordenamientos eficientes por comparación | Depende del algoritmo y de la implementación | Sí, salvo que se ordene una copia |

La idea de ingeniería es elegir el algoritmo que calcula exactamente lo requerido con el menor trabajo: leer cada valor una vez, mantener el candidato mínimo y no reordenar datos innecesariamente.

## 5. Casos relevantes para razonar sobre corrección

- **Vector de un elemento:** ese elemento es el mínimo; el inicio en el índice `1` evita una lectura fuera de rango.
- **Valores solo positivos:** inicializar con `vector[0]` evita devolver incorrectamente cero.
- **Valores negativos o `Integer.MIN_VALUE`:** se comparan directamente; no hace falta sumar ni restar y, por lo tanto, no se introduce desbordamiento aritmético.
- **Mínimos repetidos:** devolver el valor mínimo no requiere distinguir cuál aparición lo produjo.
- **Arreglo `null` o vacío:** no existe un mínimo definido, así que el método falla explícitamente con `IllegalArgumentException` antes de acceder al arreglo.

## 6. Preguntas socráticas para el examen oral

1. Si el primer elemento es `Integer.MIN_VALUE`, ¿por qué el algoritmo todavía tiene que recorrer las demás posiciones, aunque ya sea el menor entero representable?
2. ¿Cómo construirías dos arreglos indistinguibles para un algoritmo que no leyó una posición, pero cuyo mínimo real es distinto? ¿Qué demuestra eso sobre una posible solución sublineal?
3. Si ordenáramos el arreglo antes de leer el primer elemento, ¿qué trabajo adicional haríamos y qué afirmación sobre el espacio auxiliar sería correcta sin conocer el algoritmo de ordenamiento concreto?
