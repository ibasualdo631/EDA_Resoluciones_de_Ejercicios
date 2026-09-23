# Explicación conceptual y asintótica de `ValorMinimoVector`

## 1. ¿Qué hace este programa?

Este programa resuelve un problema clásico de estructuras de datos: encontrar el valor mínimo dentro de un arreglo de enteros. El método principal es `encontrarMinimo(int[] numeros)`, que recibe un arreglo y devuelve el elemento más pequeño.

La idea central es sencilla:
- asumir que el primer elemento es el mínimo actual,
- recorrer el arreglo desde la posición 1,
- comparar cada valor con el mínimo actual,
- actualizar el mínimo si se encuentra un valor menor.

Al final del recorrido, el valor que quedó guardado es el menor de todos.

---

## 2. Estructura del código

```java
public class ValorMinimoVector {

    public static int encontrarMinimo(int[] numeros) {
        validarArreglo(numeros);

        int minimo = numeros[0];

        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] < minimo) {
                minimo = numeros[i];
            }
        }

        return minimo;
    }

    private static void validarArreglo(int[] numeros) {
        if (numeros == null) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        }

        if (numeros.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede estar vacío.");
        }
    }

    public static void main(String[] args) {
        int[] datos = {12, 7, 3, 21, 5, 9};
        int minimo = encontrarMinimo(datos);
        System.out.println("El valor mínimo es: " + minimo);
    }
}
```

---

## 3. Cómo funciona paso a paso

### 3.1. Validación de entrada

```java
validarArreglo(numeros);
```

Antes de buscar el mínimo, el programa comprueba que el arreglo:
- no sea `null`
- no esté vacío

Esto evita errores como:
- intentar acceder a `numeros[0]` cuando el arreglo es `null`
- intentar buscar un mínimo en un conjunto sin elementos

Si hay un problema, se lanza una excepción con `IllegalArgumentException`.

### 3.2. Inicialización del mínimo

```java
int minimo = numeros[0];
```

Se toma el primer elemento como referencia inicial. Esto es válido porque al menos hay un elemento, gracias a la validación.

### 3.3. Bucle recorre el arreglo

```java
for (int i = 1; i < numeros.length; i++) {
    if (numeros[i] < minimo) {
        minimo = numeros[i];
    }
}
```

La variable `i` recorre cada posición del arreglo a partir de la segunda.

En cada iteración se compara:
- `numeros[i]` con el valor guardado en `minimo`
- si el elemento actual es menor, entonces se actualiza `minimo`

Esto mantiene en `minimo` el menor valor encontrado hasta ese momento.

### 3.4. Retorno del resultado

```java
return minimo;
```

Cuando termina el ciclo, `minimo` contiene el valor más pequeño del arreglo y se devuelve.

---

## 4. Conceptos de Java que aparecen

### 4.1. Arreglos
Un arreglo `int[]` almacena varios valores enteros en posiciones consecutivas.

Ejemplo:
```java
int[] datos = {12, 7, 3, 21, 5, 9};
```

Cada elemento está identificado por un índice:
- `datos[0] = 12`
- `datos[1] = 7`
- `datos[2] = 3`

### 4.2. Bucle `for`
El `for` permite repetir instrucciones un número determinado de veces.

```java
for (int i = 1; i < numeros.length; i++)
```

Esto significa:
- `i` comienza en 1
- se repite mientras `i` sea menor que la longitud del arreglo
- en cada vuelta, `i` aumenta en 1

### 4.3. Condicional `if`
El `if` decide si se actualiza el valor mínimo.

```java
if (numeros[i] < minimo) {
    minimo = numeros[i];
}
```

Si el valor actual es menor que el mínimo actual, entonces se reemplaza.

### 4.4. Excepciones
La clase `IllegalArgumentException` se usa para indicar que la entrada es inválida.

Esto es importante porque el algoritmo asume que el arreglo tiene al menos un elemento.

---

## 5. ¿Por qué esta solución es correcta?

La solución es correcta porque:
1. `minimo` siempre representa el menor valor encontrado hasta el momento.
2. El algoritmo revisa cada elemento del arreglo exactamente una vez.
3. Si encuentra un valor menor, lo reemplaza.
4. Al final, todos los elementos ya fueron comparados y el valor final en `minimo` es el menor de todos.

Este razonamiento es la base de la demostración de corrección del algoritmo.

---

## 6. Complejidad asintótica

### 6.1. Complejidad temporal

El algoritmo recorre cada elemento del arreglo una sola vez.

Si el arreglo tiene tamaño `n`, entonces:
- se compara cada elemento con el mínimo actual,
- el ciclo se repite `n - 1` veces aproximadamente,
- la operación es lineal en función de `n`.

Por lo tanto:

$$
T(n) = O(n)
$$

Esto significa que el tiempo crece de manera proporcional al tamaño del arreglo.

### 6.2. Complejidad espacial

Solo se usa una variable adicional llamada `minimo`.

No se crean arreglos extra ni estructuras temporales.

Entonces:

$$
S(n) = O(1)
$$

Esto significa que el espacio consumido es constante, independientemente del tamaño del arreglo.

---

## 7. Comparación con enfoques menos eficientes

Si se intentara resolver el problema de otra manera, por ejemplo:
- ordenar el arreglo primero
- buscar el mínimo con dos recorridos innecesarios
- usar recursión sin necesidad

entonces el costo aumentaría. En cambio, este algoritmo es óptimo para este caso porque:
- hace una sola pasada por los datos,
- usa memoria constante,
- responde la pregunta directamente.

---

## 8. Errores comunes que se evitan

1. Acceder a índices fuera de rango.
   - Si el arreglo está vacío, no se puede leer `numeros[0]`.

2. No validar la entrada.
   - Un arreglo `null` provoca error de ejecución.

3. Suposiciones incorrectas.
   - Si se asume que el primer elemento siempre es el mínimo, se podría cometer un error si el arreglo no se recorre correctamente.

4. Ordenar el arreglo para resolver un problema que no lo requiere.
   - Ordenar tiene un costo mayor que simplemente encontrar el mínimo.

---

## 9. Conclusión

`ValorMinimoVector` es un ejemplo claro de algoritmo lineal: recorre el arreglo una sola vez, mantiene el menor valor encontrado hasta el momento y devuelve el resultado final. Su implementación es simple, eficiente y fácil de entender.

En términos de análisis asintótico:
- tiempo: $O(n)$
- espacio: $O(1)$

Esto convierte al algoritmo en una solución ideal para buscar el valor mínimo dentro de un conjunto de enteros.
