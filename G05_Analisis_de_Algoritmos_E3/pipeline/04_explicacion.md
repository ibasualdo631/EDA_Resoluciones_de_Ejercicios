# Radiografía conceptual — Ejercicio 3: búsqueda binaria

## 1. Idea central del algoritmo

La búsqueda binaria localiza un objetivo dentro de un vector **ya ordenado ascendentemente**. Mantiene un intervalo de índices candidatos:

- `low` (`inicio`): primer índice que todavía podría contener el objetivo.
- `high` (`fin`): último índice que todavía podría contenerlo.
- `mid` (`medio`): índice central del intervalo.

En cada iteración compara `vector[mid]` con el objetivo. Si son iguales, termina. Si el objetivo es menor, todo lo que está en `mid` o a su derecha se puede descartar y se mueve `high` a `mid - 1`. Si el objetivo es mayor, se descarta `mid` y todo lo que está a su izquierda; `low` pasa a `mid + 1`.

La condición del ciclo es `low <= high`, porque ambos límites son inclusivos. Si llega a ser `low > high`, no queda ningún candidato y el método retorna `-1`. La implementación calcula el centro como:

```java
int mid = low + (high - low) / 2;
```

Esta forma evita el overflow que podría ocurrir al sumar directamente `low + high`.

## 2. Demostración matemática: por qué es O(log₂ n)

Sea `n` el número de elementos candidatos antes de una comparación. Una comparación cuesta tiempo constante `c` y, cuando el objetivo no se encuentra, se conserva a lo sumo la mitad del intervalo. El modelo de recurrencia es:

`T(n) = T(n/2) + c`, con `T(1) = c₀`.

Al expandir la recurrencia:

```text
T(n) = c + T(n/2)
     = 2c + T(n/4)
     = 3c + T(n/8)
     = ...
     = kc + T(n/2^k)
```

La expansión se detiene cuando queda como máximo un candidato:

`n / 2^k <= 1`.

Multiplicando por `2^k` y tomando logaritmo en base 2:

`2^k >= n`  ⇒  `k >= log₂(n)`.

Por ello, el número de comparaciones crece proporcionalmente a `log₂(n)` y el tiempo es `Θ(log₂ n)` en el caso de búsqueda que agota niveles. En notación de cota superior, el peor caso es `O(log₂ n)`; suele escribirse `O(log n)` porque cambiar la base del logaritmo solo multiplica por una constante.

Una forma equivalente de verlo es que tras `k` iteraciones quedan como máximo `n / 2^k` candidatos. El orden del vector permite inferir cuál mitad es imposible sin examinarla. Si el vector no está ordenado con el mismo criterio que las comparaciones, descartar una mitad puede eliminar la ubicación del objetivo y la garantía de corrección desaparece.

El mejor caso ocurre si el primer `mid` ya contiene el objetivo: una comparación, `Θ(1)` (y por tanto también `Ω(1)`). La búsqueda de un valor ausente sigue reduciendo el intervalo hasta que `low > high`.

## 3. Contraste de ingeniería: ¿ordenar para hacer una búsqueda?

Supongamos que el vector no está ordenado y solo se necesita buscar un objetivo una vez:

- Búsqueda lineal directa: como máximo se inspeccionan `n` elementos, costo `O(n)`.
- Ordenar primero y luego usar búsqueda binaria: ordenar cuesta `O(n log n)` (para QuickSort, `O(n log n)` promedio y peor caso `O(n²)` en sus variantes clásicas; `Arrays.sort(int[])` en Java usa una estrategia de ordenamiento para primitivos con rendimiento `O(n log n)`), y la búsqueda agrega `O(log n)`.

El costo total de ordenar y buscar una vez es `O(n log n) + O(log n) = O(n log n)`, que es asintóticamente mayor que recorrer directamente el vector una vez (`O(n)`). Además, si importa la posición original, ordenar el vector directamente puede cambiarla; habría que conservar índices o una copia, con trabajo y memoria adicionales.

### ¿Cuándo se amortiza ordenar?

Si el mismo conjunto de datos se va a consultar muchas veces y permanece sin cambios, se puede pagar el ordenamiento una vez y reutilizarlo. Para `q` consultas:

