# Tabla de pruebas — Multiplicación recursiva

## Criterio

El caso base es `b == 0`: debe devolver `0` sin hacer otra llamada recursiva. Los casos con `a == 0` y `b` distinto de cero comprueban también que el resultado permanezca en cero mientras las llamadas reducen `b` hasta el caso base. Los resultados esperados se indican como constantes.

## Casos y ejecución

| # | `a` | `b` | Resultado esperado | Resultado obtenido | Estado |
|---:|---:|---:|---:|---:|:---|
| 1 | 7 | 0 | 0 | 0 | Aprobada |
| 2 | 0 | 0 | 0 | 0 | Aprobada |
| 3 | -5 | 0 | 0 | 0 | Aprobada |
| 4 | 0 | 4 | 0 | 0 | Aprobada |
| 5 | 0 | -4 | 0 | 0 | Aprobada |
| 6 | 5 | 3 | 15 | 15 | Aprobada |
| 7 | 5 | -3 | -15 | -15 | Aprobada |
| 8 | -5 | 3 | -15 | -15 | Aprobada |
| 9 | -5 | -3 | 15 | 15 | Aprobada |

Los casos 1–3 son la comprobación directa del corte: al entrar con `b == 0`, el método devuelve inmediatamente. Si faltara ese caso base o no se tomara esa rama, la ejecución de estos casos fallaría en lugar de aprobar. Los casos 4–5 comprueban que un cero en el otro factor da el resultado correcto aunque la recursión avance hasta que `b` llegue a cero.

## Cómo ejecutar

Desde la carpeta del ejercicio:

```text
javac MultiplicacionRecursiva.java MultiplicacionRecursivaTestRunner.java
java MultiplicacionRecursivaTestRunner
```

Ejecución verificada el **12/10/2026**: compilación con `javac` y ejecución de
`MultiplicacionRecursivaTestRunner` finalizaron correctamente. Salida final:
`Resultado: 9 pruebas aprobadas.` Las tres llamadas con `b == 0` retornaron
`0`; si ese caso base no se atendiera, la ejecución fallaría (por ejemplo,
con `StackOverflowError`) en vez de aprobar.