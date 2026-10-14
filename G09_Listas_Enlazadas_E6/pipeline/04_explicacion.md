# Explicación — eliminar un nodo por posición

## Cómo se salta el nodo eliminado

En una lista simplemente enlazada, cada `Nodo` contiene un valor y una referencia `siguiente` al nodo posterior. Antes de eliminar un nodo interior, los enlaces se ven así:

```text
anterior ──siguiente──> eliminado ──siguiente──> siguiente
```

Para borrar la posición `posicion`, el método primero recorre desde `cabeza` hasta ubicar el nodo anterior. El ciclo usa `i < posicion - 1` porque, al terminar, `anterior` debe estar exactamente un enlace antes del nodo que queremos quitar.

```java
Nodo eliminado = anterior.siguiente;
anterior.siguiente = eliminado.siguiente;
```

La primera línea conserva temporalmente la referencia al nodo que se quitará. La segunda cambia **el enlace del nodo anterior**: en lugar de apuntar a `eliminado`, ahora apunta directamente al sucesor de este. El resultado es:

```text
anterior ───────────────────────────> siguiente
             eliminado
```

No se copian los valores ni se desplazan los demás nodos: solo se modifica un enlace. Por eso se mantiene el orden de todos los elementos restantes. Guardar `eliminado` antes de reasignar el enlace también asegura que el método pueda leer `eliminado.siguiente`; sobrescribir el enlace del anterior primero sin guardar el nodo podría hacer perder ese acceso durante el algoritmo.

### Cuando se elimina el último nodo

Si el nodo eliminado era el último, entonces `eliminado.siguiente` vale `null`. Al ejecutar la misma asignación, el nodo anterior queda apuntando a `null`, que es precisamente cómo se representa el final de la lista. No hace falta una rama especial para este caso.

### Cuando se elimina el primero

La cabeza no tiene un nodo anterior, así que el método usa el caso especial:

```java
cabeza = cabeza.siguiente;
```

La referencia que identificaba al primer nodo pasa a identificar al segundo. Si solo había un nodo, `cabeza.siguiente` es `null`; la lista queda vacía.

## Qué ocurre con el nodo en la memoria RAM

Cambiar el enlace lo quita de la **cadena alcanzable desde `cabeza`**, pero no equivale a borrar inmediatamente sus bytes de la memoria. En Java no se libera memoria con una instrucción manual como `free`: la JVM administra los objetos mediante el recolector de basura.

En la eliminación de una posición distinta de cero, la variable local `eliminado` todavía hace referencia al nodo hasta que termina el método. Además, el campo `eliminado.siguiente` todavía apunta al sucesor, aunque el nodo anterior de la lista ya apunta directamente a ese sucesor. Cuando finaliza la llamada y ya no queda ninguna referencia alcanzable al nodo eliminado, este queda **elegible para ser recolectado**. La recolección sucede en un momento decidido por la JVM, no necesariamente justo al salir del método.

El nodo todavía puede permanecer en memoria si alguna otra parte del programa conserva una referencia a él. En esta implementación los nodos son privados y el método no devuelve el nodo eliminado, por lo que el uso normal no conserva esa referencia. No es necesario poner `eliminado.siguiente = null` para que deje de pertenecer a la lista; hacerlo sería una decisión adicional para limpiar el enlace interno, no una operación necesaria para el recolector de basura.

Para el primer nodo, al reemplazar `cabeza` por `cabeza.siguiente`, la lista también deja de referenciar la cabeza anterior. No existe una variable local `eliminado` en esa rama. Si ninguna otra referencia mantiene al antiguo nodo, también podrá recolectarse cuando deje de estar referenciado.

## Relación con `size`

La validación se ejecuta antes de tocar enlaces:

```java
if (posicion < 0 || posicion >= size) {
    throw new IndexOutOfBoundsException(...);
}
```

Así, una posición negativa, igual a `size` o cualquier posición sobre una lista vacía produce una excepción sin cambiar la lista. Cuando la eliminación sí es válida, el método realiza la modificación correspondiente y después ejecuta una sola vez:

```java
size--;
```

Por lo tanto, el tamaño registrado disminuye exactamente en uno por eliminación válida y permanece intacto ante índices inválidos.

## Traza breve

Con `[10, 20, 30, 40]`, eliminar la posición `2`:

1. La validación permite el índice porque `0 <= 2 < 4`.
2. El recorrido se detiene en `20`, el nodo anterior a `30`.
3. `eliminado` guarda la referencia a `30`.
4. `anterior.siguiente = eliminado.siguiente` hace que `20` apunte a `40`.
5. `size` pasa de `4` a `3`.
6. La cadena desde la cabeza representa `[10, 20, 40]`. Tras terminar la llamada, si no hay otras referencias a `30`, este nodo es elegible para el recolector de basura.