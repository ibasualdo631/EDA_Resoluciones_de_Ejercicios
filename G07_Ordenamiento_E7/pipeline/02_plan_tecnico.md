# Plan técnico: instrumentación de QuickSort para contar llamadas recursivas

## 1. Objetivo

Implementar una versión de Quicksort que permita contar la cantidad exacta de llamadas recursivas realizadas durante la ejecución sobre un arreglo ya ordenado, con el pivote fijado en el primer elemento.

La solución debe ser clara, modular y útil como ejemplo didáctico para explicar el peor caso del algoritmo.

## 2. Supuesto del problema

Se trabajará con el arreglo:

```java
int[] numeros = {1, 2, 3, 4, 5, 6, 7};
```

y se usará la estrategia de pivote:

```java
int pivote = arreglo[izquierda];
```

Esto provoca el peor caso cuando el arreglo ya está ordenado, porque el primer elemento es el menor elemento de la sublista.

## 3. Estrategia de instrumentación

Para contar llamadas recursivas sin contaminar la lógica de ordenamiento, se debe usar un contador global o un parámetro adicional de seguimiento.

### Opción recomendada

Usar una variable estática dentro de la clase:

```java
private static int contadorLlamadas = 0;
```

y en cada invocación del método recursivo se incrementa antes de continuar con la partición:

```java
contadorLlamadas++;
```

Esto cuenta cada entrada a la función recursiva, incluida la llamada original inicial.

## 4. Diseño de la solución

### 4.1 Método principal

```java
public static void quicksort(int[] arreglo)
```

Debe:

- inicializar el contador en `0`,
- llamar al método recursivo privado con los índices adecuados,
- imprimir el total al final.

### 4.2 Método recursivo

```java
private static void quicksortRecursivo(int[] arreglo, int izquierda, int derecha)
```

Debe:

- incrementar el contador al entrar,
- verificar la condición base: `if (izquierda >= derecha) return;`
- elegir el pivote como `arreglo[izquierda]`,
- particionar el arreglo,
- llamar recursivamente sobre la parte izquierda y la parte derecha.

## 5. Pseudocódigo recomendado

```java
public class QuicksortInstrumentado {
    private static int contadorLlamadas = 0;

    public static void quicksort(int[] arreglo) {
        contadorLlamadas = 0;
        quicksortRecursivo(arreglo, 0, arreglo.length - 1);
        System.out.println("Cantidad de llamadas recursivas: " + contadorLlamadas);
    }

    private static void quicksortRecursivo(int[] arreglo, int izquierda, int derecha) {
        contadorLlamadas++;

        if (izquierda >= derecha) {
            return;
        }

        int pivote = arreglo[izquierda];
        int i = izquierda + 1;
        int j = derecha;

        while (i <= j) {
            while (i <= j && arreglo[i] <= pivote) {
                i++;
            }
            while (i <= j && arreglo[j] > pivote) {
                j--;
            }
            if (i <= j) {
                intercambiar(arreglo, i, j);
                i++;
                j--;
            }
        }

        intercambiar(arreglo, izquierda, j);

        quicksortRecursivo(arreglo, izquierda, j - 1);
        quicksortRecursivo(arreglo, j + 1, derecha);
    }

    private static void intercambiar(int[] arreglo, int i, int j) {
        int aux = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = aux;
    }
}
```

## 6. Importancia del contador

El contador debe incrementarse exactamente al inicio del método recursivo, no después de evaluar si la partición se hizo o no. Esto asegura que:

- cada llamada recursiva se cuenta una sola vez,
- la llamada inicial también queda registrada,
- el número total refleja el trabajo real del algoritmo.

## 7. Qué se espera en el peor caso

Con el arreglo ya ordenado y usando como pivote siempre el primer elemento:

- la partición produce una zona izquierda vacía y una zona derecha con `n - 1` elementos,
- el algoritmo no reduce el problema a la mitad,
- aumenta la cantidad de llamadas recursivas en forma casi lineal,
- el total de trabajo queda del orden de `O(n^2)`.

Para el ejemplo `{1,2,3,4,5,6,7}` se deberían observar muchas llamadas recursivas, aproximadamente una por cada elemento del arreglo, con el patrón degenerado esperado.

## 8. Directivas de implementación

1. Crear una clase `QuicksortInstrumentado`.
2. Declarar `private static int contadorLlamadas;`.
3. Implementar `public static void quicksort(int[] arreglo)` para reiniciar el contador y llamar al método recursivo.
4. Implementar `private static void quicksortRecursivo(int[] arreglo, int izquierda, int derecha)` con la lógica de Quicksort.
5. Incrementar el contador al comienzo de cada llamada recursiva.
6. Usar la condición base `if (izquierda >= derecha) return;`.
7. Elegir el pivote como el primer elemento: `int pivote = arreglo[izquierda];`.
8. Mantener la lógica de partición con punteros `i` y `j`.
9. Al final, imprimir `Cantidad de llamadas recursivas: X`.
10. Validar con un arreglo ordenado para observar el peor caso y documentar el resultado.

## 9. Resultado esperado

La salida esperada, en términos cualitativos, es:

```text
Cantidad de llamadas recursivas: 7
```

o un valor cercano que demuestre la tendencia degenerada del algoritmo con un arreglo ya ordenado.

El punto clave no es solo el valor exacto, sino entender que el número de llamadas crece de manera desproporcionada porque la partición está siempre mal balanceada.

## 10. Observación didáctica final

La instrumentación sirve para demostrar que la elección del pivote no es un detalle menor. En este caso, elegir el primer elemento del arreglo ordenado convierte el ordenamiento en un peor caso del algoritmo; la recursión se vuelve casi lineal y la complejidad temporal empeora drásticamente.
