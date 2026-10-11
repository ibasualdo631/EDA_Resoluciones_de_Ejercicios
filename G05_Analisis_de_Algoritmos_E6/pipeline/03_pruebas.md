# Pruebas — Ejercicio 6: comparar dos vectores

## Validación realizada

Se revisó `CompararVectores.java`, se compiló con `javac` y se ejecutó el método `main`. Los casos ejecutados fueron:

| Prueba | Entrada | Resultado esperado | Resultado obtenido |
|---|---|---|---|
| Vector A nulo | `null`, `[1, 2, 3]` | `IllegalArgumentException` | Correcto: excepción esperada |
| Vector B nulo | `[1, 2, 3]`, `null` | `IllegalArgumentException` | Correcto: excepción esperada |
| Ambos vectores nulos | `null`, `null` | `IllegalArgumentException` | Correcto: excepción esperada |
| Vectores iguales | `[1, 2, 3]`, `[1, 2, 3]` | `true` | Correcto: `true` |
| Vectores distintos | `[1, 2, 3]`, `[1, 9, 3]` | `false` | Correcto: `false` |
| Mismos elementos iniciales y distinto tamaño | `[1, 2, 3]`, `[1, 2, 3, 4, 5]` | `false` | Correcto: `false` |
| Vectores vacíos | `[]`, `[]` | `true` | Correcto: `true` |

**Estado:** compilación y ejecución completadas correctamente; todas las pruebas pasaron.

## Revisión del código

- La validación de `null` ocurre antes de acceder a las longitudes y lanza `IllegalArgumentException`.
- Las longitudes se comparan antes del recorrido, evitando comparar arreglos de tamaños distintos.
- Ante el primer elemento desigual, el método retorna `false` inmediatamente. Solo se recorren ambos arreglos completos cuando todos los elementos coinciden.
- Los arreglos vacíos se consideran iguales.

No se detectaron fallos en los casos ejecutados ni en la lógica revisada.