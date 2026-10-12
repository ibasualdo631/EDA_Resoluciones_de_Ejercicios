# Plan técnico — Multiplicación mediante sumas recursivas

## 1. Organización

Implementar la solución en Java como un método recursivo `multiplicar(int a, int b)` dentro de la clase indicada por la estructura existente del ejercicio. Mantener `a` fijo y reducir `b` hacia cero; no introducir ciclos, arreglos ni estructuras auxiliares.

La implementación de producción no debe usar el operador de multiplicación. El método devuelve un `int` y conserva el comportamiento aritmético habitual de Java cuando una suma o negación desborda ese tipo.

## 2. Contrato y recurrencia

- **Entrada:** dos valores `int`, `a` y `b`.
- **Salida:** el producto representado como `int`, calculado con sumas recursivas.
- **Caso base:** si `b == 0`, devolver `0`.
- **Paso recursivo positivo:** si `b > 0`, devolver `a + multiplicar(a, b - 1)`.
- **Paso recursivo negativo:** si `b < 0`, devolver `-a + multiplicar(a, b + 1)`.

Cada llamada modifica `b` exactamente una unidad hacia cero. Por eso la cantidad de llamadas depende de `|b|`, no de `|a|`. No hace falta calcular el valor absoluto de `b`: la selección de rama y la actualización de `b` evitan problemas al evaluar el valor absoluto de `Integer.MIN_VALUE`.

## 3. Tratamiento de factores negativos

Los dos factores pueden ser negativos:

- `b < 0`: cada paso aporta `-a` y aumenta `b` hasta alcanzar cero.
- `a < 0`: la suma del valor negativo conserva el signo correspondiente al resultado.
- Ambos negativos: los aportes `-a` son positivos, de modo que el resultado es positivo.
- Cualquier `b == 0`: se devuelve cero, incluso si `a` es negativo.

La negación `-a` se evalúa con semántica `int` de Java; en particular, `-Integer.MIN_VALUE` desborda y permanece representable como un entero con signo, conforme a la aritmética modular de Java.

## 4. Memoria y terminación

Sea `n = |b|`. La recursión realiza `n` pasos recursivos y alcanza el caso base en la llamada siguiente. Cada llamada pendiente conserva un marco del Call Stack, por lo que:

- **Memoria auxiliar del Call Stack:** `O(n) = O(|b|)`.
- **Tiempo:** `O(n) = O(|b|)`, con una suma y una llamada recursiva por paso.

La medida `|b|` decrece en cada llamada, lo que demuestra la terminación para todo `b` finito. Sin embargo, un `|b|` grande requiere una profundidad lineal y puede exceder el límite de pila de la JVM (`StackOverflowError`). En particular, el caso `Integer.MIN_VALUE` es finito pero exigiría una cantidad inviable de llamadas para esta solución recursiva. El método se destina a valores moderados, tal como requiere el ejercicio.

## 5. Verificación funcional

Probar usando resultados esperados constantes, sin calcularlos con el operador prohibido:

| `a` | `b` | Resultado esperado |
|---:|---:|---:|
| 5 | 3 | 15 |
| 7 | 0 | 0 |
| 5 | -3 | -15 |
| -5 | 3 | -15 |
| -5 | -3 | 15 |
| 0 | -4 | 0 |

Integrar estos casos en el mecanismo de pruebas ya existente; si no hay uno, pueden presentarse como ejemplos manuales del método.