# Explicación — Sistema de turnos

## ¿Por qué FIFO y no LIFO?

**FIFO** (*First In, First Out*: primero en entrar, primero en salir) describe
una cola. Si llegan Ana, Bruno y Carla, se atienden en ese mismo orden. Es el
modelo natural para una fila de atención: respeta la antigüedad de cada turno y
evita que una persona que acaba de llegar se adelante a quienes ya esperan.

**LIFO** (*Last In, First Out*: último en entrar, primero en salir) describe
una pila. Con esas mismas llegadas, se atendería primero a Carla, luego a Bruno
y por último a Ana. LIFO puede servir para deshacer acciones o recorrer
elementos en sentido inverso, pero normalmente sería injusto en este sistema:
cada nueva llegada retrasaría a las personas que ya estaban esperando.

La diferencia no es solo de política, sino también de las operaciones que
resultan naturales:

| Estructura | Alta | Atención/extracción | Orden de salida |
|---|---|---|---|
| Cola FIFO | Agrega al final | Quita del frente | Orden de llegada |
| Pila LIFO | Apila en la cima | Quita de la cima | Orden inverso al de llegada |

La cola enlazada de este código agrega y atiende en **O(1)**: el tiempo de esas
operaciones no crece con la cantidad de personas pendientes. Si se modelara una
pila enlazada, apilar y desapilar también podrían ser O(1), pero el
comportamiento sería LIFO. Por lo tanto, cambiar FIFO por LIFO no es una
optimización equivalente: cambia quién recibe atención primero.

## Cómo está organizado el código

### `SistemaTurnos`

Es la interfaz pública para trabajar con los turnos. Mantiene una instancia de
`ColaTurnos` y el campo `siguienteCodigo`.

- `registrarCliente` valida y crea un `Turno`, lo agrega a la cola y aumenta el
  próximo código. El contador es `long` para detectar cuando ya se superó el
  máximo código representable como `int`; en ese caso informa el error con
  `IllegalStateException`.
- `atenderSiguiente` delega la extracción del turno del frente.
- `consultarSiguiente` permite mirar quién sigue sin atenderlo todavía.
- `listarPendientes` devuelve una lista con los turnos en espera.
- `estaVacio` y `obtenerCantidadPendiente` exponen consultas sencillas sobre el
  estado de la cola.

### `Turno`

Cada turno guarda un código y el nombre del cliente. Sus campos son `final`,
así que no se pueden reasignar después de construir el objeto. El constructor
rechaza códigos no positivos y nombres nulos o en blanco; además, guarda el
nombre sin espacios sobrantes en los extremos. Así, un turno válido mantiene
sus datos coherentes durante toda su vida.

### `ColaTurnos` y `NodoTurno`

`ColaTurnos` implementa la fila con nodos enlazados. Cada `NodoTurno` contiene
una referencia a un `Turno` y una referencia al siguiente nodo. La cola guarda:

- `frente`: el nodo que representa a la persona que será atendida primero.
- `fin`: el último nodo; allí se agrega la próxima llegada.
- `cantidad`: cuántos turnos están pendientes.

Al encolar, si la cola estaba vacía, el nodo nuevo pasa a ser tanto `frente`
como `fin`. Si ya había elementos, se enlaza después de `fin` y se actualiza
`fin`. Al atender, se guarda el turno del frente, se mueve `frente` al nodo
siguiente y se reduce `cantidad`. Si no queda ninguno, también se pone `fin` en
`null`: de esa forma ambos extremos vuelven a representar correctamente una
cola vacía.

Este diseño conserva dos invariantes importantes:

1. Una cola vacía tiene `frente == null` y `fin == null`.
2. En una cola no vacía, recorrer desde `frente` siguiendo `siguiente` produce
   los turnos en orden de llegada, y `fin` apunta al último.

### ¿Qué pasa si se intenta operar con la cola vacía?

`atender` y `consultarFrente` comprueban `estaVacia()` antes de acceder a un
nodo. Si no hay turnos, lanzan `NoSuchElementException` con un mensaje
explícito; no devuelven `null` ni alteran el estado. En `main`, el ciclo
comprueba `estaVacio()` antes de atender, por eso la simulación no provoca ese
error.

### Costos algorítmicos

| Operación | Costo | Motivo |
|---|---:|---|
| Registrar / encolar | O(1) | `fin` permite enlazar directamente el nodo nuevo. |
| Atender / desencolar | O(1) | Se avanza directamente desde `frente`. |
| Consultar el siguiente | O(1) | Se lee el valor de `frente`. |
| Consultar cantidad o vacío | O(1) | Se consulta un campo o una referencia. |
| Listar pendientes | O(n) | Se recorre una vez cada uno de los `n` nodos. |

La lista que produce `listarPendientes` es una copia independiente; recorrerla
no modifica la cola. La operación usa O(n) espacio adicional para guardar esa
copia.

## Recorrido de la simulación

El método `main` registra a Ana, Bruno, Carla y Diego en ese orden. Cada alta
asigna un código secuencial y encola el turno al final. Después lista los
pendientes (todavía en orden FIFO) y finalmente los atiende mientras la cola
no esté vacía. La salida de atención conserva el mismo orden de llegada y la
cantidad pendiente termina en cero.

## Pregunta interesante: ¿por qué guardar `fin` si ya se puede recorrer desde
`frente`?

**Respuesta:** porque `fin` evita recorrer todos los nodos cada vez que llega
un cliente. Sin esa referencia, para agregar al final habría que empezar en
`frente` y avanzar hasta el último nodo, lo que costaría O(n) por alta. Al
mantener `fin`, la cola enlaza el nuevo nodo en O(1). El costo de esa rapidez es
mantener correctamente ambos extremos, en particular restablecer `fin` a
`null` cuando se atiende al único turno.

## Errores comunes que evita esta implementación

- Atender desde `fin` invertiría el orden y produciría un comportamiento LIFO.
- Olvidar actualizar `fin` al quitar el último nodo dejaría una referencia
  obsoleta y podría romper la siguiente alta.
- Devolver `null` cuando la cola está vacía haría más fácil ignorar el error;
  aquí se informa con una excepción explícita.
- Exponer los nodos o la lista interna permitiría modificar accidentalmente la
  estructura; `NodoTurno` es privado y el listado es una copia.
- Aumentar el código antes de que el turno se registre con éxito podría
  consumir números para solicitudes fallidas; el incremento ocurre después
  del encolado.

## Posibles extensiones

Si el problema pidiera atender urgencias antes que los turnos comunes, FIFO
estricto ya no alcanzaría: habría que definir una política de prioridades y
cómo resolver empates sin perder las garantías deseadas de orden. Si pidiera
deshacer la última operación, una pila LIFO sería apropiada para ese historial,
pero no debería sustituir a la cola que representa a los clientes en espera.