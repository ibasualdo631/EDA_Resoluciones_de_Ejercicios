# Batería de pruebas — Verificador de paréntesis

## Criterio

Se considera válida una entrada cuando todos los paréntesis `(` tienen un cierre `)` correspondiente y ningún cierre aparece sin una apertura previa. Solo se evalúa el balance de paréntesis; el resto de los caracteres se ignora.

## Casos válidos

| ID | Entrada | Resultado esperado | Cobertura |
|---|---|---|---|
| V01 | `(2 + 3) * (5 - 1)` | Balanceados | Ejemplo válido del enunciado; pares consecutivos en la expresión. |
| V02 | `((a + b) * c)` | Balanceados | Ejemplo válido del enunciado; anidamiento. |
| V03 | `()` | Balanceados | Par mínimo. |
| V04 | `sin parentesis` | Balanceados | No hay delimitadores; según el alcance, no se valida la sintaxis matemática. |
| V05 | cadena vacía | Balanceados | No hay delimitadores pendientes. |
| V06 | `(a) + ((b) * (c))` | Balanceados | Varios grupos anidados y separados por texto. |

## Cierres sin apertura previa

| ID | Entrada | Resultado esperado | Cobertura |
|---|---|---|---|
| C01 | `)(` | No balanceados | La entrada empieza con un cierre. |
| C02 | `)` | No balanceados | Un cierre aislado. |
| C03 | `())` | No balanceados | Cantidad de cierres mayor que aperturas; el cierre sobrante aparece al final. |
| C04 | `())(` | No balanceados | Se encuentra un cierre sin pareja antes de llegar al final, aunque luego haya una apertura. |

## Aperturas sin cierre correspondiente

| ID | Entrada | Resultado esperado | Cobertura |
|---|---|---|---|
| A01 | `(` | No balanceados | Una apertura aislada queda en la pila. |
| A02 | `(2 + 3` | No balanceados | Ejemplo inválido del enunciado; apertura pendiente al final. |
| A03 | `((x)` | No balanceados | Queda una apertura pendiente tras cerrar el grupo interno. |
| A04 | `(((` | No balanceados | Asimetría con varias aperturas sobrantes. |

## Casos adicionales de orden y cantidad

| ID | Entrada | Resultado esperado | Cobertura |
|---|---|---|---|
| E01 | `(()())` | Balanceados | Igual cantidad y anidamiento correcto. |
| E02 | `())(()` | No balanceados | Hay un cierre sobrante antes de aperturas posteriores. |
| E03 | `(()` | No balanceados | Una apertura más que cierres. |
| E04 | `())())` | No balanceados | Varios cierres sin apertura, detectados en el primer excedente. |

## Ejecución de referencia

La clase `VerificadorParentesis` lee una expresión por línea desde la entrada estándar e imprime `Paréntesis balanceados.` o `Paréntesis no balanceados.`. Para probarla manualmente, compilar y ejecutar:

```text
javac -encoding UTF-8 VerificadorParentesis.java
java VerificadorParentesis
```

Ingresar cada caso como una línea independiente al ejecutar una vez por caso.

## Resultado de validación

Se compiló la clase y se ejecutaron los siguientes casos: V01, V02, A02, C01, C04, V04 y V05. Los siete produjeron el resultado esperado. La batería restante queda especificada para ejecución manual o automatizada posterior.