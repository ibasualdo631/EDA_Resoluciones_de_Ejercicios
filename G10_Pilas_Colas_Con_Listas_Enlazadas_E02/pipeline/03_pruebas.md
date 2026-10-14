# Pruebas — Cola enlazada de enteros

## Alcance

Se verifican las operaciones de `ColaEnlazada`, el comportamiento FIFO, el manejo de una cola vacía y las invariantes de los punteros `head` y `tail`. Como ambos punteros son privados, las pruebas de sus valores usan reflexión en un arnés temporal; no se modificó la visibilidad de la implementación ni se agregó infraestructura de pruebas al proyecto.

## Casos de prueba

| ID | Caso | Resultado esperado |
|---|---|---|
| P01 | Crear una cola nueva. | `estaVacia()` es verdadero y `head`/`tail` son `null`. |
| P02 | Buscar e imprimir en una cola vacía. | La búsqueda devuelve falso; se imprime `[]`; la estructura no cambia. |
| P03 | Desencolar y consultar el frente estando vacía. | Ambas operaciones lanzan `IllegalStateException`; `head`/`tail` permanecen `null`. |
| P04 | Encolar un entero negativo en una cola vacía. | `head` y `tail` apuntan al mismo nodo; frente y búsqueda devuelven el valor correcto. |
| P05 | Desencolar el único nodo. | Se devuelve el valor encolado; `head` y `tail` quedan en `null` y la cola queda vacía. |
| P06 | Encolar varios valores, incluyendo cero y un duplicado. | La impresión refleja el orden FIFO; la búsqueda reconoce valores presentes y ausentes. |
| P07 | Desencolar una cola de varios nodos. | Los valores salen en orden FIFO; `tail` sigue siendo el mismo nodo mientras queden elementos. |
| P08 | Retirar el último elemento de una cola que tuvo varios nodos. | La cola queda vacía y tanto `head` como `tail` son `null`. |
| P09 | Encolar después de vaciar la cola. | La cola vuelve a funcionar; el nuevo elemento puede consultarse en el frente y `tail` deja de ser `null`. |

## Ejecución y resultados

- Se compilaron `ColaEnlazada.java` y un arnés temporal de pruebas con `javac`.
- Se ejecutaron 20 comprobaciones automatizadas agrupadas en los casos P01–P09: **20 aprobadas, 0 fallidas**.
- Se comprobaron directamente por reflexión los punteros privados en la cola recién creada, después de vaciarla desde uno y varios elementos, tras operaciones inválidas y después de reutilizarla.
- Las pruebas confirmaron que `tail` es `null` luego de desencolar el último elemento y que se conserva cuando todavía quedan nodos.
- Se verificó que búsqueda e impresión no alteran el orden ni la estructura observada de la cola.

## Conclusión

La implementación cumple los casos funcionales y las invariantes evaluadas. En particular, al vaciar la cola, `head` y `tail` quedan en `null`; la posterior inserción restablece correctamente ambos extremos.