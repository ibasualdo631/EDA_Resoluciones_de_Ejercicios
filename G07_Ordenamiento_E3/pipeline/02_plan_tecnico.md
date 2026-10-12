# Plan técnico — Insertion Sort con conteo de desplazamientos

## 1) Arquitectura de la solución

La solución se implementa como una versión de Insertion Sort adaptada para contar desplazamientos. La idea central es mantener un prefijo ordenado y, para cada elemento `clave`, ubicarlo en la posición correcta dentro de ese prefijo.

### Componentes principales

1. `main`:
   - Define el arreglo base: `{1, 2, 3, 5, 4, 6, 7}`.
   - Invoca al método de ordenamiento.
   - Muestra el arreglo final.
   - Muestra el total de desplazamientos.

2. `insertionSortConDesplazamientos(int[] numeros)`: 
   - Recorre desde el índice `1` hasta el final.
   - Usa `clave` para guardar el valor actual a insertar.
   - Compara la clave con elementos del prefijo ya ordenado.
   - Desplaza elementos mayores hacia la derecha para abrir lugar.
   - Inserta la clave en su posición final.
   - Devuelve el contador de desplazamientos.

3. `mostrarArreglo(int[] numeros)`: 
   - Escribe el contenido del arreglo en formato legible.
   - Sirve para visualizar la secuencia final ordenada.

### Estructura lógica

- El algoritmo no crea arreglos auxiliares ni estructuras adicionales.
- La operación principal es un recorrido hacia atrás sobre una parte ya ordenada del arreglo.
- Todo el trabajo se realiza in-place, modificando directamente el vector original.

---

## 2) Lógica del ciclo hacia atrás

El ciclo hacia atrás se encarga de ubicar la clave en la posición correcta dentro del prefijo ordenado.

```java
for (int i = 1; i < numeros.length; i++) {
    int clave = numeros[i];
    int j = i - 1;

    while (j >= 0 && numeros[j] > clave) {
        numeros[j + 1] = numeros[j];
        desplazamientos++;
        j--;
    }

    numeros[j + 1] = clave;
}
```

### ¿Por qué se recorre hacia atrás?

Porque el elemento a insertar (`clave`) debe ubicarse antes del primer elemento mayor que él. El prefijo ordenado queda a la izquierda y, al recorrer de derecha a izquierda, se pueden abrir espacios sin perder información de los valores ya ordenados.

### Comportamiento sobre el caso del ejemplo

Para el arreglo:

`{1, 2, 3, 5, 4, 6, 7}`

- Se toma `clave = 4` cuando `i = 3`.
- Se compara con `5` y se observa que `5 > 4`.
- Se ejecuta:

```java
numeros[j + 1] = numeros[j];
```

Lo que mueve `5` una posición a la derecha.
- Luego `j` decrece y el ciclo termina porque `3 <= 4`.
- Finalmente se escribe:

```java
numeros[j + 1] = clave;
```

Quedando el arreglo como:

`{1, 2, 3, 4, 5, 6, 7}`

Esto explica por qué el arreglo casi ordenado requiere solo un desplazamiento: la única inversión visible es `(5, 4)`.

---

## 3) Variable para contabilizar desplazamientos

La variable recomendada es:

```java
int desplazamientos = 0;
```

### Regla de conteo

Se cuenta un desplazamiento cada vez que un elemento existente se copia a la derecha para abrir espacio a la clave:

```java
numeros[j + 1] = numeros[j];
```

No se cuenta la escritura final de la clave:

```java
numeros[j + 1] = clave;
```

porque esa operación no mueve un elemento previo; solo coloca la clave en su posición final.

### Resultado esperado para el ejemplo

Para `{1, 2, 3, 5, 4, 6, 7}` solo se necesita mover `5` una vez. Por lo tanto:

- `desplazamientos = 1`
- Arreglo final: `{1, 2, 3, 4, 5, 6, 7}`

---

## 4) Complejidad espacial O(1)

La complejidad espacial es `O(1)` porque:

- No se usa un arreglo auxiliar adicional.
- Solo se mantienen variables escalares:
  - `clave`
  - `j`
  - `i`
  - `desplazamientos`
- El algoritmo trabaja in-place sobre el arreglo original.

La memoria usada no crece con la cantidad de elementos `n`; permanece constante independientemente del tamaño del vector. Por eso la complejidad espacial es constante.

Esta observación es distinta a la complejidad temporal: el costo de ejecución depende de cuántas inversiones hay que corregir, y puede variar desde casi lineal hasta cuadrático.

---

## 5) Directivas de implementación

1. Mantener el arreglo original y ordenar in-place.
2. Usar `for (int i = 1; i < numeros.length; i++)` para recorrer cada elemento a insertar.
3. Guardar el valor actual en `int clave = numeros[i];`.
4. Usar un bucle `while (j >= 0 && numeros[j] > clave)` para comparar hacia atrás.
5. En cada desplazamiento del arreglo, ejecutar:
   - `numeros[j + 1] = numeros[j];`
   - `desplazamientos++;`
6. Cuando termine la inserción, colocar la clave en la posición adecuada:
   - `numeros[j + 1] = clave;`
7. No incrementar el contador en la última escritura de la clave.
8. Al finalizar, imprimir:
   - arreglo ordenado
   - cantidad total de desplazamientos
9. Validar con el arreglo de prueba `{1, 2, 3, 5, 4, 6, 7}` y confirmar que se obtiene `{1, 2, 3, 4, 5, 6, 7}` y un total de `1` desplazamiento.

---

## 6) Criterio de corrección del ejercicio

Se considera correcta la implementación si:

- Ordena el arreglo ascendentemente.
- Cuenta solo los desplazamientos reales de elementos existentes hacia la derecha.
- Muestra el arreglo final correcto.
- Explica que la entrada tiene una sola inversión y, por eso, solo requiere un desplazamiento.
- Reconoce que Insertion Sort puede llegar a `O(n²)` en el peor caso cuando hay muchas inversiones, aunque en este caso casi ordenado el costo se acerca a `Θ(n)`.
