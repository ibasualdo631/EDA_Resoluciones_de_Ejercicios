# Análisis — Ejercicio 8: invertir un String de forma recursiva

## Objetivo

Escribir una función recursiva que reciba un `String` y devuelva sus caracteres
en orden inverso. La solución debe mostrar claramente el caso base, la reducción
del problema y la reconstrucción del resultado al retornar de las llamadas.

## Requerimientos funcionales

- Recibir una cadena y devolver otra con los mismos caracteres en orden inverso.
- Para la cadena vacía, devolver la cadena vacía.
- Para una cadena de un carácter, devolver ese mismo carácter.
- Separar el primer carácter del resto usando `charAt(0)` y `substring(1)`.
- Resolver recursivamente el resto de la cadena.
- Reconstruir el resultado colocando el primer carácter al final del resultado
  recursivo: `invertir(resto) + primerCaracter`.
- No usar iteración ni utilidades que inviertan la cadena; la inversión debe
  producirse mediante recursión.

## Análisis de la recursión

El caso base es una cadena vacía o de longitud 1 porque ambas pueden invertirse
sin resolver subproblemas: no hay caracteres que reordenar en `""`, y `"a"` ya
está invertida. Devolver la cadena recibida en esos casos también evita intentar
leer un carácter inexistente.

En cada llamada, `substring(1)` obtiene la cadena desde el índice 1 hasta el
final, es decir, elimina el primer carácter. Para una cadena de longitud `n`,
la siguiente llamada recibe una cadena de longitud `n - 1`; por ello, la
secuencia necesariamente llega al caso base.

Al regresar, cada llamada agrega al final el carácter que había apartado. Por
ejemplo:

```text
invertir("sol")
= invertir("ol") + "s"
= (invertir("l") + "o") + "s"
= "l" + "o" + "s"
= "los"
```

La función debe conservar todos los caracteres, incluidos espacios y signos;
no debe normalizar ni cambiar mayúsculas/minúsculas.

## Pruebas sugeridas

| Entrada | Resultado esperado | Motivo |
|---|---|---|
| `""` | `""` | Caso base: cadena vacía |
| `"a"` | `"a"` | Caso base: un carácter |
| `"sol"` | `"los"` | Inversión normal |
| `"reconocer"` | `"reconocer"` | Palíndromo |
| `"a b"` | `"b a"` | Conserva espacios |

## Suposiciones y límites

- Se asume Java, ya que la consigna menciona `String` y `substring(1)`.
- La entrada no nula es el contrato esperado. Si el proyecto ya define cómo
  tratar `null`, la implementación debe respetar esa convención en lugar de
  introducir un comportamiento incompatible.
- La consigna se centra en la inversión recursiva; no requiere validaciones,
  interacción por consola ni cambios ajenos al ejercicio.

## Prompt inicial para OpenCode

> En este ejercicio de Java, implementa una función recursiva que invierta un
> `String`. Primero revisa los archivos existentes y sigue la organización y el
> estilo del proyecto; limita los cambios a lo necesario para resolver el
> ejercicio.
>
> La función debe contemplar como casos base la cadena vacía y una cadena de
> longitud 1, devolviendo la cadena sin cambios. Explica en el código o en la
> explicación final que esos casos ya están invertidos y detienen la recursión.
> Para el caso recursivo, separa el primer carácter con `charAt(0)`, obtiene el
> resto con `substring(1)`, llama recursivamente a la función sobre ese resto y
> reconstruye el resultado agregando el primer carácter al final del resultado
> recursivo. Así, cada llamada procesa una cadena un carácter más corta.
>
> No uses ciclos, `StringBuilder.reverse()`, arreglos auxiliares ni otra función
> de biblioteca que haga la inversión. Verifica al menos estos casos: `""` →
> `""`, `"a"` → `"a"`, `"sol"` → `"los"`, `"reconocer"` → `"reconocer"` y
> `"a b"` → `"b a"`. Ejecuta las pruebas disponibles o agrega pruebas pequeñas
> siguiendo las convenciones del proyecto. Al terminar, resume los archivos
> modificados y la verificación realizada.