# Plan técnico — Potencia recursiva

## Diseño de la solución

Implementar una función estática en Java que calcule `base^exp` mediante multiplicaciones sucesivas. No se usará `Math.pow` ni otra función de biblioteca para calcular la potencia. Se mantiene el alcance del ejercicio: exponente entero no negativo y reducción de una unidad por llamada, sin exponenciación rápida.

### Firma propuesta

```java
public static int potencia(int base, int exp)
```

- `base`: base entera de la potencia.
- `exp`: exponente entero.
- Retorno: resultado entero de la potencia. Si el resultado no cabe en `int`, se lanza `ArithmeticException` en vez de devolver un valor desbordado.
- `static`: permite invocarla desde el método `main` o desde pruebas sin crear una instancia.
- La multiplicación se realiza con `Math.multiplyExact`, que detecta desbordamientos de `int` sin calcular la potencia mediante una función de biblioteca.

## Secuencia y lógica recursiva

1. Validar primero que `exp >= 0`. Si es negativo, lanzar `IllegalArgumentException` con un mensaje que indique que el exponente no puede ser negativo. La validación ocurre antes de cualquier autollamada.
2. Si `exp == 0`, devolver `1`, el elemento neutro de la multiplicación.
3. En otro caso, devolver `Math.multiplyExact(base, potencia(base, exp - 1))`. Si el producto excede el rango de `int`, la operación lanza `ArithmeticException`.

La condición `exp == 0` termina la recursión. Para todo exponente aceptado, `exp - 1` lo acerca al caso base y produce exactamente `exp` autollamadas antes de alcanzarlo. El caso base devuelve `1`, por lo que para `exp == 1` se obtiene `base * 1`.

## Complejidad

Sea `n = exp` para entradas válidas:

- **Tiempo:** `O(n)`, pues se realiza una multiplicación por cada nivel no base y hay `n` niveles recursivos.
- **Espacio auxiliar:** `O(n)`, por la pila de llamadas recursivas.
- **Autollamadas:** exactamente `n` cuando `n >= 0`; cero cuando `n == 0`. Para `exp < 0` no hay autollamadas: la función lanza la excepción inmediatamente.

## Organización y verificación

Integrar el método en la clase y ubicación que ya establezca el proyecto. Mantener la solución acotada al ejercicio y agregar o adaptar pruebas para:

- `potencia(5, 0) == 1`
- `potencia(5, 1) == 5`
- `potencia(2, 3) == 8`
- `potencia(0, 4) == 0`
- `potencia(-2, 3) == -8`
- `potencia(2, 30) == 1073741824`, todavía dentro del rango de `int`.
- `potencia(2, 31)` lanza `ArithmeticException` porque el resultado excede el rango de `int`.
- Una llamada con exponente negativo lanza `IllegalArgumentException`.

La implementación está en `Potencia.java`. Ejecutar la clase para validar los ejemplos y confirmar que el desbordamiento se detecta; comunicar el resultado.