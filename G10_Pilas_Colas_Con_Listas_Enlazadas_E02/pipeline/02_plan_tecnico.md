# Plan técnico — Cola enlazada de enteros

## 1. Tecnología y organización

- **Lenguaje:** Java.
- **Estructura:** lista simplemente enlazada propia; no utilizar arreglos, `LinkedList`, `Queue` ni otras colecciones para almacenar los elementos.
- **Organización recomendada:** una clase pública `ColaEnlazada` con una clase interna privada y estática `Nodo`. Una clase `Main` separada puede demostrar las operaciones; no se requiere menú interactivo para cumplir el enunciado.
- Mantener los detalles de nodos y punteros encapsulados. El código cliente interactúa con la cola solamente por sus métodos públicos.

## 2. Componentes y estado

### `Nodo` (clase interna privada estática)

- `int valor`: dato almacenado.
- `Nodo siguiente`: referencia al próximo nodo, o `null` si es el último.
- Constructor que recibe el valor y deja `siguiente` en `null`.

### `ColaEnlazada`

- `private Nodo head`: primer nodo (frente).
- `private Nodo tail`: último nodo (final).
- Constructor sin parámetros: inicializa `head` y `tail` en `null`.

`head` y `tail` son necesarios para que cada extremo de la cola tenga acceso directo: `head` permite consultar y retirar el frente; `tail` permite anexar al final sin recorrer la lista. Si se mantuviera solamente `head`, cada `encolar` debería recorrer desde el frente hasta el último nodo, costando O(n) por inserción. Encolar n elementos de esa forma podría acumular O(n²) trabajo. Al conservar `tail`, `encolar` cuesta O(1), y n inserciones cuestan O(n) en total.

## 3. Interfaz propuesta

| Método | Firma sugerida | Comportamiento |
|---|---|---|
| Encolar | `public void encolar(int valor)` | Agrega al final y actualiza `tail`. Si estaba vacía, asigna el nodo a `head` y `tail`. |
| Desencolar | `public int desencolar()` | Retira y devuelve el valor de `head`. Si la cola queda vacía, actualiza también `tail` a `null`. Si ya estaba vacía, lanza `IllegalStateException`. |
| Consultar frente | `public int frente()` | Devuelve el valor de `head` sin retirarlo. Si está vacía, lanza `IllegalStateException`. |
| Estado vacío | `public boolean estaVacia()` | Devuelve `head == null`. |
| Buscar | `public boolean contiene(int valor)` | Recorre la lista sin modificarla y devuelve si encuentra el valor. |
| Imprimir | `public void imprimir()` | Recorre desde `head` hasta `null` e imprime en orden FIFO; no modifica la cola. Para vacía, no imprime elementos. |

No se agrega un contador de tamaño: no es necesario para los requisitos y evitarlo simplifica el estado que debe mantenerse consistente.

## 4. Invariantes de implementación

1. Cola vacía: `head == null` y `tail == null`.
2. Cola con un nodo: `head == tail` y `head.siguiente == null`.
3. Cola con dos o más nodos: `head` referencia el frente, `tail` el último y `tail.siguiente == null`.
4. `encolar` en cola vacía asigna el nuevo nodo a ambos punteros.
5. `encolar` en cola no vacía enlaza mediante `tail.siguiente` y luego actualiza `tail`.
6. `desencolar` avanza `head`; si el nuevo `head` es `null`, también pone `tail` en `null`. Este caso evita dejar un `tail` obsoleto al retirar el último elemento.
7. `frente`, `contiene` e `imprimir` no alteran referencias ni nodos.

## 5. Secuencia lógica

### Encolar

1. Crear un nodo con el entero recibido.
2. Si `estaVacia()`, establecer `head` y `tail` en el nodo nuevo.
3. En otro caso, asignar el nodo a `tail.siguiente` y luego hacer que `tail` apunte al nuevo nodo.

### Desencolar

1. Comprobar que la cola no esté vacía; si lo está, lanzar `IllegalStateException`.
2. Guardar el valor de `head`.
3. Avanzar `head` a `head.siguiente`.
4. Si `head` quedó en `null`, asignar `null` a `tail`.
5. Devolver el valor guardado.

### Frente, búsqueda e impresión

- `frente` valida que haya un nodo y devuelve `head.valor`.
- `contiene` recorre con una referencia temporal desde `head`, comparando cada valor con el buscado; termina al encontrarlo o llegar a `null`.
- `imprimir` recorre con una referencia temporal e imprime los valores desde el frente hasta el final, sin cambiar los punteros de la cola.

## 6. Complejidad objetivo

| Operación | Tiempo | Espacio adicional |
|---|---:|---:|
| `encolar` | O(1) | O(1) por el nuevo nodo |
| `desencolar` | O(1) | O(1) |
| `frente` | O(1) | O(1) |
| `estaVacia` | O(1) | O(1) |
| `contiene` | O(n) | O(1) |
| `imprimir` | O(n) | O(1) |

## 7. Demostración sugerida (`Main`)

Crear una cola, comprobar que inicia vacía, encolar varios enteros (incluyendo uno negativo o cero), mostrar su contenido, consultar el frente, buscar un valor presente y uno ausente, desencolar en orden y vaciarla por completo. Después del último `desencolar`, volver a consultar `estaVacia`; finalmente, encolar de nuevo para verificar que la cola vacía puede reutilizarse correctamente.

## 8. Criterios de aceptación

- La solución usa nodos enlazados y mantiene exclusivamente las referencias necesarias `head` y `tail`.
- Se observan los comportamientos FIFO y todas las operaciones especificadas en el análisis.
- Tanto `head` como `tail` quedan en `null` al retirar el último elemento.
- La cola vuelve a funcionar si se encola después de quedar vacía.
- Las operaciones sobre una cola vacía que requieren un elemento producen un error explícito.
- Buscar e imprimir no destruyen ni reordenan los elementos.
- Las inserciones y extracciones en los extremos son O(1).