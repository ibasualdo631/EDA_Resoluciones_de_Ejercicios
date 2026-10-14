# Análisis — Ejercicio 4: Inserción en posición específica

## Objetivo

Implementar el método `void insertarEnPosicion(int dato, int posicion)` para agregar un nodo a una lista simplemente enlazada en el índice indicado, conservando todos los nodos y el orden de la cadena.

## Alcance y contrato

- La operación inserta un nodo con el valor `dato` en `posicion`.
- Se considera que los índices empiezan en cero: `0` inserta al inicio y `size` inserta después del último nodo.
- Las posiciones válidas son `0 <= posicion <= size`, donde `size` es la cantidad de nodos antes de insertar.
- Una posición negativa o mayor que `size` es inválida. La implementación debe rechazarla de forma explícita y consistente, sin alterar la lista.
- Se asume una lista simplemente enlazada, con referencias equivalentes a `cabeza` y `siguiente`. Si la implementación también mantiene una referencia a la cola, esta debe actualizarse al insertar al final o al insertar en una lista vacía.

## Casos de inserción

### Inicio (`posicion == 0`)

El nuevo nodo debe apuntar a la cabeza anterior y luego la cabeza de la lista debe pasar a ser el nuevo nodo. Esto también cubre la lista vacía: el nodo insertado se convierte en cabeza y, si existe, en cola.

### Medio (`0 < posicion < size`)

Se recorre la lista hasta el nodo anterior a la posición, llamado aquí `actual`. El nuevo nodo debe enlazarse primero con el sucesor que ocupaba esa posición; después, `actual` debe enlazarse con el nuevo nodo:

```java
nuevo.setSiguiente(actual.getSiguiente());
actual.setSiguiente(nuevo);
```

Después de la operación, el orden esperado es `actual -> nuevo -> sucesor anterior`.

### Final (`posicion == size`)

Si la lista no está vacía, `actual` es el último nodo y su siguiente es `null`. El mismo orden de reconexión hace que `actual` apunte al nuevo nodo y que el nuevo nodo quede como último (`nuevo.siguiente == null`). Si se mantiene una cola, debe actualizarse al nodo nuevo. En una lista vacía, insertar al final equivale a insertar en la posición cero.

## Orden crítico de reconexión

Antes de modificar el enlace de `actual`, se debe guardar en `nuevo.siguiente` el sucesor que tenía `actual`; recién entonces `actual.siguiente` puede cambiar para apuntar a `nuevo`.

Si se ejecuta primero `actual.setSiguiente(nuevo)` y luego se intenta obtener el sucesor mediante `actual.getSiguiente()`, ese getter ya devuelve `nuevo`. Al asignarlo a `nuevo.siguiente`, el nuevo nodo puede quedar apuntándose a sí mismo (`nuevo -> nuevo`), se pierde el acceso al resto de la cadena y los recorridos pueden entrar en un ciclo infinito. La cadena original debe preservarse antes de reemplazar el enlace.

La secuencia de dos asignaciones aplica a inserciones después de una cabeza existente; el caso de inicio necesita actualizar la referencia de cabeza y seguir el mismo principio de conservar primero la cabeza anterior.

## Validación y casos límite

- Rechazar `posicion < 0`.
- Rechazar `posicion > size`.
- Permitir `posicion == size` para insertar al final.
- Permitir `posicion == 0` en una lista vacía.
- Asegurar que un rechazo no deje referencias parcialmente modificadas.
- Comprobar que, luego de insertar, el tamaño aumenta exactamente en uno y el orden previo de los elementos se conserva.
- Evitar ciclos y nodos inaccesibles tras cualquier inserción.

La consigna no especifica cómo informar una posición inválida (por ejemplo, excepción u otro mecanismo). El prompt propone usar el mecanismo de error que ya siga el proyecto y exige que se documente y pruebe; no impone uno arbitrariamente.

## Prompt inicial

> Implementá el método `void insertarEnPosicion(int dato, int posicion)` para la lista simplemente enlazada existente, respetando sus nombres, convenciones y mecanismo habitual de manejo de errores.
>
> Considerá posiciones basadas en cero: la posición `0` inserta al inicio y la posición igual al tamaño actual inserta al final. Rechazá explícitamente las posiciones negativas y las mayores que el tamaño, sin modificar la lista.
>
> Cubrí los tres casos:
> - **Inicio:** enlazá el nuevo nodo con la cabeza anterior antes de actualizar la cabeza.
> - **Medio:** encontrá el nodo anterior a la posición. Conservá primero su sucesor con `nuevo.setSiguiente(actual.getSiguiente());` y luego enlazá el nodo anterior con `actual.setSiguiente(nuevo);`.
> - **Final:** enlazá el nuevo nodo después del último, dejándolo con siguiente `null`; actualizá la cola si la estructura mantiene esa referencia. En una lista vacía, la inserción al final debe dejar el nodo como cabeza y cola si corresponde.
>
> Explicá por qué el orden de reconexión importa: si se cambia primero `actual.siguiente` para apuntar a `nuevo`, se pierde el sucesor anterior; leer después `actual.getSiguiente()` puede devolver el propio nodo nuevo, creando un ciclo y dejando inaccesible el resto de la lista.
>
> Agregá o ajustá pruebas para inserción al inicio, en el medio, al final, en lista vacía, en límites válidos e inválidos, y verificá el orden de los elementos, el tamaño y que no se formen ciclos. No introduzcas cambios ajenos a este ejercicio.
