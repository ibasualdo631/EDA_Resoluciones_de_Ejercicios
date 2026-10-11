# Plan técnico — Ejercicio 3: búsqueda binaria

## 1. Alcance y decisiones de diseño

Diseñar una búsqueda binaria iterativa para un `int[]` ordenado ascendentemente. La implementación será una clase de utilidad `BusquedaBinaria`, sin estado de instancia ni dependencias externas. El algoritmo no ordena ni modifica el vector.

Se adoptan estas decisiones para mantener un contrato único:

- Un vector vacío es válido y retorna `-1` sin iniciar iteraciones.
- Una referencia `null` es un argumento inválido: emitir un mensaje breve en `System.err` y lanzar `IllegalArgumentException`.
- Si existen duplicados, se retorna cualquier índice coincidente; no se promete la primera ni la última ocurrencia.
- El vector debe estar ordenado de forma ascendente. Es una precondición, no se verificará en tiempo de ejecución porque recorrerlo para validar el orden agregaría `O(n)` al costo de búsqueda.
- La traza por consola forma parte del requisito del ejercicio. Cada ciclo imprimirá los valores usados en la comparación y la decisión que sigue.

## 2. Clase y contrato público

**Archivo sugerido:** `BusquedaBinaria.java`. El directorio debe ajustarse al paquete o estructura que se adopte cuando exista un proyecto Java; en el estado actual del ejercicio no hay fuentes Java ni configuración de build que impongan otra distribución.

```java
public final class BusquedaBinaria {
    public static int buscar(int[] vector, int objetivo)
}
```

La clase puede tener constructor privado para indicar que es una utilidad estática.

### Contrato de `buscar`

**Precondiciones**

1. `vector != null`.
2. Los elementos están ordenados ascendentemente.

**Resultados**

- Devuelve `i`, con `0 <= i < vector.length` y `vector[i] == objetivo`, cuando hay coincidencia.
- Devuelve `-1` cuando no hay coincidencia, incluyendo `vector.length == 0`.
- Lanza `IllegalArgumentException` con mensaje claro si `vector == null`.
- No cambia el contenido ni la longitud del vector.
- Ante duplicados, cualquier índice que contenga el objetivo es válido.

El extremo vacío no requiere una rama excepcional: con `low = 0` y `high = -1`, la condición del bucle es falsa y el método retorna `-1`.

## 3. Organización y secuencia del algoritmo

Usar límites inclusivos y variables enteras locales:

1. Rechazar `null` antes de consultar `vector.length`; registrar el error en `System.err` y lanzar la excepción.
2. Inicializar `low = 0`, `high = vector.length - 1` y un contador de iteraciones para la telemetría.
3. Mientras `low <= high`:
   1. Calcular el centro de forma segura: `mid = low + (high - low) / 2`.
   2. Leer una sola vez `valorEvaluado = vector[mid]`.
   3. Registrar una fila de traza con límites, centro, valor evaluado y objetivo.
   4. Si `valorEvaluado == objetivo`, registrar `ENCONTRADO` y retornar `mid`.
   5. Si `objetivo < valorEvaluado`, actualizar `high = mid - 1`.
   6. En caso contrario, actualizar `low = mid + 1`.
   7. La fila o el mensaje asociado debe indicar explícitamente cuál límite se actualizó y su nuevo valor.
4. Si el bucle termina, registrar que el intervalo quedó vacío y retornar `-1`.

El cálculo seguro evita el posible overflow de `low + high`. Con `int[]`, los índices son no negativos, y la diferencia `high - low` del intervalo válido permanece dentro del rango de índices del arreglo.

## 4. Formato de la traza de consola

Imprimir una cabecera una vez y una fila por comparación. Formato tabular recomendado:

```text
Objetivo: 23 | Tamaño del vector: 7
Iteración | inicio | fin | medio | valorEvaluado | decisión / actualización
1         | 0      | 6   | 3     | 10            | objetivo > valor; inicio = 4
2         | 4      | 6   | 5     | 23            | ENCONTRADO
Resultado: índice 5
```

