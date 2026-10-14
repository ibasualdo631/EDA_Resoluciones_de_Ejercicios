# Ejercicio 7: Modificar un elemento de la lista

## Análisis del enunciado

Se debe implementar `void modificar(int posicion, int nuevoDato)` para reemplazar el valor almacenado en el nodo de una posición determinada. Antes de recorrer la lista, hay que comprobar que la posición sea válida. Si lo es, se avanza hasta el nodo correspondiente y se cambia únicamente su campo `dato`.

El método no debe insertar ni eliminar nodos ni cambiar sus enlaces. La convención de posiciones (base cero o base uno) debe coincidir con la que ya utilice la lista; si todavía no está establecida, debe definirse y documentarse antes de implementar.

Casos que se deben contemplar: lista vacía, posición negativa, posición fuera del rango y modificación de un nodo válido, incluido el primero y el último. La validación debe ocurrir antes de iniciar el recorrido; una posición inválida no debe provocar cambios en la lista.

## Diferencia conceptual clave

- `dato` es el contenido almacenado por un nodo. Cambiar `dato` reemplaza el valor de ese elemento y conserva el nodo y su posición.
- `siguiente` es la referencia que enlaza un nodo con el próximo. Cambiar `siguiente` altera la estructura: puede omitir nodos, desconectar parte de la lista o crear un ciclo.

Por lo tanto, este ejercicio modifica el contenido de un nodo existente, no la cadena de referencias que constituye la lista.

## Prompt inicial

> Analiza la implementación existente de la lista enlazada y completa el método `void modificar(int posicion, int nuevoDato)`. El método debe validar que la posición sea válida antes de recorrer la lista y, cuando lo sea, actualizar únicamente el campo `dato` del nodo ubicado en esa posición. No debe crear ni eliminar nodos ni modificar ninguna referencia `siguiente`. Respeta la convención de indexación, las clases, los nombres y el estilo ya utilizados por el proyecto; si la base de las posiciones no está definida, indícala explícitamente antes de implementarlo. Considera lista vacía, posición negativa, posición fuera de rango y posiciones válidas (incluidos los extremos). Explica brevemente la diferencia entre cambiar el dato del nodo y cambiar su referencia al siguiente nodo.