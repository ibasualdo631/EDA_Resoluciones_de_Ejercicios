# Plan técnico — `contarOcurrencias`

## Firma del método

```java
public int contarOcurrencias(int dato)
```

- **Parámetro:** `dato`, valor entero que se busca en la lista.
- **Retorno:** cantidad de nodos cuyo valor es igual a `dato`; devuelve `0` si la lista está vacía o no contiene ese valor.
- **Complejidad temporal:** `O(n)`, donde `n` es la cantidad de nodos. Se examina cada nodo exactamente una vez.
- **Complejidad espacial:** `O(1)`, porque solo se necesitan un contador y una referencia auxiliar.

## Pasos lógicos

1. Inicializar un contador en `0`.
2. Crear una referencia de recorrido apuntando al primer nodo de la lista.
3. Mientras la referencia no sea `null`:
   - Comparar el valor del nodo actual con `dato`.
   - Si son iguales, incrementar el contador.
   - Avanzar la referencia al siguiente nodo, tanto si hubo coincidencia como si no.
4. Cuando se alcance `null`, devolver el contador.

El bucle no debe terminar al encontrar una coincidencia: el valor puede repetirse en nodos posteriores y todos deben contabilizarse.

## Pseudocódigo

```text
contarOcurrencias(dato):
    contador <- 0
    actual <- primer nodo

    mientras actual != null:
        si actual.valor == dato:
            contador <- contador + 1
        actual <- actual.siguiente

    devolver contador
```

El método solo lee los nodos; no cambia sus valores ni sus enlaces.