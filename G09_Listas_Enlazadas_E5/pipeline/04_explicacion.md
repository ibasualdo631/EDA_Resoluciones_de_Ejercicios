# Explicación — Eliminación de un nodo y Garbage Collector

## ¿Qué significa eliminar un nodo en Java?

En una lista enlazada no se desplazan los elementos como en un arreglo. Cada nodo es un objeto y contiene una referencia (`siguiente`) hacia el próximo nodo. La lista mantiene la referencia `head`, desde la cual se puede recorrer toda la cadena.

Por eso, eliminar un nodo significa **cambiar las referencias para que deje de formar parte de la cadena**, no ejecutar una operación manual de liberación de memoria. Java administra la memoria de los objetos mediante el Garbage Collector (GC).

## Cómo se modifica la cadena

El método `eliminar` recorre la lista con dos referencias locales:

- `actual` apunta al nodo que se está examinando.
- `anterior` apunta al nodo visitado justo antes de `actual`.

Cuando el dato coincide, el método reconecta la lista:

- Si el nodo coincide con `head`, asigna `head = actual.siguiente`. Así el segundo nodo pasa a ser el primero. Si se elimina el único nodo, `actual.siguiente` es `null`, por lo que `head` también queda en `null`.
- En otro caso, asigna `anterior.siguiente = actual.siguiente`. Por ejemplo, en `A -> B -> C`, si se elimina `B`, la referencia de `A` pasa a apuntar directamente a `C`, y la cadena queda `A -> C`. La misma operación sirve para eliminar el último nodo: como su siguiente es `null`, el anterior pasa a ser el final de la cadena.
- Al final asigna `actual.siguiente = null`. Esto corta el enlace saliente del nodo eliminado. No es lo que lo elimina físicamente de la memoria; asegura que el nodo desvinculado no conserve innecesariamente la referencia al resto de la lista.

La búsqueda termina al encontrar la primera coincidencia, de modo que los duplicados que aparezcan después quedan intactos. Si llega a `null` sin encontrar el dato, devuelve `false` sin modificar ningún enlace.

## Qué hace el Garbage Collector

El GC identifica objetos que ya no son alcanzables desde las raíces de la aplicación, como referencias activas en los hilos y campos estáticos. En este método, después de actualizar `head` o `anterior.siguiente`, la lista deja de tener una ruta hacia el nodo eliminado. Sin embargo, mientras la variable local `actual` siga apuntando a él, el nodo todavía es alcanzable durante la ejecución del método.

Cuando el método termina, sus variables locales dejan de estar activas. Si ninguna otra referencia de la aplicación apunta al nodo eliminado, este queda **elegible para recolección**. Ser elegible no significa que el espacio se libere en ese instante: el GC decide cuándo ejecutar la recolección. Si existe otra referencia alcanzable al nodo, seguirá vivo hasta que también desaparezca esa referencia.

Por lo tanto, no corresponde llamar al GC ni intentar liberar memoria manualmente. La responsabilidad del método es dejar coherentes los enlaces de la lista; la administración posterior de memoria le corresponde a la JVM.

## Complejidad

La búsqueda recorre como máximo todos los `n` nodos: tiempo `O(n)`. Se utilizan solo las referencias `actual` y `anterior`, sin estructuras auxiliares que crezcan con la lista: espacio adicional `O(1)`.

## Pregunta para defensa oral

**Si después de eliminar un nodo se actualiza el enlace del nodo anterior, ¿por qué no necesariamente se libera la memoria del nodo en ese mismo momento, y qué condición debe cumplirse para que el Garbage Collector pueda reclamarlo?**

Una respuesta completa debería explicar que actualizar enlaces solo lo quita de la cadena de la lista; el nodo debe dejar de ser alcanzable desde todas las referencias activas/raíces de la aplicación para ser elegible, y aun entonces el GC puede recolectarlo más tarde, no necesariamente de inmediato.