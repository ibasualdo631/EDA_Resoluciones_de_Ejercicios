# Análisis de requerimientos — Ejercicio 10: Lista doblemente enlazada simple

## Objetivo

Implementar una lista doblemente enlazada genérica, capaz de almacenar elementos de un tipo `T` y recorrer sus nodos tanto desde el inicio hasta el final como en sentido inverso.

## Requerimientos funcionales

1. **Insertar al inicio:** agregar un elemento antes del primero. La nueva referencia de inicio debe apuntar al nodo agregado.
2. **Insertar al final:** agregar un elemento después del último. La nueva referencia de fin debe apuntar al nodo agregado.
3. **Eliminar un elemento:** localizar y quitar de la lista un nodo cuyo dato coincida con el elemento solicitado. Al desvincularlo, deben actualizarse las referencias de sus nodos vecinos y, si corresponde, los extremos de la lista.
4. **Imprimir hacia adelante:** mostrar los elementos desde el inicio hasta el final, avanzando mediante la referencia al siguiente nodo.
5. **Imprimir hacia atrás:** mostrar los elementos desde el final hasta el inicio, avanzando mediante la referencia al nodo anterior.

## Estructura requerida

- La lista debe ser **genérica**: su implementación no debe depender de un tipo concreto de elemento.
- Cada nodo almacena un dato y dos referencias:
  - `anterior`: apunta al nodo precedente; en el primer nodo no hay precedente.
  - `siguiente`: apunta al nodo posterior; en el último nodo no hay posterior.
- La lista debe conservar referencias a sus extremos (inicio y fin). En una lista vacía ambos extremos no apuntan a ningún nodo. Esto permite insertar y recorrer desde cualquiera de los extremos.

## Casos especiales que debe contemplar la eliminación

- **Lista vacía:** no existe un elemento que eliminar; la operación debe terminar sin alterar el estado.
- **Elemento inexistente:** recorrer hasta el final sin modificar la lista.
- **Único nodo:** al eliminarlo, ambos extremos deben quedar vacíos.
- **Primer nodo:** avanzar el inicio y dejar sin predecesor al nuevo primer nodo.
- **Último nodo:** retroceder el fin y dejar sin sucesor al nuevo último nodo.
- **Nodo intermedio:** enlazar entre sí al nodo anterior y al siguiente del nodo eliminado.

La actualización correcta de ambos enlaces evita referencias colgantes y mantiene disponibles los recorridos en las dos direcciones después de cada eliminación.

## Criterios de aceptación

- Insertar en una lista vacía establece correctamente ambos extremos.
- Insertar al inicio o al final conserva el orden esperado y los enlaces bidireccionales.
- Las impresiones hacia adelante y hacia atrás presentan el mismo contenido en órdenes opuestos.
- Eliminar un elemento en cada posición (primero, intermedio y último), eliminar el único elemento, y solicitar la eliminación en una lista vacía o de un elemento ausente no deja enlaces ni extremos inconsistentes.

## Alcance y decisiones no especificadas

El enunciado no prescribe una interfaz de consola, un formato exacto de impresión ni qué hacer cuando hay valores repetidos. Para una implementación concreta, conviene definir la eliminación como la del **primer elemento coincidente** y usar el criterio de igualdad apropiado para `T` (por ejemplo, `equals` en Java). Estos detalles no cambian los requerimientos estructurales anteriores.