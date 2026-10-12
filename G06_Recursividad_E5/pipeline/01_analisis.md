# Análisis — Ejercicio 5: Conteo regresivo

## Objetivo

Redactar un prompt para OpenCode que solicite implementar una función recursiva que imprima los enteros desde `n` hasta `0`, en orden descendente.

## Requerimientos funcionales

- La función recibe un entero `n`.
- Debe imprimir `n`, luego `n - 1`, y así sucesivamente hasta imprimir `0`.
- La función es `void`: no devuelve un valor. Su resultado observable es el efecto secundario de escribir el conteo en la salida.
- El caso base es `n < 0`. Cuando se alcanza, la función termina sin imprimir nada ni realizar otra llamada recursiva.
- Para `n >= 0`, la función imprime el valor actual y luego se invoca recursivamente con `n - 1`. Imprimir antes de la llamada recursiva conserva el orden descendente.
- Si la función se invoca inicialmente con un valor negativo, no imprime nada, ya que el caso base se cumple de inmediato.

## Consideraciones y casos de prueba

- `n = 3`: debe imprimir `3, 2, 1, 0`, en ese orden.
- `n = 0`: debe imprimir solamente `0`.
- `n = -1`: debe finalizar sin imprimir.
- La llamada recursiva debe avanzar hacia el caso base usando `n - 1`. Usar `n + 1` alejaría los valores no negativos del caso base; la recursión no terminaría y podría agotar la pila.
- El lenguaje y el formato de salida deben seguir las convenciones que ya utilice el proyecto.

## Prompt para OpenCode

> Implementá en el proyecto una función recursiva que imprima un conteo regresivo desde un entero `n` hasta `0`, inclusive. Primero revisá el lenguaje y las convenciones existentes y adaptá la solución a ellas.
>
> La función debe ser `void`: no devuelve ningún valor; su resultado es el efecto secundario de imprimir cada número. Usá `n < 0` como caso base: al cumplirse, la función debe terminar sin imprimir ni volver a llamarse. Si `n >= 0`, imprimí `n` y después llamá recursivamente a la función con `n - 1`. El orden importa: imprimir antes de la llamada recursiva produce el conteo descendente.
>
> Explicá brevemente por qué `n + 1` no sirve: aleja la ejecución del caso base y puede provocar recursión infinita y agotamiento de la pila. Probá la ejecución con `n = 3` (debe mostrar `3, 2, 1, 0`), `n = 0` (debe mostrar `0`) y un valor negativo (no debe mostrar nada). Ejecutá las pruebas o verificaciones disponibles y resumí los archivos modificados y los resultados.