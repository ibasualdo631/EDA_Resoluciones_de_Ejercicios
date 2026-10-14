# Análisis — Ejercicio 8: Contar ocurrencias de un valor

## Objetivo

Implementar el método `int contarOcurrencias(int dato)` para una lista simplemente enlazada. Debe devolver cuántos nodos de la lista contienen el valor indicado.

## Requerimientos funcionales

- Recorrer los nodos desde el inicio de la lista hasta llegar a `null`.
- Comparar el dato almacenado en cada nodo con el parámetro `dato`.
- Incrementar un contador por cada coincidencia.
- Devolver el total al terminar el recorrido.
- No modificar los nodos ni el enlace de la lista.

## Regla clave: recorrer toda la lista

Encontrar una coincidencia solo demuestra que el valor aparece al menos una vez; no informa cuántas veces aparece. Puede haber más nodos con el mismo valor después de esa primera coincidencia. Por eso, finalizar el recorrido o ejecutar un `break` al encontrarla produciría un conteo incompleto cuando existen duplicados.

El recorrido debe continuar avanzando al siguiente nodo después de cada comparación —haya coincidencia o no— y detenerse únicamente cuando ya no queden nodos (`null`). Así se examinan todas las posiciones y se obtiene el total exacto.

En el ejemplo `10 -> 20 -> 10 -> 30 -> 10 -> null`, para `dato = 10` se deben inspeccionar los cinco nodos y devolver `3`.

## Casos límite y criterios de aceptación

- Lista vacía: devolver `0`.
- Ningún nodo contiene `dato`: devolver `0`.
- Una coincidencia: devolver `1`, pero continuar hasta el final antes de devolver.
- Varias coincidencias, consecutivas o separadas: devolver el número total, sin detenerse en la primera.
- La lista debe conservar su contenido y enlaces tras la llamada.

## Complejidad esperada

- Tiempo: `O(n)`, porque en el caso general se examinan los `n` nodos.
- Espacio adicional: `O(1)`, usando únicamente un contador y una referencia de recorrido.

## Prompt inicial

> Implementá el método `int contarOcurrencias(int dato)` para la lista simplemente enlazada existente, respetando las convenciones y la estructura del proyecto. El método debe recorrer desde el primer nodo hasta `null`, contar todos los nodos cuyo valor sea igual a `dato` y devolver ese total sin modificar la lista. No finalices el recorrido ni uses `break` al encontrar la primera coincidencia: puede haber otras apariciones más adelante y el resultado debe incluirlas todas. Si la lista está vacía o el valor no aparece, devolvé `0`. Verificá el caso `10 -> 20 -> 10 -> 30 -> 10 -> null` con `dato = 10`, que debe devolver `3`, y contemplá también los casos de lista vacía y valor ausente.