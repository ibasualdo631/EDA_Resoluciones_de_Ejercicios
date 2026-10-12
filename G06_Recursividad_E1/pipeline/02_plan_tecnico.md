# Plan técnico: factorial recursivo en Java

## Objetivo

Definir la estructura para implementar y verificar el cálculo recursivo del factorial de un entero. La solución debe rechazar entradas fuera del dominio acordado y explicar el uso de pila de llamadas.

## Organización propuesta

Mantener la solución pequeña y enfocada:

- `Factorial.java`: clase pública `Factorial` con el método estático `factorial(int n)`.
- Pruebas o ejemplos verificables en el mecanismo de pruebas que ya use el proyecto. Si no hay uno, agregar una clase de prueba mínima separada, sin mezclar las comprobaciones con el algoritmo.

No hacen falta colecciones ni estructuras de datos adicionales: el estado de cada subproblema se representa mediante el parámetro entero y las llamadas recursivas.

## Contrato del método

```java
public static long factorial(int n)
```

- Dominio aceptado: `0 <= n <= 20`.
- Resultado: el factorial de `n` como `long`.
- Para `n < 0`, lanzar `IllegalArgumentException` con un mensaje que indique que el argumento no puede ser negativo.
- Para `n > 20`, lanzar `IllegalArgumentException` con un mensaje que indique el límite máximo de `20`; este límite evita el desbordamiento de `long`.
- No capturar estas excepciones dentro del método: deben propagarse al llamador para que pueda manejarlas.

## Algoritmo y secuencia de ejecución

1. Validar que `n` no sea negativo. Si lo es, lanzar `IllegalArgumentException`.
2. Validar que `n` no supere `20`. Si lo supera, lanzar `IllegalArgumentException`.
3. Si `n` es `0` o `1`, devolver `1`.
4. En otro caso, devolver `n * factorial(n - 1)`.

La validación se realiza antes de la recursión, de modo que entradas inválidas no generen llamadas recursivas. Cada llamada reduce el argumento en uno y, dado el dominio validado, alcanza el caso base.

## Complejidad esperada

- Tiempo: `O(n)`, por una llamada para cada valor desde `n` hasta el caso base.
- Pila de llamadas: `O(n)` en el peor caso, porque las llamadas quedan apiladas hasta alcanzar `0` o `1`. Para `n = 0` o `n = 1`, el uso adicional de pila es `O(1)`.
- Con el límite `n <= 20`, la profundidad máxima está acotada y no representa un riesgo práctico de desbordamiento de pila.

## Verificación para el desarrollador

Comprobar como mínimo:

| Entrada | Resultado esperado |
|---:|---:|
| `0` | `1` |
| `1` | `1` |
| `5` | `120` |
| `20` | `2432902008176640000` |
| `-1` | `IllegalArgumentException` |
| `21` | `IllegalArgumentException` |

Confirmar que los casos válidos se calculan mediante recursión y que ambas validaciones ocurren antes de invocar recursivamente el método. Para los errores, verificar el tipo de excepción; los mensajes deben distinguir el límite inferior del superior.