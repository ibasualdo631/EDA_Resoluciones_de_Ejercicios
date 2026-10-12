# Ejercicio 10 — Plan técnico: Bubble Sort

## Diseño

Implementar una clase `BubbleSort` sin estado, con un método estático que
ordena en el lugar un arreglo `int[]` ascendentemente. Para entradas válidas,
el método devuelve un objeto inmutable con la telemetría del ordenamiento.

### API propuesta

- Clase pública `final` sin estado: `BubbleSort`.
- Método público estático: `Resultado ordenar(int[] arreglo)`.
- Tipo público estático anidado: `BubbleSort.Resultado`, inmutable, con
  `long getComparaciones()` y `long getIntercambios()`.

- `ordenar(int[] arreglo)`: verifica `null` antes de cualquier otra acción.
  Si es `null`, lanza `IllegalArgumentException` con un mensaje claro.
  Para arreglos válidos, ordena la misma instancia recibida y retorna un
  `Resultado`.
- `Resultado`: clase estática anidada e inmutable, con campos privados
  `final long comparaciones` e `intercambios`, constructor no público y
  getters. `long` evita desbordar al contabilizar las operaciones para tamaños
  grandes de arreglo.
- Para arreglos de longitud cero o uno, devuelve métricas cero sin comparar
  elementos.

### Algoritmo y telemetría

Usar dos ciclos: cada pasada compara pares adyacentes hasta el último índice
no ordenado. Incrementar `comparaciones` exactamente una vez antes de cada
comparación de valores. Si el par está en orden descendente, intercambiarlo,
incrementar `intercambios` una vez y marcar la pasada como modificada. Al
terminar una pasada sin intercambios, salir anticipadamente.

Mantener el límite interior decreciente tras cada pasada, pues el mayor
elemento pendiente queda ubicado al final. No contar comprobaciones de
límites, condiciones del ciclo o comparaciones de la bandera como
comparaciones del algoritmo. No realizar intercambios cuando los valores son
iguales.

Las métricas deben cumplir:

- `null`: excepción antes de ordenar y sin resultado.
- Longitud 0 o 1: 0 comparaciones y 0 intercambios.
- Ya ordenado, longitud `n > 1`: `n - 1` comparaciones y 0 intercambios.
- Orden inverso, longitud `n`: `n(n - 1)/2` comparaciones y
  `n(n - 1)/2` intercambios.

## Complejidad

- Peor caso: `O(n²)` comparaciones e intercambios, por ejemplo, para el orden
  inverso.
- Mejor caso con bandera de corte: `Θ(n)` (por tanto `Ω(n)`) comparaciones y
  cero intercambios para entrada ya ordenada. La primera pasada verifica los
  `n - 1` pares antes de cortar.
- Espacio auxiliar: `O(1)`, porque ordena el arreglo recibido y solo usa
  variables escalares y el objeto de métricas.

Por el costo cuadrático promedio y de peor caso, es apropiado principalmente
con fines didácticos o para conjuntos pequeños, no para grandes volúmenes.

## Validación requerida

Probar el resultado ascendente y las métricas con: `null`, arreglo vacío,
longitud uno, arreglo ordenado, arreglo inverso, valores negativos y valores
repetidos. La prueba de `null` debe comprobar el tipo de excepción. Comprobar
también que se modifica la instancia entregada, sin sustituirla por un arreglo
ordenado por biblioteca.

## Instrucción técnica para el Desarrollador

Implementá en Java la clase `BubbleSort` con un método estático
`ordenar(int[] arreglo)` que ordene ascendentemente y en el lugar. Debe lanzar
`IllegalArgumentException` si `arreglo` es `null`, antes de modificar datos.
Para arreglos vacíos o de un elemento debe retornar telemetría con cero
comparaciones e intercambios.

Definí un tipo de retorno inmutable `Resultado` (por ejemplo, una clase
estática anidada) con campos `long comparaciones` y `long intercambios` y
getters públicos. El método debe retornar dicho resultado para toda entrada
válida. Incrementá comparaciones solo al comparar dos valores del arreglo e
incrementá intercambios solo al intercambiar dos valores efectivamente
desordenados. Usá una bandera por pasada para detenerte cuando no haya
intercambios, y reducí el límite interior porque el final de cada pasada queda
ordenado.

Documentá y verificá: peor caso `O(n²)`; mejor caso con corte `Θ(n)` y, en
particular, `Ω(n)`; espacio auxiliar `O(1)`. Explicá que Bubble Sort se
recomienda principalmente para fines didácticos o conjuntos pequeños por su
costo promedio y peor caso cuadráticos. Agregá pruebas de `null`, vacío, un
elemento, ordenado, inverso, negativos y repetidos. Para un arreglo ordenado
de longitud `n > 1`, verificá `n - 1` comparaciones y cero intercambios; para
uno inverso, verificá `n(n - 1)/2` de cada operación. Respetá las convenciones
existentes del proyecto y ejecutá las pruebas pertinentes.