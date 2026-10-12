# Plan técnico — Ejercicio 5: Conteo regresivo

## Objetivo técnico

Definir cómo implementar una función recursiva que reciba un entero `n` e imprima la secuencia desde `n` hasta `0`, inclusive. La función no calcula ni devuelve un resultado: su comportamiento observable es escribir los valores en la salida estándar.

## Diseño de la solución

- **Organización:** mantener la solución mínima y acorde con la estructura y el lenguaje que ya use el proyecto. No se necesitan clases auxiliares, colecciones ni estructuras de datos adicionales; basta una función recursiva y el punto de entrada existente o el mecanismo de prueba del ejercicio.
- **Firma:** función `void` con un parámetro entero. En Java, por ejemplo, `static void imprimirConteoRegresivo(int n)`; adaptar modificadores, nombre y ubicación a las convenciones encontradas por OpenCode.
- **Caso base:** si `n < 0`, retornar inmediatamente sin imprimir y sin realizar otra llamada.
- **Caso recursivo:** si `n >= 0`, imprimir `n` y llamar a la misma función con `n - 1`.
- **Orden:** imprimir antes de la llamada recursiva para que la salida sea descendente. No se requiere trabajo al desenrollar la recursión.

Pseudocódigo:

```text
imprimirConteoRegresivo(n):
    si n < 0:
        retornar
    imprimir n
    imprimirConteoRegresivo(n - 1)
```

## Secuencia y uso de memoria

Para `n = 3`, las llamadas reciben `3`, `2`, `1`, `0` y `-1`. Se imprime `3, 2, 1, 0`; la llamada con `-1` encuentra el caso base y retorna. A continuación, las llamadas pendientes terminan y sus marcos se retiran de la pila.

Cada llamada activa ocupa un marco en la pila de ejecución (por ejemplo, con el argumento actual y la información necesaria para continuar o retornar). La profundidad máxima normal es `n + 2` llamadas cuando `n >= 0`: las llamadas de `n` a `0` más la llamada terminal con `-1`. Para un `n` inicial negativo, solo se ejecuta la llamada inicial.

### Si se usa `n + 1` en vez de `n - 1`

Con la condición base `n < 0`, para cualquier valor inicial `n >= 0`, la secuencia de argumentos sería `n`, `n + 1`, `n + 2`, …; nunca se aproxima al caso base. La llamada recursiva se hace antes de que la llamada actual pueda terminar, por lo que sus marcos permanecen apilados. Así, la pila crece con cada llamada y la memoria reservada para la pila se agota. En Java, el resultado práctico es un `StackOverflowError`; la ejecución termina abruptamente y solo alcanza a imprimir un prefijo creciente de la secuencia, cuyo tamaño depende del entorno.

Aunque un `int` de Java eventualmente desborda y pasa de `Integer.MAX_VALUE` a un valor negativo, no es realista que la recursión alcance esa cantidad de llamadas: la pila se desborda mucho antes. Por eso el desbordamiento del entero no evita el `StackOverflowError` práctico. Si la entrada inicial ya es negativa, en cambio, el caso base se cumple desde la primera llamada y no se produce recursión, incluso si el paso recursivo estuviera escrito incorrectamente.

## Plan de implementación

1. Revisar el lenguaje, el punto de entrada, las convenciones de impresión y la ubicación prevista para la solución en el proyecto.
2. Implementar la función `void` con el caso base `n < 0`.
3. En el caso recursivo, imprimir el valor actual y luego avanzar hacia el caso base mediante `n - 1`.
4. Invocar la función desde el punto de entrada o desde las pruebas disponibles, evitando agregar lógica ajena al ejercicio.
5. Verificar los casos descritos abajo y resumir archivos modificados y resultados en el prompt de entrega.

## Verificación prevista

| Entrada | Resultado esperado |
|---|---|
| `3` | Imprime `3, 2, 1, 0`, en ese orden. |
| `0` | Imprime solamente `0`. |
| `-1` | No imprime nada y termina. |

La implementación debe además usar `n - 1` y detenerse al llegar a `n < 0`. No se debe ejecutar el caso `n + 1` como prueba normal, porque deliberadamente causaría recursión no terminante y un `StackOverflowError`; basta explicar ese comportamiento.