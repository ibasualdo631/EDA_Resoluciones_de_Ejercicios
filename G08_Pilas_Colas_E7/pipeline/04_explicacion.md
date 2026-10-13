# FIFO frente a LIFO en el sistema de turnos

La diferencia no es solo cómo se llama a la estructura: determina qué persona será atendida a continuación.

## FIFO: primero en llegar, primero en salir

FIFO (*First In, First Out*) es la disciplina de una **cola**. La persona que llegó antes queda al frente y se atiende antes que quienes llegaron después. Es el comportamiento esperado en una fila común y el que implementa `ColaTurnos`.

Por ejemplo, si llegan Ana, Bruno y Carla:

| Operación | Próximo turno |
|---|---|
| Llegan Ana, Bruno y Carla | Ana |
| Se atiende una persona | Ana |
| Próximo turno | Bruno |
| Se atiende otra persona | Bruno |
| Próximo turno | Carla |

En el código, cada `Nodo` apunta al siguiente. `frente` señala a quien lleva más tiempo esperando y `fin` a quien llegó último. `encolar` enlaza el nodo nuevo después de `fin`; `desencolar` retira el nodo de `frente`. Por eso, una llegada no cambia quién es el próximo en ser atendido.

Con referencias a ambos extremos, insertar y atender cuestan **O(1)**: el trabajo no crece con la cantidad de personas en espera. Consultar el frente también cuesta **O(1)**. En cambio, `listarPendientes` recorre los nodos, así que cuesta **O(n)** para n turnos. La memoria usada es **O(n)**.

## LIFO: último en llegar, primero en salir

LIFO (*Last In, First Out*) es la disciplina de una **pila**. Cada elemento nuevo se coloca arriba y se retira primero ese mismo elemento. Con las mismas llegadas —Ana, Bruno y Carla— la atención sería Carla, luego Bruno y finalmente Ana.

Esto puede ser apropiado cuando importa procesar primero lo más reciente, como al deshacer acciones en un editor o al volver de llamadas anidadas. Para una fila de atención suele ser injusto: las llegadas nuevas se adelantan a quienes esperan desde hace más tiempo. Si siguen llegando personas, las más antiguas incluso podrían esperar indefinidamente (inanición).

Una pila enlazada puede hacer inserción y retiro en **O(1)** usando un único extremo como cima. Pero no basta con intercambiar el orden de impresión: hay que cambiar qué extremo se inserta y de cuál se retira para que las operaciones realmente sigan LIFO. En esta implementación, insertar al final y retirar del final con una lista simplemente enlazada exigiría recorrerla para encontrar el nodo anterior (**O(n)**). Una opción eficiente es tratar `frente` como la cima y apilar/desapilar siempre allí; otra es usar una estructura enlazada doble.

## Resumen práctico

| Aspecto | FIFO — cola de turnos | LIFO — pila |
|---|---|---|
| Orden de atención | Del más antiguo al más nuevo | Del más nuevo al más antiguo |
| Operación típica | Encolar al final, desencolar del frente | Apilar y desapilar por la cima |
| Uso natural | Filas, solicitudes pendientes, atención justa por llegada | Deshacer, llamadas anidadas, trabajo reciente primero |
| Riesgo en este problema | No adelanta a quienes esperan | Puede postergar indefinidamente a los más antiguos |

Por eso el sistema modela la atención con FIFO: los números correlativos de turno identifican las llegadas y la cola mantiene ese orden al atender. Cambiar a LIFO no sería una mera optimización; alteraría la regla de atención y, por lo tanto, el comportamiento esperado del problema.