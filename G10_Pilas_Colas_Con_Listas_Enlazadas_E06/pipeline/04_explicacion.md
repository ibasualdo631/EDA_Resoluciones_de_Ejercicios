# Explicación: cola de atención bancaria

## 1. La regla de negocio define la estructura

En el banco, las personas se atienden en el orden en que llegaron: la primera que espera debe ser la primera atendida. Esa regla se llama **FIFO**, por *First In, First Out* (primero en entrar, primero en salir).

Por eso el sistema usa una **cola**:

- Las nuevas llegadas se agregan por el final.
- La atención retira al cliente del frente.
- La consulta mira al frente sin quitarlo.
- La impresión recorre la fila desde el frente hacia el final.

La estructura no solo almacena clientes: sus operaciones expresan directamente la regla de negocio. Una pila sería inadecuada porque funciona como LIFO (*Last In, First Out*): atendería primero a la última persona llegada. Una lista genérica permitiría insertar o quitar elementos en posiciones arbitrarias, por lo que el programa tendría que imponer el orden manualmente en cada operación. La cola limita las operaciones principales a los extremos correctos y hace más difícil romper el orden de llegada.

## 2. Representación mediante nodos enlazados

El programa implementa la cola usando nodos, en vez de un arreglo de tamaño fijo o `java.util.Queue`.

Cada nodo contiene:

1. La referencia a un objeto `Cliente`.
2. Una referencia al siguiente nodo de la fila.

La clase `SistemaBancario` guarda dos referencias a nodos:

- **`frente`** apunta al cliente que será atendido primero.
- **`ultimo`** apunta al cliente que llegó más recientemente.

La fila vacía se representa con ambas referencias en `null`. Cuando se agrega el primer cliente, las dos referencias apuntan al mismo nodo. Así se puede agregar al final y atender del frente en tiempo constante, **O(1)**. Imprimir la fila requiere recorrer todos los nodos, por lo que toma **O(n)**.

## 3. Una sola clase pública

Todo el programa está en `SistemaBancario.java`, pero tiene tres responsabilidades diferenciadas:

- `SistemaBancario` es la clase pública: coordina el menú, la interacción y las operaciones de la cola.
- `Cliente` es una clase anidada privada que representa los datos de una persona.
- `Nodo` es una clase anidada privada que enlaza a los clientes.

Las clases anidadas permiten cumplir el requisito de entregar una sola clase pública/archivo para ejecutar, y a la vez mantienen ocultos los detalles de los nodos: el usuario del sistema no puede manipular directamente sus enlaces.

## 4. Datos del cliente

Cada `Cliente` tiene tres atributos:

- `nombre`, de tipo `String`.
- `numeroTurno`, de tipo `int`.
- `motivoConsulta`, de tipo `String`.

Son `private final`: `private` restringe el acceso desde fuera de la clase y `final` evita que el dato se reasigne después de construir el objeto. El constructor recibe los tres datos y `toString()` genera una representación legible para la consola. Por ejemplo: `Turno 1 | Nombre: Ana | Motivo: Deposito`.

El contador `siguienteTurno` comienza en uno y se incrementa al completar el alta de un cliente. Se verifica que el valor que se asignará no exceda el máximo de `int`, el tipo elegido para almacenar el turno.

## 5. Recorrido del programa

### Inicio y lectura

`main` crea el sistema y un `BufferedReader` para leer texto desde la consola. El `try` con recursos cierra el lector automáticamente al terminar. `ejecutar` presenta un menú dentro de un ciclo `while`; un `switch` selecciona la acción correspondiente. La opción `5` termina el ciclo, y una entrada finalizada (lectura `null`) también detiene el programa.

Las operaciones del menú son:

1. Agregar un cliente.
2. Atender al siguiente.
3. Consultar quién sigue.
4. Imprimir la fila.
5. Salir.

### Agregar (`encolar`)

`agregarCliente` lee nombre y motivo mediante `leerDatoObligatorio`. Ese método quita espacios al principio y al final con `trim()` y vuelve a pedir el dato si queda vacío. Luego se crea el cliente con el próximo número de turno y se pasa a `encolar`.

`encolar` crea un nodo. Si no hay nodos, establece `frente` al nuevo. Si ya hay clientes, enlaza el nuevo nodo desde `ultimo.siguiente`. En ambos casos actualiza `ultimo`. De esta manera, toda nueva llegada queda detrás de las personas que ya esperaban.

