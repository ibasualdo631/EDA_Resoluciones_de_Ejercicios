# Ejercicio 3: Obtener un elemento por posición

## Consigna

Implementar `int obtener(int posicion)` para devolver el dato almacenado en la posición indicada de una lista enlazada. Se deben validar las posiciones inválidas: `posicion < 0` y `posicion >= size`.

## Análisis del comportamiento

- La posición se interpreta como un índice basado en cero: el primer nodo corresponde a la posición `0`.
- Para una lista de tamaño `size`, los índices válidos son `0` a `size - 1`. Por ello, una lista vacía no tiene posiciones válidas.
- El método debe devolver el dato del nodo correspondiente sin modificar la lista ni sus enlaces.
- Antes de recorrer o desreferenciar nodos, debe comprobar que la posición esté dentro del rango. Si no lo está, debe lanzar `IndexOutOfBoundsException`; no debe devolver un valor centinela, devolver `null` ni dejar que el recorrido falle con una `NullPointerException`.
- En una lista simplemente enlazada no hay acceso aleatorio por índice como en un arreglo. Cada nodo solo permite llegar al siguiente. El índice identifica cuántos enlaces avanzar desde la cabeza, no una dirección de memoria calculable directamente.
- Para una posición válida `i`, se parte de la cabeza y se siguen enlaces nodo por nodo hasta alcanzar el contador `i`. En el peor caso se recorren `size - 1` enlaces.

## Contrato del método

**Firma:** `int obtener(int posicion)`

**Precondición de acceso válido:** `0 <= posicion < size`.

**Resultado:** si la precondición se cumple, devuelve el dato almacenado en el nodo cuyo índice, contado desde cero, es `posicion`.

**Excepción:** si `posicion < 0` o `posicion >= size`, lanza `IndexOutOfBoundsException` antes de iniciar el recorrido. Esto incluye cualquier índice cuando `size == 0`. La excepción es no comprobada, no se declara en la firma y no se sustituye por un valor centinela ni por otra excepción accidental del recorrido.

**Garantías:** tanto en éxito como ante un índice inválido, la operación no altera la cabeza, el tamaño, los datos ni los enlaces de la lista. Para un índice inválido, no debe comenzar el recorrido. El mensaje de la excepción puede incluir el índice solicitado y el tamaño actual para facilitar el diagnóstico; el tipo de excepción y el punto de validación no son opcionales.

## Complejidad esperada

- Tiempo: `O(n)` en el peor caso, siendo `n` el tamaño de la lista; si la posición es pequeña, se detiene antes.
- Espacio adicional: `O(1)`, usando referencias y un contador constantes.

## Casos relevantes

1. Primer elemento: posición `0`.
2. Elemento intermedio: avanzar desde la cabeza hasta el índice pedido.
3. Último elemento: posición `size - 1`.
4. Lista vacía: cualquier índice debe rechazarse.
5. Índice negativo o igual/mayor que `size`: debe rechazarse antes de intentar recorrer.

## Prompt inicial

> Implementá el método `int obtener(int posicion)` para la lista enlazada existente en este proyecto. Debe devolver el dato del nodo ubicado en `posicion`, usando índices desde cero, y no debe modificar la lista. Antes de iniciar cualquier recorrido o desreferenciar nodos, comprobá que `0 <= posicion < size`; si `posicion < 0` o `posicion >= size`, lanzá `IndexOutOfBoundsException`. No devuelvas un centinela ni permitas que un índice inválido termine en `NullPointerException`. Como la lista enlazada no permite acceso directo por índice como un arreglo, comenzá en la cabeza y avanzá por los enlaces nodo por nodo hasta llegar a la posición solicitada. No conviertas ni copies la lista a un arreglo. Mantené el estilo y los nombres de la implementación existente. Agregá o ajustá pruebas para posición cero, una posición intermedia, la última posición, lista vacía, índice negativo e índice igual o mayor que `size`; verificá que la lista no cambie después de una excepción. Explicá brevemente por qué el acceso requiere recorrido secuencial y cuál es su complejidad temporal y espacial.