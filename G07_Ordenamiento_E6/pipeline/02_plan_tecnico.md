# Plan técnico: Quicksort con pivote en el primer elemento

## 1. Objetivo técnico

Diseñar una solución para implementar Quicksort utilizando siempre el primer elemento del arreglo como pivote, con salida por consola en cada paso del algoritmo. La estructura debe facilitar la comprensión de la recursión y la partición del problema.

La solución debe mostrar de manera obligatoria:

- pivote elegido,
- subarreglo izquierdo,
- subarreglo derecho,
- arreglo final ordenado en cada llamada recursiva.

## 2. Enfoque de solución

Se implementará una versión clásica de Quicksort basada en partición por pivote y recursión.

La idea general es:

1. Tomar el primer elemento como pivote.
2. Separar los elementos del arreglo en dos grupos:
   - menores que el pivote,
   - mayores o iguales que el pivote.
3. Ordenar recursivamente cada grupo.
4. Reconstruir el arreglo final como:
   - izquierda + pivote + derecha.
5. Mostrar por consola el estado de cada paso.

## 3. Estructura del programa

### 3.1 Estructura general

Se recomienda organizar el código en funciones/modulos pequeños y claros:

- `main()`
  - Define el arreglo de prueba.
  - Llama a `quicksort(arreglo, 0, n-1)`.
  - Muestra el arreglo final ordenado.

- `quicksort(A, inicio, fin)`
  - Caso base: si `inicio >= fin`, retorna.
  - Si no, calcula la partición.
  - Llama recursivamente a los subarreglos izquierdo y derecho.

- `particionar(A, inicio, fin)`
  - Usa el primer elemento como pivote.
  - Recorre el arreglo y separa los elementos menores y mayores.
  - Devuelve la posición del pivote en el arreglo final.

- `mostrarEstado(msg, A, pivote, izq, der)`
  - Muestra por consola:
    - mensaje descriptivo del paso,
    - pivote elegido,
    - subarreglo izquierdo,
    - subarreglo derecho,
    - arreglo actual.

- `imprimirArreglo(A)`
  - Formatea la salida para visualizar el estado del arreglo.

## 4. Lógica de partición

La partición se realiza con la convención de que el primer elemento del segmento es el pivote.

Pseudoestructura:

```text
funcion particionar(A, inicio, fin):
    pivote = A[inicio]
    i = inicio + 1
    j = fin

    mientras i <= j:
        mientras i <= j y A[i] <= pivote:
            i = i + 1

        mientras i <= j y A[j] > pivote:
            j = j - 1

        si i <= j:
            intercambiar A[i] con A[j]
            i = i + 1
            j = j - 1

    intercambiar A[inicio] con A[j]
    devolver j
```

La posición `j` indica dónde quedó el pivote en ese paso.

## 5. Reglas para la salida por consola

La implementación debe mostrar obligatoriamente en cada paso:

1. El pivote elegido.
2. El subarreglo izquierdo.
3. El subarreglo derecho.
4. El arreglo final ordenado para ese estado.

Ejemplo de salida esperada:

```text
--- Paso 1 ---
Pivote elegido: 5
Subarreglo izquierdo: [2, 3, 4]
Subarreglo derecho: [6, 7, 8]
Arreglo actual: [2, 3, 4, 5, 6, 7, 8]
```

Esto debe repetirse en cada llamada recursiva para que el estudiante vea la evolución del algoritmo.

## 6. Secuencia lógica de ejecución

### 6.1 Caso base

Si el segmento tiene 0 o 1 elemento, no se hace nada. Esto evita llamadas recursivas innecesarias.

### 6.2 Partición

Se toma el primer valor del rango como pivote y se reacomodan los elementos alrededor de él.

### 6.3 Orden recursivo

Se ejecuta nuevamente `quicksort` sobre:

- el lado izquierdo,
- el lado derecho.

### 6.4 Combinación final

Cuando ambas llamadas terminan, el arreglo queda ordenado. Se muestra el estado final global.

## 7. Estructuras de datos a utilizar

Se usará:

- un arreglo unidimensional `int[]` o equivalente,
- índices `inicio` y `fin` para manejar subarreglos,
- recursión para el tratamiento de cada segmento,
- no se requieren estructuras complejas adicionales.

## 8. Consideraciones de diseño

### 8.1 Simplicidad didáctica

La solución debe priorizar la claridad del algoritmo antes que la optimización.

### 8.2 Recursividad

Toda la lógica será recursiva, lo cual es clave para que el problema se entienda como una división del problema original en subproblemas más pequeños.

### 8.3 Visualización del proceso

Para cumplir con la consigna, cada llamada recursiva deberá imprimir el estado actual del algoritmo. Esto ayuda a diferenciar el pivote, los subarreglos y la combinación final.

## 9. Pseudocódigo general

```text
funcion quicksort(A, inicio, fin):
    si inicio >= fin:
        devolver

    posicion = particionar(A, inicio, fin)

    mostrarEstado("Partición realizada", A, A[posicion], A[inicio:posicion], A[posicion+1:fin+1])

    quicksort(A, inicio, posicion - 1)
    quicksort(A, posicion + 1, fin)

    mostrarEstado("Arreglo ordenado en este paso", A, A[posicion], A[inicio:posicion], A[posicion+1:fin+1])
```

## 10. Resultado esperado

El programa debe permitir ver claramente la evolución del algoritmo, desde la elección del pivote hasta el arreglo completamente ordenado. El foco principal no es solo que ordene números, sino que muestre la lógica interna del Quicksort y su relación con la estrategia de dividir y conquistar.

## 11. Conclusión

La estructura técnica propuesta es simple, modular y didáctica. La división del problema en `quicksort`, `particionar` y `mostrarEstado` permite mantener una implementación clara, con una salida por consola que cumple estrictamente con la consigna del ejercicio.
