# Pruebas — Ejercicio 10: Cola circular

## Objetivo

Verificar que `ColaCircular` mantiene el orden FIFO, distingue correctamente los estados vacío/lleno y reutiliza las posiciones liberadas cuando `front` o `rear` llegan al final del arreglo. En particular, comprobar que una cola con espacio disponible no informa falsamente desbordamiento tras el wrap-around.

## Estrategia

Las pruebas automatizadas se ejecutan desde el método `main` de `ColaCircular.java`, usando verificaciones que lanzan `AssertionError` si falla una condición y comprobando las excepciones esperadas en operaciones inválidas. La prueba de estrés contrasta los resultados con `ArrayDeque<Integer>`, utilizado como modelo FIFO independiente.

## Casos funcionales

| Caso | Preparación y acciones | Resultado esperado |
|---|---|---|
| Estado inicial | Crear una cola con capacidad positiva. | `front = 0`, `rear = 0`, `count = 0`; la cola está vacía y no llena. |
| FIFO básico | Encolar varios valores y extraerlos. | Los valores salen en el mismo orden en que entraron. |
| Consulta del frente | Encolar valores y consultar el frente sin extraer. | Se devuelve el primer valor; tamaño y orden no cambian. |
| Extracción/consulta vacía | Intentar desencolar y consultar el frente en una cola vacía. | Cada operación lanza `NoSuchElementException`; la cola permanece vacía. |
| Capacidad llena | Insertar exactamente `N` valores. | `estaLlena()` es verdadero y `count == N`. |
| Overflow real | Insertar un elemento adicional estando llena. | Se lanza `IllegalStateException`; contenido, índices y cantidad no cambian. |
| Capacidad inválida | Construir con capacidad cero o negativa. | El constructor lanza `IllegalArgumentException`. |
| Capacidad unitaria | Encolar y desencolar repetidamente con `N = 1`. | La única celda se reutiliza; los estados lleno/vacío son correctos. |

## Casos de wrap-around y reutilización

1. Crear una cola de capacidad `5` y encolar `10, 20, 30, 40, 50`. Después de la quinta inserción, `rear` debe volver de `4` a `0`, la cola debe estar llena y no debe haberse descartado ningún valor.
2. Desencolar `10, 20, 30`. Las celdas de índices `0`, `1` y `2` quedan disponibles; la cola conserva `40, 50`, con `front = 3` y `rear = 0`.
3. Encolar `60` y `70`. Ambas inserciones deben tener éxito reutilizando las posiciones `0` y `1`; el arreglo pasa a `[60, 70, 30, 40, 50]`, con `front = 3`, `rear = 2` y `count = 4`. Esto verifica que no se produce falso overflow al rebasar el final del arreglo.
4. Desencolar y comprobar `40, 50, 60, 70`. El orden FIFO debe mantenerse tras el rebote de `rear`.
5. Tras vaciar, `front` debe haber vuelto también a `2`; los índices permanecen dentro del rango válido.

## Prueba de estrés determinista

La prueba `probarEstresDeReutilizacionCircular` usa una cola de capacidad `31` y ejecuta:

- **10 000 ciclos completos:** llena la cola, desencola `13` elementos, vuelve a llenarla usando el espacio circular liberado y prueba una inserción adicional. El encolado adicional debe fallar únicamente mientras la referencia tiene sus `31` posiciones ocupadas.
- **250 000 operaciones reproducibles:** inserciones y extracciones pseudoaleatorias con semilla fija `810`; cada extracción se compara con el frente de `ArrayDeque`.
- **Vaciado final:** extrae todo y verifica que el modelo y la cola terminan vacíos.

Después de cada operación se comprueban:

- `cantidadElementos()` coincide con el tamaño del modelo.
- `estaVacia()` y `estaLlena()` coinciden con los estados derivados del modelo.
- `front` y `rear` permanecen entre `0` y `capacidad - 1`.
- Si hay elementos, `consultarFrente()` coincide con el primer elemento del modelo.
- Cada extracción devuelve el valor FIFO esperado.

## Resultado de ejecución

Validación ejecutada sobre `ColaCircular.java`:

```text
javac -Xlint:all -d <directorio-temporal> ColaCircular.java
java -ea -cp <directorio-temporal> ColaCircular
```

Resultado: compilación correcta sin advertencias; la secuencia de wrap-around, las comprobaciones de errores, la capacidad unitaria y las 10 000 rondas más 250 000 operaciones de estrés finalizaron correctamente. El programa informó:

```text
Prueba de estrés superada: 10 000 ciclos de wrap-around y 250 000 operaciones contrastadas con ArrayDeque.
Todas las pruebas de ColaCircular finalizaron correctamente.
```