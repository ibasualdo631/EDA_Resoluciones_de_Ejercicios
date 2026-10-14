# Explicación — Verificador de paréntesis balanceados

## Idea principal

El programa lee una expresión y comprueba únicamente si los paréntesis redondos `(` y `)` están correctamente emparejados y anidados. No verifica si la expresión es una operación matemática válida.

Para recordar qué aperturas siguen esperando un cierre, `estaBalanceada` usa una pila enlazada. El tope de la pila es la apertura más reciente que todavía no fue cerrada. Por eso, cuando aparece `)`, se empareja con esa apertura: este orden es LIFO (*Last In, First Out*, último en entrar, primero en salir).

## Cómo se representa la pila en el código

Dentro del único método validador se define la clase local `Nodo`. Cada nodo contiene:

- `dato`: el carácter de apertura almacenado.
- `siguiente`: la referencia al nodo que antes era el tope.

La variable `tope` apunta al elemento más reciente. Cuando vale `null`, la pila está vacía.

- **Apilar `(`:** `tope = new Nodo(caracter, tope);` crea un nodo nuevo sobre el tope anterior.
- **Desapilar al leer `)`:** `tope = tope.siguiente;` descarta el nodo superior y deja arriba la apertura anterior.

Como el nodo nuevo enlaza directamente al tope previo, ambas operaciones se realizan en tiempo constante `O(1)`.

## Recorrido paso a paso: expresión válida

Entrada: `((a + b) * c)`

En la columna “pila”, el elemento de la izquierda es el tope. Los caracteres que no son paréntesis se ignoran.

| Paso | Carácter | Acción | Pila después de la acción |
|---:|:---:|---|---|
| 1 | `(` | Apilar la apertura | `(` |
| 2 | `(` | Apilar otra apertura sobre la anterior | `(`, `(` |
| 3 | `a` | Ignorar | `(`, `(` |
| 4 | `+` | Ignorar | `(`, `(` |
| 5 | `b` | Ignorar | `(`, `(` |
| 6 | `)` | Hay apertura en el tope; desapilarla | `(` |
| 7 | `*` | Ignorar | `(` |
| 8 | `c` | Ignorar | `(` |
| 9 | `)` | Hay apertura en el tope; desapilarla | vacía |

El recorrido termina sin encontrar un cierre sin pareja y `tope` queda en `null`. El método devuelve `true` y el programa imprime `Paréntesis balanceados.`.

Fíjate en el anidamiento: el primer cierre empareja la segunda apertura, que fue la última en apilarse. El cierre final empareja la apertura exterior.

## Recorrido paso a paso: expresión inválida

Entrada: `())(`

| Paso | Carácter | Acción | Pila después de la acción |
|---:|:---:|---|---|
| 1 | `(` | Apilar la apertura | `(` |
| 2 | `)` | Hay apertura en el tope; desapilarla | vacía |
| 3 | `)` | La pila está vacía: este cierre no tiene apertura previa | no aplica |

En el tercer paso, `tope == null`, por lo que el método devuelve `false` de inmediato. El último `(` ni siquiera necesita procesarse: ningún carácter posterior puede corregir que ya apareció un cierre sin apertura disponible. El programa imprime `Paréntesis no balanceados.`.

Una entrada puede tener igual cantidad total de aperturas y cierres y aun así ser inválida si el orden es incorrecto. Por ejemplo, en `())(` hay dos de cada tipo, pero el tercer carácter cierra sin pareja.

## El control al terminar

Si no se detectó un cierre inválido, el método retorna `tope == null`:

- `true`: todas las aperturas fueron desapiladas; la expresión está balanceada.
- `false`: queda uno o más nodos; esas aperturas nunca encontraron cierre.

Por ejemplo, `(2 + 3` termina con una apertura pendiente y, por lo tanto, se rechaza.

## Entrada y salida del programa

`main` lee una línea desde la entrada estándar con `BufferedReader`, llama a `estaBalanceada` y muestra uno de dos mensajes. Si no se recibió una línea (`readLine()` devuelve `null`), informa el problema por `System.err` en vez de tratarlo como una expresión vacía.

La cadena vacía, en cambio, sí es una línea recibida. Como no contiene delimitadores, el validador devuelve `true` de acuerdo con el alcance del ejercicio: comprobar balance, no exigir contenido matemático.

## Complejidad

Para una entrada de `n` caracteres, se inspecciona cada carácter una sola vez: tiempo `O(n)`. La pila puede guardar hasta `n` aperturas si todas aparecen antes de cualquier cierre: espacio adicional `O(n)`.