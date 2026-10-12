# Explicación

1. **Desbordamiento de pila (`StackOverflowError`):** ocurre cuando se acumulan demasiadas llamadas recursivas activas y se agota la pila de llamadas de Java. Cada llamada guarda su propio estado hasta que retorna.
2. **Caso recursivo mal diseñado:** si la llamada no se acerca al caso base —por ejemplo, usar `n + 1` en vez de `n - 1`—, la recursión puede no terminar. Se siguen acumulando llamadas y se agota la pila; no es que necesariamente se consuma toda la memoria RAM.

En este conteo, `n < 0` es el caso base y `n - 1` hace que cada llamada se acerque a él.