# Análisis — Ejercicio 1: Pila enlazada de enteros

## 1. Objetivo

Implementar una pila de números enteros mediante una lista simplemente enlazada. La estructura debe permitir apilar y desapilar elementos, consultar el tope, verificar si está vacía, buscar un valor e imprimir su contenido.

El diseño debe explicar por qué `head` identifica el tope y por qué `push` y `pop` se resuelven en tiempo O(1).

## 2. Usuarios y alcance

- **Usuario:** quien use la pila desde el programa de demostración o desde pruebas.
- **Alcance funcional:** operaciones básicas de una pila de enteros, manteniendo la política LIFO (el último elemento apilado es el primero en desapilarse).
- **Fuera de alcance:** persistencia, concurrencia, capacidad máxima configurable e interfaz gráfica. El enunciado no requiere estas funciones.

## 3. Modelo de datos

- Cada nodo contiene un entero y una referencia al nodo siguiente.
- `head` referencia el primer nodo de la lista y representa el tope de la pila.
- Una pila vacía se representa con `head == null`.
- El enlace del nodo del tope apunta al elemento que quedó inmediatamente debajo; el último nodo apunta a `null`.

Al insertar en `head` se evita recorrer la lista: el nuevo nodo apunta al antiguo `head` y luego `head` pasa a referenciar el nuevo nodo. Al quitar, basta con avanzar `head` al siguiente nodo. Por ello, ambas operaciones requieren una cantidad constante de trabajo, O(1), independientemente del tamaño de la pila.

## 4. Requerimientos funcionales y casos de uso

| Operación | Comportamiento esperado | Caso límite y resultado |
|---|---|---|
| **Apilar (`push(valor)`)** | Crear un nodo con `valor`, enlazarlo delante del `head` actual y actualizar `head`. | Apilar sobre una pila vacía crea el primer nodo, que queda como tope. Debe poder apilarse cualquier entero admitido por el tipo. Complejidad: O(1). |
| **Desapilar (`pop()`)** | Retirar el nodo referenciado por `head`, actualizar `head` al siguiente nodo y devolver el valor retirado. | En una pila vacía no hay valor que devolver: debe notificarse que la operación no es posible, por ejemplo mediante una excepción o un resultado explícito. Al quitar el único nodo, `head` vuelve a `null`. Complejidad: O(1). |
| **Consultar tope (`peek()`)** | Devolver el valor del nodo referenciado por `head` sin modificar la pila. | En una pila vacía debe informarse que no existe tope; la consulta no debe alterar el estado. Complejidad: O(1). |
| **Verificar vacío (`isEmpty()`)** | Devolver `true` si `head == null`; en otro caso, `false`. | Debe devolver `true` tanto al inicio como después de desapilar el último elemento. Complejidad: O(1). |
| **Buscar (`contains(valor)`)** | Recorrer los nodos desde el tope y devolver `true` si alguno contiene el valor buscado; de lo contrario, devolver `false`. No debe modificar la pila. | En una pila vacía, cualquier búsqueda devuelve `false`. Debe reconocer valores repetidos sin cambiar el resultado ni eliminar elementos. Complejidad: O(n) en el peor caso. |
| **Imprimir** | Recorrer desde `head` hasta `null` y mostrar los valores en orden de tope a base, manteniendo la pila intacta. | En una pila vacía debe indicarse claramente que no contiene elementos, sin intentar acceder a un nodo inexistente. Complejidad: O(n). |

## 5. Reglas de negocio e invariantes

1. El orden de salida de `pop` es el inverso al orden de inserción (LIFO).
2. `head` siempre apunta al tope actual; no se permite que operaciones de consulta lo cambien.
3. Cada nodo, excepto el último, enlaza con el elemento inmediatamente inferior.
4. Una pila sin elementos debe tener `head == null`; al desapilar su único elemento debe restablecerse ese estado.
5. La búsqueda y la impresión son operaciones de solo lectura.
6. No se necesita una referencia a la base ni recorrer la lista para insertar o retirar el tope.

## 6. Casos límite y errores a validar

- Ejecutar `peek`, `pop`, `contains` e impresión antes de insertar elementos.
- Desapilar el último elemento y comprobar que la pila queda vacía.
- Apilar después de vaciar la estructura y verificar que vuelve a operar normalmente.
- Apilar valores negativos, cero y valores repetidos.
- Buscar un valor presente en el tope, en el interior y en la base; buscar uno ausente.
- Confirmar que `peek`, `contains` e impresión no cambian el contenido ni el orden.
- Confirmar el orden de impresión (tope a base) y el orden LIFO de varios `pop`.
- Si la entrada se recibe desde consola, validar entradas no numéricas; el enunciado no define el formato ni la política de reintento.

## 7. Criterios de aceptación

- La pila está implementada con nodos de una lista simplemente enlazada, no con arreglos ni colecciones que sustituyan la estructura solicitada.
- Se ofrecen las seis operaciones indicadas en el enunciado.
- `push` y `pop` actualizan únicamente el nodo inicial y son O(1).
- Las operaciones sobre una pila vacía manejan el caso de forma explícita y segura.
- Las consultas respetan el estado de la pila y la impresión recorre de tope a base.
- La explicación incluye la relación entre `head`, el tope, LIFO y las complejidades.

## 8. Aspectos no especificados

El enunciado no define cómo reportar el error de `pop`/`peek` en vacío, el formato concreto de impresión, si se requiere una interfaz interactiva, ni cómo se introducen los valores. Para mantener la estructura simple, la implementación debe elegir una política explícita y consistente para `pop` y `peek` (preferentemente una excepción documentada o un resultado que distinga “vacío” de cualquier entero válido). La búsqueda se interpreta como una consulta de pertenencia y la impresión como recorrido desde el tope hacia la base.