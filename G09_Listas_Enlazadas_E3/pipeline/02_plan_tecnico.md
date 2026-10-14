# Plan técnico — Obtener un elemento por posición

## Alcance y supuestos

Se implementará en la clase existente que representa la lista enlazada, reutilizando sus nombres y convenciones. No se crearán una estructura alternativa, métodos de acceso aleatorio ni copias de la lista. Se asume una lista simplemente enlazada con un campo de referencia a la cabeza, enlaces al nodo siguiente y un contador `size`, tal como indica la consigna. Si la implementación existente usa otros nombres, se conservarán esos nombres.

Los índices son base cero. El contrato exige `IndexOutOfBoundsException` cuando `posicion < 0` o `posicion >= size`, incluso para una lista vacía. La validación debe ocurrir antes de cualquier recorrido o acceso a campos de un nodo.

## Organización y estructura de datos

- **Lista enlazada existente:** mantiene la cabeza y el tamaño; no se altera durante la consulta.
- **Nodo existente:** contiene el dato entero y la referencia al siguiente nodo.
- **Variables locales del método:** una referencia auxiliar al nodo actual y, si se usa recorrido contado, un entero para el índice actual. No se modifica la cabeza para recorrer.
- **Método público:** `int obtener(int posicion)`, ubicado junto a las operaciones de consulta de la lista conforme al estilo existente.

No hace falta añadir clases ni dependencias. No se debe convertir la lista a un arreglo: además de ser innecesario, ocultaría el coste secuencial que el ejercicio busca implementar.

## Secuencia de ejecución

1. Comparar `posicion` con los límites: si es menor que cero o mayor/igual a `size`, lanzar `IndexOutOfBoundsException` inmediatamente.
2. Inicializar la referencia local en la cabeza de la lista y el contador local en cero.
3. Avanzar al siguiente nodo mientras el contador sea menor que `posicion`; incrementar el contador por cada enlace seguido.
4. Al llegar al índice pedido, devolver el dato del nodo actual.

Para `posicion == 0`, no se avanza: se devuelve el dato de la cabeza. Para el último índice válido, `size - 1`, se siguen `size - 1` enlaces.

## Pseudocódigo

```text
obtener(posicion):
    si posicion < 0 o posicion >= size:
        lanzar IndexOutOfBoundsException

    actual = cabeza
    indiceActual = 0

    mientras indiceActual < posicion:
        actual = actual.siguiente
        indiceActual = indiceActual + 1

    devolver actual.dato
```

El pseudocódigo expresa la lógica y debe adaptarse a los nombres de campos y tipos que ya tenga el proyecto. Si `Nodo` es una clase interna o usa accesores, emplear su forma existente de leer el dato y el enlace.

## Firmas, tipos y errores

- Firma requerida: `int obtener(int posicion)`.
- Retorno: el dato `int` del nodo en el índice solicitado.
- Excepción ante entrada inválida: `IndexOutOfBoundsException`, no comprobada y por tanto no declarada en la firma.
- Puede incluirse en el mensaje el índice solicitado y el tamaño vigente. El mensaje no sustituye la validación ni modifica el tipo de excepción exigido.
- No usar valores centinela ni retornar `null`. Un índice inválido no debe provocar accidentalmente `NullPointerException`.

## Invariantes y garantías

- Al comenzar el recorrido, `0 <= posicion < size`.
- Antes de cada avance, `actual` apunta al nodo de `indiceActual`, donde `0 <= indiceActual <= posicion`.
- Después de cada avance, `actual` apunta al siguiente índice lógico y el contador aumenta exactamente en uno.
- Al terminar, `actual` apunta al nodo de `posicion`.
- La operación no cambia cabeza, tamaño, datos ni enlaces, tanto si retorna correctamente como si lanza la excepción de rango.

## Complejidad y decisiones

- **Tiempo:** `O(posicion + 1)` para una posición válida contando la lectura del nodo; expresado en función del tamaño, `O(n)` en el peor caso.
- **Espacio adicional:** `O(1)`, ya que solo se usan variables locales constantes.
- Se requiere recorrido nodo por nodo porque la referencia al nodo `i` solo se obtiene siguiendo la cadena de enlaces desde la cabeza; el índice no permite calcular una dirección directa como en un arreglo.

## Verificación técnica

Comprobar el comportamiento con pruebas para:

1. Posición `0` en una lista no vacía.
2. Una posición intermedia.
3. La última posición válida (`size - 1`).
4. Posición negativa: se lanza `IndexOutOfBoundsException`.
5. Posición igual a `size` y superior: se lanza `IndexOutOfBoundsException`.
6. Lista vacía: se lanza la misma excepción para cualquier índice.
7. En los casos de excepción, confirmar que cabeza, tamaño y contenido de la lista permanecen sin cambios.

La carpeta no contiene actualmente código fuente ni pruebas. Al integrar el método en el proyecto, usar el framework de pruebas ya configurado y no introducir uno nuevo solo para este ejercicio.