# Explicación — búsqueda en una lista enlazada simple

## Por qué no se aplica búsqueda binaria

Que los valores de una lista estén ordenados no basta para que la búsqueda binaria sea eficiente. El algoritmo binario necesita consultar directamente el elemento de la posición central en cada paso. En un arreglo, el acceso por índice es O(1): se calcula la dirección del elemento a partir de la dirección inicial, el índice y el tamaño de cada elemento.

En una lista simplemente enlazada no hay acceso directo por índice. Solo se conoce `head`, y para llegar a otro nodo hay que seguir el enlace `siguiente` desde el nodo actual. Encontrar el nodo central, por lo tanto, requiere recorrer enlaces. Repetir ese recorrido para las mitades sucesivas no produce el costo O(log n) de un arreglo; el acceso al supuesto punto medio ya requiere trabajo lineal en la lista. En la implementación habitual, buscar nodo por nodo conserva un costo O(n) y es más simple y natural.

El orden sí permitiría detener una búsqueda secuencial antes del final: si se llega a un dato mayor que el buscado, ya se sabe que el valor no aparecerá después. Aun así, para alcanzar ese dato hay que visitar los nodos anteriores; el peor caso sigue siendo O(n).

## Cómo funciona `buscar(int dato)`

La implementación mantiene una referencia auxiliar, `actual`, que comienza en `head`:

```java
public boolean buscar(int dato) {
    Nodo actual = head;
    while (actual != null) {
        if (actual.dato == dato) {
            return true;
        }
        actual = actual.siguiente;
    }
    return false;
}
```

1. **Inicio:** `actual` apunta al primer nodo. Si `head` es `null`, la lista está vacía y el ciclo no comienza.
2. **Comparación:** mientras haya un nodo, se compara su entero `dato` con el parámetro.
3. **Coincidencia:** si son iguales, se retorna `true` inmediatamente; no es necesario revisar los nodos restantes.
4. **Avance:** si no coinciden, `actual = actual.siguiente` pasa al próximo nodo.
5. **Fin sin coincidencia:** al avanzar desde el último nodo, `actual` queda en `null`; el método retorna `false`.

El método usa `==` porque compara valores de tipo primitivo `int`. Solo lee los nodos: no cambia `head`, los datos ni los enlaces. Si hay valores repetidos, devuelve `true` al encontrar la primera coincidencia; no necesita contar ni recorrer todas las apariciones.

## Complejidad

- **Tiempo:** O(n) en el peor caso: el valor está en el último nodo o no está. En el mejor caso, cuando está al inicio, se resuelve en O(1).
- **Espacio auxiliar:** O(1): solo se crea una referencia `actual`, sin importar cuántos nodos tenga la lista.

## Qué comprueba `main`

El programa construye listas con `insertarAlFinal` y llama a `verificarBusqueda`. Este método compara el resultado real con el esperado y lanza `AssertionError` si no coinciden. Así, cualquier caso incorrecto hace que la ejecución falle explícitamente.

Los casos comprueban:

- Búsqueda exitosa al inicio, en el medio y al final.
- Búsqueda fallida de un elemento inexistente.
- Lista vacía (`head == null`).
- Lista con un solo nodo, tanto para un valor presente como para uno ausente.
- Lista con valores repetidos, y un valor ausente en esa misma lista.

## Errores comunes evitados

- **Desreferenciar `null`:** el ciclo verifica `actual != null` antes de leer `actual.dato`.
- **Avanzar antes de comparar:** se compara el nodo actual y luego se avanza; hacerlo al revés podría omitir `head`.
- **No devolver al llegar al final:** el `return false` fuera del ciclo cubre tanto la lista vacía como una búsqueda sin éxito.
- **Modificar la lista al buscar:** se recorre con una referencia auxiliar y no se alteran los enlaces.
- **Confundir orden con acceso directo:** ordenar los valores no convierte los enlaces en posiciones calculables por índice.