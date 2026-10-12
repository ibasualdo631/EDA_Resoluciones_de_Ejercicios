# Explicación — Insertion Sort y el desplazamiento del `4`

## Estado inicial

El arreglo usa índices que empiezan en cero:

| Índice | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---:|---:|---:|---:|---:|---:|---:|
| Valor | 1 | 2 | 3 | 5 | 4 | 6 | 7 |

Insertion Sort mantiene ordenado el prefijo a la izquierda de `i`. Al llegar a
`i = 4`, el prefijo `{1, 2, 3, 5}` ya está ordenado y el elemento que se va a
insertar es el `4`.

## Traza paso a paso en memoria

1. **Guardar la clave:** se copia `numeros[4]` a la variable local `clave`.
   `clave` vale `4`. Esto es importante porque el algoritmo va a escribir sobre
   `numeros[4]`; conservar la clave en una variable evita perder su valor.
   `j` comienza en `i - 1`, es decir, `j = 3`.

   ```text
   numeros = {1, 2, 3, 5, 4, 6, 7}
   clave = 4
   j = 3
   ```

2. **Comparar con el elemento inmediatamente anterior:** se evalúa
   `numeros[3] > clave`, o sea `5 > 4`. La condición es verdadera. Se copia el
   `5` una posición a la derecha con `numeros[j + 1] = numeros[j]`.

   ```text
   numeros = {1, 2, 3, 5, 5, 6, 7}
   clave = 4
   j = 3
   desplazamientos = 1
   ```

   En este instante hay dos `5` visibles en el arreglo: el original y su copia
   temporal. No se perdió el `4`, porque sigue guardado en `clave`. Esta copia
   del `5` es el único desplazamiento contado en este caso.

3. **Retroceder y comprobar dónde insertar:** se decrementa `j`, que pasa de
   `3` a `2`. Ahora se comprueba `numeros[2] > clave`, es decir, `3 > 4`. Es
   falso, así que el `while` se corta. El `3` ya está correctamente antes del
   `4`; no hace falta seguir comparando con los valores aún más a la izquierda.

4. **Escribir la clave en el hueco:** al salir del ciclo, `j + 1` vale `3`.
   Se ejecuta `numeros[j + 1] = clave`, que coloca el `4` en el índice `3`.

   ```text
   numeros = {1, 2, 3, 4, 5, 6, 7}
   clave = 4
   j = 2
   desplazamientos = 1
   ```

   La escritura final de la clave no incrementa el contador: el contador solo
   registra las copias de elementos existentes hacia la derecha.

5. **Continuar el algoritmo:** las iteraciones siguientes consideran `6` y
   `7`. Como cada uno ya es mayor que el elemento anterior, el ciclo interno
   no desplaza nada más.

## Por qué se corta el ciclo interno

El ciclo interno desplaza elementos solo mientras se cumplan las dos
condiciones: `j >= 0` y `numeros[j] > clave`. Si encuentra un elemento menor o
igual que la clave, ya se alcanzó el lugar correcto: el prefijo anterior está
ordenado, por lo que todos los elementos a la izquierda de ese punto también
son menores o iguales que la clave. Seguir hacia atrás no podría encontrar
otro elemento que debiera moverse.

En este ejemplo, el `3` en el índice `2` produce ese corte anticipado. El
algoritmo evita revisar `1` y `2`, y pasa directamente a insertar el `4` en el
índice `3`.