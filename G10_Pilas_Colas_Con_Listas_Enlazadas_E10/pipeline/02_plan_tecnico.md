# Plan técnico — Lista doblemente enlazada genérica

## 1. Organización

Separar la solución en dos abstracciones:

- **`Nodo<T>`**: representa un elemento de la lista.
- **`ListaDoble<T>`**: administra los nodos, sus extremos y las operaciones requeridas.

La implementación debe conservar el encapsulamiento: los enlaces y los extremos son internos; las operaciones públicas exponen inserción, eliminación e impresión sin permitir que el cliente manipule punteros directamente.

## 2. Estructuras y referencias

### Nodo

Cada nodo contiene:

- `dato: T`
- `anterior: Nodo<T>` (nulo si no tiene predecesor)
- `siguiente: Nodo<T>` (nulo si no tiene sucesor)

### Lista

La lista mantiene:

- `inicio: Nodo<T>`: primer nodo, o nulo si está vacía.
- `fin: Nodo<T>`: último nodo, o nulo si está vacía.

Invariante: una lista vacía tiene `inicio == null` y `fin == null`. En una lista no vacía, `inicio.anterior == null` y `fin.siguiente == null`; para cada par de nodos consecutivos `a` y `b`, `a.siguiente == b` y `b.anterior == a`.

### Por qué se requieren ambos enlaces

`siguiente` permite recorrer desde `inicio` hacia `fin`; `anterior` permite recorrer en sentido inverso desde `fin` hacia `inicio`. Ambos son necesarios para cumplir las dos impresiones y mantener la relación bidireccional al insertar o quitar nodos. Sin `anterior`, el recorrido hacia atrás requeriría buscar repetidamente desde el inicio; sin `siguiente`, no se podría avanzar normalmente hacia adelante. Una lista doblemente enlazada conserva ambos accesos en tiempo constante entre vecinos.

## 3. Interfaz propuesta

Firmas conceptuales, adaptables a la sintaxis del lenguaje elegido:

- `insertarInicio(dato: T): void`
- `insertarFinal(dato: T): void`
- `eliminar(dato: T): boolean` — elimina la primera coincidencia y devuelve si encontró y quitó un nodo.
- `imprimirAdelante(): void` — recorre desde `inicio` mediante `siguiente`.
- `imprimirAtras(): void` — recorre desde `fin` mediante `anterior`.

La igualdad para buscar el dato debe seguir el contrato del lenguaje y tipo `T` (por ejemplo, comparación segura frente a valores nulos si el lenguaje lo requiere). El formato concreto de impresión queda a elección de la implementación, pues el enunciado no lo fija.

## 4. Lógica de las operaciones

### Insertar al inicio

1. Crear un nuevo nodo con el dato.
2. Si la lista está vacía, asignar el nuevo nodo tanto a `inicio` como a `fin`.
3. En otro caso, enlazar el nuevo nodo delante del inicio actual: nuevo.`siguiente` apunta al inicio anterior; inicio anterior.`anterior` apunta al nuevo nodo; actualizar `inicio`.

### Insertar al final

1. Crear un nuevo nodo con el dato.
2. Si la lista está vacía, asignar el nuevo nodo a ambos extremos.
3. En otro caso, enlazar el nuevo nodo después del fin actual: nuevo.`anterior` apunta al fin anterior; fin anterior.`siguiente` apunta al nuevo nodo; actualizar `fin`.

### Eliminar

1. Recorrer desde `inicio` hasta encontrar la primera coincidencia o llegar al final.
2. Si no hay coincidencia, devolver `false` sin modificar la lista.
3. Si se encontró el nodo, identificar sus vecinos `previo` y `posterior`.
4. Reenlazar los vecinos, actualizando los extremos cuando falte uno de ellos.
5. Desconectar el nodo eliminado poniendo sus dos enlaces en nulo y devolver `true`.

### Impresión

- **Adelante:** iniciar en `inicio`; emitir cada dato y avanzar por `siguiente` hasta nulo.
- **Atrás:** iniciar en `fin`; emitir cada dato y avanzar por `anterior` hasta nulo.

## 5. Eliminación: casos críticos y actualización de enlaces

Al borrar un nodo intermedio `actual`, con `previo` y `posterior` presentes, se hacen las dos actualizaciones entre vecinos:

1. `previo.siguiente = posterior`
2. `posterior.anterior = previo`

Además, se desconecta el nodo eliminado (las otras dos referencias implicadas):

3. `actual.anterior = null`
4. `actual.siguiente = null`

Estas son las cuatro referencias de enlace que se actualizan para desvincular completamente un nodo intermedio. Si el nodo está en un extremo, las asignaciones al vecino ausente se sustituyen por la actualización del extremo correspondiente:

| Caso | Actualización de la lista y enlaces |
|---|---|
| Lista vacía / elemento no encontrado | No cambiar referencias; indicar que no se eliminó. |
| Único nodo | Poner `inicio` y `fin` en nulo; desconectar ambos enlaces del nodo. |
| Primer nodo (con más nodos) | Poner `inicio = posterior` y `inicio.anterior = null`; desconectar el nodo eliminado. |
| Último nodo (con más nodos) | Poner `fin = previo` y `fin.siguiente = null`; desconectar el nodo eliminado. |
| Nodo intermedio | Aplicar las cuatro asignaciones enumeradas arriba. |

No se debe acceder a un vecino antes de comprobar que existe. Esto evita desreferencias nulas en los casos de primero, último y único nodo.

## 6. Secuencia de implementación

1. Definir `Nodo<T>` y `ListaDoble<T>` con los extremos inicializados en nulo.
2. Implementar las inserciones y comprobar los invariantes tanto en vacío como en listas no vacías.
3. Implementar recorridos e impresión en ambas direcciones.
4. Implementar la búsqueda y eliminación de la primera coincidencia, contemplando explícitamente todos los casos de la tabla.
5. Probar inserciones consecutivas, recorridos opuestos y eliminaciones al principio, en medio, al final, del único nodo, en lista vacía y de un dato ausente.

## 7. Complejidad esperada

- Insertar al inicio o al final: **O(1)**.
- Imprimir adelante o atrás: **O(n)**.
- Eliminar por valor: **O(n)** para localizar el dato; **O(1)** para desvincular el nodo una vez localizado.