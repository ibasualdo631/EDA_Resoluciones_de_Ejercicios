# Análisis — Ejercicio 2: suma recursiva de los primeros N números

## Objetivo

Definir una función recursiva que, dado un entero `n`, calcule la suma de los enteros desde `n` hasta `1`. Por ejemplo, para `n = 5`, el resultado es `5 + 4 + 3 + 2 + 1 = 15`.

## Requerimientos funcionales

- La función recibe un entero `n` y devuelve la suma de `n` hasta `1`.
- Debe resolver el cálculo mediante recursión, reduciendo el argumento en una unidad en cada llamada.
- Debe reconocer `n = 0` como caso base y devolver `0`.
- Para `n > 0`, debe aplicar la relación `suma(n) = n + suma(n - 1)`.
- Debe tratar explícitamente entradas negativas; la estrategia de validación y su representación en la firma se definirán en el plan técnico.
- La solución debe poder comprobarse con casos representativos, incluidos `0`, `1` y `5` (resultado esperado para `5`: `15`).

## Explicación de la recursión

El caso más simple es `n = 0`: no hay números positivos entre `0` y `1` que sumar, así que la suma es `0`. Este caso detiene la recursión. Sin él, cada llamada seguiría invocando a otra con un argumento menor y no existiría una condición de terminación para el descenso.

Para un `n` positivo, la suma buscada contiene al propio `n` y a todos los enteros que van de `n - 1` a `1`. Esa parte restante es precisamente `suma(n - 1)`. Por eso:

```text
suma(n) = n + suma(n - 1), si n > 0
suma(0) = 0
```

Por ejemplo, `suma(3)` se descompone en `3 + suma(2)`, luego `3 + 2 + suma(1)`, luego `3 + 2 + 1 + suma(0)`. Al llegar al caso base, las llamadas pendientes retornan y se obtiene `6`.

## Validación esperada

Verificar al menos que `suma(0) = 0`, `suma(1) = 1` y `suma(5) = 15`. También comprobar que una entrada negativa no produzca una recursión indefinida y que se maneje según la validación que se defina en el plan técnico.

## Alcance y supuestos

- La consigna pide redactar un prompt para OpenCode, no implementar aquí la función.
- La consigna visible no fija lenguaje ni firma; el flujo de trabajo adjunto anticipa una implementación posterior en Java. Por eso el prompt inicial deja Java especificado como contexto para esa etapa.
- Se asume que el dominio válido para la suma es `n >= 0`; el comportamiento ante `n < 0` debe definirse explícitamente antes de implementarlo.

## Prompt inicial para OpenCode

> Implementá una función recursiva que sume los números enteros desde `n` hasta `1`. Usá `suma(0) = 0` como caso base, porque al llegar a cero ya no quedan números positivos por agregar y así termina la recursión. Para `n > 0`, definí el resultado como `n + suma(n - 1)`: se suma el valor actual `n` y se delega en la llamada recursiva la suma de los valores restantes hasta `1`. La implementación posterior será en Java. Dejá explícito cómo se valida un valor negativo para evitar recursión indefinida y permití comprobar los casos `n = 0`, `n = 1` y `n = 5`, cuyos resultados esperados son `0`, `1` y `15`, respectivamente. No uses una fórmula iterativa o cerrada en lugar de la recursión.