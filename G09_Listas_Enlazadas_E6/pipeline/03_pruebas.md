# Auditoría y pruebas — Ejercicio 6

## Alcance

Se revisó `eliminarEnPosicion(int posicion)` para comprobar el rechazo de índices inválidos, la seguridad de las reasignaciones de enlaces y la actualización de `size` tras cada eliminación.

## Revisión del código

- La condición `posicion < 0 || posicion >= size` rechaza índices negativos, el índice igual al tamaño y cualquier índice posterior. En una lista vacía también rechaza toda posición.
- La excepción `IndexOutOfBoundsException` se lanza antes de cualquier cambio en `cabeza`, enlaces o `size`.
- Para la posición cero, la cabeza pasa a ser su sucesor. En una lista de un elemento ese sucesor es `null`.
- Para otras posiciones, el recorrido termina en el nodo anterior; se guarda el nodo eliminado antes de enlazar el anterior con el sucesor del eliminado. Esto conserva la cadena, incluso si el sucesor es `null` al eliminar el último.
- `size--` se ejecuta una sola vez después de una eliminación válida y no se alcanza si la posición es inválida.
- No se encontraron defectos en el método auditado.

## Casos ejecutados

| Caso | Resultado esperado | Resultado |
|---|---|---|
| Eliminar posición `0` de `[10, 20, 30, 40, 50]` | `[20, 30, 40, 50]`, `size == 4` | Pasó |
| Eliminar posición `1` de `[20, 30, 40, 50]` | `[20, 40, 50]`, `size == 3` | Pasó |
| Eliminar última posición de `[20, 40, 50]` | `[20, 40]`, `size == 2` | Pasó |
| Eliminar el único nodo de `[99]` | `[]`, `size == 0` | Pasó |
| Eliminar posición `-1` | `IndexOutOfBoundsException`; contenido y tamaño sin cambios | Pasó |
| Eliminar posición `size` | `IndexOutOfBoundsException`; contenido y tamaño sin cambios | Pasó |
| Eliminar posición `0` de una lista vacía | `IndexOutOfBoundsException`; lista y tamaño sin cambios | Pasó |

## Verificación

Compilación con `javac` y ejecución del método `main`: satisfactorias. Las comprobaciones fallan con una excepción si el contenido o `size` difieren del valor esperado.