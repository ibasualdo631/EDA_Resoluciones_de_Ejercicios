# Análisis — Ejercicio 5: Contar ocurrencias

## Objetivo

Implementar en Java un algoritmo que, dado un vector de enteros y un valor objetivo, devuelva cuántas posiciones del vector contienen ese valor.

## Entradas y salida

- **Entrada:** un arreglo `int[]` y un valor objetivo de tipo `int`.
- **Salida:** un entero no negativo con la cantidad de elementos iguales al objetivo.
- Las apariciones se cuentan por posición: si el mismo valor está en varias posiciones, cada una suma una ocurrencia.

## Reglas y recorrido

Se debe examinar el arreglo completo y mantener un contador que se incremente cada vez que un elemento sea igual al objetivo. No corresponde usar `break` ni retornar al encontrar la primera coincidencia: el problema pide la **cantidad total**, y todavía pueden existir coincidencias en posiciones posteriores. Del mismo modo, no encontrar el objetivo en el prefijo examinado no permite concluir que no aparezca más adelante.

Por lo tanto, para un arreglo de longitud `n`, el algoritmo realiza `n` comparaciones:

- **Tiempo:** `O(n)` en el mejor, promedio y peor caso, porque siempre recorre todo el arreglo.
- **Espacio adicional:** `O(1)`, porque solo necesita un contador y variables auxiliares constantes.

## Casos borde

| Caso | Resultado/comportamiento esperado |
|---|---|
| Arreglo vacío (`new int[0]`) | Devolver `0`; no hay elementos que comparar. |
| Arreglo `null` | Recomendar rechazar la entrada explícitamente con `IllegalArgumentException` (o documentar otra política antes de implementar). No debe tratarse silenciosamente como un arreglo vacío, pues `null` no representa un vector válido y ocultaría un error del llamador. |
| Objetivo no encontrado en un arreglo válido | Devolver `0` después de recorrer el arreglo completo. |
| Una sola aparición | Devolver `1`. |
| Varias apariciones, consecutivas o separadas | Devolver el total de posiciones coincidentes. |
| Arreglo de un elemento | Devolver `1` si coincide y `0` si no coincide. |
| Valores extremos (`Integer.MIN_VALUE` / `Integer.MAX_VALUE`) | Compararlos como cualquier otro `int`; no se requiere aritmética sobre los elementos. |

## Criterios de aceptación

1. Cuenta todas las apariciones, no solo la primera.
2. Recorre el arreglo entero incluso después de encontrar coincidencias.
3. Para un arreglo vacío devuelve cero.
4. Para `null` aplica y documenta la política elegida; la recomendación es lanzar `IllegalArgumentException`.
5. Si el objetivo no aparece, devuelve cero.
6. Incluye un análisis de complejidad temporal `O(n)` y espacial `O(1)`.
7. Comprueba el resultado con casos sin coincidencias, una coincidencia, múltiples coincidencias y arreglo vacío.

## Prompt inicial para implementar la solución

> Implementá en Java un método que reciba un arreglo de enteros (`int[]`) y un valor objetivo (`int`), y devuelva cuántas veces aparece ese valor en el arreglo. Recorré todas las posiciones y aumentá un contador por cada coincidencia. No uses `break`, `return` anticipado ni ninguna otra forma de cortar el recorrido al encontrar una coincidencia: todavía podrían existir más apariciones y se necesita el total. Para un arreglo vacío, devolvé `0`; si el arreglo es `null`, rechazá la entrada lanzando `IllegalArgumentException`; si el objetivo no aparece, devolvé `0`. Agregá pruebas o ejemplos que cubran esos casos y también una coincidencia y varias coincidencias. Explicá por qué el recorrido completo es obligatorio y justificá la complejidad: tiempo `O(n)` en todos los casos y espacio adicional `O(1)`.