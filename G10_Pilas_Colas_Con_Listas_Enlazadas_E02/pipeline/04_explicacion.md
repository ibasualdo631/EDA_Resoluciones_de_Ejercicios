# Explicación pedagógica — Cola enlazada de enteros

## 1. ¿Qué es una cola FIFO?

Una cola sigue la regla **FIFO** (*First In, First Out*): el primer elemento que se agrega es el primero que se retira. Es como una fila: las personas nuevas se colocan al final y se atiende a quien está al frente.

En esta implementación, cada elemento se guarda en un nodo enlazado con el siguiente:

```text
head                                      tail
  |                                         |
  v                                         v
[10 | siguiente] -> [-3 | siguiente] -> [0 | null]
```

`head` señala el frente de la cola y `tail` señala su último nodo. El enlace `siguiente` forma la lista simplemente enlazada; el último nodo apunta a `null`.

## 2. ¿Por qué hacen falta `head` y `tail`?

- **`head`** permite encontrar inmediatamente el elemento que debe consultarse o retirarse. Por eso `frente()` y `desencolar()` operan en **O(1)**.
- **`tail`** permite encontrar inmediatamente el final donde se agrega el próximo elemento. Por eso `encolar()` también opera en **O(1)**.

La cola necesita insertar por un extremo y retirar por el otro. Mantener una referencia a cada extremo hace que esas operaciones no dependan de cuántos nodos haya.

### ¿Qué pasaría si solo guardáramos `head`?

Una lista simplemente enlazada solo permite avanzar hacia adelante. Con `head` pero sin `tail`, para insertar al final habría que recorrer la lista nodo por nodo hasta hallar aquel cuyo `siguiente` es `null`.

- Una inserción costaría **O(n)** si ya hay *n* elementos.
- Si se agregan *n* elementos uno por uno sin `tail`, los recorridos suman aproximadamente `0 + 1 + 2 + ... + (n - 1)`, es decir, **O(n²)**.
- Con `tail`, cada inserción enlaza el nuevo nodo directamente desde el final y cuesta **O(1)**; agregar *n* elementos cuesta **O(n)** en total.

Por eso el puntero `tail` no cambia el orden FIFO: solo permite llegar más rápido al lugar correcto para añadir el próximo elemento.

## 3. Cómo trabaja `encolar`

Al encolar `10`, `-3` y `0`, la cola cambia así:

1. **Primer elemento (`10`):** la cola está vacía; el nuevo nodo pasa a ser tanto `head` como `tail`.

   ```text
   head, tail
       |
       v
   [10 | null]
   ```

2. **Segundo elemento (`-3`):** se enlaza desde el nodo que señala `tail`, y luego `tail` avanza al nuevo nodo.

   ```text
   head                 tail
     |                    |
     v                    v
   [10 | *] ----------> [-3 | null]
   ```

3. **Tercer elemento (`0`):** se repite el mismo proceso al final.

   ```text
   head                                  tail
     |                                     |
     v                                     v
   [10 | *] ----------> [-3 | *] ------> [0 | null]
   ```

En el código, la operación central es:

```java
tail.siguiente = nuevo;
tail = nuevo;
```

Primero se enlaza el nodo nuevo al final actual. Después se actualiza `tail` para que apunte al nodo recién agregado. Si se invirtiera el orden, se perdería la referencia al nodo final anterior antes de enlazarlo.

Para una cola vacía existe un caso especial: como no hay un nodo final al cual enlazar, ambos punteros deben apuntar al nuevo nodo:

```java
head = nuevo;
tail = nuevo;
```

## 4. Cómo se conserva FIFO al desencolar

`desencolar()` lee el valor de `head` y mueve `head` al siguiente nodo. No modifica `tail` mientras queden otros elementos:

```text
Antes:
head                                  tail
  |                                     |
  v                                     v
[10 | *] ----------> [-3 | *] ------> [0 | null]

Después de desencolar 10:
head                 tail
  |                    |
  v                    v
[-3 | *] ----------> [0 | null]
```

Como `head` avanza en orden, el elemento que se agregó primero sale primero. No es necesario recorrer la lista para quitarlo.

### Caso especial: retirar el último elemento

Si la cola tenía un solo nodo, al avanzar `head` este queda en `null`. También se debe poner `tail` en `null`:

```java
head = head.siguiente;
if (head == null) {
    tail = null;
}
```

El estado vacío debe cumplir siempre:

```text
head == null
tail == null
```

Si se olvidara de limpiar `tail`, este seguiría apuntando a un nodo retirado. Un `encolar()` posterior podría enlazar el elemento nuevo desde un nodo que ya no es el frente de la cola, dejando referencias incoherentes. Al limpiar ambos punteros, se puede reutilizar la cola correctamente.

## 5. Las demás operaciones

- **`frente()`** devuelve `head.valor` sin retirar el nodo. Si la cola está vacía, lanza `IllegalStateException`.
- **`estaVacia()`** comprueba si `head == null`. La invariante asegura que `tail` también será `null` en ese estado.
- **`contiene(valor)`** recorre la lista usando una referencia temporal llamada `actual`. No mueve `head` ni `tail`, así que buscar no consume elementos.
- **`imprimir()`** también recorre con una referencia temporal e imprime desde el frente al final. No cambia los enlaces ni altera el orden.

Tanto `contiene` como `imprimir` pueden visitar todos los nodos y por eso tienen complejidad **O(n)**. En cambio, `encolar`, `desencolar`, `frente` y `estaVacia` no recorren la lista y cuestan **O(1)**.

## 6. Excepciones al operar sobre una cola vacía

No hay un entero que devolver al desencolar o consultar el frente si la cola está vacía. La implementación lo señala lanzando una `IllegalStateException` con un mensaje descriptivo. Esto evita devolver un valor falso que podría confundirse con un entero válido, como `0`.

La búsqueda sobre una cola vacía devuelve `false`, porque ningún valor está presente; la impresión muestra `[]`.

## 7. Resumen de complejidad

| Operación | Complejidad | Motivo |
|---|---:|---|
| Encolar | O(1) | `tail` da acceso directo al último nodo. |
| Desencolar | O(1) | `head` da acceso directo al frente. |
| Consultar el frente | O(1) | Se consulta directamente `head`. |
| Verificar si está vacía | O(1) | Se comprueba si `head` es `null`. |
| Buscar un valor | O(n) | En el peor caso se revisan todos los nodos. |
| Imprimir | O(n) | Se visita cada nodo una vez. |

## 8. Ideas clave para recordar

1. La cola es FIFO: los elementos se agregan al final y se retiran del frente.
2. `head` representa el frente; `tail`, el final.
3. `tail` optimiza la inserción al evitar recorrer la lista, sin cambiar el comportamiento FIFO.
4. Si la cola queda vacía, ambos punteros deben quedar en `null`.
5. Después de retirar el último elemento, una inserción vuelve a asignar `head` y `tail` al nuevo nodo.