Requisitos de registro:

- Cada fila muestra los valores de `low` (`inicio`), `high` (`fin`) y `mid` (`medio`) tal como estaban inmediatamente antes de evaluar `vector[mid]`; también muestra ese valor.
- La decisión debe ser `ENCONTRADO`, `objetivo < valor; fin = medio - 1` o `objetivo > valor; inicio = medio + 1`, con el nuevo límite calculado.
- Mostrar el índice final al encontrar el objetivo; si se agota el intervalo, imprimir `Resultado: no encontrado (-1)`.
- Para un arreglo vacío, no hay filas de comparación; mostrar tamaño cero y el resultado `-1`.
- Para `null`, imprimir un diagnóstico identificable en `System.err` antes de lanzar `IllegalArgumentException`; nunca intentar leer longitud o elementos.
- Mantener exactamente una fila por comparación. No calcular índices ni leer elementos fuera del intervalo.

La salida por consola es un efecto requerido por la consigna del ejercicio, aunque una biblioteca general normalmente separaría la lógica del mecanismo de logging.

## 5. Matriz formal de complejidad

Sea `n = vector.length`; las complejidades temporales describen la búsqueda y el número de comparaciones, sin contar el costo externo de capturar o mostrar texto por consola.

| Medida | Complejidad | Condición / justificación |
|---|---:|---|
| Tiempo, peor caso | `O(log n)` | Cada comparación descarta el centro y al menos una mitad del intervalo candidato. |
| Tiempo, mejor caso | `Ω(1)`; más precisamente `Θ(1)` | El objetivo está exactamente en el primer `medio`, por lo que se necesita una sola comparación. |
| Tiempo, caso promedio | `Θ(log n)` | Bajo el modelo usual de búsquedas exitosas con posiciones igualmente probables; el número de niveles visitados crece logarítmicamente. |
| Espacio auxiliar | `O(1)` estricto | Versión iterativa con un número fijo de variables; no hay recursión ni estructuras auxiliares proporcionales a `n`. |

La llamada al método utiliza un marco de pila constante; por ello el espacio del algoritmo sigue siendo `O(1)`, no espacio literalmente nulo. La salida de consola no debe acumularse en memoria en una colección proporcional a la cantidad de iteraciones.

## 6. Verificación y criterios de aceptación

Cubrir como mínimo los siguientes resultados:

| Prueba | Entrada de ejemplo | Resultado esperado |
|---|---|---|
| Coincidencia en extremo inicial | `[2, 5, 8, 12, 16]`, objetivo `2` | `0` |
| Coincidencia en extremo final | `[2, 5, 8, 12, 16]`, objetivo `16` | `4` |
| Coincide con el medio inicial | `[2, 5, 8, 12, 16]`, objetivo `8` | `2`, una comparación |
| Ausente debajo del mínimo | `[2, 5, 8]`, objetivo `1` | `-1` |
| Ausente encima del máximo | `[2, 5, 8]`, objetivo `9` | `-1` |
| Ausente en rango intermedio | `[2, 5, 8, 12]`, objetivo `9` | `-1` |
| Un elemento coincidente | `[7]`, objetivo `7` | `0` |
| Un elemento no coincidente | `[7]`, objetivo `4` | `-1` |
| Vector vacío | `[]`, objetivo cualquiera | `-1`, cero comparaciones |
| Referencia nula | `null`, objetivo cualquiera | `IllegalArgumentException` |
| Duplicados | `[1, 3, 3, 3, 9]`, objetivo `3` | Índice entre `1` y `3` |

Aceptar la implementación solo si:

