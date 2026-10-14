# Explicación — Historial de navegación con pila enlazada

## 1. La analogía: historial como pila de páginas

Una pila funciona como una torre de elementos: los elementos nuevos se ponen arriba y se retira primero el que está arriba. En un navegador, la página que acabamos de visitar es la actual. Si volvemos atrás, descartamos esa página actual y reaparece la página visitada inmediatamente antes.

Por eso el historial de este ejercicio tiene comportamiento **LIFO** (*Last In, First Out*, “último en entrar, primero en salir”). Si se visitan las páginas A, luego B y luego C, el tope de la pila es C, debajo está B y al fondo A:

```text
tope
  |
  v
  C -> B -> A -> null
```

La flecha `siguiente` de cada nodo enlaza la página con la visita inmediatamente anterior. No se necesita buscar la página anterior: está directamente enlazada desde la actual.

## 2. `push` y la opción «Visitar una página»

En una pila, `push` coloca un elemento en el tope. El método `visitar` cumple ese mismo papel:

```java
tope = new Nodo(pagina.trim(), tope);
```

Paso a paso:

1. Se valida que la página no sea `null` ni esté vacía.
2. Se crea un nodo que contiene el nombre o la dirección de esa página.
3. El campo `siguiente` del nodo nuevo apunta al tope anterior.
4. La variable `tope` pasa a apuntar al nodo recién creado.

Por ejemplo, si antes el tope era A y se visita B, el nodo B apunta a A y se convierte en el nuevo tope:

```text
Antes:  tope -> A -> null
Visitar B
Después: tope -> B -> A -> null
```

Esto equivale a cargar una página nueva en el navegador: B se muestra y A queda disponible como página anterior.

## 3. `pop` y la opción «Volver atrás»

En una pila, `pop` retira el elemento que está en el tope. Aquí `volverAtras` realiza esa operación moviendo la referencia al nodo siguiente:

```java
if (tope == null || tope.siguiente == null) {
    return false;
}

tope = tope.siguiente;
return true;
```

Paso a paso:

1. Se comprueba si la pila está vacía (`tope == null`) o si tiene solo una página (`tope.siguiente == null`).
2. En cualquiera de esos dos casos no existe una página anterior; el método devuelve `false` y no toca el estado.
3. Si existe una página anterior, `tope = tope.siguiente` descarta la página actual de la pila.
4. El nodo siguiente pasa a ser el tope y, por tanto, la nueva página actual.

Con A, B y C visitadas, volver atrás cambia el tope de C a B:

```text
Antes:   tope -> C -> B -> A -> null
Volver:  tope ------> B -> A -> null
```

El nodo C deja de formar parte del historial accesible. Esta es la analogía con `pop`: se retira la visita más reciente y queda visible la anterior. Si luego se vuelve atrás otra vez, el tope pasa de B a A.

> La pila simple implementa el requisito del ejercicio de volver atrás. Un navegador real puede conservar además un historial de páginas “hacia adelante” para rehacer la navegación; esa funcionalidad adicional no está incluida en este ejercicio.

## 4. Consultar la página actual: equivalente a `peek`

`peek` permite mirar el elemento superior sin retirarlo. `obtenerPaginaActual` hace lo mismo:

```java
return tope == null ? null : tope.pagina;
```

Si hay nodos, devuelve la página guardada en el tope. Si no hay ninguno, devuelve `null`. La opción de menú transforma ese resultado en un mensaje para el usuario. Consultar no mueve el tope ni modifica enlaces.

## 5. Imprimir el historial sin desapilarlo

La opción de impresión debe mostrar todas las páginas, pero no simula una serie de `pop`, porque imprimir no debe alterar el estado del navegador. Usa una referencia auxiliar:

```java
for (Nodo actual = tope; actual != null; actual = actual.siguiente) {
    System.out.println("- " + actual.pagina);
}
```

`actual` recorre desde el tope hasta el final; `tope` permanece intacto. El resultado aparece de la página actual a la más antigua. Si la pila está vacía, se muestra un mensaje y se termina el método.

## 6. Cómo se organiza la clase

- `Nodo` es una clase anidada privada: cada objeto guarda una página (`pagina`) y el enlace al nodo anterior (`siguiente`). Sus campos son `final`, porque una vez creado el nodo no se cambian; el gestor cambia el enlace superior, `tope`.
- `tope` es la referencia de estado de la pila. En una pila vacía vale `null`.
- `visitar`, `volverAtras`, `obtenerPaginaActual` e `imprimirHistorial` contienen las operaciones sobre el historial.
- `main` crea el gestor y lee las opciones de consola con `Scanner`.
- El ciclo `while` permite usar varias opciones hasta elegir salir o terminar la entrada.
- `switch` ejecuta la acción elegida. `default` maneja una opción inválida sin cerrar el programa.
- Se valida la cadena de página antes de visitar para impedir que una entrada vacía se convierta en un nodo.

Aunque hay una clase anidada `Nodo`, sigue habiendo **una sola clase pública/archivo de programa**: `HistorialNavegacion`. `Nodo` es un tipo auxiliar privado, no una clase pública independiente.

## 7. Costos de las operaciones

Como la lista enlazada inserta y retira por el tope, `visitar` y `volverAtras` necesitan un número fijo de cambios de referencias: **O(1)**. Consultar el tope también es **O(1)**. Imprimir recorre los n nodos y cuesta **O(n)**.

## 8. Errores comunes que conviene evitar

- **Retirar al visitar:** visitar una página nueva debe conservar las anteriores; solo «volver atrás» mueve el tope.
- **Eliminar antes de comprobar:** si no hay página anterior, `volverAtras` debe devolver `false` y conservar la página única o el estado vacío.
- **Confundir tope con fondo:** la página actual está en el tope, no al final de la lista.
- **Imprimir desapilando:** quitar nodos durante la impresión vaciaría o alteraría el historial; se debe recorrer con un nodo auxiliar.
- **Asumir que el historial siempre tiene datos:** consultar, imprimir y volver atrás deben contemplar el caso vacío.
- **Usar una colección de biblioteca en lugar de enlaces:** el ejercicio busca practicar una pila enlazada, así que los nodos y sus referencias deben ser visibles en la solución.

## 9. Ejemplo completo

| Acción | Contenido de la pila (tope primero) | Página actual |
|---|---|---|
| Inicio | vacía | ninguna |
| Visitar A | A | A |
| Visitar B | B → A | B |
| Visitar C | C → B → A | C |
| Volver atrás | B → A | B |
| Volver atrás | A | A |
| Volver atrás | A (sin cambios) | A |

El último paso demuestra el límite: con solo A no hay una página previa a la cual regresar. La operación no desapila A y comunica que no existe una página anterior.