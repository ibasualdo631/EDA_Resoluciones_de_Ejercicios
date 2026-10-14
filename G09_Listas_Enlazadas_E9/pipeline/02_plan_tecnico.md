# Plan técnico — Inversión iterativa de una lista enlazada simple

## 1. Estrategia

Invertir la lista **in situ**, recorriéndola una sola vez y redirigiendo el enlace `next` de cada nodo. Se emplean tres referencias auxiliares:

- `anterior`: cabeza del segmento que ya está invertido.
- `actual`: nodo que se procesa en esta iteración.
- `siguiente`: referencia temporal al resto de la lista original, a partir del próximo nodo pendiente.

Además, la estructura ya existente mantiene la referencia de entrada a la lista (por ejemplo, `cabeza` o `head`). Se debe conservar el nombre y tipo definidos por el proyecto; la consigna no prescribe un lenguaje ni un nombre concreto para ese campo.

## 2. Inicialización

Antes del bucle:

```text
anterior = null
actual = cabeza
```

`anterior` comienza en `null` porque el primer nodo original se convertirá en el último nodo y su enlace final debe ser `null`. `actual` comienza en el primer nodo para procesar todos los nodos, incluido ese primero.

## 3. Coreografía exacta de punteros

Mientras `actual != null`, ejecutar **en este orden**:

```text
siguiente = actual.next
actual.next = anterior
anterior = actual
actual = siguiente
```

### Qué garantiza cada movimiento

1. **`siguiente = actual.next`**: protege el acceso al tramo aún no procesado. Debe ocurrir antes de cambiar `actual.next`; de lo contrario, se sobrescribe la única ruta de avance de una lista simplemente enlazada y los nodos restantes quedan inaccesibles desde las referencias locales.
2. **`actual.next = anterior`**: invierte el enlace del nodo actual para que apunte hacia el segmento ya procesado. En la primera iteración ese destino es `null`.
3. **`anterior = actual`**: incorpora el nodo recién redirigido al frente del segmento invertido.
4. **`actual = siguiente`**: reanuda el recorrido en el primer nodo todavía pendiente, cuya referencia quedó protegida en el paso 1.

No se debe adelantar `actual` antes de guardar `siguiente`, ni mover `anterior` antes de redirigir `actual.next`: cada variable debe seguir representando su segmento en el momento en que se usa.

## 4. Pseudocódigo completo

```text
método void invertir():
    anterior = null
    actual = cabeza

    mientras actual != null:
        siguiente = actual.next
        actual.next = anterior
        anterior = actual
        actual = siguiente

    cabeza = anterior
```

El método debe integrarse en la clase y con las convenciones existentes. El pseudocódigo asume que cada nodo tiene un enlace `next`; si el proyecto emplea otro nombre, usar el campo real. No se crean nodos ni se modifica el valor almacenado en ellos.

## 5. Traza de ejemplo

Estado inicial: `cabeza -> 10 -> 20 -> 30 -> 40 -> null`.

| Iteración | `actual` antes de procesar | `siguiente` guardado | Enlace redirigido | `anterior` / prefijo invertido al final |
|---|---:|---:|---|---|
| 1 | 10 | 20 | `10.next = null` | `10 -> null` |
| 2 | 20 | 30 | `20.next = 10` | `20 -> 10 -> null` |
| 3 | 30 | 40 | `30.next = 20` | `30 -> 20 -> 10 -> null` |
| 4 | 40 | null | `40.next = 30` | `40 -> 30 -> 20 -> 10 -> null` |

Tras la última iteración, `actual == null`, `anterior` referencia al nodo `40`, y la asignación final `cabeza = anterior` hace que la lista sea accesible en su nuevo orden.

## 6. Invariante de corrección

Al comienzo de cada iteración:

- `anterior` encabeza el prefijo ya procesado, con todos sus enlaces invertidos y terminado en `null`.
- `actual` encabeza el sufijo todavía pendiente, cuyos enlaces originales permanecen intactos.
- Ningún nodo se pierde: `siguiente` preserva la entrada al sufijo antes de modificar el enlace que conecta con él.

Cada iteración mueve exactamente un nodo desde el sufijo pendiente al prefijo invertido. Al llegar `actual` a `null`, no queda sufijo por procesar y `anterior` es la cabeza de la lista completa invertida.

## 7. Casos límite y complejidad

- **Vacía**: `actual` inicia en `null`, no se ejecuta el bucle y `cabeza` queda en `null`.
- **Un nodo**: se guarda `null` en `siguiente`, su enlace se establece en `null`, y `cabeza` continúa apuntando a ese nodo.
- **Varios nodos**: se invierten todos los enlaces una vez; el antiguo primer nodo termina con enlace `null`.
- **Tiempo**: `O(n)`, una iteración por nodo.
- **Espacio auxiliar**: `O(1)`, tres referencias, sin estructuras proporcionales al tamaño.

## 8. Instrucciones para el desarrollador

1. Ubicar la implementación de la lista y confirmar el campo que representa la cabeza y el nombre del enlace de cada nodo.
2. Implementar `void invertir()` dentro de la clase responsable de la lista, sin alterar los valores ni la API solicitada.
3. Mantener literalmente la secuencia de operaciones del bucle: guardar sucesor, redirigir enlace, avanzar `anterior` y avanzar `actual`.
4. Al terminar el bucle, asignar la cabeza a `anterior`; no omitir esta actualización, pues la antigua cabeza ya es el último nodo.
5. No crear una lista auxiliar, nodos nuevos, arreglos ni colecciones; no imprimir ni retornar valores, ya que el método indicado es `void`.
6. Verificar listas vacías, de un nodo y de varios nodos; para el ejemplo dado, comprobar que el recorrido resultante sea `40 -> 30 -> 20 -> 10 -> null`, sin ciclos ni nodos omitidos.