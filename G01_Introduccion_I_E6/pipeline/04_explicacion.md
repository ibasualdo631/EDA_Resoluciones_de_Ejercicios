# Explicación didáctica: ejercicio 6 — menú de opciones con switch

## 1. ¿Qué hace este programa?

Este programa muestra un menú interactivo en consola y permite que el usuario elija una opción del 1 al 4. Según la opción elegida, el programa ejecuta un mensaje distinto. Si el usuario escribe una opción no válida, el programa informa que la selección es incorrecta.

La idea principal es practicar la estructura `switch`, que es una forma clara y ordenada de evaluar múltiples posibilidades.

## 2. Estructura general del programa

El archivo Java tiene esta forma básica:

```java
import java.util.Scanner;

public class MenuOpciones {
    public static void main(String[] args) {
        mostrarMenu();
        int opcion = leerOpcion();
        procesarOpcion(opcion);
    }

    public static void mostrarMenu() {
        System.out.println("=== MENÚ DE OPCIONES ===");
        System.out.println("1. Opción 1");
        System.out.println("2. Opción 2");
        System.out.println("3. Opción 3");
        System.out.println("4. Opción 4");
        System.out.print("Seleccione una opción: ");
    }

    public static int leerOpcion() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Entrada inválida. Debe ingresar un número.");
            scanner.next();
        }
    }

    public static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("Elegiste la opción 1.");
                break;
            case 2:
                System.out.println("Elegiste la opción 2.");
                break;
            case 3:
                System.out.println("Elegiste la opción 3.");
                break;
            case 4:
                System.out.println("Elegiste la opción 4.");
                break;
            default:
                System.out.println("Opción inválida. Debe elegir un número entre 1 y 4.");
                break;
        }
    }
}
```

## 3. ¿Por qué se usa `switch`?

Cuando un programa tiene muchas decisiones posibles, usar varios `if` anidados puede volverse difícil de leer. `switch` permite evaluar una variable y ejecutar un bloque según el valor exacto que tenga.

En este caso, la variable es `opcion`. El programa compara ese valor con los diferentes `case`:

```java
switch (opcion) {
    case 1:
        ...
        break;
    case 2:
        ...
        break;
    ...
    default:
        ...
}
```

Esto hace más clara la lógica: cada caso representa una opción del menú.

## 4. Explicación línea por línea de los métodos

### 4.1 `main`

```java
public static void main(String[] args) {
    mostrarMenu();
    int opcion = leerOpcion();
    procesarOpcion(opcion);
}
```

Este es el punto de entrada del programa. La ejecución empieza aquí.

- `mostrarMenu()` muestra las opciones al usuario.
- `leerOpcion()` captura la opción ingresada.
- `procesarOpcion(opcion)` decide qué hacer según ese valor.

Este flujo es muy común en programas de consola: primero se muestra información, luego se recoge entrada y después se procesa.

### 4.2 `mostrarMenu()`

```java
public static void mostrarMenu() {
    System.out.println("=== MENÚ DE OPCIONES ===");
    System.out.println("1. Opción 1");
    System.out.println("2. Opción 2");
    System.out.println("3. Opción 3");
    System.out.println("4. Opción 4");
    System.out.print("Seleccione una opción: ");
}
```

Este método se encarga de la interfaz básica del programa. Imprime en pantalla el menú con cuatro opciones y luego pregunta al usuario cuál quiere elegir.

La diferencia entre `println` y `print` es importante:
- `println` imprime el texto y salta a la línea siguiente.
- `print` imprime sin saltar de línea, por lo que la respuesta del usuario aparecerá al lado de la pregunta.

### 4.3 `leerOpcion()`

```java
public static int leerOpcion() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }

        System.out.println("Entrada inválida. Debe ingresar un número.");
        scanner.next();
    }
}
```

Este método maneja la entrada del usuario desde teclado.

#### ¿Qué hace `Scanner`?
`Scanner` es una clase de Java que permite leer datos desde la consola. En este caso, se usa para leer un número entero.

```java
Scanner scanner = new Scanner(System.in);
```

`System.in` representa la entrada estándar del sistema, es decir, el teclado.

#### ¿Qué hace `hasNextInt()`?
Este método verifica si lo que el usuario escribió es un entero válido.

- Si es un número, entonces se ejecuta:

