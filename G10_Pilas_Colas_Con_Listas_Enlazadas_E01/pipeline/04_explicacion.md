# Guía didáctica — Pila enlazada de enteros

## 1. ¿Qué problema resuelve?

`PilaEnteros` implementa una pila de números enteros usando una lista simplemente enlazada. Una pila sigue la regla **LIFO** (*Last In, First Out*): el último valor que se agrega es el primero que se retira. Es como una pila de platos: solo se agrega y se saca el plato de arriba.

La estructura ofrece estas operaciones:

- `push(valor)`: agrega un valor al tope.
- `pop()`: retira y devuelve el tope.
- `peek()`: consulta el tope sin retirarlo.
- `isEmpty()`: indica si no hay elementos.
- `contains(valor)`: busca un valor.
- `imprimir(salida)`: muestra los elementos desde el tope hasta la base.

## 2. Cómo se representa la pila

Cada `Nodo` guarda dos cosas:

1. `valor`: el entero almacenado.
2. `siguiente`: una referencia al nodo que está justo debajo en la pila.

La variable `head` apunta al primer nodo. Elegimos que ese primer nodo sea el **tope**:

```text
head
  |
  v
[ 30 | siguiente ] -> [ 20 | siguiente ] -> [ 10 | null ]
      tope                                      base
```

En el dibujo, `30` fue agregado más recientemente, así que es el primero que debe salir. Una pila vacía no tiene nodos y se representa con `head == null`.

La clase `Nodo` está declarada como `private static`: es un detalle interno de la pila y no hace falta que quien la usa pueda manipular nodos directamente. Sus campos son `final`, por lo que el valor y el enlace de cada nodo no cambian después de crearlo. La pila modifica el punto de entrada `head` para insertar o retirar.

## 3. El enlace de `head` paso a paso

### Apilar: `push`

El método contiene esta instrucción:

```java
head = new Nodo(valor, head);
```

Al construir el nodo nuevo, se le entrega el `head` actual como su enlace siguiente. Después, la asignación hace que `head` apunte al nodo recién creado.

Ejemplo, empezando con una pila vacía:

1. `push(10)`: se crea el nodo `10`, cuyo enlace es `null`; `head` apunta a `10`.
2. `push(20)`: el nodo `20` recibe como siguiente al nodo que antes era `head` (`10`). Luego `head` apunta a `20`.
3. `push(30)`: `30` enlaza con `20`, y `head` pasa a apuntar a `30`.

El resultado es `head -> 30 -> 20 -> 10`. No hay que recorrer la lista para encontrar dónde insertar.

### Desapilar: `pop`

`pop()` primero verifica si la pila está vacía. Si lo está, lanza `IllegalStateException`, pues no existe un entero que devolver. Si hay un nodo:

1. Guarda el valor de `head` en una variable local.
2. Mueve `head` al nodo siguiente.
3. Devuelve el valor guardado.

Con la pila `head -> 30 -> 20 -> 10`, el método guarda `30`, mueve `head` para que apunte a `20` y devuelve `30`. El nodo que dejó de estar enlazado ya no forma parte de la pila.

Si solo había un nodo, su enlace siguiente es `null`; por lo tanto, al mover `head` la pila queda correctamente vacía.

### ¿Por qué `push` y `pop` son O(1)?

Ambos métodos hacen una cantidad fija de trabajo, sin importar si hay uno o un millón de nodos:

- `push` crea un nodo y actualiza su enlace y `head`.
- `pop` comprueba `head`, lee el valor y avanza `head` una vez.

No hay ciclos ni búsqueda de la base en estas operaciones. Por eso su tiempo es **O(1)**. La decisión clave es mantener el tope en el primer nodo accesible (`head`); insertar o retirar al final de una lista simple exigiría recorrerla y no daría esa misma complejidad.

## 4. Otras operaciones

### Consultar el tope: `peek`

Si la pila está vacía, lanza `IllegalStateException`. Si no, devuelve `head.valor` y no cambia `head` ni los enlaces. Solo accede al primer nodo, por eso es **O(1)**.

### Verificar vacío: `isEmpty`

