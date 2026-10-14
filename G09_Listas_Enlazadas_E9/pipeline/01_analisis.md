# Análisis — Ejercicio 9: invertir una lista enlazada simple

## Objetivo

Implementar el método `void invertir()` para invertir **en el lugar** una lista enlazada simple. La operación debe cambiar el orden de los enlaces entre los nodos, sin crear una lista nueva ni perder nodos.

Ejemplo:

```text
Antes:    10 -> 20 -> 30 -> 40 -> null
Después:  40 -> 30 -> 20 -> 10 -> null
```

## Requerimientos funcionales

- Invertir el sentido de los enlaces de todos los nodos.
- Actualizar la referencia inicial de la lista (`cabeza`, `head` o el nombre que utilice la implementación) para que apunte al antiguo último nodo.
- Conservar todos los nodos y sus valores; no se requiere crear ni eliminar nodos.
- Dejar la lista terminada correctamente en `null`.
- Implementar el método con la firma indicada por el ejercicio: `void invertir()`.

## Referencias auxiliares necesarias

- **`anterior`**: conserva el nodo que ya quedó colocado antes del nodo actual en la lista invertida. Se inicia en `null`, porque el antiguo primer nodo será el último y su nuevo enlace debe terminar en `null`.
- **`actual`**: recorre los nodos pendientes de invertir. Se inicia en la cabeza de la lista.
- **`siguiente`**: guarda temporalmente el nodo que sigue a `actual` en la lista original, antes de cambiar el enlace de `actual`. Permite continuar el recorrido después de invertir ese enlace.

## Regla crítica: orden de actualización

En cada iteración se debe:

1. Guardar `actual.next` en `siguiente`.
2. Cambiar `actual.next` para que apunte a `anterior`.
3. Avanzar `anterior` a `actual`.
4. Avanzar `actual` a `siguiente`.

El primer paso es fundamental: si se sobrescribe `actual.next` antes de guardar el enlace original, se pierde la referencia al resto de la lista y ya no se pueden visitar esos nodos. El orden también garantiza que cada enlace se invierte usando como destino el segmento ya procesado, mientras `siguiente` conserva el acceso al segmento todavía pendiente.

Al terminar, `actual` es `null` y `anterior` apunta al antiguo último nodo. La cabeza de la lista debe actualizarse para apuntar a `anterior`.

## Casos límite y criterios de aceptación

- **Lista vacía**: no falla; la cabeza sigue siendo `null`.
- **Un solo nodo**: la lista permanece igual y ese nodo sigue siendo la cabeza.
- **Varios nodos**: se invierte el orden completo, sin omisiones ni ciclos.
- **Terminación**: el antiguo primer nodo queda como último y su enlace es `null`.
- **Complejidad esperada**: tiempo `O(n)` y espacio auxiliar `O(1)`, donde `n` es la cantidad de nodos.

## Alcance y supuestos

La imagen especifica una lista simplemente enlazada, el método `void invertir()` y el comportamiento esperado. No especifica lenguaje, nombre del campo que representa la cabeza, visibilidad del método ni estructura de las clases. La implementación debe ajustarse a las convenciones existentes cuando se conozca el código; no deben inventarse nombres de clases o campos como si fueran parte de la consigna.

## Prompt inicial

> Implementa el método `void invertir()` para una lista enlazada simple, adaptándolo a la estructura y al nombre de la referencia a la cabeza que ya existan en el código. Invierte la lista en el lugar, sin crear nodos nuevos ni perder los existentes. Usa tres referencias auxiliares: `anterior`, `actual` y `siguiente`. Antes de modificar el enlace de `actual`, guarda su nodo siguiente en `siguiente`; luego haz que `actual` apunte a `anterior` y avanza las referencias en el orden correcto. Explica por qué guardar primero `siguiente` evita perder el resto de la lista y por qué el orden de actualización es fundamental. Al finalizar, actualiza la cabeza al antiguo último nodo. Considera listas vacías y de un solo nodo, y verifica el ejemplo `10 -> 20 -> 30 -> 40 -> null`, cuyo resultado debe ser `40 -> 30 -> 20 -> 10 -> null`. Mantén tiempo `O(n)` y espacio auxiliar `O(1)`.