```java
return scanner.nextInt();
```

- Si no es un número, entonces se muestra un mensaje:

```java
System.out.println("Entrada inválida. Debe ingresar un número.");
```

y luego:

```java
scanner.next();
```

Esto limpia la entrada incorrecta para que el programa pueda seguir pidiendo datos.

#### ¿Por qué `while (true)`?
Se usa un ciclo infinito controlado porque el programa debe seguir pidiendo una entrada válida hasta que el usuario escriba un número entero.

Es un patrón muy útil cuando la entrada debe validarse antes de continuar.

### 4.4 `procesarOpcion(int opcion)`

```java
public static void procesarOpcion(int opcion) {
    switch (opcion) {
        case 1:
            System.out.println("Elegiste la opción 1.");
            break;
        case 2:
            System.out.println("Elegiste la opción 2.");
            break;
        case 3:
            System.out.println("Elegiste la opción 3.");
            break;
        case 4:
            System.out.println("Elegiste la opción 4.");
            break;
        default:
            System.out.println("Opción inválida. Debe elegir un número entre 1 y 4.");
            break;
    }
}
```

Aquí ocurre la lógica principal del programa.

#### `switch (opcion)`
La variable `opcion` se compara contra varios valores posibles.

#### `case 1`, `case 2`, `case 3`, `case 4`
Cada `case` representa una opción del menú. Si el valor coincide, se ejecuta el bloque correspondiente.

Ejemplo:

```java
case 3:
    System.out.println("Elegiste la opción 3.");
    break;
```

Si la opción ingresada es `3`, entonces se imprime el mensaje `Elegiste la opción 3.`

#### `break`
La instrucción `break` se usa para salir del `switch` inmediatamente. Si no se usara, el programa seguiría ejecutando los siguientes `case` aunque ya hubiera encontrado uno válido.

Por eso es importante poner `break` al final de cada caso.

#### `default`
El bloque `default` se ejecuta cuando no coincide ningún `case`.

Por ejemplo, si el usuario ingresa `9`, `0` o `-2`, el programa ejecuta:

```java
System.out.println("Opción inválida. Debe elegir un número entre 1 y 4.");
```

## 5. Conceptos clave que aparecen en el ejercicio

### 5.1 `switch`
Es una estructura de control que permite tomar decisiones múltiples con una misma variable.

### 5.2 `case`
Cada valor posible de la variable se representa con un `case`.

### 5.3 `break`
Se usa para salir del bloque `switch` y evitar que continúen ejecutándose más casos.

### 5.4 `default`
Se usa para manejar valores que no están contemplados.

### 5.5 `Scanner`
Permite leer datos por teclado.

### 5.6 `while (true)`
Se usa para repetir una acción hasta que la entrada sea válida.

## 6. ¿Por qué este ejercicio es importante?

Este ejercicio enseña una de las bases de la programación estructurada:

- leer datos del usuario,
- evaluar distintas posibilidades,
- tomar decisiones,
- responder de forma clara.

Además, ayuda a comprender que un programa puede dividirse en pequeños métodos con responsabilidades específicas, lo cual hace que el código sea más ordenado y más fácil de mantener.

## 7. Errores comunes que se evitan en este ejercicio

### 7.1 Olvidar `break`
Si se omite `break`, el flujo puede seguir ejecutando otros `case` sin que el usuario lo espere.

### 7.2 No incluir `default`
Sin `default`, una opción inválida quedaría sin respuesta clara.

### 7.3 No validar la entrada
Si el usuario escribe un texto como `hola`, el programa podría romperse si no se valida antes de intentar convertirlo a entero.

### 7.4 Mezclar demasiada lógica en un solo método
Es mejor separar la impresión del menú, la lectura y el procesamiento. Esto mantiene el código legible.

## 8. Conclusión

El programa resuelve un problema pequeño pero muy útil: un menú interactivo con múltiples opciones. A través del uso de `switch`, `case`, `break` y `default`, se enseña cómo manejar decisiones con varias rutas de ejecución de manera ordenada.

También se practica una buena técnica de programación en Java: dividir la lógica en métodos y validar la entrada del usuario antes de continuar.

En resumen, este ejercicio sirve como introducción clara a la toma de decisiones con estructuras de control múltiples y al manejo de entrada por consola.
