# Cola FIFO y limitaciones del arreglo lineal

## Por que la cola es FIFO

FIFO viene de *First In, First Out*: el primer elemento que entra es el primero
que sale. La clase mantiene esa regla porque separa los dos extremos de la
cola:

- `enqueue(valor)` agrega el nuevo elemento al final, en la posicion indicada
  por `rearIndex`.
- `dequeue()` devuelve y elimina el elemento del frente, en la posicion
  indicada por `frontIndex`.

Por ejemplo, si se insertan `10`, `20` y `30`, quedan en ese orden. Cada
extraccion toma el elemento apuntado por `frontIndex`, por lo que salen
`10`, despues `20` y finalmente `30`. Las inserciones posteriores no cambian
el orden de los elementos que ya estaban esperando.

`cantidad` registra cuántos elementos hay actualmente. Al insertar aumenta en
uno y al extraer disminuye en uno. Cuando se extrae el ultimo elemento, la
implementacion restablece `frontIndex` a `0` y `rearIndex` a `-1`. Asi, la
siguiente insercion vuelve a iniciar la cola desde el principio del arreglo.

## Limitaciones fisicas del diseño lineal

El arreglo tiene una capacidad fija y los indices avanzan solo hacia adelante:
`frontIndex` avanza al quitar elementos y `rearIndex` avanza al agregarlos.
Los espacios que quedan antes de `frontIndex` no se reutilizan mientras la cola
no llegue a vaciarse por completo. No se desplazan elementos ni se hace volver
`rearIndex` al comienzo del arreglo.

Esto puede dejar posiciones libres al principio y, al mismo tiempo, impedir
nuevas inserciones al final. Por ejemplo, con capacidad `5`, se insertan cinco
valores y luego se extraen dos. Quedan tres valores en las posiciones `2`, `3`
y `4`, pero `rearIndex` ya esta en la ultima posicion. `isFull()` devuelve
`true` y no se puede insertar otro elemento, aunque las posiciones `0` y `1`
esten vacias.

En consecuencia, la capacidad utilizable puede reducirse temporalmente por la
forma en que avanzan los indices. Solo al vaciar toda la cola los indices se
reinician y se recupera el arreglo completo. Este diseno, ademas, no puede
crecer mas alla de la capacidad asignada al construir el objeto.

Estas restricciones son propias de una cola lineal basica, no un error
respecto de la politica solicitada. Una cola circular reutilizaria las
posiciones liberadas al principio, y desplazar los elementos tambien podria
compactarlos; ambas alternativas cambiarian el diseno indicado para este
ejercicio.