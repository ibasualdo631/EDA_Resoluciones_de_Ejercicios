# Plan técnico — Sistema de turnos

## 1. Decisión de diseño

El sistema modelará cada solicitud como un objeto `Turno` y almacenará esos objetos en una cola enlazada FIFO. La cola mantiene referencias al primer y al último nodo:

- `frente`: turno que se atenderá a continuación.
- `final`: turno agregado más recientemente.
- `cantidad`: total de turnos pendientes.

Al encolar se enlaza el nuevo nodo después de `final`; al atender se retira `frente`. Por lo tanto, los turnos salen en el mismo orden en que ingresaron. No se agregan prioridades ni más de una fila.

Se elige una cola enlazada para no imponer una capacidad máxima fija. Tanto el alta como la atención cuestan O(1); recorrer o mostrar todos los pendientes cuesta O(n).

## 2. Tipos y responsabilidades

### `Turno`

Representa la solicitud que espera atención; es el tipo de elemento almacenado en la cola.

```text
Turno
  - codigo: Entero positivo
  - nombreCliente: Texto

  + crear(codigo: Entero, nombreCliente: Texto) -> Resultado<Turno, ErrorValidacion>
  + obtenerCodigo() -> Entero
  + obtenerNombreCliente() -> Texto
```

`Turno` valida sus propios datos al construirse. El código es asignado secuencialmente por la capa de interacción/controlador; el nombre se recorta y no puede quedar vacío. No se rechazan nombres repetidos, ya que el análisis no define esa restricción.

### `NodoTurno` (detalle interno)

```text
NodoTurno
  - valor: Turno
  - siguiente: NodoTurno o nulo
```

No se expone al usuario del sistema. Solo `ColaTurnos` crea, enlaza y elimina nodos.

### `ColaTurnos`

```text
ColaTurnos
  - frente: NodoTurno o nulo
  - final: NodoTurno o nulo
  - cantidad: Entero no negativo

  + encolar(turno: Turno) -> Resultado<Vacío, ErrorValidacion>
  + atender() -> Resultado<Turno, ColaVacia>
  + consultarFrente() -> Resultado<Turno, ColaVacia>
  + estaVacia() -> Booleano
  + obtenerCantidad() -> Entero
  + listarPendientes() -> Lista<Turno>
```

`encolar` agrega al final y no permite un turno nulo. `atender` devuelve y quita el turno del frente. `consultarFrente` devuelve el siguiente turno sin modificar la cola. Ante cola vacía, ambas operaciones de lectura/atención devuelven `ColaVacia` explícitamente y dejan el estado intacto. `listarPendientes` devuelve una copia en orden FIFO, sin exponer nodos ni permitir modificar la estructura interna.

## 3. Invariantes de la estructura

Después de cada operación válida:

1. `cantidad >= 0`.
2. `cantidad == 0` si y solo si `frente == nulo` y `final == nulo`.
3. Si `cantidad > 0`, `frente` y `final` no son nulos.
4. `final.siguiente == nulo`.
5. Si queda un solo elemento, `frente == final`.
6. Los elementos se recorren desde `frente` en orden de llegada.

Al atender el único elemento, se actualizan ambos extremos a nulo y se decrementa `cantidad`. Al agregar a una cola vacía, el nuevo nodo pasa a ser tanto `frente` como `final`.

## 4. Validaciones y errores

- El nombre del cliente no puede ser nulo ni estar vacío o compuesto solo por espacios; se almacena recortado.
- El código de turno debe ser positivo. El controlador incrementa el contador solo cuando registra correctamente el turno.
- `encolar` rechaza una referencia nula; esta validación protege la estructura aunque el flujo normal cree turnos mediante `Turno.crear`.
- `atender` y `consultarFrente` detectan la cola vacía antes de acceder a un nodo y devuelven `ColaVacia`, sin eliminar ni modificar nada.
- No se fija máximo de turnos, ni se validan duplicados de nombre: no son reglas definidas en el análisis.
- Los errores de entrada se informan al usuario y se solicita corregir el dato; no se continúa con un turno inválido.

## 5. Flujo de interacción

1. Inicializar una `ColaTurnos` vacía y el siguiente código disponible.
2. Al registrar: leer el nombre, crear el `Turno`, validar y encolarlo. Confirmar el código asignado.
3. Al atender: invocar `atender`; mostrar cliente y código si tuvo éxito, o informar que no hay personas esperando.
4. Al consultar pendientes: recorrer la copia que devuelve `listarPendientes`, en orden FIFO; si está vacía, informar que no hay turnos.
5. Repetir las acciones hasta que el usuario elija salir.

La interfaz puede ofrecer opciones de registrar, atender, listar y salir. Los textos exactos de menú/mensajes quedan a criterio de la implementación porque no están especificados.

## 6. Pruebas de aceptación

- **Orden FIFO:** encolar A, B y C; atender debe devolver A, luego B y luego C.
- **Alta en cola vacía:** encolar A debe establecer `frente == final` y cantidad 1.
- **Vaciar cola:** tras atender el único turno, la cola debe quedar vacía, con ambos extremos nulos y cantidad 0.
- **Atender vacía:** devuelve `ColaVacia` y conserva el estado vacío.
- **Consultar vacía:** devuelve `ColaVacia` y conserva el estado vacío.
- **Nombre inválido:** nulo, vacío o solo espacios no produce un turno ni consume un código.
- **Encolar nulo:** devuelve error y no cambia cantidad ni extremos.
- **Consulta sin mutación:** consultar el frente y listar pendientes mantienen el mismo orden y cantidad.
- **Reutilización:** después de vaciar la cola, encolar otro turno debe volver a inicializar correctamente ambos extremos.

## 7. Supuestos y límites

Se asume una única fila FIFO sin prioridades ni citas con horario. Se propone generar códigos positivos secuenciales dentro de la sesión; si la consigna requiere que los ingrese el usuario o que persistan entre ejecuciones, esa regla deberá confirmarse y ajustarse. El diseño especifica contratos independientemente del lenguaje; las firmas deberán traducirse a la sintaxis y convenciones del lenguaje elegido.