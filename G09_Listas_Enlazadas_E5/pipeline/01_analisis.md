# Análisis — Ejercicio 5: Eliminación de nodo por valor

## Objetivo

Implementar en Java el método `boolean eliminar(int dato)` de una lista enlazada para quitar la primera aparición del valor indicado.

## Requerimientos funcionales

- Recorrer la lista buscando el primer nodo cuyo dato sea igual al argumento.
- Si se encuentra, desvincular ese nodo y conservar el orden y los demás elementos.
- Eliminar solamente la primera aparición, incluso si el valor está repetido.
- Devolver `true` cuando se elimina un nodo y `false` cuando el dato no existe. Este significado se infiere del tipo de retorno booleano y debe mantenerse consistente en la implementación.
- En Java no se libera ni se borra manualmente la memoria del nodo: al dejarlo inaccesible desde la lista, podrá ser reclamado posteriormente por el Garbage Collector.

## Escenarios límite y resultados esperados

| Escenario | Comportamiento esperado |
|---|---|
| Lista vacía | No modificar la lista y devolver `false`. |
| Eliminar la cabeza | Actualizar la referencia a la cabeza al siguiente nodo; si era el único, la lista queda vacía. Devolver `true`. |
| Eliminar un nodo interno | Enlazar el nodo anterior directamente con el siguiente, manteniendo el resto del orden. Devolver `true`. |
| Eliminar el último nodo | Desvincularlo; si la lista mantiene una referencia a la cola, actualizarla al nodo anterior. Si era el único nodo, cabeza y cola deben quedar vacías. Devolver `true`. |
| Dato ausente | No cambiar nodos ni referencias y devolver `false`. |
| Valor repetido | Eliminar únicamente la primera coincidencia y dejar intactas las coincidencias posteriores. Devolver `true`. |

## Restricciones y observaciones

- Respetar la estructura y las convenciones ya usadas por la implementación existente de la lista.
- No asumir nombres de clase, campos o referencias aparte del método solicitado; inspeccionar el código existente al implementar.
- La eliminación consiste en actualizar enlaces/referencias, no en borrar memoria explícitamente.
- Tras cualquier eliminación, la lista debe seguir siendo recorrible y sus referencias de inicio y fin (si existe cola) deben ser coherentes.

## Prompt inicial

Implementá el método Java `boolean eliminar(int dato)` en la lista enlazada existente. Debe eliminar solamente la primera aparición de `dato` y devolver `true` si la elimina o `false` si no está. Contemplá la lista vacía, la eliminación de la cabeza, de un nodo interno y del último nodo; si la lista mantiene una referencia a la cola, actualizala correctamente. Si el valor está repetido, dejá intactas las apariciones posteriores. Si el dato no existe, la lista debe permanecer sin cambios. No intentes liberar memoria manualmente: en Java, desvinculá el nodo para que quede inaccesible desde la lista y pueda ser reclamado por el Garbage Collector. Conservá las convenciones de la implementación existente y agregá o ajustá pruebas para verificar todos estos casos y el valor de retorno.