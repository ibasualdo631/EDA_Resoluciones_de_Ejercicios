# Análisis — Ejercicio 10: lista enlazada genérica

## Objetivo

Transformar la implementación existente de una lista enlazada simple de enteros en una estructura genérica, parametrizada por `T`. La lista y sus nodos deben almacenar valores de distintos tipos sin duplicar la implementación.

## Alcance funcional

- Parametrizar las clases como `ListaEnlazada<T>` y `Nodo<T>` siguiendo la sintaxis del lenguaje y las convenciones del proyecto.
- Reemplazar el tipo concreto `int` del dato almacenado por `T` en los atributos, constructores, parámetros y valores de retorno afectados.
- Mantener las operaciones ya presentes en la lista, en particular recorrido, inserción y eliminación, y conservar su comportamiento.
- Verificar el uso con más de un tipo de dato; la consigna propone `Integer`, `String` y `Alumno`.
- Explicar explícitamente qué cambia, qué se conserva y por qué los algoritmos estructurales no dependen del tipo almacenado.

## Cambios y elementos que se mantienen

**Cambian:** la declaración genérica de lista y nodo, y cada referencia al tipo del elemento almacenado que actualmente esté fijada a `int`. Las firmas de métodos que reciban o devuelvan elementos también deben usar `T`.

**Se mantienen:** la estructura de enlaces (`siguiente`), las referencias de control de la lista (por ejemplo, `cabeza` y, si existe, `cola`), las condiciones de lista vacía, el orden de los nodos y la lógica de actualización de enlaces. Deben conservarse también los nombres y contratos públicos existentes siempre que no contradigan la consigna.

La representación del dato pasa a ser genérica; la topología de la lista sigue siendo la misma. No se debe duplicar la clase para cada tipo.

## Independencia de los algoritmos respecto de `T`

El recorrido avanza siguiendo referencias entre nodos hasta alcanzar el final; insertar crea o recibe un nodo y actualiza enlaces; eliminar desconecta un nodo ajustando el enlace anterior o la cabeza. Estas operaciones se apoyan en la estructura de nodos y referencias, no en operaciones aritméticas ni en el orden de los valores. Por eso funcionan igual independientemente de que `T` sea numérico, texto o un objeto.

Si la implementación existente elimina buscando un valor, esa búsqueda puede necesitar una comparación de igualdad conforme al lenguaje. Esto no cambia la lógica de enlaces ni requiere que los valores sean ordenables o sumables. No debe agregarse una restricción a `T` salvo que la implementación realmente la necesite y el contrato existente la justifique.

## Criterios de aceptación

1. La lista y el nodo están parametrizados con `T`; no quedan tipos concretos de entero en las posiciones que representan el dato almacenado.
2. Las operaciones originales siguen funcionando con el mismo comportamiento y orden.
3. Se puede instanciar y usar la estructura, al menos, con `Integer` y `String`; también se muestra o prueba su uso con `Alumno` si ese tipo existe o puede definirse en las pruebas.
4. Se cubren los casos estructurales relevantes: lista vacía, inserción al comienzo/final (según las operaciones disponibles), recorrido y eliminación, incluido el único nodo.
5. La explicación distingue claramente los cambios de tipo de las operaciones de enlaces que se mantienen.

## Información pendiente de confirmar al implementar

- No hay archivos de código en la carpeta del ejercicio, por lo que no se conocen el lenguaje, los nombres exactos de los métodos ni si la lista mantiene una referencia a la cola.
- Debe inspeccionarse la implementación original antes de modificarla; no se deben inventar ni eliminar operaciones para ajustarla al ejemplo.
- Si `Alumno` no existe, puede usarse una clase mínima de prueba, sin modificar innecesariamente el modelo de producción.

## Prompt inicial

> Analizá la implementación existente de la lista enlazada simple de enteros y convertíla en una versión genérica usando `ListaEnlazada<T>` y `Nodo<T>`, con la sintaxis propia del lenguaje del proyecto. Conservá las operaciones, nombres, contratos y comportamiento actuales de la lista. Reemplazá por `T` el tipo concreto del elemento en los nodos, constructores, atributos y firmas de métodos donde corresponda, sin cambiar la lógica de enlaces ni duplicar la implementación por tipo.
>
> Asegurá que pueda utilizarse con distintos tipos, por ejemplo `Integer`, `String` y `Alumno` (definí un tipo mínimo de prueba si no existe). Explicá qué partes del código cambian y cuáles se mantienen. Justificá que recorrido, inserción y eliminación dependen de las referencias y enlaces entre nodos, no de operaciones propias del tipo almacenado; si una búsqueda para eliminar compara valores, explicá el papel de la igualdad sin exigir operaciones aritméticas u ordenamiento.
>
> Validá la solución con los casos que correspondan a las operaciones existentes, incluyendo lista vacía, inserción, recorrido y eliminación de elementos y del único nodo. Antes de modificar, inspeccioná el código original; no supongas métodos ni estructuras que no estén presentes y señalá cualquier decisión que dependa del lenguaje o del contrato existente.