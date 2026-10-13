# Análisis — Ejercicio 8: Cola de impresión

## Objetivo

Implementar una cola de impresión que conserve el orden de llegada de los documentos. Cada documento tiene un nombre y una cantidad de páginas.

## Cómo la cola representa el orden

La estructura adecuada es una cola FIFO (*First In, First Out*): el primer documento que entra es el primero que sale para imprimirse. Al agregar un documento, se encola al final; al imprimir, se toma y se retira el documento del frente. Así, el orden de salida coincide con el orden de agregado.

Por ejemplo, si se agregan `Informe (3 páginas)`, `Apuntes (1 página)` y `TP (5 páginas)`, la impresión debe ser `Informe`, `Apuntes`, `TP`. La cantidad de páginas es un dato de cada documento; no cambia su posición, no da prioridad y no justifica reordenar la cola.

## Requerimientos funcionales

- Representar cada documento con, al menos, su nombre y su cantidad de páginas.
- Permitir agregar documentos al final de la cola.
- Permitir imprimir o retirar el documento que está al frente.
- Mantener el orden de llegada durante toda la secuencia de operaciones.
- Informar de manera clara cuando se intenta imprimir y no hay documentos pendientes.

## Reglas y límites

- La política es estrictamente FIFO: no se prioriza por nombre, cantidad de páginas ni orden alfabético.
- Una cola vacía no tiene un documento para imprimir; la operación debe manejar ese caso sin quitar elementos inexistentes.
- No se especifican interfaz, lenguaje, formato de entrada/salida ni comportamiento de impresión física. Se deben respetar las convenciones del proyecto si existen; no hace falta simular la impresión página por página para demostrar el orden.

## Prompt inicial

> Implementá un sistema de cola de impresión para documentos. Cada documento debe almacenar un nombre y una cantidad de páginas. Usá una cola FIFO: los documentos se agregan al final y se imprimen/remueven desde el frente, de modo que siempre se respete exactamente el orden en que fueron agregados, sin priorizar por cantidad de páginas. Incluí una forma de agregar documentos y de procesar el siguiente documento, manejá claramente el caso de cola vacía y mostrá el orden de impresión en un ejemplo con varios documentos. Seguí el lenguaje y las convenciones del proyecto; si no hay un lenguaje definido, indicá cuál asumís.