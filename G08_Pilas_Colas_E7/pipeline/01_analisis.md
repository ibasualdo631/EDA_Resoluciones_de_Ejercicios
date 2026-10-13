# Análisis — Ejercicio 7: Sistema de turnos para atención

## Objetivo

Representar la espera de personas en una oficina mediante una cola. Cada persona que llega obtiene un turno y se incorpora al final; la atención y la consulta del próximo turno se realizan desde el frente.

## Justificación conceptual: FIFO, no LIFO

El orden de atención debe respetar el orden de llegada: quien lleva más tiempo esperando debe ser atendido primero. Esa regla corresponde a **FIFO** (*First In, First Out*: primero en entrar, primero en salir), que es precisamente el comportamiento de una cola.

Una pila aplica **LIFO** (*Last In, First Out*: último en entrar, primero en salir). Si se usara una pila, la persona recién llegada quedaría por encima de quienes llegaron antes y sería atendida primero. Cada nueva llegada podría postergar a las anteriores, contradiciendo la finalidad de asignar turnos y pudiendo causar esperas indefinidas. Por eso LIFO no modela el orden justo y cronológico de este caso.

En términos de operaciones:

- **Agregar persona:** insertar al final de la cola (encolar).
- **Atender siguiente persona:** retirar y devolver el elemento del frente (desencolar).
- **Consultar quién está primero:** observar el frente sin retirarlo (consultar/peek).

## Requerimientos funcionales

1. Incorporar a la cola a cada persona que llega, conservando el orden de llegada.
2. Atender a la persona que ocupa el frente y quitarla de la cola.
3. Consultar quién está en el frente sin modificar el contenido ni el orden.
4. Informar de forma clara si se intenta atender o consultar cuando no hay personas en espera.

## Reglas y casos límite

- Una persona recién agregada nunca debe adelantarse a las que ya estaban esperando.
- Atender modifica la cola; consultar el frente no la modifica.
- Si la cola está vacía, las operaciones de atención y consulta deben manejarse sin retirar ni inventar elementos.
- El enunciado no especifica lenguaje, interfaz, formato de salida ni cómo identificar turnos repetidos. No se asumen detalles de implementación; basta con almacenar la información identificadora necesaria para distinguir a las personas.

## Prompt inicial

> Implementá un sistema de turnos para la atención de una oficina usando una **cola FIFO**. Cada persona que llega debe agregarse al final de la cola. El sistema debe permitir: (1) agregar una persona, (2) atender y retirar a la persona que está primero, y (3) consultar quién está primero sin retirarla. Mantené el orden de llegada en todas las operaciones y manejá claramente los casos en que se intenta atender o consultar con la cola vacía. Explicá conceptualmente por qué corresponde usar FIFO y no una pila LIFO, incluyendo qué comportamiento incorrecto produciría LIFO en este problema. Usá el lenguaje y las convenciones ya establecidos por el proyecto; si no hay uno definido, indicá esa suposición antes de implementar.