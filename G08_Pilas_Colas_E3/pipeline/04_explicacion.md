# Explicación didáctica — `ColaEnteros`

## ¿Por qué la cola es FIFO?

FIFO significa **First In, First Out**: el primer elemento que entra es el
primero que sale. En esta implementación, el orden se garantiza usando dos
índices:

- `rear` apunta al último elemento agregado.
- `front` apunta al próximo elemento que se va a retirar.

Al llamar a `encolar(valor)`, `rear` avanza y el valor nuevo se guarda al final
de la secuencia. Al llamar a `desencolar()`, se devuelve `elementos[front]` y
`front` avanza. Por eso, las extracciones siguen el mismo orden cronológico que
las inserciones.

Por ejemplo, si se encolan `10`, `20` y `30`, el frente queda en `10` y el final
en `30`. Las siguientes llamadas a `desencolar()` devuelven `10`, después `20`
y finalmente `30`. La cola no busca el valor menor ni el mayor: respeta el orden
de llegada.

## Cómo representa su estado

La clase usa un arreglo `int[]` de tamaño fijo, sin colecciones. `front` y
`rear` empiezan en `0` y `-1`, respectivamente. Ese estado representa una cola
vacía; por eso `estaVacia()` comprueba `rear == -1`.

Mientras haya elementos, los pendientes ocupan las posiciones desde `front`
hasta `rear`, inclusive. Encolar incrementa `rear` y escribe allí. Desencolar
lee `elementos[front]` y luego incrementa `front`. Cuando se retira el último
elemento, los dos índices vuelven a `front = 0` y `rear = -1`, dejando la cola
lista para reutilizar el arreglo desde el principio.

## Limitación física del diseño lineal

Los índices solo avanzan hacia la derecha; no vuelven circularmente al inicio
cuando llegan al final del arreglo. Por eso, los espacios liberados por
desencolar quedan inutilizados temporalmente.

Con capacidad `3`, por ejemplo:

1. Encolar `10`, `20` y `30` ocupa los índices `0`, `1` y `2`.
2. Desencolar `10` y `20` deja `front = 2`, `rear = 2`. Aunque los índices `0`
   y `1` ya no contienen elementos pendientes, no se reutilizan.
3. Como `rear` ya está en el último índice, `estaLlena()` da `true` y otro
   `encolar()` falla, aunque lógicamente solo quede un elemento en la cola.
4. Recién al desencolar también `30`, la implementación reinicia ambos índices
   y permite volver a usar las posiciones desde el principio.

En consecuencia, `estaLlena()` significa que **no quedan posiciones físicas
después de `rear`**, no necesariamente que todos los elementos del arreglo
estén ocupados en ese momento. Esa es la limitación principal de una cola lineal
con arreglo fijo: puede rechazar una inserción por llegar al final aun cuando
haya huecos libres antes de `front`.

Una cola circular evita ese desperdicio haciendo que los índices vuelvan al
inicio del arreglo (con aritmética modular) y reutilizando los huecos a medida
que se liberan. Esta clase no implementa ese comportamiento.

## Costos y controles

`encolar()` y `desencolar()` ejecutan una cantidad constante de operaciones y
no desplazan los demás elementos, así que ambas operaciones son **O(1)** en
tiempo. El arreglo ocupa **O(n)** espacio para una capacidad `n`.

Antes de insertar, la clase verifica el desbordamiento; antes de extraer,
verifica el subdesbordamiento. Los intentos inválidos generan
`IllegalStateException`, en vez de leer o escribir fuera de los límites del
arreglo. El constructor también rechaza capacidades no positivas.