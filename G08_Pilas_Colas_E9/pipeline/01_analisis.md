# Análisis — Ejercicio 9: desperdicio de espacio en cola simple

## Objetivo

Describir e implementar una cola simple almacenada en un arreglo para mostrar qué sucede con las posiciones que quedan libres al desencolar elementos. El programa debe hacer visible que esas posiciones quedan a la izquierda de `front` y no pueden reutilizarse con la lógica lineal de una cola simple.

## Conceptos y comportamiento esperado

- `front` señala el primer elemento de la cola y `rear`, el último.
- Al encolar, el nuevo elemento se agrega en la posición posterior a `rear`; `rear` avanza.
- Al desencolar, se elimina el elemento señalado por `front`; `front` avanza.
- Las posiciones anteriores a `front` quedan libres, pero la cola simple no vuelve a ellas: las inserciones continúan avanzando hacia el final del arreglo.
- Si `rear` ya está en la última posición, una nueva inserción se rechaza aunque existan espacios libres a la izquierda de `front`. Esta situación se conoce como **falso desbordamiento**: el arreglo aún tiene posiciones desocupadas, pero las reglas de inserción de la cola lineal no permiten aprovecharlas.

## Ejemplo ilustrativo

Con un arreglo de cinco posiciones, se encolan `A`, `B`, `C` y `D`, y luego se desencolan `A` y `B`. Queda:

```text
Índice:  0  1  2  3  4
Arreglo: _  _  C  D  _
                 ↑  ↑
              front rear
```

Se puede encolar `E` en la posición 4. A partir de ahí, un intento de encolar otro elemento falla porque `rear` llegó al final del arreglo, aunque las posiciones 0 y 1 sigan libres. El programa debe mostrar esta diferencia entre espacio libre y espacio reutilizable bajo la implementación de una cola simple.

## Requerimientos identificados

1. Implementar una cola simple con arreglo.
2. Permitir y demostrar operaciones `enqueue` y `dequeue`.
3. Mostrar el contenido del arreglo después de varias operaciones, incluyendo las posiciones vacías.
4. Hacer explícito que las posiciones libres al inicio (a la izquierda de `front`) no se reutilizan.
5. Mostrar el intento de inserción que produce el falso desbordamiento, distinguiéndolo de que el arreglo esté realmente lleno.

## Alcance y supuestos

- El ejemplo usa capacidad fija de cinco posiciones únicamente para que el comportamiento sea fácil de observar; la capacidad puede ajustarse si el enunciado o el entorno del ejercicio lo requiere.
- Se analiza una cola lineal simple, no una cola circular. La reutilización de posiciones mediante circularidad queda fuera del objetivo central.
- La salida debe permitir identificar índices, elementos, posiciones vacías y el estado de `front` y `rear`; no se prescribe un lenguaje de programación específico.

## Prompt inicial

> Implementá una cola simple (lineal) usando un arreglo de capacidad fija y demostrala con varias operaciones `enqueue` y `dequeue`. Después de cada operación, mostrá el arreglo completo —incluidos los espacios vacíos— y los valores o índices de `front` y `rear`. Organizá las operaciones para que algunos `dequeue` dejen posiciones libres al inicio, a la izquierda de `front`, y luego se siga encolando hasta que `rear` llegue al final del arreglo. Intentá entonces encolar un elemento más y explicá por qué se produce un falso desbordamiento: todavía hay posiciones vacías en el arreglo, pero la cola simple no puede reutilizarlas porque solo inserta después de `rear`. Diferenciá esta situación de un desbordamiento real y no implementes una cola circular.