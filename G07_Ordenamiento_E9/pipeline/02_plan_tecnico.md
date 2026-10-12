# Plan técnico: clase integradora de ordenamientos

## Organización propuesta

Implementar una clase `IntegradorOrdenamientos` en Java, con un único arreglo de referencia inmutable durante la comparación y tres copias independientes para ejecutar Bubble Sort, Selection Sort e Insertion Sort.

La clase integradora coordina la preparación de entradas, las ejecuciones, la comprobación de resultados y la presentación de métricas. Cada algoritmo opera exclusivamente sobre su propia copia y tiene su propio objeto de métricas; no se comparten ni se acumulan contadores entre métodos.

## Estructuras y estado

### `IntegradorOrdenamientos`

- `private final int[] arregloOriginal`: referencia defensiva a los datos de entrada.
- `private final int[] copiaBurbuja`, `copiaSeleccion`, `copiaInsercion`: clones independientes de `arregloOriginal`.
- `private final Metricas metricasBurbuja`, `metricasSeleccion`, `metricasInsercion`: instancias distintas, inicializadas en cero.

Constructor:

```java
public IntegradorOrdenamientos(int[] arregloInicial)
```

Debe rechazar `null` con `IllegalArgumentException` y clonar el argumento antes de derivar las tres entradas de trabajo. Así, los cambios posteriores al arreglo que recibió el constructor tampoco pueden alterar la comparación. Para arreglos vacíos, los clones y algoritmos deben funcionar normalmente.

### `Metricas`

Clase auxiliar pequeña, anidada o de nivel superior, con dos contadores independientes:

```java
private long comparaciones;
private long movimientos;
```

Proveer incremento explícito (`registrarComparacion()` y `registrarMovimiento()`) y acceso de lectura. Crear una instancia distinta para cada algoritmo. `long` evita limitar innecesariamente el conteo para arreglos grandes.

### `ResultadoOrdenamiento` (opcional para la interfaz de salida)

Si se desea devolver resultados en vez de imprimir desde cada algoritmo, usar una estructura con el nombre del método, el arreglo ordenado y sus dos métricas. Cada resultado debe exponer una copia defensiva del arreglo para no permitir modificar el estado interno.

## Contratos y métodos

```java
public void ejecutarComparacion()
private void ordenarBurbuja(int[] arreglo, Metricas metricas)
private void ordenarSeleccion(int[] arreglo, Metricas metricas)
private void ordenarInsercion(int[] arreglo, Metricas metricas)
private boolean mismosElementosOrdenados(int[] a, int[] b)
private void mostrarResultado(String metodo, int[] arreglo, Metricas metricas)
```

`ejecutarComparacion()` invoca cada algoritmo exactamente una vez, en su copia correspondiente, comprueba que las tres salidas sean iguales y muestra las salidas y métricas. Los algoritmos ordenan ascendentemente y no usan utilidades de ordenamiento de bibliotecas.

## Instrumentación de algoritmos

- **Bubble Sort:** antes de cada evaluación `arreglo[j] > arreglo[j + 1]`, incrementar solo `metricasBurbuja.comparaciones`. Cuando se intercambian dos posiciones distintas, incrementar solo `metricasBurbuja.movimientos`. Puede detenerse tras una pasada sin intercambios.
- **Selection Sort:** antes de cada evaluación `arreglo[j] < arreglo[minimo]`, incrementar solo `metricasSeleccion.comparaciones`. Al terminar cada pasada, intercambiar solo si `minimo != i`; contar ese intercambio real como un movimiento.
- **Insertion Sort:** antes de cada evaluación de `arreglo[j] > clave`, incrementar solo `metricasInsercion.comparaciones`, incluyendo la evaluación final que determina que la condición es falsa cuando `j >= 0`. No contar cortocircuitos o comprobaciones de índices como comparaciones de valores. Cada asignación que desplaza un elemento una posición a la derecha incrementa solo `metricasInsercion.movimientos`. No contar guardar o colocar `clave` como movimiento.

Los contadores deben incrementarse junto a la operación medida, no inferirse al final a partir del tamaño del arreglo. La definición de movimiento difiere deliberadamente entre intercambios y desplazamientos y debe aparecer en la salida o explicación.

## Secuencia de ejecución

1. Crear un arreglo desordenado fijo para que la ejecución sea reproducible.
2. Construir `IntegradorOrdenamientos`; el constructor guarda una copia defensiva y crea las tres copias de trabajo desde la misma referencia inmutable.
3. Confirmar que cada copia contiene los mismos valores iniciales (puede hacerse con una aserción en pruebas; no es necesario imprimirlas).
4. Ejecutar cada algoritmo sobre su copia y con su instancia de `Metricas`.
5. Comprobar que los arreglos finales coincidan. Si no coinciden, informar el fallo explícitamente; no presentar la comparación como exitosa.
6. Mostrar las tres salidas, cada par de contadores, y la definición de las métricas.

## Reglas de consistencia

- No pasar la salida de un algoritmo como entrada al siguiente.
- No reutilizar `Metricas` entre algoritmos ni reiniciar contadores compartidos.
- Excluir verificaciones de índices y bucles de la cantidad de comparaciones.
- Excluir intercambios consigo misma, escrituras de la clave y el guardado temporal de la clave del conteo de movimientos.
- Las salidas de los tres algoritmos deben ser idénticas para cualquier entrada, incluidos valores repetidos, arreglos vacíos y de un elemento.