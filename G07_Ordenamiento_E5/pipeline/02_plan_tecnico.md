# Plan técnico — Ejercicio 5: ShellSort

## Secuencia matemática de gaps

Se utilizará la secuencia clásica de Shell:

- Sea `n` la longitud del arreglo.
- Gap inicial: `g_0 = floor(n / 2)`.
- Mientras `g_k > 0`, el siguiente gap será `g_(k+1) = floor(g_k / 2)`.
- Se procesa cada gap positivo, incluido `1`; se detiene al obtener `0`.

Ejemplos:

| `n` | Secuencia |
|---:|---|
| 10 | 5, 2, 1 |
| 7 | 3, 1 |
| 5 | 2, 1 |
| 2 | 1 |
| 0 o 1 | Sin pasadas |

En código Java, la secuencia puede controlarse con `for (int gap = arreglo.length / 2; gap > 0; gap /= 2)`. La división entera implementa el piso. El bucle siempre llega a `1` para arreglos de longitud mayor que uno, y no produce gaps inválidos para arreglos vacíos o unitarios.

## Organización y firmas

- `public static void main(String[] args)`: crea un arreglo de ejemplo, muestra el arreglo inicial, invoca el método de ordenamiento y muestra el resultado final.
- `private static void shellSort(int[] arreglo)`: ejecuta cada pasada y, como parte obligatoria del bucle que procesa cada gap, informa el gap y el estado completo del arreglo al concluir esa pasada. El registro no debe depender de que `main` lo recuerde ni de un parámetro booleano opcional.
- Se puede usar `Arrays.toString(arreglo)` únicamente para representar el arreglo en la salida; no se permite usar `Arrays.sort` ni delegar el ordenamiento.

## Secuencia de ejecución de `shellSort`

1. Inicializar el bucle de gaps con `arreglo.length / 2`.
2. Para el gap actual, recorrer desde el índice `gap` hasta el final.
3. Para cada índice, guardar el valor actual como clave y desplazar hacia la derecha los elementos mayores que la clave en pasos de `gap`. Insertar la clave en el hueco resultante. Esto aplica insertion sort a las subsecuencias intercaladas por el gap.
4. Solo después de procesar todos los índices de esa pasada, imprimir en una única línea el gap utilizado y el arreglo completo ya actualizado.
5. Reducir el gap mediante división entera por dos y repetir mientras sea positivo.

## Directivas de código

1. Implementá la solución en Java estándar, usando un `int[]` y métodos estáticos con las responsabilidades indicadas.
2. Usá exclusivamente la secuencia `floor(n/2), floor(n/4), ... , 1`, expresada como `gap = arreglo.length / 2` y luego `gap /= 2` hasta que `gap` sea cero. No sustituyas ni mezcles secuencias distintas.
3. Dentro de `shellSort`, completá una pasada entera para el gap actual antes de informar el resultado de esa etapa.
4. El método `shellSort` debe imprimir incondicionalmente, al final de cada pasada, tanto el valor del gap como el estado completo del arreglo. Ubicá esa impresión dentro del bucle externo de gaps, después del bucle interno de inserciones; no la dejes únicamente en `main`, en un comentario ni en una ruta opcional.
5. Etiquetá la salida para que pueda identificarse sin ambigüedad, por ejemplo: `Gap = 3 -> [ ... ]`. Usá `Arrays.toString` solo para formatear.
6. Conservá el orden ascendente y no uses `Arrays.sort`, colecciones ordenadas ni otro algoritmo como reemplazo.
7. No imprimas una pasada ficticia para `gap = 0`: los arreglos de longitud cero o uno no tienen etapas. El resultado final puede seguir mostrándose desde `main`.
8. Incluí un ejemplo desordenado con suficientes elementos para que aparezcan varias etapas y explicá que el gap grande permite adelantar elementos lejanos, mientras que la pasada final con gap `1` termina el ordenamiento como Insertion Sort sobre un arreglo parcialmente ordenado.

## Criterio de aceptación

Al ejecutar el ejemplo, debe aparecer exactamente una línea de etapa por cada gap positivo de la secuencia definida. Cada línea debe mostrar el gap correcto y el arreglo completo resultante después de procesar esa pasada. Al final, el arreglo debe estar ordenado ascendentemente.