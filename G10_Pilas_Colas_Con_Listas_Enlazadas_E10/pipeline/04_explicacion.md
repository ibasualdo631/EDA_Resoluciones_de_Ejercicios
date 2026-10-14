# Guía: eliminación de un nodo central en la lista doble

## Qué guarda cada nodo

La implementación está en `ListaDoble<T>`. Cada nodo anidado contiene un dato y dos referencias:

- `anterior` apunta al nodo inmediatamente previo.
- `siguiente` apunta al nodo inmediatamente posterior.

La lista además mantiene `inicio` y `fin`, que apuntan a los extremos. Por eso es posible recorrer hacia adelante con `siguiente` y hacia atrás con `anterior`.

## Ejemplo: eliminar el nodo central

Supongamos esta lista, donde queremos eliminar `actual`:

```text
inicio                                                  fin
  |                                                      |
  v                                                      v
[ A ] <----------> [ B ] <----------> [ C ] <----------> [ D ]
                    ^                 ^
                  previo            actual
                                      |
                                      +---- posterior es [D]
```

El diagrama compacto de la zona afectada es:

```text
ANTES:

previo.siguiente ──────> actual
previo           <────── actual.anterior
actual.siguiente ──────> posterior
actual           <────── posterior.anterior

DESPUÉS DE REENLAZAR LOS VECINOS:

previo.siguiente ──────> posterior
previo           <────── posterior.anterior

actual.anterior = null
actual.siguiente = null
```

Es decir, la cadena resultante queda:

```text
[ A ] <----------> [ B ] <----------> [ D ]
                    previo             posterior
```

El nodo `actual` ya no forma parte de la lista. La variable local que lo referenciaba deja de ser necesaria al terminar el método y, si no existen otras referencias externas (los nodos son privados), Java podrá recuperarlo mediante el recolector de basura.

## Paso a paso en `eliminar`

1. **Buscar:** comenzar en `inicio` y seguir `siguiente` hasta que el dato coincida o se alcance `null`. `Objects.equals` compara los datos y admite correctamente valores `null`.
2. **Guardar vecinos:** antes de modificar enlaces, conservar `actual.anterior` en `previo` y `actual.siguiente` en `posterior`.
3. **Reconectar hacia adelante:** asignar `previo.siguiente = posterior`. Así, desde el nodo previo, el recorrido ya salta el nodo que se va a quitar.
4. **Reconectar hacia atrás:** asignar `posterior.anterior = previo`. Así, el recorrido inverso también salta el nodo eliminado.
5. **Desconectar el nodo retirado:** establecer `actual.anterior = null` y `actual.siguiente = null`. No es necesario para que la lista alcance los nodos restantes —eso ya se logró en los pasos 3 y 4—, pero deja el nodo explícitamente desvinculado y evita conservar referencias innecesarias.
6. **Informar el resultado:** devolver `true`. Si la búsqueda no encuentra coincidencia, se devuelve `false` sin modificar enlaces.

Las cuatro asignaciones de enlaces en el caso central son, por tanto:

```java
previo.siguiente = posterior;
posterior.anterior = previo;
actual.anterior = null;
actual.siguiente = null;
```

Las dos primeras mantienen la lista conectada en ambos sentidos. Las dos últimas aíslan el nodo borrado. El orden importa: primero se guardan los vecinos en variables locales; luego se conectan entre sí; finalmente se limpian las referencias de `actual`.

## Por qué deben actualizarse ambos lados

Si solo se cambiara `previo.siguiente`, el recorrido hacia adelante podría parecer correcto, pero `posterior.anterior` todavía apuntaría a `actual`. Al imprimir hacia atrás desde `fin`, se visitaría un nodo que ya debía estar eliminado.

Si solo se cambiara `posterior.anterior`, ocurriría el problema opuesto: el recorrido hacia atrás omitiría el nodo, pero el recorrido hacia adelante todavía lo alcanzaría desde `previo`.

Por eso el invariante de la lista requiere que para dos nodos consecutivos `x` e `y` se cumpla simultáneamente:

```text
x.siguiente == y
y.anterior == x
```

## Diferencia con eliminar un extremo

En un nodo central, existen los dos vecinos, por lo que se enlazan entre sí. En los extremos falta uno:

- **Primero:** `previo` es `null`; se cambia `inicio` a `posterior` y el nuevo inicio queda con `anterior == null`.
- **Último:** `posterior` es `null`; se cambia `fin` a `previo` y el nuevo fin queda con `siguiente == null`.
- **Único nodo:** ambos vecinos son `null`; `inicio` y `fin` terminan en `null`.

El mismo método cubre esos casos con las comprobaciones `previo == null` y `posterior == null`, sin intentar acceder a un enlace de un vecino inexistente.