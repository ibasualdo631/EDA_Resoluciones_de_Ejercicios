# Explicación didáctica: ejercicio 5 - Condicionales con if, else if y else

## 1. Qué resuelve este programa
Este programa pide al usuario una nota numérica y, según el valor ingresado, decide si el alumno está:
- desaprobado,
- aprobado,
- promocionado.

Además, valida que la nota esté dentro de un rango permitido antes de clasificarla. Si la nota está fuera del rango, muestra un mensaje de error.

La lógica del ejercicio está basada en decisiones encadenadas, que es una de las bases más importantes de la programación.

## 2. ¿Por qué se usa if, else if y else?
En Java, las estructuras condicionales permiten ejecutar cierto bloque de código solo si se cumple una condición.

En este ejercicio, necesitamos evaluar varios casos posibles:
- si la nota es menor que 4, el alumno desaprobó,
- si la nota está entre 4 y 6, el alumno aprobó,
- si la nota es mayor que 6, el alumno promocionó,
- si la nota no entra en el rango válido, el programa informa que es inválida.

Por eso se usa una cadena de decisiones:
- `if` para la primera condición,
- `else if` para las siguientes condiciones,
- `else` para todos los casos que no cumplieron las anteriores.

## 3. Estructura general del código
El programa está escrito en una clase llamada `Main` y contiene el método `main`, que es el punto de entrada de cualquier programa Java.

```java
public class Main {
    public static void main(String[] args) {
        // código principal
    }
}
```

El `main` es la zona donde se inicia la ejecución del programa. Aquí se crea el `Scanner` para leer la entrada del usuario, se solicita la nota y se procesa.

## 4. Paso a paso del flujo principal
### 4.1. Importación del Scanner
```java
import java.util.Scanner;
```

`Scanner` es una clase de Java que permite leer datos desde la consola. Es decir, nos sirve para recibir la nota escrita por el usuario.

### 4.2. Creación del objeto `entrada`
```java
Scanner entrada = new Scanner(System.in);
```

Aquí se crea un objeto que va a leer lo que el usuario ingrese por teclado.

### 4.3. Solicitud de la nota
```java
System.out.print("Ingrese la nota del alumno: ");
```

Muestra un mensaje en pantalla para indicar al usuario qué debe ingresar.

### 4.4. Lectura de la nota
```java
double nota = entrada.nextDouble();
```

La variable `nota` guarda el valor introducido por el usuario. En este caso se usa `double`, que permite almacenar valores con decimales.

Esto es útil porque una nota puede ser, por ejemplo, `4.5`, `6.8` o `7.0`.

## 5. Validación de la nota
La validación se hace con este método:

```java
public static boolean esNotaValida(double nota) {
    return nota >= 0 && nota <= 10;
}
```

### Qué significa esta línea
- `nota >= 0` → la nota no puede ser negativa.
- `nota <= 10` → la nota no puede superar 10.
- `&&` → operador lógico AND, que exige que ambas condiciones sean verdaderas.

Es decir, la nota solo es válida si está dentro del rango:

`0 <= nota <= 10`

### Por qué es importante validar
Si no validáramos la nota, el programa podría clasificar valores imposibles como `-1`, `12`, o `999` como si fueran notas reales.

La validación evita errores de lógica y hace el programa más robusto.

## 6. Evaluación de la condición principal
```java
if (esNotaValida(nota)) {
    String estado = determinarEstado(nota);
    System.out.println("El alumno esta " + estado + ".");
} else {
    System.out.println("La nota ingresada es invalida.");
}
```

### Qué pasa aquí
- Si la nota es válida, se llama al método `determinarEstado`.
- Si la nota no es válida, se imprime un mensaje de error.

Esto separa claramente dos situaciones:
1. nota válida → determinar estado,
2. nota inválida → mostrar error.

## 7. Determinación del estado del alumno
El siguiente método funciona como el “motor de decisión” del programa:

```java
public static String determinarEstado(double nota) {
    if (nota < 4) {
        return "desaprobado";
    } else if (nota >= 4 && nota <= 6) {
        return "aprobado";
    } else {
        return "promocionado";
    }
}
```

### Cómo funciona
#### Caso 1: `nota < 4`
```java
if (nota < 4)
```
Si la nota es menor a 4, el alumno ha desaprobado.

