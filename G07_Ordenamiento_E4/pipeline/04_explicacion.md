# Explicación — `String.compareTo()` y Unicode

## Qué significa comparar dos `String`

En `SelectionSortNombres`, la función auxiliar `compararNombres` se ocupa primero de los casos `null`. Cuando ambos nombres son cadenas no nulas, ejecuta:

```java
return primero.compareTo(segundo);
```

`compareTo()` aplica el orden natural lexicográfico de `String`. Compara las dos cadenas desde el comienzo y busca la primera posición donde sus unidades de texto difieren. La decisión se toma allí; los caracteres siguientes ya no afectan el resultado.

Conceptualmente, compara las unidades UTF-16:

```text
para cada posición k compartida por ambas cadenas:
    si primero[k] != segundo[k]:
        el menor valor UTF-16 queda primero
si todas las unidades compartidas son iguales:
    la cadena más corta queda primero
si tienen igual longitud:
    son iguales para compareTo()
```

El contrato público de Java garantiza el signo del resultado:

- Negativo: `primero` precede a `segundo`.
- Cero: ambas cadenas tienen la misma secuencia de unidades UTF-16.
- Positivo: `primero` sigue a `segundo`.

No conviene depender de un número exacto como `-1` o `1`; lo correcto es comprobar si el resultado es `< 0`, `== 0` o `> 0`. En la implementación habitual, cuando encuentra una diferencia se devuelve una diferencia entre los valores de las unidades; si una cadena es prefijo de la otra, decide por la diferencia de longitudes. Las optimizaciones internas del JDK pueden variar, pero deben conservar el contrato.

## Unidades UTF-16 y valores numéricos

En Java, `char` representa una unidad de código UTF-16 de 16 bits. Para las letras latinas básicas, su valor coincide con el valor Unicode correspondiente:

| Carácter | Valor Unicode | Valor usado en la comparación |
|---|---:|---:|
| `A` | U+0041 | `0x0041` (65) |
| `J` | U+004A | `0x004A` (74) |
| `a` | U+0061 | `0x0061` (97) |
| `l` | U+006C | `0x006C` (108) |

Por eso las mayúsculas y minúsculas no se consideran equivalentes. Por ejemplo, `"Ana".compareTo("ana")` es negativo porque la primera unidad `A` (65) es menor que `a` (97). No se realiza automáticamente conversión a minúsculas ni comparación sin distinción de caja.

## Ejemplos paso a paso

### Nombres que empiezan igual: `"Juan"` y `"Julio"`

1. `J` coincide con `J` (U+004A).
2. `u` coincide con `u` (U+0075).
3. En la tercera posición se comparan `a` (U+0061) y `l` (U+006C).
4. Como `a < l`, `"Juan"` precede a `"Julio"` y `compareTo()` devuelve un valor negativo.

El hecho de compartir inicial —o incluso el prefijo `"Ju"`— no impide que los caracteres siguientes determinen el orden.

### Una cadena es prefijo de otra: `"Ana"` y `"Analia"`

Las tres unidades de `"Ana"` coinciden con las primeras tres de `"Analia"`. Agotada la cadena más corta, `"Ana"` queda antes. El contenido adicional de `"Analia"` no cambia el orden de las posiciones ya coincidentes.

### Una letra acentuada precompuesta y su forma descompuesta

El texto visible `é` puede representarse como una sola unidad, U+00E9, o como `e` seguida de la marca combinante U+0301:

```text
"é"        -> [U+00E9]
"e\u0301"  -> [U+0065, U+0301]
```

`compareTo()` no normaliza estas representaciones. Compara primero U+00E9 con U+0065 y, como son distintos, decide ahí que U+00E9 es mayor. Los textos pueden verse iguales para una persona y, aun así, no ser iguales para `compareTo()`.

## Suplementarios y puntos de código

Unicode contiene caracteres cuyo valor supera U+FFFF. UTF-16 los representa mediante un par de unidades `char` llamado par sustituto. `compareTo()` compara esas unidades UTF-16 en secuencia; no convierte necesariamente cada cadena en una lista de puntos de código Unicode antes de comparar.

Esto importa si se espera un orden numérico de puntos de código. Por ejemplo, U+E000 es menor que U+10000 como valor Unicode, pero U+10000 comienza en UTF-16 con la unidad sustituta alta U+D800. Al comparar `"\uE000"` con `"\uD800\uDC00"`, `compareTo()` compara U+E000 con U+D800: como U+D800 es menor, la cadena que representa U+10000 queda antes. Por tanto, el orden de `String` no debe describirse como una comparación simple de puntos de código Unicode escalares; es un orden por unidades UTF-16.

## Qué no hace `compareTo()`

- No es una ordenación lingüística regional. No consulta un `Locale` ni aplica reglas de diccionario para acentos, contracciones o letras propias de un idioma.
- No ignora mayúsculas/minúsculas. Para una comparación sin distinción de caja existe `compareToIgnoreCase()`, pero tampoco sustituye una colación lingüística completa.
- No normaliza Unicode. Formas visualmente equivalentes con secuencias distintas pueden compararse como diferentes.
- No analiza grafemas visuales. Un símbolo que una persona percibe como un carácter puede componerse de varias unidades o puntos de código.
- No cambia ni copia el texto. Solo determina el orden; en este ejercicio Selection Sort intercambia las referencias almacenadas en el arreglo.

Si el requisito fuera ordenar como un diccionario para un idioma concreto, habría que definir esa regla y usar un comparador basado en `java.text.Collator` con el `Locale` apropiado, en lugar de `String.compareTo()`. Eso cambiaría deliberadamente el criterio del ejercicio.

## Relación con Selection Sort y el costo

Selection Sort llama repetidamente al comparador para elegir el menor nombre de la sección todavía no ordenada. Para `n` elementos realiza `n(n-1)/2` comparaciones entre elementos, incluso si ya están ordenados. Cada llamada a `compareTo()` puede detenerse pronto si los nombres difieren al inicio, o inspeccionar casi toda la parte común si son muy parecidos.

Si `m` es la longitud en unidades UTF-16 del prefijo común inspeccionado, el costo de una comparación es proporcional a `m + 1` cuando encuentra una diferencia, o a la longitud de la cadena más corta cuando una es prefijo de la otra. Con longitud máxima `L`, el peor caso de todo el algoritmo es O(n²·L). El intercambio sigue siendo O(1): se reasignan referencias, no se recorren los caracteres para moverlos.

## Aplicación al programa

La función `compararNombres` define además la política de nulos antes de llegar a `compareTo()`:

- Dos referencias iguales, incluyendo `null` con `null`, se consideran equivalentes.
- `null` después de cualquier nombre no nulo.
- Dos nombres no nulos se comparan usando `primero.compareTo(segundo)`.

Así se evita invocar un método sobre `null`; Selection Sort coloca los nombres en orden natural ascendente y los valores nulos al final.