# Explicación didáctica — Obtener un elemento por posición

## Qué solicita el ejercicio

El método `int obtener(int posicion)` recibe un número que representa el índice del dato que queremos consultar y devuelve ese dato. La operación es de lectura: obtener un valor no debería insertar ni eliminar nodos, ni cambiar los enlaces de la lista.

La consigna define dos condiciones inválidas: `posicion < 0` y `posicion >= size`. Esto corresponde a índices desde cero. Si hay `size` elementos, sus índices posibles van de `0` hasta `size - 1`; por ejemplo, en una lista de cuatro nodos se puede pedir `0`, `1`, `2` o `3`, pero no `4`.

## Costo de acceder por posición: lista enlazada frente a vector estático

El índice indica qué elemento queremos, pero el costo de encontrarlo depende de cómo están organizados los datos.

En un **vector estático** (arreglo), los elementos ocupan posiciones contiguas de memoria. Conociendo la dirección inicial y el índice `i`, se calcula directamente la ubicación del elemento —conceptualmente, `dirección inicial + i × tamaño de cada elemento`—. No hace falta visitar los elementos anteriores: el trabajo no crece con `i` ni con el tamaño del vector. Por eso el acceso `vector[i]` cuesta **O(1)**.

En una **lista simplemente enlazada**, cada nodo contiene el dato y una referencia al siguiente nodo. La cabeza (`head`) solo da acceso directo al primer nodo; no existe una fórmula para saltar al nodo de índice `i`. Para llegar allí, `obtener(i)` debe comenzar en `head` y seguir los enlaces uno por uno:

```text
head -> nodo 0 -> nodo 1 -> ... -> nodo i
```

Para el índice `i`, se siguen `i` enlaces (y se visita el nodo que contiene el dato). Así, el trabajo **crece con la posición solicitada**: el primer elemento cuesta O(1), mientras que el último de una lista de `n` elementos puede requerir `n - 1` avances. En el peor caso, el acceso cuesta **O(n)**. Si se busca una posición cualquiera y no se sabe cuál será, su costo también se expresa como O(n) en función del tamaño de la lista.

| Estructura | Cómo llega al elemento `i` | Costo por índice |
|---|---|---|
| Vector estático | Calcula directamente su ubicación en memoria | O(1) |
| Lista enlazada simple | Recorre los enlaces desde la cabeza hasta `i` | O(n) en el peor caso |

En resumen: en el vector, el acceso no depende de cuántos elementos lo preceden; en la lista enlazada simple, sí. Este costo es el motivo por el que las listas enlazadas son menos convenientes cuando se necesitan muchas consultas por índice, aunque puedan ser útiles para otras operaciones de inserción o eliminación según la posición y las referencias disponibles.

## Recorrido paso a paso

La estrategia esperada es:

1. Comprobar los límites antes de recorrer. La posición debe ser al menos `0` y menor que el tamaño de la lista.
2. Crear una referencia auxiliar que comience en la cabeza y un contador que represente la posición del nodo actual.
3. Mientras el contador sea menor que el índice pedido, avanzar la referencia al siguiente nodo y aumentar el contador.
4. Cuando el contador coincida con la posición solicitada, devolver el dato de ese nodo.

La referencia auxiliar permite caminar por la estructura sin perder ni modificar la cabeza de la lista. El contador conecta cada nodo visitado con su índice lógico. La validación previa evita avanzar sobre una referencia nula cuando el índice no existe.

Para un índice válido `i`, se siguen `i` enlaces desde el nodo inicial. Para `i == 0` no se avanza; en el peor caso, al pedir el último elemento, se realizan `n - 1` avances para una lista de `n` nodos. Por eso el tiempo es `O(n)` en el peor caso (y `O(1)` para el primer elemento). El recorrido solo requiere una referencia y un contador, así que el espacio adicional es `O(1)`.

## Errores comunes que conviene evitar

- Tratar la lista como si admitiera `lista[posicion]` o acceso aleatorio de arreglo.
- Olvidar que el primer elemento tiene índice `0`.
- Permitir `posicion == size`, aunque parezca una posición “al final”; esa posición es posterior al último elemento.
- Empezar el recorrido sin validar el rango, lo que podría causar un error accidental al desreferenciar un nodo nulo.
- Cambiar la cabeza o los enlaces durante una operación que solo debe consultar.
- Recorrer hasta el final aunque la posición se haya alcanzado antes.

## Mejora posible

Si muchas consultas por índice fueran una necesidad central, una lista enlazada simple podría no ser la estructura adecuada: cada consulta puede requerir un recorrido lineal. Un arreglo ofrece acceso por índice en tiempo constante, aunque tiene otros compromisos de inserción y eliminación. Para este ejercicio, se debe conservar la lista enlazada y realizar el recorrido secuencial solicitado.

## Prompt inicial redactado

> Implementá el método `int obtener(int posicion)` para la lista enlazada existente en este proyecto. Debe devolver el dato del nodo ubicado en `posicion`, usando índices desde cero, y no debe modificar la lista. Validá primero que `posicion` esté en el rango `[0, size)`: si es inválida, lanzá `IndexOutOfBoundsException` (o respetá la excepción equivalente que ya use el proyecto). Como la lista enlazada no permite acceso directo por índice como un arreglo, comenzá en la cabeza y avanzá por los enlaces nodo por nodo hasta llegar a la posición solicitada. No conviertas ni copies la lista a un arreglo. Mantené el estilo y los nombres de la implementación existente. Agregá o ajustá pruebas para posición cero, una posición intermedia, la última posición, lista vacía, índice negativo e índice igual o mayor que `size`. Explicá brevemente por qué el acceso requiere recorrido secuencial y cuál es su complejidad temporal y espacial.