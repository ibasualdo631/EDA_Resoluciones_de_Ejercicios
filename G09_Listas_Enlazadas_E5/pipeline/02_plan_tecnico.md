# Plan técnico — Ejercicio 5: Eliminación de nodo por valor

## Diseño

Implementar la operación en la clase que ya representa la lista enlazada, respetando sus nombres de campos y su estructura de nodos. El método público tendrá la firma solicitada:

```java
boolean eliminar(int dato)
```

La búsqueda y la eliminación se resolverán en una única pasada, con tiempo `O(n)` y espacio auxiliar `O(1)`. Se mantendrán dos referencias locales:

- `actual`: nodo que se está examinando.
- `anterior`: nodo inmediatamente previo a `actual`; comienza en `null`.

No se requiere un método auxiliar ni crear nodos nuevos.

## Secuencia de búsqueda y desacople

1. Inicializar `actual` con la cabeza y `anterior` con `null`.
2. Avanzar mientras `actual` no sea `null` y su dato no coincida con `dato`. Antes de avanzar `actual` a su siguiente nodo, guardar el actual en `anterior`.
3. Si `actual` llega a `null`, el valor no existe. Retornar `false` sin haber cambiado enlaces ni referencias de la lista.
4. Si se encuentra la primera coincidencia:
   - **Coincidencia en la cabeza (`anterior == null`):** mover la cabeza a `actual.siguiente`.
   - **Coincidencia posterior a la cabeza:** cambiar `anterior.siguiente` para que apunte a `actual.siguiente`. Esto cubre tanto un nodo interno como el último.
5. Si la lista conserva una referencia a la cola y `actual` era la cola, mover la cola a `anterior`. En una lista con un único nodo, esto deja la cola en `null`; la actualización de cabeza deja también la cabeza en `null`.
6. Desacoplar el nodo removido asignando `null` a su enlace hacia el siguiente, si la estructura del nodo permite hacerlo. El orden importante es actualizar primero las referencias de la lista y luego limpiar ese enlace.
7. Retornar `true`.

El recorrido se detiene en la primera coincidencia. Por tanto, si hay valores duplicados, las apariciones posteriores no se visitan ni se modifican.

## Pseudocódigo

```text
anterior = null
actual = cabeza

mientras actual != null y actual.dato != dato:
    anterior = actual
    actual = actual.siguiente

si actual == null:
    retornar false

si anterior == null:
    cabeza = actual.siguiente
si no:
    anterior.siguiente = actual.siguiente

si existe cola y cola == actual:
    cola = anterior

actual.siguiente = null   // si el enlace es accesible en esta implementación
retornar true
```

Los identificadores `cabeza`, `cola`, `dato` y `siguiente` son ilustrativos: se deben sustituir por los nombres reales del código. Si la lista no guarda una cola, se omite esa actualización.

## Garbage Collector y alcance del desacople

Java no proporciona una operación para liberar manualmente la memoria de un objeto. La eliminación lógica ocurre al quitar el nodo de la cadena que la lista mantiene. Al redirigir la cabeza o el enlace del nodo anterior y limpiar el enlace saliente del nodo removido, la propia lista deja de mantener referencias a ese nodo y este deja de mantener una referencia al resto de la cadena.

El nodo será **elegible** para recolección cuando no quede ninguna referencia alcanzable desde raíces del Garbage Collector que permita llegar a él. Si otro código conserva una referencia al nodo, no será elegible todavía. Incluso cuando lo sea, el Garbage Collector decide cuándo recolectarlo; no se debe afirmar ni depender de que la memoria se libere inmediatamente.

## Invariantes después de la operación

- Si la operación retorna `false`, la estructura de la lista permanece sin cambios.
- Si retorna `true`, queda removida exactamente una instancia: la primera coincidencia desde la cabeza.
- Los nodos restantes mantienen el mismo orden y son alcanzables desde la cabeza.
- Si existe cola, apunta al último nodo alcanzable; si la lista queda vacía, cabeza y cola son `null`.
- El nodo removido ya no enlaza al siguiente mediante su campo de sucesión, cuando sea posible limpiar dicho campo.

## Validación prevista

Probar los casos de la consigna: lista vacía, eliminación de cabeza, nodo interno, último nodo y valor ausente. Añadir casos para eliminar el único nodo y para duplicados, verificando el booleano retornado, el contenido y orden resultantes, y la coherencia de cabeza/cola si se exponen mediante la API de la lista.
