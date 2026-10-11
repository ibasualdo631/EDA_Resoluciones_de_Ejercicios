# Plan técnico: Menú de opciones con switch

## 1. Objetivo

Diseñar una solución en Java para un programa interactivo con un menú de opciones, usando `switch` para decidir la acción según la opción elegida por el usuario.

La solución debe ser simple, legible y cumplir con la consigna: mostrar un menú con cuatro opciones, manejar una opción inválida y responder según la selección.

## 2. Estructura de la clase

Se propone la siguiente estructura:

```java
import java.util.Scanner;

public class MenuOpciones {
    public static void main(String[] args) {
        mostrarMenu();
        int opcion = leerOpcion();
        procesarOpcion(opcion);
    }

    public static void mostrarMenu() {
        System.out.println("=== MENÚ ===");
        System.out.println("1. Opción 1");
        System.out.println("2. Opción 2");
        System.out.println("3. Opción 3");
        System.out.println("4. Opción 4");
        System.out.print("Seleccione una opción: ");
    }

    public static int leerOpcion() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("Elegiste la opción 1");
                break;
            case 2:
                System.out.println("Elegiste la opción 2");
                break;
            case 3:
                System.out.println("Elegiste la opción 3");
                break;
            case 4:
                System.out.println("Elegiste la opción 4");
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }
}
```

## 3. Firmas de los métodos

### `public static void main(String[] args)`
- Propósito: punto de entrada del programa.
- Parámetros: argumentos del programa.
- Retorno: `void`.
- Función: coordina la ejecución del menú y el procesamiento de la opción.

### `public static void mostrarMenu()`
- Propósito: mostrar al usuario las opciones disponibles.
- Parámetros: ninguno.
- Retorno: `void`.
- Función: imprimir el menú con 4 opciones y pedir la selección.

### `public static int leerOpcion()`
- Propósito: leer la opción introducida por teclado.
- Parámetros: ninguno.
- Retorno: `int`.
- Función: capturar la entrada del usuario y devolverla como número entero.

### `public static void procesarOpcion(int opcion)`
- Propósito: evaluar la opción elegida con `switch`.
- Parámetros: `int opcion`.
- Retorno: `void`.
- Función: decidir qué mensaje mostrar según la opción seleccionada.

## 4. Plan de flujo del programa

1. Se ejecuta `main`.
2. Se llama a `mostrarMenu()` para presentar las opciones.
3. Se obtiene la opción ingresada por el usuario mediante `leerOpcion()`.
4. Se pasa la opción a `procesarOpcion(opcion)`.
5. Se usa `switch` para comparar el valor:
   - `case 1`: opción válida 1
   - `case 2`: opción válida 2
   - `case 3`: opción válida 3
   - `case 4`: opción válida 4
   - `default`: opción inválida
6. Cada `case` termina con `break` para evitar ejecutar los demás bloques.

## 5. Consideraciones de diseño

### a) Modularización
La lógica se divide en tres responsabilidades claras:
- mostrar el menú,
- leer la entrada,
- procesar la decisión.

Esto mejora la legibilidad y facilita futuras ampliaciones.

### b) Uso de `switch`
La decisión múltiple se resuelve con `switch`, que es la estructura indicada por la consigna. Es más clara que usar varios `if` anidados cuando el número de opciones es fijo.

### c) Manejo de entrada
Si se desea una solución más robusta, luego se puede validar que la entrada sea un entero antes de ejecutar `switch`. En este ejercicio, el enfoque principal es mostrar el uso básico de `switch`.

## 6. Complejidad asintótica

### `mostrarMenu()`
- Realiza un número fijo de impresiones.
- Complejidad: `O(1)`

### `leerOpcion()`
- Lee un único valor por teclado.
- Complejidad: `O(1)`

### `procesarOpcion(int opcion)`
- El `switch` compara contra un conjunto fijo de casos.
- No existe recorrido de datos ni bucles dependientes del tamaño de entrada.
- Complejidad: `O(1)`

### Ejecución completa del programa
- El número de operaciones es constante y no depende del tamaño de ningún conjunto de datos.
- Complejidad total: `O(1)`

## 7. Complejidad espacial

El programa usa variables locales y un `Scanner`; no almacena estructuras de datos dinámicas ni colecciones. La memoria requerida es constante.

Complejidad espacial: `O(1)`.

## 8. Conclusión

La solución técnica propuesta consiste en una clase `MenuOpciones` con tres métodos principales: `mostrarMenu()`, `leerOpcion()` y `procesarOpcion()`. Esta estructura mantiene el programa simple, ordenado y conforme a la consigna, utilizando `switch` como mecanismo principal para manejar las decisiones del menú.
