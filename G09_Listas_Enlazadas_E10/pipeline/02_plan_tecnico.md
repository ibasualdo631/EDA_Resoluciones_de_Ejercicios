# Plan técnico — Lista enlazada genérica

## Objetivo para el desarrollador

Convertir la lista enlazada simple de enteros existente en una implementación parametrizada por `T`, con clases `ListaEnlazada<T>` y `Nodo<T>`. La solución debe almacenar valores de distintos tipos sin duplicar código, y conservar los nombres públicos, las operaciones y el comportamiento de la versión original.

La carpeta de este ejercicio no contiene fuentes; antes de implementar, localizar y leer la versión original y sus usos. Los nombres de operaciones que siguen describen contratos, no autorizan a reemplazar ni a inventar la API existente. Ajustar los nombres y firmas a la implementación real y a las convenciones del lenguaje.

## Arquitectura y responsabilidades

### `Nodo<T>`

Representa un elemento y su enlace al nodo siguiente.

- Dato: `T dato`.
- Enlace: `Nodo<T> siguiente`, nullable para representar el final de la lista.
- Constructor: recibe un dato `T` y deja el enlace siguiente sin sucesor.
- Acceso al dato y al enlace: conservar el nivel de visibilidad que requiera la implementación, favoreciendo encapsulación sin romper usos existentes.
- No contiene operaciones propias del comportamiento de la lista ni lógica de recorrido.

### `ListaEnlazada<T>`

Administra los nodos y mantiene las invariantes estructurales.

- Referencia inicial: `Nodo<T> cabeza`; nula cuando la lista está vacía.
- Referencia final: conservar `cola` solo si ya existe o si es necesaria para mantener el contrato de inserción al final con coste constante.
- Tamaño: conservarlo solo si ya forma parte del diseño; si existe, actualizarlo en cada inserción y eliminación.
- La lista es la única responsable de enlazar y desenlazar nodos.
- No imponer restricciones a `T` (por ejemplo, que sea comparable o numérico) a menos que el contrato original requiera explícitamente esa capacidad.

## Contratos de operaciones

Preservar el nombre, retorno y política observable de cada método existente. Los contratos siguientes sirven como guía para las operaciones equivalentes; si la versión original difiere, documentar y conservar esa diferencia.

| Operación conceptual | Contrato |
|---|---|
| Consultar si está vacía | Devuelve `true` exactamente cuando no hay nodos (`cabeza == null`). No modifica la lista. |
| Insertar al inicio | Recibe un elemento `T`; crea un nodo y lo convierte en cabeza. Si la lista no está vacía, el nuevo nodo apunta a la cabeza anterior. Si se mantiene `cola`, actualizarla cuando se inserta el primer nodo. |
| Insertar al final | Recibe un elemento `T`; agrega un nodo terminal conservando el orden anterior. Si está vacía, cabeza (y cola, si existe) apuntan al nodo nuevo; en otro caso, enlaza el final actual al nuevo nodo. |
| Recorrer/mostrar | Visita los nodos desde cabeza hasta el final, una vez cada uno y en orden. La operación no altera los enlaces. Si presenta los datos, la conversión a texto corresponde al tipo/lenguaje o al comportamiento preexistente; el algoritmo de avance no interpreta `T`. |
| Eliminar por valor, si existe | Busca según la regla de igualdad del contrato original, desenlaza la ocurrencia que esa versión elimina y comunica el resultado según su retorno actual. Corregir cabeza/cola/tamaño al eliminar. Si no existe coincidencia, la estructura queda sin cambios. No ordenar ni hacer operaciones aritméticas sobre `T`. |

### Reglas transversales

- Conservar el comportamiento existente frente a elementos `null` (o el equivalente del lenguaje). Si no está definido, decidirlo explícitamente antes de cambiar la API; no confundir un dato nulo con una referencia de enlace nula.
- Cuando la eliminación compara por valor, usar el mecanismo de igualdad previsto por el lenguaje y por la implementación. No añadir un límite genérico de ordenamiento: eliminar por igualdad no implica que `T` sea ordenable.
- Si existe eliminación por posición o por nodo y no por valor, preservar esa variante y su contrato; la comparación de igualdad solo aplica a las operaciones que realmente buscan un valor.
- Si las operaciones previas tienen semánticas distintas para insertar (por ejemplo, al inicio o al final), mantenerlas sin sustituirlas por una política nueva.

