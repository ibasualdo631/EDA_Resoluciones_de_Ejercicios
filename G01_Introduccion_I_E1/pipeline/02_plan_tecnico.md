# Plan técnico: Hola Mundo en Java

## 1. Propósito

Definir la estructura mínima de una clase Java ejecutable que cumpla con la consigna del ejercicio: compilar, iniciar con la JVM y mostrar en consola el texto `Hola Mundo`.

La solución se implementará en el archivo [G01_Introduccion_I_E1/holaMundo.java](../G01_Introduccion_I_E1/holaMundo.java), manteniendo el paquete ya declarado por el proyecto y usando la firma estándar de entrada de Java.

## 2. Decisiones arquitectónicas

- **Estilo de programación:** estructurado y mínimo.
- **Unidad de compilación:** un único archivo fuente.
- **Paquete:** `G01_Introduccion_I_E1`.
- **Clase principal:** `holaMundo` (se conserva el nombre actual para no romper la compatibilidad con el archivo existente).
- **Punto de entrada:** método `main` con la firma estándar de Java.
- **Sin datos de entrada:** no se necesitan parámetros, estructuras dinámicas ni cálculos.
- **Sin estado persistente:** la clase no requiere atributos de instancia ni variables globales.
- **Salida:** una sola instrucción de impresión mediante `System.out.println()`.

## 3. Estructura de la clase

```java
package G01_Introduccion_I_E1;

public class holaMundo {
    public static void main(String[] args) {
        System.out.println("Hola Mundo");
    }
}
```

### Justificación

- La palabra clave `public` permite que la clase sea accesible desde la JVM.
- La palabra clave `class` define el bloque de código de la aplicación.
- El método `main` es el punto de entrada obligatorio para cualquier programa Java.
- `static` permite invocar el método sin crear un objeto de la clase.
- `void` indica que `main` no devuelve ningún valor.
- `System.out.println()` escribe el mensaje en la salida estándar, con salto de línea al final.

## 4. Esqueleto propuesto del ejercicio

### 4.1 Clase principal

```java
public class holaMundo
```

Responsabilidad:
- agrupar la lógica del programa;
- contener el punto de entrada del programa.

### 4.2 Método principal

```java
public static void main(String[] args)
```

Responsabilidad:
- ejecutar la aplicación cuando se inicia desde consola;
- contener la llamada a la salida estándar;
- finalizar el programa normalmente.

### 4.3 Método opcional de encapsulación

Aunque el ejercicio puede resolverse con un único `main`, si se desea una organización un poco más clara, se puede añadir este método auxiliar:

```java
private static void mostrarMensaje()
```

Responsabilidad:
- centralizar la impresión del texto `Hola Mundo`;
- mantener `main` más legible.

Implementación sugerida:

```java
public static void main(String[] args) {
    mostrarMensaje();
}

private static void mostrarMensaje() {
    System.out.println("Hola Mundo");
}
```

Este patrón no cambia el comportamiento ni la complejidad del ejercicio, pero ayuda a documentar la separación entre ejecución y acción observable.

## 5. Firmas de métodos

Se recomienda usar estas firmas:

```java
public static void main(String[] args)
private static void mostrarMensaje()
```

Si se opta por la versión mínima, la firma necesaria es solo:

```java
public static void main(String[] args)
```

## 6. Plan de complejidad asintótica

Como el programa no recibe entrada, no itera sobre colecciones ni realiza llamadas recursivas, la complejidad es constante.

### 6.1 Tiempo de ejecución

- Caso mejor: `O(1)`
- Caso promedio: `O(1)`
- Caso peor: `O(1)`

Razón: se ejecuta un número fijo de operaciones: iniciar la aplicación, llamar a `println` y finalizar.

### 6.2 Espacio auxiliar

- `O(1)`

Razón: no se crean arreglos, listas, pilas ni estructuras adicionales. Solo se usan variables locales de tipo escalar y la llamada a la instrucción de salida.

## 7. Flujo de ejecución

```text
Inicio del programa
  |
  v
JVM invoca main
  |
  v
System.out.println("Hola Mundo")
  |
  v
Fin de la ejecución
```

## 8. Criterios de validación técnica

- La clase debe ser pública y coincidir con el nombre del archivo.
- Debe existir el método `main` con la firma estándar.
- La instrucción de salida debe imprimir exactamente `Hola Mundo`.
- El paquete debe coincidir con la ruta de compilación.
- El programa debe terminar sin solicitar datos ni usar dependencias externas.

## 9. Conclusión

La solución óptima para este ejercicio es una clase mínima, con `main` como punto de entrada y una sola línea de salida. La complejidad asintótica es constante: `O(1)` en tiempo y `O(1)` en espacio, porque no existe ninguna estructura ni algoritmo donde el costo dependa del tamaño de entrada.

2. Imprimir `Programa finalizado. ¡Hasta luego!`.
3. Permitir que la condición del `do-while` finalice el ciclo.

### Opción inválida

1. No solicitar operandos.
2. Mostrar un mensaje indicando que la opción no es válida.
3. Regresar al menú en la siguiente iteración.

## 8. Manejo de entrada inválida

### Opción no entera

