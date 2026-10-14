# Plan técnico — `eliminarEnPosicion(int posicion)`

## Objetivo para el desarrollador

Implementar en la clase que representa la lista simplemente enlazada el método:

```java
void eliminarEnPosicion(int posicion)
```

El método debe retirar el nodo ubicado en `posicion` (índice basado en cero), conservar el orden de los nodos restantes y mantener sincronizados la referencia a la cabeza y el atributo `size`.

## Contrato y excepciones

- Una posición válida cumple `0 <= posicion < size`.
- Si `posicion < 0` o `posicion >= size`, lanzar `IndexOutOfBoundsException` antes de modificar cualquier enlace o el tamaño. Se recomienda incluir la posición recibida y el tamaño actual en el mensaje de la excepción.
- Para una lista vacía (`size == 0`), toda posición es inválida y debe rechazarse mediante la misma validación.
- No capturar la excepción dentro del método: dejar que la operación que lo invoca decida cómo manejarla.
- Una posición válida elimina exactamente un nodo y hace que `size` disminuya una sola vez.

## Diseño del recorrido y manipulación de enlaces

### 1. Validar primero

Como primera operación, verificar que la posición esté dentro del rango. No avanzar por la lista ni cambiar enlaces antes de superar esta comprobación. De esta manera, un índice inválido deja el objeto sin cambios.

### 2. Eliminar la cabeza

Si `posicion == 0`:

1. Guardar temporalmente la cabeza actual como el nodo que se eliminará.
2. Mover la referencia de la cabeza al nodo siguiente de la cabeza actual.
3. Decrementar `size` una vez.

Este caso incluye una lista de un elemento: su siguiente es `null`, así que después de la operación la cabeza será `null` y `size` será cero. No se debe intentar buscar un nodo anterior a la cabeza.

### 3. Eliminar cualquier otra posición

Si `posicion > 0`:

1. Inicializar un cursor en la cabeza.
2. Avanzar el cursor exactamente `posicion - 1` enlaces. Al terminar, debe señalar al nodo anterior al que se eliminará.
3. Guardar `cursor.siguiente` en una referencia temporal al nodo eliminado.
4. Reenlazar el anterior con el sucesor del eliminado: asignar al enlace `siguiente` del cursor el enlace `siguiente` del nodo eliminado.
5. Decrementar `size` una vez.

La referencia temporal evita perder el acceso al nodo eliminado antes de leer su sucesor. La misma reasignación funciona al borrar el último nodo: su sucesor es `null`, de modo que el nodo anterior pasa a ser el nuevo último.

No modificar enlaces de nodos ajenos al recorrido. No es necesario limpiar el enlace del nodo eliminado para desvincularlo de la lista; solo hacerlo si la implementación encapsula los nodos y su convención interna lo requiere.

## Pseudocódigo orientativo

```text
si posicion < 0 o posicion >= size:
    lanzar IndexOutOfBoundsException

si posicion == 0:
    eliminado = cabeza
    cabeza = cabeza.siguiente
si no:
    anterior = cabeza
    repetir posicion - 1 veces:
        anterior = anterior.siguiente
    eliminado = anterior.siguiente
    anterior.siguiente = eliminado.siguiente

size = size - 1
```

`eliminado` se muestra para hacer explícita la referencia temporal; en Java, el tipo concreto debe ser el tipo de nodo definido por el proyecto. Si la clase mantiene además una referencia a la cola (`tail`), el desarrollador debe actualizarla a `anterior` cuando se elimina el último nodo y ponerla en `null` cuando se elimina el único nodo. No añadir esa lógica si la lista no tiene atributo de cola.

## Complejidad esperada

- Tiempo: `O(posicion)` en el peor caso, ya que es necesario recorrer la lista hasta el nodo anterior.
- Espacio adicional: `O(1)`, usando un cursor y referencias temporales, sin crear nodos ni estructuras auxiliares.

## Pruebas que debe cubrir el desarrollador

1. Eliminar el único nodo de una lista de tamaño uno: cabeza `null`, tamaño cero.
2. Eliminar la cabeza de una lista con varios nodos: la nueva cabeza es el segundo nodo y los restantes conservan su orden.
3. Eliminar un nodo intermedio: el anterior enlaza con el sucesor del eliminado.
4. Eliminar el último nodo: el anterior queda con siguiente `null`.
5. Probar `-1`, `size` y una posición inválida en una lista vacía: se lanza `IndexOutOfBoundsException`; los enlaces y `size` siguen iguales.
6. Tras una eliminación válida, comprobar que `size` se redujo exactamente en uno y que recorrer la lista produce los elementos esperados.