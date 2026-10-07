# Explicación didáctica del ejercicio: calculadora básica en Java

## 1. Qué hace el programa

Este programa crea una calculadora simple en consola. El usuario ingresa dos números y el programa muestra en pantalla el resultado de:

- suma,
- resta,
- multiplicación,
- división,
- módulo.

También valida si el segundo número es cero, porque en Java la división y el módulo por cero no son operaciones válidas y provocarían un error en tiempo de ejecución.

## 2. Cómo está organizado el código

La clase principal se llama `CalculadoraBasica` y tiene este esquema:

```java
import java.util.Scanner;

public class CalculadoraBasica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el primer número: ");
        double primerNumero = leerNumero(scanner);

        System.out.print("Ingrese el segundo número: ");
        double segundoNumero = leerNumero(scanner);

        System.out.println("Suma: " + sumar(primerNumero, segundoNumero));
        System.out.println("Resta: " + restar(primerNumero, segundoNumero));
        System.out.println("Multiplicación: " + multiplicar(primerNumero, segundoNumero));

        if (segundoNumero == 0) {
            System.out.println("No se puede dividir por cero.");
            System.out.println("Módulo: no se puede calcular porque el divisor es cero.");
        } else {
            System.out.println("División: " + dividir(primerNumero, segundoNumero));
            System.out.println("Módulo: " + modulo(primerNumero, segundoNumero));
        }

        scanner.close();
    }

    private static double leerNumero(Scanner scanner) {
        return scanner.nextDouble();
    }

    private static double sumar(double a, double b) {
        return a + b;
    }

    private static double restar(double a, double b) {
        return a - b;
    }

    private static double multiplicar(double a, double b) {
        return a * b;
    }

    private static double dividir(double a, double b) {
        return a / b;
    }

    private static double modulo(double a, double b) {
        return a % b;
    }
}
```

El programa combina dos ideas importantes:

1. lectura de datos desde consola con `Scanner`;
2. delegación de cada operación matemática en métodos separados.

## 3. Qué significa cada bloque clave

### 3.1 `import java.util.Scanner;`

Esta línea importa la clase `Scanner`, que permite leer entradas del usuario desde teclado.

`Scanner` es una herramienta muy común en Java para leer texto, números y otras entradas del usuario.

### 3.2 `public class CalculadoraBasica`

La palabra `class` define una clase. En Java, todo programa ejecutable debe estar dentro de una clase.

`public` significa que la clase puede ser accedida desde fuera del archivo, y es necesaria para que la JVM pueda encontrarla.

### 3.3 `public static void main(String[] args)`

Este es el punto de entrada del programa.

- `public`: permite que la JVM pueda ejecutar el método.
- `static`: indica que el método pertenece a la clase y no necesita crear un objeto para llamarlo.
- `void`: significa que el método no devuelve ningún valor.
- `String[] args`: recibe parámetros de ejecución, aunque en este ejercicio no se usan.

Cuando se ejecuta `java CalculadoraBasica`, Java busca este método y lo ejecuta automáticamente.

### 3.4 `Scanner scanner = new Scanner(System.in);`

Aquí se crea un objeto de tipo `Scanner` conectado a la entrada estándar del sistema (`System.in`), que normalmente es el teclado.

Esto permite leer valores como si fueran datos introducidos por el usuario en consola.

### 3.5 `leerNumero(Scanner scanner)`

Este método encapsula la lectura del número:

```java
private static double leerNumero(Scanner scanner) {
    return scanner.nextDouble();
}
```

`nextDouble()` espera que el usuario ingrese un número decimal o entero válido. Si agrega un valor no numérico, produciría un error de tipo.

### 3.6 Operadores aritméticos

Los operadores usados son los básicos de Java:

- `+` para suma
- `-` para resta
- `*` para multiplicación
- `/` para división
- `%` para módulo (resto de la división)

Por ejemplo:

```java
return a + b;
return a - b;
return a * b;
return a / b;
return a % b;
```

### 3.7 Validación de división por cero

La línea clave es:

```java
if (segundoNumero == 0) {
    System.out.println("No se puede dividir por cero.");
    System.out.println("Módulo: no se puede calcular porque el divisor es cero.");
} else {
    System.out.println("División: " + dividir(primerNumero, segundoNumero));
    System.out.println("Módulo: " + modulo(primerNumero, segundoNumero));
}
```

Esto evita que Java intente ejecutar una división por cero, que es una operación inválida en matemáticas y en programación. También evita un error de ejecución del programa.

## 4. Cómo funciona el flujo de ejecución

El programa sigue este flujo paso a paso:

1. Se crea un `Scanner` para leer la entrada del usuario.
2. Se solicita el primer número.
3. Se lee el primer número con `nextDouble()`.
4. Se solicita el segundo número.
5. Se lee el segundo número.
6. Se calculan y muestran:
   - suma,
   - resta,
   - multiplicación.
7. Se verifica si el segundo número es cero.
8. Si no es cero, se calcula y muestra:
   - división,
   - módulo.
9. Si es cero, se muestra un mensaje de advertencia.
10. El programa termina.

## 5. Qué conceptos de Java aparecen

### 5.1 Variables

Se usan variables como:

```java
double primerNumero;
double segundoNumero;
```

Estas guardan los valores introducidos por el usuario para luego usarlos en los cálculos.

### 5.2 Métodos

Cada operación está encapsulada en un método independiente:

```java
private static double sumar(double a, double b)
private static double restar(double a, double b)
private static double multiplicar(double a, double b)
private static double dividir(double a, double b)
private static double modulo(double a, double b)
```

Esto hace que el código sea más legible y ordenado.

### 5.3 Estructuras de control

Se usa `if` para decidir si es válida la división:

```java
if (segundoNumero == 0) {
    ...
} else {
    ...
}
```

`if` permite ejecutar cierta acción si se cumple una condición y otra si no se cumple.

### 5.4 Concatenación de cadenas

Se usa `+` para unir texto y valores:

```java
System.out.println("Suma: " + sumar(primerNumero, segundoNumero));
```

Esto permite mostrar un mensaje acompañado del resultado calculado.

## 6. Errores comunes que se evitan

- dividir por cero;
- usar el operador incorrecto;
- confundir `%` con `/`;
- no cerrar el `Scanner`;
- no validar la entrada antes de usarla;
- escribir mensajes poco claros para el usuario.

## 7. Por qué este diseño es útil para aprender

Este código es una excelente introducción porque enseña varias ideas clave de Java al mismo tiempo:

- lectura de entrada desde consola,
- uso de variables,
- declaración de métodos,
- operadores aritméticos,
- control de flujo con `if`,
- validación de casos límite,
- salida en consola.

Además, al separar cada operación en su propio método, se comprenden mejor los principios de modularidad y reutilización.

## 8. Mejora posible

Una mejora razonable sería validar la entrada del usuario para evitar que ingrese texto no numérico. Por ejemplo, podrías usar `hasNextDouble()` antes de leer con `nextDouble()`. Esto evita errores como `InputMismatchException`.

```java
if (scanner.hasNextDouble()) {
    return scanner.nextDouble();
} else {
    System.out.println("Entrada inválida. Debe ingresar un número.");
    return 0;
}
```

Eso haría la solución más robusta, aunque en este ejercicio introductorio se mantiene la versión más simple y didáctica.

## 9. Conclusión

La calculadora básica en Java es un ejemplo claro de cómo aplicar programación estructurada en un problema simple. Combina lectura de datos, operaciones matemáticas, validación condicional y salida por consola. Gracias a esto, se puede entender de forma intuitiva cómo se construye un programa funcional que responde a la interacción del usuario.
