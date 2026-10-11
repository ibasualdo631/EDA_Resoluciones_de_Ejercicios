# Informe de pruebas — Ejercicio 3: búsqueda binaria

## Alcance de la auditoría

Se revisó `BusquedaBinaria.java`, se compiló con `javac` y se ejecutó el banco de pruebas del método `main`. Se inspeccionó también la coherencia entre las filas de traza, las decisiones de comparación y los límites inclusivos del algoritmo.

## Matriz de casos de prueba

| ID | Caso | Vector | Objetivo | Resultado esperado | Resultado observado |
|---|---|---|---:|---|---|
| BB-01 | Presente en la primera división (mejor caso) | `[2, 5, 8, 12, 16]` | `8` | Índice `2`; una comparación | Aprobado: devuelve `2`; primera fila `inicio=0, fin=4, medio=2, valor=8`. |
| BB-02 | Presente en extremo izquierdo | `[2, 5, 8, 12, 16]` | `2` | Índice `0` | Aprobado: rangos `[0,4]` → `[0,1]`; devuelve `0`. |
| BB-03 | Presente en extremo derecho | `[2, 5, 8, 12, 16]` | `16` | Índice `4` | Aprobado: rangos `[0,4]` → `[3,4]` → `[4,4]`; devuelve `4`. |
| BB-04 | Ausente, menor que el mínimo | `[2, 5, 8]` | `1` | `-1` | Aprobado: `[0,2]` → `[0,0]` → intervalo vacío `[0,-1]`. |
| BB-05 | Ausente, mayor que el máximo | `[2, 5, 8]` | `9` | `-1` | Aprobado: `[0,2]` → `[2,2]` → intervalo vacío `[3,2]`. |
| BB-06 | Ausente, intercalado entre valores | `[2, 5, 8, 12]` | `9` | `-1` | Aprobado: `[0,3]` → `[2,3]` → `[3,3]` → intervalo vacío `[3,2]`. |
| BB-07 | Un elemento, coincidencia | `[7]` | `7` | Índice `0` | Aprobado: una comparación en `[0,0]`; devuelve `0`. |
| BB-08 | Un elemento, no coincidencia | `[7]` | `4` | `-1` | Aprobado: `[0,0]` → intervalo vacío `[0,-1]`. |
| BB-09 | Vector vacío | `[]` | `4` | `-1`, cero comparaciones | Aprobado: no evalúa ningún medio; muestra rango terminal `[0,-1]`. |
| BB-10 | Referencia nula | `null` | `4` | `IllegalArgumentException` | Aprobado: registra el error en `System.err` y lanza la excepción antes de leer la longitud. |

## Revisión de la traza y los límites

En cada iteración, la traza muestra el rango vigente antes de comparar, el `medio`, el valor leído y la dirección de actualización. Cuando el objetivo es menor que el valor central, se establece `fin = medio - 1`; cuando es mayor, `inicio = medio + 1`. Las filas observadas son coherentes con esas transiciones y ningún medio queda fuera del rango vigente.

Durante la auditoría se reforzó el cierre de las búsquedas fallidas para imprimir explícitamente `inicio` y `fin` una vez agotado el intervalo. Así queda visible la convergencia final (`inicio > fin`), incluso para el arreglo vacío, en vez de depender únicamente del texto `no encontrado`.

La traza no contiene una fila de comparación para el intervalo terminal, porque en ese punto ya no existe un índice candidato; el rango vacío se informa en una línea de cierre. En caso de éxito, la traza finaliza en la fila donde el valor central coincide con el objetivo.

## Evidencia de ejecución

- Compilación: `javac -encoding UTF-8 -d <directorio-temporal> BusquedaBinaria.java` — exitosa.
- Ejecución: `java -cp <directorio-temporal> BusquedaBinaria` — exitosa.
- Resultado: los diez casos del banco de pruebas concluyeron con `PRUEBA OK`; el proceso terminó sin `AssertionError`.
- Se comprobó en las pruebas incluidas que el algoritmo no modifica los arreglos.

## Veredicto de calidad

**Aprobado para la entrega de la cátedra, con observación menor resuelta.** La implementación satisface el contrato de retorno, maneja los bordes requeridos, utiliza búsqueda binaria iterativa y cálculo seguro del medio, y aporta evidencia de la evolución del intervalo. Se incorporó la impresión del intervalo vacío terminal para hacer inequívoca la convergencia cuando el elemento no existe.

La búsqueda presupone un vector ordenado ascendentemente; no valida este orden en tiempo de ejecución. Esto es consistente con el contrato y evita introducir un recorrido `O(n)` antes de la búsqueda.