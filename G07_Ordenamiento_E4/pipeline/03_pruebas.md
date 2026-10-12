# Reporte de pruebas — Selection Sort de nombres

## Alcance y criterio de comparación

Se validó `SelectionSortNombres.ordenarNombres` con su orden natural de Java: compara cadenas no nulas usando `String.compareTo()`. La comparación es lexicográfica y sensible a mayúsculas/minúsculas; no aplica reglas de diccionario configuradas por idioma o región. En el conjunto ASCII probado, las letras mayúsculas preceden a las minúsculas: por eso el orden de `{"ana", "Ana", "ANA", "ana"}` es `{"ANA", "Ana", "ana", "ana"}`. Las cadenas iguales no cambian su orden observable en este caso.

Para nombres con la misma inicial, `compareTo()` continúa comparando los caracteres siguientes. Así, `"Juan"` precede a `"Julio"` porque comparten `"Ju"` y `a` precede a `l`; la inicial común por sí sola no determina el resultado.

## Matriz de casos ejecutados

| ID | Caso | Entrada | Resultado esperado | Verifica |
|---|---|---|---|---|
| P-01 | Ejemplo del ejercicio | `{"Lucia", "Ana", "Pedro", "Juan"}` | `{"Ana", "Juan", "Lucia", "Pedro"}` | Ordenamiento principal in situ. |
| P-02 | Sensibilidad a mayúsculas/minúsculas | `{"ana", "Ana", "ANA", "ana"}` | `{"ANA", "Ana", "ana", "ana"}` | Orden natural sensible a caja de `compareTo()`. |
| P-03 | Misma inicial, dos nombres | `{"Julio", "Juan"}` | `{"Juan", "Julio"}` | Continúa la comparación después de la inicial común y del prefijo `"Ju"`. |
| P-04 | Grupo con inicial común | `{"Julio", "Jose", "Juan", "Julia"}` | `{"Jose", "Juan", "Julia", "Julio"}` | Orden lexicográfico de varios nombres que comienzan con J. |
| P-05 | Cadenas con relación de prefijo | `{"Juanita", "Juan", "Ju"}` | `{"Ju", "Juan", "Juanita"}` | La cadena más corta va primero si es prefijo de la otra. |
| P-06 | Valores duplicados | `{"Ana", "Ana", "Ana"}` | `{"Ana", "Ana", "Ana"}` | No se pierden ni alteran elementos iguales. |
| P-07 | Elementos nulos | `{"Zoe", null, "Ana", null}` | `{"Ana", "Zoe", null, null}` | Los nulos se ubican al final sin invocar `compareTo()` sobre ellos. |
| P-08 | Arreglo vacío | `{}` | `{}` | No hay accesos fuera de rango. |
| P-09 | Un elemento | `{"Juan"}` | `{"Juan"}` | Caso mínimo no vacío. |
| P-10 | Ya ordenado | `{"Ana", "Juan", "Lucia"}` | `{"Ana", "Juan", "Lucia"}` | Conserva el orden ya correcto. |
| P-11 | Orden inverso | `{"Pedro", "Lucia", "Juan", "Ana"}` | `{"Ana", "Juan", "Lucia", "Pedro"}` | Reordena completamente una entrada descendente. |
| P-12 | Referencia de arreglo nula | `null` | Lanza `NullPointerException` | Se rechaza la entrada inválida según el contrato. |

## Resultado de ejecución

Se compilaron `SelectionSortNombres.java` y un ejecutor temporal de comprobaciones, y se ejecutaron los doce casos. **Resultado: 12/12 aprobados.** El ejecutor temporal y los `.class` se eliminaron al terminar; no se añadieron dependencias.

Salida observada para los casos destacados:

```text
P-01 sample PASS [Ana, Juan, Lucia, Pedro]
P-02 case-sensitive PASS [ANA, Ana, ana, ana]
P-03 same initial PASS [Juan, Julio]
P-04 same initial group PASS [Jose, Juan, Julia, Julio]
TOTAL 12/12 PASS
```

## Conclusión

El método cumple con el orden lexicográfico natural de Java para mayúsculas, minúsculas, prefijos y nombres de inicial común. También respeta la política de nulidad: los elementos `null` quedan al final y un arreglo `null` produce `NullPointerException`. La prueba confirma el contrato elegido, no una ordenación lingüística independiente del locale.