`Objects.requireNonNull` impide encolar accidentalmente una referencia `null`, que no representaría a un cliente válido.

### Atender (`desencolar`)

`desencolar` guarda el cliente de `frente` y avanza `frente` al nodo siguiente. Devuelve el cliente retirado dentro de un `Optional`. Si la cola está vacía, devuelve `Optional.empty()` en lugar de intentar acceder a un nodo inexistente.

Si se atiende al único cliente, avanzar deja `frente` en `null`; por eso también se asigna `null` a `ultimo`. Esta actualización es importante: mantiene sincronizados los dos extremos y permite que la próxima llegada comience una cola nueva correctamente.

`atenderProximo` inspecciona el `Optional`: si contiene un cliente lo muestra como atendido; si no, informa que la fila está vacía.

### Consultar (`consultarSiguienteCliente`)

La consulta devuelve un `Optional` con el cliente que está en `frente`, o vacío si la cola no tiene nodos. No reasigna `frente`, `ultimo` ni ningún enlace. Así se puede preguntar quién sigue sin cambiar quién será atendido después.

### Imprimir (`imprimirFila`)

Si la fila no está vacía, la variable local `actual` empieza en `frente`. Un ciclo `while` muestra el cliente del nodo y avanza a `actual.siguiente` hasta llegar a `null`. Solo se desplaza la variable local: los enlaces de la cola no se cambian y los clientes siguen en el mismo orden.

## 6. Conceptos de Java utilizados

- **Clases y objetos:** `SistemaBancario` coordina; cada instancia de `Cliente` guarda los datos de una persona; cada `Nodo` conecta elementos.
- **Clases anidadas y encapsulación:** `Cliente` y `Nodo` están restringidos dentro de `SistemaBancario`; sus detalles no son parte de una interfaz pública.
- **Campos `private` y `final`:** protegen la representación y mantienen estables los datos del cliente.
- **Referencias y enlaces:** `siguiente`, `frente` y `ultimo` forman la lista enlazada que implementa la cola.
- **Condicionales (`if`):** diferencian la cola vacía de la no vacía y manejan entradas incompletas.
- **Ciclos (`while`):** mantienen el menú activo, vuelven a pedir datos vacíos y recorren la fila para imprimirla.
- **`switch`:** deriva cada opción del menú a su operación.
- **`Optional<Cliente>`:** expresa que una consulta o extracción puede no encontrar un cliente, sin usar un resultado ficticio.
- **`BufferedReader` y excepciones:** leen líneas de texto; `IOException` se propaga desde los métodos de lectura en vez de ocultar un error de entrada/salida.
- **`try` con recursos:** cierra el lector al finalizar la aplicación.

## 7. Errores comunes que se evitan

1. **Atender desde el final:** invertiría la regla FIFO; la extracción se hace desde `frente`.
2. **Agregar en el frente:** haría que el último llegado salte a la primera posición; las altas se enlazan después de `ultimo`.
3. **Olvidar actualizar `ultimo` al retirar el último nodo:** dejaría una referencia obsoleta; ambos extremos vuelven a `null`.
4. **Eliminar al consultar:** cambiaría a quién se atiende después; consultar solo lee el nodo frontal.
5. **Imprimir extrayendo repetidamente:** vaciaría la fila; la impresión recorre con una referencia local.
6. **Usar datos de un nodo inexistente:** cada operación que necesita un cliente verifica primero si la fila está vacía.
7. **Aceptar cadenas vacías:** nombre y motivo se validan antes de crear al cliente y consumir un turno.

## 8. Mejoras posibles

Si el sistema creciera, se podría extraer la cola y el cliente a archivos separados, añadir pruebas unitarias automatizadas, validar opciones de menú sin distinguir mayúsculas o espacios adicionales, soportar persistencia y generar números de turno persistentes entre ejecuciones. Esas funciones no se agregaron porque exceden el alcance del ejercicio, que practica una cola enlazada FIFO en una aplicación sencilla de consola.

## 9. Validación realizada

El programa se compiló con `javac 21.0.10` y se ejecutó con entradas simuladas. Se comprobó la consulta, atención e impresión de una cola vacía; el orden de atención de tres clientes; que consultar e imprimir no extraen clientes; que se puede agregar y atender otro cliente después de vaciar la fila; y que una opción o campos inválidos no impiden seguir usando el menú. Los flujos verificados finalizaron con código de salida `0`; los detalles están en [`03_pruebas.md`](./03_pruebas.md).