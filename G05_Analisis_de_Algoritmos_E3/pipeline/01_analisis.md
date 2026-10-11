# Análisis — Ejercicio 3: búsqueda binaria

## Cabecera de la entrega

**Institución:** [Nombre de la institución]  
**Carrera / asignatura:** [Carrera] — Estructuras de Datos y Algoritmos  
**Trabajo práctico:** G05 — Análisis de Algoritmos  
**Ejercicio:** 3 — Buscar un elemento en un vector ordenado mediante búsqueda binaria  
**Estudiante(s):** [Nombre(s) y apellido(s)]  
**Docente:** [Nombre del/de la docente]  
**Fecha:** [Fecha de entrega]  
**Lenguaje solicitado:** Java

## 1. Objetivo y contrato propuesto

Implementar en Java una búsqueda binaria iterativa que reciba un vector ordenado ascendentemente y un valor objetivo. Si lo encuentra, devuelve un índice válido del vector; si no lo encuentra, devuelve `-1`. Si el vector es `null`, se considera un argumento inválido y se propone lanzar `IllegalArgumentException`, con un mensaje claro. El vector vacío es válido y produce `-1`.

La precondición esencial es que los elementos estén ordenados de menor a mayor según el mismo criterio de comparación usado por la búsqueda. El algoritmo no ordena ni modifica el vector. Si hay valores duplicados, el contrato propuesto permite devolver cualquiera de sus índices; no promete encontrar la primera ni la última aparición.

## 2. Estrategia: división y conquista

La búsqueda mantiene un intervalo inclusivo de índices candidatos, inicialmente `inicio = 0` y `fin = n - 1`. En cada iteración calcula el índice central:

`medio = inicio + (fin - inicio) / 2`

Compara el valor objetivo con `vector[medio]`:

- Si son iguales, termina y devuelve `medio`.
- Si el objetivo es menor, descarta el centro y toda la mitad derecha: `fin = medio - 1`.
- Si el objetivo es mayor, descarta el centro y toda la mitad izquierda: `inicio = medio + 1`.

Esto aplica división y conquista: divide el conjunto candidato en mitades, descarta por completo una mitad basándose en el orden y continúa con la otra. La fórmula de `medio` evita el posible desbordamiento de `inicio + fin`.

### Justificación formal de la corrección

**Invariante:** si el objetivo aparece en el vector, su índice pertenece al intervalo `[inicio, fin]`.

- **Inicialización:** al comenzar, `[0, n - 1]` contiene todos los índices del vector.
- **Mantenimiento:** por estar ordenado ascendentemente, si `objetivo < vector[medio]`, ningún índice mayor o igual que `medio` puede contener el objetivo. Si `objetivo > vector[medio]`, ningún índice menor o igual que `medio` puede contenerlo. Por ello, las actualizaciones conservan todos y solamente los candidatos posibles.
- **Terminación:** si se encuentra igualdad, se devuelve un índice cuyo valor coincide con el objetivo. Si `inicio > fin`, el intervalo candidato está vacío; por el invariante, el objetivo no aparece y se devuelve `-1`.

La precondición de orden es indispensable: sin ella no se puede concluir que una mitad descartada no contenga el objetivo.

### Complejidad

Un recorrido lineal puede tener que inspeccionar los `n` elementos, por lo que su peor caso es `O(n)`. En búsqueda binaria, después de `k` iteraciones quedan a lo sumo `n / 2^k` candidatos. El proceso termina cuando esa cantidad es menor que uno:

`n / 2^k < 1` implica `k > log₂(n)`.

Por tanto, el peor caso requiere `⌊log₂(n)⌋ + 1` iteraciones para `n > 0`, es decir, `O(log n)` comparaciones. La reducción a la mitad en cada paso, no simplemente la comparación central, es lo que produce esta mejora. La versión iterativa usa `O(1)` espacio auxiliar.

## 3. Casos límite y de borde

