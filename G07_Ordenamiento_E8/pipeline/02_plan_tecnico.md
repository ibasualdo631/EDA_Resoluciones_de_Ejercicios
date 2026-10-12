# Plan técnico: MergeSort paso a paso

## 1. Objetivo del diseño

Implementar una solución en Java que permita visualizar explícitamente en consola:

- cómo se divide el arreglo recursivamente;
- cuándo se alcanza la condición de corte;
- cómo se fusionan los subarreglos;
- cuál es el estado del arreglo en cada etapa.

La intención es que el algoritmo no solo ordene, sino que además eduque visualmente la lógica de `MergeSort`.

## 2. Enfoque arquitectónico

Se utilizará una solución simple y clara, orientada a la demostración didáctica:

- una clase principal para iniciar la ejecución;
- una clase `MergeSortVisualizer` o similar para encapsular la lógica del algoritmo;
- métodos separados para cada responsabilidad;
- mensajes impresos en consola para hacer visible cada etapa.

Esto mantiene el código legible, modular y apropiado para un ejercicio académico.

## 3. Estructura propuesta

```text
G07_Ordenamiento_E8/
├── src/
│   └── main/java/
│       └── ejercicio8/
│           ├── Main.java
│           └── MergeSortVisualizer.java
└── pipeline/
    ├── 01_analisis.md
    └── 02_plan_tecnico.md
```

### Clase `Main`
Responsabilidad:
- crear el arreglo de prueba;
- imprimir el arreglo inicial;
- invocar al método de ordenamiento;
- mostrar el arreglo final.

### Clase `MergeSortVisualizer`
Responsabilidad:
- encapsular la lógica de `MergeSort`;
- mostrar la división recursiva;
- mostrar la condición de corte;
- mostrar la fusión de subarreglos;
- ordenar el arreglo mediante merge sort.

## 4. Diseño de métodos

### `public static void main(String[] args)`
- crea un arreglo de ejemplo, por ejemplo:
  `int[] arreglo = {38, 27, 43, 3, 9, 82, 10};`
- imprime el texto de inicio;
- llama a `mergeSort(arreglo, 0, arreglo.length - 1);`
- imprime el arreglo final.

### `public static void mergeSort(int[] arr, int inicio, int fin)`
Es el punto central del algoritmo.

Responsabilidades:
- verificar si `inicio >= fin`;
- si llega a la condición de corte, imprimir un mensaje como:
  `"Condición de corte: subarreglo con un solo elemento o vacío"`;
- si no se corta, calcular el punto medio;
- imprimir la división del arreglo en dos mitades;
- llamar recursivamente a `mergeSort` para la izquierda y la derecha;
- luego llamar a `merge(arr, inicio, medio, fin);`.

### `private static void merge(int[] arr, int inicio, int medio, int fin)`
Responsabilidad:
- mezclar dos subarreglos ya ordenados: `arr[inicio..medio]` y `arr[medio+1..fin]`;
- crear un arreglo auxiliar temporal;
- comparar elementos de ambas mitades;
- copiar el menor elemento al auxiliar;
- copiar el resto; 
- volver los datos al arreglo original.

También debe imprimir mensajes tipo:

- `"Fusionando: [...] y [...]"`
- `"Tomando menor: X"`
- `"Arreglo después de fusionar: [...]"`

Esto deja en evidencia qué ocurre exactamente en la fase de merge.

### `private static void imprimirArreglo(int[] arr, String mensaje)`
Responsabilidad:
- mostrar el contenido actual del arreglo con formato legible;
- reutilizarse en todas las etapas del algoritmo.

Ejemplo:

```java
System.out.println(mensaje + Arrays.toString(arr));
```

## 5. Lógica de la condición de corte

La condición de corte debe ser explícita y fácil de reconocer:

```java
if (inicio >= fin) {
    System.out.println("Condición de corte: " + Arrays.toString(Arrays.copyOfRange(arr, inicio, fin + 1)));
    return;
}
```

Esto permitirá demostrar que el algoritmo deja de dividir cuando el subarreglo ya tiene tamaño 0 o 1, porque en ese caso ya está ordenado.

## 6. Lógica de división recursiva

El flujo ordenado será:

1. `mergeSort(arr, 0, n-1)`
2. si hay más de un elemento:
   - calcular `medio = (inicio + fin) / 2`
   - imprimir el rango de división
   - llamar `mergeSort(arr, inicio, medio)`
   - llamar `mergeSort(arr, medio + 1, fin)`
   - luego fusionar

Esto permite visualizar la estructura de árbol recursivo del algoritmo.

## 7. Lógica de fusión

La fase de merge debe seguir este esquema:

```java
int[] aux = new int[fin - inicio + 1];
int i = inicio;
int j = medio + 1;
int k = 0;

while (i <= medio && j <= fin) {
    if (arr[i] <= arr[j]) {
        aux[k++] = arr[i++];
    } else {
        aux[k++] = arr[j++];
    }
}

while (i <= medio) aux[k++] = arr[i++];
while (j <= fin) aux[k++] = arr[j++];

for (int p = 0; p < k; p++) {
    arr[inicio + p] = aux[p];
}
```

Además, cada paso debe ser acompañado por mensajes de consola para explicar qué está ocurriendo.

## 8. Requisitos de salida en consola

La consola debe mostrar algo como esto:

```text
Arreglo original: [38, 27, 43, 3, 9, 82, 10]

Dividiendo: [38, 27, 43, 3, 9, 82, 10]
Dividiendo en: [38, 27, 43] y [3, 9, 82, 10]

Condición de corte: [38]
Condición de corte: [27]
...

Fusionando: [27] y [38]
Arreglo después de fusionar: [27, 38]
...

Arreglo final ordenado: [3, 9, 10, 27, 38, 43, 82]
```

Esto deja visualmente claro:

- la división recursiva;
- la regla de corte;
- la fusión ordenada de subarreglos.

## 9. Consideraciones de calidad

- el código debe ser legible y didáctico;
- los nombres de métodos deben reflejar su rol;
- la salida por consola debe ser clara y no redundante;
- no debe encapsular la lógica en demasiados niveles;
- la implementación debe mantener la lógica estándar de `MergeSort` sin perder claridad didáctica.

## 10. Secuencia de ejecución esperada

1. Se crea el arreglo inicial.
2. Se imprime el arreglo original.
3. Se llama a `mergeSort` con el rango completo.
4. Cada nivel recursivo divide el arreglo por la mitad.
5. Cuando llega a un subarreglo de longitud 0 o 1, se confirma la condición de corte.
6. Vuelven los llamados recursivos.
7. Se fusionan los subarreglos ordenados.
8. Se imprime el arreglo luego de cada merge.
9. Se muestra el resultado final ordenado.

## 11. Resultado esperado

El programa debe cumplir con la idea central del ejercicio: explicar que `MergeSort` funciona en dos fases claramente diferenciadas:

- División recursiva: descompone el problema.
- Fusión: reconstruye la solución en orden.

Este diseño asegura que el alumno pueda ver no solo el resultado final sino también la lógica interna del algoritmo.
