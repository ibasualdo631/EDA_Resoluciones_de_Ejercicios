# Plan técnico — `ColaCircular`

## 1. Alcance y decisiones

Diseñar una cola FIFO de capacidad fija respaldada por un arreglo. La cola no desplaza elementos, no se redimensiona y reutiliza las posiciones desocupadas al avanzar circularmente los índices. El plan define la estructura y sus contratos; los nombres y tipos concretos deberán adaptarse al lenguaje y las convenciones del proyecto.

Usar tres datos de estado:

| Campo | Significado |
|---|---|
| `elementos[0..N-1]` | Arreglo de capacidad fija `N`, con `N > 0`. |
| `front` | Índice del próximo elemento a extraer. |
| `rear` | Índice de la próxima posición donde insertar. |
| `count` | Cantidad actual de elementos, entre `0` y `N`, inclusive. |

`front` y `rear` se mantienen en el intervalo `[0, N-1]`. El contador hace explícitos los estados incluso cuando ambos índices coinciden: `front == rear` puede representar tanto cola vacía como llena.

## 2. Inicialización e invariantes

Al construir la cola con capacidad `N`:

- Validar `N > 0`; si no se cumple, rechazar la construcción mediante el mecanismo de error del proyecto.
- Reservar el arreglo una sola vez con tamaño `N`.
- Inicializar `front = 0`, `rear = 0` y `count = 0`.

Mantener estos invariantes después de cada operación:

1. `0 <= front < N` y `0 <= rear < N`.
2. `0 <= count <= N`.
3. La cola está vacía si y solo si `count == 0`.
4. La cola está llena si y solo si `count == N`.
5. Los elementos lógicos, en orden FIFO, ocupan las `count` posiciones desde `front`, avanzando módulo `N`.
6. `rear` identifica la siguiente posición libre para inserción; cuando `count == N`, no se permite escribir allí hasta extraer un elemento.

## 3. Aritmética modular

Definir una única regla de avance para posiciones válidas:

```text
avanzar(i) = (i + 1) % N
```

Aplicaciones:

```text
front = avanzar(front)  // después de extraer
rear  = avanzar(rear)   // después de insertar
```

Para cualquier `i` en `[0, N-1]`, el resultado también está en ese intervalo. En particular, si `i = N-1`, entonces `(N-1 + 1) % N = 0`; el índice vuelve al inicio en tiempo constante y permite reutilizar los huecos iniciales. Ejemplo con `N = 5`: `rear = 4` avanza a `(4 + 1) % 5 = 0`.

No actualizar los índices con búsqueda, recorrido ni corrimiento del arreglo. El módulo se aplica una sola vez por operación pertinente.

## 4. Interfaz y contratos

Las siguientes firmas son conceptuales; conservar el estilo de tipos y manejo de errores ya adoptado por el proyecto:

```text
crear(capacidad: entero) -> ColaCircular
encolar(valor: Elemento) -> Resultado
desencolar() -> Resultado<Elemento>
estaVacia() -> booleano
estaLlena() -> booleano
```

`Resultado` representa explícitamente éxito o fallo con el mecanismo idiomático del lenguaje (por ejemplo, estado booleano, opción/resultado, excepción o error comunicado). No devolver un elemento ficticio ni modificar la estructura ante un fallo.

Para que la cota O(1) también aplique al tipo de elemento, cada celda debe contener un valor de tamaño fijo o una referencia/handle cuyo almacenamiento y devolución sean O(1). No realizar copias profundas de objetos de tamaño variable dentro de `encolar` o `desencolar`; su costo dependería del tamaño del elemento, no de la cola.

### `encolar(valor)`

- **Precondición:** la cola fue creada correctamente y `count` está entre `0` y `N`.
- **Si está llena (`count == N`):** comunicar fallo por cola llena; no escribir en el arreglo ni modificar `front`, `rear` o `count`.
- **Si hay espacio:** asignar `elementos[rear] = valor`; actualizar `rear = (rear + 1) % N`; incrementar `count` en uno; comunicar éxito.
- **Postcondición de éxito:** se conserva el orden FIFO y el nuevo valor queda al final lógico.
- **Complejidad:** O(1) en peor caso.

