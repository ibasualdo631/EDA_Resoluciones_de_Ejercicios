# Explicación: palíndromo recursivo

## Insensibilidad a mayúsculas

El método público convierte la palabra a minúsculas antes de empezar las comparaciones:

```java
return esPalindromoNormalizado(palabra.toLowerCase(Locale.ROOT));
```

Así, por ejemplo, `"rAdAr"` se procesa como `"radar"` y el uso de mayúsculas no cambia el resultado. `Locale.ROOT` hace que la conversión sea estable y no dependa del idioma configurado en la computadora. La normalización se hace una vez al entrar; las llamadas recursivas trabajan con la cadena ya normalizada.

## Cómo avanza la recursividad

`esPalindromoNormalizado` compara el primer carácter con el último:

1. Si la longitud es cero o uno, devuelve `true`: no hay un par de extremos que contradiga que la cadena sea palíndroma. Este es el caso base de éxito.
2. Si los extremos son distintos, devuelve `false` inmediatamente.
3. Si coinciden, llama al mismo método con el interior de la cadena, usando `substring(1, ultimoIndice)`.

Cada llamada recursiva elimina dos caracteres ya comprobados. Por ejemplo, con `"radar"` se comprueba `"radar"`, luego `"ada"` y finalmente `"d"`; al llegar a longitud uno se alcanza el caso base y se devuelve `true`.

## El corte anticipado cuando los extremos difieren

La condición `if (palabra.charAt(0) != palabra.charAt(ultimoIndice))` funciona como un rechazo inmediato. Al encontrar esa diferencia, el método devuelve `false` **antes** de ejecutar la llamada recursiva que está al final del método. Como los extremos de un palíndromo necesariamente deben coincidir, ninguna comprobación del interior podría cambiar ese resultado.

Por ejemplo, `"RaDaX"` se normaliza a `"radax"`. El primer carácter es `r` y el último es `x`; como difieren, devuelve `false` sin examinar recursivamente los caracteres del medio. Si ya se habían realizado llamadas para reducir la palabra, cada una recibe ese `false` y lo devuelve hacia atrás, sin continuar con otras comparaciones.

Un matiz importante: esa condición suele llamarse **caso de rechazo** o **corte anticipado**, no caso base recursivo en sentido estricto. El caso base que detiene la recursión con éxito es el de longitud menor o igual a uno. Ambos evitan llamadas adicionales, pero por razones distintas: uno resuelve una cadena trivial y el otro descarta una cadena en cuanto encuentra una contradicción.

La cadena de prueba larga cuyos extremos difieren demuestra que el método no genera una larga cadena de llamadas recursivas en ese escenario. Sin embargo, `toLowerCase(Locale.ROOT)` sí normaliza la entrada completa antes de comparar; el corte temprano evita la recursión y sus comparaciones posteriores, no esa normalización inicial.

## Pregunta trampa para examen

**Si se evalúa `"abca"`, ¿el algoritmo devuelve `true` apenas ve que los extremos son `a` y `a`?**

**Respuesta:** No. La coincidencia de los extremos solo permite seguir con el interior, que es `"bc"`. Allí `b` y `c` son distintos, así que el método devuelve `false`. Para que la palabra sea palíndroma, deben coincidir todos los pares de extremos hasta alcanzar el caso base.