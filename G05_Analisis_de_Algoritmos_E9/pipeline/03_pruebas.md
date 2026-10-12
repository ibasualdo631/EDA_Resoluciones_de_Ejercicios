# Pruebas y auditoría: `ElementoMayorMatriz`

## Alcance

Se auditó `buscarMayor` y se amplió el `main` para mostrar una tabla con el
valor esperado, el resultado obtenido y el estado de cada caso. Si un caso no
cumple su expectativa, el programa termina con `AssertionError` en lugar de
mostrar un resultado exitoso.

La implementación valida `null`, ignora filas `null` o vacías, recorre cada
fila con su propia longitud y arranca el máximo desde el primer valor real.
No se detectaron defectos funcionales en el método durante estos casos.

## Ejecución

Compilación y ejecución manual:

```text
javac -d <directorio-temporal> ElementoMayorMatriz.java
java -cp <directorio-temporal> ElementoMayorMatriz
```

Resultado observado: **11 casos aprobados, 0 fallidos**.

| Caso | Esperado | Obtenido | Estado |
|---|---:|---:|---|
| Matriz rectangular 3x3 | 12 | 12 | PASO |
| Matriz irregular | 9 | 9 | PASO |
| Números negativos y positivos | 11 | 11 | PASO |
| Todos los elementos iguales | 5 | 5 | PASO |
| Referencia `null` | `IllegalArgumentException` | `IllegalArgumentException` | PASO |
| Matriz sin filas | `IllegalArgumentException` | `IllegalArgumentException` | PASO |
| Filas vacías sin elementos | `IllegalArgumentException` | `IllegalArgumentException` | PASO |
| Filas `null` sin elementos | `IllegalArgumentException` | `IllegalArgumentException` | PASO |
| Filas vacías/null junto con datos | 7 | 7 | PASO |
| Solo valores negativos | -2 | -2 | PASO |
| `Integer.MIN_VALUE` | -2147483648 | -2147483648 | PASO |

## Observación de la auditoría

Antes de esta revisión, las pruebas del `main` imprimían resultados, pero una
discrepancia no hacía fallar el proceso y las excepciones no se presentaban
como una tabla uniforme. Ahora cada caso captura los errores de ejecución
esperados o inesperados, informa su estado y el programa señala las fallas al
final.