# Plan técnico: `modificar(int posicion, int nuevoDato)`

## Objetivo

Reemplazar el campo `dato` del nodo en la posición indicada, sin insertar o eliminar nodos y sin alterar ninguna referencia `siguiente`. La posición debe validarse antes de iniciar el recorrido hasta el nodo.

## Directivas para el desarrollador

1. Revisar la clase de la lista y sus operaciones existentes. Reutilizar los nombres, visibilidad, estilo y política de errores del proyecto. Mantener la firma `void modificar(int posicion, int nuevoDato)`.
2. Respetar la convención de indexación ya usada por las demás operaciones. Si no existe una convención establecida, documentar y aplicar indexación desde cero.
3. Validar ambos límites antes del recorrido: rechazar `posicion < 0` y `posicion >= cantidadDeElementos`. La cantidad debe obtenerse de un campo/operación de tamaño disponible en O(1), para que esta comprobación no requiera recorrer la lista.
4. Si la implementación no dispone de un tamaño en O(1), no calcular el límite superior mediante un recorrido previo, porque incumpliría la validación antes del recorrido solicitada. En ese caso, incorporar y mantener un contador de nodos en las operaciones de inserción/eliminación correspondientes, o informar que la representación actual no permite satisfacer estrictamente el requisito sin ampliar ese mantenimiento.
5. Para una posición válida, iniciar en la cabeza y avanzar exactamente `posicion` enlaces hasta el nodo objetivo. Asignar `nuevoDato` únicamente a su campo `dato`.
6. Para una posición inválida, no modificar la lista y comunicar el rechazo mediante el mecanismo ya adoptado por el proyecto; no ignorar el error silenciosamente.
7. No cambiar referencias `siguiente`, la cabeza, la cantidad de nodos ni otros datos de la lista durante la modificación.

## Secuencia del método

1. Consultar el tamaño actual en O(1).
2. Comprobar `posicion < 0 || posicion >= tamaño`.
3. Si la posición no es válida, reportar el error según la convención del proyecto y terminar.
4. Si es válida, recorrer desde la cabeza con un contador hasta alcanzar la posición.
5. Reemplazar el campo `dato` del nodo encontrado con `nuevoDato`.

## Estructuras y complejidad

- Estructura existente: lista enlazada y nodos actuales; no crear nodos para esta operación.
- Validación: metadato de cantidad de elementos disponible en O(1).
- Recorrido: referencia local al nodo actual y contador entero.
- Tiempo: O(posicion), acotado por O(n).
- Espacio auxiliar: O(1).

## Criterios de aceptación

- Rechaza una posición negativa y una posición igual o mayor que el tamaño antes de recorrer hasta un nodo.
- No cambia ningún dato ni enlace cuando la posición es inválida.
- En posición válida, solo cambia `dato` del nodo destino; la cabeza, los enlaces y el tamaño permanecen iguales.
- Cubre explícitamente la posición inicial y la última posición válida según la indexación del proyecto.