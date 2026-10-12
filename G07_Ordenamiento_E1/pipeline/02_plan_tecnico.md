# Plan técnico — Bubble Sort

## Objetivo de implementación

Crear un programa introductorio en Java que ordene un arreglo de enteros de menor a mayor con Bubble Sort. El programa debe hacer visible el resultado de cada pasada completa del ciclo externo.

## Organización propuesta

- Implementar una clase ejecutable con `main`.
- Mantener el arreglo de ejemplo en `main`; no agregar lectura interactiva ni estructuras auxiliares.
- Separar la impresión del arreglo y el algoritmo en métodos pequeños:
  - `imprimirArreglo(String etiqueta, int[] arreglo)`: imprime la etiqueta y todos los valores en orden, en un formato legible.
  - `bubbleSort(int[] arreglo)`: ordena el arreglo in-place e imprime el estado completo después de cada pasada externa.
- Usar únicamente `int[]`; el algoritmo requiere espacio auxiliar constante O(1).

## Firmas y responsabilidades

```java
static void imprimirArreglo(String etiqueta, int[] arreglo)
static void bubbleSort(int[] arreglo)
```

- `imprimirArreglo` recorre desde el índice `0` hasta `arreglo.length - 1`; no modifica el arreglo.
- `bubbleSort` recibe el arreglo mutable, lo ordena en el mismo arreglo y no retorna un valor.
- `main` declara un arreglo de ejemplo, imprime su estado inicial y llama a `bubbleSort`.

## Secuencia del algoritmo

1. Declarar el arreglo de ejemplo en `main`.
2. Imprimir el arreglo original antes de cualquier comparación.
3. En `bubbleSort`, recorrer las pasadas externas con `i` desde `0` hasta `arreglo.length - 2`.
4. En cada pasada, comparar pares adyacentes en el tramo activo: `j` desde `0` hasta `arreglo.length - i - 2`.
5. Si `arreglo[j] > arreglo[j + 1]`, intercambiar ambos valores usando una variable temporal. No intercambiar valores iguales.
6. Al terminar todas las comparaciones de esa pasada, imprimir el arreglo completo una sola vez, identificando la pasada (por ejemplo, `Pasada 1`).
7. Continuar las pasadas sin terminación anticipada, incluso si una pasada no produjo intercambios.

El mayor valor del tramo activo avanza hacia la derecha en cada intercambio con un valor menor. Por ello, al terminar la pasada queda en la última posición activa. En la pasada siguiente se excluye ese sufijo ya ordenado reduciendo el límite de comparación en uno.

## Salida requerida

- Mostrar primero el arreglo original.
- Mostrar exactamente un estado del arreglo al finalizar cada pasada ejecutada por el ciclo externo; no imprimir después de cada comparación o intercambio.
- Imprimir el arreglo completo en cada estado, incluidos los elementos del sufijo que ya quedaron ordenados.
- Para arreglos de longitud cero o uno, mostrar el estado inicial y no imprimir pasadas, ya que no se ejecuta el ciclo externo.

## Complejidad y restricciones

- Tiempo: O(n²) en el caso general; con el ciclo externo y rango activo descritos, el número de comparaciones está acotado por una suma cuadrática.
- Espacio auxiliar: O(1), porque se intercambia in-place con una variable temporal.
- Mantener Bubble Sort estable mediante la comparación estricta `>`.
- No implementar optimización de salida anticipada.
- No alterar el arreglo original en una copia: el resultado final debe quedar en el arreglo recibido.

## Instrucciones para el desarrollador

Implementá la solución en Java siguiendo las firmas, límites de índices y secuencia definidos arriba. Elegí un arreglo de ejemplo que permita observar intercambios y el avance del mayor elemento. Verificá manualmente también un arreglo ya ordenado, con valores repetidos, vacío y unitario: en todos los casos imprimí primero el estado inicial; para longitudes cero y uno no debe aparecer ninguna pasada. Para cualquier arreglo de longitud mayor que uno, imprimí una sola línea de estado después de cada pasada externa completa, aun cuando el arreglo ya estuviera ordenado. No agregues entrada interactiva, colecciones, optimización de terminación anticipada ni impresiones por intercambio.