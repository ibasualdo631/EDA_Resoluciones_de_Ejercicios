# Análisis del Ejercicio 10: Buscar un elemento en un arreglo recursivamente

## 1. Objetivo del ejercicio

Resolver una búsqueda de un valor dentro de un arreglo utilizando recursividad, sin emplear estructuras de control iterativas como `for` o `while`.

La idea central es recorrer el arreglo de forma recursiva, comparando cada elemento con el valor buscado hasta encontrarlo o llegar al final del arreglo.

## 2. Problema a resolver

Se debe implementar una función que:
- reciba un arreglo,
- reciba el valor a buscar,
- recorra el arreglo de manera recursiva,
- y devuelva la posición si encuentra el elemento,
- o un valor indicador de no encontrado si llega al final sin hallarlo.

## 3. Necesidad del parámetro de índice auxiliar

La recursión necesita saber en qué posición del arreglo está evaluando en cada llamada. Si solamente se pasa el arreglo y el valor buscado, la función no puede diferenciar entre la primera posición, la segunda o la última.

Por eso se agrega un índice auxiliar, por ejemplo:

```cpp
buscar(arr, valor, indice)
```

Este índice cumple la función de “puntero” o “cursor” que avanza por el arreglo.

En cada llamada:
- se compara `arr[indice]` con `valor`,
- si coinciden, se retorna `indice`,
- si no coinciden, se llama nuevamente a la misma función con `indice + 1`.

Esto permite recorrer el arreglo sin usar bucles.

## 4. Lógica recursiva

La función debe seguir esta estructura conceptual:

1. Verificar si el índice actual está dentro del arreglo.
2. Si el elemento actual coincide con el valor buscado, retornar la posición actual.
3. Si no coincide y aún no se llegó al final, invocar la función con el siguiente índice.
4. Si se llega al final del arreglo y no se encontró el valor, retornar `-1` (o `false`, según la convención).

## 5. Casos base

### Caso base de éxito

Cuando el elemento actual del arreglo es igual al valor buscado:

```cpp
if (arr[indice] == valor)
    return indice;
```

Esto significa que la búsqueda terminó con éxito y la función devuelve la posición en la que se encontró el elemento.

### Caso base de fallo

Cuando el índice supera el límite del arreglo o llega a su longitud:

```cpp
if (indice == tamanio)
    return -1;
```

Esto indica que la búsqueda ya recorrió todo el arreglo y no encontró el elemento solicitado.

## 6. Caso recursivo

Cuando el valor no se encontró todavía y el índice no llegó al final:

```cpp
return buscar(arr, valor, indice + 1);
```

La función se invoca a sí misma para seguir recorriendo el arreglo.

## 7. Importancia de la recursión en este ejercicio

Este problema ejemplifica cómo una solución iterativa puede transformarse en una solución recursiva. La diferencia principal es que la recursión reemplaza el ciclo por llamadas sucesivas a la misma función, mientras que el índice hace de sustituto del contador del bucle.

## 8. Conclusión

El ejercicio se resuelve con una función recursiva que:
- usa un índice auxiliar para recorrer el arreglo,
- compara el valor actual con el buscado,
- retorna la posición encontrada en el caso exitoso,
- y retorna `-1` cuando llega al final sin hallarlo.

Esto demuestra una de las ideas fundamentales de la recursividad: dividir el problema en subproblemas más pequeños, avanzando paso a paso hasta alcanzar un caso base.

## 9. Prompt sugerido para OpenCode

"Implementá una función recursiva en C++ que busque un número dentro de un arreglo sin usar `for` ni `while`. La función debe recibir el arreglo, el valor buscado y un índice auxiliar que indica la posición actual. En cada llamada, compara el elemento del arreglo en esa posición con el valor buscado. Si lo encuentra, debe devolver la posición en la que está. Si no coincide y aún no llegó al final del arreglo, debe llamar recursivamente a la función con el índice incrementado en 1. El caso base de éxito es cuando encuentra el valor; el caso base de fallo es cuando el índice llega al final del arreglo y no lo encontró, devolviendo `-1`. Explicá por qué se usa el índice auxiliar y detallá la lógica de los casos base y recursivos."
