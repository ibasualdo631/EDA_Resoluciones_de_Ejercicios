# Plan técnico — Ejercicio 7: Sumar dígitos

## 1. Arquitectura de la solución

La solución se resuelve con una única función recursiva, sin estructuras auxiliares ni acumuladores externos. La idea central es dividir el problema en un subproblema más pequeño hasta llegar a un número de un solo dígito.

### Estructura propuesta

- Función principal:
  - `public static int sumarDigitos(int n)`
- Regla recursiva:
  - `ultimoDigito = n % 10`
  - `nReducido = n / 10`
  - `return ultimoDigito + sumarDigitos(nReducido);`

### Descomposición lógica

1. Se extrae el último dígito con `n % 10`.
2. Se descarta ese dígito con `n / 10` (división entera).
3. Se llama recursivamente a la versión reducida del problema.
4. Se suma el dígito actual al resultado de la llamada recursiva.
5. La recursión termina cuando el número ya tiene un solo dígito.

### Caso base

El caso base debe definirse exactamente así:

```java
if (n < 10) {
    return n;
}
```

Esto cumple la regla del ejercicio: si el número tiene un solo dígito, su suma es el mismo número.

### Ejemplo de ejecución

Para `1234`:

- `1234 % 10 = 4`
- `1234 / 10 = 123`
- `1234 -> 4 + sumarDigitos(123)`
- `123 -> 3 + sumarDigitos(12)`
- `12 -> 2 + sumarDigitos(1)`
- `1 -> 1` (caso base)

Resultado final: `4 + 3 + 2 + 1 = 10`.

## 2. Huella de memoria espacial

La implementación recursiva usa una pila de llamadas. Cada invocación conserva:

- el valor actual de `n`
- el cálculo del dígito extraído
- la llamada recursiva siguiente

Por lo tanto, la huella de memoria espacial es:

- `O(d)` en el peor caso, donde `d` es la cantidad de dígitos del número
- equivalentemente, `O(log10 n)` para enteros positivos

Esto ocurre porque la profundidad de la recursión es proporcional a la cantidad de dígitos a procesar. No se utiliza memoria adicional por arreglos, listas ni estructuras dinámicas.

La memoria auxiliar es constante por frame, pero la cantidad total de frames crece linealmente con la longitud del número.

## 3. Directivas para la implementación

1. Mantener la función recursiva como la única lógica de cálculo.
2. Usar `n % 10` para obtener el último dígito y `n / 10` para reducir el problema.
3. Aplicar el caso base antes de cualquier otra operación recursiva:
   - si `n < 10`, devolver `n`.
4. No agregar estructuras complejas: la solución debe ser clara y directa para un ejercicio introductorio.
5. En cada llamada, dejar explícito el significado de las operaciones:
   - `n % 10` obtiene el dígito de las unidades
   - `n / 10` elimina ese dígito para continuar con el resto
6. La combinación final debe ser la suma del último dígito más el resultado de la llamada recursiva.
7. El `main` debe probar al menos:
   - `5 -> 5`
   - `24 -> 6`
   - `1234 -> 10`
8. Mantener nombres de variables descriptivos como `ultimoDigito` y `numeroReducido` para favorecer la legibilidad.
9. Incluir un caso límite opcional para `0`, que también satisface el caso base y devuelve `0`.

## 4. Complejidad esperada

- Tiempo: `O(d)`, donde `d` es la cantidad de dígitos
- Espacio auxiliar: `O(d)` por la pila de recursión

Esta complejidad es óptima para una solución de este tipo y refleja exactamente el enfoque recursivo del problema.
