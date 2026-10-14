# Análisis — Ejercicio 6: eliminar un nodo por posición

## Objetivo

Implementar `void eliminarEnPosicion(int posicion)` para quitar de una lista simplemente enlazada el nodo que ocupa una posición indicada, manteniendo correctamente los enlaces y el atributo `size`.

## Requerimientos funcionales

1. Validar que `posicion` identifique un nodo existente antes de recorrer o modificar la lista.
2. Si la posición es `0`, eliminar el primer nodo actualizando la referencia a la cabeza para que apunte al segundo nodo.
3. Para cualquier otra posición válida, recorrer la lista hasta encontrar el nodo anterior al que se eliminará. Actualizar el enlace de ese nodo anterior para que apunte al sucesor del nodo eliminado.
4. Reducir `size` exactamente en uno después de eliminar correctamente un nodo.
5. Si la posición no es válida, informar el error sin modificar los enlaces ni `size`.

## Reglas y supuestos

- Las posiciones se interpretan desde cero: para una lista de tamaño `size`, el rango válido es `0 <= posicion < size`.
- Una lista vacía no admite ninguna posición válida.
- Para expresar una posición negativa o fuera de rango, el método lanzará `IndexOutOfBoundsException`, de acuerdo con la convención de Java. La validación debe ocurrir antes de cualquier modificación.
- Al eliminar un nodo interior o final, el sucesor puede ser `null`; por eso la misma reasignación del enlace cubre ambos casos.
- La eliminación de la cabeza es un caso especial porque no existe un nodo anterior.

## Estrategia de recorrido

Para eliminar una posición `p > 0`, iniciar desde la cabeza y avanzar hasta el nodo en la posición `p - 1`. Ese nodo es el anterior al que se eliminará. Si `anterior` refiere a ese nodo y `eliminado` a `anterior.siguiente`, enlazar `anterior.siguiente` con `eliminado.siguiente`. Así se omite el nodo eliminado sin alterar el orden de los restantes. Para `p == 0`, actualizar directamente la cabeza a su nodo siguiente.

## Casos límite a contemplar

- Lista vacía: cualquier posición es inválida; no cambia `size`.
- Posición negativa o igual/mayor que `size`: se informa el error y la lista permanece intacta.
- Lista de un elemento: al eliminar la posición cero, la cabeza queda en `null` y `size` queda en cero.
- Primera posición: actualizar la cabeza.
- Posición intermedia: enlazar el anterior con el siguiente del nodo eliminado.
- Última posición: el enlace del anterior queda en `null`.

## Criterios de aceptación

- El método elimina exactamente el elemento solicitado y conserva el orden de los demás.
- La cabeza representa el primer nodo restante luego de cualquier eliminación, incluida la eliminación de la cabeza.
- `size` disminuye una sola vez por cada eliminación válida y no cambia en una operación inválida.
- Las posiciones inválidas se rechazan antes de modificar el estado de la lista.

## Prompt inicial formal

> Implementá el método `void eliminarEnPosicion(int posicion)` para una lista simplemente enlazada que mantiene una referencia a su cabeza y un atributo `size`. Considerá que las posiciones son de base cero y que solo son válidas las posiciones comprendidas entre `0` y `size - 1`, inclusive. Validá la posición antes de modificar la lista; si es negativa o está fuera de rango, lanzá `IndexOutOfBoundsException` y conservá intactos los enlaces y el tamaño. Para eliminar la posición cero, actualizá la cabeza para que apunte al segundo nodo. Para las demás posiciones, recorré la lista hasta encontrar el nodo anterior al que se eliminará y hacé que su enlace apunte al nodo siguiente del eliminado. Contemplá las listas vacías, de un solo elemento y la eliminación del último nodo. Decrementá `size` exactamente una vez después de una eliminación válida. Explicá el recorrido y los casos límite.