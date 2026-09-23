# Explicación didáctica del programa Hola Mundo

## 1. Qué hace el programa

El archivo [G01_Introduccion_I_E1/holaMundo.java](../G01_Introduccion_I_E1/holaMundo.java) es un ejemplo mínimo de una aplicación Java ejecutable. Su única función es mostrar un mensaje en la consola:

```text
Hola Mundo
```

Aunque parezca muy simple, este programa sirve para comprender la estructura básica de cualquier aplicación Java: una clase, el método `main` y una instrucción de salida estándar.

## 2. Estructura general del código

```java
package G01_Introduccion_I_E1;

public class holaMundo {
    public static void main(String[] args) {
        System.out.println("Hola Mundo");
    }
}
```

La estructura se divide en tres partes fundamentales:

1. `package G01_Introduccion_I_E1;`  
   Declara el paquete al que pertenece la clase. Esto ayuda a organizar el código y mantener una estructura lógica dentro del proyecto.

2. `public class holaMundo`  
   Define una clase pública llamada `holaMundo`. La clase es el contenedor principal del programa.

3. `public static void main(String[] args)`  
   Es el punto de entrada del programa. La JVM lo ejecuta automáticamente cuando se inicia la aplicación.

## 3. Cómo funciona cada bloque

### 3.1 `package G01_Introduccion_I_E1;`

El paquete agrupa clases relacionadas. En este caso, el programa está ubicado dentro del paquete `G01_Introduccion_I_E1` y por eso la clase pertenece a ese grupo.

Es importante porque si se compila desde la raíz correcta del proyecto, Java sabe dónde ubicar la clase y cómo resolverla.

### 3.2 `public class holaMundo`

La palabra reservada `class` indica que estamos creando una clase.

Una clase en Java es una plantilla que agrupa:
- atributos (datos),
- métodos (acciones),
- comportamiento del objeto o del programa.

En este caso la clase no tiene atributos; solo tiene un método principal.

La palabra `public` indica que la clase puede ser usada desde fuera del archivo fuente. Eso es necesario para que la JVM pueda encontrarla y ejecutarla.

### 3.3 `public static void main(String[] args)`

Este es el bloque más importante del programa.

#### `public`
Hace que el método sea accesible desde fuera de la clase, específicamente para que la JVM pueda invocarlo.

#### `static`
Indica que el método pertenece a la clase y no necesita que se cree un objeto de esa clase para ejecutarse. La JVM puede llamarlo directamente.

#### `void`
Significa que el método no devuelve ningún valor. Es decir, no produce un resultado que deba ser guardado o reutilizado por otra parte del programa.

#### `main`
Es el nombre estándar que Java reconoce como punto de entrada de una aplicación. Si la JVM no encuentra `main`, no puede arrancar el programa.

#### `String[] args`
Es un arreglo de cadenas que recibe argumentos de la línea de comandos. En este ejercicio no se usa, pero la firma es la estándar de Java.

### 3.4 `System.out.println("Hola Mundo");`

Esta instrucción es la que produce la salida visible en la consola.

#### `System`
Es una clase del lenguaje que representa el entorno del sistema.

#### `out`
Es la salida estándar, normalmente la consola del sistema.

#### `println()`
Es un método que imprime un texto y luego agrega un salto de línea.

Por eso después de mostrar `Hola Mundo`, la consola pasa a la línea siguiente.

El texto entre comillas es un literal de cadena: `"Hola Mundo"`.

## 4. Qué conceptos de Java aparecen

### 4.1 `class`
Es la palabra clave para definir una clase. Las clases son la base de la programación orientada a objetos en Java.

### 4.2 `public`
Es un modificador de acceso. Permite que el elemento sea visible desde otros contextos.

### 4.3 `static`
Indica que un miembro pertenece a la clase en lugar de a una instancia particular. En este caso, permite ejecutar `main` sin crear un objeto de la clase.

### 4.4 `void`
Indica que el método no devuelve ningún valor.

### 4.5 `main`
Es el método principal que la JVM ejecuta para iniciar la aplicación.

### 4.6 `System.out.println()`
Muestra texto en la salida estándar. Es la forma más básica de imprimir información en consola.

## 5. Por qué se implementó así

Se eligió la forma mínima y estándar posible por varias razones:

- Java exige una clase pública con un método `main` para ejecutar un programa.
- El método `main` debe ser `static` para que la JVM lo pueda invocar sin crear un objeto.
- `void` evita devolver un valor innecesario porque el programa no necesita uno.
- `println()` es la instrucción más simple para visualizar texto en consola.

La solución es pequeña, clara y pedagógica, ideal para introducir a un estudiante en la sintaxis de Java.

## 6. Flujo de ejecución paso a paso

El programa se ejecuta de la siguiente manera:

1. La JVM inicia la ejecución.
2. Busca la clase principal `holaMundo`.
3. Localiza el método `main`.
4. Ejecuta la instrucción dentro de `main`.
5. `System.out.println("Hola Mundo")` imprime el mensaje.
6. El programa termina.

## 7. Complejidad asintótica

Este programa no usa bucles, colecciones, recursión ni estructuras dinámicas. Por eso su costo es constante.

### 7.1 Tiempo de ejecución

- Mejor caso: `O(1)`
- Caso promedio: `O(1)`
- Caso peor: `O(1)`

La razón es simple: el programa ejecuta una cantidad fija de instrucciones, sin depender del tamaño de entrada.

### 7.2 Espacio auxiliares

- `O(1)`

No se crean arreglos, listas ni otros objetos adicionales. Solo se usa una referencia a la salida estándar y un literal de texto.

## 8. Errores comunes que se evitan

- Olvidar la firma correcta de `main`.
- Colocar la clase sin `public` o con un nombre que no coincida con el archivo.
- Escribir `main` sin `static`.
- Usar otra instrucción que no sea `System.out.println()`.
- Omitir las llaves `{}` o los paréntesis `()`.
- Escribir un mensaje distinto al solicitado.

## 9. Mejora posible

Aunque el programa cumple con la consigna, si se quisiera seguir la convención estándar de Java, el nombre de la clase debería escribirse en PascalCase:

```java
public class HolaMundo {
    public static void main(String[] args) {
        System.out.println("Hola Mundo");
    }
}
```

Eso sería más habitual en Java, pero el archivo actual se conserva con `holaMundo` para respetar la estructura ya existente del proyecto.

## 10. Conclusión

Este es el programa más básico de Java: una clase pública, un método `main` e impresión por consola. Su valor didáctico es enorme porque permite entender la sintaxis mínima y el comportamiento de la JVM al iniciar una aplicación Java.

Aunque no tiene lógica compleja, contiene los fundamentos que luego se reutilizan en programas más grandes: clases, métodos, modularización, entrada y salida y la ejecución del código.
