# Análisis del ejercicio: encontrar el mínimo de un vector

## 1. Enunciado y objetivo

Implementar en Java un algoritmo que encuentre y devuelva el menor valor de un vector de números enteros. La solución debe explicar la estrategia, justificar su eficiencia y contemplar entradas inválidas y casos límite.

El algoritmo trabaja sobre un arreglo primitivo `int[]`. Para que el resultado esté definido, se asume que el vector debe contener al menos un elemento. Si el arreglo es `null` o está vacío, se recomienda rechazar la entrada mediante `IllegalArgumentException`, según los requisitos de entrega.

## 2. Estrategia adecuada: recorrido lineal con variable de seguimiento

Se inicializa una variable `minimo` con el primer elemento del vector. Luego se recorre el resto del arreglo, desde el índice `1` hasta `n - 1`. Cada vez que se encuentra un valor menor que `minimo`, se actualiza la variable. Al finalizar, `minimo` contiene el menor elemento.

La inicialización con el primer valor real del vector es importante: evita asumir incorrectamente que los datos son positivos o que existe un valor centinela adecuado. Por ejemplo, inicializar el mínimo en `0` daría un resultado incorrecto si todos los elementos fueran positivos.

La estrategia es iterativa, realiza una comparación por elemento después del primero y utiliza una cantidad constante de memoria adicional.

## 3. Por qué no es necesario ordenar

Ordenar el vector antes de buscar su mínimo resuelve un problema más general que el solicitado. Una vez ordenado, el mínimo estaría en la primera posición, pero ordenar requiere normalmente `O(n log n)` tiempo para algoritmos eficientes de comparación. En cambio, un recorrido lineal encuentra el mínimo directamente en `O(n)`, sin modificar ni reordenar los datos.

Además, como no se puede descartar ninguna posición sin información adicional, el recorrido lineal ya es asintóticamente óptimo: hay que inspeccionar todos los valores para garantizar cuál es el menor. No conviene atribuir universalmente un costo espacial `O(n)` a cualquier ordenamiento: su espacio auxiliar depende del algoritmo y de la implementación. La ventaja del recorrido aquí sí es clara y estable: usa `O(1)` espacio auxiliar y evita el trabajo innecesario de ordenar.

## 4. Complejidad asintótica

Sea `n` la longitud del vector válido:

| Medida | Complejidad | Justificación |
|---|---:|---|
| Mejor caso | `Ω(n)` | También hay que inspeccionar los `n` elementos, aunque el mínimo esté al comienzo. |
| Peor caso | `O(n)` | Se procesa cada elemento una vez; el mínimo puede estar al final. |
| Caso promedio | `Θ(n)` | El número de inspecciones crece linealmente, independientemente de la distribución. |
| Espacio auxiliar | `O(1)` | Solo se necesitan el índice del recorrido y la variable que guarda el mínimo. |

En el modelo de RAM, el acceso por índice y la comparación de enteros se consideran operaciones de costo constante. Como cada elemento debe ser leído al menos una vez para descartar que sea menor que el candidato actual, no existe corte anticipado correcto para una entrada sin ordenar.

## 5. Casos límite y comportamiento esperado

| Entrada o situación | Comportamiento esperado |
|---|---|
| Referencia `null` | Lanzar `IllegalArgumentException` antes de acceder al arreglo. |
| Vector vacío (`length == 0`) | Lanzar `IllegalArgumentException`; no existe un mínimo definido. |
| Un solo elemento | Devolver ese elemento; no se realizan iteraciones de comparación adicionales. |
| Elementos repetidos | Devolver el valor mínimo; no es necesario distinguir sus posiciones. |
| Todos los valores positivos | Encontrar el menor positivo; la inicialización debe ser `vector[0]`, no cero. |
| Valores negativos | Comparar correctamente, incluso si todos son negativos. |
| Mínimo al inicio, medio o final | Devolver el mismo valor; el recorrido completo debe continuar en los tres casos. |
| Valor `Integer.MIN_VALUE` | Devolverlo correctamente; la comparación no debe depender de operaciones que puedan desbordar. |
| Vector ascendente o descendente | Devolver el primer o último valor, respectivamente; siempre se inspeccionan todos los elementos. |

