# Explicación — Conteo de ocurrencias en una lista enlazada

## ¿Qué hace `contarOcurrencias`?

El método devuelve cuántos nodos de la lista contienen el valor recibido en `dato`. Para ello utiliza dos variables:

- `actual` señala el nodo que se está examinando.
- `contador` acumula la cantidad de coincidencias encontradas.

El método inicia `actual` en la cabeza de la lista y `contador` en cero. Mientras `actual` no sea `null`, compara el dato del nodo con el valor buscado; si coinciden, incrementa el contador. Luego avanza siempre a `actual.siguiente`. Cuando llega al final, devuelve el total acumulado. Al tratarse de un recorrido de lectura, no cambia los nodos ni sus enlaces.

## Por qué el conteo global requiere inspeccionar todos los nodos

El resultado solicitado es la cantidad **total** de apariciones, no solo saber si el valor está presente. Cada nodo puede aportar una aparición más al resultado. Hasta examinar un nodo, no se puede saber si contiene el valor, y encontrar una coincidencia tampoco permite concluir que no haya otras después.

Por ejemplo, en `10 -> 20 -> 10 -> 30 -> 10 -> null`, encontrar el primer `10` no basta: todavía quedan nodos que podrían contener más apariciones. Solo después de revisar toda la lista se puede afirmar correctamente que el total es `3`.

En consecuencia, el bucle no puede terminar en la primera coincidencia. Debe avanzar tanto cuando encuentra el valor como cuando no lo encuentra y finalizar únicamente al llegar a `null`.

## Complejidad temporal

Si la lista tiene `n` nodos, el bucle visita cada uno exactamente una vez. En cada visita hace trabajo constante: una comparación, posiblemente un incremento y el avance al siguiente nodo. El trabajo total crece proporcionalmente a `n`, por lo que la complejidad temporal es `O(n)`.

Como el método no termina anticipadamente ni siquiera cuando `dato` no aparece, el número de visitas es `n` para cualquier contenido de una lista de ese tamaño. Más precisamente, para listas no vacías el coste es `Θ(n)` en los casos mejor, promedio y peor; por tanto, también es `O(n)`. Si la lista está vacía (`n = 0`), el bucle no se ejecuta y queda únicamente el coste constante de inicialización y retorno.

El atributo `size`, si está disponible, indica cuántos nodos hay, pero no cuántos contienen un dato particular. Sin una estructura auxiliar que mantenga índices o frecuencias actualizados, hace falta inspeccionar cada nodo para calcular el conteo exacto.

## Complejidad espacial

El recorrido mantiene solo `actual` y `contador`, independientemente de cuántos nodos tenga la lista. No crea una colección ni copia los elementos. Por eso, el espacio adicional es `O(1)`.