# Plan técnico del ejercicio 7: Ciclo while

## 1. Objetivo del plan

Definir la estructura mínima y clara de una solución en Java para el ejercicio de contador con `while`, priorizando:

- legibilidad del código
- uso correcto de la estructura repetitiva
- separación lógica entre inicialización, ejecución y finalización del ciclo
- explicación del costo computacional de la solución

## 2. Esqueleto de la clase

La solución se puede preparar como una clase con método principal, manteniendo la estructura más simple posible:

```java
public class Ejercicio7 {
    public static void main(String[] args) {
        int contador = 1;

        while (contador <= 10) {
            System.out.println(contador);
            contador++;
        }
    }
}
```

### Descripción del esqueleto

- `public class Ejercicio7`: nombre de la clase principal.
- `public static void main(String[] args)`: punto de entrada del programa.
- `int contador = 1`: inicialización de la variable de control.
- `while (contador <= 10)`: condición de continuidad del ciclo.
- `System.out.println(contador)`: acción repetida.
- `contador++`: actualización del contador para evitar bucle infinito.

## 3. Diseño funcional propuesto

Dado que el ejercicio es básico, no requiere una arquitectura compleja ni varios métodos. Sin embargo, se puede estructurar en dos enfoques:

### Opción A: solución mínima (recomendada)

Se resuelve directamente dentro del `main`.

```java
public class Ejercicio7 {
    public static void main(String[] args) {
        int contador = 1;

        while (contador <= 10) {
            System.out.println(contador);
            contador++;
        }
    }
}
```

### Opción B: solución modular (más didáctica)

Permite separar responsabilidades y mostrar una estructura más clara para la práctica.

```java
public class Ejercicio7 {
    public static void main(String[] args) {
        int limite = 10;
        int contador = inicializarContador();

        mostrarNumerosHasta(limite, contador);
    }

    public static int inicializarContador() {
        return 1;
    }

    public static void mostrarNumerosHasta(int limite, int contador) {
        while (contador <= limite) {
            System.out.println(contador);
            contador++;
        }
    }
}
```

## 4. Firmas de métodos sugeridas

En el enfoque modular, las firmas serían:

```java
public static int inicializarContador()
public static void mostrarNumerosHasta(int limite, int contador)
```

### Justificación

- `inicializarContador()`: devuelve el valor inicial del contador.
- `mostrarNumerosHasta(int limite, int contador)`: recibe el límite y la variable de control, y ejecuta la lógica del `while`.

## 5. Observaciones sobre diseño

### 5.1 Variable de control
La variable `contador` cumple el rol de:

- estado actual
- mecanismo para decidir si continúa el ciclo
- valor visible en consola

### 5.2 Condición del ciclo
La condición debe garantizar que el programa termine. Ejemplo:

```java
contador <= limite
```

Esto hace que el ciclo se ejecute exactamente mientras el contador no supere el valor máximo.

### 5.3 Actualización del contador
La operación de incremento es obligatoria:

```java
contador++;
```

Sin ella, el ciclo sería infinito.

## 6. Plan de complejidad asintótica

### 6.1 Tiempo
El ciclo ejecuta una iteración por cada valor del contador desde el inicio hasta el límite inclusive.

Si el límite es `n`, el número de iteraciones es `n - inicio + 1`.

En el caso típico con inicio en 1:

- complejidad temporal: `O(n)`

Esto se debe a que cada valor se procesa una sola vez.

### 6.2 Espacio
La solución usa una cantidad constante de variables:

- `contador`
- `limite` (si se usa)

Por lo tanto:

- complejidad espacial: `O(1)`

## 7. Caso general

Si el contador inicia en `inicio` y debe llegar hasta `fin`, entonces:

```java
while (contador <= fin) {
    // instrucción
    contador++;
}
```

El número total de iteraciones es:

```text
fin - inicio + 1
```

por lo que sigue siendo lineal respecto al rango de valores a recorrer.

## 8. Conclusión

La solución es una estructura repetitiva muy simple, pero con un punto clave: la variable contadora debe modificarse en cada iteración. Desde la perspectiva de análisis algorítmico, el programa tiene costo lineal en función del rango de números a mostrar y usa espacio constante.
