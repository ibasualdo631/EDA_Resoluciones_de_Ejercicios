# Plan técnico — búsqueda secuencial en lista enlazada

## Firma del método

```java
public boolean buscar(int dato)
```

El método pertenece a la clase que administra la lista y utiliza su referencia `head`. Se asume que cada nodo ofrece el entero `dato` y el enlace `siguiente`.

## Pasos lógicos

1. Inicializar una referencia auxiliar `actual` con `head`.
2. Mientras `actual` no sea `null`:
   - Comparar `actual.dato` con el parámetro `dato`.
   - Si son iguales, devolver `true` inmediatamente.
   - Si no son iguales, avanzar a `actual.siguiente`.
3. Si el recorrido termina porque `actual` llegó a `null`, devolver `false`.

La lista vacía también queda cubierta: `head` ya es `null`, por lo que no se ejecuta el ciclo y el método devuelve `false`.

## Pseudocódigo

```text
buscar(dato):
    actual = head
    mientras actual != null:
        si actual.dato == dato:
            devolver true
        actual = actual.siguiente
    devolver false
```

## Complejidad

- **Tiempo:** O(n) en el peor caso, cuando el valor está en el último nodo o no está en la lista. Si se encuentra antes, el método termina anticipadamente.
- **Espacio auxiliar:** O(1), porque solo se usa la referencia `actual`, independientemente del tamaño de la lista.

## Criterios de implementación

- Recorrer únicamente desde `head`, siguiendo `siguiente`; una lista enlazada no permite calcular directamente la dirección de un nodo por índice.
- No modificar los nodos ni los enlaces durante la búsqueda.
- Comparar enteros con `==` y asegurar que toda ruta devuelva un valor booleano.