# Análisis del problema: Calculadora básica en Java

## 1. Descripción

El ejercicio propone desarrollar una calculadora simple en Java utilizando `Scanner` para leer datos ingresados por el usuario. La aplicación debe permitir practicar operaciones aritméticas básicas: suma, resta, multiplicación, división y cálculo del módulo entre dos números.

La consigna además indica que debe manejarse la validación básica de división por cero y que el programa presente resultados en consola.

## 2. Objetivo

Crear una aplicación Java que:

- lea dos números desde la entrada estándar;
- realice las operaciones aritméticas básicas solicitadas;
- muestre el resultado de cada operación;
- evite errores al intentar dividir por cero;
- sirva como ejercicio inicial para comprender el uso de operadores aritméticos y lectura de datos.

## 3. Usuario objetivo

El programa está orientado a estudiantes que están comenzando con Java y desean practicar:

- lectura de entrada con `Scanner`;
- uso de operadores aritméticos;
- control de flujo simple;
- manejo básico de errores de ejecución.

## 4. Alcance funcional

La aplicación tendrá una única funcionalidad principal:

1. solicitar dos valores numéricos al usuario;
2. ejecutar operaciones aritméticas sobre esos valores;
3. mostrar cada resultado en consola;
4. controlar la posibilidad de división por cero.

No se requiere interfaz gráfica, almacenamiento persistente, menú complejo ni validación avanzada de datos.

## 5. Requerimientos funcionales

- **RF-01. Entrada de datos:** el programa debe leer dos números mediante `Scanner`.
- **RF-02. Suma:** debe calcular la suma entre ambos números.
- **RF-03. Resta:** debe calcular la diferencia entre ambos números.
- **RF-04. Multiplicación:** debe calcular el producto entre ambos números.
- **RF-05. División:** debe calcular el cociente de la división; si el divisor es cero, debe informarlo.
- **RF-06. Módulo:** debe calcular el resto de la división entre ambos números.
- **RF-07. Salida por consola:** cada resultado debe mostrarse en pantalla.
- **RF-08. Validación de división por cero:** la división y el módulo no deben ejecutarse si el divisor es cero.
- **RF-09. Operadores aritméticos:** se debería usar la sintaxis de Java para `+`, `-`, `*`, `/` y `%`.

## 6. Reglas y decisiones del problema

1. Se espera un programa de consola, no una aplicación gráfica.
2. La entrada será numérica y se realizará desde teclado.
3. La división por cero es una condición inválida y debe manejarse explícitamente.
4. La operación módulo solo tiene sentido cuando el divisor es distinto de cero.
5. El programa debe reutilizar los mismos dos valores para todas las operaciones.
6. Se asume que los números pueden ser enteros o decimales; en la práctica, una solución robusta suele usar `double` para permitir cálculo con valores reales.
7. El objetivo del ejercicio es practicar operadores aritméticos y flujo básico, no la creación de una interfaz moderna ni un menú de selección.

## 7. Resultado esperado

El usuario ingresa dos números y el programa muestra en consola el resultado de cada operación:

```text
Ingrese el primer número: 10
Ingrese el segundo número: 3
Suma: 13
Resta: 7
Multiplicación: 30
División: 3.3333333333333335
Módulo: 1
```

En caso de divisor igual a cero, se debe mostrar un mensaje claro, por ejemplo:

```text
No se puede dividir por cero.
```

## 8. Conceptos a explicar

- **Suma:** combinación de dos valores según el operador `+`.
- **Resta:** diferencia entre dos valores usando el operador `-`.
- **Multiplicación:** producto de dos valores usando `*`.
- **División:** cociente entre valores usando `/`.
- **Módulo:** resto de la división usando `%`.
- **Operadores aritméticos:** símbolos que permiten realizar cálculos sobre números.
- **Validación de división por cero:** control lógico para evitar una operación inválida o un error del programa.
- **`Scanner`:** clase de Java utilizada para leer datos desde la entrada del usuario.

## 9. Entrada y salida

### Entrada

El programa espera recibir dos valores numéricos desde teclado, por ejemplo:

- primer número;
- segundo número.

### Salida

La salida será una serie de líneas en consola con los resultados de:

- suma;
- resta;
- multiplicación;
- división;
- módulo.

Si el divisor es cero, se mostrará un mensaje de error.

## 10. Casos de uso

### CU-01: Operaciones normales

1. El usuario ingresa dos números válidos.
2. El programa calcula las operaciones básicas.
3. La consola muestra cada resultado.
4. El programa termina normalmente.

### CU-02: División por cero

1. El usuario ingresa un divisor igual a cero.
2. El programa detecta la condición inválida.
3. Muestra un mensaje de advertencia.
4. No intenta ejecutar la operación matemática.

### CU-03: Valores negativos

1. El usuario ingresa números negativos.
2. El programa calcula correctamente usando la aritmética de Java.
3. Los resultados se muestran con la lógica habitual del lenguaje.

## 11. Casos límite y errores posibles

- El divisor es cero en la operación de división o módulo.
- El usuario ingresa un valor no numérico.
- Se olvida utilizar `Scanner` correctamente.
- Se confunde el operador de módulo `%` con la división `/`.
- Se imprime un resultado sin formato o sin explicación.
- Se intenta dividir sin validar el valor del segundo número.
- Se realiza la operación con tipos incompatibles o con conversiones incorrectas.

## 12. Requerimientos no funcionales

- **RNF-01. Simplicidad:** la solución debe ser fácil de entender para principiantes.
- **RNF-02. Legibilidad:** el código debe usar nombres claros y una estructura ordenada.
- **RNF-03. Robustez mínima:** debe evitar fallos por división por cero.
- **RNF-04. Claridad de salida:** los resultados deben ser fáciles de interpretar.
- **RNF-05. Uso de Java estándar:** la solución debe estar escrita con sintaxis válida de Java.

## 13. Criterios de aceptación

- El programa compila y ejecuta sin errores.
- Se utiliza `Scanner` para leer los datos del usuario.
- Se realizan las operaciones de suma, resta, multiplicación, división y módulo.
- La división o módulo por cero se manejan correctamente.
- La salida se presenta en consola de forma legible.
- El ejercicio demuestra comprensión de los operadores aritméticos básicos de Java.

## 14. Supuestos y observaciones

La descripción de la actividad no especifica si los números deben ser enteros o si se aceptan decimales. Dado que el ejercicio es introductorio, la resolución más didáctica suele usar valores numéricos simples y, en muchos casos, `double` para evitar limitaciones en la división.

También se asume que el programa debe mostrar todas las operaciones sobre los mismos dos números, sin necesidad de crear un menú ni seleccionar operación por opción.

## 15. Próximo paso

En la etapa técnica se definirá el diseño del programa, la estructura de la clase, la lectura con `Scanner`, la validación de entrada y la forma de imprimir los resultados para cumplir con la consigna.