`leerOpcion` utilizará `hasNextInt()` antes de `nextInt()`. Cuando el token no sea entero, se consumirá con `next()` y se mostrará un error. Esto evita que el mismo token inválido quede pendiente y provoque un ciclo infinito.

### Operando no decimal

`leerOperando` utilizará `hasNextDouble()` antes de `nextDouble()`. Cuando el token no sea convertible a `double`, se consumirá con `next()` y se repetirá la solicitud del mismo operando.

### Entrada agotada

Como la aplicación está diseñada para interacción manual, no se requiere un flujo especial para fin de archivo. Sin embargo, los métodos de lectura pueden verificar `hasNext()` antes de consumir tokens para evitar excepciones no controladas si la entrada estándar se cierra inesperadamente. La implementación deberá decidir si informa el fin de entrada y termina de forma controlada o mantiene solo el escenario de teclado manual.

## 9. Contrato de datos

| Dato | Tipo | Alcance | Descripción |
|---|---|---|---|
| `scanner` | `Scanner` | Local a `main`, compartido por parámetros | Lee la entrada estándar |
| `opcion` | `int` | Local a `main` | Controla el `switch` y la condición de salida |
| `primerOperando` | `double` | Local al caso de operación | Primer valor ingresado |
| `segundoOperando` | `double` | Local al caso de operación | Segundo valor ingresado |
| resultado | `double` | Local a cada caso | Valor retornado por la operación |

No habrá colecciones, archivos, objetos de dominio ni estructuras de datos adicionales porque el alcance solo requiere procesar una operación por vez.

## 10. Pseudoflujo del método `main`

```text
crear scanner
opcion = 0

hacer
    mostrarMenu()
    opcion = leerOpcion(scanner)

    según opcion:
        caso 1:
            leer primerOperando
            leer segundoOperando
            resultado = sumar(primerOperando, segundoOperando)
            mostrar resultado
        caso 2:
            leer primerOperando
            leer segundoOperando
            resultado = restar(primerOperando, segundoOperando)
            mostrar resultado
        caso 3:
            leer primerOperando
            leer segundoOperando
            resultado = multiplicar(primerOperando, segundoOperando)
            mostrar resultado
        caso 4:
            leer primerOperando
            leer segundoOperando
            si segundoOperando == 0:
                mostrar error de división por cero
            si no:
                resultado = dividir(primerOperando, segundoOperando)
                mostrar resultado
        caso 5:
            mostrar mensaje de finalización
        por defecto:
            mostrar error de opción inválida
mientras opcion sea diferente de 5

cerrar scanner
```

## 11. Complejidad

- Cada operación matemática tiene complejidad temporal `O(1)` y espacial `O(1)`.
- Mostrar el menú tiene complejidad `O(1)`.
- La validación de una entrada depende de la cantidad de reintentos del usuario; cada intento individual es `O(1)`.
- La aplicación no almacena historial, por lo que utiliza espacio adicional constante `O(1)`.

## 12. Trazabilidad con los requisitos

| Requisito del análisis | Decisión técnica |
|---|---|
| RF-01 | `mostrarMenu()` con opciones `1` a `5` |
| RF-02 | `leerOpcion(Scanner)` retorna `int` |
| RF-03 | `leerOperando(Scanner, String)` retorna `double` |
| RF-04 | `sumar(double, double)` |
| RF-05 | `restar(double, double)` |
| RF-06 | `multiplicar(double, double)` |
| RF-07 | `dividir(double, double)` con divisor validado |
| RF-08 | Impresión `Resultado: <valor>` en cada caso válido |
| RF-09 | Bucle `do-while` |
| RF-10 | Caso `5` y condición `opcion != 5` |
| RF-11 | Rama `default` del `switch` |
| RF-12 | `hasNextDouble()`, consumo del token inválido y reintento |
| RF-13 | Comparación del divisor con cero antes de dividir |
| RNF-01 | Java 17+, clase única y biblioteca estándar |
| RNF-02 | Mensajes claros en `mostrarMenu`, `leerOpcion` y `leerOperando` |
| RNF-03 | Validaciones antes de `nextInt`, `nextDouble` y división |
| RNF-04 | Responsabilidades separadas en métodos estáticos |
| RNF-05 | Sin paquete, sin dependencias y sin estructuras innecesarias |

## 13. Orden recomendado de implementación

1. Crear `Calculadora.java` con la clase `Calculadora` y el método `main`.
2. Agregar `mostrarMenu()`.
3. Agregar `sumar`, `restar`, `multiplicar` y `dividir`.
4. Agregar `leerOpcion()` y `leerOperando()` con validación y consumo de tokens inválidos.
5. Integrar el `do-while` y el `switch` en `main`.
6. Incorporar la validación de división por cero.
7. Compilar con Java 17 o superior.
8. Ejecutar los ejemplos de prueba del análisis y documentar los resultados en Markdown.

## 14. Resultado esperado del diseño

La arquitectura resultante será pequeña, comprobable y suficiente para el alcance definido: `main` coordina el flujo, los métodos de lectura controlan la entrada y cada método matemático realiza una única operación. No se agregan capas, clases o estructuras que no aporten valor al requisito actual.
