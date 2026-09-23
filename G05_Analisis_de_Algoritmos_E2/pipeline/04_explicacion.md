# Explicación conceptual y asintótica del algoritmo de búsqueda en un vector desordenado

## 1. Qué hace el programa

La clase `BusquedaVectorDesordenado` implementa una búsqueda secuencial de un valor dentro de un arreglo no ordenado. La idea principal es recorrer cada posición del vector desde el inicio hasta el final y comparar cada elemento con el valor que estamos buscando.

Si el elemento coincide, el algoritmo devuelve la posición en la que apareció. Si no aparece en ninguna posición, devuelve `-1`.

Este tipo de búsqueda se llama búsqueda lineal o secuencial, y es la estrategia correcta cuando el arreglo no está ordenado.

---

## 2. Por qué se eligió esta estrategia

En un vector desordenado no existe ninguna relación de orden entre los elementos. Por ejemplo, si el arreglo es:

```java
{7, 12, 3, 9, 15, 2, 8}
```

no podemos suponer que todos los valores menores se encuentren a la izquierda o que los mayores estén a la derecha. Por eso, no es posible aplicar una búsqueda binaria, ya que ésta requiere que el conjunto esté ordenado previamente.

La solución adecuada es recorrer cada elemento uno por uno hasta encontrar el valor buscado o confirmar que no existe.

---

## 3. Estructura de la clase

```java
public class BusquedaVectorDesordenado {
```

La clase tiene dos responsabilidades principales:

1. validar la entrada;
2. buscar el valor en el arreglo.

Dentro de ella se define el método principal:

```java
public static int buscarElemento(int[] vector, int valorBuscado)
```

y también el método auxiliar:

```java
private static void validarVector(int[] vector)
```

Además, el método `main` sirve para ejecutar una prueba rápida del programa.

---

## 4. Explicación del método `buscarElemento`

```java
public static int buscarElemento(int[] vector, int valorBuscado) {
    validarVector(vector);

    int posicionesRecorridas = 0;

    for (int i = 0; i < vector.length; i++) {
        posicionesRecorridas++;

        if (vector[i] == valorBuscado) {
            System.out.println("Elemento encontrado en la posición: " + i);
            System.out.println("Posiciones recorridas: " + posicionesRecorridas);
            return i;
        }
    }

    System.out.println("El elemento no existe en el vector.");
    System.out.println("Posiciones recorridas: " + posicionesRecorridas);
    return -1;
}
```

### ¿Qué ocurre paso a paso?

1. Se valida que el vector no sea `null` ni vacío.
2. Se inicializa el contador `posicionesRecorridas` en `0`.
3. Se entra en el ciclo `for`:
   - `i` comienza en `0`;
   - mientras `i` sea menor que la longitud del vector, se sigue ejecutando;
   - al final de cada vuelta se incrementa `i`.
4. En cada iteración:
   - se aumenta el contador de posiciones recorridas;
   - se compara `vector[i]` con `valorBuscado`.
5. Si son iguales, se imprime la posición y se devuelve el índice `i`.
6. Si el ciclo termina sin coincidencias, se imprime un mensaje indicando que el valor no existe y se devuelve `-1`.

### Conceptos de Java involucrados

- `for`: estructura de repetición que ejecuta instrucciones varias veces.
- `int[]`: arreglo de enteros.
- `if`: condicional para comparar valores.
- `return`: devuelve un valor al método y termina su ejecución.
- `System.out.println`: muestra mensajes en la consola.

---

## 5. Explicación del método `validarVector`

```java
private static void validarVector(int[] vector) {
    if (vector == null) {
        throw new IllegalArgumentException("El vector no puede ser nulo.");
    }

    if (vector.length == 0) {
        throw new IllegalArgumentException("El vector no puede estar vacío.");
    }
}
```

Este método sirve para evitar errores de entrada. Si el arreglo es nulo o no tiene elementos, el programa lanza una excepción con un mensaje claro.

Esto hace que la lógica de búsqueda sea más segura y robusta, porque no intenta iterar sobre una estructura inválida.

---

## 6. Cómo funciona el contador de recorridas

El contador:

```java
int posicionesRecorridas = 0;
```

se incrementa cada vez que se revisa una posición del vector:

```java
posicionesRecorridas++;
```

Esto permite responder a la pregunta del enunciado: "¿cuántas posiciones fueron recorridas hasta encontrar el elemento o hasta determinar que no existe?"

Por ejemplo, si el valor está en la posición 3 del arreglo, el algoritmo habrá comparado 4 posiciones antes de encontrarlo. Ese valor se refleja en la salida por consola.

---

## 7. Análisis de complejidad asintótica

### 7.1. Complejidad temporal

La búsqueda lineal revisa, en el peor caso, cada elemento del arreglo una sola vez. Por lo tanto, su costo temporal depende del tamaño del vector, denotado como `n`.

#### Caso mejor

Cuando el valor buscado está en la primera posición:

- se compara solo con el primer elemento;
- se encuentra de inmediato.

Complejidad:

$$
O(1)
$$

#### Caso peor

Cuando el valor no está en el arreglo o está en la última posición:

- se revisan todas las posiciones;
- se hace un recorrido completo.

Complejidad:

$$
O(n)
$$

#### Caso promedio

En una situación típica, el valor podría aparecer en cualquier posición del vector, por lo que el algoritmo recorre aproximadamente la mitad del arreglo.

Aún así, la complejidad asintótica sigue siendo lineal:

$$
O(n)
$$

### Conclusión temporal

La complejidad general del algoritmo es:

$$
T(n) = O(n)
$$

porque en el peor caso se necesita recorrer todos los elementos.

---

## 8. Complejidad espacial

El algoritmo usa unas pocas variables: el índice del ciclo, el valor buscado y el contador de recorridas.

No crea estructuras auxiliares ni copias del arreglo.

Por lo tanto, la complejidad espacial es:

$$
O(1)
$$

---

## 9. Relación con la teoría de algoritmos

Este problema es un claro ejemplo de búsqueda secuencial, una operación básica en estructuras de datos lineales.

La búsqueda secuencial es útil cuando:

- el arreglo no está ordenado;
- se necesita encontrar un valor sin imponer condiciones extra;
- la simplicidad del algoritmo es más importante que optimizar la búsqueda.

La principal ventaja es que funciona siempre, aunque no sea la más eficiente posible. La principal desventaja es que, en arreglos grandes, el tiempo crece proporcionalmente al tamaño del vector.

---

## 10. Ejemplo de ejecución

Código de prueba:

```java
int[] vector = {7, 12, 3, 9, 15, 2, 8};
int valorBuscado = 9;
```

Ejecución:

- compara 7
- compara 12
- compara 3
- compara 9 -> coincide
- devuelve la posición 3

La salida será algo como:

```text
Elemento encontrado en la posición: 3
Posiciones recorridas: 4
El elemento 9 sí está en el vector.
```

---

## 11. Conclusión

El programa `BusquedaVectorDesordenado` resuelve el problema mediante una búsqueda secuencial. Esta estrategia es la adecuada porque el vector no está ordenado, y por esa razón no se puede aplicar una búsqueda binaria.

Desde el punto de vista asintótico:

- tiempo: $O(n)$
- espacio: $O(1)$

Esto hace que el algoritmo sea simple, correcto y eficiente dentro del contexto del problema planteado.
