# Análisis del problema: Hola Mundo en Java

## 1. Descripción

El ejercicio solicita crear el primer programa Java de la clase. El programa debe ejecutarse desde consola e imprimir un mensaje simple en pantalla, por ejemplo `Hola Mundo`.

La consigna también exige explicar los conceptos básicos que aparecen en la estructura del programa: `class`, método `main`, `public`, `static`, `void` y `System.out.println()`.

## 2. Objetivo

Desarrollar un programa Java mínimo que:

- pueda compilarse y ejecutarse correctamente;
- tenga una clase principal;
- defina el método de entrada `main`;
- muestre el mensaje `Hola Mundo` en la salida estándar;
- permita relacionar cada elemento del código con su función básica.

## 3. Usuario objetivo

El programa está dirigido a estudiantes que están comenzando a estudiar Java y necesitan reconocer la estructura mínima de una aplicación ejecutable.

## 4. Alcance funcional

El sistema tendrá una única función observable:

1. Iniciar la aplicación.
2. Ejecutar el método `main`.
3. Imprimir una línea con el mensaje `Hola Mundo`.
4. Finalizar la ejecución.

No se requieren entrada de datos, menús, cálculos, repetición, almacenamiento, interfaz gráfica ni dependencias externas.

## 5. Requerimientos funcionales

- **RF-01. Clase principal:** el archivo debe contener una clase Java ejecutable.
- **RF-02. Punto de entrada:** la clase debe definir el método `main` con la firma estándar de Java.
- **RF-03. Acceso del programa:** la clase y el método deben tener visibilidad `public` para permitir el acceso desde el entorno de ejecución.
- **RF-04. Método estático:** `main` debe ser `static`, de modo que la JVM pueda invocarlo sin crear un objeto de la clase.
- **RF-05. Retorno:** `main` debe utilizar `void`, porque no devuelve un valor al finalizar.
- **RF-06. Mensaje:** el programa debe utilizar `System.out.println()` para imprimir `Hola Mundo` en la consola.
- **RF-07. Finalización:** después de imprimir el mensaje, el programa debe terminar normalmente.
- **RF-08. Explicación:** la solución debe documentar mediante comentarios o explicación asociada la función de cada concepto solicitado.

## 6. Reglas y decisiones del problema

1. El mensaje se presenta en la salida estándar, no en una ventana ni en un archivo.
2. El texto esperado es `Hola Mundo`; se debe conservar su escritura y separación.
3. No hay datos de entrada ni interacción del usuario.
4. No es necesario crear instancias de la clase.
5. La ejecución debe realizarse con las herramientas estándar de Java.
6. El nombre de la clase pública debe coincidir exactamente con el nombre del archivo.
7. Se conserva el paquete `G01_Introduccion_I_E1` que ya aparece en el archivo actual; al compilar desde la raíz adecuada, la estructura de carpetas debe corresponder con ese paquete.

## 7. Estado actual observado

El archivo `G01_Introduccion_I_E1/holaMundo.java` ya contiene:

- la declaración del paquete `G01_Introduccion_I_E1`;
- una clase pública llamada `holaMundo`;
- un cuerpo de clase vacío.

Por lo tanto, falta incorporar el método `main` y la instrucción de impresión. También debe verificarse que la convención de nombres de la clase sea aceptable para el curso; por convención Java se recomienda `HolaMundo`, pero cambiar el nombre exige que el archivo también se renombre.

## 8. Conceptos a explicar

- **`class`:** palabra reservada que permite declarar una clase, es decir, una estructura que organiza código y datos.
- **`public`:** modificador de acceso que permite que la clase o el método sea accesible desde fuera de su contexto.
- **`static`:** indica que el método pertenece a la clase y puede ejecutarse sin crear un objeto.
- **`void`:** indica que el método no devuelve ningún valor.
- **`main`:** método que la JVM busca como punto de entrada para iniciar un programa Java.
- **`System.out.println()`:** instrucción que escribe un texto en la salida estándar y agrega un salto de línea.

## 9. Entrada y salida

### Entrada

No existe entrada de datos. El programa comienza directamente al ser ejecutado.

### Salida

Debe producirse una línea en la consola:

```text
Hola Mundo
```

No se requieren mensajes adicionales.

## 10. Casos de uso

### CU-01: Ejecutar el programa

1. El usuario compila el archivo Java.
2. El usuario inicia la clase principal.
3. La JVM localiza y ejecuta `main`.
4. La consola muestra `Hola Mundo`.
5. El programa finaliza sin solicitar datos.

## 11. Casos límite y errores posibles

- El nombre de la clase pública no coincide con el nombre del archivo.
- El método `main` no tiene la firma estándar esperada por la JVM.
- Falta alguna llave o paréntesis.
- Se escribe incorrectamente `System.out.println()`.
- El paquete declarado no coincide con la ubicación usada para compilar o ejecutar.
- Java no está instalado o no está disponible en el entorno de ejecución.
- El mensaje tiene diferencias de escritura, espacios o mayúsculas respecto del resultado esperado.

No se consideran errores de entrada porque el programa no recibe datos del usuario.

## 12. Requerimientos no funcionales

- **RNF-01. Simplicidad:** la solución debe contener únicamente la estructura necesaria para el primer programa.
- **RNF-02. Portabilidad:** debe usar sintaxis y clases estándar de Java.
- **RNF-03. Legibilidad:** el código debe ser claro para una persona que recién comienza a estudiar el lenguaje.
- **RNF-04. Documentación pedagógica:** cada elemento solicitado debe quedar explicado mediante comentarios o una explicación complementaria.
- **RNF-05. Ejecución determinista:** ante una ejecución correcta, siempre debe imprimirse el mismo mensaje.

## 13. Criterios de aceptación

- El archivo compila sin errores.
- La clase principal puede ser localizada por la JVM.
- Existe un método `main` con la firma estándar.
- La salida contiene exactamente `Hola Mundo` en una línea.
- El programa no solicita datos ni muestra un menú.
- Se explican `class`, `main`, `public`, `static`, `void` y `System.out.println()`.
- El nombre de la clase pública y el nombre del archivo son compatibles.
- El paquete y la ruta de compilación son coherentes.

## 14. Preguntas y supuestos pendientes

La consigna no especifica explícitamente si se debe conservar el nombre actual `holaMundo` o aplicar la convención `HolaMundo`. Se asume provisionalmente que se conservará la estructura existente para minimizar cambios, salvo que el docente exija nombres de clase en PascalCase.

También debe confirmarse en la etapa técnica si el paquete declarado forma parte de los requisitos del curso o si el ejercicio espera una clase sin paquete. Esta decisión afecta únicamente la forma de compilar y ejecutar, no el comportamiento esperado.

## 15. Próximo paso

Definir en el plan técnico la firma exacta de la clase y de `main`, la forma de compilación considerando el paquete y la prueba mínima que confirme que la consola imprime `Hola Mundo`.
