# Análisis — Ejercicio 7: Sumar dígitos

## Objetivo

Implementar una función recursiva que reciba un entero positivo y devuelva la
suma de sus dígitos decimales. Por ejemplo, para `1234` el resultado es
`1 + 2 + 3 + 4 = 10`.

## Regla recursiva

- **Extraer el último dígito:** `n % 10` obtiene el resto de dividir `n` por
  10. En base decimal, ese resto es el dígito de las unidades; por ejemplo,
  `1234 % 10` da `4`.
- **Reducir el problema:** `n / 10`, con división entera, elimina el último
  dígito; por ejemplo, `1234 / 10` da `123`. La llamada recursiva opera así
  sobre un número con un dígito menos.
- **Combinar:** sumar el último dígito al resultado recursivo:
  `sumarDigitos(n) = (n % 10) + sumarDigitos(n / 10)`.
- **Caso base:** si `n < 10`, el número tiene un solo dígito y ese mismo valor
  es la suma. Retornarlo detiene la recursión.

La regla se aplica a enteros positivos, según la consigna. No hace falta definir
el tratamiento de negativos para este ejercicio. El cero puede incluirse como
prueba límite opcional: devolvería cero con el caso base indicado.

## Requisitos funcionales

1. La función recibe un entero positivo y devuelve la suma de sus dígitos.
2. La solución debe ser recursiva, usando módulo 10 para extraer el último
   dígito y división entera por 10 para avanzar.
3. Debe incluir un caso base para números de un dígito.
4. Cada paso debe sumar el dígito extraído al resultado de la llamada recursiva.
5. Las pruebas deben cubrir un dígito, varios dígitos y un número con varios
   dígitos consecutivos.

## Prompt base para OpenCode

> Implementá en Java una función recursiva que calcule la suma de los dígitos de
> un entero positivo. Guardá la solución en un único archivo llamado
> `SumarDigitos.java`. En cada llamada, explicá con nombres claros cómo
> `n % 10` obtiene el último dígito y cómo `n / 10` (división entera) reduce el
> problema eliminando ese dígito. Usá como caso base `n < 10`, que debe retornar
> `n`, y combiná los resultados sumando el último dígito al resultado de la
> llamada recursiva. Incluí un `main` que valide al menos estos casos:
> `5 → 5`, `24 → 6` y `1234 → 10`. Mostrá cada resultado de forma clara y
> mantené la implementación sencilla y adecuada para un ejercicio introductorio
> de recursividad.