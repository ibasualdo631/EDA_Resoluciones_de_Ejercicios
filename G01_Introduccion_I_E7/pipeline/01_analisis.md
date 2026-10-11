# Análisis del ejercicio 7: Ciclo while

## 1. Objetivo del ejercicio

Se pide crear un programa en Java que utilice un ciclo `while` para repetir una acción mientras se cumpla una condición. La acción principal consiste en mostrar un contador en consola y, por lo tanto, reforzar el uso de:

- `while`
- variable contadora
- condición de corte
- incremento o actualización del contador
- prevención de bucles infinitos

## 2. Consigna interpretada

La consigna indica:

> Crear un programa que utilice un ciclo while para repetir una acción mientras se cumpla una condición.

En el contexto del ejercicio, la acción repetida es la impresión de números en consola. El programa debe ejecutar la instrucción mientras el contador no supere un valor límite.

## 3. Prompt sugerido para OpenCode

> Genera un programa en Java que muestre un contador utilizando while.

## 4. Requisitos funcionales

1. Definir una variable de tipo entero como contador.
2. Establecer una condición lógica de corte, por ejemplo: `contador <= 10`.
3. Dentro del `while`, imprimir el valor actual del contador.
4. Actualizar el contador en cada iteración, por ejemplo con `contador++`.
5. Asegurarse de que la condición eventualmente deje de cumplirse para evitar un ciclo infinito.
6. Mostrar la secuencia de números en consola.

## 5. Conceptos a explicar

### 5.1 `while`
Es una estructura de control repetitiva que ejecuta un bloque de código mientras una condición sea verdadera.

Estructura general:

```java
while (condicion) {
    // bloque de instrucciones
}
```

### 5.2 Contador
Es una variable que lleva registro del número de iteraciones o del valor actual que se está mostrando. En este ejercicio sirve para controlar la secuencia numérica.

### 5.3 Condición de corte
Es la expresión lógica que determina cuándo debe detenerse el ciclo. Por ejemplo:

```java
contador <= 10
```

Cuando la condición deja de ser verdadera, la ejecución del `while` termina.

### 5.4 Incremento
Es la modificación del contador para que avance en cada iteración. Sin esta actualización, el ciclo se quedaría en un bucle infinito.

Ejemplo:

```java
contador++;
```

### 5.5 Riesgo de ciclo infinito
Si la condición nunca cambia o el contador no se actualiza, el programa puede quedar ejecutándose sin fin.

Ejemplo de error:

```java
int contador = 1;
while (contador <= 10) {
    System.out.println(contador);
    // falta contador++
}
```

Esto provocaría un bucle infinito.

## 6. Lógica esperada

El programa debe iniciar con un valor inicial del contador y repetir la impresión mientras el valor cumpla la condición. Un ejemplo típico es:

- contador inicia en 1
- mientras `contador <= 10`
- imprimir `contador`
- aumentar `contador` en 1

## 7. Resultado esperado

La salida esperada en consola es una secuencia numérica ascendente, por ejemplo:

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

## 8. Solución conceptual recomendada

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

## 9. Criterios de validación

El ejercicio se considera resuelto si:

- se emplea correctamente un ciclo `while`
- existe una condición de corte clara
- el contador se actualiza en cada iteración
- el programa imprime una secuencia ordenada de números
- el ciclo finaliza sin quedar atrapado en un bucle infinito

## 10. Conclusión

Este ejercicio introduce la lógica básica de los ciclos repetitivos en Java. El alumno debe comprender que el `while` repite instrucciones mientras cierta condición siga siendo verdadera y que el control de la variable contadora es esencial para garantizar que el ciclo termine correctamente.