#### Caso 2: `nota >= 4 && nota <= 6`
```java
else if (nota >= 4 && nota <= 6)
```
Si la nota está entre 4 y 6 inclusive, el alumno aprobó.

#### Caso 3: resto
```java
else
```
Si no se cumple ninguna de las condiciones anteriores, entonces la nota es mayor a 6 y el alumno promociona.

## 8. Conceptos de Java que aparecen aquí
### 8.1. Variables
```java
double nota;
```
Una variable sirve para guardar un valor. En este caso registra la nota del alumno.

### 8.2. Operadores relacionales
Se usan para comparar valores:
- `<` menor que
- `>=` mayor o igual que
- `<=` menor o igual que

Estos operadores permiten decidir si una condición es verdadera o falsa.

### 8.3. Operadores lógicos
```java
&&
```
El `&&` significa “y”. Solo devuelve verdadero si ambas condiciones se cumplen.

Ejemplo:
```java
nota >= 4 && nota <= 6
```
Esto será verdad solo si la nota está entre 4 y 6.

### 8.4. Estructuras condicionales
- `if`
- `else if`
- `else`

Son fundamentales para hacer decisiones dentro del programa.

### 8.5. Métodos
```java
public static boolean esNotaValida(double nota)
public static String determinarEstado(double nota)
```
Los métodos permiten organizar el código, reutilizar lógica y hacerlo más legible.

## 9. ¿Qué hace cada bloque del programa?
### Bloque 1: entrada del usuario
```java
Scanner entrada = new Scanner(System.in);
System.out.print("Ingrese la nota del alumno: ");
double nota = entrada.nextDouble();
```
Recibe la nota del usuario desde la consola.

### Bloque 2: validación del rango
```java
if (esNotaValida(nota)) {
```
Verifica si la nota está dentro del rango permitido.

### Bloque 3: decisión del estado
```java
String estado = determinarEstado(nota);
System.out.println("El alumno esta " + estado + ".");
```
Si la nota es válida, se determina el estado y se muestra en pantalla.

### Bloque 4: mensaje de error
```java
else {
    System.out.println("La nota ingresada es invalida.");
}
```
Si la nota no cumple la validación, se informa que es inválida.

## 10. Ejemplos de ejecución
### Ejemplo 1: nota 3
```java
3
```
Resultado:
```text
El alumno esta desaprobado.
```

### Ejemplo 2: nota 6
```java
6
```
Resultado:
```text
El alumno esta aprobado.
```

### Ejemplo 3: nota 8
```java
8
```
Resultado:
```text
El alumno esta promocionado.
```

### Ejemplo 4: nota 11
```java
11
```
Resultado:
```text
La nota ingresada es invalida.
```

## 11. Errores comunes que se pueden evitar
### 1. No validar el rango
Si no se restringe la nota a 0-10, se pueden ingresar valores absurdos que romperían la lógica.

### 2. Usar comparaciones incorrectas
Por ejemplo:
```java
nota > 4 && nota < 6
```
Esto excluiría el valor 4 y 6. El ejercicio requiere incluir esos límites correctamente.

### 3. No usar `else if`
Si se usan varios `if` independientes, la lógica puede quedar inconsistente porque todas las condiciones podrían revisarse por separado.

### 4. Confundir `&&` con `||`
- `&&` significa “y” (ambas condiciones deben cumplirse).
- `||` significa “o” (al menos una debe cumplirse).

En este ejercicio, se necesita `&&` para definir intervalos exactos.

## 12. Mejora posible
Se puede mejorar la legibilidad del código usando un mensaje más formal, por ejemplo:
```java
System.out.println("El alumno está " + estado + ".");
```
con tilde y una redacción más cuidada. También se podría separar la lógica de impresión de la lógica de cálculo, si se quisiera profundizar en el diseño orientado a objetos.

## 13. Conclusión
Este ejercicio enseña de manera clara cómo trabajar con condiciones encadenadas en Java. La clave está en entender que cada valor debe entrar en exactamente una categoría:
- menor que 4 → desaprobado,
- entre 4 y 6 → aprobado,
- mayor que 6 → promocionado,
- fuera de rango → inválido.

Además, el problema muestra la importancia de:
- usar validaciones,
- combinar comparaciones con operadores lógicos,
- estructurar la solución con métodos claros y legibles.

En resumen, este ejercicio sienta las bases para comprender cómo se toman decisiones en programación mediante condicionales.
