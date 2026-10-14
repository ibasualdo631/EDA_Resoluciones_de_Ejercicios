# Explicación — Inversión de una lista enlazada simple

## Idea principal

Cada nodo tiene una referencia `siguiente` que apunta al nodo que viene después. Invertir la lista significa cambiar esas referencias para que apunten hacia atrás. No se mueven ni se copian los valores: se reutilizan los mismos nodos y se modifica su enlace.

Para recorrer sin perder el acceso a los nodos todavía pendientes, `invertir()` usa tres referencias:

- `actual`: nodo cuyo enlace vamos a redirigir.
- `anterior`: nodo que ya quedó delante de `actual` en el orden invertido.
- `siguiente`: respaldo del enlace original de `actual`, que permite continuar el recorrido.

El código relevante es:

```java
Nodo anterior = null;
Nodo actual = cabeza;

while (actual != null) {
    Nodo siguiente = actual.siguiente;
    actual.siguiente = anterior;
    anterior = actual;
    actual = siguiente;
}

cabeza = anterior;
```

## Recorrido paso a paso del ejemplo

Al principio:

```text
cabeza
  |
  v
10 -> 20 -> 30 -> 40 -> null

anterior = null
actual = 10
```

### Iteración 1: redirigir el enlace de 10

1. `siguiente = actual.siguiente`: se guarda el nodo `20`.
2. `actual.siguiente = anterior`: el enlace de `10` pasa de `20` a `null`.
3. `anterior = actual`: `anterior` avanza a `10`.
4. `actual = siguiente`: `actual` avanza a `20`.

Estado:

```text
segmento invertido: 10 -> null
pendiente:           20 -> 30 -> 40 -> null
anterior = 10
actual = 20
```

### Iteración 2: redirigir el enlace de 20

1. Se guarda `30` en `siguiente`.
2. `20.siguiente` cambia de `30` a `10`.
3. `anterior` avanza a `20`.
4. `actual` avanza a `30`.

Estado:

```text
segmento invertido: 20 -> 10 -> null
pendiente:           30 -> 40 -> null
anterior = 20
actual = 30
```

### Iteración 3: redirigir el enlace de 30

1. Se guarda `40` en `siguiente`.
2. `30.siguiente` cambia de `40` a `20`.
3. `anterior` avanza a `30`.
4. `actual` avanza a `40`.

Estado:

```text
segmento invertido: 30 -> 20 -> 10 -> null
pendiente:           40 -> null
anterior = 30
actual = 40
```

### Iteración 4: redirigir el enlace de 40

1. Se guarda `null` en `siguiente`, pues `40` era el último nodo.
2. `40.siguiente` cambia de `null` a `30`.
3. `anterior` avanza a `40`.
4. `actual` avanza a `null`, así que el bucle termina.

Ahora `anterior` encabeza `40 -> 30 -> 20 -> 10 -> null`. La asignación final `cabeza = anterior` hace que la lista vuelva a ser accesible desde su entrada, ahora en el orden invertido.

## Por qué el orden no se puede cambiar

La línea que guarda el sucesor debe ejecutarse **antes** de sobrescribir el enlace:

```java
Nodo siguiente = actual.siguiente; // preservar la ruta hacia los pendientes
actual.siguiente = anterior;       // redirigir el enlace actual
```

Supongamos que primero se hace `actual.siguiente = anterior` cuando `actual` es `10`. El enlace original `10 -> 20` desaparece y queda `10 -> null`. Si después se intenta obtener el sucesor con `siguiente = actual.siguiente`, se obtiene `null`, no `20`. El bucle termina prematuramente y, al establecer la cabeza en `anterior`, solo queda la lista `10 -> null`; los nodos `20`, `30` y `40` ya no se pueden alcanzar desde la lista.

En Java no hay una liberación manual de esos nodos ni una corrupción física de memoria como la que puede ocurrir con punteros sin gestionar. La catástrofe es lógica: al perder la última referencia a la cadena pendiente, sus nodos quedan **inalcanzables** y el recolector de basura puede reclamarlos. Para el programa, los datos de esos nodos se pierden.

También importa no adelantar las referencias antes de tiempo: `anterior` debe seguir señalando al prefijo invertido cuando se asigna a `actual.siguiente`, y `actual` debe avanzar usando el `siguiente` guardado, no un enlace ya modificado.

## Casos límite y eficiencia

- Lista vacía: `actual` comienza en `null`; no hay iteraciones y `cabeza` queda en `null`.
- Un nodo: su enlace termina en `null` y la cabeza sigue apuntando a ese único nodo.
- La cantidad de nodos no cambia; el atributo `size` se mantiene igual.
- Tiempo: `O(n)`, porque se procesa cada nodo una vez.
- Espacio auxiliar: `O(1)`, porque se usan solo tres referencias, sin importar el tamaño de la lista.