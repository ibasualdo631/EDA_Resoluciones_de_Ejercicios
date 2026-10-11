# Explicación — Invertir un vector

## Idea general

Invertir un vector significa cambiar el orden de sus elementos: el primero pasa al final, el segundo al penúltimo, y así sucesivamente. El ejercicio ofrece dos maneras de hacerlo: modificar el vector original o construir otro vector con los elementos en orden inverso.

## Inversión in situ

La clase `invertirVectorInSitu` trabaja directamente sobre el arreglo que recibe:

1. Si la referencia es `null`, lanza `IllegalArgumentException` porque no hay un arreglo válido que procesar.
2. Si el vector está vacío o tiene un solo elemento, muestra un aviso y lo devuelve sin cambios.
3. Para los demás casos, usa dos índices: `izquierda` empieza al principio y `derecha` al final.
4. Intercambia los elementos de esas posiciones y mueve ambos índices hacia el centro.
5. Cuando los índices se encuentran o se cruzan, todos los elementos ya están en orden inverso. Devuelve la misma referencia que recibió.

La variable temporal `temporal` permite intercambiar dos valores sin perder ninguno. Por ejemplo, con `[1, 2, 3, 4]` se intercambian primero `1` y `4`, luego `2` y `3`, y queda `[4, 3, 2, 1]`.

Esta opción tarda Θ(n) para vectores de longitud `n` y usa Θ(1) de espacio adicional: solo necesita los dos índices y una variable temporal. Mejor, promedio y peor caso tienen el mismo orden, ya que el trabajo depende de la longitud, no de los valores.

## Inversión con arreglo auxiliar

La clase `InvertirVectorAuxiliar` conserva intacto el arreglo original:

1. Si la referencia es `null`, lanza `IllegalArgumentException`.
2. Si el vector tiene menos de dos elementos, muestra el aviso correspondiente.
3. Crea un arreglo nuevo del mismo tamaño.
4. Recorre el original y copia cada elemento a la posición opuesta: el elemento `i` se coloca en `longitud - 1 - i`.
5. Devuelve el nuevo arreglo.

Por ejemplo, para `[1, 2, 3, 4, 5]`, el primer elemento se escribe en la posición 4, el segundo en la 3 y así hasta obtener `[5, 4, 3, 2, 1]`.

También requiere Θ(n) de tiempo en mejor, promedio y peor caso, pero ocupa Θ(n) espacio adicional porque crea otro arreglo. A cambio, permite mantener el original sin cambios.

## Pruebas en consola

`Ejercicio7` contiene el método `main`, que llama a las pruebas de los casos solicitados y a un caso adicional de longitud par:

- Vector vacío y vector de un elemento: comprueba que se conserva el contenido y que se muestra un aviso.
- Vector `null`: comprueba que cada método lanza `IllegalArgumentException`.
- Vector `[1, 2, 3, 4, 5]`: comprueba que se obtiene `[5, 4, 3, 2, 1]`.
- Vector par `[1, 2, 3, 4]`: comprueba que se obtiene `[4, 3, 2, 1]`.
- Vector capicúa `[1, 2, 3, 2, 1]`: comprueba que su reverso coincide con el original.

Los métodos auxiliares de prueba comparan los contenidos y verifican las referencias: in situ devuelve la misma referencia; la versión auxiliar devuelve una distinta y deja sin modificar el arreglo inicial. Si una comprobación falla, se lanza `AssertionError`; por eso el mensaje final de éxito solo aparece cuando pasan todas.

## ¿Cuál conviene usar?

Si es necesario conservar el vector original, conviene la versión auxiliar, aceptando el uso de Θ(n) espacio extra. Si se puede modificar el vector y se quiere reducir el uso de memoria, conviene la versión in situ, que usa Θ(1) espacio adicional.

## Preguntas para comprobar la comprensión

1. Para el vector `[2, 5, 8, 9]`, ¿qué parejas de posiciones intercambia el método in situ y cuál es el resultado final?
2. ¿Qué diferencia hay entre las dos versiones respecto a la referencia y al contenido del vector original, y cómo se refleja esa diferencia en su complejidad espacial?