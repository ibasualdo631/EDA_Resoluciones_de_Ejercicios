# Plan técnico: ejercicio 5 - Condicionales con if, else if y else

## 1. Objetivo del diseño
Diseñar una solución en Java simple, legible y didáctica para resolver el problema de determinar el estado del alumno según una nota numérica. El enfoque debe priorizar claridad conceptual sobre optimización, porque el ejercicio está orientado a aprender estructuras condicionales y lógica de decisión.

## 2. Estructura propuesta del programa
Se recomienda encapsular la lógica en una clase llamada `Main` o `EstadoAlumno`, con una estructura básica como la siguiente:

```java
public class Main {
    public static void main(String[] args) {
        // flujo principal del programa
    }
}
```

### 2.1. Responsabilidad de la clase
La clase principal debe:
- solicitar la nota al usuario,
- leer el valor ingresado,
- delegar la validación a un método,
- delegar la clasificación de la nota a otro método,
- mostrar el resultado en consola.

## 3. Esqueleto de la clase

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese la nota del alumno: ");
        double nota = entrada.nextDouble();

        if (esNotaValida(nota)) {
            String estado = determinarEstado(nota);
            System.out.println("El alumno está " + estado + ".");
        } else {
            System.out.println("La nota ingresada es inválida.");
        }

        entrada.close();
    }

    public static boolean esNotaValida(double nota) {
        return nota >= 0 && nota <= 10;
    }

    public static String determinarEstado(double nota) {
        if (nota < 4) {
            return "desaprobado";
        } else if (nota >= 4 && nota <= 6) {
            return "aprobado";
        } else {
            return "promocionado";
        }
    }
}
```

## 4. Firmas de métodos sugeridas
Se proponen dos métodos estáticos para mantener la lógica ordenada y reutilizable:

### 4.1. `esNotaValida(double nota) : boolean`
- Descripción: valida si la nota se encuentra dentro del rango permitido.
- Parámetros:
  - `double nota`: valor ingresado por el usuario.
- Retorno:
  - `true` si la nota es válida.
  - `false` si está fuera del rango.
- Objetivo: separar la validación de la lógica de decisión.

### 4.2. `determinarEstado(double nota) : String`
- Descripción: determina el estado del alumno según la nota.
- Parámetros:
  - `double nota`: nota a evaluar.
- Retorno:
  - `"desaprobado"`
  - `"aprobado"`
  - `"promocionado"`
- Objetivo: encapsular la lógica de condiciones en un único punto.

## 5. Alternativa con validación más explícita
Si se desea un diseño aún más didáctico, se puede agregar un tercer método:

```java
public static String clasificarNota(double nota) {
    if (!esNotaValida(nota)) {
        return "Nota inválida";
    }

    if (nota < 4) {
        return "Desaprobado";
    } else if (nota <= 6) {
        return "Aprobado";
    } else {
        return "Promocionado";
    }
}
```

Esto permite que `main` llame directamente a `clasificarNota`, sin mezclar la validación con la impresión del resultado.

## 6. Plan de ejecución del algoritmo
1. Inicializar escáner de entrada.
2. Pedir la nota al usuario.
3. Validar la nota.
4. Si la nota es inválida, mostrar mensaje de error.
5. Si es válida, evaluar:
   - menor a 4 → desaprobado,
   - entre 4 y 6 → aprobado,
   - mayor a 6 → promocionado.
6. Mostrar el resultado final.
7. Cerrar el scanner.

## 7. Complejidad asintótica
Como el programa usa una única validación y hasta tres comparaciones secuenciales, la complejidad es constante.

### 7.1. Tiempo
- `esNotaValida(double nota)`: O(1)
- `determinarEstado(double nota)`: O(1)
- `main`: O(1) considerando una sola lectura y una sola clasificación.

Complejidad total: O(1)

### 7.2. Espacio
No se utilizan estructuras dinámicas, ni listas ni arreglos. La memoria utilizada es constante.

Complejidad espacial: O(1)

## 8. Observaciones de diseño
- La solución es lineal y simple, adecuada para la etapa inicial de aprendizaje.
- La lógica de decisión está separada en métodos, lo que facilita la lectura y mantenimiento.
- El uso de `else if` permite resolver el problema sin condiciones redundantes.
- La validación por rango previene que valores fuera de 0 a 10 se clasifiquen erróneamente.

## 9. Conclusión
El proyecto se resuelve con una clase principal y dos métodos encapsulados: uno para validar la nota y otro para determinar el estado del alumno. El algoritmo presenta complejidad temporal y espacial constante, lo que es apropiado porque el problema se basa en decisiones simples sobre un único valor numérico.
