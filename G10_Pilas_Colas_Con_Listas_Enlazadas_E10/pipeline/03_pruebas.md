# Pruebas — Lista doblemente enlazada

## Resultado

**Aprobado.** Se compiló `ListaDoble.java` con `javac -Xlint:all` y se ejecutó un arnés temporal de pruebas Java. Pasaron **33.040 aserciones**, incluyendo escenarios fijos y 250 operaciones mixtas determinísticas. No se encontraron errores ni advertencias de compilación.

## Escenarios verificados

| Escenario | Verificación | Resultado |
|---|---|---|
| Lista vacía | Los recorridos imprimen `[]`; eliminar devuelve `false`; `inicio` y `fin` son nulos. | Aprobado |
| Inserciones mixtas | Insertar varias veces al inicio y al final mantiene el orden esperado. | Aprobado |
| Recorrido hacia adelante | Los datos se visitan e imprimen desde `inicio` hasta `fin`. | Aprobado |
| Recorrido hacia atrás | Los datos se visitan e imprimen desde `fin` hasta `inicio`, en orden inverso al recorrido hacia adelante. | Aprobado |
| Eliminar el único nodo | La lista queda vacía y ambos extremos son nulos. | Aprobado |
| Eliminar el primero | `inicio` avanza y el nuevo primer nodo no conserva enlace `anterior`. | Aprobado |
| Eliminar el último | `fin` retrocede y el nuevo último nodo no conserva enlace `siguiente`. | Aprobado |
| Eliminar nodos intermedios | Los vecinos quedan enlazados en ambas direcciones y ambos recorridos conservan el orden esperado. | Aprobado |
| Elemento ausente | La operación devuelve `false` y el contenido y enlaces de la lista no cambian. | Aprobado |
| Valores duplicados | Se elimina solo la primera coincidencia; las restantes siguen en su posición relativa. | Aprobado |
| Operaciones mixtas repetidas | 250 inserciones/eliminaciones al azar con semilla fija, comparadas tras cada paso con un modelo `ArrayList`. | Aprobado |

## Comprobaciones estructurales

Después de cada escenario se verificaron los enlaces internos además del resultado impreso:

- Los extremos son nulos exactamente cuando la lista está vacía.
- Al recorrer hacia adelante, cada nodo apunta con `anterior` al nodo visitado justo antes.
- Al recorrer hacia atrás, cada nodo apunta con `siguiente` al nodo visitado justo antes.
- El último nodo del recorrido hacia adelante coincide con `fin`; el último del recorrido inverso coincide con `inicio`.
- Las secuencias obtenidas por ambos recorridos son inversas entre sí y coinciden con el modelo esperado.

El arnés de validación fue temporal y se ejecutó fuera del repositorio; no se agregó infraestructura de pruebas al proyecto.