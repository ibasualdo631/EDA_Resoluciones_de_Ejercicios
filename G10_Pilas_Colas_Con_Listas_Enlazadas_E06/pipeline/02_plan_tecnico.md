# Plan técnico: cola de atención bancaria

## 1. Organización propuesta

Implementar una aplicación de consola Java separada en estas clases:

- **`Cliente`**: modelo de datos de una persona que espera atención.
- **`ColaClientes`**: estructura FIFO implementada con nodos enlazados; mantiene y ofrece las operaciones de la fila.
- **`SistemaBancario`**: punto de entrada y capa de interacción; presenta el menú, recibe datos, asigna turnos y comunica resultados.

La cola será una implementación propia, no `java.util.Queue`, para practicar listas enlazadas como corresponde al ejercicio. Los nodos serán una clase interna privada de `ColaClientes`, de modo que el resto del programa no pueda alterar directamente los enlaces.

## 2. Modelo de datos

### `Cliente`

Mantener los datos de cada cliente como atributos privados:

| Atributo | Tipo | Uso |
|---|---|---|
| `nombre` | `String` | Nombre del cliente. |
| `numeroTurno` | `int` | Turno asignado al llegar. |
| `motivoConsulta` | `String` | Consulta o trámite solicitado. |

Constructor propuesto: `Cliente(String nombre, int numeroTurno, String motivoConsulta)`. Proveer getters y un método de presentación, por ejemplo `String toString()`, para mostrar los tres datos sin exponer los atributos.

El objeto se mantiene inmutable después de su creación: atributos `final`, sin setters. Esto evita que los datos del turno cambien mientras el cliente está en la fila.

### Nodos y referencias de la cola

Cada nodo contiene un `Cliente` y una referencia al siguiente nodo. `ColaClientes` mantiene dos referencias:

- `frente`: primer cliente pendiente de atención.
- `final`: último cliente incorporado.

Cuando la cola está vacía, ambas referencias son `null`. Si contiene un solo nodo, `frente` y `final` apuntan a ese mismo nodo.

## 3. Interfaces y responsabilidades

### `ColaClientes`

- `void encolar(Cliente cliente)`: agrega un nodo al final. Rechaza una referencia `null` mediante `Objects.requireNonNull`.
- `Optional<Cliente> desencolar()`: retira y devuelve el cliente del frente; devuelve `Optional.empty()` si no hay clientes.
- `Optional<Cliente> consultarSiguiente()`: devuelve el cliente del frente sin retirarlo; devuelve vacío si la fila está vacía.
- `boolean estaVacia()`: indica si `frente == null`.
- `void imprimirFila()`: recorre desde `frente` hasta el final y muestra cada cliente en orden. Si no hay nodos, informa que la fila está vacía.

Invariantes: `frente == null` si y solo si `final == null`; cuando `final` existe, `final.siguiente == null`.

### `SistemaBancario`

- `public static void main(String[] args)`: crea la cola y ejecuta el menú.
- Mantiene el contador del próximo turno y lo incrementa cuando registra un cliente.
- Recoge nombre y motivo, crea el objeto `Cliente`, lo agrega al final y confirma el turno.
- Para atender, consulta el resultado de `desencolar()` y muestra los datos o un mensaje de fila vacía.
- Para consultar, usa `consultarSiguiente()` sin modificar la cola.
- Para imprimir, delega en `imprimirFila()`.
- Permite repetir operaciones hasta seleccionar la opción de salida.

El menú debe incluir las cuatro operaciones del análisis: agregar cliente, atender al próximo, consultar quién sigue e imprimir la fila actual. La validación de entradas se limita a los campos requeridos y a opciones de menú válidas; no se incorporan prioridades ni operaciones fuera del alcance.

## 4. Secuencia de ejecución

1. Inicializar una `ColaClientes` vacía y el contador de turnos.
2. Mostrar el menú y leer la opción elegida.
3. Ejecutar la operación seleccionada:
   - **Agregar:** leer los datos, asignar el siguiente turno, construir `Cliente` y llamar a `encolar`.
   - **Atender:** llamar a `desencolar`; mostrar el cliente si el `Optional` contiene uno o informar que no hay clientes.
   - **Consultar:** llamar a `consultarSiguiente`; mostrar el cliente sin extraerlo o informar que la fila está vacía.
   - **Imprimir:** recorrer la cola sin modificarla.
4. Volver al menú hasta que el empleado elija salir.

## 5. Cómo se preserva el orden cronológico (FIFO)

Cada llegada se enlaza después del nodo señalado por `final`; luego `final` pasa a señalar al nuevo nodo. La atención siempre obtiene y elimina el nodo señalado por `frente`, y el frente avanza a su sucesor. Por tanto, los clientes existentes no se saltan ni se reordenan: el primero que se incorpora permanece primero hasta ser atendido. Consultar e imprimir solo recorren o leen los nodos y no alteran los enlaces.

Al retirar el único nodo, se actualizan tanto `frente` como `final` a `null`. Este caso mantiene las invariantes y permite que una llegada posterior vuelva a iniciar la fila correctamente.

## 6. Costos esperados

Con referencias a frente y final, agregar y atender cuestan **O(1)**. Consultar el próximo cliente también cuesta **O(1)**. Imprimir la fila cuesta **O(n)**, donde `n` es la cantidad de clientes, porque se visita cada nodo una vez. La memoria utilizada es **O(n)**.

## 7. Pruebas funcionales que deben cubrirse

- Encolar varios clientes y comprobar que la consulta devuelve al primero sin quitarlo.
- Atender consecutivamente y verificar el orden exacto de llegada.
- Imprimir una fila con varios elementos y confirmar que queda intacta.
- Consultar, atender e imprimir con la cola vacía.
- Atender el único cliente y luego encolar otro, comprobando la transición vacía/no vacía.
- Confirmar que el número de turno aumenta al agregar clientes.