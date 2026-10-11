# Pruebas y auditoría QA — Ejercicio 4

## Dictamen

**APROBADA para la entrega** según los requisitos del análisis y del plan técnico. La implementación devuelve los resultados esperados en los casos cubiertos, ambas estrategias mantienen paridad, rechazan `null` con `IllegalArgumentException`, y el código realiza corte anticipado al detectar una coincidencia.

## Matriz de casos de prueba

| ID | Caso | Vector / condición | Esperado en ambas soluciones | Resultado |
|---|---|---|---:|---|
| P01 | Negativos y positivos con duplicado | `[-3, 10, 5, -3]` | `true` | Aprobado |
| P02 | Vector ordenado sin duplicados | `[1, 2, 3, 4]` | `false` | Aprobado |
| P03 | Vector desordenado con múltiples duplicados | `[1, 2, 1, 2]` | `true` | Aprobado |
| P04 | Duplicado adyacente al inicio | `[4, 4, 2]` | `true` | Aprobado |
| P05 | Duplicado en los extremos | `[7, 3, 5, 7]` | `true` | Aprobado |
| P06 | Vector grande sin duplicados | Enteros de `0` a `999` (1.000 elementos) | `false` | Aprobado |
| P07 | Un elemento | `[9]` | `false` | Aprobado |
| P08 | Vector vacío | `[]` | `false` | Aprobado |
| P09 | Referencia nula en fuerza bruta | `null` | Lanza `IllegalArgumentException` | Aprobado |
| P10 | Referencia nula en HashSet | `null` | Lanza `IllegalArgumentException` | Aprobado |

La batería existente en `DetectorDuplicados.main` ejecuta ambas soluciones sobre cada vector válido, comprueba el resultado esperado de cada una (lo cual también verifica su paridad) y falla explícitamente con `AssertionError` ante una discrepancia. No depende de habilitar las aserciones de JVM con `-ea`.

## Auditoría del corte anticipado

- **Fuerza bruta:** `return true` está dentro de la condición de igualdad en el bucle interno, por lo que termina ambos ciclos en la primera coincidencia que encuentra.
- **HashSet:** `return true` ocurre inmediatamente cuando `vistos.add(valor)` devuelve `false`.
- Para `[1, 2, 1, 2]`, ambas funciones reportan el duplicado existente. El retorno inmediato se verifica además por inspección del flujo de control: como la API solo devuelve `boolean`, un test de caja negra no puede distinguir entre detenerse en la primera colisión y continuar recorriendo antes de devolver el mismo valor.

## Ejecución y resultado

Se compiló con `javac -Xlint:all` y se ejecutó `DetectorDuplicados.main`. Los nueve grupos de verificaciones impresos terminaron con `OK`; no se emitieron advertencias del compilador y el proceso finalizó con código de salida `0`.

El vector de 1.000 valores distintos fuerza el recorrido completo de ambas soluciones. En fuerza bruta implica comparar `1.000 × 999 / 2 = 499.500` pares, ilustrando el caso Θ(n²); en HashSet se procesan los 1.000 valores con costo O(n) promedio bajo la hipótesis de baja colisión.

## Observaciones

- Los métodos no modifican la entrada y validan `null` antes de leer su longitud o iterar.
- Los vectores de longitud cero o uno retornan `false` antes de entrar en los bucles o construir el conjunto.
- El contrato, los nombres y las estrategias implementadas coinciden con el plan técnico. No se encontraron defectos bloqueantes ni se requirieron correcciones.