### `desencolar()`

- **Precondición:** la cola fue creada correctamente y `count` está entre `0` y `N`.
- **Si está vacía (`count == 0`):** comunicar fallo por cola vacía; no modificar índices, contador ni datos lógicos.
- **Si contiene elementos:** copiar/obtener `elementos[front]` como resultado; actualizar `front = (front + 1) % N`; decrementar `count` en uno; devolver el elemento extraído.
- **Postcondición de éxito:** se devuelve el elemento más antiguo y el resto conserva su orden FIFO.
- **Complejidad:** O(1) en peor caso.

### Consultas de estado

- `estaVacia()` devuelve `count == 0`.
- `estaLlena()` devuelve `count == N`.
- Ambas son O(1) en peor caso y no modifican el estado.
- Si el ejercicio requiere consultar el frente sin extraer, hacerlo en una operación aparte: fallar explícitamente si `count == 0`; en caso contrario devolver `elementos[front]` sin cambiar el estado. También es O(1).

## 5. Complejidad y garantía de O(1) estricta

Con arreglo de tamaño fijo, sin crecimiento ni reducción dinámica, cada inserción y extracción realiza una cantidad acotada e independiente de `N` de accesos y asignaciones, una actualización modular, una operación aritmética del contador y una comprobación de estado. No hay ciclos, recorridos, desplazamientos ni trabajo diferido.

Por lo tanto, `encolar`, `desencolar`, `estaVacia` y `estaLlena` son **O(1) en peor caso** —no solo amortizadas— bajo el modelo RAM habitual del curso, donde las operaciones sobre índices de tamaño máquina, incluyendo `%` por la capacidad fija, cuestan O(1). No redimensionar la cola: un crecimiento dinámico invalidaría esta garantía para cada operación individual.

El arreglo ocupa O(N) espacio y su reserva/inicialización puede costar O(N), según el lenguaje. Esa inicialización ocurre al crear la estructura y no altera la garantía O(1) estricta de inserciones y extracciones posteriores.

## 6. Organización propuesta

1. **Tipo o módulo `ColaCircular`:** concentra el arreglo, `front`, `rear`, `count` y `N`.
2. **Constructor/fábrica:** valida capacidad e inicializa el estado vacío.
3. **Operaciones públicas:** inserción, extracción y consultas de estado; mantener encapsulados los índices.
4. **Programa o cliente existente:** utiliza estas operaciones y presenta los mensajes o resultados según la interfaz ya definida por el ejercicio.
5. **Pruebas:** verificar contratos, FIFO, vuelta de índices y reutilización de posiciones; no inspeccionar o modificar directamente los campos internos salvo que el estilo de pruebas del proyecto lo requiera.

Antes de codificar, identificar el archivo y lenguaje correspondientes en el proyecto. No hay que inventar una segunda interfaz ni agregar operaciones ajenas al enunciado.

## 7. Criterios de aceptación y pruebas

- Construir con capacidad válida produce `front = rear = 0`, `count = 0`; una capacidad no positiva se rechaza explícitamente.
- Encolar en vacío y luego desencolar devuelve el mismo elemento y deja la cola vacía.
- Desencolar vacío falla sin modificar estado.
- Encolar `N` elementos llena la cola; un encolado extra falla sin sobrescribir datos ni mover índices.
- Tras extraer un elemento de la cola llena, encolar otro tiene éxito reutilizando el hueco liberado y preserva FIFO.
- Encolar/desencolar suficientes elementos para que `front` y `rear` crucen `N-1` demuestra el retorno a `0` con módulo.
- Vaciar y volver a usar la cola mantiene la coherencia, inclusive con `N = 1`.
- Las consultas de estado reflejan exactamente `count == 0` y `count == N`.
- La inspección del código confirma que las operaciones no recorren ni desplazan el arreglo, no cambian su capacidad y realizan un número constante de pasos.