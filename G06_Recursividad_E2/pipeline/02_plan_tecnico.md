# Plan técnico — suma recursiva desde N hasta 1

## Decisiones de diseño

- Implementar en Java, de acuerdo con el contexto indicado en el análisis.
- Exponer un método público que valide el argumento y delegue el cálculo recursivo a un método auxiliar privado.
- Usar `long` como tipo de retorno para representar el resultado de cualquier `n` no negativo que quepa en un `int`: incluso la suma para `Integer.MAX_VALUE` cabe en un `long`.
- No reemplazar la recurrencia por una fórmula cerrada ni por un bucle.

## Firmas

```java
public static long sumaRecursiva(int n)
private static long sumarDesde(int n)
```

`sumaRecursiva` constituye la interfaz de entrada. `sumarDesde` recibe únicamente valores validados y aplica la recurrencia.

## Validación y contrato

- Si `n < 0`, `sumaRecursiva` lanza `IllegalArgumentException` con un mensaje que indique que el argumento debe ser no negativo. La excepción se lanza antes de iniciar la recursión.
- Si `n == 0`, devuelve `0`.
- Si `n > 0`, devuelve la suma de los enteros de `n` a `1`, inclusive.

Validar en la interfaz pública y separar el auxiliar evita repetir la misma comprobación en cada llamada recursiva.

## Secuencia del cálculo

1. `sumaRecursiva(n)` comprueba que `n` no sea negativo.
2. Si la validación pasa, invoca `sumarDesde(n)`.
3. `sumarDesde(0)` devuelve `0` y termina el descenso.
4. Para `n > 0`, `sumarDesde(n)` devuelve `n + sumarDesde(n - 1)`.
5. Al alcanzar cero, las llamadas pendientes retornan y suman sus respectivos valores.

Por ejemplo, `sumarDesde(3)` se evalúa como `3 + (2 + (1 + 0)) = 6`.

## Complejidad

Para un argumento positivo `n`, el descenso genera llamadas con los argumentos `n, n - 1, ..., 1, 0`: son `n + 1` llamadas. Cada llamada realiza trabajo constante (comparación con el caso base y, salvo en la base, una suma y una llamada recursiva). Por lo tanto, el tiempo total es `O(n)`; el caso `n = 0` toma `O(1)`, coherente con `O(n)` para el comportamiento general.

La recursión mantiene hasta `n + 1` marcos de llamada pendientes, así que usa `O(n)` de espacio auxiliar en la pila. En Java, valores muy grandes pueden exceder la capacidad de la pila aunque el resultado numérico quepa en `long`; el uso previsto debe limitarse a valores compatibles con la profundidad recursiva disponible.

## Comprobaciones de aceptación

| Entrada | Resultado esperado |
|---:|---:|
| `0` | `0` |
| `1` | `1` |
| `5` | `15` |
| `-1` | Lanza `IllegalArgumentException` |