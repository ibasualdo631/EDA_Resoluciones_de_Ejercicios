# Plan técnico — Contar dígitos recursivamente en Java

## Objetivo

Diseñar una solución Java que cuente los dígitos decimales de un entero mediante recursión. Los valores negativos se cuentan por su magnitud: se normaliza el signo con `Math.abs`. El cero queda fuera del dominio establecido por el análisis y debe rechazarse explícitamente.

## Organización propuesta

El análisis no identifica clases Java, un sistema de construcción ni convenciones de paquetes existentes. Por eso, implementar la solución como una clase autocontenida, sin agregar dependencias:

- **Clase:** `ContadorDigitos`.
- **Método público:** `public static int contarDigitos(int numero)`.
- **Método auxiliar privado:** `private static int contarDigitosPositivo(long numero)`.
- **Responsabilidad del método público:** validar el cero, convertir el valor a magnitud positiva con `Math.abs` y delegar el conteo.
- **Responsabilidad del método auxiliar:** aplicar exclusivamente la regla recursiva sobre un `long` no negativo.

Si el proyecto incorpora más adelante una estructura de paquetes o una clase principal, ubicar la clase en el directorio y paquete que sigan esas convenciones, sin mezclar la lógica de conteo con la entrada/salida de consola.

## Contratos y tipos

### `contarDigitos(int numero)`

- **Entrada:** cualquier valor representable por `int`, excepto `0`.
- **Salida:** cantidad de dígitos de la representación decimal de su magnitud.
- **Validación:** lanzar `IllegalArgumentException` cuando `numero == 0`; no devolver un resultado aparentemente válido para una entrada fuera del dominio.
- **Normalización del signo:** evaluar `Math.abs((long) numero)`. La conversión a `long` debe ocurrir antes de `Math.abs`, porque `Math.abs(Integer.MIN_VALUE)` no puede representarse como `int` y permanece negativo por desbordamiento.
- **Ejemplos:** `contarDigitos(-47)` devuelve `2`; `contarDigitos(Integer.MIN_VALUE)` devuelve `10`.

### `contarDigitosPositivo(long numero)`

- **Precondición:** `numero >= 1`.
- **Caso base:** si `numero < 10`, devolver `1`.
- **Paso recursivo:** devolver `1 + contarDigitosPositivo(numero / 10)`. La división entera elimina el último dígito decimal y cada llamada se acerca al caso base.
- **Terminación:** para toda entrada positiva finita, la división sucesiva por `10` alcanza un valor menor que `10`.

## Secuencia de ejecución

1. Recibir el entero en `contarDigitos`.
2. Si es cero, rechazarlo con `IllegalArgumentException`, de acuerdo con el dominio del análisis.
3. Convertirlo a magnitud con `Math.abs((long) numero)`, de modo que también se procese correctamente `Integer.MIN_VALUE`.
4. Llamar al auxiliar recursivo con esa magnitud.
5. En el auxiliar, devolver `1` al llegar a un solo dígito; de lo contrario, contar el último dígito y continuar con el cociente entre `10`.
6. Devolver al llamador el total acumulado por las llamadas recursivas.

Ejemplo para `-583`: se normaliza a `583`; luego `583 → 58 → 5`, y el resultado es `1 + 1 + 1 = 3`.

## Complejidad

Sea **N** la cantidad de dígitos decimales de `|numero|`. Cada llamada recursiva realiza trabajo constante y reduce el argumento mediante división entera por `10`, eliminando exactamente un dígito. Se ejecutan N niveles hasta el caso base; por lo tanto, el tiempo es **O(N)**. La pila de llamadas también requiere **O(N)** espacio. No se usa conversión a cadena ni iteración.

## Instrucciones para el desarrollador

Implementá la clase `ContadorDigitos` en Java respetando el contrato y la secuencia anteriores:

1. Creá `public static int contarDigitos(int numero)` y `private static int contarDigitosPositivo(long numero)`.
2. Rechazá `0` con `IllegalArgumentException`. Para todos los valores no nulos, calculá la magnitud con `Math.abs((long) numero)`: no uses `Math.abs(numero)` directamente, para evitar el desbordamiento de `Integer.MIN_VALUE`.
3. Implementá el conteo únicamente con recursión y división entera por `10`: caso base `numero < 10`, resultado `1`; caso recursivo `1 + contarDigitosPositivo(numero / 10)`.
4. No reemplaces la recursión por ciclos, logaritmos ni conversión a texto. No agregues entrada/salida de consola a la clase de lógica.
5. Agregá o actualizá pruebas para `1 → 1`, `9 → 1`, `10 → 2`, `47 → 2`, `1000 → 4`, `-47 → 2`, `-9 → 1` e `Integer.MIN_VALUE → 10`; verificá además que `0` provoque `IllegalArgumentException`.
6. Ejecutá las pruebas pertinentes del proyecto. Si todavía no existe un framework o configuración de pruebas, documentá esa limitación y verificá la solución con pruebas manuales equivalentes, sin introducir dependencias innecesarias.

## Criterios de aceptación

- El conteo usa recursión con reducción por división entera entre `10`.
- El caso base devuelve `1` para magnitudes de un dígito.
- Los negativos se cuentan por su valor absoluto y `Integer.MIN_VALUE` no falla ni produce una magnitud negativa.
- El cero se rechaza explícitamente.
- Se documenta tiempo **O(N)**, con N definido como cantidad de dígitos, y espacio **O(N)** por la pila recursiva.
- Las pruebas cubren límites, varios dígitos, negativos y el valor entero mínimo.