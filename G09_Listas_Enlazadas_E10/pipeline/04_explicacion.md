# Explicación — Tipos genéricos en una lista enlazada

## ¿Qué problema resuelve parametrizar los tipos?

Sin genéricos, una lista de enteros tendría que declarar el tipo de su dato como `int`. Esa lista serviría para enteros, pero no para texto u objetos. Una alternativa —guardar todo como `Object`— permitiría mezclar elementos, pero obligaría a convertirlos manualmente al recuperarlos. Las conversiones incorrectas podrían fallar durante la ejecución y el compilador no podría comprobar que una lista contiene un solo tipo coherente.

La parametrización resuelve ambos problemas con un parámetro de tipo:

```java
ListaEnlazada<Integer> edades = new ListaEnlazada<>();
ListaEnlazada<String> nombres = new ListaEnlazada<>();
ListaEnlazada<Alumno> alumnos = new ListaEnlazada<>();
```

En cada variable, `T` queda sustituido por el tipo elegido. Por eso `edades.insertarAlFinal("Ana")` es un error de compilación, mientras que `nombres.insertarAlFinal("Ana")` es válido. El compilador ayuda a detectar incompatibilidades antes de ejecutar el programa y las operaciones reciben o devuelven el tipo correcto sin conversiones explícitas.

Java no admite tipos primitivos como argumento genérico: para enteros se usa la clase envoltorio `Integer`, no `int`. La clase genérica también evita mantener implementaciones duplicadas —una para enteros, otra para cadenas, otra para alumnos—: se escribe una estructura y el compilador verifica cada uno de sus usos.

## Cómo se relacionan `ListaEnlazada<T>` y `Nodo<T>`

En [ListaEnlazada.java](../ListaEnlazada.java), la declaración `ListaEnlazada<T>` expresa que la lista se parametriza con el tipo de sus elementos. El nodo interno también se declara `Nodo<T>` para conservar ese mismo tipo en toda la estructura:

```java
private static class Nodo<T> {
    private final T dato;
    private Nodo<T> siguiente;
}

private Nodo<T> head;
```

Así, una `ListaEnlazada<Alumno>` enlaza nodos `Nodo<Alumno>` y cada campo `dato` contiene un `Alumno`. No hay un nodo de otro tipo escondido en medio de esa lista mediante el uso normal de la API tipada.

El dato y el enlace cumplen funciones distintas:

- `dato` guarda el elemento elegido por quien usa la lista (`Integer`, `String`, `Alumno`, etc.).
- `siguiente` referencia el próximo nodo, siempre del mismo tipo parametrizado.
- `head` referencia el primer nodo, o es `null` cuando no hay ninguno.
- `size` es un contador estructural `int`; no representa un elemento de tipo `T`, por lo que correctamente sigue siendo entero.

## Por qué los algoritmos de enlaces no cambian

La lista no necesita comprender qué significa el objeto que guarda `dato`. Solo conserva cada objeto y conecta nodos. En una lista con alumnos, por ejemplo, las referencias forman:

```text
head -> Nodo<Alumno> -> Nodo<Alumno> -> null
```

En una lista de textos la secuencia de enlaces tiene exactamente la misma forma:

```text
head -> Nodo<String> -> Nodo<String> -> null
```

El tipo del objeto guardado varía; la relación «este nodo apunta al siguiente» no. Java no ofrece aritmética de punteros como C: el código trabaja con referencias y `null`, sin calcular direcciones de memoria. No obstante, la idea estructural es la misma: se sigue una referencia de nodo a nodo.

### Inserción al inicio

`insertarAlInicio` crea un nodo con el dato de tipo `T`, hace que su campo `siguiente` apunte al antiguo `head` y actualiza `head` para que apunte al nuevo nodo. Esas tres acciones son idénticas para cualquier `T`; no se suma, ordena ni inspecciona el dato.

### Inserción al final

`insertarAlFinal` crea un nodo terminal. Si la lista está vacía, el nuevo nodo pasa a ser `head`. De lo contrario, avanza siguiendo `siguiente` hasta encontrar el último nodo y enlaza allí el nuevo. El recorrido para encontrar el final examina referencias, no el contenido de los nodos.

### Recorrido e impresión

`imprimir` comienza en `head`, procesa el nodo actual y avanza con `actual = actual.siguiente` hasta llegar a `null`. El orden surge de los enlaces, no de comparar o clasificar los valores.

La lista sí convierte el dato a texto para mostrarlo mediante `String.valueOf(actual.dato)`. Esto es una decisión de presentación: usa la representación textual del objeto (`toString()` cuando corresponde), pero no modifica el algoritmo que avanza por la estructura. El caso `Alumno` lo ilustra con una representación propia, como `Ana (legajo 101)`.

## La excepción: buscar para eliminar un valor

La operación `eliminar(T dato)` debe decidir si un dato coincide con el buscado. Para esa decisión usa `Objects.equals`, no una operación aritmética ni una comparación de orden. En `Alumno`, `equals` define que dos instancias representan al mismo alumno si tienen igual legajo. Por eso se puede eliminar `new Alumno(101, "Ana")` aunque la lista contenga otra instancia con ese legajo.

Esta comparación es una necesidad de la búsqueda por valor, no de los enlaces. Si la lista solo insertara y recorriera, no necesitaría invocar `equals`. En ambos casos, una vez encontrada la posición, eliminar consiste en ajustar las referencias: cambiar `head` si se quita el primer nodo, o hacer que el nodo anterior saltee al eliminado.

La implementación acepta `null` como dato porque distingue la referencia nula de nodo (fin de la lista) del valor almacenado dentro de un nodo. `Objects.equals` también compara de forma segura cuando el dato o el buscado son `null`.

## Seguridad de tipos y límites

- **Seguridad estática:** los métodos que agregan elementos reciben `T`; no se puede insertar, por ejemplo, un `String` en una lista declarada `ListaEnlazada<Integer>` sin provocar un error de compilación.
- **Sin casts para leer los nodos:** `actual.dato` conserva el tipo genérico declarado; el cliente recibe objetos de su tipo esperado.
- **No impone capacidades innecesarias:** `T` no está limitado a `Comparable`, así que una lista de objetos que no saben ordenarse puede insertarse e imprimirse sin problema.
- **No protege contra el uso deliberado de tipos crudos ni de casts inseguros:** esa es una forma de saltarse las comprobaciones genéricas del compilador y puede reintroducir errores en tiempo de ejecución.
- **Borrado de tipos:** Java implementa genéricos principalmente mediante borrado de tipos; `T` no crea una estructura de nodos con una lógica distinta en tiempo de ejecución. La garantía principal para este ejercicio proviene de la comprobación estática del compilador.

## Qué demuestran las pruebas

El `main` de la clase instancia listas separadas de `Integer`, `String` y `Alumno`. Además de verificar tamaños y eliminaciones, captura la salida de `imprimir` y la compara con la secuencia completa esperada. En consecuencia, prueba los valores presentados y su orden, no solo que los métodos terminen sin excepción. `Alumno` no implementa aritmética ni ordenamiento: sus objetos se insertan y recorren por las mismas operaciones estructurales.

Las inserciones al inicio son O(1); insertar al final y recorrer/eliminar por búsqueda son O(n) en el peor caso; consultar si está vacía es O(1) y consultar `size` es O(1). La genericidad no altera estos costos: dependen de cuántos enlaces se recorren, no del tipo de dato almacenado.