## Invariantes

1. Una lista vacía tiene `cabeza == null`; si se conserva `cola`, también `cola == null` y el tamaño es cero.
2. Cada enlace `siguiente` conduce al siguiente nodo de la secuencia; el último nodo apunta a `null`.
3. Si la lista no está vacía y hay referencia `cola`, esta identifica el último nodo.
4. No hay ciclos en la lista simple.
5. Si se mantiene un contador, coincide con el número de nodos alcanzables desde cabeza.
6. Insertar o eliminar cambia enlaces y, cuando corresponda, los extremos y el contador; no transforma ni inspecciona el contenido de `T`, salvo la igualdad solicitada por una búsqueda por valor.

## Secuencia de implementación

1. **Inspeccionar:** ubicar la implementación original, sus pruebas y todos sus puntos de uso. Anotar API pública, política de inserción/eliminación, manejo de vacíos/null y posibles referencias a `cola` o contador.
2. **Generalizar el nodo:** declarar `Nodo<T>` y cambiar el tipo del campo de dato, constructor y accesores pertinentes de `int` a `T`. Parametrizar el enlace como `Nodo<T>`.
3. **Generalizar la lista:** declarar `ListaEnlazada<T>`, cambiar cabeza/cola a `Nodo<T>` y sustituir por `T` cada tipo concreto que represente elementos en firmas, variables y retornos.
4. **Preservar algoritmos:** adaptar las operaciones una por una, conservando sus ramas y efectos sobre los enlaces. Mantener cualquier comparación de valor existente con semántica de igualdad; eliminar conversiones, cálculos u ordenamientos que hayan sido usados solo por asumir enteros.
5. **Actualizar usos:** especificar el parámetro genérico en declaraciones e instancias existentes. Migrar los usos de enteros al envoltorio/tipo correspondiente del lenguaje (por ejemplo, `Integer` en Java), sin confundir tipo de dato almacenado con índices o contadores, que pueden seguir siendo enteros.
6. **Validar:** ejecutar las pruebas focalizadas y las comprobaciones del proyecto; corregir incompatibilidades derivadas de la generalización sin alterar funcionalidades no relacionadas.
7. **Documentar:** explicar cambios de tipos, comportamiento preservado e independencia de los algoritmos estructurales respecto de `T`.

## Estrategia de pruebas y aceptación

- Compila la lista parametrizada y el nodo, sin tipos crudos ni conversiones evitables en el código de producción.
- Prueba el tipo originalmente soportado (como `Integer`) y, además, `String` y una clase simple `Alumno`, si el entorno de pruebas lo permite.
- Cubre lista vacía, inserción según cada operación disponible, recorrido en orden, eliminación en cabeza/medio/final cuando corresponda, valor ausente y eliminación del único nodo.
- Comprueba invariantes de cabeza, cola y tamaño si esos campos existen.
- Comprueba que insertar y recorrer objetos no requiere que implementen suma, ordenamiento o comparación relacional.
- Comprueba igualdad de la búsqueda/eliminación de acuerdo con el contrato del proyecto (por identidad o igualdad lógica); no escoger una semántica nueva accidentalmente.
- Todas las pruebas preexistentes relevantes continúan pasando y las firmas de los clientes se actualizan únicamente donde la genericidad lo exige.

## Explicación técnica esperada

La genericidad cambia el tipo de los valores almacenados y el tipo asociado de los nodos: `int` se sustituye por `T`, y la lista conserva la relación de tipos `ListaEnlazada<T>` → `Nodo<T>`. No cambia la topología de la estructura. Recorrer consiste en seguir `siguiente`; insertar consiste en crear un nodo y ajustar referencias; eliminar consiste en encontrar el nodo según la operación disponible y reparar las referencias vecinas y extremos. Esas operaciones trabajan sobre enlaces, no sobre las propiedades internas del dato. Solo una búsqueda por valor necesita igualdad; ninguna operación estructural exige aritmética u orden entre valores.

## Entregables del desarrollador

- Implementación genérica de lista y nodo integrada en la ubicación original.
- Actualización mínima de instancias/usos que dejen de compilar al parametrizar las clases.
- Pruebas de los tipos y casos definidos arriba, adaptadas a las operaciones efectivamente presentes.
- Resumen breve de los cambios y de las decisiones de compatibilidad que haya sido necesario tomar.