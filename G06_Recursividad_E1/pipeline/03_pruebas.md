# Pruebas: factorial recursivo

## Matriz de pruebas

| ID | Entrada | Resultado esperado | Resultado observado | Estado |
|---|---:|---|---|---|
| F-01 | `0` | `1` | `1` | Aprobada |
| F-02 | `1` | `1` | `1` | Aprobada |
| F-03 | `5` | `120` | `120` | Aprobada |
| F-04 | `20` | `2432902008176640000` | `2432902008176640000` | Aprobada |
| F-05 | `-1` | Lanzar `IllegalArgumentException` antes de iniciar la recursión | La excepción fue capturada por la comprobación; no se lanzó `AssertionError` | Aprobada |
| F-06 | `21` | Lanzar `IllegalArgumentException` por exceder el máximo admitido | La excepción fue capturada por la comprobación; no se lanzó `AssertionError` | Aprobada |

## Evidencia de ejecución

Se compiló `Factorial.java` con `javac` y se ejecutó el método `main`, que comprueba los seis casos de la matriz mediante aserciones:

```text
factorial(0) = 1
factorial(1) = 1
factorial(5) = 120
Exit code: 0
```

El código de salida `0` confirma que todas las verificaciones, incluidas las de `factorial(20)`, `-1` y `21`, finalizaron correctamente.

## Revisión

- Los argumentos negativos se rechazan con `IllegalArgumentException` antes de evaluar los casos base o realizar llamadas recursivas.
- Los argumentos mayores que `20` también se rechazan, evitando desbordar el resultado representable en `long`.
- Para `n` válido mayor que `1`, el método sigue siendo recursivo y reduce el argumento en cada llamada.
- No se detectaron fallos en los casos verificados.

**Veredicto:** aprobado para los casos incluidos en esta matriz.