Comprueba `head == null`. La comparación tarda **O(1)** y no modifica la estructura.

### Buscar: `contains`

Usa una variable `actual` que empieza en `head` y avanza por `actual.siguiente`. En cada nodo compara `actual.valor` con el buscado. Termina al encontrarlo o al llegar a `null`.

Puede ser necesario visitar todos los nodos; su tiempo es **O(n)** en el peor caso y utiliza **O(1)** espacio adicional. La variable `actual` permite recorrer sin mover `head`, así que buscar no retira ni reordena elementos.

### Imprimir: `imprimir`

El método recibe un `PrintStream` para que la estructura no dependa directamente de la consola. Por ejemplo, se puede pasar `System.out` para imprimir en pantalla o un flujo temporal desde una prueba.

Primero valida que el flujo no sea `null`. Si no hay elementos, escribe `Pila vacia`. Si hay elementos, recorre desde `head` y muestra `Tope -> ... -> Base`. El recorrido es **O(n)** y no modifica los enlaces ni `head`.

## 5. Cómo se mantiene LIFO

Si se ejecuta:

```text
push(10)
push(20)
push(30)
```

El estado queda así:

```text
head -> 30 -> 20 -> 10
```

Las llamadas sucesivas a `pop()` devuelven `30`, luego `20` y finalmente `10`. El orden de salida es el inverso al de entrada: esa es la regla LIFO. No se necesita una estructura auxiliar para invertir los datos; el orden resulta de insertar siempre en el mismo extremo y retirar de ese mismo extremo.

## 6. Errores comunes que conviene evitar

- **Insertar recorriendo hasta la base:** cuesta O(n) y no es necesario; para una pila enlazada, el tope se conserva en `head`.
- **Mover `head` antes de guardar su valor en `pop`:** se perdería el acceso al valor que se debe devolver. Primero se guarda, después se avanza.
- **Leer `head.valor` sin validar que exista:** produciría un `NullPointerException` accidental. Aquí `pop` y `peek` notifican el caso vacío intencionalmente con `IllegalStateException`.
- **Usar un entero especial como señal de vacío:** cualquier `int`, incluido `0` o los valores extremos, puede ser un dato válido. La excepción evita confundir datos con errores.
- **Usar `head` como variable de recorrido en búsqueda o impresión:** al avanzar `head` se modificaría o perdería la pila. Se usa una referencia local, `actual`.
- **Desapilar para imprimir o buscar:** cambiaría el contenido y podría alterar el orden. Las consultas recorren nodos sin cambiar sus enlaces.

## 7. Complejidades resumidas

| Operación | Tiempo | Motivo |
|---|---:|---|
| `push` | O(1) | Enlaza un nodo nuevo directamente delante de `head`. |
| `pop` | O(1) | Avanza `head` al siguiente nodo, sin recorrido. |
| `peek` | O(1) | Lee el valor del nodo `head`. |
| `isEmpty` | O(1) | Compara `head` con `null`. |
| `contains` | O(n) | En el peor caso visita todos los nodos. |
| `imprimir` | O(n) | Visita una vez cada nodo. |

Con `n` elementos, la estructura usa **O(n)** memoria porque cada valor ocupa un nodo.

## 8. Ejecutar las pruebas

Desde la carpeta del ejercicio, se pueden compilar la implementación y las pruebas autónomas con:

```text
javac -d out PilaEnteros.java PruebasPilaEnteros.java
java -cp out PruebasPilaEnteros
```

La suite comprueba la pila vacía, las excepciones de `pop`/`peek`, el orden LIFO, valores negativos y extremos, búsqueda, impresión no destructiva y la reutilización después de vaciar. En la validación documentada se superaron **29 comprobaciones**.

## 9. Posibles mejoras si el ejercicio creciera

- Agregar un método `size()` junto a un contador actualizado en `push` y `pop`, para responder en O(1) cuántos elementos hay.
- Generalizar la clase a `Pila<T>` si se quisieran almacenar tipos distintos de `int`.
- Incorporar una interfaz de consola o un menú si el enunciado lo pidiera; la pila actual se mantiene independiente de esa interfaz.