# Análisis del Ejercicio 9: Palíndromo recursivo

## 1. Objetivo
Determinar si una palabra es palíndromo mediante una solución recursiva.

Una palabra es palíndromo cuando se lee igual de izquierda a derecha que de derecha a izquierda. Ejemplos: `radar`, `reconocer`, `oso`.

## 2. Idea central de la recursión
La solución recursiva se basa en comparar los extremos de la palabra:

- Si la primera y la última letra son distintas, la palabra no es palíndromo.
- Si son iguales, se elimina ese par de extremos y se analiza el texto interior.
- Esto reduce el problema a una palabra más pequeña en cada llamada recursiva.

La recursión termina cuando la palabra queda vacía o con un solo carácter, porque en esos casos ya es claramente un palíndromo.

## 3. Reducción comprobando extremos
La reducción es la siguiente:

1. Se toma la palabra original.
2. Se compara el primer y último carácter.
3. Si coinciden, se llama recursivamente a la subcadena que queda entre ambos extremos.
4. Si no coinciden, la función devuelve `False`.

### Ejemplo
Palabra: `reconocer`

- `r` == `r` → seguimos con `econoce`
- `e` == `e` → seguimos con `conoc`
- `c` == `c` → seguimos con `ono`
- `o` == `o` → seguimos con `n`
- longitud 1 → caso base → `True`

La clave es que en cada paso se reduce el problema eliminando los extremos y dejando solo el interior.

## 4. Caso base
El caso base es:

- si la palabra tiene longitud 0;
- o si la palabra tiene longitud 1.

En ambos casos, la palabra es palíndromo por definición.

## 5. Condiciones de decisión
La función debe resolver estas preguntas:

- ¿La palabra tiene 0 o 1 caracteres? → `True`
- ¿El primer y último carácter son distintos? → `False`
- ¿Son iguales? → repetir sobre el interior de la palabra

## 6. Prompt base sugerido
Escribí una función recursiva en Python llamada `esPalindromo` que determine si una palabra es palíndromo.

Debe hacer lo siguiente:
- si la palabra tiene 0 o 1 caracteres, devolver `True`;
- comparar el primer y el último carácter;
- si son distintos, devolver `False`;
- si son iguales, llamar recursivamente a la función con la palabra sin los extremos;
- reducir el problema eliminando los extremos en cada llamada;
- usar como caso base que una palabra con longitud 0 o 1 es palíndromo.

Explicá por qué funciona:
- porque cada llamada compara los extremos;
- si coinciden, el problema pasa al interior de la palabra;
- así el tamaño del problema disminuye hasta llegar a un caso trivial.

Incluí ejemplos de prueba:
- `"radar"` → `True`
- `"reconocer"` → `True`
- `"hola"` → `False`

Muestra el código completo y explica brevemente cada parte.

## 7. Conclusión
El ejercicio trabaja con la idea de “reducción del problema”: se compara el primer y último carácter y se descarta lo ya verificado. La recursión sigue hasta llegar al caso base, que resuelve la situación final de manera directa. Este patrón es típico de la recursividad: dividir un problema en una versión más pequeña del mismo hasta que se torne trivial.
