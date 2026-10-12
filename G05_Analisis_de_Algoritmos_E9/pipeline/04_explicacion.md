# Explicación simple: `ElementoMayorMatriz`

## ¿Qué hace?

La clase busca el número más grande que hay dentro de una matriz de enteros.
Por ejemplo, si la matriz contiene `{{2, 8}, {3}}`, el método devuelve `8`.

## ¿Cómo encuentra el mayor?

El método `buscarMayor` primero comprueba que la matriz no sea `null`. Luego
recorre las filas con el ciclo `for` de índice `i`. Dentro de cada fila, otro
ciclo `for`, de índice `j`, visita cada número. Como las filas pueden tener
distintos tamaños, el ciclo interior usa la longitud de la fila actual.

La variable `mayor` guarda el valor más grande visto hasta el momento.
`encontrado` indica si ya se leyó algún número. Cuando aparece el primero, se
usa para inicializar `mayor`; así el método también funciona si todos los
números son negativos. Después, cada valor que supera a `mayor` lo reemplaza.

Las filas `null` se saltean. Si la matriz es `null` o, luego de recorrerla, no
se encontró ningún número, el método lanza `IllegalArgumentException`, porque
no hay un máximo que pueda devolver.

## ¿Qué prueba el `main`?

El `main` llama al método con matrices normales, irregulares, con positivos y
negativos, con todos los valores iguales y con valores límite. También prueba
entradas inválidas, como una matriz `null` o sin elementos. Las funciones
auxiliares comparan el resultado con el esperado y el programa muestra una
tabla con el estado de cada caso.

Si un resultado no coincide, o se lanza una excepción inesperada, el caso se
marca como `FALLO`. Si hay fallos, el programa termina indicando el problema.

## Preguntas para comprobar la comprensión

1. ¿Por qué `mayor` se inicializa con el primer número encontrado en vez de
   comenzar suponiendo que el máximo es cero?
   Por la posible existencia de numeros negativos en la matriz.
   En caso de que la matriz tenga unicamente elementos negativos, va a recorrer todos los elementos y va a retornar que el mayor es 0, elemento que tal vez ni siquiera era parte de la matriz.  
2. Si las filas de la matriz tienen diferentes longitudes, ¿por qué el ciclo
   interno usa `matriz[i].length`?
   Para pasar a la siguiente fila cuando se llegue al ultimo elemento de la fila actual.  