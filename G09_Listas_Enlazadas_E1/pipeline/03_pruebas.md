# Pruebas y auditoría — ListaEnlazadaSimple

## Alcance

Verificar los estados de lista vacía y de un único nodo, el comportamiento de las inserciones y los invariantes del puntero `head`. Se inspeccionaron los campos privados mediante reflexión únicamente desde un arnés temporal de auditoría; la implementación no se modificó para habilitar la inspección.

## Casos ejecutados

| Caso | Acción y comprobaciones | Resultado |
|---|---|---|
| Lista vacía recién creada | `head == null`, `estaVacia() == true`, `getSize() == 0`; llamar a `imprimir()` no produce excepción. | Aprobado |
| Un nodo insertado al inicio | Insertar `17`; `head` referencia el nodo con dato `17`, su `siguiente == null`, el tamaño es 1 y la lista no está vacía. | Aprobado |
| Un nodo insertado al final | Insertar `-4` en una lista vacía; `head` referencia el nodo con dato `-4`, su `siguiente == null`, el tamaño es 1 y la lista no está vacía. | Aprobado |
| Actualización de `head` al insertar al inicio | Con `17` existente, insertar `8`; el nuevo `head` contiene `8`, enlaza con el nodo `17` y el tamaño es 2. | Aprobado |

## Evidencia de ejecución

Se compiló `ListaEnlazadaSimple.java` junto con un arnés temporal y se ejecutaron las aserciones anteriores. La ejecución terminó con `AUDIT_OK` y código de salida 0. Los archivos temporales se eliminaron.

## Resultado de auditoría

No se detectaron defectos en los comportamientos auditados. `head` se inicializa a `null`, se establece al insertar el primer nodo tanto al inicio como al final y se reasigna correctamente al insertar al inicio de una lista no vacía. En cada caso de un único nodo, el enlace `siguiente` termina en `null` y `size` coincide con la cantidad de nodos.