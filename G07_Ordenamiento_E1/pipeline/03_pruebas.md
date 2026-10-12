# Pruebas — Bubble Sort paso a paso

## Matriz de casos

| ID | Caso | Arreglo de entrada | Salida esperada después de cada pasada | Resultado |
|---|---|---|---|---|
| P-01 | Desordenado (inverso) | `[4, 3, 2, 1]` | Pasada 1: `[3, 2, 1, 4]`<br>Pasada 2: `[2, 1, 3, 4]`<br>Pasada 3: `[1, 2, 3, 4]` | Aprobado |
| P-02 | Ya ordenado | `[1, 2, 3, 4]` | Pasada 1: `[1, 2, 3, 4]`<br>Pasada 2: `[1, 2, 3, 4]`<br>Pasada 3: `[1, 2, 3, 4]` | Aprobado |
| P-03 | Elementos duplicados | `[4, 2, 4, 1, 2]` | Pasada 1: `[2, 4, 1, 2, 4]`<br>Pasada 2: `[2, 1, 2, 4, 4]`<br>Pasada 3: `[1, 2, 2, 4, 4]`<br>Pasada 4: `[1, 2, 2, 4, 4]` | Aprobado |
| P-04 | Vacío | `[]` | Sin pasadas | Aprobado |
| P-05 | Unitario | `[7]` | Sin pasadas | Aprobado |

En todos los casos se imprime primero el arreglo inicial. P-02 comprueba además que el algoritmo no termina anticipadamente: para longitud cuatro imprime las tres pasadas del ciclo externo, aunque no haya intercambios.

## Revisión de la implementación

- La comparación adyacente usa `>` y solo intercambia si el elemento izquierdo es mayor. Por tanto, los iguales no se permutan.
- El límite interno disminuye una posición en cada pasada (`arreglo.length - i - 1`); el sufijo final ya ordenado queda fuera de las comparaciones.
- Se imprime una sola línea con el arreglo completo al cerrar cada pasada, no después de cada comparación.
- La ordenación es in-place y el programa muestra el arreglo original antes de llamar al algoritmo.
- Los arreglos de longitud cero o uno no ejecutan pasadas, al no cumplirse la condición del ciclo externo.

## Ejecución y resultado

Se compiló `BubbleSort.java` con Java 21. Se ejecutó la salida principal y un harness temporal que comparó línea por línea la salida de las cinco entradas de la matriz; los cinco casos pasaron. El harness no se incorpora al programa ni agrega entrada interactiva.

La ejecución del ejemplo declarado en `main` también terminó ordenada. Produjo seis líneas de pasada para sus siete elementos; la sexta repite el arreglo ordenado, conforme a la ausencia intencional de terminación anticipada.

## Dictamen

No se detectaron defectos respecto de los requisitos del análisis. La salida paso a paso coincide con el resultado esperado en arreglos desordenados, ordenados, con duplicados, vacío y unitario.