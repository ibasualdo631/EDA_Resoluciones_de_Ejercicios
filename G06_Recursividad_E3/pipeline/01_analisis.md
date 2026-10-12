# Análisis — Ejercicio 3: Multiplicación mediante sumas

## Objetivo

Definir una solución en Java que calcule el producto de dos enteros mediante recursión y sumas, sin utilizar el operador de multiplicación (`*`) en la implementación.

## Regla recursiva

Multiplicar `a` por `b` equivale a sumar `a` repetidamente `b` veces. Se reduce el factor `b`, dejando `a` fijo:

- Si `b > 0`, se suma `a` y se continúa con `b - 1`.
- Si `b < 0`, se resta `a` y se continúa con `b + 1`. Así también se avanza hacia cero para factores negativos.
- Si `b == 0`, se devuelve `0`.

En forma matemática para enteros:

- `producto(a, 0) = 0`
- `producto(a, b) = a + producto(a, b - 1)`, si `b > 0`
- `producto(a, b) = -a + producto(a, b + 1)`, si `b < 0`

## Justificación del caso base

Si uno de los factores es cero, no hay sumandos que acumular y el resultado es cero. Usar `b == 0` como caso base también garantiza que las llamadas recursivas terminen: para `b` positivo se resta uno y para `b` negativo se suma uno, acercándose en ambos casos a cero.

## Alcance y consideraciones

- La solución debe ser genuinamente recursiva; no se debe sustituir la recursión por un ciclo.
- Debe funcionar con valores positivos, negativos y cero en ambos argumentos.
- Los cálculos deben usar `int` y respetar el comportamiento normal de Java ante desbordamiento de enteros.
- La profundidad de recursión es proporcional a `|b|`; esta solución educativa está destinada a valores moderados de `b`.
- Las pruebas deben usar resultados esperados constantes, no calcularlos con el operador prohibido.

## Prompt base para OpenCode

```text
Implementa en Java el Ejercicio 3: multiplicar dos enteros a y b recursivamente, sin usar el operador de multiplicación (*) en el código de producción.

Antes de escribir el código, explica brevemente que la multiplicación puede representarse como sumas repetidas y que se reduce el parámetro b mientras a permanece fijo.

Implementa un método recursivo con estos casos:
- Si b es 0, devuelve 0. Este es el caso base porque sumar cero veces produce cero.
- Si b es positivo, devuelve a más el resultado recursivo para (a, b - 1).
- Si b es negativo, devuelve menos a más el resultado recursivo para (a, b + 1).

Explica que en ambos casos recursivos b se acerca a 0, por lo que la recursión termina. La rama negativa permite que el método también funcione cuando alguno de los factores es negativo.

Usa enteros int y conserva el comportamiento habitual de Java ante desbordamiento. No reemplaces la recursión por ciclos, conversión a tipos de mayor tamaño, funciones de biblioteca ni una multiplicación incorporada. Mantén la implementación sencilla y acorde con un ejercicio introductorio de recursividad. Advierte brevemente que la profundidad recursiva crece con el valor absoluto de b, por lo que se esperan valores moderados.

Agrega pruebas o ejemplos con resultados esperados escritos como constantes, sin usar el operador prohibido para calcularlos. Incluye al menos: (5, 3) → 15, (7, 0) → 0, (5, -3) → -15, (-5, 3) → -15 y (-5, -3) → 15. Si el proyecto ya tiene estructura o pruebas, intégrate con sus convenciones en lugar de crear una estructura paralela.
```