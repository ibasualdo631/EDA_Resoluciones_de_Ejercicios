# Pruebas — Ejercicio 7: invertir un vector

## Alcance

Se probaron `invertirVectorInSitu.invertir` y `InvertirVectorAuxiliar.invertir`, incluyendo el contenido devuelto, el comportamiento de la referencia original, las entradas límite y la excepción para `null`.

## Casos ejecutados

| Caso | Entrada | In situ | Arreglo auxiliar | Resultado |
|---|---|---|---|---|
| Vector vacío | `[]` | Devuelve el mismo arreglo vacío e imprime el aviso | Devuelve una copia vacía e imprime el aviso | Correcto |
| Un elemento | `[7]` | Devuelve el mismo arreglo y avisa que no puede invertirse | Devuelve una copia `[7]` y avisa | Correcto |
| Referencia nula | `null` | Lanza `IllegalArgumentException` con mensaje | Lanza `IllegalArgumentException` con mensaje | Correcto |
| Longitud impar, cinco elementos | `[1, 2, 3, 4, 5]` | Resultado `[5, 4, 3, 2, 1]`; conserva la referencia original | Resultado `[5, 4, 3, 2, 1]`; preserva el original y devuelve otra referencia | Correcto |
| Longitud par | `[1, 2, 3, 4]` | Resultado `[4, 3, 2, 1]`; conserva la referencia original | Resultado `[4, 3, 2, 1]`; preserva el original y devuelve otra referencia | Correcto |
| Vector capicúa | `[1, 2, 3, 2, 1]` | Resultado idéntico al esperado | Resultado idéntico y original preservado | Correcto |

## Validación técnica

- Compilación ejecutada con `javac -Xlint:all`: **aprobada**.
- Ejecución de `Ejercicio7.main`: **aprobada**; todas las comprobaciones finalizaron correctamente.
- Diagnósticos del editor para los tres archivos Java: **sin errores**.
- Generación/verificación formal de Javadoc: **no ejecutada**; `javadoc` no está disponible en el `PATH` y no se encontró `javadoc.exe` junto a la ruta disponible de `javac`.

## Observaciones

- Se añadió al ejecutor de consola una prueba de longitud par, complementando los cinco casos solicitados y comprobando la otra condición de terminación de los intercambios.
- El caso capicúa invertido produce el mismo vector; por sí solo no demostraría que la operación invierte. La prueba de cinco elementos no capicúa y la prueba de longitud par verifican el cambio de orden.
- No se encontraron fallos funcionales en los casos ejecutados.