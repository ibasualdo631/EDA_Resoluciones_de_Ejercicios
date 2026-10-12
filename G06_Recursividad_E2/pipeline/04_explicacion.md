# Explicación de `SumaNumeros.java`

## 1) Qué problema resuelve

Este programa implementa una función recursiva que suma todos los enteros desde `n` hasta `1`.

La lógica es esta:

- si `n` es `0`, la suma debe dar `0`
- si `n` es mayor que `0`, la suma debe ser `n + suma(n - 1)`
- si `n` es negativo, el programa debe rechazarlo con `IllegalArgumentException`

La condición pedida es exactamente la definición recursiva:

```
suma(0) = 0
suma(n) = n + suma(n - 1)   si n > 0
```

El archivo a explicar es [SumaNumeros.java](C:/Users/ignac/Escritorio/(( escritorio )) importantes/Facultad/SEMESTRE IV/ESTRUCTURAS DE DATOS Y ALGORITMOS/Ejercicios/RESOLUCIONES/G06_Recursividad_E2/SumaNumeros.java).

---

## 2) Estructura general de la clase

```java
public final class SumaNumeros {
```

- `public` hace que la clase sea accesible desde fuera del archivo.
- `final` indica que no se puede heredar de ella.
- La clase se usa como una "clase utilitaria": no tiene estado ni se crea instancias de la misma.

Luego aparece esto:

```java
private SumaNumeros() {
}
```

Esto es un constructor privado. Su objetivo es impedir que alguien haga:

```java
SumaNumeros s = new SumaNumeros();
```

Como todos los métodos son estáticos (`static`), no hace falta instanciar la clase. Esto es una práctica común para clases de utilidad.

---

## 3) Método público: validación inicial

```java
public static long sumaRecursiva(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("El argumento debe ser no negativo.");
    }
    return sumarDesde(n);
}
```

Este método cumple dos tareas:

1. Validar que el valor de entrada no sea negativo.
2. Si es válido, delegar el cálculo a la función recursiva auxiliar.

### ¿Por qué validar?

Porque la definición matemática de la suma desde `n` hasta 1 solo tiene sentido para valores no negativos. Si alguien llama con `-1`, no se puede calcular una suma de enteros desde -1 hacia abajo usando la lógica pedida.

Entonces, el código lanza una excepción de tipo `IllegalArgumentException`, que es la forma correcta de indicar que el argumento es inválido.

---

## 4) La recursión real: `sumarDesde`

```java
private static long sumarDesde(int n) {
    if (n == 0) {
        return 0;
    }
    return n + sumarDesde(n - 1);
}
```

Este es el corazón del programa.

### Caso base

```java
if (n == 0) {
    return 0;
}
```

Cuando la entrada llega a `0`, la recursión termina. Ese es el caso base imprescindible. Si no existiera este caso, el método llamaría infinitamente a `sumarDesde(-1)`, `sumarDesde(-2)`, etc.

### Paso recursivo

```java
return n + sumarDesde(n - 1);
```

Aquí se calcula:

- el valor actual `n`
- más el resultado de llamar a la misma función con `n - 1`

Esto descompone el problema en subproblemas más pequeños hasta llegar a `0`.

---

## 5) Ejemplo de ejecución para `n = 5`

La llamada sería:

```java
sumaRecursiva(5)
```

Eso ejecuta:

```java
sumarDesde(5)
```

y se desarrolla así:

```text
sumarDesde(5) = 5 + sumarDesde(4)
sumarDesde(4) = 4 + sumarDesde(3)
sumarDesde(3) = 3 + sumarDesde(2)
sumarDesde(2) = 2 + sumarDesde(1)
sumarDesde(1) = 1 + sumarDesde(0)
sumarDesde(0) = 0
```

Ahora se resuelven las llamadas desde abajo:

```text
sumarDesde(1) = 1 + 0 = 1
sumarDesde(2) = 2 + 1 = 3
sumarDesde(3) = 3 + 3 = 6
sumarDesde(4) = 4 + 6 = 10
sumarDesde(5) = 5 + 10 = 15
```

Resultado final:

```text
sumaRecursiva(5) = 15
```

Esto coincide con la suma `5 + 4 + 3 + 2 + 1 = 15`.

---

## 6) ¿Por qué se usa `long`?

La firma del método es:

```java
public static long sumaRecursiva(int n)
```

`long` es un tipo entero de 64 bits, más grande que `int` (32 bits). Aunque en este ejercicio los valores probados son pequeños, `long` ofrece más margen si se usa con números mayores.

