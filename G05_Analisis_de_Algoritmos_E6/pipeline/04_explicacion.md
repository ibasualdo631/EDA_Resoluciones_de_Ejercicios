# Explicación — comparar dos vectores

## ¿Qué hace el programa?

La clase `CompararVectores` tiene un método que recibe dos arreglos de enteros y devuelve `true` si tienen el mismo tamaño y los mismos valores en las mismas posiciones. Si encuentra alguna diferencia, devuelve `false`.

## El método `compararVectores`

1. **Comprueba si algún argumento es `null`.** Si sucede, lanza `IllegalArgumentException`. Así evita intentar leer la longitud de una referencia que no apunta a un arreglo.
2. **Compara las longitudes.** Si los tamaños son distintos, los arreglos no pueden ser iguales y el método devuelve `false` sin comparar los elementos.
3. **Compara posición por posición.** El ciclo `for` usa `i` como índice, empezando en cero. Si `vectorA[i]` y `vectorB[i]` son distintos, devuelve `false` de inmediato.
4. **Si el ciclo termina**, todos los elementos coincidieron y devuelve `true`. Si ambos arreglos están vacíos, el ciclo no se ejecuta y también devuelve `true`.

La comparación termina anticipadamente porque una sola diferencia alcanza para probar que los arreglos no son iguales. En cambio, para confirmar que sí son iguales, hay que revisar todas las posiciones.

## Pruebas del `main`

El método `main` ejecuta ejemplos automáticamente:

- `verificarNull` comprueba que se lance `IllegalArgumentException` cuando A, B o ambos son `null`.
- `verificar` compara el resultado obtenido con el esperado. Si no coinciden, lanza `AssertionError`; de lo contrario, informa que la prueba pasó.
- Los casos restantes prueban arreglos iguales, distintos, vacíos y con longitudes diferentes.

## Complejidad

- **Mejor caso:** `O(1)`, si alguna referencia es `null`, las longitudes son distintas o el primer elemento ya difiere.
- **Peor caso:** `O(n)`, si los arreglos son iguales o su única diferencia está al final.
- **Espacio adicional:** `O(1)`, porque el método solo usa el índice del ciclo y no crea arreglos auxiliares.

## Preguntas de comprensión

1. Si los vectores tienen la misma longitud, pero difieren en el índice `1`, ¿qué devuelve el método y por qué no sigue comparando los índices siguientes?
El método devuelve de forma directa false, al igual que pasa si encuentra una diferencia en cualquiera de las demás posiciones, o lo mismo que ocurre en caso de que antes de ponerse a comparar se tope con el hecho de que los dos arreglos son de distinta longitud.
2. ¿Por qué `[1, 2, 3]` y `[1, 2, 3, 4, 5]` son distintos aunque los primeros tres valores coincidan?
Los vectores son distintos por que tienen diferente longitud vectorA.length != vectorB.length