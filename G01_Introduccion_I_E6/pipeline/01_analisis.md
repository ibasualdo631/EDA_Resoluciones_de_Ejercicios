# Análisis del ejercicio 6: Menú de opciones con switch

## 1. Objetivo general

Desarrollar un programa en Java que muestre un menú interactivo con varias opciones al usuario y, según la opción seleccionada, ejecute una acción distinta. La lógica principal debe implementarse mediante la estructura de control `switch`.

El ejercicio busca practicar la lectura de datos desde consola, la validación de entradas y el uso de `case`, `break` y `default` en la toma de decisiones.

## 2. Contexto del problema

La consigna indica que el programa debe:

- Mostrar un menú con cuatro opciones disponibles.
- Permitir que el usuario ingrese una opción por teclado.
- Evaluar dicha opción con una estructura `switch`.
- Mostrar un mensaje distinto según la opción elegida.
- Considerar una opción inválida dentro del menú.

La temática del ejercicio es didáctica: un menú de opciones simple, similar a un menú de consola de un sistema pequeño.

## 3. Requisitos funcionales

1. El programa debe mostrar un menú visible al usuario con 4 opciones válidas.
2. El usuario debe ingresar un número que represente la opción elegida.
3. El programa debe evaluar el valor ingresado con `switch`.
4. Cada `case` debe mostrar un mensaje específico asociado a la opción.
5. Cada `case` debe terminar con `break` para evitar que el flujo continúe en los siguientes casos.
6. Debe existir un `default` para manejar entradas no contempladas en el menú.
7. La salida debe ser clara y legible en consola.

## 4. Reglas de negocio / comportamiento esperado

- El menú debe incluir exactamente cuatro opciones válidas: números del 1 al 4.
- Si el usuario ingresa una opción comprendida entre 1 y 4, el programa muestra el mensaje correspondiente.
- Si ingresa un valor fuera del rango, como 5 o un dato no numérico, el programa debe responder con una opción inválida.
- No se especifica una lógica compleja ni persistencia; el flujo es enteramente interactivo y temporal durante la ejecución del programa.
- El programa no debe “caer” por errores de entrada; debe manejar entradas inexistentes con un mensaje seguro y controlado.

## 5. Casos de uso esperados

### Caso 1: opción válida
- Entrada: `1`
- Resultado esperado: se muestra el mensaje asociado a la opción 1.

### Caso 2: opción válida
- Entrada: `2`
- Resultado esperado: se muestra el mensaje asociado a la opción 2.

### Caso 3: opción válida
- Entrada: `3`
- Resultado esperado: se muestra el mensaje asociado a la opción 3.

### Caso 4: opción válida
- Entrada: `4`
- Resultado esperado: se muestra el mensaje asociado a la opción 4.

### Caso 5: opción inválida
- Entrada: `5`, `0`, `-1` o cualquier valor no contemplado.
- Resultado esperado: se muestra un mensaje indicando que la opción es inválida.

## 6. Restricciones técnicas

- El lenguaje de implementación es Java.
- El enfoque de resolución debe ser de consola y básico.
- La estructura principal debe ser `switch`.
- Se espera usar el flujo normal de entrada por teclado, por ejemplo `Scanner`.
- El programa debe estar pensado para una ejecución simple, sin interfaz gráfica ni archivos.

## 7. Conceptos clave a aplicar

- `switch`: estructura de control utilizada para comparar una variable con varios valores posibles.
- `case`: cada valor posible que puede tomar la variable.
- `break`: sale del bloque del `switch` para evitar que se ejecuten más casos.
- `default`: bloque ejecutado cuando no coincide ninguna opción válida.
- Menú de opciones: conjunto de acciones presentadas al usuario para que elija una.
- Estructura de control: mecanismo para tomar decisiones dentro del programa.

## 8. Resultado esperado

El usuario selecciona una opción entre 1 y 4. El programa responde de acuerdo con la opción elegida y, si la opción es inválida, muestra un mensaje indicando que la selección no es válida.

Esto permite comprender cómo se usa `switch` para manejar decisiones simples y múltiples en Java de manera ordenada y clara.

## 9. Conclusión analítica

Este ejercicio es una introducción práctica al uso de estructuras condicionales múltiples. Su importancia radica en que enseña una forma más legible y organizada de manejar varios caminos de ejecución en comparación con varios `if` anidados. Además, refuerza la idea de que cada opción del menú debe tener un comportamiento claro y una salida definida para entradas no contempladas.
