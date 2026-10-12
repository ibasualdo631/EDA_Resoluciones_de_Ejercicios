# Análisis — Ejercicio 4: Potencia recursiva

## Objetivo

Escribir un prompt para OpenCode que solicite implementar una función recursiva para calcular `base^exp` sin usar `Math.pow`.

## Requerimientos funcionales

- Calcular la potencia como multiplicaciones sucesivas: para `exp > 0`, `base^exp = base * base^(exp - 1)`.
- Usar como caso base `exp == 0` y devolver `1`, el elemento neutro de la multiplicación. Esto también representa el producto de cero factores y permite que la relación recursiva funcione: `base^1 = base * base^0 = base`.
- Reducir el exponente en una unidad en cada llamada. Así se progresa hacia cero y, para exponentes enteros no negativos, la recursión termina después de `exp` reducciones.
- No usar `Math.pow` ni otra función de biblioteca que calcule la potencia.
- Explicar el caso base, la reducción recursiva y qué retorna la función en cada caso.
- Proponer y ejecutar pruebas que cubran el caso base y valores representativos.

## Alcance y supuestos

- Se asume que `exp` es un entero no negativo. Con exponentes negativos, restar uno no conduce al caso base; si la interfaz los admite, deben rechazarse explícitamente en vez de permitir recursión infinita.
- Se conserva el lenguaje, la firma y las convenciones que ya use el proyecto. Si el ejercicio está aislado y no hay convenciones previas, el prompt debe pedir una implementación sencilla y pruebas acordes al lenguaje.
- No se requiere optimización por exponenciación rápida: la reducción pedida es `exp - 1`.

## Pruebas sugeridas

- `base^0 = 1`, por ejemplo `5^0 = 1`.
- `base^1 = base`, por ejemplo `5^1 = 5`.
- Potencia positiva, por ejemplo `2^3 = 8`.
- Base cero con exponente positivo, por ejemplo `0^4 = 0`.
- Base negativa con exponente positivo, por ejemplo `(-2)^3 = -8`.
- Si se admite la entrada de exponentes negativos, comprobar que se rechazan de manera definida.

## Borrador del prompt para OpenCode

> Implementa en este proyecto una función recursiva que calcule `base^exp` sin usar `Math.pow` ni una función equivalente de biblioteca. Antes de cambiar archivos, revisa el lenguaje, la estructura, las convenciones y las pruebas existentes; conserva la interfaz y el estilo del proyecto.
>
> Explica en el código o en la respuesta que una potencia de exponente positivo equivale a multiplicar la base por sí misma sucesivamente y que puede definirse recursivamente como `potencia(base, exp) = base * potencia(base, exp - 1)`. Usa `exp == 0` como caso base y devuelve `1`: es el elemento neutro de la multiplicación, de modo que `base^1` queda como `base * 1`.
>
> En cada llamada recursiva reduce el exponente exactamente en uno, acercándolo al caso base. Considera `exp` un entero no negativo; si la interfaz permite exponentes negativos, recházalos explícitamente para evitar recursión infinita, sin ampliar innecesariamente el alcance del ejercicio. No uses `Math.pow`.
>
> Agrega o actualiza pruebas para `5^0 = 1`, `5^1 = 5`, `2^3 = 8`, `0^4 = 0` y `(-2)^3 = -8`, adaptándolas al framework y a las convenciones del proyecto. Ejecuta las pruebas pertinentes e informa qué archivos cambiaste y el resultado. No hagas cambios ajenos al ejercicio.