# Guía explicativa — Ejercicio 4: Detectar elementos duplicados

## Propósito del programa

La clase `DetectorDuplicados` resuelve una pregunta concreta: **¿hay al menos dos posiciones del vector que contienen el mismo entero?** Por eso, los dos métodos devuelven solamente un `boolean`; no intentan listar todos los repetidos ni indicar cuál se encontró primero.

La solución presenta dos maneras de contestar lo mismo:

1. **Fuerza bruta:** compara pares de elementos directamente.
2. **HashSet:** recuerda los valores que ya aparecieron y detecta cuándo intenta ingresar uno otra vez.

Este es el trade-off central del ejercicio: hacer más trabajo de CPU y usar poca memoria auxiliar, o usar más memoria para evitar repetir comparaciones.

## 1. Recorrido del código

### Clase de utilidad

`DetectorDuplicados` es una clase `public final` con constructor privado. Los métodos que resuelven el problema son estáticos, así que se llaman directamente, por ejemplo, `DetectorDuplicados.tieneDuplicadosHashSet(vector)`, sin crear un objeto `DetectorDuplicados`.

### Validación y casos de frontera

Los dos métodos llaman primero a `validarVector`. Si la referencia es `null`, el método lanza `IllegalArgumentException`. Se elige una excepción explícita porque `null` no es lo mismo que un vector válido sin elementos: indica que falta el argumento requerido.

Después de validar, ambos métodos devuelven `false` si `vector.length <= 1`. Un vector vacío o con un elemento no tiene dos posiciones que puedan contener el mismo valor. Esta condición evita trabajo innecesario y, en fuerza bruta, hace que los bucles no comiencen para esos tamaños.

El orden importa: se valida `null` **antes** de leer `vector.length`, pues acceder a la longitud de una referencia nula produciría un `NullPointerException` en vez de la excepción definida por el contrato.

### Fuerza bruta: comparar cada par una vez

El bucle externo elige un elemento `i`; el bucle interno compara ese elemento con los que aparecen después:

```java
for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {
        if (vector[i] == vector[j]) {
            return true;
        }
    }

    [8, 3, 10, 3, 11]
    
     
}
return false;
```

Empezar `j` en `i + 1` tiene dos ventajas:

- No se compara un elemento consigo mismo.
- No se repite la comparación en sentido inverso. Si ya se comprobó `(i, j)`, no hace falta volver a comprobar `(j, i)`.

Por ejemplo, para `[8, 3, 8]`, primero se compara la posición 0 con la 1 (`8` y `3`), luego la posición 0 con la 2 (`8` y `8`). Al encontrar igualdad, `return true` termina el método inmediatamente; no hace falta continuar con el resto de los pares.

Si se terminan los bucles, cada pareja posible fue comprobada, así que el método puede concluir que no hay duplicados y devolver `false`.

### HashSet: recordar lo que ya se vio

El segundo método recorre los valores con un `for-each` y llama a `vistos.add(valor)`:

```java
HashSet<Integer> vistos = new HashSet<>();
for (int valor : vector) {
    if (!vistos.add(valor)) {
        return true;
    }
}
return false;
```

- Si el conjunto todavía no contiene ese valor, `add` lo incorpora y devuelve `true`.
- Si el conjunto ya contiene un valor igual, no lo agrega de nuevo y devuelve `false`; el método entonces devuelve `true`, indicando que encontró un duplicado.
- Si el recorrido termina sin que `add` falle, devuelve `false`.

El conjunto almacena el historial de valores visitados; así, cada elemento se consulta contra ese historial en lugar de volver a compararlo manualmente con todos los anteriores.

## 2. Trade-off CPU frente a RAM

### Fuerza bruta: poca memoria auxiliar, más comparaciones

En el caso sin duplicados, el método debe revisar todos los pares. Para un vector de tamaño `n`, son `n(n - 1) / 2` comparaciones: el trabajo crece cuadráticamente, **Θ(n²)**.

Su espacio auxiliar es **O(1)**: aparte del vector que ya recibió, usa una cantidad fija de variables (índices, longitud y valores temporales), sin crear otra colección cuyo tamaño crezca con `n`.

O(1) **no significa que el programa use literalmente cero bytes de RAM**. El método, sus variables y la pila de ejecución ocupan memoria. Significa que la cantidad de memoria adicional atribuible al algoritmo se mantiene acotada y no aumenta proporcionalmente al tamaño del vector.

### HashSet: más memoria auxiliar, menos trabajo promedio

En vez de volver a comparar valores contra todos los anteriores, HashSet conserva los valores ya vistos. Bajo una distribución adecuada de hashes, agregar o buscar cuesta **O(1) promedio**; si se procesa el vector completo, las `n` operaciones cuestan **O(n) promedio**.

El precio es la memoria para el conjunto: puede guardar hasta `n` valores distintos, por lo que requiere **O(n) de espacio auxiliar**. En Java, el código usa `HashSet<Integer>` aunque la entrada sea `int[]`; el autoboxing convierte cada `int` a un `Integer` cuando se agrega al conjunto. También se necesita la estructura interna del conjunto —tabla de buckets y entradas que enlazan o representan los elementos— en el heap. Estos detalles aumentan el consumo real frente a las variables de la fuerza bruta, pero la clase asintótica sigue siendo O(n).

No debe asumirse que toda conversión produce necesariamente un objeto `Integer` nuevo: Java puede reutilizar instancias en ciertos casos, y eso no cambia el análisis O(n) del conjunto.

### Comparación resumida

