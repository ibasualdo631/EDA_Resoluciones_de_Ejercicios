# Ejercicio 6 — Pruebas: contar dígitos

## Revisión del comportamiento

La función `contarDigitos` rechaza `0` lanzando `IllegalArgumentException` con el mensaje `El número no puede ser cero.`. Esto respeta el análisis, que excluye cero del dominio.

Para valores negativos, convierte primero el `int` a `long` y luego calcula su magnitud con `Math.abs((long) numero)`. Por eso cuenta los dígitos sin incluir el signo menos. La conversión previa a `long` también evita el desbordamiento de `Math.abs(Integer.MIN_VALUE)`.

## Tabla de pruebas

| Entrada | Resultado esperado | Resultado observado | Estado |
|---:|---|---|:---:|
| `1` | `1` dígito | `1` dígito | OK |
| `9` | `1` dígito | `1` dígito | OK |
| `10` | `2` dígitos | `2` dígitos | OK |
| `47` | `2` dígitos | `2` dígitos | OK |
| `1000` | `4` dígitos | `4` dígitos | OK |
| `-9` | `1` dígito (magnitud `9`) | `1` dígito | OK |
| `-47` | `2` dígitos (magnitud `47`) | `2` dígitos | OK |
| `Integer.MIN_VALUE` (`-2147483648`) | `10` dígitos (magnitud `2147483648`) | `10` dígitos | OK |
| `0` | Lanzar `IllegalArgumentException` | `IllegalArgumentException`: `El número no puede ser cero.` | OK |

## Verificación ejecutada

- Compilación con `javac`: correcta.
- Ejecución de los ejemplos existentes en `main`: `7 → 1`, `45 → 2`, `98765 → 5`.
- Ejecución de los casos de la tabla contra `contarDigitos`: todos coinciden con el resultado esperado.

Los negativos no forman parte del dominio definido en el análisis funcional; se incluyen como verificación del comportamiento adicional implementado y descrito en el plan técnico.