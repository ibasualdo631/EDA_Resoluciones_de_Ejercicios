# Matriz de casos de prueba

## 1. Objetivo

Verificar que la calculadora básica implementada en [CalculadoraBasica.java](../CalculadoraBasica.java) cumple con la consigna del ejercicio: compilar sin errores y mostrar los resultados correctos de las operaciones aritméticas básicas.

## 2. Alcance de pruebas

Se prueba la funcionalidad observable del programa:
- lectura de dos números desde la consola;
- ejecución de suma, resta, multiplicación, división y módulo;
- gestión de la división por cero;
- finalización normal del programa.

## 3. Matriz de casos de prueba

| ID | Caso de prueba | Descripción | Precondición | Entrada / acción | Resultado esperado | Estado |
|---|---|---|---|---|---|---|
| CT-01 | Compilación del archivo | Verificar que el archivo Java compila sin errores. | Java instalado. | Ejecutar `javac CalculadoraBasica.java` | La compilación termina sin errores. | OK |
| CT-02 | Ejecución normal | Verificar que el programa se inicia correctamente. | Archivo compilado. | Ejecutar `java CalculadoraBasica` con dos valores válidos | El programa arranca y termina normalmente. | OK |
| CT-03 | Suma | Validar la operación de suma. | Entrada válida. | Ingresar `10` y `3` | Muestra `Suma: 13.0` | OK |
| CT-04 | Resta | Validar la operación de resta. | Entrada válida. | Ingresar `10` y `3` | Muestra `Resta: 7.0` | OK |
| CT-05 | Multiplicación | Validar la operación de multiplicación. | Entrada válida. | Ingresar `10` y `3` | Muestra `Multiplicación: 30.0` | OK |
| CT-06 | División | Validar la operación de división. | Divisor distinto de cero. | Ingresar `10` y `3` | Muestra `División: 3.3333333333333335` | OK |
| CT-07 | Módulo | Validar la operación de módulo. | Divisor distinto de cero. | Ingresar `10` y `3` | Muestra `Módulo: 1.0` | OK |
| CT-08 | División por cero | Verificar control del error aritmético. | Divisor igual a cero. | Ingresar `5` y `0` | Muestra `No se puede dividir por cero.` | OK |
| CT-09 | Módulo por cero | Verificar que el módulo no se calcula cuando el divisor es cero. | Divisor igual a cero. | Ingresar `5` y `0` | Muestra un mensaje de prevención para el módulo. | OK |
| CT-10 | Cierre del scanner | Validar que el programa termina correctamente. | Programa ejecutado. | Finalizar la ejecución. | No aparecen errores extra ni excepción de flujo. | OK |

## 4. Casos límite y errores probables

| ID | Error posible | Síntoma esperado | Resultado correcto |
|---|---|---|---|
| CE-01 | Divisor cero sin validación | Excepción aritmética. | Se muestra un mensaje de prevención y no se intenta la operación. |
| CE-02 | Uso incorrecto de `Scanner` | Error de lectura o `InputMismatchException`. | La entrada de números es válida y el programa la consume correctamente. |
| CE-03 | Operador incorrecto | Resultado erróneo. | Se usan `+`, `-`, `*`, `/` y `%` según lo requerido. |
| CE-04 | Salida no legible | El usuario no comprende el resultado. | Se muestran etiquetas claras como `Suma`, `Resta`, etc. |

## 5. Evidencia de ejecución

Comandos ejecutados para validación:

```bash
javac CalculadoraBasica.java
java CalculadoraBasica
```

Entrada de prueba:

```text
10
3
```

Salida observada:

```text
Ingrese el primer número: Ingrese el segundo número: Suma: 13.0
Resta: 7.0
Multiplicación: 30.0
División: 3.3333333333333335
Módulo: 1.0
```

Prueba adicional para división por cero:

```text
5
0
```

Salida observada:

```text
Ingrese el primer número: Ingrese el segundo número: Suma: 5.0
Resta: 5.0
Multiplicación: 0.0
No se puede dividir por cero.
Módulo: no se puede calcular porque el divisor es cero.
```

## 6. Conclusión

La implementación cumple con la consigna del ejercicio: lee dos números, calcula las operaciones básicas y controla correctamente la división por cero. El programa termina sin errores y presenta resultados legibles en consola.
