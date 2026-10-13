# Cola circular: explicación sencilla

Una cola funciona como una fila: el primero que entra es el primero que sale
(FIFO). Esta implementación guarda los valores en un arreglo de tamaño fijo.

## ¿Por qué se usa módulo?

Imaginá que las posiciones del arreglo forman una pista circular. Cuando un
índice llega al final, vuelve al principio. El operador `%` permite hacer ese
“rebote” sin tener que comprobarlo con un `if`.

Por ejemplo, si hay 5 posiciones (numeradas del 0 al 4) y `rear` está en la
posición 4, al avanzar se calcula:

```text
(4 + 1) % 5 = 0
```

Así, el siguiente lugar vuelve a ser la posición 0. No hace falta entender más
matemática: en esta cola, el módulo sirve para mantener `front` y `rear` dentro
del arreglo y reutilizar las posiciones que quedaron libres.

## Cómo se organiza

- `elementos`: el arreglo que guarda los valores.
- `front`: posición del próximo valor que se va a retirar.
- `rear`: posición libre donde se guardará el próximo valor.
- `count`: cantidad de valores que hay actualmente en la cola.

`count` permite diferenciar claramente una cola vacía (`count == 0`) de una
llena (`count == elementos.length`), incluso cuando los dos índices coinciden.

## Preguntas y respuestas sobre la implementación

**¿Qué hace `encolar`?**  
Primero comprueba que la cola no esté llena. Guarda el valor en `rear`, avanza
`rear` con `(rear + 1) % elementos.length` y suma uno a `count`.

**¿Qué hace `desencolar`?**  
Comprueba que haya elementos, guarda el valor señalado por `front`, avanza
`front` con el mismo mecanismo circular, resta uno a `count` y devuelve el
valor. El arreglo no se desplaza.

**¿La consulta del frente elimina el elemento?**  
No. `consultarFrente` devuelve el valor de `front` sin mover el índice ni
modificar `count`.

**¿Qué pasa si intento sacar de una cola vacía?**  
`desencolar` y `consultarFrente` lanzan `NoSuchElementException`. Como la
comprobación ocurre antes de cambiar los índices o el contador, el estado no se
altera.

**¿Qué pasa si intento insertar cuando está llena?**  
`encolar` lanza `IllegalStateException` antes de escribir en el arreglo, así
que no sobrescribe ningún valor.

**¿Por qué no alcanza con mirar si `front` y `rear` coinciden?**  
Pueden coincidir tanto cuando la cola está vacía como cuando dio una vuelta
completa y está llena. `count` distingue esos dos casos.

**¿Se pueden reutilizar las posiciones que ya se desocuparon?**  
Sí. Cuando un índice avanza hasta el final, el módulo lo lleva al principio.
Por eso, las inserciones siguientes pueden ocupar las celdas liberadas por las
extracciones anteriores.

**¿Qué ocurre con una capacidad de 1?**  
El arreglo tiene una sola posición y cada avance vuelve a esa misma posición.
`count` indica si está ocupada o libre; por eso también se puede insertar,
retirar y volver a usar correctamente.

**¿Por qué las operaciones son rápidas?**  
Cada operación revisa o cambia una cantidad fija de datos: no recorre ni
desplaza el arreglo. Por eso `encolar`, `desencolar` y `consultarFrente` toman
tiempo constante, O(1).

## Cómo comprobarlo

El `main` demuestra el rebote y la reutilización, y también prueba los errores,
la capacidad unitaria y muchas operaciones comparándolas con una `ArrayDeque`.
Se puede ejecutar desde la carpeta que contiene `ColaCircular.java`:

```text
javac ColaCircular.java
java ColaCircular
```

Al final, debería aparecer `Todas las pruebas de ColaCircular finalizaron
correctamente.`