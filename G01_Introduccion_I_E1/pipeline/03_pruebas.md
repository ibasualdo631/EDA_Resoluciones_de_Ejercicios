# Matriz de casos de prueba

## 1. Objetivo

Verificar que el programa Java contenido en [G01_Introduccion_I_E1/holaMundo.java](../G01_Introduccion_I_E1/holaMundo.java) cumple con la consigna del ejercicio: compilar correctamente, arrancar con la JVM y mostrar exactamente el mensaje `Hola Mundo` en consola.

## 2. Alcance de pruebas

Se prueba únicamente la funcionalidad observable del programa:
- inicio del programa;
- ejecución del método `main`;
- impresión en la salida estándar;
- finalización normal del proceso.

No se validan entradas del usuario, menús, cálculos ni dependencias externas, porque el programa no los requiere.

## 3. Matriz de casos de prueba

| ID | Caso de prueba | Descripción | Precondición | Entrada / acción | Resultado esperado | Estado |
|---|---|---|---|---|---|---|
| CT-01 | Compilación del archivo | Verificar que el archivo Java puede compilarse sin errores. | Java instalado y disponible en el sistema. | Ejecutar `javac G01_Introduccion_I_E1/holaMundo.java` | La compilación termina sin errores. | OK |
| CT-02 | Ejecución normal | Verificar que la aplicación puede iniciarse con la JVM. | El archivo ya ha sido compilado. | Ejecutar `java -cp . G01_Introduccion_I_E1.holaMundo` | El programa se inicia y termina normalmente. | OK |
| CT-03 | Mensaje exacto | Confirmar que la salida es idéntica al texto solicitado. | El programa se ejecuta correctamente. | Ejecutar el programa. | La consola muestra exactamente: `Hola Mundo` | OK |
| CT-04 | Punto de entrada correcto | Validar que la clase define `main` con la firma estándar. | Archivo Java válido. | Revisar la firma `public static void main(String[] args)` | Existe un método `main` con la firma correcta. | OK |
| CT-05 | Clase principal accesible | Verificar que la clase es pública y puede ser localizada por la JVM. | Clase definida en el paquete correcto. | Compilar y ejecutar. | La JVM reconoce la clase principal y la ejecuta. | OK |
| CT-06 | No requiere entrada de usuario | Comprobar que el programa no solicita datos ni espera interacción. | El programa se ejecuta desde consola. | Ejecutar sin argumentos ni teclado. | El programa imprime directamente el mensaje y finaliza. | OK |
| CT-07 | Salida estándar | Verificar que el texto se imprime en la consola y no en un archivo o ventana gráfica. | Entorno Java estándar. | Ejecutar el programa. | El texto aparece en consola (`System.out`). | OK |
| CT-08 | Finalización normal | Confirmar que el programa finaliza sin errores luego de imprimir. | Programa compilado. | Ejecutar la clase principal. | El proceso termina de forma normal y sin mensajes extra. | OK |
| CT-09 | Validación del paquete | Confirmar que la clase pertenece al paquete declarado. | Estructura de carpetas coherente con el paquete. | Compilar desde la raíz del proyecto. | La compilación reconoce el paquete y no falla por estructura. | OK |
| CT-10 | Documentación pedagógica | Verificar que el código explica los conceptos básicos solicitados. | Código fuente visible. | Revisar comentarios de la clase y del método `main`. | Se explican conceptos como `class`, `public`, `static`, `void` y `System.out.println()`. | Parcial |

## 4. Casos límite y errores probables

| ID | Error posible | Síntoma esperado | Resultado correcto |
|---|---|---|---|
| CE-01 | Clase sin `main` | La JVM no puede iniciar la aplicación. | Existe el método principal obligatorio. |
| CE-02 | Firma incorrecta de `main` | Error de ejecución o no arranca. | Se usa `public static void main(String[] args)`. |
| CE-03 | Nombre de la clase no compatible | Error de compilación si no coincide con el archivo. | La clase debe ser pública y nombrada consistentemente con el archivo. |
| CE-04 | Error de escritura en el texto | La salida no coincide con el requisito. | Debe mostrarse exactamente `Hola Mundo`. |
| CE-05 | Uso incorrecto de `println` | No aparece el mensaje o sale sin salto de línea. | Se usa `System.out.println(...)`. |
| CE-06 | Paquete inconsistente con la ruta | Error de compilación o ejecución. | El paquete y la ubicación del archivo son coherentes. |

## 5. Evidencia de ejecución

Comando ejecutado para validación:

```bash
javac G01_Introduccion_I_E1/holaMundo.java
java -cp . G01_Introduccion_I_E1.holaMundo
```

Salida observada:

```text
Hola Mundo
```

## 6. Conclusión

La solución cumple con los requisitos funcionales del ejercicio. La salida observable es correcta, el programa inicia en consola, finaliza normalmente y presenta el mensaje solicitado sin interacción del usuario.

Se recomienda, como mejora docente, mantener la explicación de los conceptos básicos más explícita en el código o en la explicación asociada del ejercicio.
