# Análisis: cola genérica

## Objetivo y alcance

Adaptar la cola del ejercicio para que una misma estructura pueda almacenar elementos de distintos tipos, manteniendo el comportamiento FIFO (primero en entrar, primero en salir). La solución se implementará en Java y se comprobará con una cola de nombres (`String`) y otra de objetos `Cliente`.

El alcance es generalizar el tipo de elemento que maneja la cola. No incluye cambiar sus operaciones, su representación enlazada ni las reglas actuales para operar con una cola vacía, salvo lo necesario para preservar su comportamiento.

## Base y supuestos del análisis

- La consigna del Ejercicio 4 y las instrucciones del equipo son la fuente de requisitos.
- El proyecto está planteado en Java y la cola pertenece al ejercicio de pilas y colas con listas enlazadas.
- FIFO debe conservarse independientemente de la clase de los elementos.
- `Cliente` es un tipo de objeto de prueba; sus atributos y constructor concretos se definirán de acuerdo con la solución del ejercicio.
- No se especifica un framework de pruebas. Las comprobaciones pueden expresarse con aserciones o con el mecanismo de pruebas que ya use el proyecto.
- No se detallan contratos para valores `null` ni para extraer/consultar una cola vacía. Deben conservarse los contratos de la implementación existente; si no están definidos, no se deben inventar como parte de este cambio.
- No se encontraron archivos fuente `.java` ni artefactos de arquitectura en el material disponible al redactar este análisis. Por ello, los nombres exactos de operaciones y las reglas actuales de error quedan sujetos a confirmación al inspeccionar la implementación.

## Requerimientos funcionales

- **REQ-001 — Tipo parametrizable:** La cola debe poder declararse para un tipo de elemento concreto, de modo que una instancia acepte y devuelva elementos de ese tipo sin conversiones explícitas del cliente.
- **REQ-002 — Aislamiento de tipos:** Cada instancia debe trabajar con un único tipo declarado. Una cola de nombres no debe aceptar objetos `Cliente`, ni una cola de `Cliente` debe aceptar nombres.
- **REQ-003 — Inserción sin ordenar por tipo:** Insertar un elemento debe conservar su posición de llegada; la cola no debe comparar ni reordenar elementos según sus valores o su clase.
- **REQ-004 — Extracción FIFO:** Al retirar elementos, debe obtenerse primero el que llevaba más tiempo en la cola. El resultado debe ser idéntico en cuanto al orden para `String` y `Cliente`.
- **REQ-005 — Consulta FIFO:** Si la cola ofrece una operación para consultar el próximo elemento sin retirarlo, esta debe señalar al elemento más antiguo y no alterar la secuencia.
- **REQ-006 — Independencia entre instancias:** Operar sobre una cola no debe cambiar el contenido ni el orden de otra cola, aunque sus elementos sean de clases diferentes.
- **REQ-007 — Estados de la cola:** Los casos vacía, un elemento y varios elementos deben seguir siendo válidos. Al vaciar una cola y volver a insertar, la secuencia FIFO debe comenzar correctamente, respetando el comportamiento previo de la implementación.

## Abstracción que preserva FIFO

La cola debe tratar el elemento como un valor de tipo genérico y separar ese tipo de la regla de orden. La estructura enlazada mantiene sus referencias de inicio y final: insertar enlaza el nuevo elemento al final, y extraer obtiene y desvincula el elemento del inicio. Así, la relación entre enlaces y extremos determina FIFO; el tipo concreto (`String`, `Cliente` u otro) solo determina qué elemento se almacena y se devuelve. Ninguna de esas operaciones necesita conocer atributos, comparar objetos ni cambiar la regla de llegada y salida.

## Escenarios de uso y aceptación

### Escenario 1 — Cola de nombres (prioridad P1)

Como usuario de la estructura, quiero encolar nombres y recuperarlos en el orden de llegada.

**Prueba independiente:** crear una cola de `String`, insertar `"Ana"`, `"Luis"` y `"Mora"` y extraer los tres.

**Aceptación:**

1. Dado que la cola está vacía, cuando se insertan esos tres nombres en ese orden, entonces las extracciones producen `"Ana"`, `"Luis"` y `"Mora"` en ese orden.
2. Dado que la cola contiene esos nombres, cuando se consulta el próximo elemento (si existe esa operación), entonces se obtiene `"Ana"` y la siguiente extracción sigue siendo `"Ana"`.
3. Dado que una cola está declarada para `String`, entonces la comprobación de tipos de Java impide insertar un `Cliente`.

