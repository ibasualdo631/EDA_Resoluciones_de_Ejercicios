# Análisis del Ejercicio 7: Peor caso de Quicksort

## 1. Contexto del problema

El ejercicio propone aplicar el algoritmo Quicksort a un arreglo ya ordenado de forma ascendente:

```java
int[] numeros = {1, 2, 3, 4, 5, 6, 7};
```

Además, el programa debe contar la cantidad de llamadas recursivas realizadas. La consigna final pide que el alumno explique por qué elegir siempre el primer elemento como pivote puede provocar un rendimiento muy malo en este caso particular.

## 2. Qué ocurre con el pivote en un arreglo ya ordenado

En Quicksort, el pivote es el elemento que se usa para dividir el arreglo en dos partes:

- elementos menores o iguales que el pivote
- elementos mayores que el pivote

Si se elige como pivote el primer elemento del arreglo, y ese arreglo ya está ordenado, entonces el pivote siempre es el menor elemento de la sublista actual.

Por ejemplo, con `{1, 2, 3, 4, 5, 6, 7}`:

- Primer pivote: `1`
- Todos los demás elementos son mayores que `1`
- Resultado de la partición: una sublista izquierda vacía y una sublista derecha con 6 elementos

Es decir, la partición no divide el problema de manera equilibrada; en lugar de partirlo a la mitad, produce una partición de tamaño `0` y `n - 1`.

## 3. Por qué esto genera un rendimiento paupérrimo

El caso más importante es que el algoritmo no logra disminuir el tamaño del problema de forma balanceada. En vez de dividir en dos mitades, lo hace en:

- `0` elementos en una parte
- `n - 1` elementos en la otra

Esto convierte la recursión en una estructura tipo cadena, donde cada llamada resuelve un problema sólo un poco menor que el anterior.

### Recurrencia del tiempo

Si el pivote siempre es el menor elemento, la relación de recurrencia es aproximadamente:

```text
T(n) = T(n - 1) + T(0) + O(n)
```

Como `T(0)` es constante y la partición cuesta `O(n)`, se reduce a:

```text
T(n) = T(n - 1) + O(n)
```

Esto da como resultado:

```text
T(n) = O(n^2)
```

La razón es que, en cada nivel, se realiza trabajo lineal sobre el arreglo y, además, hay `n` niveles de recursión.

## 4. Ejemplo concreto con {1,2,3,4,5,6,7}

Con un arreglo de 7 elementos, la secuencia de particiones sería:

1. `quicksort({1,2,3,4,5,6,7})`
   - pivote = `1`
   - sublista izquierda: `[]`
   - sublista derecha: `{2,3,4,5,6,7}`

2. `quicksort({2,3,4,5,6,7})`
   - pivote = `2`
   - sublista izquierda: `[]`
   - sublista derecha: `{3,4,5,6,7}`

3. `quicksort({3,4,5,6,7})`
   - pivote = `3`
   - sublista izquierda: `[]`
   - sublista derecha: `{4,5,6,7}`

4. `quicksort({4,5,6,7})`
   - pivote = `4`
   - sublista izquierda: `[]`
   - sublista derecha: `{5,6,7}`

5. `quicksort({5,6,7})`
   - pivote = `5`
   - sublista izquierda: `[]`
   - sublista derecha: `{6,7}`

6. `quicksort({6,7})`
   - pivote = `6`
   - sublista izquierda: `[]`
   - sublista derecha: `{7}`

7. `quicksort({7})`
   - base case

Esto genera una cadena de llamadas recursivas casi lineal en profundidad, equivalente a recorrer el arreglo una y otra vez.

## 5. Cantidad de llamadas recursivas

Si el algoritmo se invoca con el arreglo completo y luego se llama recursivamente sobre la sublista derecha, la cantidad de llamadas es aproximadamente `n` para un arreglo de tamaño `n`:

```text
n = 7  -> 7 llamadas recursivas principales
```

En un caso peor, el número total de comparaciones y movimientos se comporta como:

```text
7 + 6 + 5 + 4 + 3 + 2 + 1 = 28
```

y, en general, la suma es:

```text
1 + 2 + 3 + ... + n = O(n^2)
```

Esto confirma que la estrategia de elegir el primer elemento como pivote en un arreglo ya ordenado es claramente ineficiente.

## 6. Por qué no es una buena elección en este caso

En un arreglo ordenado, el primer elemento es el mínimo del conjunto. Esto hace que, al elegirlo como pivote:

- el lado izquierdo queda vacío,
- el lado derecho conserva casi todo el problema,
- la recursión deja de ser balanceada,
- la complejidad empeora de `O(n log n)` a `O(n^2)`.

Este comportamiento se conoce como peor caso del Quicksort.

## 7. Conclusión pedagógica

El ejercicio busca mostrar que el rendimiento del Quicksort depende mucho de la elección del pivote. Aunque el algoritmo es rápido en promedio, una mala elección puede degradarlo drásticamente.

En un arreglo ya ordenado, si siempre se toma el primer elemento como pivote, el algoritmo se vuelve casi equivalente a un ordenamiento secuencial con muchos pasos redundantes. Por eso, en la práctica, se suelen usar estrategias como:

- elegir el pivote central,
- elegir el pivote aleatorio,
- usar la mediana de tres,
- o aplicar técnicas de mejora para reducir el peor caso.

## 8. Prompt propuesto para el ejercicio

> Implementar Quicksort sobre un arreglo ya ordenado.
>
> Ejemplo:
>
> ```java
> int[] numeros = {1, 2, 3, 4, 5, 6, 7};
> ```
>
> El programa debe contar la cantidad de llamadas recursivas realizadas.
>
> El objetivo es que el alumno explique en el prompt por qué elegir siempre el primer elemento como pivote puede generar un mal rendimiento.
>
> Explicar detalladamente qué ocurre cuando el arreglo ya está ordenado y se toma como pivote el primer elemento. Deben mostrar por qué la partición queda desbalanceada, generando una sublista vacía y otra casi completa. Además, justificar por qué esto produce un peor caso del algoritmo, con complejidad O(n^2) en lugar de O(n log n).
>
> El alumno debe incluir un análisis del comportamiento recursivo y la cantidad de llamadas que se realizan en este caso particular.

## 9. Resumen breve

Si el arreglo viene ordenado y se usa siempre el primer elemento como pivote, el algoritmo entra en su peor caso. En lugar de dividir el problema en dos mitades, lo divide en `0` y `n - 1`, y eso provoca una recursión lineal y un costo cuadrático total.

Por eso, la elección del pivote es crítica para la eficiencia de Quicksort.
