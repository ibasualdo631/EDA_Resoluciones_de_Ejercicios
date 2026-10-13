# Análisis — Ejercicio 1: Pila de enteros

## Objetivo

Implementar una clase `PilaEnteros` que almacene números enteros en un arreglo de capacidad fija y ofrezca las operaciones `push`, `pop`, `peek`, `isEmpty`, `isFull` y `size`. La pila debe seguir el principio LIFO: el último elemento insertado es el primero en salir.

## Requerimientos funcionales

- `push(valor)`: insertar un entero en el tope.
- `pop()`: quitar y devolver el elemento del tope.
- `peek()`: devolver el elemento del tope sin quitarlo.
- `isEmpty()`: indicar si no hay elementos.
- `isFull()`: indicar si se alcanzó la capacidad del arreglo.
- `size()`: devolver cuántos elementos hay actualmente.

## Representación y uso de `top`

- El arreglo tiene una capacidad fija `capacidad`; sus índices válidos van de `0` a `capacidad - 1`.
- `top` representa el índice del último elemento ocupado, no la cantidad de elementos.
- La pila vacía se representa con `top = -1`. Por ello, el tamaño siempre es `top + 1`.
- En `push`, primero se comprueba que la pila no esté llena; luego se incrementa `top` y se escribe el valor en `arreglo[top]`.
- En `pop`, primero se comprueba que la pila no esté vacía; se guarda y devuelve `arreglo[top]`, y después se decrementa `top`.
- `peek` consulta `arreglo[top]` sin modificar `top`.
- `isEmpty` es verdadero cuando `top == -1`; `isFull` cuando `top == capacidad - 1`.

## Casos límite y errores

- **Overflow (desbordamiento):** intentar `push` cuando `top == capacidad - 1`. La operación debe fallar de forma explícita y no modificar ni el arreglo ni `top`.
- **Underflow (subdesbordamiento):** intentar `pop` o `peek` cuando `top == -1`. La operación debe fallar de forma explícita y no modificar el estado.
- Una pila recién creada debe estar vacía (`top = -1`, `size() = 0`); `isFull()` solo será verdadero al ocupar todas las posiciones.
- La capacidad debe ser positiva para que exista al menos una posición utilizable. La consigna no define el mecanismo concreto de reporte de errores ni el lenguaje de implementación; el plan debe respetar las convenciones existentes del proyecto o dejar esa decisión explícita.

## Criterios de aceptación

1. Las operaciones mantienen el orden LIFO.
2. `top`, `size`, `isEmpty` e `isFull` permanecen consistentes tras inserciones y extracciones.
3. Overflow y underflow se detectan antes de alterar el estado.
4. `peek` no elimina el elemento y `pop` sí lo elimina.
5. Los casos de pila vacía, pila llena, capacidad mínima y reutilización tras un `pop` quedan contemplados en el plan de pruebas.

## Prompt inicial para el planificador

> Elaborá un plan técnico para implementar el Ejercicio 1: una clase `PilaEnteros` respaldada por un arreglo de tamaño fijo. Debe ofrecer `push`, `pop`, `peek`, `isEmpty`, `isFull` y `size`, y respetar el comportamiento LIFO.
>
> Usá `top` como índice del último elemento ocupado: inicia en `-1`, la pila está vacía cuando `top == -1` y su tamaño es `top + 1`. Para `push`, verificá primero Overflow (`top == capacidad - 1`); solo si hay espacio incrementá `top` y almacená el valor. Para `pop` y `peek`, verificá primero Underflow (`top == -1`); `pop` devuelve y elimina el elemento decrementando `top`, mientras que `peek` solo lo consulta. Los errores deben reportarse explícitamente y las operaciones inválidas no deben cambiar el estado.
>
> Antes de proponer archivos o sintaxis, identificá el lenguaje y las convenciones del repositorio; no inventes requisitos que no estén presentes. Si el proyecto no establece cómo reportar errores o cómo configurar la capacidad fija, señalá esas decisiones en el plan. Incluí alcance, diseño de la representación, pasos de implementación y pruebas para pila vacía, pila llena, Overflow, Underflow, `peek` sin mutación, secuencia LIFO, capacidad mínima y reutilización de espacio después de un `pop`. No escribas la implementación todavía.