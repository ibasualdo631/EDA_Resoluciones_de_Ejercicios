# Pruebas — Cola simple de enteros

## Alcance

Revisión de `ColaEnteros.java`, enfocada en los límites de capacidad, la
transición entre cola vacía y no vacía, la coherencia de `frontIndex` y
`rearIndex`, y las excepciones.

## Casos verificados

| Caso | Resultado |
|---|---|
| Construcción con capacidad 0 o negativa | Pasa: lanza `IllegalArgumentException`. |
| Estado inicial | Pasa: vacía, tamaño 0 y no llena. |
| `front()` y `dequeue()` con cola vacía | Pasa: ambas lanzan `IllegalStateException`. |
| Insertar hasta la capacidad y consultar el frente | Pasa: queda llena, el frente conserva el primer valor y consultarlo no elimina elementos. |
| Insertar en cola llena | Pasa: lanza `IllegalStateException`. |
| Eliminar hasta vaciar | Pasa: respeta FIFO y restablece el estado vacío y el tamaño 0. |
| Volver a insertar después de vaciar | Pasa: los índices vuelven a una posición válida y la cola puede llenarse de nuevo. |
| Llegar al final físico tras eliminar parcialmente | Pasa: se informa llena y no se reutilizan posiciones liberadas antes de `front`, según la cola lineal solicitada. |

## Resultado

No se encontraron defectos en los estados de frontera ni en el manejo de
excepciones. `isFull()` considera tanto la cantidad como el fin físico del
arreglo; por eso puede devolver `true` cuando `size() < capacidad` si `rear`
ya llegó a la última posición. Este comportamiento es el esperado para una
cola lineal no circular.

Se compiló la clase junto con un arnés temporal de pruebas y se ejecutaron
los casos anteriores; todos finalizaron correctamente. El arnés y los
archivos `.class` temporales se eliminaron después de la ejecución.