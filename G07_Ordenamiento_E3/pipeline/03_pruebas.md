# Pruebas — Insertion Sort con conteo de desplazamientos

## Convención

Un desplazamiento es una copia de un elemento existente una posición a la
derecha (`numeros[j + 1] = numeros[j]`). La escritura de la clave en su sitio
final no se cuenta. Por eso, al insertar el `4` del ejemplo, el elemento que
efectivamente se desplaza es el `5`; el `4` es la clave que ocupa el espacio
liberado.

## Matriz de casos

| ID | Caso | Entrada | Salida ordenada esperada | Desplazamientos esperados | Qué valida |
|---|---|---|---|---:|---|
| P-01 | Casi ordenado del enunciado | `{1, 2, 3, 5, 4, 6, 7}` | `{1, 2, 3, 4, 5, 6, 7}` | 1 | Solo se desplaza `5` una posición al insertar `4`; las demás claves no desplazan elementos. |
| P-02 | Inverso (peor caso para orden ascendente) | `{7, 6, 5, 4, 3, 2, 1}` | `{1, 2, 3, 4, 5, 6, 7}` | 21 | Cada clave desplaza todos los elementos anteriores mayores que ella: `1 + 2 + 3 + 4 + 5 + 6 = 21`. |

## Resultado y comprobación

- **P-01:** el método entrega `{1, 2, 3, 4, 5, 6, 7}` y cuenta **1** desplazamiento. La única inversión de la entrada es `(5, 4)`.
- **P-02:** el método entrega `{1, 2, 3, 4, 5, 6, 7}` y cuenta **21** desplazamientos. Los siete elementos en orden inverso forman `7 × 6 / 2 = 21` inversiones, de modo que se contrasta el aumento desde 1 hasta 21 desplazamientos.

El conteo no incluye las escrituras de la clave ni intercambios: corresponde
exactamente a las asignaciones que copian un elemento existente hacia la
derecha dentro del ciclo `while`.