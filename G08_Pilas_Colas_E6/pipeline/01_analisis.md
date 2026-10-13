# Análisis — Ejercicio 6: cola simple de enteros basada en arreglos

## Objetivo

Implementar una clase `ColaEnteros` que almacene números enteros en un arreglo y se comporte como una cola FIFO (*first in, first out*): el primer elemento agregado es el primero que se elimina. La consigna solicita las operaciones `enqueue`, `dequeue`, `front`, `isEmpty`, `isFull` y `size`.

## Índices `front` y `rear`

- `front` indica la posición del elemento que está al frente de la cola, es decir, el próximo que se eliminará.
- `rear` indica la posición del último elemento agregado, es decir, el final de la cola.
- Cuando la cola no está vacía, los elementos válidos ocupan las posiciones entre ambos índices, inclusive. En una cola lineal sin circularidad, esto presupone que no hay huecos dentro de ese tramo.

Con la representación convencional de arreglo lineal y posiciones desde cero:

1. **Inicialización:** `front = 0` y `rear = -1` representan una cola vacía.
2. **Agregar (`enqueue`):** si hay capacidad, se incrementa `rear` y se almacena el nuevo entero en `arreglo[rear]`. `front` no cambia, salvo al insertar en una cola vacía si se eligió otra convención de índices.
3. **Eliminar (`dequeue`):** si no está vacía, se obtiene el valor de `arreglo[front]` y se incrementa `front`. `rear` no cambia mientras queden elementos. Al eliminar el último, la implementación debe restablecer el estado vacío o mantener otra representación coherente que permita volver a insertar.
4. **Consultar el frente (`front`):** devuelve el elemento en `arreglo[front]` sin eliminarlo ni modificar los índices.

`rear` no es necesariamente la cantidad de elementos: puede conservar un índice mayor aunque `front` también haya avanzado. Por eso `size` debe informar el número de elementos presentes, no la capacidad ni el valor aislado de uno de los índices. En una implementación lineal con índices válidos y una cola no vacía, la cantidad es `rear - front + 1`; si se restablecen los índices al vaciar, el tamaño vacío es cero.

### Límite de una cola lineal

Una cola lineal simple no reutiliza automáticamente las posiciones liberadas antes de `front`. En consecuencia, puede llegar al final físico del arreglo y reportar llena aunque existan celdas libres al principio. Reutilizar esas celdas requeriría desplazar elementos o implementar una cola circular; ninguna de esas mejoras está indicada en la consigna. El prompt propuesto conserva la cola lineal y debe evitar presentarla como circular.

## Requerimientos funcionales

- Almacenar únicamente valores enteros en un arreglo de capacidad fija.
- `enqueue(valor)`: agregar al final respetando FIFO.
- `dequeue()`: quitar y devolver el elemento del frente.
- `front()`: consultar y devolver el elemento del frente sin quitarlo.
- `isEmpty()`: indicar si no hay elementos.
- `isFull()`: indicar si la representación ya no permite agregar otro elemento.
- `size()`: devolver la cantidad actual de elementos.

## Decisiones y casos límite que el prompt debe hacer explícitos

La imagen no fija el lenguaje, la capacidad del arreglo, las firmas exactas ni cómo comunicar errores. Para que la solución generada sea concreta, el prompt formal siguiente propone:

- Usar el lenguaje y las convenciones ya empleados en el curso/proyecto; si no hay contexto disponible, pedir que se declare el lenguaje en lugar de inventar dependencias.
- Recibir la capacidad como parámetro de construcción y rechazar una capacidad no positiva.
- Mantener un arreglo lineal fijo, sin desplazamiento de elementos ni comportamiento circular.
- Definir qué pasa al ejecutar `dequeue()` o `front()` con la cola vacía y al ejecutar `enqueue()` con la cola llena: informar el error mediante el mecanismo idiomático del lenguaje, sin devolver un valor centinela que pueda confundirse con un entero válido.
- Comprobar que eliminar el último elemento deja la cola en un estado vacío correcto y permite volver a insertar mientras exista capacidad disponible.

## Prompt inicial formal

> Implementa una clase `ColaEnteros` que represente una cola FIFO de enteros utilizando un arreglo de capacidad fija. Sigue el lenguaje, el estilo y las convenciones del proyecto o del curso; no agregues dependencias externas.
>
> La clase debe ofrecer las operaciones `enqueue(valor)`, `dequeue()`, `front()`, `isEmpty()`, `isFull()` y `size()`. `enqueue` agrega al final; `dequeue` devuelve y elimina el elemento más antiguo; `front` devuelve el elemento más antiguo sin eliminarlo; los predicados informan si la cola está vacía o llena, y `size` devuelve la cantidad de elementos actualmente almacenados.
>
> Usa los índices `front` y `rear` para señalar, respectivamente, el primer elemento pendiente de eliminación y el último elemento agregado. Explica cómo se inicializan y cómo cambian al insertar y eliminar elementos. Mantén coherentes ambos índices y el tamaño al pasar de vacía a no vacía, al quitar el último elemento y al insertar nuevamente.
>
> Implementa una cola lineal simple: no la conviertas en circular ni desplaces los elementos para reutilizar posiciones liberadas. Define una capacidad positiva en la construcción. Si la cola está vacía, `dequeue` y `front` deben comunicar claramente el error; si está llena, `enqueue` también debe comunicarlo claramente. Usa el mecanismo habitual del lenguaje para estos errores, no valores centinela.
>
> Incluye una explicación breve de la representación y ejemplos o pruebas que verifiquen FIFO, consulta no destructiva del frente, tamaño, estados vacío/lleno, error al operar en los límites y reinserción después de vaciar la cola. No implementes funcionalidades ajenas a la consigna.