| Caso | Comportamiento esperado |
|---|---|
| Objetivo en el extremo inicial (`índice 0`) | Se encuentra y devuelve `0`, incluso si el intervalo se reduce hasta el primer índice. |
| Objetivo en el extremo final (`índice n - 1`) | Se encuentra y devuelve `n - 1`. |
| Objetivo menor que el mínimo | Se descartan candidatos hasta que `inicio > fin`; devuelve `-1`. |
| Objetivo mayor que el máximo | Se descartan candidatos hasta que `inicio > fin`; devuelve `-1`. |
| Objetivo ausente dentro del rango mínimo–máximo | La búsqueda converge sin igualdad; devuelve `-1`. |
| Objetivo justo en el punto medio de la primera iteración | La primera comparación coincide y devuelve el índice central. |
| Vector de un elemento, valor coincidente | Compara el único elemento y devuelve `0`. |
| Vector de un elemento, valor no coincidente | El intervalo se agota tras la comparación y devuelve `-1`. |
| Vector vacío (`length == 0`) | Se inicializa `fin = -1`; no se entra al bucle y se devuelve `-1`. |
| Referencia nula (`null`) | Se rechaza explícitamente antes de acceder a `length`; contrato propuesto: lanzar `IllegalArgumentException`. |

## 4. Requerimientos de telemetría por consola

La ejecución debe permitir seguir cada iteración sin alterar el resultado de la búsqueda:

- Indicar el valor objetivo y el vector recibido, o al menos el tamaño del vector.
- Antes de cada comparación, imprimir los valores actuales de `inicio`, `fin`, `medio` y `vector[medio]` (valor evaluado).
- Mostrar el resultado de la comparación y la acción tomada: encontrado, mover `fin` a `medio - 1` o mover `inicio` a `medio + 1`.
- Al terminar, imprimir claramente el índice encontrado o que el elemento está ausente (`-1`).
- Para un vector vacío, informar que no hay iteraciones y que el resultado es `-1`.
- Para `null`, informar el argumento inválido antes de lanzar la excepción; no intentar calcular longitud ni leer elementos.

Formato sugerido por iteración:

`Iteración k: inicio=..., fin=..., medio=..., valorEvaluado=...`

La traza debe representar los valores usados efectivamente en la comparación. Si se implementa el algoritmo como método reutilizable, conviene separar la lógica del mensaje final o documentar el efecto de imprimir, pero para este ejercicio se requiere la traza visible en consola.

## 5. Borrador del Prompt Técnico Inicial para el Planificador

> Diseña un plan de implementación en Java para el Ejercicio 3: buscar un elemento en un vector ordenado ascendentemente mediante búsqueda binaria iterativa. No escribas todavía la implementación: especifica el contrato, la estructura propuesta, los pasos del algoritmo, las pruebas y los criterios de aceptación.
>
> Requisitos funcionales: recibir un vector `int[]` ordenado de menor a mayor y un objetivo; devolver un índice válido si se encuentra y `-1` si está ausente. No ordenar ni modificar el vector. Si hay duplicados, se puede devolver cualquier índice coincidente. Trata `null` como argumento inválido y propone lanzar `IllegalArgumentException` con mensaje claro. El vector vacío debe retornar `-1`.
>
> Usa límites inclusivos (`inicio`, `fin`), calcula `medio` con `inicio + (fin - inicio) / 2` y explica cómo se actualizan los límites según la comparación. La búsqueda binaria depende estrictamente de la precondición de orden ascendente. Incluye la demostración mediante invariante, la terminación, y la comparación de complejidad con búsqueda lineal: peor caso `O(log n)` frente a `O(n)`, con espacio auxiliar `O(1)`.
>
> El plan debe exigir traza por consola en cada iteración con `inicio`, `fin`, `medio` y el valor evaluado, además de la decisión adoptada y el resultado final. Debe contemplar: coincidencia en índice `0` y `n - 1`, ausencia menor que el mínimo, mayor que el máximo y dentro del rango, coincidencia con el medio inicial, vector unitario coincidente y no coincidente, vector vacío y referencia `null`. Define pruebas concretas para cada caso y valida que la traza y los resultados correspondan.
>
> Presenta el plan ordenado en tareas pequeñas, indicando los archivos/clases sugeridos solo si el repositorio existente los determina. Evita agregar dependencias externas y no amplíes el alcance con ordenamiento, interfaz gráfica ni búsqueda de primera/última ocurrencia.

## 6. Decisiones pendientes para etapas posteriores

- Confirmar si el equipo docente espera `IllegalArgumentException` para `null` o prefiere otro contrato de error.
- Confirmar si, ante duplicados, basta cualquier coincidencia o se requiere específicamente la primera/última posición.
- Adaptar la cabecera con los datos reales de institución, estudiante(s), docente y fecha.