- Repetir recorrido lineal: `O(qn)`.
- Ordenar una vez y realizar búsquedas binarias: `O(n log n + q log n)`.

Ignorando constantes y suponiendo consultas comparables, el segundo enfoque conviene cuando:

`n log n + q log n < qn`.

Para `n` suficientemente grande, esto requiere aproximadamente `q` del orden de `log n` o más; el punto de equilibrio exacto depende de los costos constantes, la distribución de los objetivos, el algoritmo de ordenamiento y cuánto cambia el conjunto. Si el vector ya está ordenado, el costo inicial desaparece. Si los datos cambian frecuentemente, hay que incluir el costo de mantener el orden; inserciones en un arreglo ordenado pueden costar `O(n)` por los desplazamientos, y entonces el beneficio puede no compensar. También puede elegirse una estructura de datos de búsqueda adecuada si hay muchas actualizaciones.

**Resumen de la decisión:** para una sola consulta sobre datos arbitrarios, recorrer directamente suele ser mejor; ordenar se vuelve atractivo cuando se reutiliza el orden en suficientes consultas o cuando la ordenación ya era necesaria por otros requisitos.

## 4. Análisis de memoria: iteración y recursión

La búsqueda de esta solución es iterativa. Mantiene un número fijo de variables (`low`, `high`, `mid`, el valor central y el contador de pasos). No crea arreglos ni estructuras que crezcan con `n`, por lo que el espacio auxiliar del algoritmo es `O(1)`.

Una versión recursiva también reduce el problema a la mitad, así que su profundidad es `O(log n)`. Sin embargo, cada llamada debe conservar su marco de ejecución en el *Call Stack* (parámetros, variables locales y dirección de retorno). En consecuencia, la versión recursiva consume `O(log n)` espacio adicional de pila, mientras que la iterativa no crea una cadena de llamadas y conserva `O(1)` espacio auxiliar.

La pila de la llamada inicial al método existe siempre, pero ocupa una cantidad constante; por eso no cambia la clasificación. La salida de telemetría se imprime directamente, sin acumular todas las filas en una lista, así que no añade memoria proporcional al número de iteraciones. Las cadenas temporales de formato tampoco permanecen acumuladas a lo largo de toda la búsqueda.

## 5. Cómo leer la traza de `BusquedaBinaria`

Una fila como:

```text
[Paso 2] inicio: 3 | fin: 4 | medio: 3 | valor medio: 12 | comparativa con 16: objetivo > valor; inicio = 4
```

describe el intervalo **antes** de la comparación. El valor central es `12`; como el objetivo `16` es mayor, el siguiente intervalo comienza en `medio + 1`, es decir, `[4, 4]`. Si después el algoritmo encuentra el objetivo, retorna ese índice. Si no lo encuentra, las actualizaciones terminan con `inicio > fin` y la línea `Intervalo agotado` muestra ese intervalo vacío.

La traza es útil para aprender y verificar la ejecución. En una biblioteca de producción, normalmente se haría configurable o se usaría un logger, ya que imprimir en cada búsqueda mezcla la lógica algorítmica con la presentación y la consola puede dominar el tiempo observado. Las cotas anteriores describen las comparaciones del algoritmo, no el costo de renderizar la salida.

## 6. Preguntas socráticas para el examen oral

1. **Si al comparar el medio encuentras un valor mayor que el objetivo, ¿por qué es correcto descartar no solo el propio `medio`, sino también toda la mitad derecha? ¿Qué propiedad del vector necesitas para sostener esa conclusión?**
2. **Con un vector de 31 elementos, ¿cuál sería el máximo aproximado de comparaciones en una búsqueda exitosa y qué relación tiene ese número con `log₂(31)`? ¿Por qué duplicar `n` no duplica el trabajo?**
3. **Si se realizan muchísimas consultas sobre un vector que cambia después de cada búsqueda, ¿ordenarlo una sola vez sigue amortizando el costo? ¿Qué costos de actualización habría que incluir para decidir entre ordenar, recorrer o usar otra estructura?**