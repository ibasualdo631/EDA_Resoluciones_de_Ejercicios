# Importancia de las colas en los sistemas operativos

## Idea principal

Una cola es una estructura de datos que organiza elementos siguiendo la regla FIFO (*First In, First Out*): el primero que entra es el primero que sale. Los elementos nuevos se agregan al final y el próximo elemento a atender se toma del frente.

Esta regla resulta útil en un sistema operativo porque muchos recursos trabajan a una velocidad distinta de la que tienen las aplicaciones que los solicitan. Una aplicación puede producir información rápidamente, mientras que un periférico —por ejemplo, una impresora— la consume más lentamente y de forma secuencial. La cola sirve como zona de espera entre ambos.

## Buffering y periféricos

El *buffering* consiste en guardar temporalmente datos pendientes para que un productor y un consumidor no tengan que operar exactamente al mismo ritmo. En el caso de la impresión:

1. Una aplicación entrega un trabajo de impresión al sistema.
2. El sistema de impresión guarda el trabajo pendiente —a menudo en disco mediante un spooler, y también puede usar buffers en memoria— en lugar de exigir que la impresora lo atienda inmediatamente.
3. La impresora toma los trabajos de la cola cuando queda disponible y los procesa de manera secuencial.
4. Mientras tanto, las aplicaciones pueden continuar con otras tareas y pueden enviarse más trabajos.

Así, la cola desacopla temporalmente a quien solicita el servicio del dispositivo que lo brinda. También permite absorber ráfagas de solicitudes, mantener un orden comprensible y compartir un periférico entre varios programas o usuarios. Si un periférico está ocupado, el sistema puede registrar el trabajo y atenderlo después, en vez de perderlo o hacer que la aplicación controle directamente cada instante de la impresión.

El buffering no vuelve más rápida a la impresora: administra la espera y evita que las diferencias de velocidad entre productor y dispositivo detengan innecesariamente a las aplicaciones. Tampoco garantiza que todo sistema real atienda siempre estrictamente en FIFO. Puede haber prioridades, cancelaciones, reintentos o selección de otra impresora; FIFO es una política básica y predecible para explicar el caso.

## Relación con `ColaImpresion.java`

El programa representa los trabajos pendientes con objetos `Documento` y mantiene su orden en una cola enlazada:

- `frente` señala el nodo del documento que lleva más tiempo esperando.
- `fin` señala el último nodo, donde se agrega el próximo documento.
- `cantidad` registra cuántos documentos siguen pendientes.
- Cada `Nodo` contiene un documento y una referencia `siguiente` al próximo nodo.

La lista enlazada permite agregar al final y retirar desde el frente sin desplazar los demás trabajos. Como se mantienen referencias tanto al frente como al final, `encolar` y `procesarSiguiente` realizan un número constante de operaciones: su costo es O(1), independientemente de la cantidad de documentos en espera.

### Encolar un trabajo

`encolar(Documento documento)` agrega un nuevo nodo al final. Si la cola estaba vacía, ese nodo pasa a ser a la vez `frente` y `fin`. Si ya había elementos, el antiguo último nodo enlaza al nuevo mediante `fin.siguiente`; después se actualiza `fin` y se incrementa `cantidad`.

El rechazo de un documento nulo evita insertar un trabajo inexistente. El constructor de `Documento` también valida el identificador, el nombre y la cantidad de páginas, para que cada trabajo tenga datos razonables desde el momento en que se crea.

### Procesar el próximo trabajo

`procesarSiguiente()` devuelve y retira el documento ubicado en `frente`. Luego adelanta `frente` al siguiente nodo y reduce `cantidad`. Si se retiró el último documento, `frente` queda en `null`; en ese caso también se pone `fin` en `null`. Mantener ambas referencias vacías es importante: una cola vacía debe poder reutilizarse correctamente más adelante.

Si no hay documentos, el método lanza `NoSuchElementException`, en vez de intentar leer un nodo inexistente o devolver un documento ficticio. `verFrente()` aplica la misma protección al consultar el siguiente trabajo sin retirarlo. `estaVacia()` y `obtenerCantidad()` permiten observar el estado de la cola.

## Recorrido del ejemplo

El `main` encola, en este orden:

1. `Informe final.pdf`
2. `Presentacion.pptx`
3. `Apuntes.pdf`

El ciclo de procesamiento los retira exactamente en ese mismo orden. Ese comportamiento muestra FIFO: el tercer documento no se adelanta a los dos que ya estaban esperando. Al finalizar, la cola está vacía y `obtenerCantidad()` informa cero documentos pendientes.

## Conceptos de Java que aparecen

- **Clases y objetos:** `ColaImpresion` administra la estructura; cada `Documento` representa un trabajo concreto.
- **Clases anidadas:** `Documento` y `Nodo` están declaradas dentro de `ColaImpresion`. `Documento` es pública para poder crear trabajos desde el programa, mientras que `Nodo` es privada porque es un detalle interno de implementación.
- **Encapsulamiento:** los enlaces y el contador son privados; se modifica el estado mediante operaciones de la cola.
- **Referencias y `null`:** se usan para representar que no hay un nodo al frente o al final.
- **Ciclos:** el `for` envía todos los documentos y el `while` procesa hasta que la cola queda vacía.
- **Excepciones:** `IllegalArgumentException` informa datos inválidos y `NoSuchElementException` señala que se pidió un elemento cuando no había ninguno.
- **Inmutabilidad:** los atributos de `Documento` son `final`; una vez construido, el trabajo no cambia mientras espera.
- **Complejidad:** insertar y retirar cuestan O(1); consultar si está vacía y obtener la cantidad también cuestan O(1).

## Límite del ejemplo y mejoras posibles

Este programa simula la administración de los trabajos; no se comunica con una impresora real, no guarda trabajos en disco y no contempla concurrencia, prioridades, cancelación ni fallas del dispositivo. Un sistema de impresión real necesita además esas políticas y mecanismos. Podrían agregarse como ampliaciones si la consigna los pide, pero no son necesarios para entender la función básica de una cola: organizar solicitudes pendientes y entregarlas a un recurso cuando este queda disponible.