# Ejercicio 1: Factorial recursivo

## Objetivo

Redactar un prompt para OpenCode que solicite implementar en Java una función recursiva para calcular el factorial de un número entero no negativo.

## Análisis del problema

El factorial se define como el producto de los enteros positivos menores o iguales que `n`. Para `n = 0`, por convención, `0! = 1`.

Se puede resolver recursivamente porque la definición del factorial contiene una versión más pequeña del mismo problema:

- **Caso base:** `factorial(0) = 1` y `factorial(1) = 1`. En ambos casos el resultado es `1`.
- **Reducción:** para `n > 1`, `factorial(n) = n * factorial(n - 1)`.
- **Terminación:** cada llamada reduce `n` en uno, así que para un `n` no negativo se alcanza el caso base.

La función debe rechazar valores negativos, porque el factorial de un entero negativo no está definido en este ejercicio. Para evitar desbordamientos con un tipo primitivo, se puede usar `long` y limitar las entradas a `0..20` (`20!` cabe en un `long`).

## Prompt para OpenCode

> Implementá en Java una función recursiva que calcule el factorial de un número entero no negativo. Usá una función con firma `public static long factorial(int n)`.
>
> Explicá en comentarios breves o en una explicación posterior por qué el factorial puede resolverse recursivamente: cada caso se expresa usando el factorial de un valor menor.
>
> Incluí los casos base `n == 0` y `n == 1`, que deben devolver `1`. Para `n > 1`, reducí el problema devolviendo `n * factorial(n - 1)`. Explicá que `n` disminuye en cada llamada hasta alcanzar el caso base.
>
> Rechazá los valores negativos con `IllegalArgumentException`. Para evitar desbordamientos del tipo `long`, aceptá únicamente valores entre `0` y `20`, inclusive, y rechazá valores mayores indicando claramente el límite.
>
> Agregá pruebas o ejemplos verificables para `factorial(0) = 1`, `factorial(1) = 1`, `factorial(5) = 120` y `factorial(20) = 2432902008176640000`. Incluí también comprobaciones de que `-1` y `21` se rechazan. Mantené la solución enfocada en la recursión y no reemplaces el cálculo por un ciclo.