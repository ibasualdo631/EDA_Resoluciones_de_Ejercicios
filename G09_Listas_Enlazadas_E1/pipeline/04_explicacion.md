# Explicación: referencias en el Heap y el papel de `head`

## 1. Qué vive en el Heap

En Java, crear una lista con `new ListaEnlazadaSimple()` reserva un objeto `ListaEnlazadaSimple` en el Heap. Su variable `lista`, por ejemplo, guarda una referencia a ese objeto. Los nodos también son objetos independientes en el Heap: cada llamada a `new Nodo(dato)` reserva un nodo con un entero y una referencia `siguiente`.

Un esquema conceptual después de insertar `20` al inicio, luego `10` al inicio y finalmente `30` al final:

```text
Variable lista
    |
    v
Objeto ListaEnlazadaSimple
    head -----------------> Nodo A { dato: 10, siguiente: Nodo B }
    size: 3                                  |
                                             v
                                 Nodo B { dato: 20, siguiente: Nodo C }
                                                               |
                                                               v
                                             Nodo C { dato: 30, siguiente: null }
```

Las flechas representan referencias, no copias de los objetos. `head` no contiene el primer entero: conserva una referencia al primer nodo. Cada enlace `siguiente` permite llegar al siguiente objeto. El final se representa con `null`.

## 2. La coreografía al insertar al inicio

En `insertarAlInicio`, estas instrucciones deben ocurrir en este orden:

```java
Nodo nuevo = new Nodo(dato);
nuevo.siguiente = head;
head = nuevo;
size++;
```

Supongamos que la lista contiene `20 -> 30` y `head` apunta al nodo de `20`.

1. `new Nodo(10)` crea un objeto nuevo, todavía sin sucesor.
2. `nuevo.siguiente = head` hace que el nodo nuevo apunte al antiguo primer nodo (`20`).
3. `head = nuevo` mueve la entrada de la lista al nodo de `10`.
4. `size++` refleja el nodo añadido.

El resultado es:

```text
head -> [10 | siguiente] -> [20 | siguiente] -> [30 | null]
```

La asignación del enlace **antes** de mover `head` es importante: conserva la ruta hacia los nodos existentes. Si se reemplazara primero `head` sin guardar la referencia antigua en otro sitio, se perdería el acceso a esa cadena.

Cuando la lista estaba vacía, el antiguo `head` era `null`. Por eso el enlace del nodo nuevo queda en `null`, y al asignar `head = nuevo` se establece el primer y único nodo.

## 3. La coreografía al insertar al final

`insertarAlFinal` crea un nodo nuevo cuyo `siguiente` es `null`. Si `head` no es `null`, usa la referencia local `actual` para recorrer:

```text
actual = head
actual = actual.siguiente
...
```

`actual` cambia para señalar sucesivamente los nodos, pero `head` permanece apuntando al primero. Al llegar al último (`actual.siguiente == null`), se enlaza el nuevo nodo:

```java
actual.siguiente = nuevo;
```

Así se conserva la entrada a la lista y se amplía la cadena en su extremo. Si la lista está vacía, no existe último nodo al que enlazarlo: se asigna directamente `head = nuevo`. En cualquiera de las ramas `size` aumenta exactamente una vez.

## 4. Por qué `head` mantiene accesible toda la estructura

El recolector de basura de Java determina qué objetos siguen siendo alcanzables comenzando desde referencias raíz (por ejemplo, variables locales activas en pilas de ejecución y campos estáticos). En este caso, una ruta típica es:

```text
raíz de ejecución -> variable lista -> objeto ListaEnlazadaSimple
                  -> head -> primer Nodo -> siguiente -> ... -> último Nodo
```

Mientras exista una ruta desde una raíz hasta `lista`, y `lista.head` apunte al primer nodo, todos los nodos enlazados pueden alcanzarse siguiendo `siguiente`. No hace falta una variable separada que apunte a cada nodo.

## 5. Qué significa realmente “perder `head`”

Si se sobrescribe o se pone a `null` el único enlace que inicia la cadena, por ejemplo `lista.head = null`, y no queda ninguna otra referencia alcanzable a los nodos, la ruta desde las raíces se corta:

```text
raíz -> lista -> head: null       [Nodo A] -> [Nodo B] -> [Nodo C]
                                      (sin ruta desde una raíz)
```

Aunque los nodos sigan apuntándose entre sí, ese ciclo o cadena aislada no los mantiene vivos. El Garbage Collector puede reclamar los nodos cuando los detecte como inalcanzables. Si `lista` sigue referenciada, el objeto contenedor continúa vivo, pero sus antiguos nodos pueden quedar elegibles para recolección.

Esto **no** significa que el Garbage Collector los destruya en el instante exacto en que se pierde `head`. La recolección es automática y no determinista: los objetos inalcanzables quedan elegibles para ser reclamados en una ejecución posterior. Tampoco equivale a llamar a un destructor ni a borrar la memoria manualmente.

Además, perder el campo `head` no hace inalcanzables los nodos si existe otra referencia viva que permita llegar a ellos. Lo determinante no es el nombre `head`, sino que no exista ninguna ruta desde una raíz hasta esos objetos.

## 6. Aplicación a la implementación

- `head` es el acceso persistente al comienzo de la lista y debe conservarse entre operaciones.
- `actual` en `insertarAlFinal` e `imprimir` es temporal: sirve para recorrer, no reemplaza ni cambia `head`.
- `size` almacena un número, no una referencia a los nodos. Que `size` sea mayor que cero no mantiene objetos vivos por sí mismo.
- `estaVacia()` comprueba `head == null`; `getSize()` consulta el contador.
- Un nodo tiene `siguiente == null` cuando es el último. No se debe confundir ese final normal con perder el `head` de toda la estructura.

Por eso las inserciones actualizan cuidadosamente los enlaces: mantener una referencia de entrada válida preserva la accesibilidad de la cadena y evita desconectar accidentalmente los nodos existentes.