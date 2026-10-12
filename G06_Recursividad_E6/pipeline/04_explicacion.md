# Explicación breve del algoritmo

La función recursiva cuenta los dígitos de un número haciendo que el número se haga más chico en cada llamada.

- Caso base: si el número es menor que 10, devuelve 1. Eso significa que ya quedó con un solo dígito y no hace falta seguir reduciéndolo.
- Caso recursivo: si el número tiene más de un dígito, devuelve `1 + contarDigitosPositivo(numero / 10)`.

Es decir, al dividir por 10 se elimina el último dígito, y el `1` suma esa cifra que se quitó. Luego la recursión sigue contando los dígitos que quedan.

Por ejemplo, con `98765`:

- `98765 -> 1 + contarDigitosPositivo(9876)`
- `9876 -> 1 + contarDigitosPositivo(987)`
- `987 -> 1 + contarDigitosPositivo(98)`
- `98 -> 1 + contarDigitosPositivo(9)`
- `9 -> 1`

Entonces: `1 + 1 + 1 + 1 + 1 = 5`.

La idea clave es que el problema se reduce cada vez hasta llegar al caso base, y ahí la recursión termina.
