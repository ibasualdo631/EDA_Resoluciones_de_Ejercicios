# Ejercicio 7: modificar un elemento por posición

## Qué debe hacer el método

La operación recibe una posición y un nuevo valor. Primero determina si esa posición pertenece a la lista; solo después recorre los nodos. Al llegar al nodo buscado, reemplaza su dato. La lista conserva la misma cantidad de nodos y el mismo orden.

## `dato` y `siguiente` representan cosas distintas

Un nodo de una lista enlazada contiene, conceptualmente, dos partes:

- `dato`: el valor que representa el elemento.
- `siguiente`: la referencia al nodo que viene después.

Modificar `dato` es como corregir el contenido de una casilla sin moverla. El nodo sigue siendo el mismo y sus vecinos siguen enlazados igual. En cambio, modificar `siguiente` cambia qué nodo viene después: eso puede alterar el orden, dejar nodos inaccesibles o, si se enlaza con un nodo anterior, formar un ciclo.

En este ejercicio corresponde cambiar solo `dato`. No hace falta reemplazar el nodo ni tocar `siguiente`.

## Por qué la topología no cambia

La topología es la forma en que los nodos están conectados. Si la lista tiene la forma `A -> B -> C`, esa conexión está guardada en las referencias `siguiente`: `A.siguiente` apunta a `B` y `B.siguiente` apunta a `C`.

El método primero encuentra el objeto nodo que ocupa la posición indicada y luego ejecuta `actual.dato = nuevoDato`. Esa asignación reemplaza el valor de un campo dentro del mismo objeto. No crea otro nodo, no cambia la identidad del nodo seleccionado y no asigna nada a `siguiente`. Por ello, las referencias del nodo anterior y del nodo seleccionado siguen apuntando a los mismos objetos que antes; las conexiones entrantes y salientes se conservan.

Por ejemplo, si `B.dato` cambia de `20` a `99`, la secuencia sigue siendo `A -> B -> C`; solamente cambia lo que almacena B. Esto también mantiene el tamaño de la lista. La topología sí se alteraría si se reemplazara `B.siguiente` por otra referencia, pues entonces cambiaría el nodo que sigue a B.

## Recorrido y validación

Una lista enlazada se recorre siguiendo referencias: se parte de la cabeza y se avanza de nodo en nodo hasta alcanzar la posición buscada. Como cada avance depende de `siguiente`, una posición inválida debe rechazarse antes de iniciar ese recorrido, tal como indica el enunciado.

La posición debe interpretarse con la misma convención que usa el resto del proyecto (por ejemplo, comenzar en cero). Una vez validada, el método avanza hasta el nodo objetivo y asigna `nuevoDato` a su campo `dato`. Si la posición no es válida, la lista debe permanecer sin cambios.

## Errores comunes que se evitan

- Asignar `nuevoDato` a `siguiente`: el dato es el valor; `siguiente` es un enlace.
- Cambiar enlaces al modificar un valor: no se está insertando ni eliminando ningún nodo.
- Recorrer antes de comprobar el rango: puede causar una referencia nula o un acceso inválido.
- Interpretar la posición con una base distinta a la usada por las demás operaciones.