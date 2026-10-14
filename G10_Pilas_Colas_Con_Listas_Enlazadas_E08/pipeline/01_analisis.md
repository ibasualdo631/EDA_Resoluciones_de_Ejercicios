# Análisis — Ejercicio 8: invertir una palabra con pila

## Objetivo

Definir el comportamiento requerido para un programa que reciba una palabra y produzca otra con los mismos caracteres en orden inverso. La solución debe utilizar una pila enlazada.

## Enunciado y ejemplo

El ejercicio solicita invertir una palabra mediante una pila enlazada y explicar por qué la política LIFO permite invertir el orden de los caracteres.

- Entrada de ejemplo: `algoritmo`
- Salida esperada: `omtirogla`

La salida conserva todos los caracteres de la entrada; únicamente cambia su orden.

## Requerimientos funcionales

1. Recibir una palabra como secuencia de caracteres.
2. Insertar los caracteres en una pila enlazada, uno por nodo.
3. Extraer los caracteres de la pila y concatenarlos para formar el resultado.
4. Devolver o mostrar la palabra invertida.
5. Explicar que LIFO (*Last In, First Out*: último en entrar, primero en salir) hace que el último carácter insertado sea el primero extraído, revirtiendo así el orden.

## Regla algorítmica y fundamento

Al recorrer `algoritmo` de izquierda a derecha, se apilan sucesivamente `a`, `l`, `g`, `o`, `r`, `i`, `t`, `m` y `o`. Por la regla LIFO, al desapilar se obtienen `o`, `m`, `t`, `i`, `r`, `o`, `g`, `l` y `a`; al concatenarlos se forma `omtirogla`.

La pila enlazada debe mantener una referencia a su cima. Apilar agrega un nodo en la cima y desapilar retira y devuelve el nodo de esa cima. Cada carácter se apila y desapila una vez, por lo que el tiempo esperado es lineal respecto de la cantidad de caracteres; el espacio auxiliar también es lineal.

## Entradas límite y comportamiento esperado

- **Cadena vacía:** no contiene caracteres que procesar; el resultado debe ser una cadena vacía y no debe intentarse desapilar una pila vacía.
- **Un solo carácter:** el resultado es idéntico a la entrada.
- **Caracteres repetidos:** se conservan todas las apariciones y se invierten sus posiciones; no se eliminan duplicados.
- **Mayúsculas, minúsculas y signos:** se conservan sin modificación; solo se invierte el orden.

## Supuestos y aspectos por confirmar

- Se considera que la entrada es una palabra o cadena, no una línea con espacios. El enunciado no especifica cómo tratar espacios.
- No se especifica la normalización ni la semántica para caracteres Unicode compuestos. La implementación deberá seguir la unidad de carácter definida por el lenguaje y el alcance académico del ejercicio.
- No se indica si el resultado debe imprimirse o retornarse desde un método; ambas opciones satisfacen la transformación si se documenta la interfaz elegida.

## Criterios de aceptación

- La estructura utilizada es una pila enlazada, no una inversión basada únicamente en índices o en una biblioteca de cadenas.
- Para toda entrada de longitud `n`, el resultado tiene longitud `n` y contiene los mismos caracteres en orden inverso.
- Se maneja la entrada vacía sin error.
- La explicación conecta explícitamente el orden de apilado, la extracción LIFO y la cadena resultante.