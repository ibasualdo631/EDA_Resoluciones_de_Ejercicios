# Pruebas — Ejercicio 8: suma de una matriz

## Convención de auditoría

Para esta tabla, `operaciones totales` cuenta una iteración del cuerpo del ciclo por cada celda evaluada. `lecturas` y `sumas acumuladas` siguen contando cada una una operación por celda; por eso ambos contadores son `N`, mientras que el total solicitado es `N` (no la suma de ambos contadores). En matrices rectangulares, `N = filas × columnas`. Para matrices irregulares, `N` es la suma de las longitudes de las filas.

## Casos ejecutados

| Caso | Matriz / entrada | Suma esperada | Celdas evaluadas | Operaciones esperadas | Resultado |
|---|---|---:|---:|---:|---|
| Cuadrada | `{{1, 2}, {3, 4}}` | 10 | 4 | 4 | Aprobado |
| Rectangular | `{{1, 2, 3, 4}, {5, 6, 7, 8}}` | 36 | 8 | 8 | Aprobado |
| Números negativos | `{{-5, 2}, {-3, -1}}` | -7 | 4 | 4 | Aprobado |
| Referencia null | `null` | No aplica | 0 | 0 antes de propagar el error | Aprobado: lanza `IllegalArgumentException` |

## Resultado de auditoría

Las tres matrices válidas produjeron la suma esperada y cumplieron `getOperacionesTotales() == N`. También se verificó que `getLecturas() == N` y `getSumas() == N`. Para `null`, el método lanzó `IllegalArgumentException` y el contador total quedó en cero.

Se ajustó `SumaMatriz.getOperacionesTotales()` para contar las celdas procesadas una vez, conforme al criterio solicitado. Los contadores de lecturas y sumas acumuladas permanecen disponibles por separado.

**Validación:** compilación con `javac` y ejecución de un arnés temporal con aserciones para los cuatro casos; todos aprobaron.