# Pruebas — Potencia recursiva

## Casos verificados

| Caso | Resultado esperado |
|---|---:|
| `potencia(5, 0)` | `1` |
| `potencia(5, 1)` | `5` |
| `potencia(2, 3)` | `8` |
| `potencia(0, 4)` | `0` |
| `potencia(-2, 3)` | `-8` |
| `potencia(2, 30)` | `1073741824` |
| `potencia(2, 31)` | Lanza `ArithmeticException` |
| `potencia(2, -1)` | Lanza `IllegalArgumentException` |

`Math.multiplyExact` impide que un producto fuera del rango de `int` se convierta silenciosamente en un valor incorrecto.

## Ejecución

Compilación y ejecución de `Potencia.java` completadas correctamente con `javac` y `java`. Todos los casos anteriores finalizaron sin fallos.