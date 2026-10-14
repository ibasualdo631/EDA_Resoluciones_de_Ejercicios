# Análisis — Cola enlazada de enteros

## 1. Objetivo

Implementar una cola FIFO (primero en entrar, primero en salir) de números enteros mediante una lista simplemente enlazada. La estructura debe permitir agregar y retirar elementos, consultar su frente, comprobar si está vacía, buscar un valor e imprimir sus elementos.

## 2. Requerimientos funcionales

1. **Encolar:** agregar un entero al final de la cola, sin modificar el orden relativo de los elementos ya presentes.
2. **Desencolar:** retirar y devolver el elemento del frente. La operación debe conservar el orden FIFO.
3. **Consultar el frente:** devolver el entero del frente sin retirarlo.
4. **Verificar si está vacía:** informar si la cola no contiene elementos.
5. **Buscar un valor:** indicar si un entero dado aparece en la cola. La búsqueda no debe modificarla.
6. **Imprimir contenido:** mostrar los valores desde el frente hasta el final, en orden FIFO. La impresión no debe modificar la cola.

## 3. Representación y reglas de negocio

- Cada nodo almacena un entero y una referencia al nodo siguiente.
- La cola mantiene dos referencias: `head`, al primer nodo, y `tail`, al último nodo.
- Cuando la cola está vacía, tanto `head` como `tail` deben ser `null`.
- Cuando contiene un solo elemento, `head` y `tail` deben referirse al mismo nodo, cuyo siguiente es `null`.
- Cuando contiene varios elementos, `head` identifica el frente, `tail` el final y `tail.next` es `null`.
- Al desencolar el único elemento, se debe actualizar `head` y también `tail` a `null`; de lo contrario, `tail` quedaría apuntando a un nodo que ya no pertenece a la cola.
- Encolar en una cola vacía debe asignar el nuevo nodo a ambas referencias. Encolar en una cola no vacía debe enlazar el nodo desde `tail` y actualizar `tail`.

## 4. Importancia de `head` y `tail`

`head` permite acceder directamente al frente para consultarlo y desencolarlo. `tail` permite acceder directamente al último nodo para encolar en tiempo constante.

Si solo se usara `head`, para encolar habría que recorrer la lista desde el frente hasta encontrar el último nodo. Esa operación costaría **O(n)** por inserción, donde *n* es la cantidad de elementos. Mantener `tail` evita ese recorrido y hace que encolar cueste **O(1)**. Sin `tail`, insertar *n* elementos sucesivamente podría requerir **O(n²)** tiempo total; con ambas referencias, esas inserciones cuestan **O(n)** en conjunto.

## 5. Casos límite y manejo de errores

- **Desencolar una cola vacía:** no existe elemento que devolver. La implementación debe manejar esta situación de forma explícita y coherente (por ejemplo, lanzar una excepción o comunicar el error); la consigna no fija un mecanismo concreto.
- **Consultar el frente de una cola vacía:** tampoco existe un valor que devolver y debe manejarse explícitamente, sin modificar la estructura.
- **Retirar el último elemento:** verificar que ambas referencias queden en `null`.
- **Encolar en una cola vacía:** comprobar que `head` y `tail` queden apuntando al único nodo.
- **Valores repetidos:** la búsqueda debe informar presencia si encuentra al menos una coincidencia; los duplicados son elementos válidos.
- **Valores negativos y cero:** deben aceptarse, dado que el tipo solicitado es entero y no se especifica una restricción de rango.
- **Búsqueda o impresión en cola vacía:** la búsqueda debe informar ausencia y la impresión no debe mostrar elementos; ninguna de las dos operaciones debe alterar el estado.

## 6. Complejidad esperada

| Operación | Complejidad esperada |
|---|---:|
| Encolar | O(1), manteniendo `tail` |
| Desencolar | O(1), manteniendo `head` |
| Consultar el frente | O(1) |
| Verificar si está vacía | O(1) |
| Buscar un valor | O(n) |
| Imprimir contenido | O(n) |

## 7. Alcance y puntos no especificados

El enunciado determina el uso de una lista simplemente enlazada y las operaciones requeridas, pero no prescribe nombres de métodos, interfaz de usuario, formato de impresión ni mecanismo específico para reportar errores en operaciones sobre una cola vacía. Estos detalles deben definirse en el diseño técnico sin alterar el comportamiento FIFO ni las invariantes de la estructura.