| Estrategia | Tiempo si no hay duplicados | Mejor caso con duplicado temprano | Memoria auxiliar |
|---|---:|---:|---:|
| Fuerza bruta | Θ(n²) | Θ(1) | O(1) |
| HashSet | O(n) promedio, con baja colisión | O(1) promedio al detectar muy pronto | O(n) |

El vector sin duplicados fuerza a ambas soluciones a recorrer toda la entrada y es el caso de mayor trabajo dentro de estos escenarios. Para HashSet, la cota lineal indicada es **promedio**, no una garantía incondicional para cualquier distribución de colisiones. La inserción puede requerir trabajo adicional cuando varios valores caen en el mismo bucket.

## 3. Cómo funciona conceptualmente una tabla hash

Una tabla hash utiliza una función hash para transformar la información del valor en un número que ayuda a elegir un **bucket** (un espacio o grupo de búsqueda dentro de la tabla). Al insertar un elemento, la colección:

1. Calcula el hash del elemento.
2. Usa ese resultado para ubicar el bucket candidato.
3. Comprueba dentro de ese bucket si ya hay un elemento igual.
4. Si no lo hay, inserta el valor; si ya hay uno igual, conserva un solo elemento y `add` devuelve `false`.

Un bucket puede contener varios elementos cuando ocurren **colisiones de hash**. Una colisión significa que dos valores llegan al mismo bucket o comparten un hash; no significa automáticamente que los valores sean iguales. HashSet distingue la igualdad real (para `Integer`, el valor entero) de una mera coincidencia de hash. Por eso valores distintos pueden coexistir aunque colisionen.

El promedio de O(1) se explica porque, con una buena distribución, cada operación examina solo unos pocos elementos del bucket correspondiente, no todos los valores almacenados. Si las colisiones se concentran, el bucket exige más trabajo y el costo promedio esperado ya no describe necesariamente esa ejecución.

## 4. Demostración de la suma de comparaciones

En un vector de `n` elementos sin duplicados:

- El elemento de índice 0 se compara con `n - 1` elementos posteriores.
- El elemento de índice 1 se compara con `n - 2`.
- El elemento de índice 2 se compara con `n - 3`.
- ...
- El penúltimo elemento se compara con 1 elemento.
- El último no tiene elementos posteriores.

Por lo tanto, el total es:

```text
(n - 1) + (n - 2) + ... + 2 + 1
```

Es la suma de los primeros `n - 1` enteros. La fórmula de una serie de `m` términos consecutivos desde 1 hasta `m` es `m(m + 1) / 2`; usando `m = n - 1`:

```text
1 + 2 + ... + (n - 1)
= (n - 1)n / 2
= (n² - n) / 2
= (1/2)n² - (1/2)n
```

Al analizar crecimiento asintótico se omiten constantes multiplicativas y términos de menor orden. El término dominante es `n²`; por eso la cantidad de comparaciones crece como **Θ(n²)** y, en particular, está en **O(n²)**.

Ejemplo para `n = 4`:

```text
(4 - 1) + (4 - 2) + (4 - 3) = 3 + 2 + 1 = 6
4(4 - 1) / 2 = 6
```

La fórmula cuenta las parejas sin orden: `(0, 1)` y `(1, 0)` son la misma pareja para esta tarea, y el límite `j = i + 1` evita contarlas dos veces.

## 5. Complejidad en función de dónde aparece el duplicado

El **mejor caso** se produce si `vector[0] == vector[1]`: fuerza bruta hace una comparación y HashSet inserta el primer elemento y detecta el segundo. Ambos pueden terminar en tiempo constante, Θ(1), asumiendo operaciones promedio de HashSet.

Si el duplicado aparece más tarde, ambas estrategias pueden terminar antes de procesar toda la entrada, pero el número de pasos depende de la ubicación y del orden de comparación. Si no hay duplicados, la fuerza bruta tiene que comprobar todos los pares y HashSet debe procesar todos los elementos.

Un vector vacío o unitario retorna `false` inmediatamente después de validar el argumento. `null`, en cambio, no representa un caso de resultado: se rechaza lanzando `IllegalArgumentException`.

## 6. Preguntas para el examen oral

1. Si la memoria disponible fuera muy limitada, pero el vector pudiera ser grande, ¿qué solución elegirías y qué costo de tiempo aceptarías? Justifica la decisión con las complejidades y un contexto de uso concreto.

Si la memoria disponible es muy ajustada, optaría de manera directa por la opción número 1, la fuerza bruta, puesto que en caso de  elegir la opción 2, estaría empleando un arreglo auxiliar que me haría ocupar más memoria, pues no estoy copiando mi vector original pero si estoy almacenando hasta n valores en una estructura auxiliar, empleando O(n) de memoria.

2. Si HashSet tiene O(1) promedio para una inserción, ¿por qué el recorrido completo es O(n), y qué podría ocurrir si muchos valores producen colisiones?

El recorrido completo es O(n) ya que debe recorrer todos los elementos del arreglo original. Si hay muchas colisiones, hacer varias incersiones conlleva más trabajo. Encontrar un duplicado temprano permite cortar antes, pero  no impide que haya colisiones entre valores distintos.

3. En la suma `(n - 1) + (n - 2) + ... + 1`, ¿por qué se comparan solo los elementos posteriores a `i`, y cómo esa decisión lleva a `n(n - 1) / 2` en vez de comparar todos los pares dos veces?
Se comparan solo los elementos posteriores a i asi no se compara un elemento consigo mismo y cada pareja se compare solo una vez (i,j) y luego (j,i).