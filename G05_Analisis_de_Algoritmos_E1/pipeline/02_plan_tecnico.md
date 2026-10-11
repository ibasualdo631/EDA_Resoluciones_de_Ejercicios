# Plan técnico: mínimo de un vector de enteros

## 1. Objetivo y contrato

Implementar en Java una operación que devuelva el menor entero presente en un arreglo primitivo `int[]`. La solución recorrerá el vector una sola vez, no lo ordenará ni lo modificará y rechazará las entradas para las que el mínimo no está definido.

### Contrato del método

```java
public static int encontrarMinimo(int[] vector)
```

- **Precondición:** `vector` no es `null` y contiene al menos un elemento.
- **Resultado:** el valor entero mínimo del vector.
- **Entrada inválida:** lanzar `IllegalArgumentException` si `vector == null` o `vector.length == 0`.
- **Efectos:** no altera el contenido del arreglo.

Se elige `IllegalArgumentException` para ambos casos porque el arreglo recibido incumple el dominio válido del método; se comprueba antes de acceder a `vector[0]`.

## 2. Estructura técnica

Se implementará una clase independiente llamada `MinimoVector`:

```java
public final class MinimoVector {
    private MinimoVector() {
    }

    public static int encontrarMinimo(int[] vector) {
        // Validar, inicializar minimo y recorrer el arreglo.
    }

    public static void main(String[] args) {
        // Demostraciones de consola de los casos representativos.
    }
}
```

La clase no necesita estado ni instancias: el algoritmo solo requiere los datos de entrada y sus variables locales. El método `main` ofrecerá ejemplos ejecutables, sin mezclar la lógica del método de cálculo con la presentación del resultado.

## 3. Secuencia del algoritmo

1. Comprobar si la referencia es `null`; si lo es, lanzar `IllegalArgumentException`.
2. Comprobar si la longitud es cero; si lo es, lanzar `IllegalArgumentException`.
3. Inicializar `int minimo = vector[0]`.
4. Recorrer con un bucle iterativo desde `i = 1` mientras `i < vector.length`.
5. Si `vector[i] < minimo`, actualizar `minimo`.
6. Al terminar el recorrido, devolver `minimo`.

Inicializar desde el primer elemento real soporta valores positivos, negativos y todo el rango de `int`, incluido `Integer.MIN_VALUE`; evita un centinela arbitrario y el error de iniciar en cero.

## 4. Complejidad asintótica formal

Para un vector válido de longitud `n`, el algoritmo realiza una inspección de cada elemento. La inicialización procesa el primero; el bucle compara cada uno de los `n - 1` restantes una vez.

| Escenario / medida | Complejidad | Fundamentación |
|---|---:|---|
| Mejor caso | `Ω(n)` | Incluso si el primer elemento resulta ser el mínimo, los demás deben inspeccionarse: cualquiera podría ser menor. |
| Peor caso | `O(n)` | Se visita cada posición una sola vez; el mínimo podría estar al final. |
| Caso promedio | `Θ(n)` | El número de elementos inspeccionados no depende de la distribución; siempre es `n`. |
| Tiempo ajustado para cualquier entrada válida | `Θ(n)` | Se hacen `n - 1` comparaciones, salvo el caso `n = 1`, en que son cero; el crecimiento asintótico sigue siendo lineal. |
| Espacio auxiliar (RAM) | `O(1)` | Se usa una cantidad fija de almacenamiento: índice y variable `minimo`; no se crean arreglos ni estructuras proporcionales a `n`. |

No hay corte anticipado correcto: encontrar un candidato pequeño no demuestra que sea el mínimo hasta leer todos los elementos restantes. En el modelo RAM, el acceso por índice, la comparación entre enteros y la asignación se consideran operaciones de costo constante.

## 5. Decisión: no ordenar el vector

Ordenar previamente no es necesario para calcular el mínimo. Un ordenamiento eficiente por comparaciones cuesta normalmente `O(n log n)` tiempo; después de ordenarlo solo se leería el primer elemento. El recorrido secuencial obtiene directamente el mínimo en `Θ(n)`, que además es asintóticamente óptimo porque es necesario inspeccionar todos los elementos. El recorrido utiliza `O(1)` espacio auxiliar y conserva el orden original del vector.

No se afirmará que todo ordenamiento usa necesariamente `O(n)` memoria adicional: ese costo depende del algoritmo y de su implementación. La justificación de la ineficiencia es suficiente con el trabajo temporal adicional de ordenar para resolver una tarea que requiere un único recorrido lineal.

## 6. Casos que la implementación debe contemplar

- Datos positivos y desordenados.
- Valores negativos, incluyendo un vector compuesto únicamente por negativos.
- Elementos repetidos y mínimo repetido.
- Mínimo ubicado al inicio, en una posición intermedia o al final.
- Vector de un elemento.
- Vectores ordenados ascendente y descendentemente.
- `Integer.MIN_VALUE`.
- Arreglo `null` y arreglo vacío: cada uno debe producir `IllegalArgumentException`.

## 7. Hyper-Prompt definitivo para el Agente Desarrollador

> **Actúa como Desarrollador Java Senior.** Implementa el ejercicio académico “Encontrar el valor mínimo de un vector de números enteros”, siguiendo este contrato y plan:
>
> 1. Crea una clase `MinimoVector` y el método `public static int encontrarMinimo(int[] vector)`.
> 2. Si `vector` es `null` o tiene longitud cero, lanza `IllegalArgumentException` con un mensaje claro. Valida antes de cualquier acceso al arreglo.
> 3. Inicializa la variable `minimo` con `vector[0]` y usa un bucle iterativo desde el índice `1` hasta `vector.length - 1`. Actualiza `minimo` únicamente cuando el elemento actual es menor. No ordenes ni modifiques el vector.
> 4. Incluye en la cabecera del archivo un bloque de comentario que cite este prompt técnico inicial y anote cualquier ajuste realizado.
> 5. Añade comentarios claros que expliquen explícitamente por qué ordenar el vector antes de buscar el mínimo sería una ineficiencia algorítmica: un ordenamiento eficiente de comparación suele costar `O(n log n)`, mientras que el recorrido lineal encuentra directamente el mínimo en `Θ(n)`. Explica que es necesario examinar todos los elementos porque cualquiera podría ser menor, aunque ya exista un candidato, y que el recorrido usa `O(1)` espacio auxiliar. No afirmes que todos los algoritmos de ordenamiento necesariamente requieren `O(n)` memoria adicional.
> 6. Agrega un `main` ejecutable que demuestre casos con enteros positivos, negativos, desordenados, repetidos y un solo elemento; incluye ejemplos donde el mínimo aparezca al inicio, en el medio y al final, arreglos ascendente y descendente, y `Integer.MIN_VALUE`. Comprueba también `null` y arreglo vacío, verificando que se lance `IllegalArgumentException`.
> 7. Después del código, explica el funcionamiento y presenta la complejidad: mejor caso `Ω(n)`, peor caso `O(n)`, promedio `Θ(n)` y espacio RAM auxiliar `O(1)`. Aclara que para toda entrada válida el algoritmo inspecciona todos los elementos, por lo que su tiempo es ajustadamente `Θ(n)` y no tiene corte anticipado.
>
> Entrega código Java completo, compilable, legible y adecuado para la cátedra de Análisis de Algoritmos y Vectores. Los mensajes del `main` deben permitir comprobar visualmente los resultados y el tratamiento de entradas inválidas.
