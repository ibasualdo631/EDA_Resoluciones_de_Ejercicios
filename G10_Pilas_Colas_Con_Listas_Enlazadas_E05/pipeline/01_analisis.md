# Análisis — Ejercicio 5: Historial de navegación con pila

## Objetivo

Implementar un historial web sencillo mediante una pila enlazada que permita visitar páginas, volver a la página anterior, consultar la página actual e imprimir el historial.

## Reglas de negocio

1. **Visitar una página**
   - Cada visita agrega la página al tope de la pila.
   - La página recién visitada pasa a ser la página actual.
   - Las páginas visitadas anteriormente permanecen debajo de ella.

2. **Volver atrás**
   - La página actual solo se retira si existe una página anterior debajo de ella.
   - Al retirarla, la página anterior queda en el tope y pasa a ser la página actual.
   - Si el historial está vacío o contiene una sola página, no hay una página anterior a la cual volver. La operación debe indicarlo y dejar el historial sin cambios.
   - La pila representa esta acción con política **LIFO**: la última página visitada es la primera que se retira. Así se deshace primero la navegación más reciente y se revela la página inmediatamente anterior.

3. **Consultar la página actual**
   - La página actual es la que está en el tope de la pila.
   - La consulta no modifica el historial.
   - Si la pila está vacía, no existe una página actual y el programa debe comunicarlo.

4. **Imprimir el historial**
   - La operación muestra las páginas almacenadas sin modificarlas.
   - Si no hay páginas, debe comunicar que el historial está vacío.
   - El enunciado no especifica un orden de impresión. Una convención coherente con la pila es imprimir desde el tope hacia el fondo (página actual y visitas más recientes primero); si se elige otro orden, debe mantenerse consistentemente.

## Datos y restricciones observables

- Cada elemento del historial representa una página visitada; el enunciado no determina si se almacenará una URL, un nombre u otra representación.
- La estructura solicitada es una pila enlazada.
- Las operaciones de consulta e impresión son de lectura y no deben cambiar el estado de la pila.
- El enunciado no define comportamiento para entradas vacías o inválidas, duplicados, ni un formato específico de salida. Estos detalles quedan fuera de las reglas explícitas del ejercicio, salvo que se concreten durante la implementación.