- Se respeta la firma `public static int buscar(int[] vector, int objetivo)`.
- Los casos encontrados retornan un índice dentro del vector cuyo valor es el objetivo; los casos ausentes retornan `-1`.
- El cálculo usa `low + (high - low) / 2`.
- Se usan límites inclusivos y el ciclo termina correctamente con `low > high`.
- No se altera el arreglo ni se exige ordenarlo dentro del método.
- La traza imprime una fila por comparación con los valores correctos de `inicio`, `fin`, `medio` y `valorEvaluado`, y muestra la decisión/actualización.
- Vacío y `null` siguen los contratos diferenciados indicados.
- El método es iterativo y no agrega dependencias externas.

## 7. Hyper-Prompt definitivo para el Agente Desarrollador

> Implementa en Java el Ejercicio 3, búsqueda binaria sobre un vector `int[]` ordenado ascendentemente. Antes de modificar archivos, revisa la estructura y convenciones existentes y utiliza el paquete/ruta que corresponda. Si no existe una estructura Java, crea únicamente los archivos mínimos necesarios para este ejercicio. No agregues dependencias externas ni implementes ordenamiento.
>
> Crea la clase de utilidad `BusquedaBinaria` con la firma exacta `public static int buscar(int[] vector, int objetivo)`. El método retorna un índice entre `0` y `n - 1` cuando encuentra el objetivo, o `-1` cuando no existe. No modifica el arreglo. Si hay duplicados, puede retornar cualquiera de las posiciones coincidentes.
>
> Usa búsqueda binaria iterativa con límites inclusivos (`low`/`inicio` y `high`/`fin`), comenzando en `0` y `vector.length - 1`; itera mientras `low <= high`. Calcula siempre el centro con `mid = low + (high - low) / 2`, nunca con `(low + high) / 2`. Lee el elemento central y compara: igualdad retorna `mid`; objetivo menor mueve `high = mid - 1`; objetivo mayor mueve `low = mid + 1`. Al agotar el intervalo retorna `-1`.
>
> Contrato de entradas: `null` es inválido; antes de consultar su longitud, imprime un diagnóstico claro en `System.err` y lanza `IllegalArgumentException` con mensaje descriptivo. Un arreglo vacío es válido y devuelve `-1` sin comparaciones. Asume que el vector ya está ordenado ascendentemente y no valides su orden recorriéndolo, porque eso convertiría el costo de búsqueda en `O(n)`.
>
> La traza en vivo por consola es obligatoria. Imprime una cabecera tabular y una fila por cada comparación con estas columnas: `Iteración`, `inicio`, `fin`, `medio`, `valorEvaluado`, `objetivo` y `decisión/actualización`. Los límites y el medio de la fila deben ser los valores usados para calcular y efectuar esa comparación. Después de comparar, indica `ENCONTRADO`, `objetivo < valor; fin = medio - 1` o `objetivo > valor; inicio = medio + 1`, incluyendo el nuevo valor del límite cuando corresponda. Al finalizar, imprime el índice encontrado o `no encontrado (-1)`. Para el arreglo vacío, informa tamaño cero y el resultado sin emitir filas de comparación. No acumules la traza en una colección.
>
> Incluye o adapta pruebas conforme al framework ya presente. Cubre: objetivo en índice `0`, objetivo en índice `n - 1`, coincidencia con el medio de la primera iteración, ausente menor que el mínimo, ausente mayor que el máximo, ausente entre elementos, vector unitario coincidente y no coincidente, vector vacío, `null` con excepción y duplicados. Verifica además que el vector no sea modificado y que la traza tenga una fila por comparación con límites/valor/decisión coherentes.
>
> En la explicación final documenta la precondición de orden, la terminación cuando `low > high`, el cálculo seguro del medio, el peor caso temporal `O(log n)`, mejor caso `Ω(1)` (más precisamente `Θ(1)`), promedio `Θ(log n)` bajo posiciones exitosas equiprobables, y espacio auxiliar `O(1)` por el enfoque iterativo. Ejecuta las pruebas o compilación disponibles y reporta el resultado real; no declares validación exitosa si no se ejecutó.