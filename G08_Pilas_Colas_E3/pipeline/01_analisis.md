# Análisis — Ejercicio 3: cola simple de enteros basada en arreglos

## Objetivo

Implementar una cola FIFO de enteros usando un arreglo de capacidad fija. La inserción se realiza al final de la cola y la extracción se realiza desde el frente.

## Representación y rol de los índices

Se asume una cola lineal (no circular) y la convención de que `front` señala el primer elemento pendiente de extraer, mientras que `rear` señala la posición del último elemento encolado.

- Al inicio, `front = 0` y `rear = -1`; la cola está vacía.
- Al encolar un entero, se comprueba que haya espacio y que `rear` no haya llegado al último índice del arreglo. Luego se incrementa `rear` y se guarda el entero en `arreglo[rear]`.
- Al desencolar, se comprueba que la cola no esté vacía. Se obtiene `arreglo[front]` y se incrementa `front`; `rear` no cambia.
- La cola está vacía cuando `front > rear`. Si se extrae el último elemento, se pueden restablecer ambos índices a `front = 0` y `rear = -1` para reutilizar el arreglo desde el principio en la siguiente operación.

Así, `rear` avanza al insertar y `front` avanza al extraer. Ninguna operación desplaza los demás elementos: encolar y desencolar tienen costo O(1).

## Límites y casos de borde

- **Desbordamiento:** en una cola lineal no circular, no se puede encolar cuando `rear` alcanzó el último índice, aunque haya posiciones libres antes de `front`. No se compacta ni se reutiliza ese espacio mientras la cola no quede completamente vacía.
- **Subdesbordamiento:** no se puede desencolar si la cola está vacía.
- La capacidad debe ser positiva y quedar definida al crear la cola.
- Al vaciarse, se reinician ambos índices; esto permite volver a usar todo el arreglo sin cambiar el comportamiento FIFO.

## Alcance asumido

La consigna escrita solicita una cola simple de enteros basada en arreglos. Por eso se asume un arreglo de tamaño fijo y una implementación lineal no circular. La imagen adjunta, en cambio, describe validación de paréntesis con una pila; no se toma como especificación de este ejercicio.

## Prompt inicial

> Implementá en Java una clase `ColaEnteros` que represente una cola FIFO de enteros usando únicamente un arreglo de tamaño fijo, sin colecciones nativas. Incluí un constructor que reciba la capacidad, los índices `front` y `rear`, y métodos para encolar, desencolar, consultar si está vacía y consultar si está llena.
>
> Usá la convención `front = 0` y `rear = -1` para la cola vacía: `front` señala el próximo elemento a extraer y `rear` señala el último elemento encolado. Al encolar, verificá primero el desbordamiento, incrementá `rear` y guardá el valor. Al desencolar, verificá primero el subdesbordamiento, devolvé el valor de `arreglo[front]` e incrementá `front`. Si se extrae el último elemento, restablecé los índices a `front = 0` y `rear = -1`. Rechazá capacidades no positivas y señalá explícitamente los intentos de encolar en una cola llena o desencolar una vacía.
>
> Agregá un `main` breve que pruebe varias inserciones y extracciones en orden FIFO, la extracción hasta vaciar la cola y la reutilización del arreglo después de vaciarla. Mantené O(1) para encolar y desencolar, sin desplazar elementos.