# Plan técnico: búsqueda del valor mínimo en un arreglo de enteros

## 1. Objetivo
Desarrollar un software en Java que recorra un arreglo de enteros `int[]` y devuelva el menor valor existente, usando una estrategia que garantice una complejidad temporal lineal: `O(n)`.

La solución propuesta será iterativa, ya que evita el costo adicional de llamadas recursivas y mantiene un consumo constante de memoria.

---

## 2. Estructura de la clase

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

### Descripción
- La clase se llama `ValorMinimoVector` para reflejar claramente la responsabilidad del programa.
- El método principal `encontrarMinimo` es el punto central del algoritmo.
- El método `validarArreglo` es responsable de asegurar que la entrada sea válida antes de procesarla.
- El bloque `main` sirve como ejemplo de uso y prueba manual.

---

## 3. Firmas de métodos

### Método principal
```java
public static int encontrarMinimo(int[] numeros)
```
- Entrada: arreglo de enteros `int[]`.
- Salida: el menor valor del arreglo como `int`.
- Propósito: revisar cada elemento exactamente una vez para decidir cuál es el mínimo.

### Método de validación
```java
private static void validarArreglo(int[] numeros)
```
- Entrada: arreglo de enteros `int[]`.
- Salida: ninguna (`void`).
- Propósito: eliminar entradas inválidas como `null` o arreglos vacíos.

### Método de prueba de ejecución
```java
public static void main(String[] args)
```
- Entrada: argumentos de consola.
- Salida: impresión del resultado.
- Propósito: verificar el comportamiento del algoritmo en un caso de prueba.

---

## 4. Idea del algoritmo

1. Se toma el primer elemento del arreglo como valor inicial de referencia.
2. Se recorre el arreglo desde la posición `1` hasta el final.
3. Si el elemento actual es menor que el mínimo encontrado hasta ese momento, se actualiza el valor del mínimo.
4. Al finalizar el recorrido, se devuelve el mínimo acumulado.

### Ejemplo de ejecución
Para el arreglo:
```java
[12, 7, 3, 21, 5, 9]
```
- inicio: `minimo = 12`
- compara 7 -> `minimo = 7`
- compara 3 -> `minimo = 3`
- compara 21 -> no cambia
- compara 5 -> no cambia
- compara 9 -> no cambia
- resultado final: `3`

---

## 5. Complejidad asintótica

### Tiempo
El algoritmo visita cada elemento del arreglo exactamente una vez.

- En el peor caso: `n` comparaciones.
- En el mejor caso: `n` comparaciones, porque también debe recorrer todo el arreglo.
- En el caso promedio: `n` comparaciones.

Por lo tanto:

$$
T(n) = O(n)
$$

### Espacio
No se utilizan estructuras auxiliares adicionales ni arreglos temporales.

Se usa únicamente una variable para almacenar el valor mínimo actual.

$$
S(n) = O(1)
$$

### Justificación formal
La recurrencia del enfoque iterativo puede expresarse como:

$$
T(n) = T(n-1) + O(1)
$$

Resolviendo la recurrencia:

$$
T(n) = O(n)
$$

Esto demuestra que el algoritmo es lineal en la cantidad de elementos del arreglo.

---

## 6. Conclusión
La solución propuesta cumple con el requisito de complejidad `O(n)`, además de ser simple, eficiente y fácil de mantener. La estrategia iterativa es la más adecuada para este problema porque requiere una sola pasada por el arreglo y usa memoria constante.
