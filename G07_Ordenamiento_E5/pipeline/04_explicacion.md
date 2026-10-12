# Explicación — ShellSort y el efecto de los gaps

## ¿Qué hace el programa?

`ShellSort.java` ordena arreglos de enteros de menor a mayor aplicando varias pasadas de inserción. A diferencia de Insertion Sort tradicional, que compara posiciones contiguas, ShellSort empieza comparando elementos separados por una distancia llamada **gap** (salto). La distancia se reduce en cada etapa hasta llegar a `1`.

El programa ejecuta dos ejemplos en `main`: uno de longitud impar y otro de longitud par. Para cada uno muestra el arreglo inicial, los gaps y el arreglo al terminar cada pasada, y finalmente el arreglo ordenado.

## Secuencia matemática de gaps

Para un arreglo de longitud `n`, se utiliza la secuencia:

`g₀ = floor(n / 2)`  
`gₖ₊₁ = floor(gₖ / 2)`, mientras `gₖ > 0`.

En Java, la división entre enteros descarta la parte decimal, por eso el código la implementa así:

```java
for (int gap = arreglo.length / 2; gap > 0; gap /= 2) {
    // pasada de inserción para este gap
}
```

Con los dos arreglos de ejemplo, `n` es 9 y 8, respectivamente. En ambos casos la secuencia resultante es `4, 2, 1`. Cada valor positivo se procesa exactamente una vez. Si el arreglo tiene longitud 0 o 1, el primer gap es 0 y no hay pasadas que realizar.

## Cómo funciona una pasada

Para un `gap` fijo, los elementos se agrupan en subsecuencias cuyos índices están separados por ese salto. Por ejemplo, si el gap es 3, se ordenan por inserción las secuencias de índices:

- `0, 3, 6, 9, ...`
- `1, 4, 7, 10, ...`
- `2, 5, 8, 11, ...`

Estas subsecuencias no se copian a estructuras auxiliares: se ordenan directamente dentro del mismo arreglo.

En `shellSort`, el ciclo de `i` considera cada posición desde `gap` hasta el final. Se guarda el valor que se va a insertar en `valor`; luego, mientras el elemento ubicado `gap` posiciones antes sea mayor, se lo desplaza hacia adelante. Finalmente, `valor` se coloca en el hueco disponible:

```java
int valor = arreglo[i];
int j = i;

while (j >= gap && arreglo[j - gap] > valor) {
    arreglo[j] = arreglo[j - gap];
    j -= gap;
}

arreglo[j] = valor;
```

Esto es una inserción ordenada dentro de la subsecuencia definida por el gap. Cuando termina el ciclo de inserciones de ese gap, se imprimen obligatoriamente el mensaje `Gap actual: X` y el estado completo del arreglo.

## Por qué los gaps grandes reducen el trabajo final

En Insertion Sort estándar, cada movimiento hacia la izquierda avanza solo una posición. Si un valor pequeño está cerca del extremo derecho y debería quedar al principio, puede tener que atravesar muchos elementos mediante desplazamientos individuales. Además, antes de llegar allí debe compararse repetidamente con elementos que todavía están fuera de orden.

Con un gap grande, una inserción puede desplazar un elemento varias posiciones en un solo paso. La pasada no garantiza que el elemento alcance su índice definitivo, porque solo compara elementos de su subsecuencia; sí puede acercarlo mucho a su zona correcta y reducir el desorden general. Los gaps posteriores, más pequeños, refinan ese orden parcial.

Cuando el gap llega a `1`, las subsecuencias se convierten en el arreglo completo. Esa pasada es exactamente una inserción tipo Insertion Sort normal y garantiza que el arreglo quede ordenado. Sin embargo, ya no suele empezar desde el desorden original: los elementos alejados fueron acercados en las pasadas anteriores, por lo que normalmente hacen falta menos desplazamientos y comparaciones en el cierre.

No se debe interpretar esto como una cantidad fija de operaciones ni como una complejidad que no depende de los gaps. El ahorro real depende del arreglo de entrada y de la secuencia elegida. La secuencia usada aquí (`n/2`, luego dividir por dos con división entera) es sencilla y adecuada para mostrar el mecanismo.

## Lectura de la salida de consola

Para el ejemplo impar, las pasadas terminan con estos estados:

```text
Gap actual: 4
[6, 1, 5, 2, 8, 3, 7, 4, 9]
Gap actual: 2
[5, 1, 6, 2, 7, 3, 8, 4, 9]
Gap actual: 1
[1, 2, 3, 4, 5, 6, 7, 8, 9]
```

Para el ejemplo par:

```text
Gap actual: 4
[6, 2, 5, 1, 8, 3, 7, 4]
Gap actual: 2
[5, 1, 6, 2, 7, 3, 8, 4]
Gap actual: 1
[1, 2, 3, 4, 5, 6, 7, 8]
```

En ambos casos las pasadas con gaps 4 y 2 acercan elementos y preparan el arreglo; la pasada con gap 1 completa el orden ascendente.

## Conceptos de Java presentes

- **Arreglos (`int[]`)**: almacenan y modifican los valores en el mismo lugar.
- **Métodos estáticos**: `main` coordina los ejemplos, `ejecutarCaso` imprime el antes y después de cada arreglo, y `shellSort` se encarga del algoritmo.
- **Ciclos `for`**: recorren los gaps y las posiciones que se insertan en cada pasada.
- **Ciclo `while`**: desplaza valores mayores que la clave dentro de la subsecuencia actual.
- **Condiciones compuestas**: `j >= gap && arreglo[j - gap] > valor` evita acceder a un índice negativo y mantiene el orden ascendente.
- **`Arrays.toString`**: convierte el arreglo a texto para mostrarlo; solo formatea, no ordena.

## Errores comunes evitados

- Reducir el gap después de imprimir puede confundir el valor informado con la pasada que acaba de terminar. Aquí se imprime dentro del ciclo, tras terminar las inserciones del gap actual.
- Usar `j - 1` en lugar de `j - gap` convertiría la inserción en una pasada normal, perdiendo la función del salto.
- Omitir el caso `j >= gap` podría provocar acceso a un índice inválido.
- Usar `>=` en la comparación seguiría ordenando, pero desplazaría también valores iguales; con `>` se dejan en su lugar cuando ya están correctamente ordenados respecto de la clave.
- `Arrays.toString` se usa únicamente para imprimir; el ordenamiento lo realiza ShellSort.

## Posibles mejoras

Se puede recibir el arreglo desde teclado o separar los casos de prueba de la demostración. También se podrían comparar distintas secuencias de gaps contando comparaciones y desplazamientos, sin asumir que una única secuencia es óptima para todos los tamaños y entradas.