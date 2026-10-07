# Plan técnico: Calculadora básica en Java

## 1. Propósito

Definir la estructura básica de una clase Java que lea dos números, ejecute operaciones aritméticas y muestre sus resultados en consola. La solución debe respetar la consigna del ejercicio y usar `Scanner` para la entrada del usuario.

El programa se implementará en un archivo Java con una sola clase principal que contiene el flujo del programa y métodos auxiliares para cada operación.

## 2. Decisiones arquitectónicas

- **Estilo de programación:** estructurado y sencillo, apto para principiantes.
- **Unidad de compilación:** un único archivo `.java`.
- **Clase principal:** `CalculadoraBasica`.
- **Punto de entrada:** método `main` con la firma estándar de Java.
- **Entrada:** `Scanner` para leer dos números desde la consola.
- **Operaciones:** `sumar`, `restar`, `multiplicar`, `dividir` y `modulo`.
- **Validación:** controlar la división por cero antes de ejecutar la operación.
- **Sin estructuras de datos complejas:** no se requieren listas, arreglos ni colecciones.
- **Salida:** resultados impresos con `System.out.println()`.

## 3. Estructura de la clase

```java
import java.util.Scanner;

public class CalculadoraBasica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el primer número: ");
        double a = leerNumero(scanner);

        System.out.print("Ingrese el segundo número: ");
        double b = leerNumero(scanner);

        System.out.println("Suma: " + sumar(a, b));
        System.out.println("Resta: " + restar(a, b));
        System.out.println("Multiplicación: " + multiplicar(a, b));

        if (b == 0) {
            System.out.println("No se puede dividir por cero.");
        } else {
            System.out.println("División: " + dividir(a, b));
            System.out.println("Módulo: " + modulo(a, b));
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

## 4. Justificación de la estructura

### 4.1 Clase principal

```java
public class CalculadoraBasica
```

Responsabilidad:
- contener la lógica del programa;
- agrupar los métodos de cálculo;
- servir como punto de entrada para la JVM.

### 4.2 Método principal

```java
public static void main(String[] args)
```

Responsabilidad:
- iniciar la aplicación;
- leer los datos del usuario;
- invocar las operaciones aritméticas;
- mostrar resultados por consola.

### 4.3 Métodos auxiliares

```java
private static double leerNumero(Scanner scanner)
private static double sumar(double a, double b)
private static double restar(double a, double b)
private static double multiplicar(double a, double b)
private static double dividir(double a, double b)
private static double modulo(double a, double b)
```

Responsabilidad:
- encapsular la lectura de entrada;
- separar cada operación aritmética en un método dedicado;
- mantener `main` limpio y legible.

## 5. Firmas sugeridas

La forma recomendada de implementar el ejercicio es la siguiente:

```java
public static void main(String[] args)
private static double leerNumero(Scanner scanner)
private static double sumar(double a, double b)
private static double restar(double a, double b)
private static double multiplicar(double a, double b)
private static double dividir(double a, double b)
private static double modulo(double a, double b)
```

Si se desea una versión aún más mínima, se podría omitir `leerNumero` y realizar la lectura directamente dentro de `main`, pero la versión modular es más clara para el aprendizaje.

## 6. Validación de la división por cero

El método de división y el módulo requieren una comprobación previa:

```java
if (b == 0) {
    System.out.println("No se puede dividir por cero.");
} else {
    System.out.println("División: " + dividir(a, b));
    System.out.println("Módulo: " + modulo(a, b));
}
```

Esto evita que Java lance una excepción aritmética y garantiza una salida controlada para el usuario.

## 7. Plan de complejidad asintótica

Como el programa trabaja con únicamente dos números y ejecuta un número fijo de operaciones, la complejidad depende de una cantidad constante de instrucciones.

### 7.1 Tiempo de ejecución

- **Caso mejor:** `O(1)`
- **Caso promedio:** `O(1)`
- **Caso peor:** `O(1)`

Razón:
- no hay ciclos dependientes del tamaño de entrada;
- la cantidad de operaciones es constante;
- cada cálculo se ejecuta una sola vez.

### 7.2 Espacio auxiliar

- **`O(1)`**

Razón:
- no se crean arreglos, listas ni estructuras dinámicas;
- solo se usan variables locales para almacenar los dos números y el resultado.

## 8. Flujo de ejecución

```text
Inicio del programa
  |
  v
leer primer número
  |
  v
leer segundo número
  |
  v
calcular suma, resta y multiplicación
  |
  v
validar divisor
  |
  +--> si b == 0: mostrar error
  |
  +--> si b != 0: calcular división y módulo
  |
  v
mostrar resultados en consola
  |
  v
fin del programa
```

## 9. Criterios de validación técnica

- La clase debe ser pública.
- Debe existir el método `main` con la firma estándar.
- Debe usarse `Scanner` para leer los números del usuario.
- Las operaciones suma, resta, multiplicación, división y módulo deben estar implementadas.
- La división por cero debe manejarse con un mensaje claro.
- La salida debe imprimirse en consola de manera legible.
- No se requieren estructuras avanzadas ni dependencias externas.

## 10. Conclusión

La solución más adecuada para este ejercicio es una clase simple con `main` y varios métodos auxiliares para cada operación. El diseño es claro, didáctico y cumple con los requisitos del problema sin introducir complejidad innecesaria.

La complejidad asintótica del algoritmo es constante: `O(1)` en tiempo y `O(1)` en espacio, porque el problema no depende del tamaño de entrada sino de una cantidad fija de operaciones aritméticas.
