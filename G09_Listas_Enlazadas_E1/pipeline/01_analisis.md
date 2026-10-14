# Análisis — Ejercicio 1: lista enlazada simple desde cero

## Objetivo

Implementar en Java una lista enlazada simple de números enteros, construida manualmente y sin utilizar `ArrayList`, `LinkedList` ni estructuras equivalentes de la biblioteca para almacenar sus elementos.

## Requerimientos funcionales

- `insertarAlInicio(int dato)`: incorpora el valor como primer elemento.
- `insertarAlFinal(int dato)`: incorpora el valor después del último elemento.
- `imprimir()`: recorre la lista desde el primero hasta el último nodo y muestra los valores en ese orden.
- `estaVacia()`: informa si la lista no contiene nodos.
- `getSize()`: devuelve el número actual de elementos.

## Representación y reglas de estado

- Cada nodo almacena un `int dato` y una referencia `Nodo siguiente` al próximo nodo. El último nodo tiene `siguiente == null`.
- La lista mantiene `head`, que apunta al primer nodo. Si está vacía, `head == null`. `head` es la entrada para recorrer la estructura; no es el nodo ni contiene el valor del primer elemento.
- La lista mantiene `size`, inicializado en cero. Debe coincidir siempre con el número de nodos alcanzables desde `head`.
- Al insertar en una lista vacía, el nodo nuevo pasa a ser `head`, tanto si se inserta al inicio como al final.
- Al insertar al inicio de una lista no vacía, el nuevo nodo debe enlazarse con el antiguo `head` y luego convertirse en el nuevo `head`.
- Al insertar al final, el nuevo nodo se enlaza después del último nodo; sin un puntero `tail`, se llega a él recorriendo desde `head`.
- Cada inserción exitosa incrementa `size` exactamente una vez, después de enlazar el nuevo nodo. No se debe recalcular el tamaño recorriendo la lista en `getSize()`.
- `estaVacia()` puede comprobar `head == null`; el invariante de tamaño garantiza que esto equivale a `size == 0`. La estructura enlazada encabezada por `head` es la fuente de verdad.

## Casos relevantes

1. Lista recién creada: `head == null`, `size == 0`, está vacía y no tiene elementos para imprimir.
2. Primera inserción, por cualquiera de los dos métodos: la lista queda con un único nodo y `size == 1`.
3. Inserciones sucesivas al inicio y al final: se conservan todos los enlaces y el orden esperado.
4. Una lista con un solo nodo debe pasar correctamente a tener varios nodos al agregar al inicio o al final.

La consigna no prescribe el formato exacto de impresión; el prompt solicita mostrar los elementos en orden, sin imponer una representación textual específica.

## Prompt inicial formal

> Implementá en Java una lista enlazada simple de números enteros desde cero. No utilices `ArrayList`, `LinkedList` ni otras colecciones de Java para almacenar los elementos.
>
> Representá cada nodo con una clase interna o auxiliar que tenga un campo `int dato` y una referencia `Nodo siguiente`. La lista debe tener un puntero `head` al primer nodo: `head` debe ser `null` cuando la lista está vacía y, en caso contrario, permitir iniciar el recorrido de todos los nodos. El último nodo debe apuntar a `null`.
>
> Mantené también un atributo `size`, inicializado en cero, que represente la cantidad actual de nodos. Actualizalo exactamente una vez por cada inserción exitosa y asegurá que siempre coincida con el número de nodos enlazados desde `head`. `getSize()` debe devolver ese atributo sin recorrer la lista. Implementá `estaVacia()` de forma consistente con el estado de la lista.
>
> La lista debe ofrecer estos métodos: `insertarAlInicio(int dato)`, `insertarAlFinal(int dato)`, `imprimir()`, `estaVacia()` y `getSize()`. Al insertar el primer elemento, ambos métodos de inserción deben inicializar `head`. Al insertar al inicio, enlazá el nodo nuevo con el antiguo primer nodo antes de actualizar `head`. Al insertar al final, recorré los enlaces desde `head` hasta el último nodo y agregá allí el nuevo nodo.
>
> Incluí un ejemplo breve de uso que cree una lista, compruebe el estado vacío, inserte varios valores al inicio y al final, imprima los elementos en orden y consulte el tamaño. Explicá los cambios de `head` y `size`, y verificá especialmente la lista vacía y la inserción del primer nodo. El formato textual exacto de `imprimir()` queda a elección, siempre que muestre los elementos desde el primero hasta el último.