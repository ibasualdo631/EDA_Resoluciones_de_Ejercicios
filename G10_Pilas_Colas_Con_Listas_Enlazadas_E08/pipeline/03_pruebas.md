# Pruebas — Inversión de palabra con pila enlazada

## Alcance

Se validó la clase `InvertirPila.java`, compilándola y ejecutando su interfaz de consola con los tres tipos de entrada requeridos: una palabra, una sola letra y una cadena vacía.

## Entorno y ejecución

- Lenguaje: Java.
- Compilación: `javac -encoding UTF-8 -d <directorio-temporal> InvertirPila.java`
- Ejecución por caso: `echo <entrada> | java -cp <directorio-temporal> InvertirPila`
- El directorio temporal de compilación se eliminó después de completar las pruebas.

## Casos y resultados

| Caso | Entrada | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|
| Palabra del enunciado | `algoritmo` | `omtirogla` | `omtirogla` | Aprobado |
| Una sola letra | `x` | `x` | `x` | Aprobado |
| Cadena vacía | cadena vacía | cadena vacía | cadena vacía | Aprobado |

## Resultado de validación

- Compilación: aprobada, código de salida `0`.
- Ejecución: los tres casos terminaron con código de salida `0` y coincidieron con el resultado esperado.
- No se encontraron fallos en el alcance probado.