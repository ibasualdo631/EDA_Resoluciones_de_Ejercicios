# Análisis — Ejercicio 2: Búsqueda secuencial en lista enlazada

## Objetivo

Implementar la búsqueda de un entero en una lista simplemente enlazada y explicar por qué debe realizarse nodo por nodo, desde `head`, hasta encontrar el valor o llegar a `null`.

## Requerimiento funcional

- Incorporar el método `boolean buscar(int dato)`.
- Devolver `true` si algún nodo contiene `dato`; devolver `false` si se alcanza el final sin encontrarlo.
- Empezar el recorrido en `head` y avanzar siguiendo el enlace al nodo siguiente.

## Fundamento conceptual

En un arreglo, los elementos se almacenan en posiciones contiguas. Conociendo la dirección inicial y el tamaño de cada elemento, se puede calcular directamente dónde está el índice `i`; por eso el acceso por índice es constante.

En una lista enlazada, cada nodo puede estar en una ubicación distinta de memoria. No existe una dirección inicial desde la cual calcular la dirección del nodo número `i` mediante un desplazamiento fijo. Para llegar a una posición posterior hay que seguir los enlaces: desde `head`, consultar cada nodo y avanzar al siguiente. Por tanto, la búsqueda general requiere un recorrido secuencial y su tiempo es O(n) en el peor caso, con O(1) de espacio adicional.

## Casos relevantes

- Lista vacía (`head == null`): devolver `false`.
- Valor en el primer nodo: devolver `true` sin continuar.
- Valor en un nodo posterior, incluido el último: devolver `true`.
- Valor ausente: recorrer hasta `null` y devolver `false`.

## Prompt inicial

> Implementá el método `boolean buscar(int dato)` para una lista simplemente enlazada de enteros. El recorrido debe comenzar en `head` y avanzar nodo por nodo usando el enlace al siguiente nodo; debe detenerse y devolver `true` al encontrar un nodo cuyo dato sea igual a `dato`, o devolver `false` al llegar a `null`. Considerá también el caso de una lista vacía. Explicá conceptualmente por qué no se puede acceder directamente a un nodo por índice como en un arreglo: los nodos de la lista no ocupan necesariamente posiciones contiguas de memoria, así que no se puede calcular la dirección de un nodo a partir de un desplazamiento fijo. Por eso, para alcanzar un nodo o buscar un valor, es necesario seguir secuencialmente los enlaces desde `head`. Indicá la complejidad temporal y espacial del método.