Esto es útil porque la suma puede crecer rápidamente, y `long` reduce el riesgo de desbordamiento en valores más grandes.

---

## 7) La parte de validación del `main`

```java
public static void main(String[] args) {
    verificarResultado(0, 0);
    verificarResultado(1, 1);
    verificarResultado(5, 15);
    verificarRechazo(-1);

    System.out.println("sumaRecursiva(0) = " + sumaRecursiva(0));
    System.out.println("sumaRecursiva(1) = " + sumaRecursiva(1));
    System.out.println("sumaRecursiva(5) = " + sumaRecursiva(5));
}
```

El método `main` hace dos cosas:

### a) Prueba valores válidos

```java
verificarResultado(0, 0);
verificarResultado(1, 1);
verificarResultado(5, 15);
```

Se comprueba que el resultado obtenido coincida con el esperado.

### b) Prueba un valor inválido

```java
verificarRechazo(-1);
```

Se verifica que al pasar `-1`, el método lance `IllegalArgumentException`.

Esto hace que el programa valide explícitamente tanto la lógica correcta como el manejo del error.

---

## 8) Cómo funciona `verificarResultado`

```java
private static void verificarResultado(int entrada, long esperado) {
    long resultado = sumaRecursiva(entrada);
    if (resultado != esperado) {
        throw new AssertionError(
                "sumaRecursiva(" + entrada + "): se esperaba "
                        + esperado + " y se obtuvo " + resultado);
    }
}
```

- Llama a `sumaRecursiva(entrada)`
- Compara el resultado con el valor esperado
- Si no coincide, lanza un `AssertionError`

Esto hace que el programa falle en forma clara si la recursión está mal implementada.

---

## 9) Cómo funciona `verificarRechazo`

```java
private static void verificarRechazo(int entrada) {
    try {
        sumaRecursiva(entrada);
    } catch (IllegalArgumentException exception) {
        return;
    }
    throw new AssertionError(
            "sumaRecursiva(" + entrada + ") debía lanzar IllegalArgumentException");
}
```

Este método intenta ejecutar `sumaRecursiva(entrada)` en un bloque `try`.

- Si se lanza `IllegalArgumentException`, entonces el programa hace `return` y considera la prueba correcta.
- Si no se lanza ninguna excepción, entonces falla con `AssertionError`.

Es una forma elegante de probar errores esperados sin interrumpir el programa manualmente.

---

## 10) Conceptos clave de Java que aparecen

### 1. Recursión
La función se llama a sí misma:

```java
sumarDesde(n - 1)
```

Eso es recursión.

### 2. Caso base
El caso `n == 0` evita un bucle infinito.

### 3. Validación con excepciones

```java
throw new IllegalArgumentException(...);
```

Lanza un error cuando el valor no es válido.

### 4. Métodos estáticos

```java
public static long sumaRecursiva(int n)
```

No necesitan una instancia de la clase para ejecutarse.

### 5. Clase utilitaria
La clase no almacena estados y solo ofrece operaciones relacionadas con la suma.

---

## 11) Complejidad temporal y espacial

### Tiempo
Hay una llamada recursiva por cada valor desde `n` hasta `1`.

Por lo tanto, el tiempo es:

```text
O(n)
```

### Memoria
Cada llamada queda guardada en la pila de ejecución hasta llegar al caso base.

Por lo tanto, el uso de memoria es:

```text
O(n)
```

Esto es típico de una solución recursiva lineal.

---

## 12) Errores comunes que se evitan aquí

1. No definir el caso base
   - provocaría recursión infinita.

2. No validarla entrada negativa
   - podría permitir errores semánticos o comportamiento inesperado.

3. No decrementar `n` en cada llamada
   - la recursión no avanzaría y tampoco terminaría.

4. Usar una fórmula cerrada o un ciclo
   - el enunciado pide explícitamente una solución recursiva, y este código cumple esa condición.

---

## 13) Resumen final

Este programa demuestra una implementación clara y correcta de suma recursiva en Java:

- valida entradas negativas
- usa un caso base (`n == 0`)
- reduce el problema en cada llamada
- prueba los casos esperados
- lanza una excepción cuando el valor es inválido

Por eso, para `n = 5` devuelve `15`, y para `n = 0` devuelve `0`.

Si quieres, también puedo ayudarte a hacer una versión con:

- una explicación más breve para estudiar antes del parcial,
- una versión con comentarios dentro del código,
- o una comparación entre recursión y ciclo para entender mejor la diferencia.