## 6. Requisitos funcionales y de calidad para la implementación

1. Definir una clase clara, por ejemplo `MinimoVector`.
2. Exponer un método estático público con firma `public static int encontrarMinimo(int[] vector)`.
3. Validar `null` y longitud cero con `IllegalArgumentException`.
4. Inicializar `minimo` con `vector[0]` y recorrer iterativamente desde el índice `1`.
5. Mantener el vector sin ordenar ni modificar.
6. Incluir en la cabecera del archivo el prompt inicial utilizado y los ajustes realizados durante el desarrollo.
7. Documentar en el código por qué ordenar no es necesario: el recorrido cuesta `O(n)` frente a `O(n log n)` de ordenar para luego consultar el mínimo.
8. Incluir un `main` que muestre pruebas de consola con enteros positivos, negativos, repetidos, datos desordenados y un único elemento.
9. Comprobar además las entradas `null` y vacía y vectores ascendentes y descendentes. Las pruebas deben confirmar tanto los resultados como las excepciones esperadas.
10. Presentar la justificación de complejidad temporal y espacial de forma consistente con el algoritmo implementado.

## 7. Borrador del prompt técnico inicial para Planificador y Desarrollador

> **Actúa primero como Planificador / Software Architect y luego como Desarrollador Java Senior.** Analiza e implementa el ejercicio: “Encontrar el valor mínimo de un vector de números enteros”.
>
> **Planificación:** define una clase `MinimoVector` y el método público estático `public static int encontrarMinimo(int[] vector)`. Establece el contrato de entrada: el vector no puede ser `null` ni vacío; en cualquiera de esos casos se debe lanzar `IllegalArgumentException`. Explica que se inicializa el mínimo con el primer elemento y se recorre linealmente el resto del arreglo. Incluye la matriz de complejidad: mejor caso `Ω(n)`, peor caso `O(n)`, promedio `Θ(n)` y espacio auxiliar `O(1)`. Justifica que no hay corte anticipado porque cualquier elemento restante podría ser menor.
>
> **Implementación:** escribe una solución Java completa y compilable. Incluye en la cabecera un bloque de comentario con este prompt inicial y cualquier ajuste aplicado. El método debe validar el arreglo, inicializar `minimo` con `vector[0]` y recorrer desde el índice `1` hasta `n - 1`, sin ordenar ni modificar el vector. Agrega comentarios que expliquen por qué ordenar antes sería innecesario e ineficiente: el barrido resuelve el problema en `O(n)`, mientras que ordenar primero requiere normalmente `O(n log n)` tiempo.
>
> Incluye un `main` con pruebas de consola que cubran valores positivos, negativos, desordenados, repetidos y un solo elemento. Verifica también un mínimo ubicado al inicio, en el medio y al final, arreglos ascendentes y descendentes, `Integer.MIN_VALUE`, y las entradas `null` y vacía, confirmando en estas dos últimas que se lanza `IllegalArgumentException`. Finaliza explicando brevemente el funcionamiento, la complejidad temporal y espacial y el resultado de las pruebas. Mantén la explicación y el código adecuados para una entrega académica de Análisis de Algoritmos.

## 8. Criterio de aceptación

La entrega se considera correcta si devuelve el mínimo para todo vector no vacío de enteros, rechaza explícitamente las entradas inválidas, no altera el arreglo, cubre los casos límite indicados y justifica correctamente que el algoritmo requiere `Θ(n)` tiempo y `O(1)` espacio auxiliar. Debe quedar claro que un recorrido completo es necesario aun cuando el mínimo aparezca al principio.
