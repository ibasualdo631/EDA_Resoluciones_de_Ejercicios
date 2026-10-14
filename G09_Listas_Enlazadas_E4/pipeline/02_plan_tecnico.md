# Plan técnico — Inserción en posición específica

## Contrato

Implementar `void insertarEnPosicion(int dato, int posicion)` en la lista enlazada existente, siguiendo sus nombres y convenciones.

- Las posiciones se cuentan desde cero.
- Sea `n` el tamaño actual de la lista. El rango permitido es inclusivo: `0 <= posicion <= n`.
- `posicion == 0` inserta antes de la cabeza.
- `posicion == n` inserta después del último nodo.
- Si `posicion < 0` o `posicion > n`, rechazar mediante el mecanismo de error que ya use el proyecto, sin cambiar enlaces, cabeza, cola ni tamaño.

## Secuencia de ejecución

1. **Validar antes de mutar.** Determinar el tamaño actual o usar la información de tamaño que ya mantenga la estructura. Rechazar cualquier índice fuera de `[0, n]` antes de crear/modificar enlaces.
2. **Crear** el nuevo nodo con `dato`.
3. **Resolver el inicio** (`posicion == 0`): conservar la cabeza anterior en el enlace del nodo nuevo (`nuevo.setSiguiente(cabezaAnterior)`), luego actualizar la cabeza para que apunte a `nuevo`. Si la lista estaba vacía y existe cola, actualizarla también.
4. **Resolver medio o final** (`posicion > 0`): recorrer desde la cabeza hasta el nodo situado en el índice `posicion - 1`; nombrarlo `actual`.
5. **Reconectar en este orden exacto**:

   ```java
   nuevo.setSiguiente(actual.getSiguiente());
   actual.setSiguiente(nuevo);
   ```

   La primera línea conserva en el nodo nuevo el sucesor original de `actual`; la segunda cambia el enlace previo para insertar el nodo entre ambos. No invertirlas ni volver a leer el sucesor desde `actual` después de sobrescribirlo.
6. **Actualizar metadatos** después de enlazar: incrementar el tamaño exactamente una vez si la lista lo almacena; si la inserción fue al final y existe cola, asignar la cola a `nuevo`.

## Casos de punteros

### Lista vacía (`n == 0`, `posicion == 0`)

`nuevo.siguiente = null`; cabeza pasa a `nuevo`; cola pasa a `nuevo` si está mantenida. La inserción en el único índice permitido es simultáneamente al inicio y al final.

### Inicio en lista no vacía (`posicion == 0`)

Antes: `cabeza -> A -> ...`  
Primero: `nuevo.siguiente = cabezaAnterior`  
Luego: `cabeza = nuevo`  
Después: `cabeza -> nuevo -> A -> ...`

### Medio (`0 < posicion < n`)

Antes: `... -> actual -> sucesor -> ...`  
Primero: `nuevo.siguiente = actual.siguiente`  
Luego: `actual.siguiente = nuevo`  
Después: `... -> actual -> nuevo -> sucesor -> ...`

### Final (`posicion == n`, `n > 0`)

`actual` es el último y `actual.siguiente == null`. Primero `nuevo.siguiente` recibe ese `null`; después `actual.siguiente` apunta a `nuevo`. Si existe cola, actualizarla a `nuevo`.

## Por qué el orden es crítico

Si se ejecuta primero `actual.setSiguiente(nuevo)`, el enlace que permitía alcanzar al sucesor queda reemplazado. Leer después `actual.getSiguiente()` devuelve el propio `nuevo`; asignarlo a `nuevo.siguiente` puede formar el ciclo `nuevo -> nuevo`, dejando inaccesible el resto de la lista y pudiendo hacer que un recorrido no termine. La coreografía obligatoria guarda el sucesor antes de reemplazar el enlace.

## Manejo de límites

- `posicion == 0`: válido, también en lista vacía.
- `posicion == n`: válido; insertar al final.
- `posicion < 0`: inválido.
- `posicion > n`: inválido.
- La validación precede a cualquier cambio estructural; el error sigue el mecanismo establecido por el proyecto, sin introducir uno nuevo arbitrariamente.

## Pruebas de aceptación

- Insertar en una lista no vacía en `0`: el nuevo nodo es cabeza y la secuencia original permanece enlazada detrás.
- Insertar en medio: la secuencia queda `actual, nuevo, sucesor` y el resto de los elementos conserva el orden.
- Insertar en `n`: el nuevo nodo queda al final con `siguiente == null`; comprobar también la cola si existe.
- Insertar en lista vacía en `0`: cabeza y cola señalan al único nodo si la estructura mantiene ambas.
- Para posiciones `-1` y `n + 1`: verificar el error esperado y que contenido, tamaño, cabeza y cola no cambian.
- Tras cada inserción válida, verificar que el tamaño aumenta en uno, que cada nodo de la secuencia es alcanzable una sola vez y que no hay ciclos.
