# Plan técnico — Lista enlazada simple

## Arquitectura orientada a objetos

La solución se organiza en una clase pública contenedora `ListaEnlazada` y una clase `Nodo` anidada, estática y privada. La lista es propietaria de sus nodos y encapsula el acceso a los enlaces.

```java
public class ListaEnlazada {
    private static class Nodo {
        private int dato;
        private Nodo siguiente;

        private Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo head;
    private int size;
}
```

`Nodo` es `static` porque cada nodo solo necesita guardar el entero y el enlace al siguiente nodo; no requiere una referencia implícita a una instancia de `ListaEnlazada`. La clase contenedora es la única que administra `head`, `size` y las conexiones.

## Invariantes de representación

1. Lista vacía: `head == null` y `size == 0`.
2. Lista no vacía: `head` referencia el primer nodo.
3. El último nodo alcanzable tiene `siguiente == null`.
4. `size` es igual al número de nodos alcanzables siguiendo `siguiente` desde `head`.
5. No se exponen nodos ni enlaces a los clientes; las modificaciones se hacen mediante los métodos públicos.

No se agrega un puntero `tail`: es innecesario para los requisitos y haría que la inserción final tuviera otro estado que mantener. Por ello, insertar al final requiere recorrer los enlaces desde `head`.

## Firmas y contratos

Todas las operaciones públicas son instancias de `ListaEnlazada`. Para los métodos que reciben un `int`, no hay una precondición sobre el valor: cualquier entero, incluso cero o negativo, es válido. No se requiere validación de `null`.

| Firma | Precondición | Efecto / postcondición | Complejidad temporal |
|---|---|---|---|
| `public ListaEnlazada()` | Ninguna. | Inicializa `head = null` y `size = 0`. | O(1) |
| `public void insertarAlInicio(int dato)` | Instancia válida de la lista; `dato` puede ser cualquier `int`. | Crea un nodo, enlaza su `siguiente` con el `head` anterior, asigna el nodo a `head` e incrementa `size` exactamente una vez. | O(1) |
| `public void insertarAlFinal(int dato)` | Instancia válida de la lista; `dato` puede ser cualquier `int`. | Si está vacía, asigna el nodo nuevo a `head`; si no, recorre hasta el último nodo y enlaza allí el nuevo. Incrementa `size` exactamente una vez. | O(n), peor caso |
| `public void imprimir()` | Instancia válida de la lista. | Recorre desde `head` hasta `null` y muestra los datos en orden. Si está vacía, no hay datos que mostrar. El formato textual concreto queda a elección de la implementación. | O(n) |
| `public boolean estaVacia()` | Instancia válida de la lista. | Devuelve `true` si `head == null`; en caso contrario, devuelve `false`. | O(1) |
| `public int getSize()` | Instancia válida de la lista. | Devuelve el atributo `size` sin recorrer los nodos. | O(1) |

`n` representa la cantidad de elementos antes de iniciar la operación. La inserción al final es O(n) en el peor caso; para la lista vacía la rama de inserción es O(1), pero no cambia la cota general del método.

## Secuencia de inserción

### Inserción al inicio

1. Crear `nuevo = new Nodo(dato)`; el constructor deja `nuevo.siguiente == null`.
2. Asignar `nuevo.siguiente = head`, conservando la referencia al comienzo anterior.
3. Asignar `head = nuevo`.
4. Incrementar `size` una vez.

Esto también resuelve la lista vacía: el antiguo `head` es `null`, así que el nodo nuevo queda como único nodo y su enlace final sigue siendo `null`. No se necesita recorrer la lista.

### Inserción al final

1. Crear el nodo nuevo.
2. Si `head == null`, asignarlo a `head`.
3. De lo contrario, inicializar una referencia local `actual = head`; avanzar con `actual = actual.siguiente` mientras `actual.siguiente != null`.
4. Asignar `actual.siguiente = nuevo`.
5. Incrementar `size` una vez, una vez establecido el enlace.

La referencia local permite recorrer la lista sin modificar `head`. En una lista de un nodo, el ciclo no avanza y el enlace nuevo se establece directamente sobre ese único nodo.

## Plan de complejidad

| Operación | Tiempo | Espacio auxiliar | Justificación |
|---|---:|---:|---|
| Crear lista | O(1) | O(1) | Inicializa dos atributos escalares/referencias. |
| Insertar al inicio | O(1) | O(1) | Solo crea y conecta un nodo y actualiza dos atributos. |
| Insertar al final (con `head`, sin `tail`) | O(n) | O(1) | En el peor caso visita todos los nodos existentes; usa una referencia local de recorrido. |
| Imprimir | O(n) | O(1) | Visita cada nodo una vez, sin almacenar una copia. |
| Consultar vacío | O(1) | O(1) | Comprueba `head`. |
| Consultar tamaño | O(1) | O(1) | Lee `size`, que se mantiene durante las inserciones. |

El almacenamiento total de la lista es O(n): cada elemento ocupa un nodo con un entero y una referencia.

## Verificaciones de implementación

- Construir una lista y comprobar `estaVacia() == true` y `getSize() == 0`.
- Insertar el primer elemento por cada método de inserción y comprobar que `head` representa el único elemento y el tamaño es uno.
- Insertar varios elementos al inicio y al final, comprobar su orden mediante `imprimir()` y que `getSize()` coincide con el número insertado.
- Probar una lista de un nodo al insertar al final, confirmando que el enlace antes nulo apunta al nodo nuevo.
- Revisar que toda inserción incrementa `size` una sola vez y que ninguna operación de consulta modifica la estructura.