### Escenario 2 — Cola de objetos `Cliente` (prioridad P1)

Como usuario de la estructura, quiero encolar objetos `Cliente` y recuperarlos en el orden en que fueron agregados.

**Prueba independiente:** crear instancias distintas `clienteA`, `clienteB` y `clienteC`, insertarlas en ese orden y extraerlas.

**Aceptación:**

1. Dado que la cola de `Cliente` está vacía, cuando se insertan `clienteA`, `clienteB` y `clienteC`, entonces las extracciones devuelven esas mismas instancias en el mismo orden.
2. La prueba verifica el orden mediante identidad de referencia o, si la implementación existente establece otra convención, mediante los datos del objeto; no depende de que `Cliente` implemente comparación.
3. Dado que una cola está declarada para `Cliente`, entonces la comprobación de tipos de Java impide insertar un `String`.

### Escenario 3 — Estados y aislamiento (prioridad P2)

Como usuario de la estructura, quiero que las transiciones entre vacía, no vacía y vacía nuevamente sigan funcionando para cualquier tipo de elemento.

**Aceptación:**

1. Insertar un único elemento y extraerlo devuelve ese elemento y deja la cola vacía.
2. Vaciar una cola con varios elementos y volver a insertar comprueba que el elemento nuevo puede retirarse correctamente.
3. Intercalar operaciones en la cola de nombres y en la cola de clientes no altera el orden ni el contenido pendiente de ninguna de ellas.
4. Si la implementación tiene operaciones de extracción o consulta sobre cola vacía, sus resultados o errores deben coincidir con el contrato previo.

## Plan de pruebas

1. **Compilación y tipos:** compilar el código Java y construir instancias tipadas de `String` y `Cliente`. Comprobar que las operaciones devuelven el tipo esperado sin casts. Verificar que las inserciones cruzadas (`Cliente` en cola de `String` y `String` en cola de `Cliente`) no compilan; estas comprobaciones negativas pueden hacerse como ejemplos de compilación separados y no como pruebas que deban compilar junto con la solución.
2. **FIFO con `String`:** insertar al menos tres nombres diferentes; extraer y comparar cada resultado con la secuencia de inserción. Comprobar la consulta del frente sin extracción si esa operación forma parte de la API.
3. **FIFO con `Cliente`:** insertar al menos tres instancias diferentes y verificar que se recuperen en el orden insertado. Preferir comprobar que son las mismas referencias insertadas, sin exigir un `equals` o un ordenamiento propio de `Cliente`.
4. **Límites e independencia:** probar cola vacía, un elemento, extracción hasta vaciar, reinserción después de vaciar y operaciones intercaladas sobre ambas colas.
5. **Compatibilidad de comportamiento:** comparar la semántica de las operaciones y sus respuestas/errores de frontera con la implementación original cuando esta esté disponible.

## Criterios de éxito

- **SC-001:** El 100 % de las comprobaciones de orden para la secuencia de nombres pasa y las extracciones coinciden exactamente con el orden de inserción.
- **SC-002:** El 100 % de las comprobaciones de orden para la secuencia de objetos `Cliente` pasa y las extracciones corresponden a las instancias insertadas en orden.
- **SC-003:** Ambas colas pueden utilizarse sin casts explícitos y las inserciones de tipos incompatibles se rechazan durante la compilación.
- **SC-004:** Las pruebas de cola vacía, elemento único, vaciado, reinserción e independencia pasan sin cambiar el comportamiento previo de las operaciones existentes.

## Entidades de prueba

- **Elemento genérico:** valor que la cola almacena y entrega; su tipo concreto no debe influir en el orden FIFO.
- **Nombre (`String`):** dato textual empleado para demostrar el uso con un tipo de referencia común.
- **`Cliente`:** objeto de dominio empleado para demostrar el uso con objetos; las pruebas requieren instancias diferenciables, pero no fijan atributos de dominio no indicados en la consigna.
- **Cola:** secuencia de elementos con inserción por un extremo y extracción por el otro, cuyo orden observable es FIFO.

## Fuera de alcance

- Cambiar el orden FIFO o introducir prioridades.
- Ordenar, comparar o transformar los elementos.
- Definir nuevos requisitos de concurrencia, capacidad o persistencia.
- Especificar una política nueva para `null` o para operaciones sobre una cola vacía.
