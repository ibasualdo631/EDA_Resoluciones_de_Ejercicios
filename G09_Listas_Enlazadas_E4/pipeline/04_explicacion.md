# Explicación — El orden de los punteros

Al insertar un nodo intermedio hay que conservar el enlace al resto de la lista antes de cambiar el puntero del nodo anterior. Supongamos que se quiere insertar `X` entre `A` y `B`:

```text
Antes: A -> B -> C -> null
```

La secuencia correcta es:

```java
nuevo.siguiente = actual.siguiente;
actual.siguiente = nuevo;
```

Primero `X.siguiente` conserva el sucesor de `A` (`B`); después `A.siguiente` pasa a apuntar a `X`:

```text
Después: A -> X -> B -> C -> null
```

## Qué ocurre si se invierten las asignaciones

Si se hace primero `actual.siguiente = nuevo`, el enlace de `A` a `B` se reemplaza. Luego, al ejecutar `nuevo.siguiente = actual.siguiente`, `actual.siguiente` ya no es `B`: es `X`. Por lo tanto, `X.siguiente` queda apuntándose a sí mismo:

```text
Resultado incorrecto:
head -> A -> X
             ^ |
             |_|

B -> C -> null   (ya no se pueden alcanzar desde head)
```

La catástrofe principal es una corrupción lógica de la estructura:

- **Se pierden nodos de la cadena visible.** `B` y los nodos posteriores quedan desconectados desde `head`.
- **Se forma un ciclo.** `X.siguiente == X`; cualquier recorrido que avance mientras el nodo no sea `null` queda atrapado visitando `X` indefinidamente. Por ejemplo, `imprimir()` no llega a su condición de salida y sigue imprimiendo el mismo dato.
- **El tamaño deja de representar la lista recorrible.** El método incrementaría `size`, aunque varios nodos ya no sean accesibles desde la cabeza.

En Java esto no es una escritura fuera de memoria ni una corrupción arbitraria del heap: los punteros son referencias administradas por la JVM. El ciclo alcanzable desde `head` no se recolecta porque sigue siendo accesible, y los nodos desconectados solo serán elegibles para el recolector si ninguna otra referencia los conserva. El problema visible es el ciclo y la pérdida de conectividad; un recorrido infinito puede consumir tiempo y producir salida o trabajo sin límite, pero la mera existencia del ciclo no implica por sí sola una fuga de memoria.

## Regla práctica

Antes de sobrescribir un enlace, copiar o transferir a `nuevo` el sucesor que ese enlace permitía alcanzar. En este método, respetar siempre:

```java
nuevo.siguiente = actual.siguiente;
actual.siguiente = nuevo;
```
