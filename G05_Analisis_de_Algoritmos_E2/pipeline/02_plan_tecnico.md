# Plan técnico: búsqueda de un elemento en un vector desordenado

## 1. Objetivo

Diseñar una solución en Java para buscar un valor dentro de un vector no ordenado, justificando la estrategia por la ausencia de orden y calculando su complejidad asintótica. La solución debe recorrer el arreglo de forma secuencial y reportar cuántas posiciones se revisaron antes de encontrar el valor o declarar que no existe.

---

## 2. Estructura de la clase

```java
public class BusquedaVectorDesordenado {

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

    private static void validarVector(int[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("El vector no puede ser nulo.");
        }

        if (vector.length == 0) {
            throw new IllegalArgumentException("El vector no puede estar vacío.");
        }
    }

    public static void main(String[] args) {
        int[] vector = {7, 12, 3, 9, 15, 2, 8};
        int valorBuscado = 9;

        int resultado = buscarElemento(vector, valorBuscado);

        if (resultado != -1) {
            System.out.println("El elemento " + valorBuscado + " sí está en el vector.");
        } else {
            System.out.println("El elemento " + valorBuscado + " no está en el vector.");
        }
    }
}
```

### Descripción

- La clase se llamará `BusquedaVectorDesordenado` y encapsula la lógica de la búsqueda.
- El método principal es `buscarElemento`, encargado de recorrer el vector y decidir si existe el valor buscado.
- El método `validarVector` evita errores de entrada como `null` o un arreglo vacío.
- La salida por consola informa tanto la posición como la cantidad de comparaciones realizadas.

---

## 3. Firmas de métodos

### Método principal
```java
public static int buscarElemento(int[] vector, int valorBuscado)
```
- Entrada: un arreglo de enteros `int[]` y un valor entero `int`.
- Salida: la posición del elemento en el vector si existe; `-1` si no existe.
- Propósito: recorrer el vector secuencialmente comparando cada elemento con el valor buscado.

### Método de validación
```java
private static void validarVector(int[] vector)
```
- Entrada: un arreglo de enteros `int[]`.
- Salida: ninguna (`void`).
- Propósito: asegurar que el vector no sea `null` ni vacío antes de iniciar la búsqueda.

### Método de prueba de ejecución
```java
public static void main(String[] args)
```
- Entrada: argumentos de la línea de comandos.
- Salida: mensajes por pantalla con el resultado.
- Propósito: verificar el comportamiento del algoritmo con un caso de prueba concreto.

---

## 4. Idea del algoritmo

1. Se recibe el vector y el valor a buscar.
2. Se valida que el vector sea válido.
3. Se recorre desde la posición `0` hasta `vector.length - 1`.
4. En cada iteración se compara `vector[i]` con `valorBuscado`.
5. Si coinciden, se devuelve la posición `i` y se informa cuántas posiciones se recorrieron.
6. Si el recorrido termina sin encontrar coincidencia, se devuelve `-1` y se reporta el total recorrido.

### Ejemplo de ejecución

Vector:
```java
[7, 12, 3, 9, 15, 2, 8]
```
Valor buscado: `9`

Comportamiento:
- compara 7
- compara 12
- compara 3
- compara 9 -> coincide
- se devuelve la posición 3
- posiciones recorridas: 4

---

## 5. Complejidad asintótica

### Tiempo

La búsqueda secuencial compara el valor buscado con cada elemento del vector hasta encontrarlo o llegar al final.

- Mejor caso: el valor está en la primera posición.
  - Se hace 1 comparación.
  - Complejidad: $O(1)$

- Peor caso: el valor no existe o aparece al final.
  - Se revisan todas las posiciones.
  - Complejidad: $O(n)$

- Caso promedio: el valor puede estar en cualquier posición.
  - Se revisa aproximadamente la mitad del arreglo.
  - Complejidad: $O(n)$

Por tanto, la complejidad temporal del algoritmo es:

$$
T(n) = O(n)
$$

### Espacio

Se utilizan variables simples como el índice del ciclo y el contador de posiciones recorridas. No se crean estructuras auxiliares adicionales.

$$
S(n) = O(1)
$$

### Justificación formal

La búsqueda lineal puede describirse como:

$$
T(n) = T(n-1) + O(1)
$$

Resolviendo la recurrencia:

$$
T(n) = O(n)
$$

Esto demuestra que el algoritmo es lineal en el número de elementos del arreglo.

---

## 6. Conclusión

La solución más adecuada para un vector desordenado es la búsqueda secuencial. Esta estrategia no depende del orden de los elementos y garantiza una respuesta correcta en todos los casos. Su complejidad es lineal, `O(n)`, y requiere espacio constante, `O(1)`, lo que la convierte en una solución simple, eficiente y correcta para este problema.
