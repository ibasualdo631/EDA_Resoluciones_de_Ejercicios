# Explicación: factorial recursivo y Call Stack

## Qué hace el programa

La clase `Factorial` calcula el factorial de un entero no negativo con el método estático `factorial(int n)`. El factorial de `n` es el producto de todos los enteros positivos desde `1` hasta `n`; por ejemplo, `5! = 5 × 4 × 3 × 2 × 1 = 120`. Por convención, `0! = 1`.

La solución implementa esa definición directamente: para `n > 1`, usa la identidad `n! = n × (n - 1)!`. Así el problema se reduce a otro del mismo tipo, pero con un argumento menor.

## Cómo funciona `factorial`

1. **Valida el argumento.** Si `n < 0`, lanza `IllegalArgumentException`, porque el método solo admite enteros no negativos. Si `n > 20`, también lanza esa excepción: `20!` cabe en un `long`, pero `21!` lo excede.
2. **Resuelve el caso base.** Si `n` es `0` o `1`, devuelve `1`. Estos casos detienen la recursión.
3. **Reduce el problema.** Para cualquier otro valor válido, devuelve `n * factorial(n - 1)`. La llamada recursiva vuelve a ejecutar el mismo método con un argumento menor.

La validación ocurre en cada invocación, pero cada llamada recursiva recibe `n - 1`, por lo que sigue siendo válida y finalmente llega al caso base.

## Qué es el Call Stack

El **Call Stack** (pila de llamadas) es la estructura que administra las funciones que están ejecutándose y que todavía deben terminar. Funciona con la regla **LIFO** (*Last In, First Out*): la última llamada que se apila es la primera que puede continuar y finalizar.

Cada llamada a `factorial` tiene su propio **marco** (*stack frame*), con su propio valor de `n` y el punto al que debe regresar cuando termine la llamada anidada. Los marcos no se mezclan: mientras `factorial(3)` espera el resultado de `factorial(2)`, conserva su propio `n = 3` y su multiplicación pendiente.

## Recorrido: `factorial(4)`

### Bajada: se apilan las llamadas

`factorial(4)` no puede devolver todavía su producto, así que llama a `factorial(3)`. Ese método hace lo mismo, y las llamadas continúan hasta alcanzar el caso base:

```text
factorial(4) espera 4 * factorial(3)
  factorial(3) espera 3 * factorial(2)
    factorial(2) espera 2 * factorial(1)
      factorial(1) devuelve 1
```

En ese punto, los marcos de `factorial(4)`, `factorial(3)` y `factorial(2)` siguen en la pila, esperando el resultado de la llamada que hicieron. El caso base devuelve inmediatamente `1`.

### Subida: se desapilan y completan los resultados

Al volver del caso base, se resuelven las multiplicaciones pendientes en orden inverso al de las llamadas:

```text
factorial(2) = 2 * 1 = 2
factorial(3) = 3 * 2 = 6
factorial(4) = 4 * 6 = 24
```

Cada método devuelve su resultado a quien lo llamó y su marco sale del Call Stack. Cuando termina `factorial(4)`, la pila ya no contiene ninguno de esos marcos.

## El resto del programa

- `MAXIMO_FACTORIAL_LONG` centraliza el mayor argumento aceptado, `20`.
- `main` ejecuta comprobaciones de resultados para `0`, `1`, `5` y `20`, y verifica que `-1` y `21` se rechacen. Si algo no coincide, una comprobación lanza `AssertionError`; si todas pasan, imprime tres ejemplos.
- `verificarResultado` compara el resultado del método con el valor esperado.
- `verificarRechazo` comprueba que se lance `IllegalArgumentException`. Si la llamada no la lanza, produce `AssertionError`.

## Conceptos y costos

- **Recursión:** un método se llama a sí mismo para resolver una versión más pequeña del problema.
- **Caso base:** condición que detiene la recursión; aquí, `n == 0` o `n == 1`.
- **Pila de llamadas:** conserva los argumentos y las operaciones pendientes de cada llamada.
- **Tipo `long`:** permite representar valores mayores que `int`; se limita `n` a `20` para evitar que el factorial desborde ese tipo.
- **Costo:** para un argumento `n` válido, se realizan `O(n)` llamadas y se usa `O(n)` espacio de Call Stack. El límite `20` mantiene la profundidad de esta solución acotada.

Un error común sería omitir el caso base o no reducir `n`: la recursión no terminaría y la pila podría agotarse. También sería incorrecto multiplicar por `factorial(n)` en lugar de `factorial(n - 1)`, porque se volvería a llamar con el mismo argumento.

## Preguntas de repaso

  1. En `factorial(4)`, ¿qué llamadas permanecen esperando en el Call Stack cuando se ejecuta el caso base `factorial(1)`?
  Las llamadas que quedan esperando cuando se llega al caso base factorial(1) son factorial(2), factorial(3) y factorial(4), dando como resultado 24
  2. ¿Por qué `factorial(3)` puede multiplicar por `3` solo después de que `factorial(2)` devuelve su resultado?
  Porque la recursividad emplea la pila de llamadas apilando llamadas hasta llegar al caso base, una vez que se llego, se resuelven resolviendo esas llamadas de la pila, quedando suspendidas las llamadas que entraron primero.