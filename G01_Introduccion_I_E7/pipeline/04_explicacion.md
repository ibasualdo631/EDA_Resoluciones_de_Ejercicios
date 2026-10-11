# Explicación didáctica del ejercicio 7: ciclo while

## 1. Qué hace el programa

El programa imprime una secuencia de números del 1 al 10 utilizando un ciclo `while`.

La idea principal es repetir una acción mientras una condición siga siendo verdadera. En este caso, la acción es mostrar el valor del contador en consola y la condición es que el contador no supere el valor 10.

## 2. Estructura general del programa

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

## 3. Cómo funciona paso a paso

### 3.1 Inicio del programa

La ejecución comienza en el método `main`:

```java
int limite = 10;
int contador = inicializarContador();
```

- `limite` representa el máximo valor que queremos mostrar.
- `contador` es la variable que irá cambiando durante la repetición.
- Se inicializa con `1`, porque queremos empezar desde el primer número.

### 3.2 Llamada a `inicializarContador()`

```java
public static int inicializarContador() {
    return 1;
}
```

Este método devuelve el valor inicial del contador. Como el ejercicio pide mostrar números desde 1, devuelve `1`.

### 3.3 Llamada a `mostrarNumerosHasta()`

```java
mostrarNumerosHasta(limite, contador);
```

Aquí se envían dos argumentos:

- el límite máximo: `10`
- el valor inicial del contador: `1`

### 3.4 Condición del ciclo `while`

```java
while (contador <= limite) {
```

La condición indica: “mientras el contador sea menor o igual que el límite, ejecuta el bloque”.

Esto significa que:

- si `contador = 1` y `limite = 10`, entra al ciclo
- cuando `contador = 11`, la condición deja de cumplirse y el ciclo termina

### 3.5 Cuerpo del ciclo

```java
System.out.println(contador);
contador++;
```

El bloque del `while` hace dos cosas:

1. Imprime el valor actual del contador.
2. Incrementa el valor del contador en 1.

Por ejemplo:

- Primero imprime `1`
- luego pasa a `2`
- luego a `3`
- ...
- hasta llegar a `10`

Cuando `contador` llega a `11`, la condición deja de cumplirse y el ciclo termina.

## 4. ¿Por qué es importante `contador++`?

La instrucción:

```java
contador++;
```

es esencial porque hace que el valor del contador cambie en cada iteración.

Si no se incrementara, el valor del contador seguiría siendo el mismo y el programa quedaría atrapado en un ciclo infinito.

Ejemplo incorrecto:

```java
while (contador <= limite) {
    System.out.println(contador);
    // falta contador++;
}
```

Eso haría que la condición siempre fuera verdadera, por lo que el programa no terminaría nunca.

## 5. Conceptos clave de Java que aparecen

### 5.1 `while`
Es una estructura repetitiva que ejecuta un bloque de instrucciones mientras una condición sea verdadera.

Forma general:

```java
while (condicion) {
    // bloque de código
}
```

### 5.2 Variable contador
Es una variable entera usada para llevar el control del número actual en la secuencia.

### 5.3 Condición de corte
Es la expresión que decide cuándo el ciclo debe detenerse. En este caso:

```java
contador <= limite
```

### 5.4 Incremento
La expresión `contador++` aumenta el contador en 1. Es la forma más simple de avanzar de un valor a otro.

## 6. Cómo se comporta la salida

La consola mostrará esta secuencia:

```text
1
2
3
4
5
6
7
8
9
10
```

Esto coincide con el resultado esperado del ejercicio.

## 7. Complejidad del algoritmo

### Tiempo
El programa ejecuta una iteración por cada número desde 1 hasta 10, por lo que se repite un número lineal de veces.

- Complejidad temporal: `O(n)`

### Espacio
Solo utiliza unas cuantas variables, sin guardar listas ni arreglos.

- Complejidad espacial: `O(1)`

## 8. Errores comunes que se evitaron

- No actualizar el contador.
- Usar la condición incorrecta, por ejemplo `contador < limite` y luego imprimir el límite con un valor que no se muestre según se espera.
- Olvidar la condición de corte.
- No entender que el bloque del `while` debe ejecutar una acción y luego cambiar el estado que controla la repetición.

## 9. Conclusión

Este ejercicio enseña la base de los ciclos repetitivos en Java. El `while` sirve para repetir una acción mientras una condición siga siendo verdadera. El punto clave es controlar la variable del contador y hacer que cambie en cada iteración para asegurar que el ciclo termine correctamente.

En resumen: el programa funciona porque:

- inicia en un valor determinado
- evalúa una condición
- imprime el valor actual
- aumenta el contador
- termina cuando ya no se cumple la condición

Eso es precisamente la lógica fundamental detrás de los bucles en programación.
