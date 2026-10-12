# Pruebas — Ejercicio 8: invertir un String de forma recursiva

## Alcance

Se verificó que `InvertirPalabra.invertir` preserve el comportamiento
recursivo esperado, devuelva correctamente los casos base y no lance
`NullPointerException` cuando recibe `null`. Para la entrada nula se definió
paso directo: `null` produce `null`.

## Casos ejecutados

Se ejecutó `java .\InvertirPalabra.java` desde la carpeta del ejercicio.

| Entrada | Resultado esperado | Resultado | Estado |
|---|---|---|---|
| `null` | `null` | `null`, sin excepción | Aprobado |
| `""` | `""` | `""` | Aprobado |
| `"a"` | `"a"` | `"a"` | Aprobado |
| `"sol"` | `"los"` | `"los"` | Aprobado |
| `"reconocer"` | `"reconocer"` | `"reconocer"` | Aprobado |
| `"a b"` | `"b a"` | `"b a"` | Aprobado |
| `"hola"` | `"aloh"` | `"aloh"` | Aprobado |
| `"recursivo"` | `"ovisrucer"` | `"ovisrucer"` | Aprobado |

## Resultado

Los ocho casos pasaron. La guarda explícita para `null` evita acceder a
`length()` con una referencia nula; los demás casos confirman la inversión,
incluidos los espacios y el palíndromo. No se observaron fallos.