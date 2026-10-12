# Plan técnico — Selection Sort de nombres

## Decisiones de diseño

- **Lenguaje:** Java, según la sintaxis `String[]` de la consigna.
- **Método público:** `public static void ordenarNombres(String[] nombres)`.
- **Modificación:** ordenar in situ; el método devuelve `void`.
- **Algoritmo:** Selection Sort ascendente. No usar `Arrays.sort`, streams ni otros algoritmos de ordenamiento.
- **Comparación:** comparar nombres no nulos mediante `String.compareTo()`. Esto aplica el orden natural de Java, no reglas regionales de diccionario.
- **Arreglo nulo:** rechazar la referencia nula al inicio con `Objects.requireNonNull(nombres, "nombres no puede ser null")`. El método lanza `NullPointerException` y no intenta ordenar.
- **Elementos nulos:** considerarlos mayores que cualquier nombre no nulo y colocarlos al final. Nunca invocar `compareTo()` cuando alguno de los operandos sea `null`. Dos valores nulos se consideran iguales para esta ordenación.
- **Memoria:** intercambiar referencias en el arreglo; no copiar ni transformar las cadenas.

## Estructura y firmas

Usar una clase de solución según la organización ya existente en el proyecto. La operación central y el comparador auxiliar pueden tener estas firmas:

```java
public static void ordenarNombres(String[] nombres)
private static int compararNombres(String primero, String segundo)
```

`compararNombres` devuelve un entero negativo si `primero` debe ir antes, cero si son iguales según la política de ordenación, o un entero positivo si debe ir después. El contrato consistente es:

| `primero` | `segundo` | Resultado |
|---|---|---:|
| `null` | `null` | `0` |
| no nulo | `null` | negativo |
| `null` | no nulo | positivo |
| ambos no nulos | ambos no nulos | `primero.compareTo(segundo)` |

## Implementación de referencia

```java
import java.util.Objects;

public static void ordenarNombres(String[] nombres) {
    Objects.requireNonNull(nombres, "nombres no puede ser null");

    for (int i = 0; i < nombres.length - 1; i++) {
        int indiceMenor = i;

        for (int j = i + 1; j < nombres.length; j++) {
            if (compararNombres(nombres[j], nombres[indiceMenor]) < 0) {
                indiceMenor = j;
            }
        }

        if (indiceMenor != i) {
            String temporal = nombres[i];
            nombres[i] = nombres[indiceMenor];
            nombres[indiceMenor] = temporal;
        }
    }
}

private static int compararNombres(String primero, String segundo) {
    if (primero == segundo) {
        return 0;
    }
    if (primero == null) {
        return 1;
    }
    if (segundo == null) {
        return -1;
    }
    return primero.compareTo(segundo);
}
```

Si el proyecto exige que cada método esté dentro de una clase, colocar ambos métodos en esa clase; el fragmento muestra solo los imports y métodos para no imponer un nombre de clase ajeno a la estructura existente.

## Secuencia de ejecución

1. Validar la referencia del arreglo con `Objects.requireNonNull`.
2. Recorrer las posiciones `i` desde `0` hasta `nombres.length - 2`. El prefijo anterior a `i` ya queda ordenado.
3. Inicializar `indiceMenor` con `i`.
4. Recorrer con `j` las posiciones posteriores a `i`. Comparar el candidato `nombres[j]` con el mínimo actual mediante `compararNombres`.
5. Actualizar `indiceMenor` cuando el candidato sea estrictamente menor.
6. Al terminar la búsqueda, intercambiar las referencias en `i` e `indiceMenor` solo si son posiciones distintas.
7. Finalizar con el arreglo ordenado en orden natural ascendente y todos sus valores nulos al final.

La condición estricta `< 0` conserva la primera aparición del mínimo durante la búsqueda. Selection Sort no es estable en general: no prometer preservación del orden relativo de elementos iguales.

## Traza del ejemplo

Entrada: `["Lucia", "Ana", "Pedro", "Juan"]`.

1. Desde la posición 0, el menor restante es `"Ana"`; intercambiarlo con `"Lucia"`: `["Ana", "Lucia", "Pedro", "Juan"]`.
2. Desde la posición 1, el menor restante es `"Juan"`; intercambiarlo con `"Lucia"`: `["Ana", "Juan", "Pedro", "Lucia"]`.
3. Desde la posición 2, el menor restante es `"Lucia"`; intercambiarlo con `"Pedro"`: `["Ana", "Juan", "Lucia", "Pedro"]`.
4. La última posición queda ordenada por consecuencia.

Con entrada `["Zoe", null, "Ana", null]`, el resultado debe ser `["Ana", "Zoe", null, null]`.

## Complejidad

- Se evalúan `n(n-1)/2` pares de posiciones en el bucle interno: Θ(n²) comparaciones de elementos incluso si la entrada ya está ordenada.
- Comparar dos nombres no nulos con `compareTo()` puede examinar hasta `L` caracteres, donde `L` es la longitud máxima. El peor caso es O(n²·L); los casos con nulos requieren solo un número constante de comprobaciones por par y no elevan ese límite.
- Se realizan como máximo `n-1` intercambios; cada uno reasigna referencias en O(1).
- Espacio auxiliar: O(1), sin contar el arreglo de entrada.

La inmutabilidad de `String` impide cambiar los caracteres de los objetos, pero no hace constante la comparación lexicográfica. El algoritmo ordena las referencias y `compareTo()` lee los contenidos necesarios para decidir el orden.

## Instrucciones detalladas para el desarrollador

1. Localizar la clase y el punto de entrada que ya usa el ejercicio; respetar el estilo y la estructura existentes. No crear una clase ejecutable duplicada si ya hay una.
2. Añadir `import java.util.Objects;` si no existe y definir `ordenarNombres(String[] nombres)` como método estático público (o ajustar solo el modificador si la arquitectura existente requiere una instancia).
3. Rechazar `nombres == null` al inicio con `Objects.requireNonNull` y el mensaje indicado. No convertir una entrada inválida en un arreglo vacío ni omitir silenciosamente el error.
4. Implementar los dos bucles de Selection Sort exactamente como se describe: `i` fija el comienzo de la zona desordenada, `indiceMenor` guarda el mínimo, y `j` explora el resto.
5. Centralizar la política de comparación de nulos en `compararNombres`. Resolver primero los casos de identidad/nulidad y usar `primero.compareTo(segundo)` únicamente cuando ambos sean no nulos. No comparar referencias de `String` con `==` para determinar igualdad de contenido.
6. Actualizar el índice mínimo únicamente cuando el comparador devuelve un valor menor que cero; no ordenar mediante concatenación, conversión a minúsculas ni locale no solicitado.
7. Intercambiar los elementos usando una variable temporal de tipo `String`, solo si `indiceMenor != i`. El intercambio mueve referencias y no altera los objetos inmutables.
8. Conservar el arreglo de ejemplo y mostrar el resultado `["Ana", "Juan", "Lucia", "Pedro"]`.
9. Probar arreglo vacío, un elemento, orden ascendente, orden inverso, valores repetidos, prefijos (`"Ana"`/`"Analia"`), nulos intercalados y varios nulos. Confirmar que una referencia de arreglo nula lanza `NullPointerException`.
10. Revisar que no se use otro algoritmo o método de biblioteca y documentar que el orden es el natural de Java, sensible a mayúsculas y no necesariamente idéntico a la colación lingüística local.